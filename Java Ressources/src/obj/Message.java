package obj;

public class Message {
	String text;
	Boolean sendByAsso=false;
	int id;
	public Message(String text, Boolean sendByAsso, int id){
		this.text=text;
		this.sendByAsso=sendByAsso;
		this.id=id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getSendByAsso() {
		return sendByAsso;
	}
	public void setSendByAsso(Boolean sendByAsso) {
		this.sendByAsso = sendByAsso;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
