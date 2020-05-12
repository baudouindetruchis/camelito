package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				
				//know who is connected
				//HttpSession session = request.getSession();
				//int user_id = (int) session.getAttribute("user_id");
				//HttpSession session = request.getSession();
				
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
					

					//PreparedStatement getScore = con.prepareStatement("SELECT * FROM success WHERE id_user = " + id + " AND status = false");
					//PreparedStatement getScore = con.prepareStatement("SELECT * FROM score);
					//ResultSet getScore = getCart.executeQuery();
					
					//User obj = (User) session.getAttribute(getScore);
					//obj.setPseudo(getScore);
					
					//if((int) session.getAttribute("type")==1) {
								
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
									


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		//time to connect to bdd
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		String page = "./view/score.jsp";
		
		try  {
			Connection con = DriverManager.getConnection(url, user, psw);
			Statement st;
			ResultSet rst;
			
			st = con.createStatement();
			rst=st.executeQuery("SELECT * FROM score");
			
			while(rst.next()) {
				System.out.print(rst.getInt("id")+"\t");
				System.out.print(rst.getInt("point")+"\t");
				System.out.println();
			}
						
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
		
		
		
	}
	

	
	}
