package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;

import obj.Article;
import obj.User;

/**
 * Servlet implementation class PanierClick
 */
@WebServlet("/ShoppingClick")
public class ShoppingClick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingClick() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("blob");
		actionTab(request);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void actionTab(HttpServletRequest request) {
		Integer id_article = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("act");

		HttpSession session = request.getSession();
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
				theCart.next();
				Object array_id_articles;
				Object array_quantities;
				boolean is_new_cart = false;
				try {
					array_id_articles = theCart.getArray("list_id_articles").getArray();
					array_quantities = theCart.getArray("liste_quantities").getArray();
				} catch (PSQLException e) {
					array_id_articles = new Integer[0];
					array_quantities = new Integer[0];
					is_new_cart = true;
				}

				if (array_id_articles instanceof Integer[] && array_quantities instanceof Integer[]) {
					Integer[] tmp_list_id_articles = (Integer[]) array_id_articles;
					Integer[] tmp_list_id_quantities = (Integer[]) array_quantities;

					List<Integer> list_id_articles = new ArrayList<Integer>(Arrays.asList(tmp_list_id_articles));
					List<Integer> list_quantities = new ArrayList<Integer>(Arrays.asList(tmp_list_id_quantities));

					System.out.println("id article : " + id_article);
					System.out.println("list_id_articles : " + listToString(list_id_articles));
					System.out.println("list_quantities : " + listToString(list_quantities));
					int idToChangeLocation = list_id_articles.indexOf(id_article);
					if (idToChangeLocation == -1) {
						System.out.println("add to list");
						list_id_articles.add(id_article);
						list_quantities.add(0);
						idToChangeLocation = list_id_articles.indexOf(id_article);
					}
					int newVal;
					switch (action) {
					case "less":
						newVal = (list_quantities.get(idToChangeLocation) - 1);
						list_quantities.set(idToChangeLocation, newVal);
						break;
					case "more":
						newVal = (list_quantities.get(idToChangeLocation) + 1);
						list_quantities.set(idToChangeLocation, newVal);
						break;
					default:
						System.out.println("unkonwn action in actionTab in panier click");
						break;
					}
					System.out.println(action + " " + user_id + " " + id_article);
					System.out.println("list_id_articles : " + listToString(list_id_articles));
					System.out.println("list_quantities : " + listToString(list_quantities));

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
							anArticle.setMagasin(store);
							anArticle.setQuantity(quantity_article);
							anArticle.setSelling_price(price);

							// store the article
							lArt.add(anArticle);
						}
						// set session attribute
						System.out.println(lArt.toString());
						session.setAttribute("articleList", lArt);
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
	}

	private String listToString(List<Integer> l) {
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

}
