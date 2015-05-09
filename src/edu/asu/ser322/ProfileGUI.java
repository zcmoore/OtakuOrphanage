package edu.asu.ser322;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.User;
import edu.asu.ser322.data.model.WatchRecord;

public class ProfileGUI extends JPanel
{
	private JLabel imageOfUser;
	public JLabel userNameLabel;

	private JTable userWatchTable;
	private JScrollPane scp;

	private JButton loadButton;
	private JButton backButton;
	private JButton deleteAccount;
	private BufferedImage img;

	private List<User> searchWatches;
	private DefaultTableModel tableModel;
	Vector<String> columnNames = new Vector<String>();
	Vector<Vector<String>> rowValues = new Vector<Vector<String>>();

	Client client;

	public ProfileGUI(Client client)
	{
		this.client = client;
		init();
	}

	public void init()
	{
		setOpaque(false);
		addImageBackGround();
		layout();
		imageOfUser = new JLabel();
		userNameLabel = new JLabel();

		tableModel = new DefaultTableModel();
		userWatchTable = new JTable(tableModel);
		scp = new JScrollPane(userWatchTable);

		loadButton = new JButton("Load");
		backButton = new JButton("Back");
		deleteAccount = new JButton("Delete Account");

		columnNames.add("Series Name");
		columnNames.add("Season Number");
		columnNames.add("Episode Number");
		tableModel.setColumnIdentifiers(columnNames);

		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int reply = JOptionPane.showConfirmDialog(client,
						"Are you sure you want to delete your account?", "Delete User",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					boolean check = DAOCollection.getUserDao().deleteUser(
							Session.getActiveUser().getUsername());
					Session.logout();
					clearAllVectors();
					client.showLogin();
				}
				else if (reply == JOptionPane.NO_OPTION)
				{
				}

			}
		});

		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				List<WatchRecord> watched = DAOCollection.getUserDao()
						.findWatchRecordsFor(Session.getActiveUser().getUsername());
				for (WatchRecord watchRecord : watched)
				{
					Vector vector = new Vector<String>();
					vector.add(watchRecord.getSeason().getSeriesName());
					vector.add(watchRecord.getSeason().getSeasonNumber());
					vector.add(watchRecord.getEpisodesWatched());
					rowValues.add(vector);
				}
				repaint();
				tableModel.setDataVector(rowValues, columnNames);
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				client.showMainMenu();
			}
		});

		scp.setBounds(325, 100, 600, 300);
		add(scp);

		loadButton.setBounds(597, 460, 80, 30);
		add(loadButton);

		imageOfUser.setBounds(900, 580, 150, 75);
		add(imageOfUser);

		userNameLabel.setBounds(500, 30, 300, 75);
		add(userNameLabel);

		backButton.setBounds(1100, 580, 80, 30);
		add(backButton);

		deleteAccount.setBounds(30, 580, 150, 30);
		add(deleteAccount);
	}
	
	public void clearAllVectors()
	{
		columnNames.clear();
		rowValues.clear();
	}

	public void setTextStyle(String currentUser)
	{
		String usernameStyle = "<html>" + "<font size=+3><font color=#00ff00>"
				+ "Welcome " + currentUser + "</font>";
		this.userNameLabel.setText(usernameStyle);
		repaint();
	}

	public void setPictureOfSelectedItem(String picturePath)
	{
		ImageIcon icon = createImageIcon(picturePath);
		imageOfUser.setIcon(icon);
	}

	public ImageIcon createImageIcon(String path)
	{
		java.net.URL imageURL = MainMenuGUI.class.getResource(path);
		ImageIcon returnIcon;

		if (imageURL != null)
		{
			returnIcon = new ImageIcon(imageURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			returnIcon = null;
		}

		return returnIcon;
	}

	public void layout()
	{}

	public void addImageBackGround()
	{
		try
		{
			img = ImageIO.read(new File("rec/ProfileBackground.jpg"));
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
