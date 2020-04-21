package login;


public class Person implements java.io.Serializable{
	private String firstname;
	private String lastname;
	private String birthday;
	private String gender;
	
	
	public Person() {
	}
	
	public String getfirstname() {
		return firstname;
	}
	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getlastname() {
		return lastname;
	}
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	public String getbirthday() {
		return birthday;
	}
	public void setbirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	
}
