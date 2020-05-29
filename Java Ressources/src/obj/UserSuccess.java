package obj;

public class UserSuccess {

	public String name;
	String type;
	int value;
	String pic;
	boolean isDone = false;
	boolean isBest = false;
	boolean isLast = false;
	
	public boolean getIsLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public UserSuccess(String name, String type, int value, String pic, Boolean isLast) {
		this.name=name;
		this.type=type;
		this.value=value;
		this.pic=pic;
		this.isLast=isLast;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public void setBest(boolean isBest) {
		this.isBest = isBest;
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
	public boolean getIsDone() {
		return isDone;
	}
	public void setToDone() {
		this.isDone = true;
	}
	public boolean getIsBest() {
		return isBest;
	}
	public void setToBestOfType() {
		this.isBest = true;
	}
	public void setToNOTBestOfType() {
		this.isBest = false;
	}
	
	
}
