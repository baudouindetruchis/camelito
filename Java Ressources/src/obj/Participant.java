package obj;

public class Participant {
	int id;
	int place;
	String pseudo;
	int score;
	String favSucces;
	int favSuccesId;

	public Participant(int place, String pseudo, int score, String favSucces, int favSuccesId, int id) {
		this.id= id;
		this.place= place;
		this.pseudo = pseudo;
		this.score = score;
		this.favSucces = favSucces;
		this.favSuccesId = favSuccesId;
	}
	public Participant() {
		this.id= 0;
		this.place= 0;
		this.pseudo = "";
		this.score = 0;
		this.favSucces = "";
		this.favSuccesId = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFavSuccesId() {
		return favSuccesId;
	}
	public void setFavSuccesId(int favSuccesId) {
		this.favSuccesId = favSuccesId;
	}
	public String getFavSucces() {
		return favSucces;
	}
	public void setFavSucces(String favSucces) {
		this.favSucces = favSucces;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
