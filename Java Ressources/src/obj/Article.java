package obj;

public class Article {
	
	int id;
	String name;
	String description;
	String magasin;
	float selling_price;
	float real_price;
	int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMagasin() {
		return magasin;
	}

	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}

	public float getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(float selling_price) {
		this.selling_price = selling_price;
	}

	public float getReal_price() {
		return real_price;
	}

	public void setReal_price(float real_price) {
		this.real_price = real_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
