package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ListItems")
public class ListItems extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a new session if does not exist
		HttpSession session = request.getSession(true);
		
		// Concat previous array in saved in session with new item
		ArrayList<String> previousItems = (ArrayList<String>) session.getAttribute("previousItems");
		if (previousItems == null) {
			previousItems = new ArrayList<String>();
		}
		String newItem = request.getParameter("newItem");
		if ((newItem != null) && (!newItem.trim().equals(""))) {
			previousItems.add(newItem);
		}
		
		// Save everything
		session.setAttribute("previousItems", previousItems);
				
		PrintWriter writer = response.getWriter();
		
		// build HTML code
		String htmlResponse = "<html><h3> List of Items</h3>";
		if (previousItems.size() == 0) {
			htmlResponse += "<i>No item</i>";
		} 
		else {
			htmlResponse += "<ul>";
			for(String item: previousItems) {
				htmlResponse += " <li>" + item;
			}
			htmlResponse += "</ul>";
		}
		htmlResponse += "</html>";
		
		// return response
	    writer.println(htmlResponse);
	    
	}

}
