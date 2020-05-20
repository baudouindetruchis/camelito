package obj;

import java.util.ArrayList;
import java.util.List;

public class User {
	int id;
	String pseudo;
	String mail;
	int type;
	String password;
	boolean status;
	
	String first_name;
	String last_name;
	int score;
	int promotion;
	
	
	String address;
	String storeName;
	public boolean getStatus() {
		return status;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	List<Integer> list_success = new ArrayList<Integer>();

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(int score) {
		this.score += score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Integer> getList_success() {
		return list_success;
	}

	public void add_a_success(int new_success) {
		this.list_success.add(new_success);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String user_name) {
		this.pseudo = user_name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String toString() {
		return "id:" + id + " user_name:" + pseudo + " mail:" + mail + " type:" + type + " password:" + password
				+ " status:" + status;
	}
}
