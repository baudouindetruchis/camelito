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
 * Servlet implementation class profilForm
 */
@WebServlet("/profilForm")
public class profilForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profilForm() {
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
		String page = "./view/index.jsp";

        HttpSession session=request.getSession(false);
        String name = (String) session.getAttribute("userName");
        System.out.println(name);
        
        
		/*
        try (Connection con = DriverManager.getConnection(url, user, psw))
                 {
			
           
            System.out.println(id);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM public.\"Detail\" WHERE id_User LIKE '"+id+"'");
            ResultSet rs = pst.executeQuery();
            
            
			if(rs==null) {
				
				
			}else {
				
	            while (rs.next()) {            	
	            	
	            }
	             
			  	
			}
			
            
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

		//if request is not from HttpServletRequest, you should do a typecast before
		  //save message in session
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
