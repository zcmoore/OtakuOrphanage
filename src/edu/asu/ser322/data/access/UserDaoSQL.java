package edu.asu.ser322.data.access;

import static edu.asu.ser322.data.StorageFactory.createDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.User;
import edu.asu.ser322.data.model.WatchRecord;

/**
 * {@link UserDao} which uses an SQLite database as its persistent store.
 * <p>
 * Note: the current version of this code is not secure, as it is vulnerable to various
 * attacks including injection, and <b>should not be used in production.</b>
 * 
 * @author Moore, Zachary
 *
 */
class UserDaoSQL implements UserDao
{
	private boolean addWatch(User user, Season anime, int episodeCount, int rating)
	{
		if (!user.validate())
			return false;
		
		boolean result = false;
		String sql = "INSERT INTO Watched(User, Series, Season, EpisodeCount) VALUES(?, ?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getUsername());
			statement.setString(2, anime.getSeriesName());
			statement.setInt(3, anime.getSeasonNumber());
			statement.setInt(4, episodeCount);
			
			statement.execute();
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}
	
	private WatchRecord parseWatchRecord(ResultSet results) throws SQLException
	{
		WatchRecord record = new WatchRecord();
		
		String seriesName = results.getString("Series");
		int seasonNumber = results.getInt("Season");
		int eipsodeCount = results.getInt("EpisodeCount");
		
		SeasonDao dao = DAOCollection.getSeasonDao();
		Season season = dao.findSeason(seriesName, seasonNumber);
		record.setEpisodesWatched(eipsodeCount);
		record.setSeason(season);
		
		return record;
	}
	
	private boolean updateWatch(User user, Season anime, int episodeCount, int rating)
	{
		if (!user.validate())
			return false;
		
		boolean result = false;
		String sql = "INSERT INTO Users(Username, Password, Waifu) VALUES(?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			Character waifu = user.getWaifu();
			if (waifu != null)
				statement.setInt(3, waifu.getId());
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
	public boolean addUser(User user)
	{
		if (!user.validate())
			return false;
		
		boolean result = false;
		String sql = "INSERT INTO Users(Username, Password, Waifu) VALUES(?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			Character waifu = user.getWaifu();
			if (waifu != null)
				statement.setInt(3, waifu.getId());
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
	public boolean deleteUser(String username)
	{
		boolean result = false;
		String sql = "DELETE FROM Users WHERE Username=?";
		
		try (Connection connection = createDatabaseConnection();
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
	public User findUser(String username)
	{
		String sql = "SELECT * FROM Users WHERE Username=?";
		User user = User.NULL_USER;
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				String resultUsername = results.getString("Username");
				// FIXME: This is a security risk...
				String resultPassword = results.getString("Password");
				int resultWaifuID = results.getInt("Waifu");
				
				CharacterDao dao = DAOCollection.getCharacterDao();
				Character waifu = dao.findCharacter(resultWaifuID);
				
				user = new User(resultUsername, resultPassword, waifu);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public List<WatchRecord> findWatchRecordsFor(String username)
	{
		String sql = "SELECT * FROM Watched WHERE User=?";
		List<WatchRecord> records = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				WatchRecord record = parseWatchRecord(results);
				User user = findUser(username);
				record.setUser(user);
				records.add(record);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return records;
	}
	
	@Override
	public List<User> listAll()
	{
		String sql = "SELECT * FROM Users WHERE Username=?";
		List<User> users = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				String resultUsername = results.getString("Username");
				// FIXME: This is a security risk...
				String resultPassword = results.getString("Password");
				int resultWaifuID = results.getInt("Waifu");
				
				CharacterDao dao = DAOCollection.getCharacterDao();
				Character waifu = dao.findCharacter(resultWaifuID);
				
				User user = new User(resultUsername, resultPassword, waifu);
				users.add(user);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public List<WatchRecord> listAllWatchRecords()
	{
		String sql = "SELECT * FROM Watched";
		List<WatchRecord> records = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				WatchRecord record = parseWatchRecord(results);
				User user = findUser(results.getString("User"));
				record.setUser(user);
				records.add(record);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return records;
	}
	
	@Override
	public boolean login(String username, String password)
	{
		String sql = "SELECT * FROM Users WHERE Username=?";
		boolean result = false;
		
		try (Connection connection = createDatabaseConnection();
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
	
	@Override
	public boolean registerWatch(User user, Season anime, int episodeCount, int rating)
	{
		String sql = "SELECT * FROM Watched WHERE User=? AND Series=? AND Season=?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getUsername());
			statement.setString(2, anime.getSeriesName());
			statement.setInt(3, anime.getSeasonNumber());
			ResultSet results = statement.executeQuery();
			
			if (results.next())
				return updateWatch(user, anime, episodeCount, rating);
			else
				return addWatch(user, anime, episodeCount, rating);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateUser(User user)
	{
		if (!user.validate())
			return false;
		
		boolean result = false;
		String sql = "UPDATE Users set Password=?, Waifu=?, WHERE Username=?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, user.getPassword());
			statement.setInt(2, user.getWaifu().getId());
			
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
	public boolean userExists(String username)
	{
		return !User.NULL_USER.equals(findUser(username));
	}
	
}
