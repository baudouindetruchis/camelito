package JavaFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.Commande;
import obj.User;

public class CommandsFunctions {
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	/**
	 * get the commands for each store and check if a command is not yet done to upload it or create a new one 
	 * @param request
	 * @param response
	 * @param session
	 */
	public static void reloadCommands(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		int id_user = (int) session.getAttribute("id");
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			PreparedStatement getCart = con.prepareStatement(
					"SELECT list_id_articles, liste_quantities FROM public.carts WHERE id_user = " + id_user + " AND status = false");
			ResultSet cart= getCart.executeQuery();
			cart.next();
			Object array_article =   cart.getArray("list_id_articles").getArray();
			Integer[] list_article = (Integer[]) array_article;
			Object array_articleQuantity =   cart.getArray("liste_quantities").getArray();
			Integer[] list_articleQuantity = (Integer[]) array_articleQuantity;
			
			for(int i =0; i<list_article.length; i++) {
			
				int id_Article = list_article[i];
				int quantity = list_articleQuantity[i];
				
				PreparedStatement getArticle = con.prepareStatement("SELECT id_store FROM public.articles WHERE id = '" + id_Article+"'" );
				ResultSet articleInfo = getArticle.executeQuery();
				articleInfo.next();
				
				int id_store = articleInfo.getInt("id_store");
				PreparedStatement getStoreCommand = con.prepareStatement("SELECT id, id_command, list_id_articles, liste_quantities FROM public.commands WHERE id_store = '" + id_store+"' AND status = true" );
				ResultSet storeCommand = getStoreCommand.executeQuery(); 
				
				if(storeCommand.next()) {//Une commande est en cours
						int id= storeCommand.getInt("id");
						Object array_listArticle =   storeCommand.getArray("list_id_articles").getArray();
						Integer[] list_listArticle = (Integer[]) array_listArticle;
						Object array_listArticleQuantity =   storeCommand.getArray("liste_quantities").getArray();
						Integer[] list_listArticleQuantity = (Integer[]) array_listArticleQuantity;
						updateIdCommandList(request,id);
						updateExistingList(con, list_listArticle,list_listArticleQuantity,id_Article,quantity,id);
						
				}else {
					
					int id= createCmd(con, id_Article, quantity, id_store);
					updateIdCommandList(request,id);	
				}
			}
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * create a command and return its id
	 * @param con
	 * @param id_Article
	 * @param quantity
	 * @param id_store
	 * @return
	 */
		public static int createCmd(Connection con, int id_Article, int quantity, int id_store ) {
			int id=0;
			try {
				PreparedStatement getStorepastCommand = con.prepareStatement("SELECT id, id_command FROM public.commands WHERE id_store = '" + id_store+"' AND status = false ORDER BY id_command" );
				ResultSet storePastCommand = getStorepastCommand.executeQuery();
				int idLastCommand=0;
				while(storePastCommand.next()) {//get the id of last command
					idLastCommand= storePastCommand.getInt("id_command"); 
				}
				
				List<Integer> listIdArticles = new ArrayList<Integer>();
				listIdArticles.add(id_Article);
				List<Integer> liste_quantities = new ArrayList<Integer>();
				liste_quantities.add(quantity);
				idLastCommand++;
				
				PreparedStatement creatCommand = con.prepareStatement("INSERT INTO public.commands(id_command, id_store,list_id_articles, liste_quantities) VALUES ('"+ idLastCommand+"','"+id_store+"','"+ ArticleListFunctions.listToString(listIdArticles)+"','"+ArticleListFunctions.listToString(liste_quantities) +"')" );
				creatCommand.execute();
				
				PreparedStatement getidCmd = con.prepareStatement("SELECT id FROM public.commands WHERE id_store = '" + id_store+"' AND id_command ='"+idLastCommand+"'" );
				ResultSet idCmd = getidCmd.executeQuery();
				idCmd.next() ;	
				 id= idCmd.getInt("id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return id;
		}
	
/**
 * When a store has a command that is not yet been closed, if a new article is add to carts the command is updated in the database
	 	
 * @param con
 * @param list_listArticle
 * @param list_listArticleQuantity
 * @param id_Article
 * @param quantity
 * @param id
 */
	public static void updateExistingList(Connection con, Integer[] list_listArticle,Integer[] list_listArticleQuantity,int id_Article, int quantity, int id) {
		int quant = 0;
		int pos;
		List<Integer>listIdArticles = new ArrayList<Integer>();
		List<Integer>listQuantityArticles = new ArrayList<Integer>();
		Boolean articleAlreadyInlist = false;
		for(pos=0; pos<list_listArticle.length; pos++) {
			
			if(list_listArticle[pos]==id_Article) {
				articleAlreadyInlist=true;
				quant = list_listArticleQuantity[pos];
				break;
			}
		}
		for(int point =0; point<list_listArticle.length;point++) {
			if(articleAlreadyInlist && pos!= point) {	
				listIdArticles.add(list_listArticle[point]);
				listQuantityArticles.add(list_listArticleQuantity[point]);
			}else if(!articleAlreadyInlist) {
				listIdArticles.add(list_listArticle[point]);
				listQuantityArticles.add(list_listArticleQuantity[point]);
			}		
		}
		
		if(articleAlreadyInlist) {
			listIdArticles.add(id_Article);
			listQuantityArticles.add(quant+quantity);
		}else{
			listIdArticles.add(id_Article);
			listQuantityArticles.add(quantity);
		}
		try {
			PreparedStatement getStoreCommand = con.prepareStatement("UPDATE public.commands SET list_id_articles='" +  ArticleListFunctions.listToString(listIdArticles)+ "', liste_quantities='"+  ArticleListFunctions.listToString(listQuantityArticles)+"'WHERE id = '" + id+"'" );
			getStoreCommand.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * When a article is added to one cart the list of id of command per store in the cart has to be updated
	 * @param request
	 * @param idCommand
	 */
	public static void updateIdCommandList(HttpServletRequest request, int idCommand) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		List<Integer> listCmd= new ArrayList<Integer>();
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			int user_id = user.getId();
			PreparedStatement getCartListIDCmd = con.prepareStatement(
					"SELECT liste_command FROM public.carts WHERE id_user = " + user_id + " AND status = false");
			ResultSet listCmdRS = getCartListIDCmd.executeQuery();
			listCmdRS.next();
			
			Object array_id =   listCmdRS.getArray("liste_command").getArray();
			Integer[] tabCmd=   (Integer[]) array_id;
			
			for(int id : tabCmd ) {
				listCmd.add(id);
			}
			 
			if(!listCmd.contains(idCommand)) {
				listCmd.add(idCommand);
			}
			 
			PreparedStatement editQuantity = con.prepareStatement(
					"UPDATE public.carts SET liste_command ='"+  ArticleListFunctions.listToString(listCmd)+ "'  WHERE id_user = " + user_id + " AND status = false");
			editQuantity.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get information about one specific article a create a obj Article
	 * @param con
	 * @param commands
	 * @param id_store
	 * @param id_Article
	 * @param quantity
	 * @return
	 */
	public static Article getArticle(Connection con, ResultSet commands, int id_Article,int quantity) {
			
			Article newArticle =new Article();
				try {
						newArticle.setQuantity(quantity);
						newArticle.setId(id_Article);
						PreparedStatement getArticle;
						getArticle = con.prepareStatement("SELECT name, pic, selling_price FROM public.articles WHERE id = '" + id_Article+"'" );
	
						ResultSet articleInfo = getArticle.executeQuery();
						if(articleInfo.next()){
							float price = articleInfo.getFloat("selling_price");
							String name = articleInfo.getString("name");
							newArticle.setName(name);
							newArticle.setSelling_price(price);
							newArticle.setImg(articleInfo.getString("pic"));
						}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return newArticle;
		}
	
	/**
	 * Get the information about a command et create and obj Command
	 * @param commands
	 * @param con
	 * @return
	 */
	public static Commande prepareCommmande(ResultSet commands, Connection con) {
		List< Article> listArticles = new ArrayList< Article>();
		Commande commande = new Commande();
		
		try {
			float cmdPrice =0;
			int id_store = commands.getInt("id_store");
			int id_command = commands.getInt("id_command");
			Boolean status = commands.getBoolean("status");
			String store_name="";
			
			PreparedStatement getStore = con
					.prepareStatement("SELECT name FROM public.stores WHERE id = '" + id_store+"'" );
			ResultSet getName = getStore.executeQuery();
			if(getName.next()) {
				store_name = getName.getString("name");
			}
			
			
			Object array_article =   commands.getArray("list_id_articles").getArray();
			Integer[] list_article = (Integer[]) array_article;
			
			Object array_articleQuantity;
			array_articleQuantity = commands.getArray("liste_quantities").getArray();
			Integer[] list_articleQuantity = (Integer[]) array_articleQuantity;
			Article newArticle=new Article();
			for(int i =0; i<list_article.length; i++) {
				
				int id_Article = list_article[i];
				int quantity = list_articleQuantity[i];
				newArticle =getArticle(con,commands, id_Article, quantity);

				
				cmdPrice=(float) (cmdPrice+(newArticle.getSelling_price()*quantity));
				listArticles.add(newArticle);
				}
				
			if(status) {
				commande.setReady("lacommandenestpasprete");
			}else{
				commande.setReady("lacommandeestprete");
			}
			commande.setListArticles(listArticles);
			commande.setprice(cmdPrice);
			commande.setStore_name(store_name);
			commande.setId(id_command);
			commande.setIdAndName(id_command+store_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	return commande;
	}

}
