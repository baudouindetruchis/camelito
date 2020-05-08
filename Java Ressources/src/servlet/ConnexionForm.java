package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("password");
		int idUser=0;
		
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
        	}
        	
        	
        	if(rightPasswrd) {
        		PreparedStatement pst = con.prepareStatement("SELECT * FROM public.users WHERE id = '"+idUser+"'");
            	ResultSet rs = pst.executeQuery();
    			
    			if(rs==null) {
    				/*
    				 * Error of connection
    				 */
    				
    			}else {
    				List<User> result = new ArrayList<>();
    				User connectedUser;
    	            while (rs.next()) {     
    	            	//get value from bdd
    	            	int id = rs.getInt("id");
    	            	String user_name = rs.getString("user_name");
    	            	String mail = rs.getString("mail");
    	            	int type  = rs.getInt("type");
    	            	String password = rs.getString("password");
    	            	boolean status = rs.getBoolean("status");
    	            	
    	            	//get details
    					PreparedStatement getDetails = con.prepareStatement("SELECT * FROM public.details WHERE id_user = "+id+" LIMIT 1");
    					ResultSet res = getDetails.executeQuery();
    					res.next();
    	            	String firstname = res.getString("first_name");
    	            	String lastname = res.getString("last_name");
    	            	
    	            	//create corresponding user object
    	            	User obj = new User();
    	                obj.setId(id);
    	                obj.setMail(mail);
    	                obj.setPassword(password);
    	                obj.setStatus(status);
    	                obj.setType(type);
    	                obj.setPseudo(user_name);
    					obj.setFirst_name(firstname);
    					obj.setLast_name(lastname);

    	                result.add(obj);

    	            }
    	            connectedUser=result.get(0);
    	            //set session attribute
    	            session.setAttribute("user",connectedUser);  
    	            session.setAttribute("user_id",connectedUser.getId());  
    	           // session.setAttribute("mail",connectedUser.getMail());  
    	            session.setAttribute("type",connectedUser.getType());
    	            session.setAttribute("userName",connectedUser.getPseudo());
    	            //user details
    				session.setAttribute("fName", connectedUser.getFirst_name());
    				session.setAttribute("lName", connectedUser.getLast_name());

    				//load page
                    page = "./view/profil.jsp";
    			}
    			
        	}}
        	
            
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

		//if request is not from HttpServletRequest, you should do a typecast before
		  //save message in session
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
