package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFunction.StockFunctions;
import obj.Article;
import obj.User;

/**
 * Servlet implementation class StockLoad
 */
@WebServlet("/StockClick")
public class StockClick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockClick() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int newVal = (int) Math.round(Float.parseFloat(request.getParameter("newStock")));
		int id_article = Integer.parseInt(request.getParameter("id"));
		
		// connection a la bdd
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int user_id = user.getId(); 
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			// there should only be one cart by user whith a false status
			PreparedStatement getArticle = con
					.prepareStatement("SELECT * FROM public.articles WHERE id = " + id_article);
			ResultSet theArticle = getArticle.executeQuery();
			if (theArticle == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				// recuperation de la liste de course en cours
				theArticle.next();

				if (newVal > 99) {
					newVal = 99;
				} else if (newVal < 0) {
					newVal = 0;
				}

				PreparedStatement editStock = con.prepareStatement(
						"UPDATE public.articles SET available = " + newVal + " WHERE id = " + id_article);
				editStock.execute();
				System.out.println( "available = " + newVal + " WHERE id = " + id_article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Article> stockList = StockFunctions.getStockList(user_id);
		session.setAttribute("stockList", stockList);
		
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

}
