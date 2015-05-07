package edu.asu.ser322;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Central frame to manage and connect all screens associated with the OtakuOrphanage
 * application.
 * <p>
 * All transitions should be made via calls such as {@link #showLogin()},
 * {@link #showMainMenu()}, and {@link #showRegisterPanel()}
 * 
 * @author Moore, Zachary
 *
 */
@SuppressWarnings("serial")
public class Client extends JFrame
{
	public static final Dimension DEFAULT_FRAME_SIZE = new Dimension(1280, 720);
	public static final String APPLICATION_NAME = "Otaku Orphanage";
	public static final int VERSION = 1;
	public static final int REVISION = 0;
	
	private final JPanel panelContainer;
	private final CardLayout cardLayout;
	private final LoginGUI loginGUI;
	private final MainMenuGUI mainMenuGUI;
	private final RegisterGUI registerGUI;
	private final UpdateDatabaseGUI updateDatabeGUI;
	private final ProfileGUI profileGUI;
	
	public Client()
	{
		super(APPLICATION_NAME + " V" + VERSION + "." + REVISION);
		
		panelContainer = new JPanel();
		cardLayout = new CardLayout();
		loginGUI = new LoginGUI(this);
		mainMenuGUI = new MainMenuGUI(this);
		registerGUI = new RegisterGUI(this);
		updateDatabeGUI = new UpdateDatabaseGUI(this);
		profileGUI = new ProfileGUI(this);
		panelContainer.setLayout(cardLayout);
		
		this.addGUI(loginGUI);
		this.addGUI(mainMenuGUI);
		this.addGUI(registerGUI);
		this.addGUI(updateDatabeGUI);
		this.addGUI(profileGUI);
		this.add(panelContainer);
		
		this.showGUI(loginGUI);
		
		this.pack();
		this.invalidate();
		
		this.setVisible(true);
		this.setSize(DEFAULT_FRAME_SIZE);
		this.setLocation(getCenteredPosition());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addGUI(Component component)
	{
		String key = Integer.toString(component.hashCode());
		panelContainer.add(component, key);
	}
	
	private void showGUI(Component component)
	{
		String key = Integer.toString(component.hashCode());
		cardLayout.show(panelContainer, key);
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
		showGUI(mainMenuGUI);
	}
	
	public void showRegisterPanel()
	{
		showGUI(registerGUI);
	}
	
	public void showLogin()
	{
		showGUI(loginGUI);
	}
	
	public void showUpdateDatabase()
	{
		showGUI(updateDatabeGUI);
	}
	
	public void showProfile()
	{
		profileGUI.setTextStyle(Session.getActiveUser().getUsername());
		showGUI(profileGUI);
	}
	
}
