import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import database.client.*;

public class MyPages extends JPanel implements ActionListener{
	private ArrayList<UserBook> books;
	public static JPanel panel = new JPanel();
	private Owner user;
	private JFrame frame;
	
	public MyPages(Owner owner, JFrame frame){
		this.user=owner;
		this.books = UserBookTable.get().getBooksByAccountID(owner.getid());
		this.frame=frame;
	}
	
//	Use: displayBooks(x,y,z);
//	Before: x is a JFrame, y is an Owner, z is a Book[]
//	After: Shows information about the Owner and the books that he is selling.
	public void mypagesForm() {
		frame.setTitle("My Pages"); // Set a new title to the frame
		JPanel labelpanel = new JPanel();
		JPanel bookspanel = new JPanel();
		panel=new JPanel();
		GroupLayout pan=new GroupLayout(panel);
		panel.setLayout(pan);
		GroupLayout.SequentialGroup horiGroup = pan.createSequentialGroup();
		GroupLayout.ParallelGroup panels=pan.createParallelGroup();		panels.addComponent(labelpanel);
		panels.addComponent(bookspanel);
		horiGroup.addGroup(panels);
		GroupLayout.SequentialGroup vertGroup = pan.createSequentialGroup();
		GroupLayout.ParallelGroup labelGroup=pan.createParallelGroup();
		labelGroup.addGap(150);
		GroupLayout.ParallelGroup bookGroup=pan.createParallelGroup();
		bookGroup.addGap(150);
		labelGroup.addComponent(labelpanel);
		bookGroup.addComponent(bookspanel);
		vertGroup.addGroup(labelGroup);
		vertGroup.addGroup(bookGroup);
		
		pan.setHorizontalGroup(horiGroup);
		pan.setVerticalGroup(vertGroup);
		
		JButton back=new JButton("Back to Search");
		back.addActionListener(this);
		
		JLabel nameLabel = new JLabel("Name :");
		JLabel nameVal = new JLabel(user.getName());
		
		JLabel emailLabel = new JLabel("Email :");
		JLabel emailVal = new JLabel(user.getEmail());
		
		
		JLabel phoneLabel = new JLabel("Phone :");
		JLabel phoneVal = new JLabel(user.getPhone());
		
		JLabel usernameLabel = new JLabel("Username :");
		JLabel userVal = new JLabel(user.getUsername());
		
		GroupLayout inputs=new GroupLayout(labelpanel);
		labelpanel.setLayout(inputs);
		inputs.setAutoCreateGaps(true);
		inputs.setAutoCreateContainerGaps(true);
		//First Sequential Group
		GroupLayout.SequentialGroup hGroup = inputs.createSequentialGroup();
		GroupLayout.ParallelGroup labels = inputs.createParallelGroup(); //One for Labels
		GroupLayout.ParallelGroup fields = inputs.createParallelGroup(); //Other for values/fields
		labels.addGap(75);
		fields.addGap(75);
		labels.addComponent(nameLabel);
		labels.addComponent(emailLabel);
		labels.addComponent(phoneLabel);
		labels.addComponent(usernameLabel);
		fields.addComponent(nameVal);
		fields.addComponent(emailVal);
		fields.addComponent(phoneVal);
		fields.addComponent(userVal);
		
		hGroup.addGroup(labels);
		hGroup.addGroup(fields);
		//Then horizontal Group
		inputs.setHorizontalGroup(hGroup);
		GroupLayout.SequentialGroup vGroup = inputs.createSequentialGroup();
		
		GroupLayout.ParallelGroup nameGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup emailGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup locationGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup phoneGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup usernameGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		nameGroup.addComponent(nameLabel);
		nameGroup.addComponent(nameVal);
		emailGroup.addComponent(emailLabel);
		emailGroup.addComponent(emailVal);
		phoneGroup.addComponent(phoneLabel);
		phoneGroup.addComponent(phoneVal);
		usernameGroup.addComponent(usernameLabel);
		usernameGroup.addComponent(userVal);
		vGroup.addGroup(nameGroup);
		vGroup.addGroup(emailGroup);
		vGroup.addGroup(locationGroup);
		vGroup.addGroup(phoneGroup);
		vGroup.addGroup(usernameGroup);
		inputs.setVerticalGroup(vGroup);
		
		GroupLayout bookslayout =new GroupLayout(bookspanel);
		bookspanel.setLayout(bookslayout);
		bookslayout.setAutoCreateGaps(true);
		bookslayout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGrouptwo = bookslayout.createSequentialGroup();
		GroupLayout.ParallelGroup labelstwo = bookslayout.createParallelGroup(); //One for Labels
		GroupLayout.ParallelGroup valuestwo = bookslayout.createParallelGroup(); //One for Labels
		GroupLayout.ParallelGroup updatebutton = bookslayout.createParallelGroup();
		valuestwo.addGap(100);
		updatebutton.addGap(100);
		GroupLayout.SequentialGroup vGrouptwo = bookslayout.createSequentialGroup();
		GroupLayout.ParallelGroup booksGroupTitle[]= new GroupLayout.ParallelGroup[books.size()];
		JLabel BookNameLabel[]=new JLabel[books.size()];
		JButton UpdateButton[]=new JButton[books.size()];
		
		for(int i = 0; i<books.size(); i++){
			DatabaseBook prototype = DatabaseBookTable.get().getBook(books.get(i).ISBN);
			
			int k = i+1;
			booksGroupTitle[i] = bookslayout.createParallelGroup();
			JLabel TitleLabel= new JLabel("Book "+k+" :");
			BookNameLabel[i] = new JLabel(prototype.title);
			labelstwo.addComponent(TitleLabel);
			valuestwo.addComponent(BookNameLabel[i]);
			booksGroupTitle[i].addComponent(TitleLabel);
			booksGroupTitle[i].addComponent(BookNameLabel[i]);
			UpdateButton[i] = new JButton("update");
			UpdateButton[i].setActionCommand("update"+i);
			UpdateButton[i].addActionListener(this);
			updatebutton.addComponent(UpdateButton[i]);
			booksGroupTitle[i].addComponent(UpdateButton[i]);
			vGrouptwo.addGroup(booksGroupTitle[i]);
			}
		GroupLayout.ParallelGroup backGroup=bookslayout.createParallelGroup(GroupLayout.Alignment.CENTER);
		backGroup.addComponent(back);
		backGroup.addGap(100);
		vGrouptwo.addGroup(backGroup);
		valuestwo.addComponent(back);
		hGrouptwo.addGroup(labelstwo);
		hGrouptwo.addGroup(valuestwo);
		hGrouptwo.addGroup(updatebutton);
		bookslayout.setHorizontalGroup(hGrouptwo);
		bookslayout.setVerticalGroup(vGrouptwo);
		
		panel.add(labelpanel);
		panel.add(bookspanel);
		frame.add(panel);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		JButton source = (JButton) e.getSource();
		String command = source.getActionCommand();
//		if(source.getText().equals("Update Owner")){
//		  JOptionPane.showMessageDialog(source, "Viljum við leyfa þetta?");  
//		  }
		if(books!=null){
			  for(int i=0; i<books.size(); i++){
				  if(("update"+i).equals(command))
				  {	
					  panel.setVisible(false);
					  JPanel updating= new JPanel();
					  Update updateForm = new Update(books.get(i));
					  updating = updateForm.initUI();
					  frame.add(updating);
					  frame.setVisible(true);
					  break;
				  }
				}  
			 }
		if(source.getText().equals("Back to Search")){
			frame.remove(panel);
			View.search.searchDialog();

		}
	}
	


	
}

