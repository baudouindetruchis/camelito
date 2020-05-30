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


import obj.User;
import obj.UserSuccess;

/**
 * Servlet implementation class SuccessForm
 */
@WebServlet("/SuccessForm")
public class SuccessForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("id");
		int valueSuccess = Integer.parseInt(request.getParameter("valueSuccess"));
		String typeSuccess = request.getParameter("typeSuccess");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
			PreparedStatement getSucces = con
					.prepareStatement("SELECT * FROM public.success WHERE success_type= '"+ typeSuccess+"' AND value="+valueSuccess);
			
			ResultSet success = getSucces.executeQuery();
			
			success.next();
			int idSuccess= success.getInt("id");	
			String successName = success.getString("success_name");
			
			UserSuccess lastSuccess= new UserSuccess(successName, typeSuccess, valueSuccess, success.getString("succes_pic"), true);
			PreparedStatement updateFavSuccess = con
					.prepareStatement("UPDATE public.details SET favsuccess='"+idSuccess +"' WHERE id_user= '"+ id+"'");
			
			updateFavSuccess.execute();
			User user = (User) session.getAttribute("user");
			user.setFavSuccess(lastSuccess);
			
			
			
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
