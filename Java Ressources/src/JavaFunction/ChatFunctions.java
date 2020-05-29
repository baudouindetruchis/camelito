package JavaFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChatFunctions {

	private static final String URL ="jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * Insert into the bdd the message send via an asso account
	 * @param request
	 * @param con
	 * @param text
	 */
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
/**
 * Insert into the bdd the message send via an store account
 * @param request
 * @param con
 * @param text
 */
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
