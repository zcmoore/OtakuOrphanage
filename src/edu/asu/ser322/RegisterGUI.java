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
import javax.swing.JTextField;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.UserDao;

@SuppressWarnings("serial")
public class RegisterGUI extends JPanel
{
	
	private JLabel RegisterPageTitle;
	private JLabel enterUserNameLabel;
	private JLabel enterPasswordLabel;
	private JLabel enterWaifuLabel;
	
	private JTextField enterUserNameTextField;
	private JTextField enterPasswordTextField;
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
		//setOpaque(false);
		
		userDao = DAOCollection.getUserDao();
		
		String registerPageTitleLabel = "<html>\n" + "<font size=+4><font color=#00FFFF>Register</font>";
		String enterUserNameLabelText = "<html>\n" + "<font size=+1><font color=red>Enter Username:</font>";
		String enterPasswordLabelText = "<html>\n" + "<font size=+1><font color=red>Enter Password:</font>";
		String enterWaifuLabelText = "<html>\n" + "<font size=+1><font color=red>Waifu:</font>";
		String becomeOtakuButtonText = "<html>\n" + "<font color=red>Become Otaku</font>";
		
		RegisterPageTitle = new JLabel(registerPageTitleLabel);
		enterUserNameLabel = new JLabel(enterUserNameLabelText);
		enterPasswordLabel = new JLabel(enterPasswordLabelText);
		enterWaifuLabel = new JLabel(enterWaifuLabelText);
		
		enterUserNameTextField = new JTextField();
		enterPasswordTextField = new JTextField();
		//TODO: search for own, or select in combo box from data?
		enterWaifuTextField = new JTextField();
		becomeOtakuButton = new JButton(becomeOtakuButtonText);
	}
	
	public void layout()
	{
		setLayout(null);
		
		RegisterPageTitle.setBounds(480,20,500,80);
		add(RegisterPageTitle);
		
		enterUserNameLabel.setBounds(300,120,150,30);
		add(enterUserNameLabel);
		
		enterUserNameTextField.setBounds(460,120,150,30);
		add(enterUserNameTextField);
		
		enterPasswordLabel.setBounds(300,160,150,30);
		add(enterPasswordLabel);
		
		enterPasswordTextField.setBounds(460,160,150,30);
		add(enterPasswordTextField);
		
		enterWaifuLabel.setBounds(300,200,150,30);
		add(enterWaifuLabel);
		
		enterWaifuTextField.setBounds(460,200,150,30);
		add(enterWaifuTextField);
		
		becomeOtakuButton.setBounds(1100,580, 150, 75);
		becomeOtakuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String user = enterUserNameTextField.getText();
				//FIXME: user Exists might be giving me errors
				if(userDao.userExists(user))
				{
					//JOptionPane.showMessageDialog(client, "User Exists");
					System.out.println("user exist");
				}
				else if(!userDao.userExists(user) && enterPasswordTextField.getText().trim() != "")
				{
					client.showMainMenu();
				}
			}
		});
		add(becomeOtakuButton);
	}
	/*
	public void addImageBackGround()
    {
        try 
        {
            img = ImageIO.read(new File("rec/IMAGETOLOAD.jpg"));
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
          g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
        }
    }
	*/
}

