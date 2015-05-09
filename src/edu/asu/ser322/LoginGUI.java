package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * Login page Panel
 * to activate a session for the current user being logged in
 * 
 * @author Benjamin Paothatat
 * @author Cuahuctemoc Osorio
 * @author Moore, Zachary
 */
@SuppressWarnings("serial")
public class LoginGUI extends JPanel
{
	private JLabel animeDatabaseLogin;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JButton registerButton;
	public static final String MAINMENU = "1";
	
	private BufferedImage img;
	
	Client client;
	MainMenuGUI mainMenu;
	
	public LoginGUI(Client client)
	{
		this.client = client;
		init();
		layout();
	}
	/**
	 * this will initialize each component of what we have
	 * and give the listeners to the buttons
	 */
	private void init()
	{
		setOpaque(false);
		addImageBackGround();
		
		String animeDatabaseLoginText = "<html>\n"
				+ "<font size=+4><font color=#00FFFF>Otaku Orphanage</font>";
		String userLabelText = "<html>\n"
				+ "<font size=+2><font color=red>Username:</font>";
		String passwordLabelText = "<html>\n"
				+ "<font size=+2><font color=red>Password:</font>";
		
		animeDatabaseLogin = new JLabel(animeDatabaseLoginText);
		userLabel = new JLabel(userLabelText);
		usernameTextField = new JTextField();
		passwordLabel = new JLabel(passwordLabelText);
		passwordTextField = new JPasswordField();
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String username = usernameTextField.getText();
				String password = String.valueOf(passwordTextField.getPassword());
				password = password.trim();
				
				if (Session.login(username, password))
				{
					passwordTextField.setText("");
					client.showMainMenu();
				}
				else
				{
					JOptionPane.showMessageDialog(client,
							"This is not the correct login Information, please try again",
							"Wrong Credentials", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				client.showRegisterPanel();
			}
		});
	}
	
	/**
	 * this sets the layout of the Login page.
	 */
	public void layout()
	{
		setLayout(null);
		
		animeDatabaseLogin.setBounds(480, 20, 500, 80);
		add(animeDatabaseLogin);
		
		userLabel.setBounds(100, 180, 130, 30);
		add(userLabel);
		
		usernameTextField.setBounds(240, 180, 200, 30);
		add(usernameTextField);
		
		passwordLabel.setBounds(100, 220, 130, 30);
		add(passwordLabel);
		
		passwordTextField.setBounds(240, 220, 200, 30);
		add(passwordTextField);
		
		loginButton.setBounds(900, 580, 150, 75);
		add(loginButton);
		
		registerButton.setBounds(1100, 580, 150, 75);
		add(registerButton);
	}
	
	/**
	 * this will load the image into a buffer which will be used to be the back ground
	 */
	public void addImageBackGround()
	{
		try
		{
			img = ImageIO.read(new File("rec/LoginBackground.jpg"));
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
	
	/**
	 * This will paint what is in the buffer to the background of the Panel
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
}
