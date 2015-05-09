package edu.asu.ser322;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.asu.ser322.data.access.CharacterDao;
import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.EpisodeDao;
import edu.asu.ser322.data.access.FranchiseDao;
import edu.asu.ser322.data.access.SeasonDao;
import edu.asu.ser322.data.access.StudioDao;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Franchise;
import edu.asu.ser322.data.model.Gender;
import edu.asu.ser322.data.model.Person;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.Studio;

/**
 * Main Menu Panel, Where most search are to be made.
 * 
 * @author Moore, Zachary
 * @author Osorio, Cuahuctemoc
 * @author Paothatat, Benjamin
 * 
 */
public class MainMenuGUI extends JPanel
{
	@FunctionalInterface
	private interface Search extends Consumer<String>
	{
	}
	
	private class SearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String primaryParameter = tableList.getSelectedItem().toString();
			String secondaryParameter = searchBy.getSelectedItem().toString();
			String searchTerm = searchBarTextField.getText();
			
			Map<String, Search> searches = searchFunctions.get(primaryParameter);
			Search search = searches.get(secondaryParameter);
			search.accept(searchTerm);
		}
	}
	
	private Client client;
	
	private JLabel selectedItemPictureLabel;
	private JLabel selectedItemInfoLabel;
	private JComboBox<Object> tableList;
	private JComboBox<String> searchBy;
	private JTextField searchBarTextField;
	private List<String> ListOfEntities;
	private List<Character> searchResultsOfCharacter;
	private List<Person> searchResultsOfPerson;
	private List<Season> searchResultsOfSeason;
	
	private DefaultTableModel tableModel;
	private Vector<String> columnNames = new Vector<>();
	private Vector<Vector<String>> rowValues = new Vector<>();
	private Vector<String> vector = new Vector<>();
	private Map<String, Map<String, Search>> searchFunctions = new LinkedHashMap<>();
	
	private BufferedImage img;
	
	/**
	 * constructor for the MainMenu Panel passes and client instance into here to connect
	 * each panel
	 * 
	 * @param client
	 */
	public MainMenuGUI(Client client)
	{
		this.client = client;
		this.ListOfEntities = new ArrayList<>();
		init();
	}
	
	/**
	 * 
	 * This will grab the images from the file and place it into a buffer which will be
	 * painted in another functions
	 * 
	 */
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
	
	public void layout()
	{
		
	}
	
	/**
	 * this will populate the top combo box of what can be searched
	 */
	public void populateListOfTableArray()
	{
		ListOfEntities.add("Character");
		ListOfEntities.add("Episode");
		ListOfEntities.add("Franchise");
		ListOfEntities.add("Person");
		ListOfEntities.add("Season");
		ListOfEntities.add("Studio");
	}
	
	/**
	 * this will paint the the images onto the background
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (img != null)
		{
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	private void addSearch(String searchTerm, String subSearchTerm, Search action)
	{
		if (!searchFunctions.containsKey(searchTerm))
			searchFunctions.put(searchTerm, new LinkedHashMap<>());
		
		Map<String, Search> map = searchFunctions.get(searchTerm);
		map.put(subSearchTerm, action);
	}
	
	/**
	 * 
	 * This will grab from the combobox what you want to search by and when you type it in
	 * the search it will search by those parameters through the database give you results
	 * if it finds anything
	 * 
	 */
	private void displayCharacterResults(List<Character> results)
	{
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Name");
		columnNames.add("Gender");
		columnNames.add("Archetype");
		columnNames.add("Hair Color");
		columnNames.add("Age");
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (Character character : results)
		{
			Vector<String> vector = new Vector<String>();
			String name = character.getName();
			String gender = character.getGender().toString();
			String archetype = character.getArchetype();
			String hairColor = character.getHairColor();
			String age = Integer.toString(character.getAge());
			
			vector.add(name);
			vector.add(gender);
			vector.add(archetype);
			vector.add(hairColor);
			vector.add(age);
			
			tableModel.addRow(vector);
		}
		
		repaint();
	}
	
	/**
	 * This will place the column name at the top to be able to identify each column And
	 * this this will fill the vectors with the results which will then be able to fill
	 * the table will the results
	 */
	private void displayEpisodeResults(List<Episode> episodes)
	{
		Vector<String> columnNames = new Vector<>();
		
		columnNames.add("SeriesName");
		columnNames.add("SeasonNumber");
		columnNames.add("EpisodeNumber");
		columnNames.add("EpisodeName");
		columnNames.add("AirDate");
		columnNames.add("ArtStyle");
		columnNames.add("Appropriateness");
		
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (Episode episode : episodes)
		{
			Vector<String> vector = new Vector<String>();
			
			String seriesName = episode.getSeriesName();
			String seasonNumber = Integer.toString(episode.getSeasonNumber());
			String episodeNumber = Integer.toString(episode.getEpisodeNumber());
			String showName = episode.getEpisodeName();
			Date airDate = episode.getAirDate();
			String airDateString = (airDate != null) ? formatDate(airDate) : "";
			String artStyle = episode.getArtStyle();
			String appropriateness = episode.getApproprateness();
			
			vector.add(seriesName);
			vector.add(seasonNumber);
			vector.add(episodeNumber);
			vector.add(showName);
			vector.add(airDateString);
			vector.add(artStyle);
			vector.add(appropriateness);
			
			tableModel.addRow(vector);
		}
		
		repaint();
	}
	
	/**
	 * This will place the column name at the top to be able to identify each column And
	 * this this will fill the vectors with the results which will then be able to fill
	 * the table will the results
	 */
	private void displayFranchiseResults(List<Franchise> franchises)
	{
		Vector<String> columnNames = new Vector<>();
		
		columnNames.add("FranchiseID");
		columnNames.add("FranchiseName");
		
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (Franchise franchise : franchises)
		{
			Vector<String> vector = new Vector<String>();
			
			String ID = Integer.toString(franchise.getId());
			String name = franchise.getName();
			vector.add(ID);
			vector.add(name);
			
			tableModel.addRow(vector);
		}
		
		repaint();
	}
	
	/**
	 * 
	 * This will place the column name at the top to be able to identify each column And
	 * this this will fill the vectors with the results which will then be able to fill
	 * the table will the results
	 * 
	 */
	private void displaySeasonResults(List<Season> seasons)
	{
		Vector<String> columnNames = new Vector<>();
		
		columnNames.add("SeriesName");
		columnNames.add("SeasonNumber");
		columnNames.add("ShowName");
		columnNames.add("AirDate");
		columnNames.add("FinishDate");
		columnNames.add("Appropriateness");
		
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (Season season : seasons)
		{
			Vector<String> vector = new Vector<String>();
			
			// TODO: cleanup
			String seriesName = season.getSeriesName();
			String seasonNumber = Integer.toString(season.getSeasonNumber());
			String showName = season.getName();
			String airDate = "";
			if (season.getAirDate() != null)
			{
				airDate = formatDate(season.getAirDate());
			}
			String finishDate = "";
			if (season.getFinishDate() != null)
			{
				finishDate = formatDate(season.getFinishDate());
			}
			
			String appropriateness = season.getAppropriateness();
			vector.add(seriesName);
			vector.add(seasonNumber);
			vector.add(showName);
			vector.add(airDate);
			vector.add(finishDate);
			vector.add(appropriateness);
			
			tableModel.addRow(vector);
		}
		
		repaint();
	}
	
	/**
	 * 
	 * It will place the columns Names at the top of table to be able to identify each
	 * column
	 * 
	 */
	private void displayStudioResults(List<Studio> studios)
	{
		Vector<String> columnNames = new Vector<>();
		
		columnNames.add("StudioName");
		columnNames.add("StartDate");
		columnNames.add("CloseDate");
		
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (Studio studio : studios)
		{
			Vector<String> vector = new Vector<String>();
			
			String studioName = studio.getName();
			String studioStartDate = formatDate(studio.getStartDate());
			String studioCloseDate = formatDate(studio.getCloseDate());
			
			vector.add(studioName);
			vector.add(studioStartDate);
			vector.add(studioCloseDate);
			
			tableModel.addRow(vector);
		}
		
		repaint();
	}
	
	/**
	 * Returns a string representing the given date in the format: dd/mm/yyyy, or an empty
	 * String if the date is null
	 * 
	 * @param date
	 * @return A formatted dateString
	 */
	private String formatDate(Date date)
	{
		String defaultDateString = "";
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		
		return (date == null) ? defaultDateString : format.format(date);
	}
	
	private void updateSubSearchTerms()
	{
		Map<String, Search> map = searchFunctions.get(tableList.getSelectedItem());
		String[] subSearchTerms = map.keySet().toArray(new String[map.size()]);
		ComboBoxModel<String> model = new DefaultComboBoxModel<>(subSearchTerms);
		searchBy.setModel(model);
		repaint();
	}
	
	/**
	 * this will initialize most of objects that we will be using, this creates the all
	 * swing components and give listeners to where it is needed it places the layout of
	 * the components being used.
	 */
	private void init()
	{
		//@formatter:off
		addSearch("Character", "By Name", 		this::searchCharactersByName);
		addSearch("Character", "By Archetype", 	this::searchCharactersByArchetype);
		addSearch("Character", "By Gender", 	this::searchCharactersByGender);
		addSearch("Character", "By Hair Color", this::searchCharactersByHairColour);
		addSearch("Character", "List All", 		(s) -> searchAllCharacters());
		
		addSearch("Franchise", "By Name", 		this::searchFranchisesByName);
		addSearch("Franchise", "List All", 		(s) -> searchAllFranchises());
		
		addSearch("Episode", "By Name", 		this::searchEpisodesByName);
		addSearch("Episode", "List All", 		(s) -> searchAllEpisodes());
		
		addSearch("Season", "By Series Name", 	this::searchSeasonsBySeriesName);
		addSearch("Season", "By Genre", 		this::searchSeasonsByGenre);
		addSearch("Season", "By Year", 			this::searchSeasonsByYear);
		addSearch("Season", "List All", 		(s) -> searchAllSeasons());
		
		// TODO: add Person Searches
		/*
		addSearch("Person", "By Name", 			function);
		addSearch("Person", "Seasons Acted In", function);
		addSearch("Person", "Played Character", function);
		addSearch("Person", "Archetypes", 		function);
		addSearch("Person", "List All", 		function);
		*/
		
		addSearch("Studio", "By Name", 			this::searchStudiosByName);
		addSearch("Studio", "List All", 		(s) -> searchAllStudios());
		//@formatter:on
		
		setOpaque(false);
		addImageBackGround();
		populateListOfTableArray();
		
		JLabel titleLabel = new JLabel("Anime Database");
		Set<String> primarySearchTerms = searchFunctions.keySet();
		String[] tableListArray = primarySearchTerms
				.toArray(new String[primarySearchTerms.size()]);
		tableList = new JComboBox<>(tableListArray);
		searchBarTextField = new JTextField();
		JButton searchButton = new JButton("Search");
		JButton logoutButton = new JButton("Logout");
		JButton profileButton = new JButton("Profile");
		JButton goToUpdateButton = new JButton("Update Database");
		tableModel = new DefaultTableModel();
		
		selectedItemPictureLabel = new JLabel();
		selectedItemInfoLabel = new JLabel();
		Component resultsTable = new JTable(tableModel);
		resultsTable = new JScrollPane(resultsTable);
		
		searchBy = new JComboBox<>();
		updateSubSearchTerms();
		
		tableList.addActionListener(new FunctionalListener(this::updateSubSearchTerms));
		searchButton.addActionListener(new SearchListener());
		
		ActionListener listener = new FunctionalListener(client::showUpdateDatabase);
		goToUpdateButton.addActionListener(listener);
		
		listener = new FunctionalListener(client::showProfile);
		profileButton.addActionListener(listener);
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Session.logout();
				client.showLogin();
			}
		});
		
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
		
		profileButton.setBounds(30, 30, 100, 30);
		add(profileButton);
		
		resultsTable.setBounds(50, 200, 900, 250);
		add(resultsTable);
		
		selectedItemPictureLabel.setBounds(350, 200, 150, 200);
		add(selectedItemPictureLabel);
		
		selectedItemInfoLabel.setBounds(510, 200, 210, 200);
		add(selectedItemInfoLabel);
		
		goToUpdateButton.setBounds(140, 30, 200, 30);
		add(goToUpdateButton);
	}
	
	/**
	 * this will place the results of the search and place them into a vector which will
	 * then in turn places them into the table to display the results to the users
	 * 
	 * @param character
	 */
	private void parseCharacter(Character character)
	{
		Vector<String> vector = new Vector<String>();
		String name = character.getName();
		String gender = character.getGender().toString();
		String archetype = character.getArchetype();
		String hairColor = character.getHairColor();
		String age = Integer.toString(character.getAge());
		
		vector.add(name);
		vector.add(gender);
		vector.add(archetype);
		vector.add(hairColor);
		vector.add(age);
		rowValues.add(vector);
	}
	
	/**
	 * this will grab the season results and place them into a vector which will then in
	 * turn place them into the table to display the results
	 * 
	 * @param season
	 */
	private void parseSeason(Season season)
	{
		Vector<String> vector = new Vector<String>();
		String seriesName = season.getSeriesName();
		String seasonNumber = Integer.toString(season.getSeasonNumber());
		String showName = season.getName();
		String airDate = "";
		if (season.getAirDate() != null)
		{
			airDate = formatDate(season.getAirDate());
		}
		String finishDate = "";
		if (season.getFinishDate() != null)
		{
			finishDate = formatDate(season.getFinishDate());
		}
		
		String appropriateness = season.getAppropriateness();
		vector.add(seriesName);
		vector.add(seasonNumber);
		vector.add(showName);
		vector.add(airDate);
		vector.add(finishDate);
		vector.add(appropriateness);
		rowValues.add(vector);
	}
	
	/**
	 * 
	 * This will place the column name at the top to be able to identify each column And
	 * this this will fill the vectors with the results which will then be able to fill
	 * the table will the results
	 * 
	 */
	private void personSearches()
	{
		if (searchBy.getSelectedItem().toString().equals("By Name"))
		{
			searchResultsOfPerson = DAOCollection.getPeopleDao().findPerson(
					searchBarTextField.getText());
			columnNames.add("PersonID");
			columnNames.add("Name");
			
			tableModel.setColumnIdentifiers(columnNames);
			
			for (Person person : searchResultsOfPerson)
			{
				Vector<String> vector = new Vector<String>();
				String personId = Integer.toString(person.getID());
				String name = person.getName();
				
				vector.add(personId);
				vector.add(name);
				
				rowValues.add(vector);
			}
		}
		else if (searchBy.getSelectedItem().toString().equals("Seasons Acted In"))
		{
			columnNames.add("SeriesName");
			columnNames.add("SeasonNumber");
			columnNames.add("ShowName");
			columnNames.add("AirDate");
			columnNames.add("FinishDate");
			columnNames.add("Appropriateness");
			tableModel.setColumnIdentifiers(columnNames);
			searchResultsOfPerson = DAOCollection.getPeopleDao().findPerson(
					searchBarTextField.getText());
			
			if (searchResultsOfPerson.size() != 0)
			{
				JOptionPane
						.showMessageDialog(
								client,
								"Multiple persons' with the same name!!!\nEach person is seperated by ======.",
								"Multiple people with the same name",
								JOptionPane.WARNING_MESSAGE);
				
				for (Person person : searchResultsOfPerson)
				{
					searchResultsOfSeason = DAOCollection.getPeopleDao()
							.getSeasonsActedBy(person);
					for (Season season : searchResultsOfSeason)
					{
						parseSeason(season);
					}
					seperator(6);
				}
			}
		}
		else if (searchBy.getSelectedItem().toString().equals("Played Character"))
		{
			columnNames.add("Name");
			columnNames.add("Gender");
			columnNames.add("Archetype");
			columnNames.add("Hair Color");
			columnNames.add("Age");
			tableModel.setColumnIdentifiers(columnNames);
			searchResultsOfPerson = DAOCollection.getPeopleDao().findPerson(
					searchBarTextField.getText());
			if (searchResultsOfPerson.size() != 0)
			{
				if (searchResultsOfPerson.size() > 1)
				{
					JOptionPane
							.showMessageDialog(
									client,
									"Multiple persons' with the same name!!!\nEach person is seperated by ======.",
									"Multiple people with the same name",
									JOptionPane.WARNING_MESSAGE);
				}
				
				for (Person person : searchResultsOfPerson)
				{
					searchResultsOfCharacter = DAOCollection.getPeopleDao()
							.getCharactersActedBy(person);
					for (Character character : searchResultsOfCharacter)
					{
						parseCharacter(character);
					}
					seperator(5);
				}
			}
			
		}
		else if (searchBy.getSelectedItem().toString().equals("Archetype Distribution"))
		{
			columnNames.add("Archetype");
			columnNames.add("Number of instances of being that Archetype");
			tableModel.setColumnIdentifiers(columnNames);
			searchResultsOfPerson = DAOCollection.getPeopleDao().findPerson(
					searchBarTextField.getText());
			if (searchResultsOfPerson.size() != 0)
			{
				if (searchResultsOfPerson.size() > 1)
				{
					JOptionPane
							.showMessageDialog(
									client,
									"Multiple persons' with the same name!!!\nEach person is seperated by ======.",
									"Multiple people with the same name",
									JOptionPane.WARNING_MESSAGE);
				}
				for (Person person : searchResultsOfPerson)
				{
					Map<String, Integer> countingArchetype = DAOCollection.getPeopleDao()
							.getArchetypeDistributionOf(person);
					for (String key : countingArchetype.keySet())
					{
						vector = new Vector<String>();
						String insertedKey = key;
						String count = Integer.toString(countingArchetype.get(key));
						vector.add(insertedKey);
						vector.add(count);
						rowValues.add(vector);
					}
					
					seperator(2);
				}
			}
		}
		else
		{
			searchResultsOfPerson = DAOCollection.getPeopleDao().listAll();
			columnNames.add("PersonID");
			columnNames.add("Name");
			
			tableModel.setColumnIdentifiers(columnNames);
			
			for (Person person : searchResultsOfPerson)
			{
				Vector<String> vector = new Vector<String>();
				String personId = Integer.toString(person.getID());
				String name = person.getName();
				
				vector.add(personId);
				vector.add(name);
				
				rowValues.add(vector);
			}
		}
		repaint();
		tableModel.setDataVector(rowValues, columnNames);
	}
	
	private void searchAllCharacters()
	{
		CharacterDao dao = DAOCollection.getCharacterDao();
		List<Character> results = dao.listAll();
		displayCharacterResults(results);
	}
	
	private void searchAllEpisodes()
	{
		EpisodeDao dao = DAOCollection.getEpisodeDao();
		List<Episode> results = dao.listAll();
		displayEpisodeResults(results);
	}
	
	private void searchAllFranchises()
	{
		FranchiseDao dao = DAOCollection.getFranchiseDao();
		List<Franchise> results = dao.listAll();
		displayFranchiseResults(results);
	}
	
	private void searchAllSeasons()
	{
		SeasonDao dao = DAOCollection.getSeasonDao();
		List<Season> results = dao.listAll();
		displaySeasonResults(results);
	}
	
	private void searchAllStudios()
	{
		StudioDao dao = DAOCollection.getStudioDao();
		List<Studio> results = dao.listAll();
		displayStudioResults(results);
	}
	
	private void searchCharactersByArchetype(String searchTerm)
	{
		CharacterDao dao = DAOCollection.getCharacterDao();
		List<Character> results = dao.findCharactersByArchetype(searchTerm);
		displayCharacterResults(results);
	}
	
	private void searchCharactersByGender(String searchTerm)
	{
		Gender gender = Gender.valueOf(searchTerm);
		CharacterDao dao = DAOCollection.getCharacterDao();
		List<Character> results = dao.findCharactersByGender(gender);
		displayCharacterResults(results);
	}
	
	private void searchCharactersByHairColour(String searchTerm)
	{
		CharacterDao dao = DAOCollection.getCharacterDao();
		List<Character> results = dao.findCharactersByHairColour(searchTerm);
		displayCharacterResults(results);
	}
	
	private void searchCharactersByName(String searchTerm)
	{
		CharacterDao dao = DAOCollection.getCharacterDao();
		List<Character> results = dao.findCharactersByName(searchTerm);
		displayCharacterResults(results);
	}
	
	private void searchEpisodesByName(String searchTerm)
	{
		EpisodeDao dao = DAOCollection.getEpisodeDao();
		List<Episode> results = dao.findEpisode(searchTerm);
		displayEpisodeResults(results);
	}
	
	private void searchFranchisesByName(String searchTerm)
	{
		FranchiseDao dao = DAOCollection.getFranchiseDao();
		List<Franchise> results = new LinkedList<>();
		Franchise result = dao.findFranchise(searchTerm);
		results.add(result);
		displayFranchiseResults(results);
	}
	
	private void searchSeasonsByGenre(String searchTerm)
	{
		SeasonDao dao = DAOCollection.getSeasonDao();
		List<Season> results = dao.findSeasonsByGenre(searchTerm);
		displaySeasonResults(results);
	}
	
	private void searchSeasonsBySeriesName(String searchTerm)
	{
		SeasonDao dao = DAOCollection.getSeasonDao();
		List<Season> results = dao.findSeasonsBySeriesName(searchTerm);
		displaySeasonResults(results);
	}
	
	private void searchSeasonsByYear(String searchTerm)
	{
		SeasonDao dao = DAOCollection.getSeasonDao();
		int year = Integer.parseInt(searchTerm);
		List<Season> results = dao.seasonsByAirYear(year);
		displaySeasonResults(results);
	}
	
	private void searchStudiosByName(String searchTerm)
	{
		StudioDao dao = DAOCollection.getStudioDao();
		List<Studio> results = new LinkedList<>();
		Studio result = dao.findStudio(searchTerm);
		results.add(result);
		displayStudioResults(results);
	}
	
	/**
	 * This will separate the results should there be an instances of the same name of
	 * Persons
	 * 
	 * @param length
	 */
	private void seperator(int length)
	{
		vector = new Vector<>();
		String dashes = "=============";
		for (int i = 0; i < length; i++)
		{
			vector.add(dashes);
		}
		rowValues.add(vector);
	}
}
