package edu.asu.ser322.data.access;

import static edu.asu.ser322.data.StorageFactory.createDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.model.Season;

public class SeasonDaoSQL implements SeasonDao
{
	@Override
	public boolean addSeason(Season season)
	{
		boolean result = false;
		String sql = "INSERT INTO Seasons(SeriesName, SeasonNumber, ShowName, AirDateDay, AirDateMonth,"
				+ "AirDateYear, FinishDateDay, FinishDateMonth, FinishDateYear, Appropriateness)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			Calendar calendar = new GregorianCalendar();
			
			statement.setString(1, season.getName());
			statement.setInt(2, season.getSeasonNumber());
			statement.setString(3, season.getName());
			
			calendar.setTime(season.getAirDate());
			statement.setInt(4, calendar.get(Calendar.DAY_OF_MONTH));
			statement.setInt(5, calendar.get(Calendar.MONTH));
			statement.setInt(6, calendar.get(Calendar.YEAR));
			
			calendar.setTime(season.getAirDate());
			statement.setInt(7, calendar.get(Calendar.DAY_OF_MONTH));
			statement.setInt(8, calendar.get(Calendar.MONTH));
			statement.setInt(9, calendar.get(Calendar.YEAR));
			statement.setString(10, season.getAppropriateness());
			
			statement.execute();
			
			associateGenres(season);
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}
	
	private void associateGenres(Season season)
	{
		for (String genre : season.getGenres())
		{
			associateGenre(season, genre);
		}
	}
	
	private void associateGenre(Season season, String genre)
	{
		String sql = "INSERT INTO GenreMap(Series, SeasonNumber, Genre)"
				+ "VALUES(?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, season.getSeriesName());
			statement.setInt(2, season.getSeasonNumber());
			statement.setString(3, genre);
			
			statement.execute();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	@Override
	public boolean updateSeason(Season season)
	{
		// TODO: Implement
		return false;
	}
	
	private List<String> getGenres(Season season)
	{
		String sql = "SELECT * FROM GenreMap WHERE Series=? AND SeasonNumber=?";
		List<String> genres = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, season.getSeriesName());
			statement.setInt(2, season.getSeasonNumber());
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				String genre = results.getString("Genre");
				genres.add(genre);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return genres;
	}
	
	@Override
	public Season findSeason(String seriesname, int seasonnumber)
	{
		String sql = "SELECT * FROM Seasons WHERE SeriesName= ? AND SeasonNumber = ?";
		Season season = Season.NULL_SEASON;
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesname);
			statement.setInt(2, seasonnumber);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				season = parseSeason(results);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return season;
	}
	
	private Date parseAirDate(ResultSet results) throws SQLException
	{
		Calendar calendar = new GregorianCalendar();
		int day = results.getInt("AirDateYear");
		int month = results.getInt("AirDateMonth");
		int year = results.getInt("AirDateDay");
		calendar.set(year, month, day);
		
		return calendar.getTime();
	}
	
	private Date parseFinishDate(ResultSet results) throws SQLException
	{
		Calendar calendar = new GregorianCalendar();
		int day = results.getInt("FinishDateYear");
		int month = results.getInt("FinishDateMonth");
		int year = results.getInt("FinishDateDay");
		calendar.set(year, month, day);
		
		return calendar.getTime();
	}
	
	private Season parseSeason(ResultSet results) throws SQLException
	{
		String seriesName = results.getString("SeriesName");
		int seasonNumber = results.getInt("SeasonNumber");
		
		Date airDate = parseAirDate(results);
		Date finishDate = parseFinishDate(results);
		
		Season season = new Season(seriesName, seasonNumber);
		season.setName(results.getString("ShowName"));
		season.setAppropriateness(results.getString("Appropriateness"));
		season.setAirDate(airDate);
		season.setFinishDate(finishDate);
		
		List<String> genres = getGenres(season);
		season.setGenres(genres);
		
		return season;
	}
	
	@Override
	public List<Season> findSeasonsBySeriesName(String seriesname)
	{
		String sql = "SELECT * FROM Seasons WHERE SeriesName=?";
		List<Season> seasons = new LinkedList<Season>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesname);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season season = parseSeason(results);
				seasons.add(season);
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
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, airYear);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season season = parseSeason(results);
				seasons.add(season);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return seasons;
	}
	
	@Override
	public List<Season> listAll()
	{
		String sql = "SELECT * FROM Seasons";
		List<Season> seasons = new LinkedList<Season>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season season = parseSeason(results);
				seasons.add(season);
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
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, genre);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season season = parseSeason(results);
				seasons.add(season);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return seasons;
	}
	
	@Override
	public boolean removeSeason(String seriesName, int seasonNumber)
	{
		boolean result = false;
		String sql = "DELETE FROM Seasons WHERE SeriesName = ? AND SeasonNumber = ?";
		
		try (Connection connection = createDatabaseConnection();
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
	
	@Override
	public List<Season> findSeasonsInSeries(String seriesname)
	{
		String sql = "SELECT * FROM Seasons WHERE SeriesName=?";
		List<Season> seasons = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesname);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Season season = parseSeason(results);
				seasons.add(season);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return seasons;
	}
	
	@Override
	public List<String> listSeries()
	{
		String sql = "SELECT DISTINCT FROM (SELECT SeriesName FROM Seasons)";
		List<String> seriesList = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				String series = results.getString("SeriesName");
				seriesList.add(series);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return seriesList;
	}
	
	@Override
	public boolean seasonExists(String seriesname, int seasonnumber)
	{
		return !Season.NULL_SEASON.equals(findSeason(seriesname, seasonnumber));
	}
	
	@Override
	public boolean seasonExists(String seriesname)
	{
		return findSeasonsBySeriesName(seriesname).size() > 0;
	}
	
}
