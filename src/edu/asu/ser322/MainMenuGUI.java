package edu.asu.ser322;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuGUI extends JPanel
{
	Client client;
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
