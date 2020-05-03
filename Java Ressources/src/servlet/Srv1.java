package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Srv1
 */
@WebServlet(urlPatterns = {"/Srv1", "/Srv_1"})
public class Srv1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srv1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get parameter
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		String sex = request.getParameter("sex");

		// create cookies
		Cookie firstNameCookies = new Cookie("firstName", firstName);
		firstNameCookies.setMaxAge(60*60); //Store cookie for 1 hour
		response.addCookie(firstNameCookies);
		Cookie lastNameCookie = new Cookie("lastName", lastName);
		lastNameCookie.setMaxAge(60*60); //Store cookie for 1 hour
		response.addCookie(lastNameCookie);
		Cookie birthDateCookies = new Cookie("birthDate", birthDate);
		birthDateCookies.setMaxAge(60*60); //Store cookie for 1 hour
		response.addCookie(birthDateCookies);
		Cookie sexCookies = new Cookie("sex", sex);
		sexCookies.setMaxAge(60*60); //Store cookie for 1 hour
		response.addCookie(sexCookies);
		
		//set table content
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Lab1 Form display</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<H1>Your Parameter : </H1>");
		
		out.println("<table border=1>");
		out.println("<tr>");
		out.println("	<td> First Name</td>");
		out.println("	<td> Last Name</td>	");
		out.println("<td> Birth Date</td>");
		out.println("<td>Sex</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>"+firstName+"</td>");
		out.println("<td>"+lastName+"</td>");
		out.println("<td>"+birthDate+"</td>");
		out.println("<td>"+sex+"</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</BODY></HTML>");
		
		doGet(request, response);
		
	}

}