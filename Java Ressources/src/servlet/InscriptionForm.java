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

import obj.User;

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
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String year = request.getParameter("year");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String secondPassword = request.getParameter("secondPassword");

		String pseudo = request.getParameter("pseudo");
		String categorie = request.getParameter("categorie");
		int type = 0;
		switch (categorie) {
		case "Client":
			type = 1;
		case "Asso":
			type = 2;
		case "Commercant":
			type = 3;

		}

		//connect to the bdd
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		String page = "./view/index.jsp";

		HttpSession session = request.getSession();
		try (Connection con = DriverManager.getConnection(url, user, psw)) {
			
			//if the two password match
			if (password.contentEquals(secondPassword)) { 
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
				PreparedStatement addDetail = con.prepareStatement("INSERT INTO public.details(id_user, last_name, first_name)"
						+ "VALUES("+ id + ",'" + lastname + "','" + firstname + "')");
				addDetail.execute(); //TODO if client/asso init score and list				
				
				// create the object user
				User obj = new User();
				obj.setId(id);
				obj.setMail(email);
				obj.setPassword(password);
				obj.setType(type);
				obj.setUser_name(pseudo);
				obj.setFirst_name(firstname);
				obj.setLast_name(lastname);

				//add all value to the 
				session.setAttribute("user", obj);
				session.setAttribute("user_id", obj.getId());
				session.setAttribute("mail", obj.getMail());
				session.setAttribute("userName", obj.getUser_name());
				session.setAttribute("fName", obj.getFirst_name());
				session.setAttribute("lName", obj.getLast_name());

				//finaly load the profil page
				page = "./view/profil.jsp";
				
			} else {
				System.out.println("erreur sur les mdp");
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// if request is not from HttpServletRequest, you should do a typecast before
		// save message in session
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
