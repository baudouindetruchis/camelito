package JavaFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import obj.Article;

public class StockFunctions {

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
	private static final String USER_BDD = "postgres";
	private static final String PSW = "123";
	
	
	public static void updateBddStock(int newVal, int id_article) {
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			// there should only be one cart by user whith a false status
			PreparedStatement getArticle = con
					.prepareStatement("SELECT * FROM public.articles WHERE id = " + id_article);
			ResultSet theArticle = getArticle.executeQuery();
			if (theArticle == null) {
				System.out.println("Erreur de connexion (cart=null)");
			} else {
				// recuperation de la liste de course en cours
				theArticle.next();
				
				newVal=secureNewVal(newVal);
				
				PreparedStatement editStock = con.prepareStatement(
						"UPDATE public.articles SET available = " + newVal + " WHERE id = " + id_article);
				editStock.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static int secureNewVal(int val) {
		int newVal = val;
		if (newVal > 99) {
			newVal = 99;
		} else if (newVal < 0) {
			newVal = 0;
		}
		return newVal;
	}
	
	public static List<Article> getStockList(int user_id){
		List<Article> stockList = new ArrayList<Article>();
		
		try (Connection con = DriverManager.getConnection(URL, USER_BDD, PSW)) {
			
			PreparedStatement getStore = con
					.prepareStatement("SELECT * FROM public.stores WHERE id_user = " + user_id);
			ResultSet theStore = getStore.executeQuery();
			if (theStore == null) {
				System.out.println("Erreur de connexion (commandes=null)");
			} else {
				theStore.next();
				int id_store = theStore.getInt("id");
				String name_store = theStore.getString("name");
				PreparedStatement getArticles = con
						.prepareStatement("SELECT * FROM public.articles WHERE id_store = " + id_store);
				ResultSet rsArticle = getArticles.executeQuery();
				Article anArticle;
				
				String description;
				int id;
				String name;
				float real_price;
				float selling_price;
				int stock;
				
				while(rsArticle.next()){
					//get data
					description = rsArticle.getString("description");
					id = rsArticle.getInt("id");
					name = rsArticle.getString("name");
					real_price = rsArticle.getFloat("initial_price");
					selling_price= rsArticle.getFloat("selling_price");
					stock = rsArticle.getInt("available");
					
					//init object
					anArticle = new Article();
					anArticle.setDescription(description);
					anArticle.setId(id);
					anArticle.setMagasin(name_store);
					anArticle.setName(name);
					anArticle.setReal_price(real_price);
					anArticle.setSelling_price(selling_price);
					anArticle.setStock(stock);
					
					stockList.add(anArticle);					
				}
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockList;
	}	
}
