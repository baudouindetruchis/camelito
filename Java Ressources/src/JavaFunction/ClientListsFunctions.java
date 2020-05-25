package JavaFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obj.Article;
import obj.Commande;

public class ClientListsFunctions {

	
	
	/**
	 * get all the article in the cart of one user and return it as an obj command
	 * @param con
	 * @param commandes
	 * @return
	 */
	public static Commande getListArticle(Connection con,ResultSet commandes) {
		
		int price = 0;
		Commande commande = prepareCmd(commandes,con);
		
		try{
			List<Article> listArticles= new ArrayList<>();
			int id= commandes.getInt("id");
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
				Article newArticle =getArticle(con, id_Article, quantity);
				
				price =(int) (price + newArticle.getSelling_price());
				listArticles.add(newArticle);	
			}
			commande.setIdAndName("commande"+ String.valueOf(id)+user_Name);
			commande.setprice(price);
			commande.setId(id);
			commande.setUser_name(user_Name);
			commande.setListArticles(listArticles);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} 
		
		return commande;
	}

	/**
	 * Check if one command for one store hase already been picked up and set the variable in the obj Command
	 * @param commandes
	 * @param con
	 * @return
	 */
	public static Commande prepareCmd(ResultSet commandes,Connection con ) {
		Commande commande = new Commande();
		try {		
			
			Object array_Command =   commandes.getArray("liste_command").getArray();
			Integer[] list_IdCommand = (Integer[]) array_Command;
			Boolean cmdPrete=true;
			for(int idCommand: list_IdCommand) {
				PreparedStatement getCommand;
				
					getCommand = con.prepareStatement("SELECT status FROM public.commands WHERE id = '" + idCommand+"'" );
				
				ResultSet commandRS = getCommand.executeQuery();
				while(commandRS.next()){
					Boolean commandeReady = commandRS.getBoolean("status");
					if(commandeReady) {
						cmdPrete=false;
					}	
				}
			}
			if(cmdPrete) {
				commande.setReady("lacommandeestprete");
			}else {
				commande.setReady("lacommandenestpasprete");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commande;
		
	}
	
	
	/**
	 * get the information for one article and return it has an obj
	 * @param con
	 * @param id_Article
	 * @param quantity
	 * @return
	 */
	public static Article getArticle(Connection con, int id_Article, int quantity ) {
		Article newArticle =new Article();
		try {
			
			PreparedStatement getArticle = con.prepareStatement("SELECT id_store, name,  selling_price FROM public.articles WHERE id = '" + id_Article+"'" );
			ResultSet articleInfo = getArticle.executeQuery();
			articleInfo.next();
			
			int price = articleInfo.getInt("selling_price")*quantity;
			String name = articleInfo.getString("name");
			
			newArticle.setName(name);
			newArticle.setQuantity(quantity);
			newArticle.setId(id_Article);
			newArticle.setSelling_price(price);
			} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return newArticle;

	}

}
