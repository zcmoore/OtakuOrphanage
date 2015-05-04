package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Studio;
/**
 * 
 * @author Benjamin Paothatat
 *
 */

public class StudioDaoSQL implements StudioDao
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
	public boolean addStudio(Studio studio) 
	{
		boolean result = false;
		String sql = "INSERT INTO Studios(StudioName, StartDateDay, StartDateMonth, StartDateYear,"
				+ "CloseDateDay, CloseDateMonth, CloseDateYear) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, studio.getName());
			GregorianCalendar startDate = studio.getStartDate();
			statement.setInt(2, startDate.get(startDate.DATE));
			statement.setInt(3, startDate.get(startDate.MONTH));
			statement.setInt(4, startDate.get(startDate.YEAR));
			GregorianCalendar closeDate = studio.getCloseDate();
			statement.setInt(5, closeDate.get(startDate.DATE));
			statement.setInt(6, closeDate.get(startDate.MONTH));
			statement.setInt(7, closeDate.get(startDate.YEAR));
			
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
	public boolean updateStudio(Studio studio) {
		boolean result = false;
		String sql = "UPDATE Studios set StartDateDay = ?, StartDateMonth = ?, StartDateYear = ?,"
				+ "CloseDateDay = ?, CloseDateMonth = ?, CloseDateYear = ? WHERE  StudioName = ?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			GregorianCalendar startDate = studio.getStartDate();
			statement.setInt(1, startDate.get(startDate.DATE));
			statement.setInt(2, startDate.get(startDate.MONTH));
			statement.setInt(3, startDate.get(startDate.YEAR));
			GregorianCalendar closeDate = studio.getCloseDate();
			statement.setInt(4, closeDate.get(startDate.DATE));
			statement.setInt(5, closeDate.get(startDate.MONTH));
			statement.setInt(6, closeDate.get(startDate.YEAR));
			statement.setString(7, studio.getName());
			
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
	public Studio findStudio(String studioName) {
		String sql = "SELECT * FROM Studios WHERE StudioName=?";
		Studio studio = Studio.NULL_STUDIO;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, studioName);
			ResultSet result = statement.executeQuery();
			
			if(result.next())
			{
				GregorianCalendar startDate = new GregorianCalendar();
				startDate.set(result.getInt("StartDateYear"), result.getInt("StartDateMonth"), result.getInt("StartDateDay"));
				studio = new Studio(result.getString("StudioName"), startDate);
				GregorianCalendar closeDate = new GregorianCalendar();
				closeDate.set(result.getInt("CloseDateYear"), result.getInt("CloseDateMonth"), result.getInt("CloseDateDay"));
				studio.setCloseDate(closeDate);
			}
			
			
			statement.execute();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return studio;
	}

	@Override
	public boolean studioExists(String studioName) {
		return findStudio(studioName) != Studio.NULL_STUDIO;
	}

	@Override
	public boolean deleteStudio(String studioName) {
		boolean result = false;
		String sql = "DELETE FROM Studios WHERE StudioName=?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, studioName);
			
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
	public boolean addEmployee(String studioName, String personName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEmployee(String studioName, String personName) {
		// TODO Auto-generated method stub
		return false;
	}

}
