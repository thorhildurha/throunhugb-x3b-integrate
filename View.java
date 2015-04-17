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

		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    search = new Search(loggedin, frame);
	    search.searchDialog();

	}
}
