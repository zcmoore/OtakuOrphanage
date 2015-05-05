package edu.asu.ser322;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.UserDao;
import edu.asu.ser322.data.model.User;

public class Session
{
	private static User activeUser;
	
	public static User getActiveUser()
	{
		return activeUser;
	}
	
	public static boolean login(String username, String password)
	{
		UserDao dao = DAOCollection.getUserDao();
		boolean success = dao.login(username, password);
		
		if (success)
		{
			User user = dao.findUser(username);
			activeUser = user;
		}
		
		return success;
	}
	
}
