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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.asu.ser322.data.access.CharacterDao;
import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.EpisodeDao;
import edu.asu.ser322.data.access.FranchiseDao;
import edu.asu.ser322.data.access.PeopleDao;
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
	
	private DefaultTableModel tableModel;
	private Map<String, Map<String, Search>> searchFunctions = new LinkedHashMap<>();
	private BufferedImage img;
	
	private Client client;
	private JLabel selectedItemPictureLabel;
	private JLabel selectedItemInfoLabel;
	
	/** Selector for the primary search criteria (i.e. what entity is being searched) */
	private JComboBox<Object> tableList;
	
	/** Selector for the secondary search criteria (i.e. what criteria is being searched) */
	private JComboBox<String> searchBy;
	
	/** Input field for the search term */
	private JTextField searchBarTextField;
	
	/**
	 * constructor for the MainMenu Panel passes and client instance into here to connect
	 * each panel
	 * 
	 * @param client
	 */
	public MainMenuGUI(Client client)
	{
		this.client = client;
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
	
	private void displayArchetypeDistribution(Map<String, Integer> results)
	{
		Vector<String> columnNames = new Vector<>();
		
		columnNames.add("Archetype");
		columnNames.add("Number of Roles Played");
		
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (String key : results.keySet())
		{
			Vector<String> vector = new Vector<String>();
			
			int count = results.get(key);
			String value = Integer.toString(count);
			vector.add(key);
			vector.add(value);
			
			tableModel.addRow(vector);
		}
		
		repaint();
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
	
	private void displayPeopleResults(List<Person> people)
	{
		Vector<String> columnNames = new Vector<>();
		
		columnNames.add("PersonID");
		columnNames.add("Name");
		
		tableModel.setColumnIdentifiers(columnNames);
		tableModel.getDataVector().clear();
		
		for (Person person : people)
		{
			Vector<String> vector = new Vector<String>();
			
			String personId = Integer.toString(person.getID());
			String name = person.getName();
			
			vector.add(personId);
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
		
		// TODO: Reorganize Searches
		addSearch("Person", "By Name", 			this::searchPeopleByName);
		addSearch("Person", "Seasons Acted In", this::searchSeasonsByActor);
		addSearch("Person", "Played Character", this::searchCharactersByActor);
		addSearch("Person", "Archetypes", 		this::searchArchetypeDistributionByPerson);
		addSearch("Person", "List All", 		(s) -> searchAllPeople());
		
		addSearch("Studio", "By Name", 			this::searchStudiosByName);
		addSearch("Studio", "List All", 		(s) -> searchAllStudios());
		//@formatter:on
		
		setOpaque(false);
		addImageBackGround();
		
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
	
	private void searchAllPeople()
	{
		PeopleDao dao = DAOCollection.getPeopleDao();
		List<Person> results = dao.listAll();
		displayPeopleResults(results);
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
	
	private void searchArchetypeDistributionByPerson(String searchTerm)
	{
		// TODO: Clean up
		// TODO: Support Multiple Actors Found
		// TODO: Handle "no results"
		PeopleDao dao = DAOCollection.getPeopleDao();
		Person actor = dao.findPerson(searchTerm).get(0);
		Map<String, Integer> results = dao.getArchetypeDistributionOf(actor);
		displayArchetypeDistribution(results);
	}
	
	private void searchCharactersByActor(String searchTerm)
	{
		// TODO: Clean up
		// TODO: Support Multiple Actors Found
		// TODO: Handle "no results"
		PeopleDao dao = DAOCollection.getPeopleDao();
		Person actor = dao.findPerson(searchTerm).get(0);
		List<Character> results = dao.getCharactersActedBy(actor);
		displayCharacterResults(results);
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
	
	private void searchPeopleByCharacterActed(String searchTerm)
	{
		// TODO
		throw new UnsupportedOperationException("Not Yet Implemented");
	}
	
	private void searchPeopleByName(String searchTerm)
	{
		PeopleDao dao = DAOCollection.getPeopleDao();
		List<Person> results = dao.findPerson(searchTerm);
		displayPeopleResults(results);
	}
	
	private void searchSeasonsByActor(String searchTerm)
	{
		// TODO: Clean up
		// TODO: Support Multiple Actors Found
		// TODO: Handle "no results"
		PeopleDao dao = DAOCollection.getPeopleDao();
		Person actor = dao.findPerson(searchTerm).get(0);
		List<Season> results = dao.getSeasonsActedBy(actor);
		displaySeasonResults(results);
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
	
	private void updateSubSearchTerms()
	{
		Map<String, Search> map = searchFunctions.get(tableList.getSelectedItem());
		String[] subSearchTerms = map.keySet().toArray(new String[map.size()]);
		ComboBoxModel<String> model = new DefaultComboBoxModel<>(subSearchTerms);
		searchBy.setModel(model);
		repaint();
	}
}
