package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Franchise;
import edu.asu.ser322.data.model.Person;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.Studio;
import edu.asu.ser322.data.model.Character;

/**
 * 
 * @author Cuahuc
 *
 */

public class MainMenuGUI extends JPanel
{
	private Client client;
	
	private JLabel titleLabel;
	private JLabel accountInfoLabel;
	private JLabel selectedItemPictureLabel;
	private JLabel selectedItemInfoLabel;
	private JComboBox tableList;
	private JTextField searchBarTextField;
	private JButton settingsButton;
	private JButton searchButton;
	private JButton logoutButton;
	//private JList titleHolderList;
	private JTable results;
	private List<String> ListOfEntities;
	private List<Character> searchResultsOfCharacter;
	private List<Season> searchResultsOfSeason;
	private List<Episode> searchResultsOfEpisodes;
	private List<Franchise> searchResultsOfFranchise;
	private List<Studio> searchResultsOfStudio;
	private List<Person> searchResultsOfPerson;
	private DefaultTableModel tableModel;
	
	private BufferedImage img;
	
	public MainMenuGUI(Client client)
	{
		this.client = client;
		this.ListOfEntities = new ArrayList<>();
		
		init();
		layout();
	}
	
	private void init()
	{
		setOpaque(false);
		addImageBackGround();
		titleLabel = new JLabel("Anime Database");
		informationSelectedListener();
		populateListOfTableArray();
		tableList = new JComboBox<>(ListOfEntities.toArray());
		searchBarTextField = new JTextField();
		searchButton = new JButton("Search");
		logoutButton = new JButton("Logout");
		settingsButton = new JButton("Settings");
		results = new JTable(tableModel);
		JScrollPane spTable = new JScrollPane(results);
		
		//searchResultsOfCharacter = new LinkedList<Character>();
		//searchResultsOfSeason = new LinkedList<Season>();
		//searchResultsOfEpisodes = new LinkedList<Episode>();
		//searchResultsOfFranchise = new LinkedList<Franchise>();
		//searchResultsOfStudio = new LinkedList<Studio>();
		//searchResultsOfPerson = new LinkedList<Person>();
		
		//titleHolderList.setVisible(false);
		
		selectedItemPictureLabel = new JLabel();
		selectedItemInfoLabel = new JLabel();
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Populate the JList with search results
				//titleHolderList.setVisible(true);
				// loadInfoOnShows();
				if(tableList.getSelectedItem().toString().equals("Character"))
				{
					
					searchResultsOfCharacter = DAOCollection.getCharacterDao().findCharactersByName(searchBarTextField.getText());
					String[] columnNames = {"CharacterID", "Name", "Gender", "Archetype", "Hair Color", "Birthday Day"};
					tableModel = new DefaultTableModel(columnNames, searchResultsOfCharacter.size());
					for(int i = 0; i < searchResultsOfCharacter.size(); i++)
					{
						int id = searchResultsOfCharacter.get(i).getId();
						String name = searchResultsOfCharacter.get(i).getName();
						String gender = searchResultsOfCharacter.get(i).getGender().toString();
						String archetype = searchResultsOfCharacter.get(i).getHairColor();
					    String dob = searchResultsOfCharacter.get(i).getBirthDate().toString();
						Object[] data = {id, name, gender, archetype, dob};
						tableModel.addRow(data);
					}
					results = new JTable(tableModel);
				}
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Session.logout();
				client.showLoginPanel();
			}
		});
	}
	
	public void layout()
	{
		titleLabel.setBounds(600, 20, 300, 30);
		add(titleLabel);
		
		tableList.setBounds(810, 60, 150, 30);
		add(tableList);
		
		searchBarTextField.setBounds(970, 60, 200, 30);
		add(searchBarTextField);
		
		searchButton.setBounds(1180, 60, 80, 30);
		add(searchButton);
		
		logoutButton.setBounds(1180, 15, 80, 30);
		add(logoutButton);
		
		// TODO: function for the settings button has yet to be defined
		// Settings.setBounds(30, 30, 100, 30);
		// add(Settings);
		
	   
		
		results.setBounds(50, 200, 400, 250);
		add(results);
		
		selectedItemPictureLabel.setBounds(350, 200, 150, 200);
		add(selectedItemPictureLabel);
		
		selectedItemInfoLabel.setBounds(510, 200, 210, 200);
		add(selectedItemInfoLabel);
	}

	
	public void setInfoOfSelectedItem(String info)
	{
		final String startTag = "<html><body style='width: ";
		final String endTag = "px'>";
		String infoOfShow = startTag + "200" + endTag + info;
		selectedItemInfoLabel.setText(infoOfShow);
	}
	
	public void setPictureOfSelectedItem(String picturePath)
	{
		ImageIcon icon = createImageIcon(picturePath);
		selectedItemPictureLabel.setIcon(icon);
	}
	
	public ImageIcon createImageIcon(String path)
	{
		java.net.URL imageURL = MainMenuGUI.class.getResource(path);
		ImageIcon returnIcon;
		
		if (imageURL != null)
		{
			returnIcon = new ImageIcon(imageURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			returnIcon = null;
		}
		
		return returnIcon;
	}
	
	public void informationSelectedListener()
	{
		//titleHolderList = new JList(ListOfEntities.toArray());
		//titleHolderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//titleHolderList.addListSelectionListener(new ListSelectionListener() {
			//@Override
			//public void valueChanged(ListSelectionEvent e)
			//{
				// TODO: Here is where we will get the info for the titles that
				// we have
				// selected
				
				// setInfoOfSelectedItem(String of info);
				// setPictureOfSelectedItem(String path to image);
				
			}
		//});
		
	//}
	
	public void populateListOfTableArray()
	{
		ListOfEntities.add("Character");
		ListOfEntities.add("Episode");
		ListOfEntities.add("Franchise");
		ListOfEntities.add("Person");
		ListOfEntities.add("Seasons");
		ListOfEntities.add("Studio");
	}
	
	
	public void addImageBackGround()
	{
		try
		{
			img = ImageIO.read(new File("rec/MainMenuBackground.jpg"));
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
