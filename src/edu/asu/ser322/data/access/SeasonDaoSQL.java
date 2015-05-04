package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Season;

public class SeasonDaoSQL implements SeasonDao
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
	public boolean addSeason(Season season) 
	{
		boolean result = false;
		String sql = "INSERT INTO Seasons(SeriesName, SeasonNumber, ShowName, AirDateDay, AirDateMonth,"
				+ "AirDateYear, FinishDateDay, FinishDateMonth, FinishDateYear, Genre, Appropriateness)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, season.getName());
			statement.setInt(2, season.getSeasonNumber());
			statement.setString(3, season.getName());
			statement.setInt(4, season.getAirDate().getDay());
			statement.setInt(5, season.getAirDate().getMonth());
			statement.setInt(6, season.getAirDate().getYear());
			statement.setInt(7, season.getFinishDate().getDay());
			statement.setInt(8, season.getFinishDate().getMonth());
			statement.setInt(9, season.getFinishDate().getYear());
			statement.setString(10, season.getGenre());
			statement.setString(11, season.getAppropriateness());
		
			
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
	public boolean updateSeason(Season season) 
	{
		//TODO: Implement
		return false;
	}

	@Override
	public Season findSeason(String seriesname, int seasonnumber) 
	{
		String sql = "SELECT * FROM Seasons WHERE SeriesName= ? AND SeasonNumber = ?";
		Season season = Season.NULL_SEASON;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesname);
			statement.setInt(2, seasonnumber);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				season = new Season(results.getString("SeriesName"), results.getInt("SeasonNumber"));
				season.setName(results.getString("ShowName"));
				Date airDate = new Date(results.getInt("AirDateYear"), results.getInt("AirDateMonth"), results.getInt("AirDateDay"));
				season.setAirDate(airDate);
				Date finishDate = new Date(results.getInt("FinishDateYear"), results.getInt("FinishDateMonth"), results.getInt("FinishDateDay"));
				season.setFinishDate(finishDate);
				season.setGenre(results.getString("Genre"));
				season.setAppropriateness(results.getString("Appropriateness"));

			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	
		return season;
	}
	
	@Override
	public List<Season> findSeason(String seriesname) 
	{
		String sql = "SELECT * FROM Seasons WHERE SeriesName=?";
		List<Season> seasons = new LinkedList<Season>();
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesname);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season insertedSeason = new Season(results.getString("SeriesName"), results.getInt("SeasonNumber"));
				insertedSeason.setName(results.getString("ShowName"));
				Date airDate = new Date(results.getInt("AirDateYear"), results.getInt("AirDateMonth"), results.getInt("AirDateDay"));
				insertedSeason.setAirDate(airDate);
				Date finishDate = new Date(results.getInt("FinishDateYear"), results.getInt("FinishDateMonth"), results.getInt("FinishDateDay"));
				insertedSeason.setFinishDate(finishDate);
				insertedSeason.setGenre(results.getString("Genre"));
				insertedSeason.setAppropriateness(results.getString("Appropriateness"));

				seasons.add(insertedSeason);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	
		return seasons;
	}

	@Override
	public List<Season> seasonsByAirYear(int airYear) 
	{
		String sql = "SELECT * FROM Seasons WHERE AirDateYear=?";
		List<Season> seasons = new LinkedList<Season>();
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, airYear);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season insertedSeason = new Season(results.getString("SeriesName"), results.getInt("SeasonNumber"));
				insertedSeason.setName(results.getString("ShowName"));
				Date airDate = new Date(results.getInt("AirDateYear"), results.getInt("AirDateMonth"), results.getInt("AirDateDay"));
				insertedSeason.setAirDate(airDate);
				Date finishDate = new Date(results.getInt("FinishDateYear"), results.getInt("FinishDateMonth"), results.getInt("FinishDateDay"));
				insertedSeason.setFinishDate(finishDate);
				insertedSeason.setGenre(results.getString("Genre"));
				insertedSeason.setAppropriateness(results.getString("Appropriateness"));

				seasons.add(insertedSeason);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	
		return seasons;
	}

	@Override
	public List<Season> seasonsByGenre(String genre) 
	{
		String sql = "SELECT * FROM Seasons WHERE Genre=?";
		List<Season> seasons = new LinkedList<Season>();
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, genre);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season insertedSeason = new Season(results.getString("SeriesName"), results.getInt("SeasonNumber"));
				insertedSeason.setName(results.getString("ShowName"));
				Date airDate = new Date(results.getInt("AirDateYear"), results.getInt("AirDateMonth"), results.getInt("AirDateDay"));
				insertedSeason.setAirDate(airDate);
				Date finishDate = new Date(results.getInt("FinishDateYear"), results.getInt("FinishDateMonth"), results.getInt("FinishDateDay"));
				insertedSeason.setFinishDate(finishDate);
				insertedSeason.setGenre(results.getString("Genre"));
				insertedSeason.setAppropriateness(results.getString("Appropriateness"));

				seasons.add(insertedSeason);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	
		return seasons;
	}

	@Override
	public boolean removeSeason(String seriesName, int seasonNumber) {
		boolean result = false;
		String sql = "DELETE FROM Seasons WHERE SeriesName = ? AND SeasonNumber = ?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesName);
			statement.setInt(2, seasonNumber);
		
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