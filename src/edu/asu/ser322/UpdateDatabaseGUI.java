package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Gender;
import edu.asu.ser322.data.model.Season;

public class UpdateDatabaseGUI extends JPanel
{
	private JComboBox<Object> boxEntities;
	private JLabel TitleOfPage;
	private JLabel prompt;
	private JLabel primaryKey;
	private JLabel secondItem;
	private JLabel thirdItem;
	private JLabel fourthItem;
	private JLabel fifthItem;
	
	private JTextField primaryKeyField;
	private JTextField secondItemField;
	private JTextField thirdItemField;
	private JTextField fourthItemField;
	private JTextField fifthItemField;
	private JTextField sixthItemField;
	
	private JButton editItemToDatabase;
	private JButton backButton;
	private List<String> listOfEntitiesToModify;
	private BufferedImage img;
	
	Client client;
	
	public UpdateDatabaseGUI(Client client)
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
		boxEntities = new JComboBox<>(listOfEntitiesToModify.toArray());
		TitleOfPage = new JLabel();
		prompt = new JLabel();
		
		primaryKey = new JLabel();
		secondItem = new JLabel();
		thirdItem = new JLabel();
		fourthItem = new JLabel();
		fifthItem = new JLabel();
		
		primaryKeyField = new JTextField();
		secondItemField = new JTextField();
		thirdItemField = new JTextField();
		fourthItemField = new JTextField();
		fifthItemField = new JTextField();
		sixthItemField = new JTextField();
		
		addImageBackGround();
		
		editItemToDatabase = new JButton("Update Database");
		editItemToDatabase.setVisible(false);
		backButton = new JButton("Back");
		
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
		
		editItemToDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (boxEntities.getSelectedItem().toString().equals("Add Characters"))
				{
					Character instertedCharacter = new Character();
					
					instertedCharacter.setName(primaryKeyField.getText());
					instertedCharacter.setGender(Gender.valueOf(secondItemField.getText()
							.toUpperCase()));
					instertedCharacter.setArchetype(thirdItemField.getText());
					instertedCharacter.setHairColor(fourthItemField.getText());
					instertedCharacter.setAge(Integer.valueOf(fifthItemField.getText()));
					
					DAOCollection.getCharacterDao().addCharacter(instertedCharacter);
				}
				else if (boxEntities.getSelectedItem().toString().equals("Add Seasons"))
				{
					Season instertedSeason = new Season();
					instertedSeason.setName(primaryKeyField.getText());
					instertedSeason.setSeasonNumber(Integer.valueOf(secondItemField
							.getText()));
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
					Date startDate;
					Date endDate;
					try
					{
						startDate = df.parse(thirdItemField.getText());
						instertedSeason.setAirDate(startDate);
						endDate = df2.parse(fourthItemField.getText());
						instertedSeason.setFinishDate(endDate);
						
					}
					catch (ParseException exception)
					{
						exception.printStackTrace();
					}
					instertedSeason.setAppropriateness(fifthItemField.getText());
					System.out.print(fifthItem.getText());
					DAOCollection.getSeasonDao().addSeason(instertedSeason);
				}
				else if (boxEntities.getSelectedItem().toString()
						.equals("Remove Characters"))
				{
					Character instertedCharacter = new Character();
					
					instertedCharacter.setName(primaryKeyField.getText());
					
					DAOCollection.getCharacterDao().deleteCharacter(instertedCharacter);
				}
				else if (boxEntities.getSelectedItem().toString()
						.equals("Remove Seasons"))
				{
					DAOCollection.getSeasonDao().removeSeason(primaryKeyField.getText(),
							Integer.valueOf(secondItemField.getText()));
				}
			}
		});
		
		add(TitleOfPage);
		add(boxEntities);
		add(primaryKey);
		add(secondItem);
		add(thirdItem);
		add(fourthItem);
		add(fifthItem);
		add(backButton);
		
		add(primaryKeyField);
		add(secondItemField);
		add(thirdItemField);
		add(fourthItemField);
		add(fifthItemField);
		
		add(editItemToDatabase);
	}
	
	public void layout()
	{
		setLayout(null);
		
		TitleOfPage.setBounds(600, 20, 300, 80);
		boxEntities.setBounds(100, 60, 300, 30);
		primaryKey.setBounds(110, 100, 150, 30);
		secondItem.setBounds(110, 150, 150, 30);
		thirdItem.setBounds(110, 200, 150, 30);
		fourthItem.setBounds(110, 250, 150, 30);
		fifthItem.setBounds(110, 300, 150, 30);
		backButton.setBounds(1100, 580, 80, 30);
		
		primaryKeyField.setBounds(300, 100, 150, 30);
		secondItemField.setBounds(300, 150, 150, 30);
		thirdItemField.setBounds(300, 200, 150, 30);
		fourthItemField.setBounds(300, 250, 150, 30);
		fifthItemField.setBounds(300, 300, 150, 30);
		
		editItemToDatabase.setBounds(180, 350, 100, 30);
	}
	
	public void setTextStyle(String itemSelected)
	{
		if (itemSelected.equals("Add Characters"))
		{
			primaryKey.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Name</font>");
			secondItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Gender</font>");
			thirdItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Archetype</font>");
			fourthItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Hair Color</font>");
			fifthItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Age</font>");
			
			primaryKeyField.setVisible(true);
			secondItemField.setVisible(true);
			thirdItemField.setVisible(true);
			fourthItemField.setVisible(true);
			fifthItemField.setVisible(true);
			
			editItemToDatabase.setVisible(true);
		}
		else if (itemSelected.equals("Add Seasons"))
		{
			primaryKey.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Series Name</font>");
			secondItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Season Number</font>");
			thirdItem
					.setText("<html>\n"
							+ "<font><font size=+1><font color=#FF0000>Air Date (DD/MM/YYYY)</font>");
			fourthItem
					.setText("<html>\n"
							+ "<font><font size=+1><font color=#FF0000>Date Finish (DD/MM/YYYY)</font>");
			fifthItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Appropriateness</font>");
			
			primaryKeyField.setVisible(true);
			secondItemField.setVisible(true);
			thirdItemField.setVisible(true);
			fourthItemField.setVisible(true);
			fifthItemField.setVisible(true);
			
			editItemToDatabase.setVisible(true);
			
		}
		else if (itemSelected.equals("Remove Characters"))
		{
			primaryKey.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Name</font>");
			secondItem.setText("");
			thirdItem.setText("");
			fourthItem.setText("");
			fifthItem.setText("");
			
			primaryKeyField.setVisible(true);
			secondItemField.setVisible(false);
			thirdItemField.setVisible(false);
			fourthItemField.setVisible(false);
			fifthItemField.setVisible(false);
			
			editItemToDatabase.setVisible(true);
		}
		else if (itemSelected.equals("Remove Seasons"))
		{
			primaryKey.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Series Name</font>");
			secondItem.setText("<html>\n"
					+ "<font><font size=+1><font color=#FF0000>Season Number</font>");
			thirdItem.setText("");
			fourthItem.setText("");
			fifthItem.setText("");
			
			primaryKeyField.setVisible(true);
			secondItemField.setVisible(true);
			thirdItemField.setVisible(false);
			fourthItemField.setVisible(false);
			fifthItemField.setVisible(false);
			
			editItemToDatabase.setVisible(true);
		}
		else if (itemSelected.equals(""))
		{
			primaryKey.setText("");
			secondItem.setText("");
			thirdItem.setText("");
			fourthItem.setText("");
			fifthItem.setText("");
			
			primaryKeyField.setVisible(false);
			secondItemField.setVisible(false);
			thirdItemField.setVisible(false);
			fourthItemField.setVisible(false);
			fifthItemField.setVisible(false);
			
			editItemToDatabase.setVisible(false);
		}
	}
	
	public void insertIntoTable()
	{
		
	}
	
	public void populateEntities()
	{
		listOfEntitiesToModify.add("");
		listOfEntitiesToModify.add("Add Characters");
		listOfEntitiesToModify.add("Add Seasons");
		listOfEntitiesToModify.add("Remove Characters");
		listOfEntitiesToModify.add("Remove Seasons");
	}
	
	public void addImageBackGround()
	{
		try
		{
			img = ImageIO.read(new File("rec/updateDatabasebackground.jpg"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (img != null)
		{
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
