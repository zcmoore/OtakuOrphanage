package edu.asu.ser322;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Client extends JFrame
{
	public static final Dimension DEFAULT_FRAME_SIZE = new Dimension(1280, 720);
	public static final String APPLICATION_NAME = "Otaku Orphanage";
	public static final int VERSION = 1;
	public static final int REVISION = 0;
	
	public Client()
	{
		super(APPLICATION_NAME + " V" + VERSION + "." + REVISION);
		this.pack();
		this.invalidate();
		
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
}
