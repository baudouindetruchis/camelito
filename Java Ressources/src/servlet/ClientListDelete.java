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
import obj.Commande;
import obj.User;

/**
 * Servlet implementation class ClientListDelete
 */
@WebServlet("/ClientListDelete")
public class ClientListDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientListDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		int idComm = Integer.parseInt(request.getParameter("id"));
		String user_name = request.getParameter("user_name");
		
		
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			

			PreparedStatement deleteCart = con
					.prepareStatement("DELETE from public.carts WHERE id = '"+idComm+"'");
			
			deleteCart.execute();
			
			
			@SuppressWarnings("unchecked")
			List<Commande> listArticlesByUser = (List<Commande>) session.getAttribute("listArticlesByUser");
			for(Commande  command :listArticlesByUser ) {
				if(command.getId()==(idComm)) {
					listArticlesByUser.remove(command);
					break;
				}
			}
			session.setAttribute("listArticlesByUser", listArticlesByUser);
			
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
