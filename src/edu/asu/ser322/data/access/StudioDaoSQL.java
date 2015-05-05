package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Studio;

/**
 * 
 * @author Benjamin Paothatat
 * @author Moor, Zachary
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
				PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setString(1, studio.getName());
			
			Date startDate = studio.getStartDate();
			Date closeDate = studio.getCloseDate();
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(startDate);
			statement.setInt(2, calendar.get(Calendar.DATE));
			statement.setInt(3, calendar.get(Calendar.MONTH));
			statement.setInt(4, calendar.get(Calendar.YEAR));
			
			calendar.setTime(closeDate);
			statement.setInt(5, calendar.get(Calendar.DATE));
			statement.setInt(6, calendar.get(Calendar.MONTH));
			statement.setInt(7, calendar.get(Calendar.YEAR));
			
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
	public boolean updateStudio(Studio studio)
	{
		boolean result = false;
		String sql = "UPDATE Studios set StartDateDay = ?, StartDateMonth = ?, StartDateYear = ?,"
				+ "CloseDateDay = ?, CloseDateMonth = ?, CloseDateYear = ? WHERE  StudioName = ?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			Date startDate = studio.getStartDate();
			Date closeDate = studio.getCloseDate();
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(startDate);
			statement.setInt(1, calendar.get(Calendar.DATE));
			statement.setInt(2, calendar.get(Calendar.MONTH));
			statement.setInt(3, calendar.get(Calendar.YEAR));
			
			calendar.setTime(closeDate);
			statement.setInt(4, calendar.get(Calendar.DATE));
			statement.setInt(5, calendar.get(Calendar.MONTH));
			statement.setInt(6, calendar.get(Calendar.YEAR));
			
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
	public Studio findStudio(String studioName)
	{
		String sql = "SELECT * FROM Studios WHERE StudioName=?";
		Studio studio = Studio.NULL_STUDIO;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, studioName);
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				// TODO: Cleanup
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("StartDateYear"),
						result.getInt("StartDateMonth"), result.getInt("StartDateDay"));
				Date startDate = calendar.getTime();
				
				calendar.set(result.getInt("CloseDateYear"),
						result.getInt("CloseDateMonth"), result.getInt("CloseDateDay"));
				Date closeDate = calendar.getTime();
				
				studio = new Studio(result.getString("StudioName"), startDate, closeDate);
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
	public List<Studio> listAll()
	{
		String sql = "SELECT * FROM Studios WHERE StudioName=?";
		List<Studio> studios = new LinkedList<>();
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				// TODO: Cleanup
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("StartDateYear"),
						result.getInt("StartDateMonth"), result.getInt("StartDateDay"));
				Date startDate = calendar.getTime();
				
				calendar.set(result.getInt("CloseDateYear"),
						result.getInt("CloseDateMonth"), result.getInt("CloseDateDay"));
				Date closeDate = calendar.getTime();
				
				Studio studio = new Studio(result.getString("StudioName"), startDate,
						closeDate);
				
				studios.add(studio);
			}
			
			statement.execute();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return studios;
	}
	
	@Override
	public boolean studioExists(String studioName)
	{
		return findStudio(studioName) != Studio.NULL_STUDIO;
	}
	
	@Override
	public boolean deleteStudio(String studioName)
	{
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
	public boolean addEmployee(String studioName, String personName, String role)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean removeEmployee(String studioName, String personName, String role)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
