package edu.asu.ser322.data.access;

import static edu.asu.ser322.data.StorageFactory.createDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Gender;

/**
 * This dao will allow for the connections between the database and the GUI, 
 * here is where the Queries are made and the returned as either booleans
 * or linkedlist
 * 
 * @author Benjamin Paothatat
 * @author Moore, Zachary
 * 
 */
public class EpisodeDaoSQL implements EpisodeDao
{
	@Override
	public boolean addCharacterApperance(Episode episode, Character character)
	{
		boolean result = false;
		String sql = "INSERT INTO CharacterAppearances(Character, Series, Season, Episode, Role) VALUES(?, ?, ?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, character.getId());
			statement.setString(2, episode.getSeriesName());
			statement.setInt(3, episode.getSeasonNumber());
			statement.setInt(4, episode.getEpisodeNumber());
			
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public boolean addEpisode(Episode episode)
	{
		boolean result = false;
		String sql = "INSERT INTO Episodes(SeriesName, SeasonNumber, ShowName, AirDateDay, AirDateMonth"
				+ "AirDateYear, ArtStyle, Approprateness, EpisodeName, Type) VALUES(?, ?, ?, ?, ? , ?, ?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			Date airDate = episode.getAirDate();
			Calendar calender = new GregorianCalendar();
			calender.setTime(airDate);
			
			statement.setString(1, episode.getSeriesName());
			statement.setInt(2, episode.getSeasonNumber());
			statement.setInt(3, episode.getEpisodeNumber());
			statement.setInt(4, calender.get(Calendar.DATE));
			statement.setInt(5, calender.get(Calendar.MONTH));
			statement.setInt(6, calender.get(Calendar.DATE));
			statement.setString(7, episode.getArtStyle());
			statement.setString(8, episode.getApproprateness());
			statement.setString(9, episode.getEpisodeName());
			statement.setString(9, episode.getType());
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
	public boolean deleteEpisode(Episode episode)
	{
		boolean result = false;
		String sql = "DELETE FROM Episodes WHERE SeriesName = ?, SeasonNumber = ?, EpisodeNumber = ?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, episode.getSeriesName());
			statement.setInt(2, episode.getSeasonNumber());
			statement.setInt(3, episode.getEpisodeNumber());
			
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
	public boolean episodeExists(String episodeName)
	{
		return !(findEpisode(episodeName).isEmpty());
	}
	
	@Override
	public List<Episode> findEpisode(String episodeName)
	{
		String sql = "SELECT * FROM Episodes WHERE EpisodeName=?";
		List<Episode> episodes = new LinkedList<Episode>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, episodeName);
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("AirDateYear"), result.getInt("AirDateMonth"),
						result.getInt("AirDateDay"));
				Date airDate = calendar.getTime();
				
				Episode insteredEpisode = new Episode(result.getString("SeriesName"),
						result.getInt("SeasonNumber"), result.getInt("EpisodeNumber"));
				insteredEpisode.setAirDate(airDate);
				insteredEpisode.setType("Type");
				insteredEpisode.setApproprateness("Approprateness");
				insteredEpisode.setEpisodeName(result.getString("EpisodeName"));
				insteredEpisode.setArtStyle("ArtStyle");
				episodes.add(insteredEpisode);
			}
			
			statement.execute();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return episodes;
	}
	
	@Override
	public List<Episode> listAll()
	{
		String sql = "SELECT * FROM Episodes";
		List<Episode> episodes = new LinkedList<Episode>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("AirDateYear"), result.getInt("AirDateMonth"),
						result.getInt("AirDateDay"));
				Date airDate = calendar.getTime();
				
				Episode insteredEpisode = new Episode(result.getString("SeriesName"),
						result.getInt("SeasonNumber"), result.getInt("EpisodeNumber"));
				insteredEpisode.setAirDate(airDate);
				insteredEpisode.setType("Type");
				insteredEpisode.setApproprateness("Approprateness");
				insteredEpisode.setEpisodeName(result.getString("EpisodeName"));
				insteredEpisode.setArtStyle("ArtStyle");
				episodes.add(insteredEpisode);
			}
			
			statement.execute();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return episodes;
	}
	
	@Override
	public List<Character> listCharactersIn(Episode episode)
	{
		String sql = "SELECT * FROM Characters characters WHERE"
				+ "(SELECT * FROM CharacterAppearances appearance WHERE Series = ?, Season = ?, Episode = ?"
				+ "AND characters.CharacterID = appearance.Character";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, episode.getSeriesName());
			statement.setInt(2, episode.getSeasonNumber());
			statement.setInt(3, episode.getEpisodeNumber());
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				Calendar calendar = new GregorianCalendar();
				calendar.set(results.getInt("DOBYear"), results.getInt("DOBMonth"),
						results.getInt("DOBDay"));
				Date airDate = calendar.getTime();
				
				Character insteredCharacter = new Character();
				insteredCharacter.setArchetype(results.getString("Archtype"));
				insteredCharacter.setBirthDate(airDate);
				insteredCharacter.setGender(Gender.valueOf(results.getString("Gender")
						.toUpperCase()));
				insteredCharacter.setHairColor(results.getString("HairColor"));
				insteredCharacter.setName(results.getString("Name"));
				insteredCharacter.setId(results.getInt("CharacterId"));
			}
			
			statement.execute();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return characters;
	}
	
	@Override
	public boolean removeCharacterApperance(Episode episode, Character character)
	{
		boolean result = false;
		String sql = "DELETE FROM CharacterAppearances Character = ?, Series = ?, Season = ?, Episode = ?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, character.getId());
			statement.setString(2, episode.getSeriesName());
			statement.setInt(3, episode.getSeasonNumber());
			statement.setInt(4, episode.getEpisodeNumber());
			
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public boolean updateEpisode(Episode episode)
	{
		boolean result = false;
		String sql = "UPDATE Episodes set AirDateDay = ?, AirDateMonth = ?, AireDateYear = ?,"
				+ "ArtSytle = ?, Approprateness = ?, EpisodeName = ?, Type = ? WHERE  SeriesName = ?, SeasonNumber = ?, EpisodeNumber = ?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(8, episode.getSeriesName());
			statement.setInt(9, episode.getSeasonNumber());
			statement.setInt(10, episode.getEpisodeNumber());
			
			Date airDate = episode.getAirDate();
			Calendar calender = new GregorianCalendar();
			calender.setTime(airDate);
			
			statement.setInt(1, calender.get(Calendar.DATE));
			statement.setInt(2, calender.get(Calendar.MONTH));
			statement.setInt(3, calender.get(Calendar.DATE));
			statement.setString(4, episode.getArtStyle());
			statement.setString(5, episode.getApproprateness());
			statement.setString(6, episode.getEpisodeName());
			statement.setString(7, episode.getType());
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
