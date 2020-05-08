package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String secondPassword = request.getParameter("secondPassword");

		String pseudo = request.getParameter("pseudo");
		String categorie = request.getParameter("categorie");
		int type = 0;
		switch (categorie) {
		case "Client":
			type = 1;
			break;
		case "Asso":
			type = 2;
			break;
		case "Commercant":
			type = 3;
			break;

		}
		
		String promotion =request.getParameter("year");
		int year = 0;
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();
		int currentMonth =currentdate.getMonthValue();
		int delta = 0;
		if(currentMonth<=7)  delta = 1;
		switch (promotion){
		
			case "3":
				year = currentYear + 3 - delta;
				break;
			case "4": 
				year = currentYear + 2 - delta;
				break;
			case "5": 
				year = currentYear + 1 - delta;
				break;			
			case "1":
				year = currentYear + 5 - delta;
				break;			
			case "2":
				year = currentYear + 4 - delta;
				break;
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
				addDetail.execute(); //TODO if client/asso init score and list				
				
				// create the object user
				User obj = new User();
				obj.setId(id);
				obj.setMail(email);
				obj.setPassword(password);
				obj.setType(type);
				obj.setPseudo(pseudo);
				obj.setFirst_name(firstname);
				obj.setLast_name(lastname);
				obj.setPromotion(year);

				//add all value to the 
				session.setAttribute("user", obj);
				session.setAttribute("user_id", obj.getId());
				session.setAttribute("mail", obj.getMail());
				session.setAttribute("userName", obj.getPseudo());
				session.setAttribute("fName", obj.getFirst_name());
				session.setAttribute("lName", obj.getLast_name());
				session.setAttribute("type",obj.getType());

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
