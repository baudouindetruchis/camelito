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
 * Servlet implementation class magasinListForm
 */
@WebServlet("/MagasinListLoadForm")
public class MagasinListLoadForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinListLoadForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		List< Commande> listStoresCommands = new ArrayList< Commande>();
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
			PreparedStatement getCommmands = con
					.prepareStatement("SELECT id_command, id_store, list_id_articles, liste_quantities, status FROM public.commands ");
			
			ResultSet commands = getCommmands.executeQuery();
			
			while(commands.next()) { 
				Commande commande =CommandsFunctions.prepareCommmande( commands,  con);
				
				listStoresCommands.add(commande);
			}
			session.setAttribute("listStoresCommands", listStoresCommands);
			
			
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load page
		String page = "./view/magasinList.jsp";
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
