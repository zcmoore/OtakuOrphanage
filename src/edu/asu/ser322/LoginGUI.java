package edu.asu.ser322;
/**
 * @author Benjamin Paothatat, Tmoney 
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.asu.ser322.data.User;
import edu.asu.ser322.data.UserDaoSQL;

public class LoginGUI extends JPanel //implements ActionListener
{
	protected JLabel animeDatabaseLogin;
	protected JLabel userLabel;
	protected JLabel passwordLabel;
	protected JTextField usernameTextField;
	protected JTextField passwordTextField;
	protected JButton loginButton;
	protected JButton registerButton;
	UserDaoSQL sql = new UserDaoSQL();
	//protected GroupLayout layout = new GroupLayout(this);
	
	
    public LoginGUI()
    {
    	init();
    	layout();
    }
    
    private void init()
    {
    	animeDatabaseLogin = new JLabel("Anime Database");
    	userLabel = new JLabel("Username: ");
    	usernameTextField = new JTextField();
    	passwordLabel = new JLabel("Password: "); 
    	passwordTextField = new JTextField();
    	loginButton = new JButton("Login");
    	registerButton = new JButton("Register");
    	
    }
    
    public void layout()
    {
    	//animeDatabaseLogin.setBounds
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
    	
    	loginButton.setBounds(300, 500, 150, 75);
    	loginButton.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			int counter = 0;
    			counter++;
    			System.out.println(counter);
    			User user = sql.findUser(usernameTextField.getText());
    			System.out.println(user.getUsername());
    			System.out.println(user.getPassword());
    		}
    	});
    	add(loginButton);
    	
    	registerButton.setBounds(900, 500, 150, 75);
    	//registerButton.addActionListener(this);
    	add(registerButton);
    }

    /*
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == loginButton)
		{
			int counter = 0;
			counter++;
			System.out.println(counter);
			/*
			User user = sql.findUser(usernameTextField.getText());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			if(user == null)
			{
			    System.out.println("User not found");	
			}
			else
			{
			    if(user.getPassword() == passwordTextField.getText())	
			    {
			    	System.out.println("Login successful");
			    }
			    else
			    {
			    	System.out.println("Login failed");
			    	System.out.println("user password: " + user.getPassword());
			    }
			}
			
		}
		else if(e.getSource() == registerButton)
		{
			//TODO: Go to register page
		}
		
	}
	*/
  
}
