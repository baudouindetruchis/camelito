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

import JavaFunction.ChatFunctions;

/**
 * Servlet implementation class ChatForm
 */
@WebServlet("/ChatForm")
public class ChatForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL ="jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nameTalmingToStore = request.getParameter("talkingTo");
		if(nameTalmingToStore!= null) {
			session.setAttribute("talkingToName", nameTalmingToStore);
		}
		
		int idStoreTalkingTo=ChatFunctions.getStoreId(nameTalmingToStore);
		if(idStoreTalkingTo!=0) {
			session.setAttribute("talkingTo", idStoreTalkingTo);
		}else {
			String text = (String) request.getParameter("txt");
			int id_user = (int) session.getAttribute("id");
			
			try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
				if((int)session.getAttribute("type")==3) {
					ChatFunctions.sentTextComm( con,  text,  id_user);
				}else {
					ChatFunctions.sentTextAsso( request, con,  text);
				}
				
				

			}catch (SQLException e) {
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
