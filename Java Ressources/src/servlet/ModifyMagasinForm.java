package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFunction.ModifyProfilFunctions;
import obj.User;

/**
 * Servlet implementation class monMagasinForm
 */
@WebServlet("/ModifyMagasinForm")
public class ModifyMagasinForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMagasinForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = request.getParameter("newAddress");	
		String storeName = request.getParameter("newName");		
		
		//connect to the bdd
				String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
				String user = "postgres";
				String psw = "123";
				String page = "./view/profil.jsp";

				HttpSession session = request.getSession();
				try (Connection con = DriverManager.getConnection(url, user, psw)) {
					int id = (int) session.getAttribute("id");
					
					PreparedStatement editAddress = con.prepareStatement("UPDATE public.stores SET address = '"+ address.replace("'", "''''") +"' WHERE id_user = '"+id+"'" );
					PreparedStatement editStoreName = con.prepareStatement("UPDATE public.stores SET name = '"+ storeName.replace("'", "''''") +"' WHERE id_user = '"+id+"'" );	
					
					if(!address.isEmpty()&& !(address== null)) {
						editAddress.execute();
						User obj = (User) session.getAttribute("user");
					 	obj.setAddress(address);	
					}
					if(!storeName.isEmpty()&& !(storeName== null)) {
						editStoreName.execute();
						User obj = (User) session.getAttribute("user");
					 	obj.setStoreName(storeName);	
					}		
				} catch (SQLException e) {
					System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
