
public class Owner {
	private String name; 
	private String location;
	private String email;
	private String phone;
	private String username;
	private Boolean isloggedin;
	
	public Owner(){
		this.name = name;
		this.location = location;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.isloggedin=false;
	}
	
	//Use: String[] info=owner.getinfo();
	//Before: owner is an Owner
	//After: info contains owners information [name, location, email, phone]
	public String[] getinfo(){
		String[] results = {this.name, this.location, this.email, this.phone, this.username};
		return results;
	}
	
	
//	Use: x = a.getUsername()
//	Before: a is an Owner
//	After: x is a String and is the username of a
	public String getUsername() {
		return this.username;
	}
	
//	Use: x = a.getName()
//	Before: a is an Owner
//	After: x is a String and is the name of a
	public String getName() {
		return this.name;
	}
	
//	Use: x = a.getLocation()
//	Before: a is an Owner
//	After: x is a String and is the location of a
	public String getLocation() {
		return this.location;
	}
	
//	Use: x = a.getEmail()
//	Before: a is an Owner
//	After: x is a String and is the email of a
	public String getEmail() {
		return this.email;
	}
	
//	Use: x = a.getPhone()
//	Before: a is an phone
//	After: x is a String and is the phone of a	
	public String getPhone() {
		return this.phone;
	}
	
//	Use: x = a.getUsername()
//	Before: a is an username
//	After: x is a String and is the username of a
	public void setUsername(String username) {
		this.username = username;
	}
	
	
//	Use: x = a.setName(x)
//	Before: a is an Owner, x is a string
//	After: x is the new name of a
	public void setName(String name) {
		this.name = name;
	}
	
//	Use: x = a.setLocation(x)
//	Before: a is an Owner, x is a string
//	After: x is the new location of a
	public void setLocation(String location) {
		this.location = location;
	}
	
//	Use: x = a.setEmail(x)
//	Before: a is an Owner, x is a string
//	After: x is the new email of a
	public void setEmail(String email){
		this.email = email;
	}
	
//	Use: x = a.setPhone(x)
//	Before: a is an Owner, x is a string
//	After: x is the new phone of a
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public Boolean isloggedin(){
		return this.isloggedin;
	}
	
	public void setloggedin(Boolean set){
		this.isloggedin=set;
	}

}
