import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.client.*;

public class Update extends JPanel implements ActionListener{
	private UserBook registerbook; //The book to update
	private JPanel panel;
	private JFrame frame;
	private Owner user;
	private JTextField pricefield;
	private JComboBox conditionField;
	private JCheckBox Sold;
	
	public Update(UserBook x, Owner user, JFrame frame){
		this.registerbook=x;
		this.user=user;
		this.frame = frame;
		initUI();
	}
	public void initUI(){
		DatabaseBook prototype=DatabaseBookTable.get().getBook(registerbook.ISBN);
		View.frame.setTitle("Update"); // Set a new title to the frame
		//Create elements
		JPanel labelpane=new JPanel();
		panel=new JPanel();
		BoxLayout paneling=new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(paneling);

		JLabel SoldLabel= new JLabel("Sold:");
		Sold = new JCheckBox();
		JLabel NameLabel= new JLabel("Name: ");
		JLabel NameVal=new JLabel(prototype.title);
		JLabel AuthorLabel=new JLabel("Author: ");
		JLabel AuthorVal = new JLabel(prototype.authors);
		JLabel IsbnLabel=new JLabel("ISBN: ");
		JLabel IsbnVal=new JLabel(Integer.toString(registerbook.ISBN));
		JLabel price=new JLabel("Price:");
		JLabel conditionLabel = new JLabel("Condition:");
		pricefield=new JTextField(Integer.toString(registerbook.getPrice()));
		String[] conditions={"like new","very good","good","fair","bad","very bad"};
		conditionField=new JComboBox(conditions);
		if(registerbook.getCondition().equals("like  new")){
			conditionField.setSelectedIndex(0);
		}
		else if(registerbook.getCondition().equals("very good")){
			conditionField.setSelectedIndex(1);
		}
		else if(registerbook.getCondition().equals("good")){
			conditionField.setSelectedIndex(2);
		}
		else if(registerbook.getCondition().equals("fair")){
			conditionField.setSelectedIndex(3);
		}
		else if(registerbook.getCondition().equals("bad")){
			conditionField.setSelectedIndex(4);
		}
		else if(registerbook.getCondition().equals("very bad")){
			conditionField.setSelectedIndex(5);
		}
		JButton update=new JButton("Update");
		
		//Create Groups for Labels and values/inputfields
		GroupLayout inputs=new GroupLayout(labelpane);
		labelpane.setLayout(inputs);
		inputs.setAutoCreateGaps(true);
		//First Sequential Group
		GroupLayout.SequentialGroup hGroup = inputs.createSequentialGroup();
		GroupLayout.ParallelGroup labels=inputs.createParallelGroup(); //One for Labels
		GroupLayout.ParallelGroup fields=inputs.createParallelGroup(); //Other for values/fields
		labels.addComponent(SoldLabel);
		labels.addComponent(IsbnLabel);
		labels.addComponent(NameLabel);
		labels.addComponent(AuthorLabel);
		labels.addComponent(price);
		labels.addComponent(conditionLabel);
		fields.addComponent(Sold);
		fields.addComponent(IsbnVal);
		fields.addComponent(NameVal);
		fields.addComponent(AuthorVal);
		fields.addComponent(pricefield,GroupLayout.DEFAULT_SIZE,100,GroupLayout.PREFERRED_SIZE);
		fields.addComponent(conditionField,GroupLayout.DEFAULT_SIZE,100,GroupLayout.PREFERRED_SIZE);
		fields.addComponent(update);
		
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
		GroupLayout.ParallelGroup soldGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
		soldGroup.addComponent(SoldLabel);
		soldGroup.addComponent(Sold);
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
		buttonGroup.addComponent(update);
		vGroup.addGroup(soldGroup);
		vGroup.addGroup(IsbnGroup);
		vGroup.addGroup(NameGroup);
		vGroup.addGroup(AuthorGroup);
		vGroup.addGroup(priceGroup);
		vGroup.addGroup(conditionGroup);
		vGroup.addGroup(buttonGroup);
		inputs.setVerticalGroup(vGroup);
		update.setActionCommand("update");
		update.addActionListener(this);
		panel.add(labelpane);
		frame.add(panel);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("update")){
			if(Sold.isSelected()){
				UserBookTable.get().deleteBook(registerbook.instanceID);
			}
			String inputprice=pricefield.getText();
			String inputcondition=conditionField.getSelectedItem().toString();
			int price;
			if(inputprice.isEmpty()){
				price=registerbook.getPrice();
			}
			else{
				price=Integer.parseInt(inputprice);
			}
			registerbook.editCondition(inputcondition);
			registerbook.editPrice(price);
			panel.setVisible(false);
			MyPages mypage= new MyPages(user,frame);
			mypage.mypagesForm();
			JOptionPane.showMessageDialog(View.frame, "Thank you! \n We have successfully updated your book");
				/*JOptionPane.showMessageDialog(View.frame,
						    "Something went wrong! \n Please try again",
						    "Registration Error",
						    JOptionPane.ERROR_MESSAGE);*/
			}
				
	}

}
