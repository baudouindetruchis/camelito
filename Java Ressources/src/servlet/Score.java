package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
  
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



import obj.User;

/**
 * Servlet implementation class modifyProfilForm
 */
@WebServlet("/Score")
public class Score extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Score() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
				//time to connect to bdd
				String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
				String user = "postgres";
				String psw = "123";
				String page = "./view/score.jsp";
				
				try  {
					Connection con = DriverManager.getConnection(url, user, psw);
					//int id = (int) session.getAttribute("id");
					
					Statement st;
					ResultSet rst;
					
					st = con.createStatement();
					rst=st.executeQuery("SELECT * FROM score");
					
					while(rst.next()) {
						System.out.print(rst.getInt("id")+"\t");
						System.out.print(rst.getInt("point")+"\t");
						System.out.println();
					}
					
					
		            Context initContext = new InitialContext();
		            Statement statement = con.createStatement();
		              
		            //Récupération des stations dans la bd
		            ResultSet resultat =statement.executeQuery("SELECT * FROM score");
		              
		            Map<String, String> stations = new HashMap<String, String>();
		            String id = "";
		            String point= "";
		              
		              
		            while (resultat.next()) {
		                 id=resultat.getString("id");
		                 point=resultat.getString("point");
		                 stations.put(id, point);
		                               
		                }
		              
		            /* Stockage du résultat et des messages d'erreur dans l'objet request */
		            request.setAttribute( "stations", stations );
		  
		            resultat.close();
		            statement.close();
		            con.close();                             
		            statement = null;
		            resultat = null;					
					
	
		
					}catch(Exception e) {
						e.printStackTrace();
					}
				
				this.getServletContext().getRequestDispatcher( "/WebContent/score.jsp" ).forward( request, response );
				
				
				}
									


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
		//doGet(request, response);
	}
	

	
	}
