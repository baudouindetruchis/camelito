package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFunction.ArticleListFunctions;

/**
 * Servlet implementation class PanierClick
 */
@WebServlet("/PanierClick")
public class PanierClick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierClick() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("act");
		String msg="";

		HttpSession session = request.getSession(false);

		switch (action) {
		case "comm":
//			actionMore(request);
			break;
		case "ann":
			ArticleListFunctions.actionAnnul(request);
			break;
		case "pay":
			ArticleListFunctions.isCommandValid();
			ArticleListFunctions.decreaseStock();
			ArticleListFunctions.actionPay(request);
			ArticleListFunctions.updateScore(session);
			break;
		case "less":
		case "more":
		case "supp":
			msg =ArticleListFunctions.modifQuantity(request);
			System.out.println(msg);
			ArticleListFunctions.loadCart(session);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			break;

		default:
			System.out.println("unkonwn action in doGet panier click");
			break;
		}

		System.out.println(action);
		switch (action) {
		case "pay":
			ArticleListFunctions.setCommandList(session);
		case "ann":
			session.removeAttribute("panierList");
			session.removeAttribute("total_price");
			break;
		default:
			break;
		}
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
