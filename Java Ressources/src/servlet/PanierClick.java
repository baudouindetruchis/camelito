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
				msg+=ArticleListFunctions.updateScoreAndSaving(session);
				msg += "<br><br>Vous allez automatiquement être redirigé vers une plateforme de paiement, veuillez patienter...";
				ArticleListFunctions.decreaseStockForCart(session);
				ArticleListFunctions.actionPay(request);
				ArticleListFunctions.setCommandList(session);
				CommandsFunctions.reloadCommands(request, response, session);
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
		PrintWriter out = response.getWriter();
		out.println(msg);
		System.out.println("set msg to :");
		System.out.println("."+msg+".");
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
