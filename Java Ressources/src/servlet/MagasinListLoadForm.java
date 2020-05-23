package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		HashMap<Integer, Article> listArticles = new HashMap<Integer, Article>();
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
			PreparedStatement getUsers = con
					.prepareStatement("SELECT list_id_articles, liste_quantities FROM public.carts WHERE status = true");
			
			ResultSet users = getUsers.executeQuery();
			while(users.next()) { 
				
				Object array_articleByUser =   users.getArray("list_id_articles").getArray();
				Integer[] list_articleByUser = (Integer[]) array_articleByUser;
				
				Object array_articleQuantity =   users.getArray("liste_quantities").getArray();
				Integer[] list_articleQuantity = (Integer[]) array_articleQuantity;

				for(int i =0; i<list_articleByUser.length; i++) {
					Article newArticle =new Article();
					int id_Article = list_articleByUser[i];
					int quantity = list_articleQuantity[i];
					
					
					newArticle.setId(id_Article);
					PreparedStatement getArticle = con
							.prepareStatement("SELECT name, id_Store, selling_price FROM public.articles WHERE id = '" + id_Article+"'" );
					
					ResultSet articleInfo = getArticle.executeQuery();
					while(articleInfo.next()){
						int price = articleInfo.getInt("selling_price");
						int id_Store = articleInfo.getInt("id_Store");
						String name = articleInfo.getString("name");
						PreparedStatement getStore = con
								.prepareStatement("SELECT name FROM public.stores WHERE id = '" + id_Store+"'" );
						ResultSet getName = getStore.executeQuery();
						getName.next();
							
						String storeName = getName.getString("name");
						if(listArticles.containsKey(id_Article)) {
							quantity =quantity+ listArticles.get(id_Article).getQuantity();
								
						}					
						
						newArticle.setMagasin(storeName);
						newArticle.setQuantity(quantity);
						newArticle.setSelling_price(price);
						newArticle.setName(name);

						}
					listArticles.put(newArticle.getId(), newArticle);
				}
		
			}
			
			ClientCommand commandes = new ClientCommand();
			
			for(int key : listArticles.keySet()) {
				Article art =listArticles.get(key);
				commandes.addArticle(art);
			}
			List<ClientSubCommand> commandTotal= commandes.getCommandTotal();
			
			session.setAttribute("listCommands", commandTotal);
			
			
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
