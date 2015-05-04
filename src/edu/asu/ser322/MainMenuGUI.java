package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel
{
	Client client;
	
	private JLabel TitleLabel;
	private JLabel AccounInfoLabel;
	private JLabel pictureOfSelectedItem;
	private JLabel infoOfSelectedItem;
	
	private JComboBox ListOfTables;
	
	private JTextField searchBarTextField;
	
	private JButton Settings;
	private JButton SearchButton;
	
	private JList ListOfTitlesHolder;
	
	private ArrayList<String> ListOfTableArray = new ArrayList<String>();
	private ArrayList<String> ListOfTitles = new ArrayList<String>();
	
	private BufferedImage img;
	
	public MainMenuGUI(Client client)
	{
		this.client = client;
		init();
		layout();
	}
	
	private void init()
	{
		setOpaque(false);
		addImageBackGround();
		TitleLabel = new JLabel("Anime Database");
		
		loadInfoOnShows();
		
		populateListOfTableArray();
		ListOfTables = new JComboBox(ListOfTableArray.toArray());
		
		searchBarTextField = new JTextField();
		
		SearchButton = new JButton("Search");
		Settings = new JButton("Settings");
		
		ListOfTitlesHolder.setVisible(false);
		
		pictureOfSelectedItem = new JLabel();
		infoOfSelectedItem = new JLabel();
	}
	
	public void layout()
	{
		TitleLabel.setBounds(600, 20, 300, 30);
		add(TitleLabel);
		
		ListOfTables.setBounds(810, 60, 150, 30);
		add(ListOfTables);
		
		searchBarTextField.setBounds(970, 60, 200, 30);
		add(searchBarTextField);
		
		SearchButton.setBounds(1180, 60, 80, 30);
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Populate the JList with search results
				ListOfTitlesHolder.setVisible(true);
				// loadInfoOnShows();
			}
		});
		add(SearchButton);
		
		// TODO: function for the settings button has yet to be defined
		// Settings.setBounds(30, 30, 100, 30);
		// add(Settings);
		
		ListOfTitlesHolder.setBounds(50, 200, 250, 400);
		add(ListOfTitlesHolder);
		
		pictureOfSelectedItem.setBounds(350, 200, 150, 200);
		add(pictureOfSelectedItem);
		
		infoOfSelectedItem.setBounds(510, 200, 210, 200);
		add(infoOfSelectedItem);
	}
	
	public void populateListOfTitles(String title)
	{
		ListOfTitles.add(title);
	}
	
	public void setInfoOfSelectedItem(String info)
	{
		final String startTag = "<html><body style='width: ";
		final String endTag = "px'>";
		String infoOfShow = startTag + "200" + endTag + info;
		infoOfSelectedItem.setText(infoOfShow);
	}
	
	public void setPictureOfSelectedItem(String picturePath)
	{
		ImageIcon icon = createImageIcon(picturePath);
		pictureOfSelectedItem.setIcon(icon);
	}
	
	public ImageIcon createImageIcon(String path)
	{
		java.net.URL imageURL = MainMenuGUI.class.getResource(path);
		ImageIcon returnIcon;
		if (imageURL != null)
		{
			return new ImageIcon(imageURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public void loadInfoOnShows()
	{
		ListOfTitlesHolder = new JList(ListOfTableArray.toArray());
		ListOfTitlesHolder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListOfTitlesHolder.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				// TODO: Here is where we will get the info for the titles that we have
				// selected
				
				// setInfoOfSelectedItem(String of info);
				// setPictureOfSelectedItem(String path to image);
				
			}
		});
		
	}
	
	public void populateListOfTableArray()
	{
		ListOfTableArray.add("Character");
		ListOfTableArray.add("Episode");
		ListOfTableArray.add("Franchise");
		ListOfTableArray.add("People");
		ListOfTableArray.add("Seasons");
		ListOfTableArray.add("Series");
		ListOfTableArray.add("Studio");
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
