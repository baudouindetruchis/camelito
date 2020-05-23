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

import JavaFunction.ConnectionFunctions;

/**
 * Servlet implementation class InscriptionForm
 */
@WebServlet("/InscriptionForm")
public class InscriptionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get values from the form request
		String inscrPswMsg ="";
		String inscrPseudoMsg ="";
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String secondPassword = request.getParameter("secondPassword");
		String pseudo = request.getParameter("pseudo");
		String categorie = request.getParameter("categorie");
		
		int type = ConnectionFunctions.getType(categorie);
		String promotion =request.getParameter("year");
		int year = ConnectionFunctions.getPromo( promotion);

		//connect to the bdd
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		String page = "./view/index.jsp";

		HttpSession session = request.getSession();
		try (Connection con = DriverManager.getConnection(url, user, psw)) {
			PreparedStatement verifPseudoUnicity = con.prepareStatement("SELECT * FROM public.users WHERE user_name ='"+ pseudo+ "'");
			
			ResultSet rsPseudo = verifPseudoUnicity.executeQuery();
					
			
			if(rsPseudo.next()) {
				inscrPseudoMsg = "Pseudo dejà utilisé";
			}else {
				inscrPseudoMsg ="";
				//if the two password match
				if (password.contentEquals(secondPassword)) {
					password = BCrypt.hashPassword(password);
					
					//add a user to the bdd based on form value
					PreparedStatement addUser = con.prepareStatement("INSERT INTO public.users(user_name, mail, type, password) "
							+ "VALUES('"+ pseudo + "','" + email + "'," + type + ",'" + password + "')");
					addUser.execute();
					//get auto generated id
					PreparedStatement getId = con.prepareStatement("SELECT MAX(ID) AS id FROM public.users LIMIT 1");
					ResultSet rs = getId.executeQuery();
					rs.next();
					int id = rs.getInt("id");
					//add a details to the bdd based on form value
					PreparedStatement addDetail = con.prepareStatement("INSERT INTO public.details(id_user, last_name, first_name, promotion)"
							+ "VALUES("+ id + ",'" + lastname + "','" + firstname +"','"+ year+ "')");
					addDetail.execute(); 				
					
					// create the object user and initialize session attributes
					
					ConnectionFunctions.connect(request, id, email, type, pseudo, firstname, lastname, year, 0, "","",false,"../public/images/Add_User.png");
					
					
					
					page = "./view/profil.jsp";
					
				} else {
					
					inscrPswMsg = "Erreur sur les mots de passe";	
				}
			}	
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		session.setAttribute("inscrPswMsg",inscrPswMsg );
		session.setAttribute("inscrPseudoMsg",inscrPseudoMsg );
		
		 
		//response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	

}
