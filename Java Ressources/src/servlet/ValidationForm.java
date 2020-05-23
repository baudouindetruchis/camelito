package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class ValidationForm
 */
@WebServlet("/ValidationForm")
public class ValidationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id_user = Integer.parseInt(request.getParameter("id"));
		String changeType = request.getParameter("changeType");
		String toValidate = request.getParameter("toValidate");
		Boolean valider ;
		ArrayList<User> listUsers ;
		ArrayList<User> listValideUsers ;
		if(toValidate.contentEquals("true")) {
			valider = true;
		}else {
			valider=false;
		}
		
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
			if(changeType.equals("true")) {
				PreparedStatement modifyType = con
						.prepareStatement("Update public.users set type= 1 WHERE id = '"+id_user+"'");
				
				modifyType.execute();
			}
			

			PreparedStatement modifyProfil = con
					.prepareStatement("Update public.users set status='"+valider+"' WHERE id = '"+id_user+"'");
			
			modifyProfil.execute();
			
			listUsers = (ArrayList<User>) session.getAttribute("listUsers");
			listValideUsers= (ArrayList<User>) session.getAttribute("listValideUsers");
			if(valider) {
				for(User user : listUsers) {
					if(user.getId()==id_user) {
						listUsers.remove(user);
						listValideUsers.add(user);
						break;
					}
					session.setAttribute("surLesValides", "false");
				}
				
				
			}else {
				for(User user : listValideUsers) {
					if(user.getId()==id_user) {
						listValideUsers.remove(user);
						listUsers.add(user);
						break;
					}
				}
				session.setAttribute("surLesValides", "true");
			}
			
				session.removeAttribute("listUsers");
				session.setAttribute("listUsers",listUsers);	
			
				session.removeAttribute("listValideUsers");
				session.setAttribute("listValideUsers",listValideUsers);
			
			
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
