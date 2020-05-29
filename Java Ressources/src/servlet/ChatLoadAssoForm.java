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

import JavaFunction.ConnectionFunctions;
import obj.User;

/**
 * Servlet implementation class ChatLoadAssoForm
 */
@WebServlet("/ChatLoadAssoForm")
public class ChatLoadAssoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatLoadAssoForm() {
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
		List<String> listNameStores = new ArrayList<String>();
		HttpSession session = request.getSession();
		try (Connection con = DriverManager.getConnection(url, user, psw)) {
			
			
			PreparedStatement getAllStores = con.prepareStatement("SELECT name FROM public.stores" );
			ResultSet rsStores = getAllStores.executeQuery();
			
			while(rsStores.next()) {
				String store = rsStores.getString("name");
				listNameStores.add(store);
			}
		
			session.setAttribute("listStores", listNameStores);
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
