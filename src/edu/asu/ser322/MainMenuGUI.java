package edu.asu.ser322;

import java.awt.Color;
import java.util.ArrayList;

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
    private String[] LOT = {"Characters", "Series"};

    private JTextField searchTextBar;

    private JButton Settings;
    private JButton SearchButton;

    public MainMenuGUI(Client client)
    {
    	this.client = client;
    	init();
    	layout();
    }
    
    private void init()
    {
    	TitleLabel = new JLabel("Anime Database");
    	Settings = new JButton();
    	SearchButton = new JButton();
    	ListOfTables = new JComboBox<String>(LOT);
    }
    
    public void layout()
    {
    	TitleLabel.setBounds(600, 20, 300, 30);
    	add(TitleLabel);
    	
    	Settings.setBounds(600, 20, 300, 30);
    	add(Settings);
    	
    	add(searchTextBar);
    	add(SearchButton);
    	ListOfTables.setSelectedIndex(0);
    	add(ListOfTables);
    }
}
