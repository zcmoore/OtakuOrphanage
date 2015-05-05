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

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.UserDao;
import edu.asu.ser322.data.model.User;

/**
 * 
 * @author Cuahuc
 * @author Moore, Zachary
 *
 */

@SuppressWarnings("serial")
public class RegisterGUI extends JPanel
{
	
	private JLabel RegisterPageTitle;
	private JLabel enterUserNameLabel;
	private JLabel enterPasswordLabel;
	private JLabel enterWaifuLabel;
	
	private JTextField enterUserNameTextField;
	private JPasswordField enterPasswordTextField;
	private JTextField enterWaifuTextField;
	
	private JButton becomeOtakuButton;
	
	private UserDao userDao;
	Client client;
	
	private BufferedImage img;
	
	public RegisterGUI(Client client)
	{
		this.client = client;
		init();
		layout();
	}
	
	private void init()
	{
		setOpaque(false);
		addImageBackGround();
		
		userDao = DAOCollection.getUserDao();
		
		String registerPageTitleLabel = "<html>\n"
				+ "<font size=+4><font color=#00FFFF>Register</font>";
		String enterUserNameLabelText = "<html>\n"
				+ "<font size=+1><font color=red>Enter Username:</font>";
		String enterPasswordLabelText = "<html>\n"
				+ "<font size=+1><font color=red>Enter Password:</font>";
		String enterWaifuLabelText = "<html>\n"
				+ "<font size=+1><font color=red>Waifu:</font>";
		String becomeOtakuButtonText = "<html>\n"
				+ "<font color=red>Welcome to the Orphanage</font>";
		
		RegisterPageTitle = new JLabel(registerPageTitleLabel);
		enterUserNameLabel = new JLabel(enterUserNameLabelText);
		enterPasswordLabel = new JLabel(enterPasswordLabelText);
		enterWaifuLabel = new JLabel(enterWaifuLabelText);
		
		enterUserNameTextField = new JTextField();
		enterPasswordTextField = new JPasswordField();
		// TODO: search for own, or select in combo box from data?
		enterWaifuTextField = new JTextField();
		becomeOtakuButton = new JButton(becomeOtakuButtonText);
		
		becomeOtakuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String username = enterUserNameTextField.getText();
				String password = String.valueOf(enterPasswordTextField.getPassword());
				password = password.trim();
				
				if (userDao.userExists(username))
				{
					JOptionPane.showMessageDialog(client,
							"User Exists,\nPlease choose another Username");
				}
				else if (password == null || password.trim().length() <= 0)
				{
					JOptionPane.showMessageDialog(client,
							"You need a Password,\nPlease enter a password");
				}
				else
				{
					// TODO: account for Waifu
					User user = new User(username, password, null);
					UserDao dao = DAOCollection.getUserDao();
					dao.addUser(user);
					Session.login(username, password);
					
					client.showMainMenu();
				}
			}
		});
	}
	
	public void layout()
	{
		setLayout(null);
		
		RegisterPageTitle.setBounds(580, 20, 500, 80);
		add(RegisterPageTitle);
		
		enterUserNameLabel.setBounds(400, 120, 150, 30);
		add(enterUserNameLabel);
		
		enterUserNameTextField.setBounds(560, 120, 150, 30);
		add(enterUserNameTextField);
		
		enterPasswordLabel.setBounds(400, 160, 150, 30);
		add(enterPasswordLabel);
		
		enterPasswordTextField.setBounds(560, 160, 150, 30);
		add(enterPasswordTextField);
		
		enterWaifuLabel.setBounds(500, 200, 150, 30);
		add(enterWaifuLabel);
		
		enterWaifuTextField.setBounds(560, 200, 150, 30);
		add(enterWaifuTextField);
		
		becomeOtakuButton.setBounds(1100, 580, 150, 75);
		add(becomeOtakuButton);
	}
	
	public void addImageBackGround()
	{
		try
		{
			img = ImageIO.read(new File("rec/RegisterBackground.jpg"));
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
