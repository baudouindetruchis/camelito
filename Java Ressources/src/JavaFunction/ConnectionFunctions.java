package JavaFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import obj.User;
import obj.UserSuccess;

public class ConnectionFunctions {
	
	
	/**
	 * Create the object user and set it as session attribut
	 * @param request
	 * @param id
	 * @param email
	 * @param type
	 * @param pseudo
	 * @param firstName
	 * @param lastName
	 * @param year
	 * @param score
	 * @param storeName
	 * @param address
	 * @param status
	 * @param profilPic
	 * @param savings
	 */
	public static void connect( HttpServletRequest request, int id,  String email, int type, String pseudo, String firstName, String lastName, int year, int score, String storeName, String address, Boolean status, String profilPic, float savings) {
		HttpSession session = request.getSession(false);
		
		User obj = new User();
		obj.setId(id);
		obj.setMail(email);
		obj.setType(type);
		obj.setPseudo(pseudo);
		obj.setFirst_name(firstName);
		obj.setLast_name(lastName); 
		obj.setPromotion(year);
		obj.setScore(score);
		obj.setStatus(status);
		obj.setProfilPic(profilPic);
		obj.setSaving(savings);

		//add all value to the 
		
		session.setAttribute("status",obj.getStatus());
		session.setAttribute("type",obj.getType());
		session.setAttribute("promo",obj.getPromotion());
		session.setAttribute("id",obj.getId());
		
		if(obj.getType() == 1 || obj.getType() == 2) {
			session.setAttribute("user", obj);
		}
		else {
			connectAsComm(  request,  storeName,  address,  obj   ) ;
				
		}
		
		setSuccess(session);//TODO
	}
	/**
	 * set the user obj with the right propertie depending on the type
	 * @param request
	 * @param storeName
	 * @param address
	 * @param obj
	 */
	public static void connectAsComm( HttpServletRequest request, String storeName, String address, User obj   ) {
		HttpSession session = request.getSession(false);
		
		obj.setAddress(address);
		obj.setStoreName(storeName);
		//add all value to the 
		session.setAttribute("user", obj);
		
	}
	/**
	 * get the number corresponding to the type entered as a string
	 * @param categorie
	 * @return
	 */
	public static int getType(String categorie) {
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
		return type;
	}
	/**
	 * get thyear of diploma depending of the name of promotion
	 * @param promotion
	 * @return
	 */
	public static int getPromo(String promotion) {		
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
		return year;		
	}

	/**
	 * Set the successList session attribute 
	 * @param session
	 */
	public static void setSuccess(HttpSession session) {
		System.out.println("setting success");
		User user = (User) session.getAttribute("user");
		int id_user = user.getId();
		String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String USER_BDD = "postgres";
		String PSW = "123";
		
		int userScore = user.getScore();
		int userSaving = (int) Math.floor(user.getSaving());
		//check if the user have chosed a profil pic
		int countDefault =0;
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement psCount = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM public.details WHERE id_user = " + id_user + " AND profil_pic = '../public/images/Add_User.png'");
			ResultSet r = psCount.executeQuery();
			r.next();
			countDefault = r.getInt("rowcount");
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("countDefault:"+countDefault);
		int isPersonalized = countDefault==0 ? 1 : 0; //si l'usitisateur n'a pas l'image pas default alors il a personalisé
		//recupère le nombre de commande passé par l'utilisateur
		int nbComm = 0;
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement psCount = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM public.carts WHERE id_user = " + id_user + " AND status = true");
			ResultSet r = psCount.executeQuery();
			r.next();
			nbComm = r.getInt("rowcount");
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final int userNbCommand = nbComm;
		//defini une map avec tt les type et les score de l'utilisateur pour chacun
		@SuppressWarnings("serial")
		Map<String, Integer>mapTypeAndVal =new HashMap<String, Integer>() {{
	        put("bienvenu", 0);
	        put("perso", isPersonalized);
	        put("score", userScore);
	        put("command", userNbCommand);
	        put("saving", userSaving);
	    }}; 
		List<UserSuccess> listSuccess = new ArrayList<UserSuccess>();
		List<UserSuccess> subListSuccessDone;
		List<UserSuccess> subListSuccessNOTDone;
		String type;
	    int userVal;

	    //defin variable before loop to avoid memory loss
	    UserSuccess aSuccess;
		String successType;
		int successValue;
		String successPic;
		String successName;
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			for (Map.Entry<String, Integer> oneTypeAndVal : mapTypeAndVal.entrySet()) {
			    type = oneTypeAndVal.getKey();
			    userVal = oneTypeAndVal.getValue();
			    subListSuccessDone = new ArrayList<UserSuccess>();
			    subListSuccessNOTDone = new ArrayList<UserSuccess>();

				PreparedStatement getSuccess = con
						.prepareStatement("SELECT * FROM public.success WHERE success_type = '" + type+"'");
				ResultSet rsSuccess = getSuccess.executeQuery();
				
				if (rsSuccess == null) {
					System.out.println("Erreur de connexion");
				} else {
					while (rsSuccess.next()) {
						//add every succes
						successName = rsSuccess.getString("success_name");
						successType = rsSuccess.getString("success_type");
						successValue = rsSuccess.getInt("value");
						successPic = rsSuccess.getString("succes_pic");
						
						aSuccess=new UserSuccess(successName, successType, successValue, successPic);
						if(successValue<=userVal) {
							aSuccess.setToDone();
							subListSuccessDone.add(aSuccess);
						} else {
							subListSuccessNOTDone.add(aSuccess);
						}
					}
//					set the best succes for a type
					if(subListSuccessDone.size()!=0) {
						UserSuccess bestSuccess = subListSuccessDone.remove(subListSuccessDone.size()-1);
						bestSuccess.setToBestOfType();
						subListSuccessDone.add(bestSuccess);
					}
					listSuccess.addAll(subListSuccessDone);
					listSuccess.addAll(subListSuccessNOTDone);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//set session attribute
		session.setAttribute("successList", listSuccess);
	}
}
