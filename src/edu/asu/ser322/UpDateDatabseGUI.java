package edu.asu.ser322;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
	private JButton backButton;
	private List<String> listOfEntitiesToModify;
	
	Client client;
	
	public UpDateDatabseGUI(Client client)
	{
		this.client = client;
		this.listOfEntitiesToModify = new ArrayList<>();
		init();
		layout();
		
		primaryKeyField.setVisible(false);
		secondItemField.setVisible(false);
		thirdItemField.setVisible(false);
		fourthItemField.setVisible(false);
		fifthItemField.setVisible(false);
		sixthItemField.setVisible(false);
	}
	
	public void init()
	{
		populateEntities();
		boxEntities = new JComboBox(listOfEntitiesToModify.toArray());
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
		backButton = new JButton("back");
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				client.showMainMenu();
			}
		});
		
		boxEntities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setTextStyle(boxEntities.getSelectedItem().toString());
				repaint();
			}
		});
		
		add(TitleOfPage);
		add(boxEntities);
		add(primaryKey);
		add(secondItem);
		add(thirdItem);
		add(fourthItem);
		add(fifthItem);
		add(sixthItem);
		add(backButton);
		
		add(primaryKeyField);
		add(secondItemField);
		add(thirdItemField);
		add(fourthItemField);
		add(fifthItemField);
		add(sixthItemField);
	}
	
	public void layout()
	{
		setLayout(null);
		
		TitleOfPage.setBounds(600, 20, 300, 80);
		boxEntities.setBounds(810, 60, 150, 30);
		primaryKey.setBounds(810, 100, 150, 30);
		secondItem.setBounds(810, 150, 150, 30);
		thirdItem.setBounds(810, 200, 150, 30);
		fourthItem.setBounds(810, 250, 150, 30);
		fifthItem.setBounds(810, 300, 150, 30);
		sixthItem.setBounds(810, 350, 150, 30);
		backButton.setBounds(1100, 580, 80, 30);
		
		primaryKeyField.setBounds(970, 100, 150, 30);
		secondItemField.setBounds(970, 150, 150, 30);
		thirdItemField.setBounds(970, 200, 150, 30);
		fourthItemField.setBounds(970, 250, 150, 30);
		fifthItemField.setBounds(970, 300, 150, 30);
		sixthItemField.setBounds(970, 350, 150, 30);
	}
	
	public void setTextStyle(String itemSelected)
	{	
		if (itemSelected.equals("Characters"))
		{
			primaryKey.setText("Name");
			secondItem.setText("Gender");
			thirdItem.setText("archetype");
			fourthItem.setText("Hair Color");
			fifthItem.setText("Birth Date");
			sixthItem.setText("");
			
			primaryKeyField.setVisible(true);
			secondItemField.setVisible(true);
			thirdItemField.setVisible(true);
			fourthItemField.setVisible(true);
			fifthItemField.setVisible(true);
			sixthItemField.setVisible(false);
		}
		else if (itemSelected.equals("Seasons"))
		{
			primaryKey.setText("Series Name");
			secondItem.setText("Season Number");
			thirdItem.setText("Air Date");
			fourthItem.setText("Date Finished");
			fifthItem.setText("Genres");
			sixthItem.setText("Appropriateness");
			
			primaryKeyField.setVisible(true);
			secondItemField.setVisible(true);
			thirdItemField.setVisible(true);
			fourthItemField.setVisible(true);
			fifthItemField.setVisible(true);
			sixthItemField.setVisible(true);
			
		}
		else if(itemSelected.equals(""))
		{
			primaryKey.setText("");
			secondItem.setText("");
			thirdItem.setText("");
			fourthItem.setText("");
			fifthItem.setText("");
			sixthItem.setText("");
			
			primaryKeyField.setVisible(false);
			secondItemField.setVisible(false);
			thirdItemField.setVisible(false);
			fourthItemField.setVisible(false);
			fifthItemField.setVisible(false);
			sixthItemField.setVisible(false);
		}
	}
	
	public void insertIntoTable()
	{
		
	}
	
	public void populateEntities()
	{
		listOfEntitiesToModify.add("");
		listOfEntitiesToModify.add("Characters");
		listOfEntitiesToModify.add("Seasons");
	}
	
}
