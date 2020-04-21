package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/loginServlet", "/FormServlet", "/Form_Servlet"})
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read form fields
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");

		System.out.println("firstname: " + firstname);
		System.out.println("last name: " + lastname);
		System.out.println("birthday: " + birthday);
		System.out.println("gender: " + gender);

		// Get response writer
		PrintWriter writer = response.getWriter();

		// Cookies
		Cookie ck_first = new Cookie("firstname", firstname);
		Cookie ck_last = new Cookie("lastname", lastname);
		Cookie ck_bd = new Cookie("birthday", birthday);
		Cookie ck_gender = new Cookie("gender", gender);

		ck_first.setMaxAge(60*60*24*1);
		ck_last.setMaxAge(60*60*24*1);
		ck_bd.setMaxAge(60*60*24*1);
		ck_gender.setMaxAge(60*60*24*1);

		response.addCookie(ck_first);
		response.addCookie(ck_last);
		response.addCookie(ck_bd);
		response.addCookie(ck_gender);

		// build HTML code
		String htmlResponse = "<html><table>";
		htmlResponse += "<tr> <td>First name</td> <td>Last name</td> <td>Birthday</td> <td>Genre</td> </tr><tr>";
		htmlResponse += "<td>" + firstname + "</td>";
		htmlResponse += "<td>" + lastname + "</td>";
		htmlResponse += "<td>" + birthday + "</td>";
		htmlResponse += "<td>" + gender + "</td>";
		htmlResponse += "</tr></table></html>";

	    // return response
	    writer.println(htmlResponse);
	}

}
