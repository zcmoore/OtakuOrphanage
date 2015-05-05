package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Gender;

public class CharacterDaoSQL implements CharacterDao
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
	public boolean addCharacter(Character character)
	{
		boolean result = false;
		String sql = "INSERT INTO Characters(CharacterID, Name, Gender, DOBDay, DOBMonth, DOBYear, HairColor, Archetype) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			Date dobDate = character.getBirthDate();
			Calendar calender = new GregorianCalendar();
			calender.setTime(dobDate);
			
			statement.setInt(1, character.getId());
			statement.setString(2, character.getName());
			statement.setString(3, character.getGender().toString());
			statement.setInt(4, calender.get(Calendar.DAY_OF_MONTH));
			statement.setInt(5, calender.get(Calendar.MONTH));
			statement.setInt(6, calender.get(Calendar.DATE));
			statement.setString(7, character.getHairColor());
			statement.setString(8, character.getArchetype());
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
	public boolean updateCharacter(Character character)
	{
		boolean result = false;
		String sql = "UPDATE Characters set Name = ?, Gender = ?, DOBDay = ?, DOBMonth = ?, DOBYear = ?, HairColor = ?, "
				+ "Archetype = ? WHERE CharacterID = ?";
		
		try (Connection connection = createConnection();
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
	public List<Character> findCharactersByName(String name)
	{
		String sql = "SELECT * FROM Characters WHERE Name = ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createConnection();
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
	public List<Character> findCharactersByHairColour(String colour)
	{
		String sql = "SELECT * FROM Characters WHERE HairColor = ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createConnection();
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
	public List<Character> findCharactersByArchetype(String archetype)
	{
		String sql = "SELECT * FROM Characters WHERE Archetype = ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createConnection();
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
		
		try (Connection connection = createConnection();
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
	public List<Character> findCharactersByAge(int age, ComparisonType comparisonType)
	{
		String sql = "SELECT * FROM Characters WHERE Age "
				+ comparisonType.symbolicRepresentation() + " ?";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createConnection();
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
	public List<Character> getWaifus()
	{
		// TODO: verify
		String sql = "SELECT * FROM Users INNER JOIN Characters "
				+ "ON Users.Waifu = Characters.CharacterId;";
		List<Character> characters = new LinkedList<Character>();
		
		try (Connection connection = createConnection();
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
	
	private Character parseCharacter(ResultSet result) throws SQLException
	{
		int year = result.getInt("DOBYear");
		int month = result.getInt("DOBMonth");
		int day = result.getInt("DOBDay");
		int id = result.getInt("CharacterID");
		String archetype = result.getString("Archetype");
		String name = result.getString("Name");
		String genderString = result.getString("Gender");
		Gender gender = Gender.valueOf(genderString);
		String hairColour = result.getString("HairColor");
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(year, month, day);
		Date dobDate = calendar.getTime();
		
		Character character = new Character();
		character.setBirthDate(dobDate);
		character.setId(id);
		character.setArchetype(archetype);
		character.setName(name);
		character.setGender(gender);
		character.setHairColor(hairColour);
		
		return character;
	}
	
	@Override
	public Character findCharacter(int id)
	{
		String sql = "SELECT * FROM Characters WHERE CharacterID = ?";
		Character character = Character.NULL_CHARACTER;
		
		try (Connection connection = createConnection();
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
	public boolean characterExists(int id)
	{
		return findCharacter(id) != Character.NULL_CHARACTER;
	}
	
	@Override
	public boolean deleteCharacter(int id)
	{
		boolean result = false;
		String sql = "DELETE FROM Characters WHERE CharacterID=?";
		
		try (Connection connection = createConnection();
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
	public boolean deleteCharacter(Character character)
	{
		boolean result = false;
		String sql = "DELETE FROM Characters WHERE CharacterID = ?";
		
		try (Connection connection = createConnection();
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
	
}
