package edu.asu.ser322;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel
{
	Client client;

    private JLabel TitleLabel;
    private JLabel AccounInfoLabel;

    private JComboBox ListOfTables;
    private ArrayList<String> ListOfTableArray = new ArrayList<String>();
    private JTextField searchBarTextField;
    private JButton Settings;
    private JButton SearchButton;
    
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
    	
    	populateListOfTableArray();
    	ListOfTables = new JComboBox(ListOfTableArray.toArray());
    	
       	searchBarTextField = new JTextField();
    	
    	SearchButton = new JButton("Search");
    	Settings = new JButton("Settings");
    	
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
    	add(SearchButton);
    	
    	Settings.setBounds(30, 30, 100, 30);
    	add(Settings);
    	
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
          g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
        }
    }
}
