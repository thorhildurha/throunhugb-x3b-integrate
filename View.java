import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import database.client.*;

public class View {
	private final static Owner loggedin=new Owner(); //We do not want to put public here because then it is global
								   //Now this is a private static variable in the view class
	public static JFrame frame=new JFrame(); //We only want one application window named frame	
	public static MyPages mypage;  //We only want one mypage, that we can change
	public static Search search;
	
	public View(){
		frame = new JFrame();
	    Setup();
	}
	public static void main(String[] args){
		View view = new View();
	}
	public void Setup(){	
		DatabaseBookScraper.get().createBook(245, "Mamma", "Jón Trausti", 3000, "description", "Verkfræði", "rafmagn");
		DatabaseBookScraper.get().createBook(898, "Rauða gula hænan", "Agnes", 3000, "description", "Verkfræði", "rafmagn");
		DatabaseBookScraper.get().createBook(976, "Rauða mamman", "ég", 3333, "des", "verk", "raf");
		UserAccount some = UserAccountTable.get().getAccount("gudrunerla");
		int id = some.accountID;
		UserBookTable.get().eraseBook(245);
		UserBookTable.get().eraseBook(898);
		UserBookTable.get().eraseBook(976);
		UserBookTable.get().createBook(id, 898);
		UserBookTable.get().createBook(id, 245);
		UserBookTable.get().createBook(id, 976);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    search = new Search(loggedin, frame);
	    search.searchDialog();

	}
}
