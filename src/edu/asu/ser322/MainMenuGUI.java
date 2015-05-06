package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Franchise;
import edu.asu.ser322.data.model.Person;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.Studio;
import edu.asu.ser322.data.model.User;
import edu.asu.ser322.data.model.Gender;

/**
 * 
 * @author Cuahuc
 * @author Benjamin Paothatat
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
	private JComboBox searchBy;
	private JTextField searchBarTextField;
	private JButton profileButton;
	private JButton searchButton;
	private JButton logoutButton;
	private JButton goToUpdateButton;
	// private JList titleHolderList;
	private JTable results;
	private List<String> ListOfEntities;
	private List<String> entitiesSubSearch;
	private Hashtable<String, String[]> linkEntitesToSearch;
	private String[] chacacterSerach;
	private String[] franchiseSerach;
	private String[] episodeSearch;
	private String[] seasonSearch;
	private String[] personSearch;
	private String[] studioSearch;
	private List<Character> searchResultsOfCharacter;
	private List<Franchise> searchResultsOfFranchise;
	private List<Episode> searchResultsOfEpisode;
	private List<Person> searchResultsOfPerson;
	private List<Season> searchResultsOfSeason;
	private List<Episode> searchResultsOfEpisodes;
	private List<Studio> searchResultsOfStudio;
	private DefaultTableModel tableModel;
	JScrollPane spTable;
	Vector<String> columnNames = new Vector<String>();
	Vector<Vector<String>> rowValues = new Vector<Vector<String>>();
	Vector<String> vector = new Vector<String>();
	
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
		// informationSelectedListener();
		populateListOfTableArray();
		entitiesSubSearch = new ArrayList<String>();
		tableList = new JComboBox<>(ListOfEntities.toArray());
		searchBarTextField = new JTextField();
		searchButton = new JButton("Search");
		logoutButton = new JButton("Logout");
		profileButton = new JButton("Profile");
		goToUpdateButton = new JButton("Update Database");
		tableModel = new DefaultTableModel();
		results = new JTable(tableModel);
		spTable = new JScrollPane(results);
		// columnNames = new Vector<String>();
		// rowValues = new Vector<String>();
		
		chacacterSerach = new String[] { "By Name", "By Age", "By Archetype",
				"By Gender", "By Hair Color", "List All" };
		franchiseSerach = new String[] { "By Name", "List All" };
		episodeSearch = new String[] { "By Name", "List All" };
		seasonSearch = new String[] { "By Series Name", "By Genre",
				"By Year Of Air Date", "List All" };
		personSearch = new String[] { "By Name", "List All" };
		studioSearch = new String[] { "By Name", "List All" };
		linkEntitesToSearch = new Hashtable<String, String[]>();
		linkEntitesToSearch.put("Character", chacacterSerach);
		linkEntitesToSearch.put("Franchise", franchiseSerach);
		linkEntitesToSearch.put("Episode", episodeSearch);
		linkEntitesToSearch.put("Season", seasonSearch);
		linkEntitesToSearch.put("Person", personSearch);
		linkEntitesToSearch.put("Studio", studioSearch);
		searchBy = new JComboBox(linkEntitesToSearch.get(tableList.getSelectedItem()
				.toString()));
		
		selectedItemPictureLabel = new JLabel();
		selectedItemInfoLabel = new JLabel();
		
		tableList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				searchBy.removeAllItems();
				String[] replacementSearchBy = linkEntitesToSearch.get(tableList
						.getSelectedItem().toString());
				for (int i = 0; i < replacementSearchBy.length; i++)
				{
					searchBy.addItem(replacementSearchBy[i]);
				}
				repaint();
			}
			
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Populate the JList with search results
				// titleHolderList.setVisible(true);
				// loadInfoOnShows();
				if (tableList.getSelectedItem().toString().equals("Character"))
				{
					characterSearches();
				}
				else if (tableList.getSelectedItem().toString().equals("Franchise"))
				{
					franchiseSearches();
				}
				else if (tableList.getSelectedItem().toString().equals("Episode"))
				{
					episodeSearches();
				}
				else if (tableList.getSelectedItem().toString().equals("Season"))
				{
					seasonSearches();
				}
				else if (tableList.getSelectedItem().toString().equals("Person"))
				{
					personSearches();
				}
				else
				{
					studioSearches();
				}
				
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Session.logout();
				client.showLogin();
			}
		});
		
		goToUpdateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				client.showUpdateDatabase();
			}
		});
		profileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				client.showProfile();
			}
		});
		
	}
	
	public void layout()
	{
		titleLabel.setBounds(600, 20, 300, 30);
		add(titleLabel);
		
		searchBy.setBounds(810, 100, 150, 30);
		add(searchBy);
		
		tableList.setBounds(810, 60, 150, 30);
		add(tableList);
		
		searchBarTextField.setBounds(970, 60, 200, 30);
		add(searchBarTextField);
		
		searchButton.setBounds(1180, 60, 80, 30);
		add(searchButton);
		
		logoutButton.setBounds(1180, 15, 80, 30);
		add(logoutButton);
		
		// TODO: function for the settings button has yet to be defined
		profileButton.setBounds(30, 30, 100, 30);
		add(profileButton);
		
		spTable.setBounds(50, 200, 900, 250);
		add(spTable);
		
		selectedItemPictureLabel.setBounds(350, 200, 150, 200);
		add(selectedItemPictureLabel);
		
		selectedItemInfoLabel.setBounds(510, 200, 210, 200);
		add(selectedItemInfoLabel);
		
		goToUpdateButton.setBounds(140, 30, 200, 30);
		add(goToUpdateButton);
	}
	
	public void clearAllVectors()
	{
		columnNames.clear();
		vector.clear();
		rowValues.clear();
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
	
	public void populateListOfTableArray()
	{
		ListOfEntities.add("Character");
		ListOfEntities.add("Episode");
		ListOfEntities.add("Franchise");
		ListOfEntities.add("Person");
		ListOfEntities.add("Season");
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
	
	private Vector<String> parseCharacter(Character character)
	{
		Vector vector = new Vector<String>();
		String name = character.getName();
		String gender = character.getGender().toString();
		String archetype = character.getArchetype();
		String hairColor = character.getHairColor();
		String dob = "";
		Date dateOfBirth = character.getBirthDate();
		if (dateOfBirth != null)
		{
			dob = parseDate(dateOfBirth);
		}
		
		vector.add(name);
		vector.add(gender);
		vector.add(archetype);
		vector.add(hairColor);
		vector.add(dob);
		rowValues.add(vector);
		return vector;
	}
	
	private Vector<String> parseEpisode(Episode episode)
	{
		vector = new Vector<String>();
		String seriesName = episode.getSeriesName();
		String seasonNumber = Integer.toString(episode.getSeasonNumber());
		String episodeNumber = Integer.toString(episode.getEpisodeNumber());
		String showName = episode.getEpisodeName();
		String airDate = "";
		if (episode.getAirDate() != null)
		{
			airDate = parseDate(episode.getAirDate());
		}
		
		String artStyle = episode.getArtStyle();
		String appropriateness = episode.getApproprateness();
		vector.add(seriesName);
		vector.add(seasonNumber);
		vector.add(episodeNumber);
		vector.add(showName);
		vector.add(airDate);
		vector.add(artStyle);
		vector.add(appropriateness);
		rowValues.add(vector);
		return vector;
	}
	
	private Vector<String> parseSeason(Season season)
	{
		Vector vector = new Vector<String>();
		String seriesName = season.getSeriesName();
		String seasonNumber = Integer.toString(season.getSeasonNumber());
		String showName = season.getName();
		String airDate = "";
		if (season.getAirDate() != null)
		{
			airDate = parseDate(season.getAirDate());
		}
		String finishDate = "";
		if (season.getFinishDate() != null)
		{
			finishDate = parseDate(season.getFinishDate());
		}
		
		String appropriateness = season.getAppropriateness();
		vector.add(seriesName);
		vector.add(seasonNumber);
		vector.add(showName);
		vector.add(airDate);
		vector.add(finishDate);
		vector.add(appropriateness);
		rowValues.add(vector);
		return vector;
	}
	
	private void characterSearches()
	{
		clearAllVectors();
		if (searchBy.getSelectedItem().toString().equals("By Name"))
		{
			searchResultsOfCharacter = DAOCollection.getCharacterDao()
					.findCharactersByName(searchBarTextField.getText());
		}
		else if (searchBy.getSelectedItem().toString().equals("By Age"))
		{
			// TODO: Add age to Characters
		}
		else if (searchBy.getSelectedItem().toString().equals("By Archetype"))
		{
			searchResultsOfCharacter = DAOCollection.getCharacterDao()
					.findCharactersByArchetype(searchBarTextField.getText());
		}
		else if (searchBy.getSelectedItem().toString().equals("By Gender"))
		{
			searchResultsOfCharacter = DAOCollection.getCharacterDao()
					.findCharactersByGender(Gender.valueOf(searchBarTextField.getText()));
		}
		else if (searchBy.getSelectedItem().toString().equals("By Hair Color"))
		{
			searchResultsOfCharacter = DAOCollection.getCharacterDao()
					.findCharactersByHairColour(searchBarTextField.getText());
		}
		else
		{
			searchResultsOfCharacter = DAOCollection.getCharacterDao().listAll();
		}
		
		columnNames.add("Name");
		columnNames.add("Gender");
		columnNames.add("Archetype");
		columnNames.add("Hair Color");
		columnNames.add("Birthday Day");
		tableModel.setColumnIdentifiers(columnNames);
		
		for (int i = 0; i < searchResultsOfCharacter.size(); i++)
		{
			parseCharacter(searchResultsOfCharacter.get(i));
		}
		
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
	}
	
	private void franchiseSearches()
	{
		clearAllVectors();
		
		columnNames.add("FranchiseID");
		columnNames.add("FranchiseName");
		
		tableModel.setColumnIdentifiers(columnNames);
		
		if (searchBy.getSelectedItem().toString().equals("By Name"))
		{
			Franchise franchise = DAOCollection.getFranchiseDao().findFranchise(
					searchBarTextField.getText());
			
			String ID = Integer.toString(franchise.getId());
			String name = franchise.getName();
			vector.add(ID);
			vector.add(name);
			
			rowValues.add(vector);
		}
		else
		{
			searchResultsOfFranchise = DAOCollection.getFranchiseDao().listAll();
			for (Franchise franchise : searchResultsOfFranchise)
			{
				vector = new Vector<String>();
				String ID = Integer.toString(franchise.getId());
				String name = franchise.getName();
				vector.add(ID);
				vector.add(name);
				
				rowValues.add(vector);
			}
		}
		
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
	}
	
	private void episodeSearches()
	{
		clearAllVectors();
		
		if (searchBy.getSelectedItem().toString().equals("By Name"))
		{
			searchResultsOfEpisodes = DAOCollection.getEpisodeDao().findEpisode(
					searchBarTextField.getText());
		}
		else
		{
			searchResultsOfEpisodes = DAOCollection.getEpisodeDao().listAll();
		}
		columnNames.add("SeriesName");
		columnNames.add("SeasonNumber");
		columnNames.add("EpisodeNumber");
		columnNames.add("EpisodeName");
		columnNames.add("AirDate");
		columnNames.add("ArtStyle");
		columnNames.add("Appropriateness");
		
		tableModel.setColumnIdentifiers(columnNames);
		
		for (Episode episode : searchResultsOfEpisodes)
		{
			parseEpisode(episode);
		}
		
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
	}
	
	private void seasonSearches()
	{
		clearAllVectors();
		if (searchBy.getSelectedItem().toString().equals("By Series Name"))
		{
			searchResultsOfSeason = DAOCollection.getSeasonDao().findSeasonsBySeriesName(
					searchBarTextField.getText());
		}
		else if (searchBy.getSelectedItem().toString().equals("By Year Of Air Date"))
		{
			searchResultsOfSeason = DAOCollection.getSeasonDao().seasonsByAirYear(
					Integer.parseInt(searchBarTextField.getText()));
		}
		else if (searchBy.getSelectedItem().toString().equals("By Genre"))
		{
			searchResultsOfSeason = DAOCollection.getSeasonDao().seasonsByGenre(
					searchBarTextField.getText());
		}
		else
		{
			searchResultsOfSeason = DAOCollection.getSeasonDao().listAll();
		}
		
		columnNames.add("SeriesName");
		columnNames.add("SeasonNumber");
		columnNames.add("ShowName");
		columnNames.add("AirDate");
		columnNames.add("FinishDate");
		columnNames.add("Appropriateness");
		
		tableModel.setColumnIdentifiers(columnNames);
		
		for (Season season : searchResultsOfSeason)
		{
			parseSeason(season);
		}
		
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
	}
	
	private void personSearches()
	{
		clearAllVectors();
		if (searchBy.getSelectedItem().toString().equals("By Name"))
		{
			searchResultsOfPerson = DAOCollection.getPeopleDao().findPerson(
					searchBarTextField.getText());
		}
		else
		{
			searchResultsOfPerson = DAOCollection.getPeopleDao().listAll();
		}
		
		columnNames.add("PersonID");
		columnNames.add("Name");
		
		tableModel.setColumnIdentifiers(columnNames);
		
		for (Person person : searchResultsOfPerson)
		{
			Vector vector = new Vector<String>();
			String personId = Integer.toString(person.getID());
			String name = person.getName();
			
			vector.add(personId);
			vector.add(name);
			
			rowValues.add(vector);
		}
		
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
		
	}
	
	private void studioSearches()
	{
		clearAllVectors();
		columnNames.add("StudioName");
		columnNames.add("StartDate");
		columnNames.add("CloseDate");
		
		tableModel.setColumnIdentifiers(columnNames);
		
		if (searchBy.getSelectedItem().toString().equals("By Name"))
		{
			Studio studio = DAOCollection.getStudioDao().findStudio(
					searchBarTextField.getText());
			parseStudio(studio);
		}
		else
		{
			searchResultsOfStudio = DAOCollection.getStudioDao().listAll();
			for (Studio studio : searchResultsOfStudio)
			{
				parseStudio(studio);
			}
		}
		
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
	}
	
	private Vector<String> parseStudio(Studio studio)
	{
		Vector vector = new Vector<String>();
		String studioName = studio.getName();
		String studioStartDate = "";
		String studioCloseDate = "";
		if (studio.getStartDate() != null)
		{
			studioStartDate = parseDate(studio.getStartDate());
		}
		
		if (studio.getCloseDate() != null)
		{
			studioCloseDate = parseDate(studio.getCloseDate());
		}
		
		vector.add(studioName);
		vector.add(studioStartDate);
		vector.add(studioCloseDate);
		
		rowValues.add(vector);
		return vector;
	}
	
	private String parseDate(Date date)
	{
		String result;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		result = (calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar
				.get(Calendar.YEAR));
		return result;
	}
	
}
