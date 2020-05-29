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

/**
 * Servlet implementation class ChatForm
 */
@WebServlet("/ChatForm")
public class ChatForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
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
		int idStoreTalkingTo=getStoreId(nameTalmingToStore);
		if(idStoreTalkingTo!=0) {
			session.setAttribute("talkingTo", idStoreTalkingTo);
		}else {
			String text = (String) request.getParameter("txt");
			int id_user = (int) session.getAttribute("id");
			
			try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
				if((int)session.getAttribute("type")==3) {
					sentTextComm( con,  text,  id_user);
				}else {
					sentTextAsso( request, con,  text);
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
	
	public static void sentTextComm(Connection con, String text, int id_user) {
		
		try {
			PreparedStatement getIdStore = con.prepareStatement("SELECT id FROM public.stores WHERE id_user='"+ id_user+"'");
			ResultSet rsStore = getIdStore.executeQuery();
			int  id_store=0;
			while(rsStore.next()) {
				id_store = rsStore.getInt("id");
			}
			
			
			PreparedStatement sendtext;
			sendtext = con.prepareStatement("INSERT INTO public.messages (id_store, txt, is_send_by_asso) "
					+ "VALUES('"+ id_store+"','"+text+ "'," +false+")");
			sendtext.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void sentTextAsso(HttpServletRequest request,Connection con, String text) {
		
		try {
			HttpSession session = request.getSession();
			int id_store =(int) session.getAttribute("talkingTo");
			
			PreparedStatement sendtext;
			sendtext = con.prepareStatement("INSERT INTO public.messages (id_store, txt, is_send_by_asso) "
					+ "VALUES('"+ id_store+"','"+text+ "'," +true+")");
			sendtext.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int getStoreId(String name) {
		int id_store = 0;
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement getId = con.prepareStatement("SELECT id FROM public.stores WHERE name ='"+ name+ "'");
			ResultSet rsID = getId.executeQuery();
			while(rsID.next()) {
				id_store = rsID.getInt("id");
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id_store;
	}

}
