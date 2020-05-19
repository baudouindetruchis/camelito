package obj;

import java.util.List;

public class ClientCommand {
	
	int id;
	float priceTotal;
	List<ClientSubCommand> commandTotal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getPriceTotal() {
		return priceTotal;
	}
	public void finaliseCommand() {
		//TODO set les total price
		this.priceTotal = priceTotal;
	}
	
	public void addArticle(Article art) {
		
	}
	public List<ClientSubCommand> getCommandTotal() {
		return commandTotal;
	}
	
}
