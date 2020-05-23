package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.User;


/**
 * Servlet implementation class ValidationLoadForm
 */
@WebServlet("/ValidationLoadForm")
public class ValidationLoadForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationLoadForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession(false);
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			Boolean toValide=false;
			PreparedStatement getUsers;
			for(int i=0; i<2;i++) {
				ArrayList<User> listUsers = new ArrayList<User>();
			getUsers = con.prepareStatement("SELECT * FROM public.users WHERE status ='" +toValide+"'");

			ResultSet users = getUsers.executeQuery();
			while(users.next()) {  
				User user = new User();
				
				user.setId(users.getInt("id"));
				user.setPseudo(users.getString("user_name"));
				user.setMail(users.getString("mail"));
				user.setType(users.getInt("type"));
				user.setStatus(toValide);
				listUsers.add(user);
			}
			if(!toValide) {
				session.setAttribute("listUsers", listUsers);
			}else {
				session.setAttribute("listValideUsers", listUsers);
			}
			toValide=true;
		}
				 
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("./view/validationProfil.jsp");
			
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
