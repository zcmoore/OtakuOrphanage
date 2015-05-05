package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.User;

public interface EpisodeDao
{
	/**
	 * Add an episode to the persistent store
	 * 
	 * @param episode
	 *            Target to be added
	 * @return true if the episode was added successfully
	 */
	public boolean addEpisode(Episode episode);
	
	/**
	 * Updates an episode in the persistent store
	 * 
	 * @param episode
	 *            Target to update.
	 * @return true if the episode was updated successfully. False if the update failed
	 */
	public boolean updateEpisode(Episode episode);
	
	/**
	 * Finds the episode with the given episodeName, and loads it from the persistent
	 * store
	 * 
	 * @param episodeName
	 *            Target episode's name
	 * @return the episode object, or {@link User#NULL_EPISODE} if a episode cannot be
	 *         found
	 */
	public List<Episode> findEpisode(String episodeName);
	
	/**
	 * Checks that an episode is in the system
	 * 
	 * @param episodeName
	 * @return true if an episode with the specified episodeName exists
	 */
	public boolean episodeExists(String episodeName);
	
	/**
	 * Deletes an episode from the persistent store
	 * 
	 * @param episode
	 *            Target episode
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean deleteEpisode(Episode episode);
	
	/**
	 * Adds a characters appearance to the persistent store
	 * 
	 * @param episode
	 *            characterId Target episode and character
	 * @return true if the addition was successful. False otherwise
	 */
	public boolean addCharacterApperance(Episode episode, Character character);
	
	/**
	 * Deletes a characters appearance from the persistent store
	 * 
	 * @param episode
	 *            characterId Target episode and character
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean removeCharacterApperance(Episode episode, Character character);
	
	/**
	 * List of characters in episode from the persistent store
	 * 
	 * @param episode
	 *            Target episode
	 * @return list of character objects or an empty list
	 */
	public List<Character> listCharactersIn(Episode episode);

	List<Episode> listAll();
	
}
