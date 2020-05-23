package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFunction.StockFunctions;
import obj.Article;
import obj.User;

/**
 * Servlet implementation class StockLoad
 */
@WebServlet("/StockForm")
public class StockForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockForm() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// connection a la bdd
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int user_id = user.getId();

		// get value from request
		String d = request.getParameter("description");
		String rp = request.getParameter("real_price");
		String sp = request.getParameter("selling_price");
		String ida = request.getParameter("idArticle");
		String s = request.getParameter("stock");
		String picture = request.getParameter("pic");

		String sqlOrder =(String) session.getAttribute("sqlOrder");
		
		// get value or default 
		String description = d==null ? "" : d;
		float real_price = rp.matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(rp) : (float) 0.0;
		float selling_price = sp.matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(sp) : (float) 0.0;
		int stock = s.matches("[0-9].*") ? Integer.parseInt(s) : 0;
		int idArticle;
		
		//choose function based on 
		String act = request.getParameter("act");
		switch (act) {
		case "Ajouter":
			String n =  request.getParameter("name");
			String name = n==null ? "" : n;
			StockFunctions.addArticle(user_id, description, real_price, selling_price, stock, name, picture);			
			break;
		case "Modifier":
			idArticle = ida.matches("[0-9]*") ? Integer.parseInt(ida) : 0;
			StockFunctions.modifArticle(idArticle, description, real_price, selling_price, stock, picture);	
			break;
		case "Supprimer":
			idArticle = ida.matches("[0-9]*") ? Integer.parseInt(ida) : 0;
			StockFunctions.suppArticle(idArticle);
			break;

		default:
			break;
		}

		// update session data
		List<Article> stockList = StockFunctions.getStockList(user_id, sqlOrder);
		session.setAttribute("stockList", stockList);

		// load page
		String page = "./view/stock.jsp";
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
