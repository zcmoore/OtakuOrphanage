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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.User;

public class ProfileGUI extends JPanel
{
	private JLabel imageOfUser;
	public JLabel userNameLabel;
	
	private JTable userWatchTable;
	private JScrollPane scp;
	
	private JTextField searchBarField;
	private JButton searchButton;
	private JButton backButton;
	private JButton deleteAccount;
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
		setOpaque(false);
		addImageBackGround();
		layout();
		imageOfUser = new JLabel();
		userNameLabel = new JLabel();
		
		userWatchTable = new JTable();
		scp = new JScrollPane(userWatchTable);
		
		searchBarField = new JTextField();
		searchButton = new JButton("Search");
		backButton = new JButton("Back");
		deleteAccount = new JButton("Delete Account");
		
		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int reply = JOptionPane.showConfirmDialog(client,
						"Are you sure you want to delete your account?", "Delete User",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					boolean check = DAOCollection.getUserDao().deleteUser(
							Session.getActiveUser().getUsername());
					Session.logout();
					client.showLogin();
				}
				else if (reply == JOptionPane.NO_OPTION)
				{
				}
				
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				;
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				client.showMainMenu();
			}
		});
		
		scp.setBounds(325, 10, 600, 300);
		add(scp);
		
		searchBarField.setBounds(500, 350, 200, 30);
		add(searchBarField);
		
		searchButton.setBounds(700, 350, 80, 30);
		add(searchButton);
		
		imageOfUser.setBounds(900, 580, 150, 75);
		add(imageOfUser);
		
		userNameLabel.setBounds(30, 30, 150, 75);
		add(userNameLabel);
		
		backButton.setBounds(1100, 580, 80, 30);
		add(backButton);
		
		deleteAccount.setBounds(30, 580, 150, 30);
		add(deleteAccount);
	}
	
	public void setTextStyle(String currentUser)
	{
		String usernameStyle = "<html>\n" + "<font size=+3><font color=#00ff00>"
				+ currentUser + "</font>";
		this.userNameLabel.setText(usernameStyle);
		repaint();
	}
	
	public void setPictureOfSelectedItem(String picturePath)
	{
		ImageIcon icon = createImageIcon(picturePath);
		imageOfUser.setIcon(icon);
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
	
	public void layout()
	{
		/*
		 * scp.setBounds(325, 10, 600, 300); add(scp);
		 * 
		 * searchBarField.setBounds(500, 350, 200, 30); add(searchBarField);
		 * 
		 * searchButton.setBounds(700, 350, 80, 30); add(searchButton);
		 * 
		 * imageOfUser.setBounds(900, 580, 150, 75); add(imageOfUser);
		 * 
		 * userNameLabel.setBounds(1100, 580, 150, 75); add(userNameLabel);
		 */
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
