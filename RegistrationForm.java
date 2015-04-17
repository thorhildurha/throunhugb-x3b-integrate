import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.GroupLayout;
import database.client.*;
//This class draws a UI interface for a registration form

public class RegistrationForm extends JPanel implements ActionListener{
	private DatabaseBook registerbook; //The book to register
	private Owner user;
	private JFrame frame; //The frame for the program 
	private JPanel center; //The panel for the registration form
	private JTextField pricefield; 
	private JComboBox conditionField;
	private NumberFormat priceFormat;
	
	
	public RegistrationForm(DatabaseBook x, JFrame frame, Owner user){
		this.registerbook=x;
		this.frame=frame;
		this.priceFormat = NumberFormat.getNumberInstance();
		this.user=user;
	}
	public void initUI(){
		frame.setTitle("Registration Form"); // Set a new title to the frame
		//Create elements
		JPanel labelpane=new JPanel();
		center=new JPanel();
		BoxLayout centering=new BoxLayout(center,BoxLayout.Y_AXIS);
		center.setLayout(centering);
		
		JLabel NameLabel= new JLabel("Name: ");
		JLabel NameVal=new JLabel(registerbook.title);
		JLabel AuthorLabel=new JLabel("Author: ");
		JLabel AuthorVal = new JLabel(registerbook.authors);
		JLabel IsbnLabel=new JLabel("ISBN: ");
		JLabel IsbnVal=new JLabel(Integer.toString(registerbook.ISBN));
		JLabel price=new JLabel("Price:");
		JLabel conditionLabel = new JLabel("Condition:");	
		pricefield = new JTextField();
		String[] conditions={"like new","very good","good","fair","bad","very bad"};
		conditionField=new JComboBox(conditions);
		JButton register=new JButton("Register");
		register.setActionCommand("register");
		register.addActionListener(this);
		
		//Create Groups for Labels and values/inputfields
		GroupLayout inputs=new GroupLayout(labelpane);
		labelpane.setLayout(inputs);
		inputs.setAutoCreateGaps(true);
		//First Sequential Group
		GroupLayout.SequentialGroup hGroup = inputs.createSequentialGroup();
		GroupLayout.ParallelGroup labels=inputs.createParallelGroup(); //One for Labels
		GroupLayout.ParallelGroup fields=inputs.createParallelGroup(); //Other for values/fields
		GroupLayout.ParallelGroup buttongroup = inputs.createParallelGroup();
		labels.addComponent(IsbnLabel);
		labels.addComponent(NameLabel);
		labels.addComponent(AuthorLabel);
		labels.addComponent(price);
		labels.addComponent(conditionLabel);
		fields.addComponent(IsbnVal);
		fields.addComponent(NameVal);
		fields.addComponent(AuthorVal);
		fields.addComponent(pricefield,GroupLayout.DEFAULT_SIZE,100,GroupLayout.PREFERRED_SIZE);
		fields.addComponent(conditionField,GroupLayout.DEFAULT_SIZE,100,GroupLayout.PREFERRED_SIZE);
		fields.addComponent(register);
		hGroup.addGroup(labels);
		hGroup.addGroup(fields);
		//Then horizontal Group
		inputs.setHorizontalGroup(hGroup);
		GroupLayout.SequentialGroup vGroup = inputs.createSequentialGroup();
		
		GroupLayout.ParallelGroup IsbnGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup NameGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup AuthorGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup priceGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup conditionGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		GroupLayout.ParallelGroup buttonGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		IsbnGroup.addComponent(IsbnLabel);
		IsbnGroup.addComponent(IsbnVal);
		NameGroup.addComponent(NameLabel);
		NameGroup.addComponent(NameVal);
		AuthorGroup.addComponent(AuthorLabel);
		AuthorGroup.addComponent(AuthorVal);
		priceGroup.addComponent(price);
		priceGroup.addComponent(pricefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);		
		conditionGroup.addComponent(conditionLabel);
		conditionGroup.addComponent(conditionField,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
		buttonGroup.addComponent(register);
		vGroup.addGroup(IsbnGroup);
		vGroup.addGroup(NameGroup);
		vGroup.addGroup(AuthorGroup);
		vGroup.addGroup(priceGroup);
		
		vGroup.addGroup(conditionGroup);
		vGroup.addGroup(buttonGroup);
		inputs.setVerticalGroup(vGroup);
		center.add(labelpane);
		frame.add(center);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String inputprice=pricefield.getText();
		String inputcondition=conditionField.getSelectedItem().toString();
		UserBookTable table = UserBookTable.get();
		table.createBook(user.getid(),registerbook.ISBN,Integer.parseInt(inputprice),inputcondition);
		frame.remove(center);
		View.search.searchDialog();
		JOptionPane.showMessageDialog(frame, "Thank you! \n We have successfully registered your book");
		}
			/*JOptionPane.showMessageDialog(frame,
				    "Something went wrong! \n Please try again",
				    "Registration Error",
				    JOptionPane.ERROR_MESSAGE);*/
		
}
