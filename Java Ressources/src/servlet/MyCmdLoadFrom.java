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

import JavaFunction.CommandsFunctions;
import obj.Article;
import obj.Commande;

/**
 * Servlet implementation class MyCmdLoadFrom
 */
@WebServlet("/MyCmdLoadFrom")
public class MyCmdLoadFrom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCmdLoadFrom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id_user = (int) session.getAttribute("id");
		List<Article> listArticles = new ArrayList< Article>();
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement getStoreId = con
					.prepareStatement("SELECT id FROM public.stores WHERE id_user='"+ id_user+"'");
			
			ResultSet store = getStoreId.executeQuery();
			store.next();
			int id_store= store.getInt("id");
			int price = 0;
			
			PreparedStatement getCommmand = con
					.prepareStatement("SELECT list_id_articles, liste_quantities FROM public.commands WHERE status = true AND id_store='"+ id_store+"'");
			ResultSet command = getCommmand.executeQuery();
			
			while(command.next()) { 
				Object array_articleByUser =   command.getArray("list_id_articles").getArray();
				Integer[] list_articleByUser = (Integer[]) array_articleByUser;
				Object array_articleQuantity =   command.getArray("liste_quantities").getArray();
				Integer[] list_articleQuantity = (Integer[]) array_articleQuantity;		
				for(int i=0; i<list_articleByUser.length;i++) {
					int id_article = list_articleByUser[i];
					int quantity = list_articleQuantity[i];
					Article newArticle = CommandsFunctions.getArticle(con, command, id_article, quantity);
					price= (int) (price + newArticle.getSelling_price()*quantity);
					listArticles.add(newArticle);
				}
				
			}		
			session.setAttribute("TotalPrice", price);
			session.setAttribute("CommandePourStore", listArticles);
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load page
		String page = "./view/commandPageStore.jsp";
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
