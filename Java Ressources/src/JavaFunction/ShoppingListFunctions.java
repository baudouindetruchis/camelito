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
import obj.User;

public class ShoppingListFunctions {

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	public static String modifQuantity(HttpServletRequest request) {
		Integer id_article = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("act");
		
		// msg permet de retrouner des info via la reponse html
		String msg = ""; 

		//connection a la bdd
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
				//recuperation de la liste de course en cours
				theCart.next();
				Object array_id_articles;
				Object array_quantities;
				boolean is_new_cart = false;
				try {
					array_id_articles = theCart.getArray("list_id_articles").getArray();
					array_quantities = theCart.getArray("liste_quantities").getArray();
				} catch (PSQLException e) {
					//initialisation si liste null
					array_id_articles = new Integer[0];
					array_quantities = new Integer[0];
					is_new_cart = true;
				}

				if (array_id_articles instanceof Integer[] && array_quantities instanceof Integer[]) {
					//passage au format liste java plutot que sql
					Integer[] tmp_list_id_articles = (Integer[]) array_id_articles;
					Integer[] tmp_list_id_quantities = (Integer[]) array_quantities;
					List<Integer> list_id_articles = new ArrayList<Integer>(Arrays.asList(tmp_list_id_articles));
					List<Integer> list_quantities = new ArrayList<Integer>(Arrays.asList(tmp_list_id_quantities));

					//recupère l'endroit dans la liste de l'artique selectionné
					int idToChangeLocation = list_id_articles.indexOf(id_article);
					if (idToChangeLocation == -1) {
						//ou le rajoute s'il n'est pas present dans la bdd
						list_id_articles.add(id_article);
						list_quantities.add(0);
						idToChangeLocation = list_id_articles.indexOf(id_article);
					}
					//calcul la nouvelle valeur
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
					//et update la liste
					list_quantities.set(idToChangeLocation, newVal);
					
					if(newVal==0) {
						list_quantities.remove(idToChangeLocation);
						list_id_articles.remove(idToChangeLocation);
						msg="article suprimé de votre liste";
					} else if(newVal==-1) {
						list_quantities.remove(idToChangeLocation);
						list_id_articles.remove(idToChangeLocation);
						msg="impossible d'avoir des quatité negative";
					} else {
						msg="le stock disponible est malhersment limite";
						// SQL to connect to the article
						PreparedStatement pstArticle = con
								.prepareStatement("SELECT * FROM public.articles WHERE id = " + id_article);
						ResultSet rsArticle = pstArticle.executeQuery();
						rsArticle.next();
						// get stock of the article
						int theStock = rsArticle.getInt("available");
						//si quantité supp au stock alors impossible on retourne en arrière
						if(theStock<newVal) {
							list_quantities.set(idToChangeLocation, pastVal);							
						}
					}

					//maj de la bdd avec les nouvelle valeur
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

					if (list_id_articles.size() == list_quantities.size()) {
						// pour chaque articles de la liste
						List<Article> lArt = new ArrayList<>();
						int id_art;
						int quantity_article;
						Article anArticle;
						String name;
						int id_store;
						int price;
						int stock;
						String store;
						for (int i = 0; i < list_id_articles.size(); i++) {
							id_art = list_id_articles.get(i);
							quantity_article = list_quantities.get(i);

							// SQL to connect to an article
							PreparedStatement pstArticle = con
									.prepareStatement("SELECT * FROM public.articles WHERE id = " + id_art);
							ResultSet rsArticle = pstArticle.executeQuery();
							rsArticle.next();

							// get data on the article
							name = rsArticle.getString("name");
							id_store = rsArticle.getInt("id_store");
							price = rsArticle.getInt("selling_price");
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
							anArticle.setId(id_art);
							anArticle.setName(name);
							anArticle.setMagasin(store);
							anArticle.setStock(stock);
							anArticle.setQuantity(quantity_article);
							anArticle.setSelling_price(price);

							// store the article
							lArt.add(anArticle);
						}
						// set session attribute
						session.setAttribute("panierList", lArt);
					} else {
						System.out.println("Pas autant d'articles que de quantités");
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
	

	public static void actionPay(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();

			PreparedStatement editQuantity = con.prepareStatement("UPDATE public.carts SET status = true"
					+ " WHERE id_user = " + user_id + " AND status = false");
			editQuantity.execute();
			updateCommList(user_id, session, con); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void actionAnnul(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();

			PreparedStatement editQuantity = con.prepareStatement("UPDATE public.carts SET liste_quantities = null"
					+ " WHERE id_user = " + user_id + " AND status = false");
			editQuantity.execute();
			editQuantity = con.prepareStatement("UPDATE public.carts SET list_id_articles = null"
					+ " WHERE id_user = " + user_id + " AND status = false");
			editQuantity.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateCommList(int user_id, HttpSession session, Connection con) {
		//complete the part "mes commandes"
		try {
			PreparedStatement getCommandes = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = true");
			ResultSet commandes = getCommandes.executeQuery();
			if (commandes == null) {
				System.out.println("Erreur de connexion (commandes=null)");
			} else {
				List<Integer> listCommandes = new ArrayList<>();
				int id;
				while(commandes.next()) {
					id = commandes.getInt("id");
					listCommandes.add(id);
				}
				session.setAttribute("commandeList", listCommandes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
