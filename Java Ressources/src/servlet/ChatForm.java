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

/**
 * Servlet implementation class ChatForm
 */
@WebServlet("/ChatForm")
public class ChatForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		HttpSession session = request.getSession();
		
		String text = (String) request.getAttribute("txt");
		System.out.println(text);
		int id_user = (int) session.getAttribute("id");
		
		try (Connection con = DriverManager.getConnection(url, user, psw)) {
			
			
			PreparedStatement getIdStore = con.prepareStatement("SELECT id FROM public.stores WHERE id_user='"+ id_user+"'");
			ResultSet rsStore = getIdStore.executeQuery();
			
			rsStore.next();
			int id_store = rsStore.getInt("id");
			
			PreparedStatement sendtext = con.prepareStatement("INSERT INTO public.messages (id_store, txt, is_send_by_asso) "
					+ "VALUES('"+ id_store+"','"+text+ "'," +false+")");
			sendtext.execute();
		
		
		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("./view/chooseStoreTotalk.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
