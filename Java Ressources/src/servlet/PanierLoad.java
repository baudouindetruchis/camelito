package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.ClientCommand;
import obj.User;

/**
 * Servlet implementation class LoadPanier
 */
@WebServlet("/PanierLoad")
public class PanierLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierLoad() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			//complete the part "mes commandes"
			PreparedStatement getCommandes = con
					.prepareStatement("SELECT * FROM public.carts WHERE id_user = " + user_id + " AND status = true");
			ResultSet commandes = getCommandes.executeQuery();
			if (commandes == null) {
				System.out.println("Erreur de connexion (commandes=null)");
			} else {
				List<Integer> listCommandes = new ArrayList<>();
				int id;
				float totalPrice;
				ClientCommand aClientCommand;
				while(commandes.next()) {
					id = commandes.getInt("id");
					id = commandes.getInt("id");
					
					
					aClientCommand = new ClientCommand();
					aClientCommand.setId(id);
					aClientCommand.se
					
					listCommandes.add(id);
				}
				session.setAttribute("commandeList", listCommandes);
			}
			
			// complete the part "mon panier" 
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
					if (list_id_articles.length == list_id_quantities.length) {
						// pour chaque articles de la liste
						List<Article> cart = new ArrayList<>();
						int id_article;
						int quantity_article;
						Article anArticle;
						float total_price=0;
						String name ;
						int id_store;
						int price;
						String store;
						for (int i = 0; i < list_id_articles.length; i++) {
							id_article = list_id_articles[i];
							quantity_article = list_id_quantities[i];

							// SQL to connect to an article
							PreparedStatement pstArticle = con.prepareStatement(
									"SELECT * FROM public.articles WHERE id = " + id_article);
							ResultSet rsArticle = pstArticle.executeQuery();
							rsArticle.next();

							// get data on the article
							name = rsArticle.getString("name");
							id_store = rsArticle.getInt("id_store");
							price = rsArticle.getInt("selling_price");

							// SQL to connect to a store
							PreparedStatement pstStore = con.prepareStatement(
									"SELECT * FROM public.stores WHERE id = " + id_store);
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

							total_price+=quantity_article*price;

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
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load page
		String page = "./view/panier.jsp";
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
