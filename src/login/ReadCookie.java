package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;


@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = null;
		String lastname = null;
		String birthday = null;
		String gender = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("firstname"))
					firstname = c.getValue();
				else if (c.getName().equals("lastname"))
					lastname = c.getValue();
				else if (c.getName().equals("birthday"))
					birthday = c.getValue();
				else if (c.getName().equals("gender"))
					gender = c.getValue();
			}
		}

		PrintWriter out = response.getWriter();

		// build HTML code
		out.println("<html><table>");
		out.println("<tr> <td>First name</td> <td>Last name</td> <td>Birthday</td> <td>Genre</td> </tr><tr>");
		out.println("<td>" + firstname + "</td>");
		out.println("<td>" + lastname + "</td>");
		out.println("<td>" + birthday + "</td>");
		out.println("<td>" + gender + "</td>");
		out.println("</tr></table></html>");

	}

}
