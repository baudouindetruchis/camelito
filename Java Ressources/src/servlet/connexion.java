package servlet;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class connexion
 */
@WebServlet(name = "connexion", urlPatterns = { "/connexion" })
public class connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 String dbName = "jdbc:postgresql://localhost:5432/camelitoLocal";
         String dbDriver = "org.postgresql.Driver";
         String userName = "postgres";
         String password = "123"; 

         try{
         try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Connection con = DriverManager.getConnection(dbName, userName, password);
         System.out.println("Got Connection");
         java.sql.Statement statement = con.createStatement();
         String sql = "select * from login";
         ResultSet rs = statement.executeQuery(sql);
         while (rs.next()) {
             System.out.println(rs.getString("uname"));
         }
         }catch(SQLException e){
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
