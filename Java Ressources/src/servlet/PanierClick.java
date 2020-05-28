package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFunction.ArticleListFunctions;
import JavaFunction.CommandsFunctions;
import JavaFunction.ConnectionFunctions;

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
		String msg = "";

		HttpSession session = request.getSession(false);

		switch (action) {
		case "comm":
//			actionMore(request);
			break;
		case "ann":
			ArticleListFunctions.actionAnnul(request);
			session.removeAttribute("panierList");
			session.removeAttribute("total_price");
			break;
		case "pay":
			Object[] res = ArticleListFunctions.isCommandValid(session);
			boolean isValid = (boolean) res[0];
			msg+=(String) res[1];
			if (isValid) {
				// Careful setCommandList and reloadCommands must be run before the others
				CommandsFunctions.reloadCommands(request, response, session);
				
				ArticleListFunctions.decreaseStockForCart(session);
				ArticleListFunctions.actionPay(request);
				msg+=ArticleListFunctions.updateScoreAndSaving(session);
				msg += "<br><br>Vous allez automatiquement être redirigé vers une plateforme de paiement, veuillez patienter...";
				//must be run after score and savings
				ArticleListFunctions.setCommandList(session);
				ConnectionFunctions.setSuccess(session);//update the success list with new savings and score
				session.removeAttribute("panierList");
				session.removeAttribute("total_price");
			} else {
				msg += "Votre commande n'a malheuresement pas pu aboutir";
			}
			break;
		case "less":
		case "more":
		case "supp":
			msg = ArticleListFunctions.modifQuantity(request);
			ArticleListFunctions.loadCart(session);
//			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			break;

		default:
			msg = "404 : action inconnue in doGet panier click";
			break;
		}
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(msg);
		out.close();
		return;
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
