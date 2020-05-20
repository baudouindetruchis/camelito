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
@WebServlet("/StockLoad")
public class StockLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockLoad() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get session and attribute
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int user_id = user.getId();
		
		//set session atribute
		String sqlOrder = "ORDER BY id ASC";
		session.setAttribute("sqlOrder", sqlOrder);
		
		List<Article> stockList = StockFunctions.getStockList(user_id, sqlOrder);
		session.setAttribute("stockList", stockList);
		
		// load page
		String page = "./view/stock.jsp";
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
