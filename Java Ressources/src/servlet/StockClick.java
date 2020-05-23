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
@WebServlet("/StockClick")
public class StockClick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockClick() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get session atribute
		String newValStr = request.getParameter("newStock");
		String id_articleStr = request.getParameter("id");
		String reqSqlOrder = request.getParameter("sqlOrder");

		// connection a la bdd
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int user_id = user.getId(); 

		String sqlOrder =(String) session.getAttribute("sqlOrder");
		
		if(reqSqlOrder!= null && !reqSqlOrder.isEmpty()){
			session.setAttribute("sqlOrder", reqSqlOrder);

			List<Article> stockList = StockFunctions.getStockList(user_id, reqSqlOrder);
			session.setAttribute("stockList", stockList);
		
		} else if(newValStr!= null && !newValStr.isEmpty() && id_articleStr!= null && !id_articleStr.isEmpty() ) {
			// if request have param it's a stock modif
			
			int newVal = (int) Math.round(Float.parseFloat(newValStr));
			int id_article = Integer.parseInt(id_articleStr);
			
			//update bdd data
			StockFunctions.updateBddStock(newVal, id_article);
			
		} else {
			//otherwise it's empty all
			StockFunctions.emptyStock(user_id);
		}
		
		//update session data
		List<Article> stockList = StockFunctions.getStockList(user_id, sqlOrder);
		session.setAttribute("stockList", stockList);
		
		// don't reload the whole page
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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
