package obj;

import java.util.List;

public class ClientSubCommand {
	
	String storeName;
	float priceStore;
	List<Article> commandToStore;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public float getPriceStore() {
		return priceStore;
	}
	public void setPriceStore(float priceStore) {
		this.priceStore = priceStore;
	}
	public List<Article> getCommandToStore() {
		return commandToStore;
	}
	public void setCommandToStore(List<Article> commandToStore) {
		this.commandToStore = commandToStore;
	}
	
}
