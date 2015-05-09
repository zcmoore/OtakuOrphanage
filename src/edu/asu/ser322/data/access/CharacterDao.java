package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Gender;

/**
 * Intermediate layer to communicate {@link Character} data between a client and a
 * persistent store.
 * 
 * @author Moore, Zachary
 * 
 */
public interface CharacterDao
{
	/**
	 * Add a character to the persistent store
	 * 
	 * @param character
	 *            Character data to add. Must be valid
	 * @return true if the data was added successfully
	 */
	public boolean addCharacter(Character character);
	
	public boolean characterExists(int id);
	
	public boolean deleteCharacter(Character chracter);
	
	public boolean deleteCharacter(int id);
	
	public Character findCharacter(int id);
	
	public List<Character> findCharactersByAge(int age, ComparisonType comparisonType);
	
	public List<Character> findCharactersByArchetype(String archetype);
	
	public List<Character> findCharactersByGender(Gender gender);
	
	public List<Character> findCharactersByHairColour(String colour);
	
	/**
	 * Finds all characters with the specified name, and loads them from the persistent
	 * store
	 * 
	 * @param name
	 *            Character's name
	 * @return a list of characters who's name is equal to the search term, or an empty
	 *         list if no matches were found
	 */
	public List<Character> findCharactersByName(String name);
	
	/**
	 * @return A list of characters who are claimed as waifus by one or more users
	 */
	public List<Character> getWaifus();
	
	public List<Character> listAll();
	
	/**
	 * Updates a character in the persistent store
	 * 
	 * @param character
	 *            Target to update.
	 * @return true if the data was updated successfully. False if the update failed, if
	 *         the specified entity does not exist in the persistent store, or if the
	 *         entity is invalid.
	 */
	public boolean updateCharacter(Character character);
	
	public boolean associateCharacterWithShow(int characterId, Episode episode,
			String role);
}
