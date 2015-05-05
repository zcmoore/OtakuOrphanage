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
	/** Login panel Id */
	public static final String LOGIN_PANEL_ID = "0";
	/** Main menu Id */
	public static final String MAIN_MENU_ID = "1";
	/** Register Page Id */
	public static final String REGISTER_PANEL_ID = "2";
	private final JPanel panelContainer = new JPanel();
	private final CardLayout cardLayout = new CardLayout();
	private final LoginGUI loginGUI;
	private final MainMenuGUI mainMenuGUI;
	private final RegisterGUI registerGUI;

	public Client()
	{
		super(APPLICATION_NAME + " V" + VERSION + "." + REVISION);
		loginGUI = new LoginGUI(this);
		mainMenuGUI = new MainMenuGUI(this);
		registerGUI = new RegisterGUI(this);
		panelContainer.setLayout(cardLayout);
		panelContainer.add(loginGUI, LOGIN_PANEL_ID);
		panelContainer.add(mainMenuGUI, MAIN_MENU_ID);
		panelContainer.add(registerGUI, REGISTER_PANEL_ID);
		cardLayout.show(panelContainer, LOGIN_PANEL_ID);
		this.pack();
		this.invalidate();

		this.add(panelContainer);
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
		cardLayout.show(panelContainer, MAIN_MENU_ID);
	}

	public void showLoginPanel()
	{
		cardLayout.show(panelContainer, LOGIN_PANEL_ID);
	}

	public void showRegisterPanel()
	{
		cardLayout.show(panelContainer, REGISTER_PANEL_ID);
	}

	public void showLogin()
	{
		cardLayout.show(panelContainer, LOGIN);
	}

}
