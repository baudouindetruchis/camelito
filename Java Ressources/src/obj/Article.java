package obj;

import JavaFunction.UtilFunc;

public class Article {

	int id;
	String name;
	String description;
	String magasin;
	float selling_price = 0;
	float real_price = 0;
	float saving;
	int quantity;
	int stock;
	String img;

	public Article() {
		
	}
	
	public Article(int id, String name, String description, String magasin, float selling_price, float real_price,
			int quantity, int stock, String img) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.magasin = magasin;
		this.selling_price = selling_price;
		this.real_price = real_price;
		this.saving = real_price-selling_price;
		this.quantity = quantity;
		this.stock = stock;
		this.img = img;
	}

	public float getSaving() {
		return saving;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

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
		float s = UtilFunc.round(this.real_price - this.selling_price, 2);
		this.saving = s;
	}

	public float getReal_price() {
		return real_price;
	}

	public void setReal_price(float real_price) {
		this.real_price = real_price;
		float s = UtilFunc.round(this.real_price - this.selling_price, 2);
		this.saving = s;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
