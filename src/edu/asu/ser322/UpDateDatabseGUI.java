package edu.asu.ser322;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpDateDatabseGUI extends JPanel 
{
	private JComboBox boxEntities;
	private JLabel TitleOfPage;
	private JLabel prompt;
	private JLabel primaryKey;
	private JLabel secondItem;
	private JLabel thirdItem;
	private JLabel fourthItem;
	private JLabel fifthItem;
	private JLabel sixthItem;
	
	private JTextField primaryKeyField;
	private JTextField secondItemField;
	private JTextField thirdItemField;
	private JTextField fourthItemField;
	private JTextField fifthItemField;
	private JTextField sixthItemField;
	
	private JButton addItemToDatabase;
	
	Client client;
	
	public UpDateDatabseGUI(Client client)
	{
		this.client = client;
		init();
	}
	
	public void init()
	{
		boxEntities = new JComboBox();
		
		TitleOfPage = new JLabel();
		prompt = new JLabel();
		
		primaryKey = new JLabel();
		secondItem = new JLabel();
		thirdItem = new JLabel();
		fourthItem = new JLabel();
		fifthItem = new JLabel();
		sixthItem = new JLabel();
		
		primaryKeyField = new JTextField();
		secondItemField = new JTextField();
		thirdItemField = new JTextField();
		fourthItemField = new JTextField();
		fifthItemField = new JTextField();
		sixthItemField = new JTextField();
		
		addItemToDatabase = new JButton();
	}
	
	public void layout()
	{
		setLayout(null);
		
		TitleOfPage.setBounds(600,20,300,80);
		add(TitleOfPage);
	}
	
	public void setTextStyle()
	{
		;
	}
	
	public void populateEntities()
	{
		boxEntities.addItem("");
	}
}
