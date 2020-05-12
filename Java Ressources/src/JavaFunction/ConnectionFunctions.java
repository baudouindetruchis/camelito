package JavaFunction;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import obj.User;

public class ConnectionFunctions {
	
	
	public static void connect( HttpServletRequest request, int id,  String email, int type, String pseudo, String firstName, String lastName, int year   ) {
		HttpSession session = request.getSession(false);
		
		System.out.println(email);
		
		User obj = new User();
		obj.setId(id);
		obj.setMail(email);
		obj.setType(type);
		obj.setPseudo(pseudo);
		obj.setFirst_name(firstName);
		obj.setLast_name(lastName); 
		obj.setPromotion(year);

		//add all value to the 
		session.setAttribute("user", obj);
		session.setAttribute("type",obj.getType());
		session.setAttribute("promo",obj.getPromotion());
		session.setAttribute("id",obj.getId());
		
	}
	
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
