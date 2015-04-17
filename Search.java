import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

import database.client.*;
public class Search extends JFrame implements ActionListener
{

  private Owner user;
  private JFrame frame;
  private JPanel panel;
  public static JScrollPane scrollpane;
  private ArrayList<UserBook> usedbooks;  //search for books
  private ArrayList<DatabaseBook> newbooks;  //register books
  private JPanel results;
  //Fill in fields
  private JTextField TitleText; 
  private JTextField AuthorText;
  private JTextField isbnText;
  private JTextField categoryText;
  private JTextField subcategoryText;
  private JCheckBox wanttoregister;
  
  public Search(Owner loggedin, JFrame frame){
    this.user=loggedin;
    this.results=new JPanel();
    this.frame=frame;
    this.usedbooks=new ArrayList<UserBook>();
    this.newbooks=new ArrayList<DatabaseBook>();
  }
  
  //Before: nothing
  //After: creates the JPanel for the Search Form and displays it
  public void searchDialog()
  {
	frame.setTitle("Search");
	panel=null; //First we clear the panel just in case
	panel=new JPanel(); 
    JPanel searchpanel = new JPanel(); //The panel that shows the search conditions
    
    JLabel Iwanttoregister=new JLabel("I want to register a book");
    wanttoregister=new JCheckBox();
	//We create the group Layout for the panels and buttons
	GroupLayout panels= new GroupLayout(panel);
	panels.setAutoCreateGaps(true);
	panels.setAutoCreateContainerGaps(true);
	panel.setLayout(panels);
	GroupLayout.SequentialGroup horiGroup=panels.createSequentialGroup();
	GroupLayout.ParallelGroup pan = panels.createParallelGroup(GroupLayout.Alignment.LEADING);
    GroupLayout.ParallelGroup buttonpan=panels.createParallelGroup();
    GroupLayout.ParallelGroup logoutpan=panels.createParallelGroup();

  //If a user is loggedin then show mypages button and logout button
	if(user.isloggedin()){
    	JButton mypages = new JButton("My Pages");
    	mypages.setActionCommand("mypages");
    	mypages.addActionListener(this);
    	pan.addComponent(mypages);
    	buttonpan.addComponent(mypages);
    	JButton logout = new JButton("Logout");
    	logout.setActionCommand("logout");
    	logout.addActionListener(this);
    	pan.addComponent(logout);
    	logoutpan.addComponent(logout);
    }
	//if he is not loggedin show login button
    else{
    	JButton login = new JButton("Login");
    	login.setActionCommand("login");
    	login.addActionListener(this);
    	pan.addComponent(login);
    	buttonpan.addComponent(login);
    }
	pan.addComponent(searchpanel);
	pan.addComponent(results);
	horiGroup.addGroup(pan);
	panels.setHorizontalGroup(horiGroup);
	GroupLayout.SequentialGroup vertGroup = panels.createSequentialGroup();
	GroupLayout.ParallelGroup searchpan=panels.createParallelGroup();
    GroupLayout.ParallelGroup resultpan=panels.createParallelGroup();
    searchpan.addComponent(searchpanel);
    resultpan.addComponent(results);
    vertGroup.addGroup(buttonpan);
    vertGroup.addGroup(logoutpan);
    vertGroup.addGroup(searchpan);
    vertGroup.addGroup(resultpan);
    panels.setVerticalGroup(vertGroup);
    
    //Next we create the GroupLayout for the search conditions
    GroupLayout inputs=new GroupLayout(searchpanel);
    inputs.setAutoCreateGaps(true);
    inputs.setAutoCreateContainerGaps(true);
    GroupLayout.SequentialGroup hGroup = inputs.createSequentialGroup();
    searchpanel.setLayout(inputs);
    GroupLayout.ParallelGroup labels=inputs.createParallelGroup(GroupLayout.Alignment.LEADING); //One for Labels
    GroupLayout.ParallelGroup fields=inputs.createParallelGroup(GroupLayout.Alignment.LEADING); //Other for values/field
   
    JButton searchButton = new JButton("Search");
    JLabel titleLabel = new JLabel ("Book title");
    labels.addComponent(titleLabel);
    
    JLabel authorLabel = new JLabel ("Author");
    labels.addComponent(authorLabel);
    
    JLabel isbnLabel = new JLabel ("ISBN");
    labels.addComponent(isbnLabel);
    
    JLabel categoryLabel= new JLabel ("Faculty");
    labels.addComponent(categoryLabel);
    
    JLabel subcategoryLabel =new JLabel("Programme");
    labels.addComponent(subcategoryLabel);
        
    TitleText = new JTextField(20);
    fields.addComponent(TitleText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    
    AuthorText = new JTextField(20);
    fields.addComponent(AuthorText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    
    isbnText = new JTextField(20);
    fields.addComponent(isbnText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    
    categoryText = new JTextField(20);
    fields.addComponent(categoryText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    
    subcategoryText=new JTextField(20);
    fields.addComponent(subcategoryText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);

    labels.addComponent(Iwanttoregister);
    fields.addComponent(wanttoregister);
    fields.addComponent(searchButton);
    
    hGroup.addGroup(labels);
    hGroup.addGroup(fields);
    
    inputs.setHorizontalGroup(hGroup);
    
    GroupLayout.SequentialGroup vGroup = inputs.createSequentialGroup();
    
    GroupLayout.ParallelGroup IsbnGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
    GroupLayout.ParallelGroup TitleGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
    GroupLayout.ParallelGroup AuthorGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
    GroupLayout.ParallelGroup CategoryGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
    GroupLayout.ParallelGroup SubCategoryGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
	GroupLayout.ParallelGroup registerGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
    GroupLayout.ParallelGroup buttonGroup=inputs.createParallelGroup(GroupLayout.Alignment.CENTER);
    IsbnGroup.addComponent(isbnLabel);
    IsbnGroup.addComponent(isbnText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    TitleGroup.addComponent(titleLabel);
    TitleGroup.addComponent(TitleText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    AuthorGroup.addComponent(authorLabel);
    AuthorGroup.addComponent(AuthorText,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
    CategoryGroup.addComponent(categoryLabel);
    CategoryGroup.addComponent(categoryText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
    SubCategoryGroup.addComponent(subcategoryLabel);
    SubCategoryGroup.addComponent(subcategoryText,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE);
	buttonGroup.addComponent(searchButton);
	registerGroup.addComponent(Iwanttoregister);
	registerGroup.addComponent(wanttoregister);
	registerGroup.addGap(50);
	buttonGroup.addGap(50);
    vGroup.addGroup(IsbnGroup);
    vGroup.addGroup(TitleGroup);
    vGroup.addGroup(AuthorGroup);
    vGroup.addGroup(CategoryGroup);
    vGroup.addGroup(SubCategoryGroup);
    vGroup.addGroup(registerGroup);
    vGroup.addGroup(buttonGroup);
    inputs.setVerticalGroup(vGroup);
    
    searchButton.setActionCommand("search");
    searchButton.addActionListener(this);
    
    scrollpane = new JScrollPane(panel);
    frame.add(scrollpane);
    frame.setVisible(true);
  }

  //Displays the books in newbooks
  public void shownewbooks(){

	  results.removeAll(); //remove previous results
	  GroupLayout result =new GroupLayout(results);
	  results.setLayout(result);
	  result.setAutoCreateGaps(true);
	  result.setAutoCreateContainerGaps(true);
	  
	  GroupLayout.SequentialGroup hGroup = result.createSequentialGroup();
	  GroupLayout.ParallelGroup labels=result.createParallelGroup(); 
	  GroupLayout.ParallelGroup values=result.createParallelGroup(); 
	  GroupLayout.ParallelGroup registerbutton = result.createParallelGroup();
	  
	  GroupLayout.SequentialGroup vGroup = result.createSequentialGroup();
	  GroupLayout.ParallelGroup resultGroupTitle[]= new GroupLayout.ParallelGroup[newbooks.size()];
	  GroupLayout.ParallelGroup resultGroupAuthor[]=new GroupLayout.ParallelGroup[newbooks.size()];
	  GroupLayout.ParallelGroup resultGroupPrice[]=new GroupLayout.ParallelGroup[newbooks.size()];
	  JLabel BookNameLabel[]=new JLabel[newbooks.size()];
	  JLabel BookAuthorLabel[]=new JLabel[newbooks.size()];
	  JLabel BookPriceLabel[]=new JLabel[newbooks.size()];
	  JButton RegisterButton[]=new JButton[newbooks.size()];

	  for(int i = 0; i<newbooks.size(); i++){
		  resultGroupTitle[i]=result.createParallelGroup();
		  resultGroupAuthor[i]=result.createParallelGroup();
		  resultGroupPrice[i]=result.createParallelGroup();
		  resultGroupPrice[i].addGap(50);

		  JLabel TitleLabel= new JLabel("Title:");
		  JLabel AuthorLabel = new JLabel("Author:");
		  JLabel PriceLabel= new JLabel("Price:");
		  BookNameLabel[i] = new JLabel(newbooks.get(i).title);
		  BookAuthorLabel[i] = new JLabel(newbooks.get(i).authors);
		  BookPriceLabel[i] = new JLabel(Integer.toString(newbooks.get(i).price)); //Price is private :/
		  labels.addComponent(TitleLabel);
		  labels.addComponent(AuthorLabel);
		  labels.addComponent(PriceLabel);
		  values.addComponent(BookNameLabel[i]);
		  values.addComponent(BookAuthorLabel[i]);
		  values.addComponent(BookPriceLabel[i]);
		  
		  resultGroupTitle[i].addComponent(TitleLabel);
		  resultGroupTitle[i].addComponent(BookNameLabel[i]);
		  resultGroupAuthor[i].addComponent(AuthorLabel);
		  resultGroupAuthor[i].addComponent(BookAuthorLabel[i]);
		  RegisterButton[i] = new JButton("register");
		  RegisterButton[i].setActionCommand("register"+i);
		  RegisterButton[i].addActionListener(this);
		  RegisterButton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

		  registerbutton.addComponent(RegisterButton[i]);
		  resultGroupAuthor[i].addComponent(RegisterButton[i]);
		  resultGroupPrice[i].addComponent(PriceLabel);
		  resultGroupPrice[i].addComponent(BookPriceLabel[i]);
		  vGroup.addGroup(resultGroupTitle[i]);
		  vGroup.addGroup(resultGroupAuthor[i]);
		  vGroup.addGroup(resultGroupPrice[i]);
	  }
	  hGroup.addGroup(labels);
	  hGroup.addGroup(values);
	  hGroup.addGroup(registerbutton);
	  result.setHorizontalGroup(hGroup);
	  result.setVerticalGroup(vGroup);
	  panel.revalidate(); //let the scroll pane know that changes have been made
	  frame.setVisible(true);
	  
  }
  
  //Use: showbooks();
  //Before: Nothing
  //After: The information in usedbooks have been displayed on the JPanel
  public void showbooks(){
	  results.removeAll(); //remove previous results
	  GroupLayout result =new GroupLayout(results);
	  results.setLayout(result);
	  result.setAutoCreateGaps(true);
	  result.setAutoCreateContainerGaps(true);
	  
	  GroupLayout.SequentialGroup hGroup = result.createSequentialGroup();
	  GroupLayout.ParallelGroup labels=result.createParallelGroup(); //One for Labels
	  GroupLayout.ParallelGroup values=result.createParallelGroup(); //One for Labels
	  
	  GroupLayout.SequentialGroup vGroup = result.createSequentialGroup();
	  GroupLayout.ParallelGroup resultGroupTitle[]= new GroupLayout.ParallelGroup[usedbooks.size()];
	  GroupLayout.ParallelGroup resultGroupAuthor[]=new GroupLayout.ParallelGroup[usedbooks.size()];
	  GroupLayout.ParallelGroup resultGroupPrice[]=new GroupLayout.ParallelGroup[usedbooks.size()];
	  GroupLayout.ParallelGroup resultGroupCondition[] = new GroupLayout.ParallelGroup[usedbooks.size()];
	  GroupLayout.ParallelGroup resultGroupseller[] = new GroupLayout.ParallelGroup[usedbooks.size()];
	  
	  JLabel BookNameLabel[]=new JLabel[usedbooks.size()];
	  JLabel BookAuthorLabel[]=new JLabel[usedbooks.size()];
	  JLabel BookPriceLabel[]=new JLabel[usedbooks.size()];
	  JLabel BookConditionLabel[] = new JLabel[usedbooks.size()];
	  JButton[] BookSellerLabel=new JButton[usedbooks.size()];
	  DatabaseBookTable table = DatabaseBookTable.get();
	  
	  //Display the book result
	  for(int i = 0; i<usedbooks.size(); i++){
		  UserBook usedbook= usedbooks.get(i);
		  int sellerid=usedbook.accountID;
		  UserAccount seller=UserAccountTable.get().getAccount(sellerid); //The account of the seller of book(i)
		  DatabaseBook prototype=table.getBook(usedbook.ISBN);
		  
		  resultGroupTitle[i]=result.createParallelGroup();
		  resultGroupAuthor[i]=result.createParallelGroup();
		  resultGroupPrice[i]=result.createParallelGroup();
		  resultGroupCondition[i]=result.createParallelGroup();
		  resultGroupseller[i]=result.createParallelGroup();
		  resultGroupseller[i].addGap(50);

		  
		  JLabel TitleLabel= new JLabel("Title:");
		  JLabel AuthorLabel = new JLabel("Author:");
		  JLabel PriceLabel= new JLabel("Price:");
		  JLabel ConditionLabel = new JLabel("Condition:");
		  
		  BookNameLabel[i] = new JLabel(prototype.title); //No Title or Author on used books
		  BookAuthorLabel[i] = new JLabel(prototype.authors); //No Author on used books
		  BookPriceLabel[i] = new JLabel(Integer.toString(usedbooks.get(i).getPrice())); 
		  BookSellerLabel[i]=new JButton("see more");
		  BookSellerLabel[i].setActionCommand("more"+i);
		  BookSellerLabel[i].setName(seller.username);
		  BookSellerLabel[i].addActionListener(this);
		  BookSellerLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
		  BookConditionLabel[i]=new JLabel(usedbooks.get(i).getCondition());
		  labels.addComponent(TitleLabel);
		  labels.addComponent(AuthorLabel);
		  labels.addComponent(PriceLabel);
		  labels.addComponent(ConditionLabel);
		  values.addComponent(BookNameLabel[i]);
		  values.addComponent(BookAuthorLabel[i]);
		  values.addComponent(BookPriceLabel[i]);
		  values.addComponent(BookConditionLabel[i]);
		  values.addComponent(BookSellerLabel[i]);
		  
		  resultGroupTitle[i].addComponent(TitleLabel);
		  resultGroupTitle[i].addComponent(BookNameLabel[i]);
		  resultGroupAuthor[i].addComponent(AuthorLabel);
		  resultGroupAuthor[i].addComponent(BookAuthorLabel[i]);
		  resultGroupPrice[i].addComponent(PriceLabel);
		  resultGroupPrice[i].addComponent(BookPriceLabel[i]);
		  resultGroupCondition[i].addComponent(ConditionLabel);
		  resultGroupCondition[i].addComponent(BookConditionLabel[i]);
		  resultGroupseller[i].addComponent(BookSellerLabel[i]);
		  vGroup.addGroup(resultGroupTitle[i]);
		  vGroup.addGroup(resultGroupAuthor[i]);
		  vGroup.addGroup(resultGroupPrice[i]);
		  vGroup.addGroup(resultGroupCondition[i]);
		  vGroup.addGroup(resultGroupseller[i]);
	  }
	  hGroup.addGroup(labels);
	  hGroup.addGroup(values);
	  result.setHorizontalGroup(hGroup);
	  result.setVerticalGroup(vGroup);
	  results.setVisible(true);
	  panel.revalidate(); //let the scroll pane know that changes have been made
	  frame.setVisible(true);
  }
  
  //Use: b=isloggedin();
  //Before: nothing
  //After: returns true if there is someone logged in, false otherwise
  public Boolean isloggedin(){
    if(user.isloggedin()){
      return true;
    }
    else{
      return false;
    }
  }

  //
  //Before: 
  //After: 
  public void search(){
	  usedbooks.clear();
	  //The input fields
	  String Title = TitleText.getText();
	  String Author=AuthorText.getText();
	  String isbnString =isbnText.getText();
	  String category=categoryText.getText();
	  String subcategory=subcategoryText.getText();
	  
	  //The usedbooks table
	  UserBookTable table = UserBookTable.get();
	  
	  if(Title.isEmpty()&&Author.isEmpty()&&isbnString.isEmpty()&&category.isEmpty()&&subcategory.isEmpty()){
		  JOptionPane.showMessageDialog(frame,
				    "Please type in search conditions!",
				    "Missing search conditions",
				    JOptionPane.INFORMATION_MESSAGE);
	  }
	  else{//If search by isbn then it is unique
		  if(!isbnString.isEmpty()){
			  int isbn = Integer.parseInt(isbnString);
			  usedbooks=table.getBooks(isbn);
		  } 
		  else{
			 if(Title.isEmpty()){
				 Title=null;
			 }
			 if(Author.isEmpty()){
				 Author=null;
			 }
			 if(category.isEmpty()){
				 category=null;
			 }
			 if(subcategory.isEmpty()){
				 subcategory=null;
			 }
			 usedbooks= table.searchEverything(Title,Author,category,subcategory);
		  }
		  if(usedbooks.isEmpty()){
			  results.setVisible(false);
			  JOptionPane.showMessageDialog(frame,
					    "No search results!",
					    "Results",
					    JOptionPane.INFORMATION_MESSAGE);
		  }
		  else{
			  Collections.sort(usedbooks, new Comparator<UserBook>() {
				  @Override
				  public int compare(UserBook book1, UserBook book2)
				  {
					  if(book1.getPrice()<book2.getPrice()){
						  return -1;
					  }
					  else{
						  return 1;
					  }
				  }
			  });
			  showbooks();
		  }
	  }
	  
  }
  public void register(){
	  newbooks.clear();
	  String Title = TitleText.getText();
	  String Author=AuthorText.getText();
	  String isbnString =isbnText.getText();
	  String category=categoryText.getText();
	  String subcategory=subcategoryText.getText();
	  
	  DatabaseBookTable table = DatabaseBookTable.get();
	  DatabaseBook some = null;
	  if(isbnString.isEmpty()&&Author.isEmpty()&&Title.isEmpty()&&category.isEmpty()&&subcategory.isEmpty()){
		  JOptionPane.showMessageDialog(frame,
				    "Please type in search conditions!",
				    "Missing search conditions",
				    JOptionPane.INFORMATION_MESSAGE);
	  }
	  else if(!isbnString.isEmpty()){
		  int isbn=Integer.parseInt(isbnString);
		  newbooks.add((table.getBook(isbn)));
	  }
	  else{
		  if(Title.isEmpty()){
			  Title=null;
		  }
		  if(Author.isEmpty()){
			  Author=null;
		  }
		  if(category.isEmpty()){
			  category=null;
		  }
		  if(subcategory.isEmpty()){
			  subcategory=null;
		  }
		  newbooks=table.searchEverything(Title, Author, category, subcategory);
	  }

	  if(newbooks.isEmpty()){
		  JOptionPane.showMessageDialog(frame,
				    "There were no results!",
				    "No results",
				    JOptionPane.INFORMATION_MESSAGE);
	  }
	  else{
		  Collections.sort(newbooks, new Comparator<DatabaseBook>() {
			  @Override
			  public int compare(DatabaseBook book1, DatabaseBook book2)
			  {
				  if(book1.price<=book2.price){
					  return -1;
				  }
				  else{
					  return 1;
				  }
			  }
		  });
		  
		  shownewbooks();
		  
	  }
  }
  public void actionPerformed(ActionEvent e){
	  JButton source = (JButton) e.getSource();
	  String command=source.getActionCommand();
	  if("search".equals(command)){
		  if(wanttoregister.isSelected()){
			  register();
		  }
		  else{
			  search();  
		  }
	  }
	  if("login".equals(command)){
		  Login loginform = new Login(user,frame);
	  }
	  if("mypages".equals(command)){
		  frame.remove(scrollpane);
		  panel=null;
		  MyPages mypage = new MyPages(user,frame);  
		  mypage.mypagesForm();
	  }
	  if("logout".equals(command)){
		  user.setloggedin(false);
		  frame.remove(scrollpane);
		  searchDialog();		  
	  }
	  if(!usedbooks.isEmpty()){
		  UserAccountTable table = UserAccountTable.get();
		  for(int i=0; i<usedbooks.size(); i++){
			  if(("more"+i).equals(command)){
				  UserAccount ownerof=table.getAccount(source.getName());
				  String phone=null;
				  UserBook book=usedbooks.get(i);
				  DatabaseBook prototype = DatabaseBookTable.get().getBook(book.ISBN);
				  if(ownerof.getPhone()==0){
					  phone="Not registered";
				  }
				  else{
					  phone=Integer.toString(ownerof.getPhone());
				  }
				  JOptionPane.showMessageDialog(frame," Faculty: " + prototype.category + "\n Programme: " + prototype.subcategory+"\n Owner: " + ownerof.getName()+ "\n Email: " + ownerof.getEmail()+"\n Phone: " + phone,"More information",JOptionPane.INFORMATION_MESSAGE);
				  break;
			  }
		  }
	  }
	  if(newbooks!=null){
		  for(int i=0; i<newbooks.size(); i++){
			  if(("register"+i).equals(command))
			  {	
				  if(isloggedin()){
					  RegistrationForm registerform = new RegistrationForm(newbooks.get(i),frame,user);
					  frame.remove(scrollpane);
					  registerform.initUI();
				  }
				  else{
					  JOptionPane.showMessageDialog(frame,
							    "You need to be signed in!",
							    "Registration Error",
							    JOptionPane.ERROR_MESSAGE);
				  }
				  break;
			  }
		  }
	  }
  }
}