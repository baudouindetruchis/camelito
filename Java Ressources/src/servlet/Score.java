package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.Participant;
import obj.User;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/Score")
public class Score extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		setParticipantList(session);

		String page = "./view/score.jsp";// TODO
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void  setParticipantList(HttpSession session) {
		List<Participant> participantsList = new ArrayList<Participant>();
		User currentUser = (User) session.getAttribute("user");
		int currentUserId = currentUser.getId();
		
		int indexOfUser = 0;
		int count = 0;
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement getParticipant = con.prepareStatement("SELECT * FROM details WHERE score IS NOT NULL ORDER BY score DESC");
			ResultSet rsParticipant = getParticipant.executeQuery();
			
			Participant aParticipant;
			int user_id;
			String pseudo;
			int score;
			String favSucces;
			int favSuccesId;
						
			int classement = 0;
			int previousScore = (int) Double.MAX_VALUE;
			while (rsParticipant.next()) {
				user_id = rsParticipant.getInt("id_user");
				score = rsParticipant.getInt("score");
				favSuccesId = rsParticipant.getInt("favSuccess");
				
				//get pseudo
				PreparedStatement getPseudo = con.prepareStatement("SELECT * FROM users WHERE id ="+user_id);
				ResultSet rsPseudo = getPseudo.executeQuery();
				rsPseudo.next();
				pseudo = rsPseudo.getString("user_name");
				
				//get fav succes
				if(favSuccesId != 0) {
					PreparedStatement getFavSucces = con.prepareStatement("SELECT * FROM success WHERE id ="+favSuccesId);
					ResultSet rsFavSucces = getFavSucces.executeQuery();
					rsFavSucces.next();
					favSucces = rsFavSucces.getString("success_name");					
				} else {
					favSucces="";
				}
				
				//handel classement
				if(score<previousScore) {
					previousScore=score;
					classement=count+1;
				}
				
				if(currentUserId==user_id){
					indexOfUser=count;
				}
				
				//create participant and a it to list
				aParticipant = new Participant(classement, pseudo, score, favSucces);
				participantsList.add(aParticipant);
				count++;
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// handel fist and last user
		Participant prevParticipant = participantsList.get(indexOfUser+1);
		Participant currParticipant = participantsList.get(indexOfUser);
		Participant succParticipant = participantsList.get(indexOfUser-1);
		
		session.setAttribute("prevParticipant", prevParticipant);
		session.setAttribute("currParticipant", currParticipant);
		session.setAttribute("succParticipant", succParticipant);	

		List<Participant> shortParticipantsList = participantsList.subList(0, 10);
		session.setAttribute("participantsList", shortParticipantsList);

		//blank participant used for first and last place
		Participant blankParticipant = new Participant();
		participantsList.add(blankParticipant);
		participantsList.add(0, blankParticipant);
		session.setAttribute("fullParticipantsList", participantsList);		
	}

}