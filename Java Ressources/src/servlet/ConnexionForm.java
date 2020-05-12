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
 * Servlet implementation class ConnexionForm
 */
@WebServlet("/ConnexionForm")
public class ConnexionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String erroMsg="";
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("password");
		int idUser=0;
		
		//data for the bdd
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		String page = "./view/index.jsp";
        HttpSession session=request.getSession();
        
        try (Connection con = DriverManager.getConnection(url, user, psw))
        {
        	PreparedStatement pswCheck = con.prepareStatement("SELECT id,password FROM public.users WHERE user_name LIKE '"+userName+"'");
        	ResultSet rsPsw = pswCheck.executeQuery();
        	Boolean rightPasswrd = false;
        	
        	while (rsPsw.next()) { 
        		String hashed = rsPsw.getString("password");
        		rightPasswrd = BCrypt.checkPassword(pwd,  hashed);
        		if (rightPasswrd) {
        			idUser = rsPsw.getInt("id");

                	PreparedStatement usr = con.prepareStatement("SELECT * FROM public.users WHERE id = '"+idUser+"'");
                    ResultSet getUser = usr.executeQuery();
            			
            		
            			List<User> result = new ArrayList<>();
            			User connectedUser;
            	           while (getUser.next()) {     
	            	        //get value from bdd
	            	        int id = getUser.getInt("id");
	            	        String user_name = getUser.getString("user_name");
	            	        String mail = getUser.getString("mail");
	            	        int type  = getUser.getInt("type");
	            	        String password = getUser.getString("password");
	            	        boolean status = getUser.getBoolean("status");
	            	          	
	            	          //get details
	            			PreparedStatement getDetails = con.prepareStatement("SELECT * FROM public.details WHERE id_user = "+id+" LIMIT 1");
	            			ResultSet res = getDetails.executeQuery();
	            			res.next();
	            	       	String firstname = res.getString("first_name");
	            	       	String lastname = res.getString("last_name");
	            	       	int promotion = res.getInt("promotion");
	            	           	            	       	
	            	       	ConnectionFunctions.connect(request, id, mail, type, user_name, firstname, lastname, promotion);
	                     	
            				//load page
                            page = "./view/profil.jsp";			
                	}
        					
	        	}
        	
        	}
        	
        erroMsg = "Identifiant ou mot de passe incorrect";
           
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("connectionMsg",erroMsg );
		response.sendRedirect(page);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
