package JavaFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.User;

public class CommandsFunctions {
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";

	
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
					int id= idCmd.getInt("id");
					updateIdCommandList(request,id);
					
					
				}
		
			}
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
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

}
