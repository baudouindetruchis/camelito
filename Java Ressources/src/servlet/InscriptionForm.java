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

/**
 * Servlet implementation class InscriptionForm
 */
@WebServlet("/InscriptionForm")
public class InscriptionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String year = request.getParameter("year");
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String secondPassword = request.getParameter("secondPassword");
		
		String pseudo = request.getParameter("pseudo");
		int id = 0;
		String categorie = request.getParameter("categorie");
		int type=0;
		switch(categorie) {
			case "Client" : type = 1;
			case "Asso" : type = 2;
			case "Commercant" : type = 3;
		
		}
		//Boolean status 
		
		
		
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		String page = "./view/index.jsp";

        HttpSession session=request.getSession();
        
        try (Connection con = DriverManager.getConnection(url, user, psw))
        {
        	PreparedStatement getLastID = con.prepareStatement("SELECT id FROM public.\"User\" ");
        	
        	ResultSet rsID = getLastID.executeQuery();
							
	        while (rsID.next()) {            	
	            if(rsID.isLast()) {
	            	 id= rsID.getInt("id");
	            	 id++;
	            }

	        }
	        System.out.println(password);
	        System.out.println(secondPassword);
	        System.out.println(password==secondPassword);
	        if(password.contentEquals(secondPassword)) {
	        	PreparedStatement addUser = con.prepareStatement("INSERT INTO public.\"User\" VALUES('"+
		            	id+"','"+pseudo+"','"+email+"','"+type+"','"+password+"'," + true+")");
		       addUser.execute();
		        
		       User obj = new User();
               obj.setId(id);
               obj.setMail(email);
               obj.setPassword(password);
               //obj.setStatus(status);
               obj.setType(type);
               obj.setUser_name(pseudo);
		       
               session.setAttribute("user",obj);  
	            session.setAttribute("user_id",obj.getId());  
	            session.setAttribute("mail",obj.getMail());
	            session.setAttribute("userName",obj.getUser_name());
	            
	            session.setAttribute("msg", "Hello world"); 
		       page = "./view/profil.jsp";
	        }else {
	        	System.out.println("erreur sur les mdp");
	        }
	        
	        
			
            
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
