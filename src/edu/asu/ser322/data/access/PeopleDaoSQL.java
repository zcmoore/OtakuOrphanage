package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Person;

/**
 * 
 * @author Benjamin Paothatat
 * 
 */
public class PeopleDaoSQL implements PeopleDao {
	/**
	 * @return Connection to {@link SQL#CONNECTION_URL}, or null if a connection
	 *         cannot be established.
	 */
	private Connection createConnection() {
		Connection connection = null;

		try {
			Class.forName(SQL.DRIVER_PATH);
			connection = DriverManager.getConnection(SQL.CONNECTION_URL);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return connection;
	}

	@Override
	public boolean addPerson(String name) {
		boolean result = false;
		String sql = "INSERT INTO People(Name) VALUES(?)";

		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, name);

			statement.execute();
			result = true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Person> findPerson(String name) {
		String sql = "SELECT * FROM People WHERE Name=?";
		List<Person> people = new LinkedList<Person>();

		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				int resultPersonId = results.getInt("PersonID");
				String resultName = results.getString("Name");
				Person insertedPerson = new Person(resultPersonId, resultName);
				people.add(insertedPerson);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return people;
	}

	@Override
	public boolean personExists(String name) {
		return !(findPerson(name).isEmpty());
	}

}
