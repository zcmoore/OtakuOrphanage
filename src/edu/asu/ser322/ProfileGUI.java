package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import edu.asu.ser322.data.model.User;

public class ProfileGUI extends JPanel
{
	private JLabel imageOfUser;
	private JLabel userNameLabel;
	
	private JTable userWatchTable;
	private JScrollPane scp;
	
	private JTextField searchBarField;
	private JButton searchButton;
	private JButton backButton;
	private BufferedImage img;
	
	private List<User> searchWatches;
	
	Client client;
	
	public ProfileGUI(Client client)
	{
		this.client = client;
		init();
	}
	
	public void init()
	{
		imageOfUser = new JLabel();
		userNameLabel = new JLabel();
		
		userWatchTable = new JTable();
		scp = new JScrollPane(userWatchTable);
		
		searchBarField = new JTextField();
		searchButton = new JButton();
		backButton = new JButton();
	}
	
	public void buttonActions()
	{
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				client.showMainMenu();
			}
			
		});
	}
	
	public void layout()
	{
		//imageOfUser.setBounds(30, 30, 100, 200);
		//add(imageOfUser);
		
		//userNameLabel.setBounds(150, 30, 150, 80);
		//add(userNameLabel);
		
		scp.setBounds(30, 30, 900, 450);
		add(scp);
		
		//searchBarField.setBounds(970, 60, 200, 30);
		//add(searchBarField);
		
		//searchButton.setBounds(1180, 60, 80, 30);
		//add(searchButton);
	}
	public void addImageBackGround()
	{
		try
		{
			img = ImageIO.read(new File("rec/ProfileBackground.jpg"));
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
