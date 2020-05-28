package obj;

public class Participant {
	int place;
	String pseudo;
	int score;
	
	public Participant(int place, String pseudo, int score) {
		this.place= place;
		this.pseudo = pseudo;
		this.score = score;
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
