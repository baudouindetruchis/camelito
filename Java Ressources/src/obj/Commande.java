package obj;

import java.util.ArrayList;
import java.util.List;

public class Commande {
	float price;
	int id;
	String idAndName;
	String user_name;
	List<Article> listArticles= new ArrayList<>();
	String ready;
	String store_name;
	
	
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getReady() {
		return ready;
	}
	public void setReady(String ready) {
		this.ready = ready;
	}
	public String getIdAndName() {
		return idAndName;
	}
	public void setIdAndName(String idAndName) {
		this.idAndName = idAndName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public float getprice() {
		return price;
	}
	public void setprice(float price) {
		this.price = price;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public List<Article> getListArticles() {
		return listArticles;
	}
	public void setListArticles(List<Article> listArticles) {
		this.listArticles = listArticles;
	}
	
	
	
}
