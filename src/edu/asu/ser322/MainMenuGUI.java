package edu.asu.ser322;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel
{
	Client client;

    private JLabel TitleLabel;
    private JLabel AccounInfoLabel;

    private JComboBox ListOfTables;
    private ArrayList<String> LOT = ArrayList<String>();

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
    	this.setBackground(Color.BLACK);
    }
    
    public void layout()
    {
    	
    }
}
