package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import obj.User;

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
		
		
		List<User> result = new ArrayList<>();

        String SQL_SELECT = "Select * from USER";

        // auto close connection and preparedStatement
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/camelitoLocal", "postgres", "123");
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
        	System.out.println(resultSet);
        	
        	ResultSetMetaData rsmd = resultSet.getMetaData();
        	int columnsNumber = rsmd.getColumnCount();
        	while (resultSet.next()) {
            	System.out.println("a");
        	    for (int i = 1; i <= columnsNumber; i++) {
        	        if (i > 1) System.out.print(",  ");
        	        String columnValue = resultSet.getString(i);
        	        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                	System.out.println("b");
        	    }
        	    System.out.println("");
        	}
        	
//            while (resultSet.next()) {
//            	int id = resultSet.getInt("id");
//            	String user_name = resultSet.getString("user_name");
//            	String mail = resultSet.getString("Mail");
//            	int type  = resultSet.getInt("Type");
//            	String password = resultSet.getString("Password");
//            	boolean status = resultSet.getBoolean("Status");
//            	
//            	User obj = new User();
//                obj.setId(id);
//                obj.setMail(mail);
//                obj.setPassword(password);
//                obj.setStatus(status);
//                obj.setType(type);
//                obj.setUser_name(user_name);
//
//                result.add(obj);
//
//            }
//            result.forEach(x -> System.out.println(x));

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
