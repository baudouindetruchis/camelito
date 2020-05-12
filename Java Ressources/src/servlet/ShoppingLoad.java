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
import obj.User;

/**
 * Servlet implementation class ShoppingLoad
 */
@WebServlet("/ShoppingLoad")
public class ShoppingLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingLoad() {
		super();
		// TODO Auto-generated constructor stub
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

			PreparedStatement getStock = con
					.prepareStatement("SELECT * FROM public.articles WHERE available != 0 ");
			ResultSet allStock = getStock.executeQuery();
			if (allStock == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				
				List<Article> lArt = new ArrayList<Article>();
				Article anArticle;
				while(allStock.next()) {
					//get value from bdd
	            	int id = allStock.getInt("id");
	            	String description = allStock.getString("description");
	            	int id_store = allStock.getInt("id_store");
	            	String name = allStock.getString("name");
	            	int stock =allStock.getInt("available");;
	            	float real_price  =allStock.getInt("initial_price");
	            	float selling_price =allStock.getInt("selling_price");
	            	
					// SQL to connect to a store
					PreparedStatement pstStore = con.prepareStatement(
							"SELECT * FROM public.stores WHERE id = " + id_store);
					ResultSet rsStore = pstStore.executeQuery();
					rsStore.next();

					// get data on the store
					String store = rsStore.getString("name");
	            	
	            	//create corresponding article object
	            	anArticle= new Article();
	            	anArticle.setId(id);
	            	anArticle.setDescription(description);
	            	anArticle.setMagasin(store);
	            	anArticle.setName(name);
	            	anArticle.setStock(stock);
	            	anArticle.setReal_price(real_price);
	            	anArticle.setSelling_price(selling_price);
					
					lArt.add(anArticle);
				}
				session.setAttribute("articleList", lArt);		
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// load page
		String page = "./view/shopping.jsp";// TODO
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
