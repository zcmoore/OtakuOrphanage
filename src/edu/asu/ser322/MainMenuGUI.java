package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	private JTable results;
	private List<String> ListOfEntities;
	private List<Character> searchResultsOfCharacter;
	private List<Franchise> searchResultsOfFranchise;
	private List<User> searchResultsOfUsers;
	private List<Season> searchResultsOfSeason;
<<<<<<< Updated upstream
	// private List<Episode> searchResultsOfEpisodes;
	// private List<Studio> searchResultsOfStudio;
=======
	private List<Episode> searchResultsOfEpisodes;
	private List<Studio> searchResultsOfStudio;
>>>>>>> Stashed changes
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
		populateListOfTableArray();
		tableList = new JComboBox<>(ListOfEntities.toArray());
		searchBarTextField = new JTextField();
		searchButton = new JButton("Search");
		logoutButton = new JButton("Logout");
		settingsButton = new JButton("Settings");
		tableModel = new DefaultTableModel();
		results = new JTable(tableModel);
<<<<<<< Updated upstream
		JScrollPane spTable = new JScrollPane(results);
=======
		spTable = new JScrollPane(results);
		//columnNames = new Vector<String>();
		//rowValues = new Vector<String>();
>>>>>>> Stashed changes
		
		selectedItemPictureLabel = new JLabel();
		selectedItemInfoLabel = new JLabel();
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Populate the JList with search results
				if (tableList.getSelectedItem().toString().equals("Character"))
				{
					clearAllVectors();
					if (DAOCollection.getCharacterDao().characterExists(
							DAOCollection.getCharacterDao()
									.findCharactersByName(searchBarTextField.getText())
									.get(0).getId()))
					{
						
					}
					else if (DAOCollection.getCharacterDao().characterExists(
							DAOCollection.getCharacterDao()
									.findCharactersByName(searchBarTextField.getText())
									.get(0).getId()))
					{
						
					}
					else if (DAOCollection.getCharacterDao().characterExists(
							DAOCollection.getCharacterDao()
									.findCharactersByName(searchBarTextField.getText())
									.get(0).getId()))
					{
						
					}
					else if (DAOCollection.getCharacterDao().characterExists(
							DAOCollection.getCharacterDao()
									.findCharactersByName(searchBarTextField.getText())
									.get(0).getId()))
					{
						
					}
					else if (DAOCollection.getCharacterDao().characterExists(
							DAOCollection.getCharacterDao()
									.findCharactersByName(searchBarTextField.getText())
									.get(0).getId()))
					{
						
					}
					else if (DAOCollection.getCharacterDao().characterExists(
							DAOCollection.getCharacterDao()
									.findCharactersByName(searchBarTextField.getText())
									.get(0).getId()))
					{
						
					}
					
					searchResultsOfCharacter = DAOCollection.getCharacterDao()
							.findCharactersByName(searchBarTextField.getText());
					
					if (searchResultsOfCharacter.size() != 0)
					{
						for (int j = 0; j < searchResultsOfCharacter.size(); j++)
						{
							;
						}
					}
					
					columnNames.add("CharacterID");
					columnNames.add("Name");
					columnNames.add("Gender");
					columnNames.add("Archetype");
					columnNames.add("Hair Color");
					columnNames.add("Birthday Day");
					tableModel.setColumnIdentifiers(columnNames);
					
					for (int i = 0; i < searchResultsOfCharacter.size(); i++)
					{
						String ID = Integer.toString(searchResultsOfCharacter.get(i)
								.getId());
						String name = searchResultsOfCharacter.get(i).getName();
						String gender = searchResultsOfCharacter.get(i).getGender()
								.toString();
						String archetype = searchResultsOfCharacter.get(i).getArchetype();
						String hairColor = searchResultsOfCharacter.get(i).getHairColor();
						String dob = searchResultsOfCharacter.get(i).getBirthDate()
								.toString();
						vector.add(ID);
						vector.add(name);
						vector.add(gender);
						vector.add(archetype);
						vector.add(hairColor);
						vector.add(dob);
						rowValues.add(vector);
					}
					
					repaint();
					tableModel.setDataVector(rowValues, columnNames);
				}
				else if (tableList.getSelectedItem().toString().equals("Franchise"))
				{
					clearAllVectors();
<<<<<<< Updated upstream
					searchResultsOfFranchise.add(DAOCollection.getFranchiseDao()
							.findFranchise(searchBarTextField.getText()));
				}
				else if (tableList.getSelectedItem().toString().equals("User"))
