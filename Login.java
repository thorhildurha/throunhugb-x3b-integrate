import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.client.*;

public class Login extends JDialog implements ActionListener{
	private Owner newuser;
	private JFrame frame;
	private JDialog dialog;
	private JPanel loginpanel;
	private JPanel newuserpanel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JTextField usernameText;
	private JPasswordField newpasswordText;
	private JTextField nameText;
	private JTextField emailText;
	private JTextField phoneText;
	private JTextField locationText;
	
	public Login(Owner owner,JFrame frame){
		this.newuser=owner;
		this.dialog = new JDialog();	
		this.frame=frame;
		this.loginpanel=new JPanel();
		this.newuserpanel=new JPanel();
		dialog.add(loginpanel);
		loginDialog();
	}
	
//	Use: new Login().loginDialog();
//	Before: nothing
//	After: loginDialog has been created
	public void loginDialog() {
		dialog.setSize(350,200);
	    GroupLayout inputs = new GroupLayout(loginpanel);
	    inputs.setAutoCreateGaps(true);
	    inputs.setAutoCreateContainerGaps(true);
	    GroupLayout.SequentialGroup hGroup = inputs.createSequentialGroup();
	    loginpanel.setLayout(inputs);
	    GroupLayout.ParallelGroup labels = inputs.createParallelGroup(); //One for Labels
	    GroupLayout.ParallelGroup fields = inputs.createParallelGroup(); //Other for values/fields
	    
	    JLabel userLabel = new JLabel("User");
	    labels.addComponent(userLabel);
	    
	    userText = new JTextField(20);
	    fields.addComponent(userText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JLabel passwordLabel = new JLabel("Password");
	    labels.addComponent(passwordLabel);
	    
	    passwordText = new JPasswordField(20);
	    fields.addComponent(passwordText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JButton loginButton = new JButton("login");
	    loginButton.setBounds(225, 80, 80, 25);
	    loginpanel.add(loginButton);
	    JButton signupButton = new JButton("sign up");
	    signupButton.setBounds(10, 80, 80, 25);
	    loginpanel.add(signupButton);
	    loginButton.setActionCommand("login");
	    loginButton.addActionListener(this);
	    signupButton.setActionCommand("newuser");
	    signupButton.addActionListener(this);
	    
	    hGroup.addGroup(labels);
	    hGroup.addGroup(fields);
	    
	    inputs.setHorizontalGroup(hGroup);
	    
	    GroupLayout.SequentialGroup vGroup = inputs.createSequentialGroup();  
	    GroupLayout.ParallelGroup UserGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    GroupLayout.ParallelGroup PasswordGroup = inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    
	    UserGroup.addComponent(userLabel);
	    UserGroup.addComponent(userText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    PasswordGroup.addComponent(passwordLabel);
	    PasswordGroup.addComponent(passwordText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE); 
	    
	    vGroup.addGroup(UserGroup);
	    vGroup.addGroup(PasswordGroup);

	    inputs.setVerticalGroup(vGroup);
	    
	    loginpanel.setVisible(true);
	    dialog.setVisible(true);
				
	}
	
//	Use: new Login().NewUserForm();
//	Before: nothing
//	After: NewUserForm has been created
	public void NewUserForm() {
		dialog.setSize(350,450);
		newuserpanel=new JPanel();
		GroupLayout newinputs = new GroupLayout(newuserpanel);
	    newinputs.setAutoCreateGaps(true);
	    newinputs.setAutoCreateContainerGaps(true);
	    GroupLayout.SequentialGroup newhGroup = newinputs.createSequentialGroup();
	    newuserpanel.setLayout(newinputs);
	    GroupLayout.ParallelGroup newlabels = newinputs.createParallelGroup(); //One for Labels
	    GroupLayout.ParallelGroup newfields = newinputs.createParallelGroup(); //Other for values/fields
	    
	    JLabel nameLabel = new JLabel("Name *");
	    newlabels.addComponent(nameLabel);
	    
	    nameText = new JTextField(20);
	    newfields.addComponent(nameText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JLabel emailLabel = new JLabel("Email *");
	    newlabels.addComponent(emailLabel);
	    
	    emailText = new JTextField(20);
	    newfields.addComponent(emailText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JLabel locationLabel = new JLabel("Location");
	    newlabels.addComponent(locationLabel);
	    
	    locationText = new JTextField(20);
	    newfields.addComponent(locationText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JLabel phoneLabel = new JLabel("Phone");
	    newlabels.addComponent(phoneLabel);
	    
	    phoneText = new JTextField(20);
	    newfields.addComponent(phoneText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JLabel usernameLabel = new JLabel("Username *");
	    newlabels.addComponent(usernameLabel);
	    
	    usernameText = new JTextField(20);
	    newfields.addComponent(usernameText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    JLabel passwordLabel = new JLabel("Password *");
	    newlabels.addComponent(passwordLabel);
	    
	    newpasswordText = new JPasswordField(20);
	    newfields.addComponent(newpasswordText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
		JButton submitButton = new JButton("submit");
		submitButton.setBounds(225, 200, 80, 25);
		newuserpanel.add(submitButton);

		JButton cancelButton = new JButton("cancel");
		cancelButton.setBounds(10, 200, 80, 25);
		newuserpanel.add(cancelButton);
		
		submitButton.setActionCommand("submituser");
	    submitButton.addActionListener(this);
	    cancelButton.setActionCommand("cancel");
	    cancelButton.addActionListener(this);
	    
	    newhGroup.addGroup(newlabels);
	    newhGroup.addGroup(newfields);
	    
	    newinputs.setHorizontalGroup(newhGroup);
	    
	    GroupLayout.SequentialGroup newvGroup = newinputs.createSequentialGroup();  
	    GroupLayout.ParallelGroup NameGroup = newinputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    GroupLayout.ParallelGroup EmailGroup = newinputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    GroupLayout.ParallelGroup LocationGroup = newinputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    GroupLayout.ParallelGroup PhoneGroup = newinputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    GroupLayout.ParallelGroup UsernameGroup = newinputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    GroupLayout.ParallelGroup PasswordGroup = newinputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	    
	    NameGroup.addComponent(nameLabel);
	    NameGroup.addComponent(nameText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    EmailGroup.addComponent(emailLabel);
	    EmailGroup.addComponent(emailText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    LocationGroup.addComponent(locationLabel);
	    LocationGroup.addComponent(locationText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    PhoneGroup.addComponent(phoneLabel);
	    PhoneGroup.addComponent(phoneText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    UsernameGroup.addComponent(usernameLabel);
	    UsernameGroup.addComponent(usernameText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    PasswordGroup.addComponent(passwordLabel);
	    PasswordGroup.addComponent(newpasswordText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	    
	    newvGroup.addGroup(NameGroup);
	    newvGroup.addGroup(EmailGroup);
	    newvGroup.addGroup(LocationGroup);
	    newvGroup.addGroup(PhoneGroup);
	    newvGroup.addGroup(UsernameGroup);
	    newvGroup.addGroup(PasswordGroup);

	    newinputs.setVerticalGroup(newvGroup);
	   		
		
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);
		
		dialog.add(newuserpanel);
		dialog.setVisible(true);
		
	}
	
	
public void actionPerformed(ActionEvent e){
		JButton source = (JButton) e.getSource();
		String command = source.getActionCommand();
		if("login".equals(command)){
			if (authenticate(userText.getText(),passwordText.getText())) {
				JOptionPane.showMessageDialog(source, "Welcome "+ newuser.getName() + " you have been logged in");	
				frame.remove(View.search.scrollpane);
				View.search.searchDialog();
				dialog.dispose();
			} 
			else {
				JOptionPane.showMessageDialog(dialog, "Invalid username or password");	
				}
		}
		else if("newuser".equals(command)){
			loginpanel.setVisible(false);
			NewUserForm();
		}
		
		//Try to create a new user
		else if("submituser".equals(command)){
			Boolean created=createaccount();
			
			//If new account created, show login panel again
			if(created){
				newuserpanel.setVisible(false);
				dialog.remove(newuserpanel);
				loginpanel.setVisible(true);
			}
		}
		
		//display loginpanel again
		else if("cancel".equals(command)){
			newuserpanel.setVisible(false);
			dialog.remove(newuserpanel);
			loginpanel.setVisible(true);
		}	
}
public boolean createaccount(){
	String name = nameText.getText().trim();
	String email = emailText.getText().trim();
	String username=usernameText.getText().trim();
	String phonenumber = phoneText.getText().trim();
	String pw = newpasswordText.getText();
	int phone=0;
	//If user does not fill in phonenumber, phone must be 0
	if(!phonenumber.isEmpty()){
		phone=Integer.parseInt(phonenumber);
	}

	if (name.isEmpty()||email.isEmpty() ||username.isEmpty() || pw.isEmpty()) {
		JOptionPane.showMessageDialog(dialog,"You have to fill out the required fields (*)");
		return false;
	}
	else {
		if(UserAccountTable.get().existsUN(username)){
			JOptionPane.showMessageDialog(dialog, "The username " + username  + " aldready exists.\n Try another username!");
			return false;
		}
		else{
			UserAccountTable.get().createAccount(username,pw,phone,name,email);
			JOptionPane.showMessageDialog(dialog, "Thank you "+ name + " you have been registered!");
			return true;
		}
		
	}
}
//	Use: a.authenticate(x,y);
//	Before: a is a class, x is a string, y is a char[]
//	After: Check if x is the correct username and y is the correct password
	private boolean authenticate(String username, String password) {
//		TODO: Tjékkum hvort username og lykilorð passi við eitthvað í gagnagrunninum, 
//			  þá true annars false
		
        // hardcoded username and password
		UserAccountTable table = UserAccountTable.get();
		Boolean isusername=table.existsUN(username);
		if(isusername){
			UserAccount loginuser= table.getAccount(username);
			if(loginuser.isPW(password)){
				newuser.setloggedin(true);
				newuser.setUsername(username);
				newuser.setid(loginuser.accountID);
				newuser.setName(loginuser.getName());
				newuser.setEmail(loginuser.getEmail());
				if(loginuser.getPhone()==0){
					newuser.setPhone("");
				}
				else{
					newuser.setPhone(Integer.toString(loginuser.getPhone()));
				}
				return true;
			}
			return false;
		}
		else{
			return false;
		}
		

    }

}
