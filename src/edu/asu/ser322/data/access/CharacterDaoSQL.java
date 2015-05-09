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

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Gender;

/**
 * {@link CharacterDao} which uses an SQLite database as its persistent store.
 * <p>
 * Adjacent classes may use {@link #parseCharacter(ResultSet)} to retrieve a
 * {@link Character} from an SQL {@link ResultSet}.
 * 
 * @author Moore, Zachary
 * 
 */
public class CharacterDaoSQL implements CharacterDao
{
	/**
	 * @param result
	 *            A ResultSet currently pointing to the desired row. The row is expected
	 *            to be in the Characters table, and be valid.
	 * @return A Character representing the given row specified by the given ResultSet
	 * @throws SQLException
	 *             if the expected column names are not found, most commonly because the
	 *             ResultSet is from a different table
	 */
	protected Character parseCharacter(ResultSet result) throws SQLException
	{
		
		int id = result.getInt("CharacterID");
		int age = result.getInt("Age");
		String archetype = result.getString("Archetype");
		String name = result.getString("Name");
		String genderString = result.getString("Gender");
		Gender gender = Gender.valueOf(genderString);
		String hairColour = result.getString("HairColor");
		
		Character character = new Character();
		character.setId(id);
		character.setArchetype(archetype);
		character.setName(name);
		character.setGender(gender);
		character.setHairColor(hairColour);
		character.setAge(age);
		
		return character;
	}
	
	@Override
	public boolean addCharacter(Character character)
	{
		boolean result = false;
		String sql = "INSERT INTO Characters(CharacterID, Name, Gender, HairColor, Archetype, Age) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			
			statement.setInt(1, character.getId());
			statement.setString(2, character.getName());
			statement.setString(3, character.getGender().toString());
			statement.setString(4, character.getHairColor());
			statement.setString(5, character.getArchetype());
			statement.setInt(6, character.getAge());
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
	public boolean characterExists(int id)
	{
		return findCharacter(id) != Character.NULL_CHARACTER;
	}
	
	@Override
	public boolean deleteCharacter(Character character)
	{
		boolean result = false;
		String sql = "DELETE FROM Characters WHERE CharacterID = ?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, character.getId());
			
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
	public boolean deleteCharacter(int id)
	{
		boolean result = false;
		String sql = "DELETE FROM Characters WHERE CharacterID=?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, id);
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
	public Character findCharacter(int id)
	{
		String sql = "SELECT * FROM Characters WHERE CharacterID = ?";
		Character character = Character.NULL_CHARACTER;
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			if (result.next())
				character = parseCharacter(result);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return character;
	}
	
	@Override
	public List<Character> findCharactersByAge(int age, ComparisonType comparisonType)
	{
		String sql = "SELECT * FROM Characters WHERE Age "
				+ comparisonType.symbolicRepresentation() + " ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, age);
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public List<Character> findCharactersByArchetype(String archetype)
	{
		archetype = "%" + archetype + "%";
		String sql = "SELECT * FROM Characters WHERE Archetype LIKE ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, archetype);
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public List<Character> findCharactersByGender(Gender gender)
	{
		String sql = "SELECT * FROM Characters WHERE Gender = ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, gender.name().toUpperCase());
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public List<Character> findCharactersByHairColour(String colour)
	{
		colour = "%" + colour + "%";
		String sql = "SELECT * FROM Characters WHERE HairColor LIKE ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, colour);
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public List<Character> findCharactersByName(String name)
	{
		name = "%" + name + "%";
		String sql = "SELECT * FROM Characters WHERE Name LIKE ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public List<Character> getWaifus()
	{
		// TODO: verify
		String sql = "SELECT * FROM Users INNER JOIN Characters "
				+ "ON Users.Waifu = Characters.CharacterId;";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public List<Character> listAll()
	{
		String sql = "SELECT * FROM Characters";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Character character = parseCharacter(result);
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
	public boolean updateCharacter(Character character)
	{
		boolean result = false;
		String sql = "UPDATE Characters set Name = ?, Gender = ?, DOBDay = ?, DOBMonth = ?, DOBYear = ?, HairColor = ?, "
				+ "Archetype = ? WHERE CharacterID = ?";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			Date dobDate = character.getBirthDate();
			Calendar calender = new GregorianCalendar();
			calender.setTime(dobDate);
			
			statement.setString(1, character.getName());
			statement.setString(2, character.getGender().name());
			statement.setInt(3, calender.get(Calendar.DAY_OF_MONTH));
			statement.setInt(4, calender.get(Calendar.MONTH));
			statement.setInt(5, calender.get(Calendar.DATE));
			statement.setString(6, character.getHairColor());
			statement.setString(7, character.getArchetype());
			statement.setInt(8, character.getId());
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
	public boolean associateCharacterWithShow(int characterId, Episode episode,
			String role)
	{
		boolean result = false;
		String sql = "INSERT INTO CharacterAppearances(Character, Series, Season, Episode, Role) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try (Connection connection = createDatabaseConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			
			statement.setInt(1, characterId);
			statement.setString(2, episode.getSeriesName());
			statement.setInt(3, episode.getSeasonNumber());
			statement.setInt(4, episode.getEpisodeNumber());
			statement.setString(5, role);
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
