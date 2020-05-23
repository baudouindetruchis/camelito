package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.ClientCommand;
import obj.ClientSubCommand;
import obj.Commande;
import obj.User;

/**
 * Servlet implementation class magasinListForm
 */
@WebServlet("/MagasinListLoadForm")
public class MagasinListLoadForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagasinListLoadForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		List< Commande> listStoresCommands = new ArrayList< Commande>();
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
			PreparedStatement getCommmands = con
					.prepareStatement("SELECT id_command, id_store, list_id_articles, liste_quantities,status FROM public.commands ");
			
			ResultSet commands = getCommmands.executeQuery();
			
			while(commands.next()) { 
				List< Article> listArticles = new ArrayList< Article>();
				Commande commande = new Commande();
				int cmdPrice =0;
				int id_store = commands.getInt("id_store");
				int id_command = commands.getInt("id_command");
				//TODO Boolean status = commands.getBoolean("status");
				String store_name="";
				
				PreparedStatement getStore = con
						.prepareStatement("SELECT name FROM public.stores WHERE id = '" + id_store+"'" );
				ResultSet getName = getStore.executeQuery();
				if(getName.next()) {
					store_name = getName.getString("name");
				}
				
				Object array_article =   commands.getArray("list_id_articles").getArray();
				Integer[] list_article = (Integer[]) array_article;
				
				Object array_articleQuantity =   commands.getArray("liste_quantities").getArray();
				Integer[] list_articleQuantity = (Integer[]) array_articleQuantity;
				
				for(int i =0; i<list_article.length; i++) {
					Article newArticle =new Article();
					int id_Article = list_article[i];
					int quantity = list_articleQuantity[i];
					
					newArticle.setQuantity(quantity);
					newArticle.setId(id_Article);
					PreparedStatement getArticle = con
							.prepareStatement("SELECT name, selling_price FROM public.articles WHERE id = '" + id_Article+"'" );
					
					ResultSet articleInfo = getArticle.executeQuery();
					if(articleInfo.next()){
						int price = articleInfo.getInt("selling_price");
						String name = articleInfo.getString("name");
						newArticle.setName(name);
						cmdPrice=cmdPrice+(price*quantity);
						System.out.println(cmdPrice);
					}
					
					listArticles.add(newArticle);
				}
				
				commande.setListArticles(listArticles);
				System.out.println(cmdPrice);
				commande.setprice(cmdPrice);
				commande.setStore_name(store_name);
				commande.setId(id_command);
				commande.setIdAndName(id_command+store_name);
				listStoresCommands.add(commande);
			}
			session.setAttribute("listStoresCommands", listStoresCommands);
			
			
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load page
		String page = "./view/magasinList.jsp";
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
