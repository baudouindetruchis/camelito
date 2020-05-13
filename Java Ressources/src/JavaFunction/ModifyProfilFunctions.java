package JavaFunction;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ModifyProfilFunctions {
	
	
public static boolean verifyEmail(String email) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) return false; 
		else return pat.matcher(email).matches();	
	}
	
	public static boolean verifyPromo(int promo) {
		
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();
		if ((promo>currentYear+5) || (promo<currentYear)) return false;
		else return true;
	
		}
}
