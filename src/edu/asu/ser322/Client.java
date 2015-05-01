package edu.asu.ser322;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Client extends JFrame
{
	public static final Dimension DEFAULT_FRAME_SIZE = new Dimension(1280, 720);
	public static final String APPLICATION_NAME = "Otaku Orphanage";
	public static final int VERSION = 1;
	public static final int REVISION = 0;
	public static final String LOGIN = "0";
	public static final String MAINMENU = "1";
	JPanel panelCont = new JPanel();
	CardLayout cardLayout = new CardLayout();
	LoginGUI loginGUI;
	MainMenuGUI mainMenuGUI;
	
	public Client()
	{
		super(APPLICATION_NAME + " V" + VERSION + "." + REVISION);
		loginGUI = new LoginGUI(this);
		mainMenuGUI = new MainMenuGUI(this);
		panelCont.setLayout(cardLayout);
		panelCont.add(loginGUI, LOGIN);
		panelCont.add(mainMenuGUI, MAINMENU);
		cardLayout.show(panelCont, LOGIN);
		this.pack();
		this.invalidate();
		
		this.add(panelCont);
		setVisible(true);
		this.setSize(DEFAULT_FRAME_SIZE);
		this.setLocation(getCenteredPosition());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private Point getCenteredPosition()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 2 - this.getWidth() / 2;
		int y = screenSize.height / 2 - this.getHeight() / 2;
		
		return new Point(x, y);
	}
	
	public void showMainMenu()
	{
		cardLayout.show(panelCont, MAINMENU);
	}

}
