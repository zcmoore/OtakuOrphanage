package edu.asu.ser322.data.access;

import java.util.List;
import java.util.Map;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Person;
import edu.asu.ser322.data.model.Season;

/**
 * 
 * @author Benjamin Paothatat
 *
 */
public interface PeopleDao
{
	/**
	 * Add a person to the persistent store
	 * 
	 * @param person
	 *            Person data to add. Must be valid
	 * @return true if the person was added successfully
	 */
	public boolean addPerson(String name);
	
	/**
	 * Finds the people with the given name, and loads it from the persistent store
	 * 
	 * @param name
	 *            Target people's name
	 * @return the List of persons, or an empty list
	 */
	public List<Person> findPerson(String name);
	
	/**
	 * Checks that a person is in the system
	 * 
	 * @param name
	 * @return true if a person with the specified name exists
	 */
	public boolean personExists(String name);
	
	List<Season> getSeasonsActedBy(Person person);

	List<Character> getCharactersActedBy(Person person);

	Map<String, Integer> getArchetypeDistributionOf(Person person);
}
