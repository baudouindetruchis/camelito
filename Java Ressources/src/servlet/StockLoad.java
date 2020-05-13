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
 * Servlet implementation class StockLoad
 */
@WebServlet("/StockLoad")
public class StockLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			System.out.println(user_id);
			
			PreparedStatement getStore = con
					.prepareStatement("SELECT * FROM public.stores WHERE id_user = " + user_id);
			ResultSet theStore = getStore.executeQuery();
			if (theStore == null) {
				System.out.println("Erreur de connexion (commandes=null)");
			} else {
				theStore.next();
				int id_store = theStore.getInt("id");
				String name_store = theStore.getString("name");
				PreparedStatement getArticles = con
						.prepareStatement("SELECT * FROM public.articles WHERE id_store = " + id_store);
				ResultSet rsArticle = getArticles.executeQuery();
				
				List<Article> stockList = new ArrayList<Article>();
				Article anArticle;
				
				String description;
				int id;
				String name;
				float real_price;
				float selling_price;
				int stock;
				
				while(rsArticle.next()){
					//get data
					description = rsArticle.getString("description");
					id = rsArticle.getInt("id");
					name = rsArticle.getString("name");
					real_price = rsArticle.getFloat("initial_price");
					selling_price= rsArticle.getFloat("selling_price");
					stock = rsArticle.getInt("available");
					
					//init object
					anArticle = new Article();
					anArticle.setDescription(description);
					anArticle.setId(id);
					anArticle.setMagasin(name_store);
					anArticle.setName(name);
					anArticle.setReal_price(real_price);
					anArticle.setSelling_price(selling_price);
					anArticle.setStock(stock);
					
					stockList.add(anArticle);					
				}
				// set session attribute
				session.setAttribute("stockList", stockList);
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load page
		String page = "./view/stock.jsp";
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
