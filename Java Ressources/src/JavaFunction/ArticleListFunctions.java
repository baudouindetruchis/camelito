package JavaFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;

import obj.Article;
import obj.ClientCommand;
import obj.User;

public class ArticleListFunctions {

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * Page shopping Recupère la liste d'articles dont le stock est > a 0 Ainsi que
	 * les quantitée indiquée dans le panier actuel du client et stock cette liste
	 * dans l'artibue de session articleList
	 * 
	 * @param session
	 */
	public static void setAllStockList(HttpSession session) {

		User user = (User) session.getAttribute("user");
		int user_id = user.getId();

		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			// Get curent cart
			Object[] cartDetail = getCurrentCartDetail(con, user_id);
			@SuppressWarnings("unchecked")
			List<Integer> list_id_articles = (List<Integer>) cartDetail[0];
			@SuppressWarnings("unchecked")
			List<Integer> list_quantities = (List<Integer>) cartDetail[1];

			// get all available article
			PreparedStatement getStock = con.prepareStatement("SELECT * FROM public.articles WHERE available != 0 ");
			ResultSet allStock = getStock.executeQuery();
			if (allStock == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {

				List<Article> lArt = new ArrayList<Article>();
				Article anArticle;
				while (allStock.next()) {
					anArticle = getAnArticleFromRsStock(allStock, list_id_articles, list_quantities, con);

					lArt.add(anArticle);
				}
				session.setAttribute("articleList", lArt);
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet d'afficher les article victime de leur succes
	 */
	public static void setNotAvailableList(HttpSession session) {
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			// get all not available article
			PreparedStatement getStock = con.prepareStatement("SELECT * FROM public.articles WHERE available = 0 ");
			ResultSet allStock = getStock.executeQuery();
			if (allStock == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {

				List<Article> lArt = new ArrayList<Article>();
				Article anArticle;
				while (allStock.next()) {
					anArticle = getAnArticleFromRsStock(allStock, new ArrayList<Integer>(), new ArrayList<Integer>(),
							con);

					lArt.add(anArticle);
				}
				session.setAttribute("articleNotAvailableList", lArt);
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * return the deatail (list_ article_id and list_quantity) of user current cart
	 * 
	 * @param con
	 * @param user_id
	 * @return Object[] { list_id_articles, list_quantities }
	 */
	private static Object[] getCurrentCartDetail(Connection con, int user_id) {
		List<Integer> list_id_articles = null;
		List<Integer> list_quantities = null;

		try {
			PreparedStatement getCart = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet theCart = getCart.executeQuery();
			if (theCart == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				// recuperation de la liste de course en cours
				theCart.next();
				Object array_id_articles;
				Object array_quantities;
				try {
					array_id_articles = theCart.getArray("list_id_articles").getArray();
					array_quantities = theCart.getArray("liste_quantities").getArray();
				} catch (PSQLException e) {
					// initialisation si liste null
					array_id_articles = new Integer[0];
					array_quantities = new Integer[0];
				}

				if (array_id_articles instanceof Integer[] && array_quantities instanceof Integer[]) {
					// passage au format liste java plutot que sql
					Integer[] tmp_list_id_articles = (Integer[]) array_id_articles;
					Integer[] tmp_list_id_quantities = (Integer[]) array_quantities;
					list_id_articles = new ArrayList<Integer>(Arrays.asList(tmp_list_id_articles));
					list_quantities = new ArrayList<Integer>(Arrays.asList(tmp_list_id_quantities));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Object[] cartDetail = new Object[] { list_id_articles, list_quantities };
		return cartDetail;
	}

	/**
	 * Check if every quantity are inferior to the available stock Return a boolean
	 * following if all quantity are inferior or not
	 * 
	 * @param session
	 * @return
	 */
	public static boolean isCommandValid(HttpSession session) {
		boolean allArticlesAreInStock = true;

		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {

			int user_id = user.getId();
			// complete the part "mon panier"
			// there should only be one cart by user whith a false status
			PreparedStatement getCart = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet theCart = getCart.executeQuery();
			if (theCart == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				theCart.next();
				List<Article> myListArt = ArticleListFunctions.getCart(theCart, con);

				if (!myListArt.isEmpty()) {
					int stock;
					int quantity;
					for (Article anArticle : myListArt) {
						stock = anArticle.getStock();
						quantity = anArticle.getQuantity();
						allArticlesAreInStock = allArticlesAreInStock && (stock >= quantity);
					}
				} else {
					allArticlesAreInStock = false;
				}
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allArticlesAreInStock;
	}

	/**
	 * remove reserved quantity from available stock for every article in the cart
	 * 
	 * @param session
	 */
	public static void decreaseStockForCart(HttpSession session) {
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			// complete the part "mon panier"
			// there should only be one cart by user whith a false status
			PreparedStatement getCart = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet theCart = getCart.executeQuery();
			if (theCart == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				theCart.next();
				List<Article> myListArt = ArticleListFunctions.getCart(theCart, con);
				for (Article art : myListArt) {
					decreaseStockForArticle(art);
				}
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * remove reserved quantity from available stock for a given article
	 * 
	 * @param anArticle
	 */
	private static void decreaseStockForArticle(Article anArticle) {
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			// statement to update available stock
			int id_article = anArticle.getId();
			int currentStock = anArticle.getStock();
			int quantity = anArticle.getQuantity();
			int newStock = currentStock - quantity;
			PreparedStatement pst = con.prepareStatement(
					"UPDATE public.articles SET available = " + newStock + " WHERE id = " + id_article);
			pst.executeQuery();

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * When looping over a ResultSet create correcponding object Article
	 * 
	 * @param allStock
	 * @param list_id_articles
	 * @param list_quantities
	 * @param con
	 * @return
	 */
	private static Article getAnArticleFromRsStock(ResultSet allStock, List<Integer> list_id_articles,
			List<Integer> list_quantities, Connection con) {
		Article anArticle = new Article();
		try {
			// get value from bdd
			int id_article = allStock.getInt("id");
			String description = allStock.getString("description");
			int id_store = allStock.getInt("id_store");
			String name = allStock.getString("name");
			int stock = allStock.getInt("available");
			;
			float real_price = allStock.getInt("initial_price");
			float selling_price = allStock.getInt("selling_price");

			String pic = allStock.getString("pic");

			// get quantity from user current cart
			int quantity = 0;
			if (list_id_articles.contains(id_article)) {
				int index_of_article = list_id_articles.indexOf(id_article);
				quantity = list_quantities.get(index_of_article);
			}

			// SQL to connect to a store
			PreparedStatement pstStore;
			ResultSet rsStore;
			String store = "";
			try {
				pstStore = con.prepareStatement("SELECT * FROM public.stores WHERE id = " + id_store);
				rsStore = pstStore.executeQuery();
				rsStore.next();

				// get data on the store
				store = rsStore.getString("name");

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// create corresponding article object
			anArticle.setId(id_article);
			anArticle.setDescription(description);
			anArticle.setMagasin(store);
			anArticle.setName(name);
			anArticle.setStock(stock);
			anArticle.setQuantity(quantity);
			anArticle.setReal_price(real_price);
			anArticle.setSelling_price(selling_price);
			anArticle.setImg(pic);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return anArticle;
	}

	/**
	 * Page shopping et panier Recuppère le panier actuel du client Modifi la
	 * quantité d'un article donné dans la Bdd
	 * 
	 * @param request
	 * @return un message expliquant ce qui a raté
	 */
	public static String modifQuantity(HttpServletRequest request) {
		Integer id_article = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("act");

		// msg permet de retrouner des info via la reponse html
		String msg = "";

		// connection a la bdd
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			// there should only be one cart by user whith a false status
			PreparedStatement getCart = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet theCart = getCart.executeQuery();
			if (theCart == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				// recuperation de la liste de course en cours
				theCart.next();
				Object array_id_articles;
				Object array_quantities;
				boolean is_new_cart = false;
				try {
					array_id_articles = theCart.getArray("list_id_articles").getArray();
					array_quantities = theCart.getArray("liste_quantities").getArray();
				} catch (PSQLException e) {
					// initialisation si liste null
					array_id_articles = new Integer[0];
					array_quantities = new Integer[0];
					is_new_cart = true;
				}

				if (array_id_articles instanceof Integer[] && array_quantities instanceof Integer[]) {
					// passage au format liste java plutot que sql
					Integer[] tmp_list_id_articles = (Integer[]) array_id_articles;
					Integer[] tmp_list_id_quantities = (Integer[]) array_quantities;
					List<Integer> list_id_articles = new ArrayList<Integer>(Arrays.asList(tmp_list_id_articles));
					List<Integer> list_quantities = new ArrayList<Integer>(Arrays.asList(tmp_list_id_quantities));

					// recupère l'endroit dans la liste de l'artique selectionné
					int idToChangeLocation = list_id_articles.indexOf(id_article);
					if (idToChangeLocation == -1) {
						// ou le rajoute s'il n'est pas present dans la bdd
						list_id_articles.add(id_article);
						list_quantities.add(0);
						idToChangeLocation = list_id_articles.indexOf(id_article);
					}
					// calcul la nouvelle valeur
					int newVal = 0;
					int pastVal = list_quantities.get(idToChangeLocation);
					switch (action) {
					case "less":
						newVal = (pastVal - 1);
						list_quantities.set(idToChangeLocation, newVal);
						break;
					case "more":
						newVal = (pastVal + 1);
						list_quantities.set(idToChangeLocation, newVal);
						break;
					case "supp":
						break;
					default:
						System.out.println("unkonwn action in actionTab in panier click");
						break;
					}
					// et update la liste
					list_quantities.set(idToChangeLocation, newVal);

					if (newVal == 0) {
						list_quantities.remove(idToChangeLocation);
						list_id_articles.remove(idToChangeLocation);
						msg = "article suprimé de votre liste";
					} else if (newVal == -1) {
						list_quantities.remove(idToChangeLocation);
						list_id_articles.remove(idToChangeLocation);
						msg = "impossible d'avoir des quatité negative";
					} else {
						msg = "le stock disponible est malhersment limite";
						// SQL to connect to the article
						PreparedStatement pstArticle = con
								.prepareStatement("SELECT * FROM public.articles WHERE id = " + id_article);
						ResultSet rsArticle = pstArticle.executeQuery();
						rsArticle.next();
						// get stock of the article
						int theStock = rsArticle.getInt("available");
						// si quantité supp au stock alors impossible on retourne en arrière
						if (theStock < newVal) {
							list_quantities.set(idToChangeLocation, pastVal);
						}
					}

					// maj de la bdd avec les nouvelle valeur
					String str_list_quantities = listToString(list_quantities);
					String str_list_id_articles = listToString(list_id_articles);
					PreparedStatement editQuantity;
					if (!is_new_cart) {
						editQuantity = con.prepareStatement("UPDATE public.carts SET liste_quantities = '"
								+ str_list_quantities + "' WHERE id_user = " + user_id + " AND status = false");
						editQuantity.execute();
						editQuantity = con.prepareStatement("UPDATE public.carts SET list_id_articles = '"
								+ str_list_id_articles + "' WHERE id_user = " + user_id + " AND status = false");
						editQuantity.execute();
					} else {
						editQuantity = con.prepareStatement(
								"INSERT INTO carts(id_user, list_id_articles, liste_quantities) VALUES(" + user_id
										+ ", '" + str_list_id_articles + "', '" + str_list_quantities + "')");
						editQuantity.execute();
					}
				} else {
					System.out.println("Problme de convertion de list");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * Convertie un List<Integer> en chaine de charactere utilisablr dans les
	 * requetes SQL
	 * 
	 * @param l
	 * @return
	 */
	private static String listToString(List<Integer> l) {
		StringBuilder sb = new StringBuilder();
		if (l.size() == 0) {
			sb.append("{}");
		} else {
			sb.append("{");
			for (Integer itm : l) {
				sb.append(itm);
				sb.append(", ");
			}
			sb.setLength(sb.length() - 2);
			sb.append("}");
		}
		return sb.toString();
	}

	/**
	 * recupère la liste des commande validé et met a jour l'attribut de sesion
	 * commandeList avec la liste des commande true (payé) du client
	 * 
	 * @param session
	 * @param con
	 * @param user_id
	 */
	public static void setCommandList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			// complete the part "mes commandes"
			PreparedStatement getCommandes = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = true");
			ResultSet commandes = getCommandes.executeQuery();
			if (commandes == null) {
				System.out.println("Erreur de connexion (commandes=null)");
			} else {
				List<ClientCommand> allClientCommand = new ArrayList<>();
				int id;
				ClientCommand aClientCommand;
				while (commandes.next()) {
					id = commandes.getInt("id");
					aClientCommand = new ClientCommand();
					aClientCommand.setId(id);

					List<Article> myListArt = ArticleListFunctions.getCart(commandes, con);
					for (Article anArt : myListArt) {
						aClientCommand.addArticle(anArt);
					}
					float total_price = ArticleListFunctions.getPriceOfList(myListArt);

					aClientCommand.setPriceTotal(total_price);
					allClientCommand.add(aClientCommand);
				}
				session.setAttribute("commandeList", allClientCommand);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * modifi l'etat de la commande dans la bdd lorsqu'un client paye passe le statu
	 * de la commande en cour a payé
	 * 
	 * @param request
	 */
	public static void actionPay(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			PreparedStatement editQuantity = con.prepareStatement(
					"UPDATE public.carts SET status = true" + " WHERE id_user = " + user_id + " AND status = false");
			editQuantity.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * modifi le score dans la bdd lorsqu'un client paye incremete le score d'un
	 * client en se basant sur ses achats
	 * 
	 * @param session
	 */
	public static void updateScoreAndSaving(HttpSession session) {
		User user = (User) session.getAttribute("user");

		float tPrice = (float) session.getAttribute("total_price");
		@SuppressWarnings("unchecked")
		List<Article> panierList = (List<Article>) session.getAttribute("panierList");
		int nombreArticle = panierList.size();
		int scoreCommande = (int) Math.round(3 + Math.sqrt(tPrice) + 2 * Math.sqrt(nombreArticle));
		float savings = getSavingOfList(panierList);
		
		user.increaseScore(scoreCommande);
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			int newScore = user.getScore();
			PreparedStatement editScore = con
					.prepareStatement("UPDATE public.details SET score = " + newScore + " WHERE id_user = " + user_id);
			editScore.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculate the saving made trough one command
	 * difference between initial/real price and selling price
	 * 
	 * @param panierList
	 * @return
	 */
	private static float getSavingOfList(List<Article> panierList) {
		float total_saving = (float) 0;
		int quantity_article;
		float saving_oneArticle;

		for (Article anArt : panierList) {
			quantity_article = anArt.getQuantity();
			saving_oneArticle = anArt.getReal_price() - anArt.getSelling_price();
			total_saving += quantity_article * saving_oneArticle;
		}
		return total_saving;
	}

	/**
	 * vide la panier d'un client lorsqua ce dernier annul une commande
	 * 
	 * @param request
	 */
	public static void actionAnnul(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();

			PreparedStatement editQuantity = con.prepareStatement("UPDATE public.carts SET liste_quantities = null"
					+ " WHERE id_user = " + user_id + " AND status = false");
			editQuantity.execute();
			editQuantity = con.prepareStatement("UPDATE public.carts SET list_id_articles = null" + " WHERE id_user = "
					+ user_id + " AND status = false");
			editQuantity.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * renvoi une List<Article> a partir du panier pris en argument
	 * 
	 * @param theCart
	 * @param con
	 * @return
	 */
	private static List<Article> getCart(ResultSet theCart, Connection con) {
		List<Article> myListArticle = new ArrayList<Article>();
		try {
			Object array_id_articles = theCart.getArray("list_id_articles").getArray();
			Object array_quantities = theCart.getArray("liste_quantities").getArray();

			if (array_id_articles instanceof Integer[] && array_quantities instanceof Integer[]) {
				Integer[] list_id_articles = (Integer[]) array_id_articles;
				Integer[] list_id_quantities = (Integer[]) array_quantities;
				if (list_id_articles.length == list_id_quantities.length) {
					// pour chaque articles de la liste
					int id_article;
					int quantity_article;
					Article anArticle;
					String name;
					int id_store;
					float price;
					int stock;
					String store;
					for (int i = 0; i < list_id_articles.length; i++) {
						id_article = list_id_articles[i];
						quantity_article = list_id_quantities[i];

						// SQL to connect to an article
						PreparedStatement pstArticle = con
								.prepareStatement("SELECT * FROM public.articles WHERE id = " + id_article);
						ResultSet rsArticle = pstArticle.executeQuery();
						rsArticle.next();

						// get data on the article
						name = rsArticle.getString("name");
						id_store = rsArticle.getInt("id_store");
						price = rsArticle.getFloat("selling_price");
						stock = rsArticle.getInt("available");

						// SQL to connect to a store
						PreparedStatement pstStore = con
								.prepareStatement("SELECT * FROM public.stores WHERE id = " + id_store);
						ResultSet rsStore = pstStore.executeQuery();
						rsStore.next();

						// get data on the store
						store = rsStore.getString("name");

						// create coresponding article object
						anArticle = new Article();
						anArticle.setId(id_article);
						anArticle.setName(name);
						anArticle.setStock(stock);
						anArticle.setMagasin(store);
						anArticle.setQuantity(quantity_article);
						anArticle.setSelling_price(price);

						myListArticle.add(anArticle);
					}
				} else {
					System.out.println("Pas autant d'articles que de quantités");
				}
			} else {
				System.out.println("Problme de convertion de list");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myListArticle;
	}

	/**
	 * recupère la liste des articles actuelment dans le panier du client calcul le
	 * prix de cette liste et les ajoute au attribu de session
	 * 
	 * @param session
	 */
	public static void loadCart(HttpSession session) {
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {

			int user_id = user.getId();

			// complete the part "mon panier"
			// there should only be one cart by user whith a false status
			PreparedStatement getCart = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet theCart = getCart.executeQuery();
			if (theCart == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				theCart.next();

				List<Article> myListArt = ArticleListFunctions.getCart(theCart, con);
				float total_price = ArticleListFunctions.getPriceOfList(myListArt);

				// set session attribute
				session.setAttribute("panierList", myListArt);
				session.setAttribute("total_price", total_price);

			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fait le somme de prix de tout les articles present dans la liste
	 * 
	 * @param myListArt
	 * @return total price
	 */
	private static float getPriceOfList(List<Article> myListArt) {
		float total_price = (float) 0;
		int quantity_article;
		float price;

		for (Article anArt : myListArt) {
			quantity_article = anArt.getQuantity();
			price = anArt.getSelling_price();
			total_price += quantity_article * price;
		}
		return total_price;
	}

}
