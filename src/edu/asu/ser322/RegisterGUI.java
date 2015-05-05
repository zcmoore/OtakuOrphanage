package edu.asu.ser322;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
	
	private JTextField enterUserNameTextField;
	private JPasswordField enterPasswordTextField;
	
	private JButton becomeOtakuButton;
	private JButton backToLogin;
	
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
		Font terrorFont;
		try
		{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			terrorFont = Font.createFont(Font.TRUETYPE_FONT, new File("terror_pro.ttf"));
			ge.registerFont(terrorFont);
			terrorFont = terrorFont.deriveFont(16.0f);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			terrorFont = getFont();
		}
		
		setOpaque(false);
		addImageBackGround();
		
		userDao = DAOCollection.getUserDao();
		
		String registerPageTitleLabel = "<html>\n"
				+ "<font><font color=red>Register</font>";
		String enterUserNameLabelText = "<html>\n"
				+ "<font size=+1><font color=red>Enter Username:</font>";
		String enterPasswordLabelText = "<html>\n"
				+ "<font size=+1><font color=red>Enter Password:</font>";
		String becomeOtakuButtonText = "<html>\n"
				+ "<center><font color=red>Welcome to the Orphanage</font></center>";
		String backToLoginButtonText = "<html>\n" + "<font color=red>Back</font>";
		
		RegisterPageTitle = new JLabel(registerPageTitleLabel);
		RegisterPageTitle.setFont(terrorFont.deriveFont(48.0f));
		enterUserNameLabel = new JLabel(enterUserNameLabelText);
		enterPasswordLabel = new JLabel(enterPasswordLabelText);
		
		enterUserNameTextField = new JTextField();
		enterPasswordTextField = new JPasswordField();
		becomeOtakuButton = new JButton(becomeOtakuButtonText);
		becomeOtakuButton.setBorder(BorderFactory.createEmptyBorder());
		becomeOtakuButton.setContentAreaFilled(false);
		becomeOtakuButton.setHorizontalTextPosition(JButton.CENTER);
		becomeOtakuButton.setHorizontalAlignment(JButton.CENTER);
		becomeOtakuButton.setFont(terrorFont.deriveFont(20.0f));
		
		backToLogin = new JButton(backToLoginButtonText);
		backToLogin.setBorder(BorderFactory.createEmptyBorder());
		backToLogin.setContentAreaFilled(false);
		backToLogin.setHorizontalTextPosition(JButton.CENTER);
		backToLogin.setHorizontalAlignment(JButton.CENTER);
		backToLogin.setFont(terrorFont.deriveFont(32.0f));
		
		becomeOtakuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String username = enterUserNameTextField.getText();
				String password = String.valueOf(enterPasswordTextField.getPassword());
				password = password.trim();
				
				if (userDao.userExists(username))
				{
					JOptionPane.showMessageDialog(client,
							"User Exists,\nPlease choose another Username",
							"User exists", JOptionPane.WARNING_MESSAGE);
				}
				else if (password == null || password.trim().length() <= 0)
				{
					JOptionPane.showMessageDialog(client,
							"Username is blank,\nPlease enter a Username",
							"Username Blank", JOptionPane.WARNING_MESSAGE);
				}
				else if (!userDao.userExists(username) && password.trim().length() == 0)
				{
					JOptionPane.showMessageDialog(client,
							"You need a Password,\nPlease enter a password",
							"Password Blank", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					// TODO: initialize user with Waifu
					User user = new User(username, password, null);
					UserDao dao = DAOCollection.getUserDao();
					dao.addUser(user);
					Session.login(username, password);
					
					client.showMainMenu();
				}
			}
		});
		
		backToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				client.showLogin();
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
		
		// enterWaifuLabel.setBounds(500, 200, 150, 30);
		// add(enterWaifuLabel);
		
		// enterWaifuTextField.setBounds(560, 200, 150, 30);
		// add(enterWaifuTextField);
		
		becomeOtakuButton.setBounds(1100, 580, 150, 75);
		add(becomeOtakuButton);
		
		backToLogin.setBounds(50, 580, 150, 75);
		add(backToLogin);
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
