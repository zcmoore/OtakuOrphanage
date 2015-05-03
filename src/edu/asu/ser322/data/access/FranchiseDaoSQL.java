package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Franchise;

/**
 * 
 * @author Benjamin Paothatat
 *
 */
public class FranchiseDaoSQL implements FranchiseDao
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
	public boolean addFranchise(Franchise franchise) 
	{
		boolean result = false;
		String sql = "INSERT INTO Franchises(FranchiseID, FranchiseName) VALUES(?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, franchise.getId());
			statement.setString(2, franchise.getName());
			
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
	public boolean updateFranchise(Franchise franchise) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Franchise findFranchise(String name) {
		String sql = "SELECT * FROM Franchises WHERE FranchiseName=?";
		Franchise franchise = Franchise.NULL_FRANCHISE;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				int resultId = results.getInt("FranchiseID");
				String resultName = results.getString("FranchiseName");
			
				franchise = new Franchise(resultId, resultName);
			
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return franchise;
	}

	@Override
	public boolean deleteFranchise(String name) {
		boolean result = false;
		String sql = "DELETE FROM Franchises WHERE Username=?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, name);
			
			statement.execute();
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}

}
