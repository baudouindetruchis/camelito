package obj;

import java.util.ArrayList;
import java.util.List;

public class ClientSubCommand {
	
	String storeName="defaultName";
	float priceStore=0;
	List<Article> commandToStore = new ArrayList<Article>();
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public float getPriceStore() {
		return priceStore;
	}
	public void increasePriceStore(float priceStore) {
		this.priceStore += priceStore;
	}
	public List<Article> getCommandToStore() {
		return commandToStore;
	}
	public void addArticle(Article anArt) {
		this.commandToStore.add(anArt);
	}
	
}
