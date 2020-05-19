package obj;

import java.util.ArrayList;
import java.util.List;

public class ClientCommand {
	
	int id;
	float priceTotal;
	List<ClientSubCommand> commandTotal=new ArrayList<ClientSubCommand>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(float priceTotal) {
		this.priceTotal = priceTotal;
	}
	
	public void addArticle(Article art) {
		boolean added = false;
		for(ClientSubCommand tempComm : commandTotal) {
			if(tempComm.getStoreName().equals(art.getMagasin())) {
				int id=commandTotal.indexOf(tempComm);
				tempComm.increasePriceStore(art.getSelling_price()*art.getQuantity());
				tempComm.addArticle(art);
				this.commandTotal.set(id, tempComm);
				added=true;
			}
		}

		if(!added) {
			ClientSubCommand tempComm = new ClientSubCommand();
			tempComm.setStoreName(art.getMagasin());
			tempComm.increasePriceStore(art.getSelling_price()*art.getQuantity());
			tempComm.addArticle(art);
			this.commandTotal.add(tempComm);
		}
	}
	
	public List<ClientSubCommand> getCommandTotal() {
		return commandTotal;
	}
	
}