=======
					System.out.print(DAOCollection.getFranchiseDao().findFranchise(searchBarTextField.getText()) != Franchise.NULL_FRANCHISE);
					
					//searchResultsOfFranchise.add(DAOCollection.getFranchiseDao().findFranchise(searchBarTextField.getText()));
					//System.out.println(searchResultsOfFranchise.size());
					columnNames.add("FranchiseID");
					columnNames.add("FranchiseName");
			
					tableModel.setColumnIdentifiers(columnNames);
					
					//tableModel = new DefaultTableModel();
					/*
					for(int i = 0; i < searchResultsOfCharacter.size(); i++)
					{
						//int id = searchResultsOfCharacter.get(i).getId();
						String ID = Integer.toString(searchResultsOfFranchise.get(i).getId());
						String name = searchResultsOfFranchise.get(i).getName();
					    vector.add(ID);
					    vector.add(name);

					    rowValues.add(vector);
					}
					*/
					repaint();
					tableModel.setDataVector(rowValues, columnNames);
				}
				else if(tableList.getSelectedItem().toString().equals("Episode"))
>>>>>>> Stashed changes
				{
					clearAllVectors();
					

					searchResultsOfEpisodes = DAOCollection.getEpisodeDao().findEpisode(searchBarTextField.getText());
					columnNames.add("SeriesName");
					columnNames.add("SeasonNumber");
					columnNames.add("EpisodeNumber");
					columnNames.add("EpisodeName");
					columnNames.add("AirDate");
					columnNames.add("ArtStyle");
					columnNames.add("Appropriateness");
					
			
					tableModel.setColumnIdentifiers(columnNames);
					//ListIterator<Episode> listIterator = searchResultsOfEpisodes.listIterator()
					//tableModel = new DefaultTableModel();
					for(Episode episode: searchResultsOfEpisodes)
					{
						vector = new Vector<String>();
						String seriesName = episode.getSeriesName();
						String seasonNumber = Integer.toString(episode.getSeasonNumber());
						String episodeNumber = Integer.toString(episode.getEpisodeNumber());
						String showName = episode.getEpisodeName();
						String airDate = episode.getAirDate().toString();
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
					}
					
					repaint();
					tableModel.setDataVector(rowValues, columnNames);
				}
				else if (tableList.getSelectedItem().toString().equals("Seasons"))
				{
					clearAllVectors();
					
					searchResultsOfSeason = DAOCollection.getSeasonDao().findSeason(searchBarTextField.getText());
					columnNames.add("SeriesName");
					columnNames.add("SeasonNumber");
					columnNames.add("ShowName");
					columnNames.add("AirDate");
					columnNames.add("FinishDate");
					columnNames.add("Appropriateness");
					
			
					tableModel.setColumnIdentifiers(columnNames);
					
					//tableModel = new DefaultTableModel();
					for(Season season: searchResultsOfSeason)
					{
						vector = new Vector<String>();
						//int id = searchResultsOfCharacter.get(i).getId();
						String seriesName = season.getSeriesName();
						String seasonNumber = Integer.toString(season.getSeasonNumber());
						String showName = season.getName();
						String airDate = season.getAirDate().toString();
						String FinishDate = season.toString();
						String appropriateness = season.getAppropriateness();
					    vector.add(seriesName);
					    vector.add(seasonNumber);
					    vector.add(showName);
					    vector.add(airDate);
					    vector.add(FinishDate);
					    vector.add(appropriateness);
					    rowValues.add(vector);
					}
					
					repaint();
					tableModel.setDataVector(rowValues, columnNames);
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
		
<<<<<<< Updated upstream
		results.setBounds(50, 200, 600, 250);
		add(results);
=======
	   
		
		spTable.setBounds(50, 200, 900, 250);
		add(spTable);
>>>>>>> Stashed changes
		
		selectedItemPictureLabel.setBounds(50, 300, 150, 200);
		add(selectedItemPictureLabel);
		
		selectedItemInfoLabel.setBounds(210, 300, 210, 200);
		add(selectedItemInfoLabel);
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
		ListOfEntities.add("Franchise");
		ListOfEntities.add("Episode");
		ListOfEntities.add("Seasons");
		// ListOfEntities.add("Episode");
		// ListOfEntities.add("Studio");
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
