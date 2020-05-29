package obj;

public class Participant {
	int place;
	String pseudo;
	int score;
	String favSucces;

	public Participant(int place, String pseudo, int score, String favSucces) {
		this.place= place;
		this.pseudo = pseudo;
		this.score = score;
		this.favSucces = favSucces;
	}
	public Participant() {
		this.place= 0;
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
