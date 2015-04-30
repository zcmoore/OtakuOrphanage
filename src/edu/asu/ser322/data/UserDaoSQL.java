package edu.asu.ser322.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import edu.asu.ser322.data.StorageFactory.SQL;

/**
 * {@link UserDao} which uses an SQLite database as its persistent store. Note: the
 * current version of this code is not secure, as it is vulnerable to various attacks
 * including injection, and <b>should not be used in production.</b>
 * 
 * @author Moore, Zachary
 *
 */
public class UserDaoSQL implements UserDao
{
	/**
	 * @return Connection to {@link SQL#CONNECTION_URL}, or null if a connection cannot be
	 *         established.
	 */
	private Connection createConnection()
	{
		Connection connection = null;
		
		try
		{
			Class.forName(SQL.DRIVER_PATH);
			connection = DriverManager.getConnection(SQL.CONNECTION_URL);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return connection;
	}
	
	@Override
	public boolean addUser(User user)
	{
		// TODO: validate user
		boolean result = false;
		String sql = "INSERT INTO User (Username, Password, Waifu)" + "VALUES(?, ?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getWaifu().getID());
			
			statement.execute();
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public boolean updateUser(User user)
	{
		// TODO Auto-generated method stub return false;
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public User findUser(String username)
	{
		// TODO Auto-generated method stub return null;
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public boolean userExists(String username)
	{
		// TODO Auto-generated method stub return false;
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public boolean deleteUser(String username)
	{
		// TODO Auto-generated method stub return false;
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public boolean login(String username, String password)
	{
		// TODO Auto-generated method stub return false;
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public boolean logout(String username, String password)
	{
		// TODO Auto-generated method stub return false;
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
}
