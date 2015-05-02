package edu.asu.ser322;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.UserDao;
import edu.asu.ser322.data.model.User;

/**
 * 
 * 
 * @author Benjamin Paothatat
 * @author Cuahuctemoc Osorio
 */
@SuppressWarnings("serial")
public class LoginGUI extends JPanel // implements ActionListener
{
	protected JLabel animeDatabaseLogin;
	protected JLabel userLabel;
	protected JLabel passwordLabel;
	protected JTextField usernameTextField;
	protected JPasswordField passwordTextField;
	protected JButton loginButton;
	protected JButton registerButton;
	private UserDao userDao;
	public static final String MAINMENU = "1";
	Client client;
	MainMenuGUI mainMenu;

	
	// protected GroupLayout layout = new GroupLayout(this);
	
	public LoginGUI(Client client)
	{
		this.client = client;
		init();
		layout();
	}
	
	private void init()
	{
		userDao = DAOCollection.getUserDao();
		
		animeDatabaseLogin = new JLabel("Anime Database");
		userLabel = new JLabel("Username: ");
		usernameTextField = new JTextField();
		passwordLabel = new JLabel("Password: ");
		passwordTextField = new JPasswordField();
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		
	}
	
	public void layout()
	{
		setLayout(null);
		
		animeDatabaseLogin.setBounds(600, 20, 300, 30);
		add(animeDatabaseLogin);
		
		userLabel.setBounds(510, 200, 100, 30);
		add(userLabel);
		
		usernameTextField.setBounds(600, 200, 200, 30);
		add(usernameTextField);
		
		passwordLabel.setBounds(510, 250, 100, 30);
		add(passwordLabel);
		
		passwordTextField.setBounds(600, 250, 200, 30);
		add(passwordTextField);
		//FIXME: Implement the button differently
		loginButton.setBounds(300, 500, 150, 75);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(userDao.login(usernameTextField.getText(),passwordTextField.getText()))
				{
					//TODO: Change panel to main menu
					client.showMainMenu();
					System.out.println("Sign in successful.");
				}
			}
		});
		add(loginButton);
		
		registerButton.setBounds(900, 500, 150, 75);
		//TODO: Created register panel or take information from login page?
		// registerButton.addActionListener(this);
		add(registerButton);
	}
}
