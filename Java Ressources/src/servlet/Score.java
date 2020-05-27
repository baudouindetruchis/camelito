package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class servlet
 */
@WebServlet("/Score")
public class Score extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Score() {
        super();
        //this.nom = "nom par defaut";
        
        System.out.println("la servlet a bien était appelé");
        String driverName = "org.postgresql.Driver";
        String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/";
        String dbName = "camelitoLocal";
        String userId = "postgres";
        String password = "123";

        try {
        Class.forName(driverName);
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try{ 
        	connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
        	statement=connection.createStatement();
        	String sql ="SELECT * FROM details WHERE score IS NOT NULL ORDER BY score";

        	resultSet = statement.executeQuery(sql);
        	while(resultSet.next()) {
        		resultSet.getString("last_name");
        		resultSet.getString("first_name");
        		resultSet.getString("score");
        	}

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println("toute la servlet à était lue");
        // TODO Auto-generated constructor stub
    }
    
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
		String page = "./view/score.jsp";// TODO
		response.sendRedirect(page);
				}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	}