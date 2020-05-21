package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.Commande;

/**
 * Servlet implementation class ClientListLoadForm
 */
@WebServlet("/ClientListLoadForm")
public class ClientListLoadForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientListLoadForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Commande> listArticlesByUser = new ArrayList<Commande>();
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement getCommandes = con
					.prepareStatement("SELECT id_user, list_id_articles, liste_quantities FROM public.carts WHERE status = true");
			
			ResultSet commandes = getCommandes.executeQuery();
			int id=0;
			while(commandes.next()) {   
				int price = 0;
				Commande commande = new Commande();
				List<Article> listArticles= new ArrayList<>();
				
				int id_user = commandes.getInt("id_user");
				
				Object array_articleByUser =   commandes.getArray("list_id_articles").getArray();
				Integer[] list_articleByUser = (Integer[]) array_articleByUser;
				Object array_articleQuantity =   commandes.getArray("liste_quantities").getArray();
				Integer[] list_articleQuantity = (Integer[]) array_articleQuantity;
				
				PreparedStatement getUser = con.prepareStatement("SELECT user_name FROM public.users WHERE id = '" + id_user+"'" );
				ResultSet usernameRS = getUser.executeQuery();
				usernameRS.next();
				
				String user_Name = usernameRS.getString("user_name");

				for(int i =0; i<list_articleByUser.length; i++) {
					int id_Article = list_articleByUser[i];
					int quantity = list_articleQuantity[i];
					Article newArticle =new Article();
					PreparedStatement getArticle = con.prepareStatement("SELECT name,  selling_price FROM public.articles WHERE id = '" + id_Article+"'" );
					ResultSet articleInfo = getArticle.executeQuery();
					articleInfo.next();
					
					price =price + articleInfo.getInt("selling_price");
					String name = articleInfo.getString("name");

					newArticle.setName(name);
					newArticle.setQuantity(quantity);
					newArticle.setId(id_Article);
					listArticles.add(newArticle);	
				}
				id++;
				commande.setprice(price);
				commande.setId(user_Name + String.valueOf(id));
				commande.setUser_name(user_Name);
				commande.setListArticles(listArticles);
				listArticlesByUser.add(commande);
			}
			
			session.setAttribute("listArticlesByUser", listArticlesByUser);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("./view/clientsList.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
