package JavaFunction;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import obj.User;

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
	 */
	public static void connect( HttpServletRequest request, int id,  String email, int type, String pseudo, String firstName, String lastName, int year, int score, String storeName, String address, Boolean status, String profilPic   ) {
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

}
