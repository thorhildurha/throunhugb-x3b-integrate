public class Book {
	private String Name; //same as prototype, available search input
	private String Author; //same as prototype, available search input
	private String ISBN; //same as prototype, available search input
	private String price; //controlled by owner 
	private String condition; //controlled by owner
	private String course; //same as prototype, available search input
	private Owner owner; //logged in user (location, name, email, phone number
	
	public Book(String name, String author, String isbn){//search 
		this.Name=name;
		this.Author=author;
		this.ISBN=isbn;
	}
	
	public Boolean update(String price, String condition){ //Returns true if no Error in registration method
		this.price=price;
		this.condition=condition;
		return true;
	}
	
	public String getName(){
		return this.Name;
	}
	public String getAuthor(){
		return this.Author;
	}
	public String getIsbn(){
		return this.ISBN;
	}
	
	public Owner getOwner() {
		return this.owner;
	}
	
	public void setOwner(Owner owner){
		this.owner = owner;
	}
	
	public String getPrice(){
		return this.price;
	}
	public void setPrice(String price){
		this.price=price;
	}

	
	
}
