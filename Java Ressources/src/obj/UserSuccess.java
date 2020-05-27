package obj;

public class UserSuccess {
	
	String type;
	int value;
	String pic;
	boolean isDone = false;
	boolean isBest = false;
	
	public UserSuccess(String type, int value, String pic) {
		this.type=type;
		this.value=value;
		this.pic=pic;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setToDone() {
		this.isDone = true;
	}
	public boolean isBest() {
		return isBest;
	}
	public void setToBestOfType() {
		this.isBest = true;
	}
	public void setToNOTBestOfType() {
		this.isBest = false;
	}
	
	
}
