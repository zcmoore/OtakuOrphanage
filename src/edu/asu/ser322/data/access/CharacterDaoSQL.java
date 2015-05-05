package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
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
			statement.setInt(4, calender.get(calender.DATE));
			statement.setInt(5, calender.get(calender.MONTH));
			statement.setInt(6, calender.get(calender.DATE));
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
			
			statement.setInt(8, character.getId());
			statement.setString(1, character.getName());
			statement.setString(2, character.getGender().name());
			statement.setInt(3, calender.get(calender.DATE));
			statement.setInt(4, calender.get(calender.MONTH));
			statement.setInt(5, calender.get(calender.DATE));
			statement.setString(6, character.getHairColor());
			statement.setString(7, character.getArchetype());
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
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("DOBYear"), result.getInt("DOBMonth"),
						result.getInt("DOBDay"));
				Date dobDate = calendar.getTime();
				
				Character insteredCharacter = new Character();
				insteredCharacter.setBirthDate(dobDate);
				insteredCharacter.setId(result.getInt("CharacterID"));
				insteredCharacter.setArchetype(result.getString("Archetype"));
				insteredCharacter.setName(result.getString("Name"));
				insteredCharacter.setGender(Gender.valueOf(result.getString("Gender")));
				insteredCharacter.setHairColor(result.getString("HairColor"));
				characters.add(insteredCharacter);
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
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("DOBYear"), result.getInt("DOBMonth"),
						result.getInt("DOBDay"));
				Date dobDate = calendar.getTime();
				
				Character insteredCharacter = new Character();
				insteredCharacter.setBirthDate(dobDate);
				insteredCharacter.setId(result.getInt("CharacterID"));
				insteredCharacter.setArchetype(result.getString("Archetype"));
				insteredCharacter.setName(result.getString("Name"));
				insteredCharacter.setGender(Gender.valueOf(result.getString("Gender")));
				insteredCharacter.setHairColor(result.getString("HairColor"));
				characters.add(insteredCharacter);
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
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("DOBYear"), result.getInt("DOBMonth"),
						result.getInt("DOBDay"));
				Date dobDate = calendar.getTime();
				
				Character insteredCharacter = new Character();
				insteredCharacter.setBirthDate(dobDate);
				insteredCharacter.setId(result.getInt("CharacterID"));
				insteredCharacter.setArchetype(result.getString("Archetype"));
				insteredCharacter.setName(result.getString("Name"));
				insteredCharacter.setGender(Gender.valueOf(result.getString("Gender")));
				insteredCharacter.setHairColor(result.getString("HairColor"));
				characters.add(insteredCharacter);
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
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("DOBYear"), result.getInt("DOBMonth"),
						result.getInt("DOBDay"));
				Date dobDate = calendar.getTime();
				
				Character insteredCharacter = new Character();
				insteredCharacter.setBirthDate(dobDate);
				insteredCharacter.setId(result.getInt("CharacterID"));
				insteredCharacter.setArchetype(result.getString("Archetype"));
				insteredCharacter.setName(result.getString("Name"));
				insteredCharacter.setGender(Gender.valueOf(result.getString("Gender")));
				insteredCharacter.setHairColor(result.getString("HairColor"));
				characters.add(insteredCharacter);
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
	public List<Character> findCharactersByAge(int age, ComparisonType comparisonType)
	{
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public List<Character> getWaifus()
	{
		// TODO Auto-generated method stub
		return null;
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
			
			while (result.next())
			{
				Calendar calendar = new GregorianCalendar();
				calendar.set(result.getInt("DOBYear"), result.getInt("DOBMonth"),
						result.getInt("DOBDay"));
				Date dobDate = calendar.getTime();
				
				Character insteredCharacter = new Character();
				insteredCharacter.setBirthDate(dobDate);
				insteredCharacter.setId(result.getInt("CharacterID"));
				insteredCharacter.setArchetype(result.getString("Archetype"));
				insteredCharacter.setName(result.getString("Name"));
				insteredCharacter.setGender(Gender.valueOf(result.getString("Gender")));
				insteredCharacter.setHairColor(result.getString("HairColor"));
				character = insteredCharacter;
			}
			
			statement.execute();
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
			statement.setInt(1, character.getID());
			
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
