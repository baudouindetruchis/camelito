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

import obj.Message;

/**
 * Servlet implementation class loadChats
 */
@WebServlet("/loadChats")
public class loadChats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadChats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int usrType= (int) session.getAttribute("type");
		int id_user= (int) session.getAttribute("id");
		
		if(usrType==2) {//asso
			getMsgForAsso(request);			
		}else {//commercant
			
			
			
			
			List<Message> msgList = new ArrayList<Message>();
			
			try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
				PreparedStatement getStore = con.prepareStatement("SELECT id FROM public.stores WHERE id_user ='"+ id_user+ "'");
				ResultSet rsStore = getStore.executeQuery();
				if(rsStore.next()) {
					int id_store = rsStore.getInt("id");
					PreparedStatement getmessages = con.prepareStatement("SELECT * FROM public.messages WHERE id_store ='"+ id_store+ "'");
					ResultSet rsMessages = getmessages.executeQuery();
					while(rsMessages.next()) {
						int id= rsMessages.getInt("id");
						String txt = rsMessages.getString("txt");
						Boolean is_send_by_asso = rsMessages.getBoolean("is_send_by_asso");
						Message newMsg = new  Message(txt,is_send_by_asso, id);
						msgList.add(newMsg);
					}
					
				}
				session.setAttribute("listMsg", msgList);
			}catch (SQLException e) {
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		response.sendRedirect("./view/chatPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void getMsgForAsso(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Message> msgList = new ArrayList<Message>();
	
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int id_store =(int) session.getAttribute("talkingTo");
			PreparedStatement getmessages = con.prepareStatement("SELECT * FROM public.messages WHERE id_store ='"+ id_store+ "'");
			ResultSet rsMessages = getmessages.executeQuery();
			while(rsMessages.next()) {
				int id= rsMessages.getInt("id");
				String txt = rsMessages.getString("txt");
				Boolean is_send_by_asso = rsMessages.getBoolean("is_send_by_asso");
				Message newMsg = new  Message(txt,is_send_by_asso, id);
				msgList.add(newMsg);
			}
			session.setAttribute("listMsg", msgList);
		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
