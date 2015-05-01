package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.User;

/**
 * {@link UserDao} which uses an SQLite database as its persistent store. Note: the
 * current version of this code is not secure, as it is vulnerable to various attacks
 * including injection, and <b>should not be used in production.</b>
 * 
 * @author Moore, Zachary
 *
 */
class UserDaoSQL implements UserDao
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
		String sql = "INSERT INTO Users(Username, Password, Waifu) VALUES(?, ?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			Character waifu = user.getWaifu();
			if (waifu != null)
				statement.setInt(3, waifu.getID());
			else
				statement.setNull(3, Types.INTEGER);
			
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
		// TODO: validate user
		boolean result = false;
		String sql = "UPDATE Users set Password=?, Waifu=?, WHERE Username=?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getPassword());
			statement.setInt(2, user.getWaifu().getID());
			
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
	public User findUser(String username)
	{
		String sql = "SELECT * FROM Users WHERE Username=?";
		User user = User.NULL_USER;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				String resultUsername = results.getString("Username");
				// FIXME: This is a security risk...
				String resultPassword = results.getString("Password");
				//int resultWaifuID = results.getInt("Waifu");
				
				//CharacterDao dao = DAOCollection.getCharacterDao();
				//Character waifu = dao.findCharacter(resultWaifuID);
				
				user = new User(resultUsername, resultPassword, null);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public boolean userExists(String username)
	{
		return !User.NULL_USER.equals(findUser(username));
	}
	
	@Override
	public boolean deleteUser(String username)
	{
		boolean result = false;
		String sql = "DELETE FROM Users WHERE Username=?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, username);
			
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
	public boolean login(String username, String password)
	{
		String sql = "SELECT * FROM Users WHERE Username=?";
		boolean result = false;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				String actualPassword = results.getString("Password");
				result = actualPassword.equals(password);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public boolean logout(String username, String password)
	{
		// TODO add actual implementation or documentation that explains otherwise
		return login(username, password);
	}
	
}
