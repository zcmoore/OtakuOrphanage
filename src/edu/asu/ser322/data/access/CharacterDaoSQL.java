package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.List;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Gender;

public class CharacterDaoSQL implements CharacterDao {

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
		String sql = "INSERT INTO Characters(CharacterID, Name, Gender, DOB, HairColor, Archtype) "
						+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, character.getId());
			statement.setString(2, character.getName());
			//FIXME: Not sure if toString will give proper value for Enum of Gender
			statement.setString(3, character.getGender().toString());
			SimpleDateFormat dateformateyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
			String date_to_string = dateformateyyyyMMdd.format(character.getBirthDate()); 
			statement.setString(4, date_to_string);
            statement.setString(5, character.getHairColor());
			statement.setString(6, character.getArchetype());
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
	public boolean updateCharacter(Character character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Character> findCharactersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> findCharactersByHairColour(String colour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> findCharactersByArchetype(String archetype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> findCharactersByGender(Gender gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> findCharactersByAge(int age,
			ComparisonType comparisonType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> getWaifus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Character findCharacter(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean characterExists(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCharacter(int id) {
		boolean result = false;
		String sql = "DELETE FROM Character WHERE CharacterID=?";
		
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
	public boolean deleteCharacter(Character chracter) {
		// TODO Auto-generated method stub
		return false;
	}

}
