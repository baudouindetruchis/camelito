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

import obj.Commande;

/**
 * Servlet implementation class MagasinGetCommandFrom
 */
@WebServlet("/MagasinGetCommandFrom")
public class MagasinGetCommandFrom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinGetCommandFrom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String store_name = request.getParameter("store_name");
		int id= Integer.parseInt(request.getParameter("id"));
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement getCommmandStore = con
					.prepareStatement("SELECT id FROM public.stores WHERE name = '"+store_name+"'");
			
			ResultSet store = getCommmandStore.executeQuery();
			store.next();
			int id_store = store.getInt("id");
			
			PreparedStatement getCommmandToModify = con.prepareStatement("UPDATE public.commands SET status = false WHERE id_command = '"+id+"' AND id_store ='"+id_store+"'");
					
			getCommmandToModify.execute();
		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute(store_name, "recuperee");
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
