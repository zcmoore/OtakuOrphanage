package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Studio;
import edu.asu.ser322.data.model.User;

/**
 * 
 * @author Benjamin Paothatat
 * @author Moor, Zachary
 *
 */
public interface StudioDao
{
	/**
	 * Add a studio to the persistent store
	 * 
	 * @param studio
	 *            Studio data to add. Must be valid
	 * @return true if the studio was added successfully
	 */
	public boolean addStudio(Studio studio);
	
	/**
	 * Updates a studio in the persistent store
	 * 
	 * @param studio
	 *            Target to update.
	 * @return true if the user was updated successfully. False if the update failed
	 */
	public boolean updateStudio(Studio studio);
	
	/**
	 * Finds the studio with the given studioname, and loads it from the persistent store
	 * 
	 * @param studioname
	 *            Target studio's studioname
	 * @return the Studio object, or {@link User#NULL_STUDIO} if a studio cannot be found
	 */
	public Studio findStudio(String studioname);
	
	/**
	 * Checks that a studio is in the system
	 * 
	 * @param studioname
	 * @return true if a user with the specified studioname exists
	 */
	public boolean studioExists(String studioname);
	
	/**
	 * Deletes a studio with the specified studioname
	 * 
	 * @param studio
	 *            Target studio's studioname
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean deleteStudio(String studioname);
	
	/**
	 * Adds an employee to the persistent store
	 * 
	 * @param studioname
	 *            personname Target studio's name and person' name
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean addEmployee(String studioname, String personname, String role);
	
	/**
	 * Removes an employee from the persistent store
	 * 
	 * @param studioname
	 *            personname Target studio's name and person' name
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean removeEmployee(String studioname, String personname, String role);
	
	List<Studio> listAll();
	
}
