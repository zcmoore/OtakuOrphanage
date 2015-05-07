package edu.asu.ser322.data.access;

import static edu.asu.ser322.data.StorageFactory.createDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Person;
import edu.asu.ser322.data.model.Season;

/**
 * 
 * This dao will allow for the connections between the database and the GUI, 
 * here is where the Queries are made and the returned as either booleans
 * or linkedlist or a hashmap of archetype and number of roles
 * 
 * @author Benjamin Paothatat
 * 
 */
public class PeopleDaoSQL implements PeopleDao
{
	@Override
	public boolean addPerson(String name)
	{
		boolean result = false;
		String sql = "INSERT INTO People(Name) VALUES(?)";
		
		try (Connection connection = createDatabaseConnection();
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
	
	@Override
	public List<Person> findPerson(String name)
	{
		name = "%" + name + "%";
		String sql = "SELECT * FROM People WHERE Name LIKE ?";
		List<Person> people = new LinkedList<Person>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				int resultPersonId = results.getInt("PersonID");
				String resultName = results.getString("Name");
				Person insertedPerson = new Person(resultPersonId, resultName);
				people.add(insertedPerson);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return people;
	}
	
	@Override
	public List<Person> listAll()
	{
		String sql = "SELECT * FROM People";
		List<Person> people = new LinkedList<Person>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				int resultPersonId = results.getInt("PersonID");
				String resultName = results.getString("Name");
				Person person = new Person(resultPersonId, resultName);
				people.add(person);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return people;
	}
	
	@Override
	public boolean personExists(String name)
	{
		return !(findPerson(name).isEmpty());
	}
	
	@Override
	public List<Season> getSeasonsActedBy(Person person)
	{
		String sql = "SELECT * FROM Seasons season WHERE EXISTS "
				+ "(SELECT * FROM ActorAppearances act WHERE"
				+ " season.SeasonNumber = act.Season AND season.SeriesName = act.Series AND"
				+ " act.Actor = ?)";
		List<Season> seasons = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, person.getID());
			ResultSet results = statement.executeQuery();
			SeasonDao dao = DAOCollection.getSeasonDao();
			
			while (results.next())
			{
				int seasonNumber = results.getInt("SeasonNumber");
				String seriesName = results.getString("SeriesName");
				Season season = dao.findSeason(seriesName, seasonNumber);
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
	public List<Character> getCharactersActedBy(Person person)
	{
		String sql = "SELECT * FROM Characters character WHERE EXISTS "
				+ "(SELECT * FROM ActorAppearances act WHERE "
				+ "character.CharacterID = act.Character AND act.Actor = ?)";
		List<Character> characters = new LinkedList<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, person.getID());
			ResultSet results = statement.executeQuery();
			CharacterDao dao = DAOCollection.getCharacterDao();
			
			while (results.next())
			{
				int characterID = results.getInt("CharacterID");
				
				Character character = dao.findCharacter(characterID);
				characters.add(character);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return characters;
	}
	
	@Override
	public Map<String, Integer> getArchetypeDistributionOf(Person person)
	{
		// TODO: verify
		String sql = "SELECT Characters.Archetype AS Archetype, COUNT(Characters.CharacterId) AS NumberOfRoles FROM "
				+ "ActorAppearances INNER JOIN Characters "
				+ "ON ActorAppearances.Actor = ? AND ActorAppearances.Character = Characters.CharacterId "
				+ "GROUP BY Characters.Archetype;";
		Map<String, Integer> distribution = new HashMap<>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, person.getID());
			ResultSet results = statement.executeQuery();
			
			while (results.next())
			{
				String archetype = results.getString("Archetype");
				int numberOfRoles = results.getInt("NumberOfRoles");
				distribution.put(archetype, Integer.valueOf(numberOfRoles));
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return distribution;
	}
	
}
