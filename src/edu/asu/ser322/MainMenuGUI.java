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
	private JList titleHolderList;
	private List<String> tableListData;
	private List<String> titleListData;
	private BufferedImage img;
	
	public MainMenuGUI(Client client)
	{
		this.client = client;
		this.tableListData = new ArrayList<>();
		this.titleListData = new ArrayList<>();
		
		init();
		layout();
	}
	
	private void init()
	{
		setOpaque(false);
		addImageBackGround();
		titleLabel = new JLabel("Anime Database");
		
		loadInfoOnShows();
		
		populateListOfTableArray();
		tableList = new JComboBox<>(tableListData.toArray());
		
		searchBarTextField = new JTextField();
		
		searchButton = new JButton("Search");
		logoutButton = new JButton("Logout");
		settingsButton = new JButton("Settings");
		
		titleHolderList.setVisible(false);
		
		selectedItemPictureLabel = new JLabel();
		selectedItemInfoLabel = new JLabel();
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Populate the JList with search results
				titleHolderList.setVisible(true);
				// loadInfoOnShows();
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
		
		titleHolderList.setBounds(50, 200, 250, 400);
		add(titleHolderList);
		
		selectedItemPictureLabel.setBounds(350, 200, 150, 200);
		add(selectedItemPictureLabel);
		
		selectedItemInfoLabel.setBounds(510, 200, 210, 200);
		add(selectedItemInfoLabel);
	}
	
	public void populateListOfTitles(String title)
	{
		titleListData.add(title);
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
	
	public void loadInfoOnShows()
	{
		titleHolderList = new JList<>(tableListData.toArray());
		titleHolderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		titleHolderList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				// TODO: Here is where we will get the info for the titles that
				// we have
				// selected
				
				// setInfoOfSelectedItem(String of info);
				// setPictureOfSelectedItem(String path to image);
				
			}
		});
		
	}
	
	public void populateListOfTableArray()
	{
		tableListData.add("Character");
		tableListData.add("Episode");
		tableListData.add("Franchise");
		tableListData.add("People");
		tableListData.add("Seasons");
		tableListData.add("Series");
		tableListData.add("Studio");
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
