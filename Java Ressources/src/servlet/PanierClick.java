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

import org.postgresql.jdbc.PgArray;

import obj.Article;
import obj.User;

/**
 * Servlet implementation class PanierClick
 */
@WebServlet("/PanierClick")
public class PanierClick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierClick() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("act");
		String page = "./view/panier.jsp";

		switch (action) {
		case "comm":
//			actionMore(request);
			break;
		case "ann":
//			actionMore(request);
			break;
		case "pay":
//			actionMore(request);
			break;

		default:
			break;
		}
		switch (action) {
		case "comm":
		case "ann":
		case "pay":
			response.sendRedirect(page);
			break;
		case "less":
		case "more":
		case "supp":
			actionTab(request);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			break;

		default:
			System.out.println("unkonwn action in doGet panier click");
			break;
		}

		System.out.println(action);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		doGet(request, response);
	}

	private void actionTab(HttpServletRequest request) {
		int idToChange = (int) Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("act");

		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String userBdd = "postgres";
		String psw = "123";
//		String page = "./view/index.jsp";
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(url, userBdd, psw)) {
			int user_id = user.getId();
			// there should only be one cart by user whith a false status
			PreparedStatement getCart = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet theCart = getCart.executeQuery();
			if (theCart == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				theCart.next();
				Object array_id_articles = theCart.getArray("list_id_articles").getArray();
				Object array_quantities = theCart.getArray("liste_quantities").getArray();

				if (array_id_articles instanceof Integer[] && array_quantities instanceof Integer[]) {
					Integer[] list_id_articles = (Integer[]) array_id_articles;
					Integer[] list_id_quantities = (Integer[]) array_quantities;

					int idToChangeLocation = Arrays.asList(list_id_articles).indexOf(idToChange);
					switch (action) {
					case "less":
						list_id_quantities[idToChangeLocation] = list_id_quantities[idToChangeLocation] - 1;
						break;
					case "more":
						list_id_quantities[idToChangeLocation] = list_id_quantities[idToChangeLocation] + 1;
						break;
					case "supp":
						list_id_quantities[idToChangeLocation] = 0;
						break;

					default:
						System.out.println("unkonwn action in actionTab in panier click");
						break;
					}
					
					String strList = listToString(list_id_quantities);
					PreparedStatement editQuantity = con.prepareStatement(
							"UPDATE public.carts SET liste_quantities = '" + strList
									+ "' WHERE id_user = " + user_id + " AND status = false");
					editQuantity.execute();

					if (list_id_articles.length == list_id_quantities.length) {
						// pour chaque articles de la liste
						List<Article> cart = new ArrayList<>();
						int id_article;
						int quantity_article;
						Article anArticle;
						float total_price = 0;
						String name;
						int id_store;
						int price;
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

							total_price += quantity_article * price;

							// store the article
							cart.add(anArticle);
						}
						// set session attribute
						session.setAttribute("panierList", cart);
						session.setAttribute("total_price", total_price);
					} else {
						System.out.println("Pas autant d'articles que de quantités");
					}
				} else {
					System.out.println("Problme de convertion de list");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String listToString(Integer[] l) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(Integer itm : l) {
			sb.append(itm);
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append("}");
		return sb.toString();
	}

}
