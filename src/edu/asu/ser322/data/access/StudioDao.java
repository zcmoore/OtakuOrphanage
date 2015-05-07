package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Person;
import edu.asu.ser322.data.model.Studio;
import edu.asu.ser322.data.model.User;

/**
 * 
 * @author Benjamin Paothatat
 * @author Moore, Zachary
 *
 */
public interface StudioDao
{
	/**
	 * Adds an employee to the persistent store by {@link Person} reference. The
	 * {@link Person} must be valid, and should be obtained via
	 * {@link PeopleDao#findPerson(String)} or another Dao that returns {@link People}
	 * results.
	 * 
	 * @param studioName
	 *            Employer's name
	 * @param employee
	 *            New Employee's reference (depends on id)
	 * @return true if the addition was successful. False otherwise
	 */
	public boolean addEmployee(String studioName, Person employee, String role);
	
	/**
	 * Adds an employee to the persistent store by name. If there is no person registered
	 * with the given name, a new person will be created. If there are multiple persons
	 * with the same name, this method will fail. In the latter case, use
	 * {@link #addEmployee(String, Person, String)}
	 * 
	 * @param studioName
	 *            Employer's name
	 * @param employee
	 *            New Employee's name
	 * @return true if the addition was successful. False otherwise
	 */
	public boolean addEmployee(String studioName, String personName, String role);
	
	/**
	 * Add a studio to the persistent store
	 * 
	 * @param studio
	 *            Studio data to add. Must be valid
	 * @return true if the studio was added successfully
	 */
	public boolean addStudio(Studio studio);
	
	/**
	 * Deletes a studio with the specified studioname
	 * 
	 * @param studio
	 *            Target studio's studioname
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean deleteStudio(String studioName);
	
	/**
	 * Finds the studio with the given studioname, and loads it from the persistent store
	 * 
	 * @param studioname
	 *            Target studio's studioname
	 * @return the Studio object, or {@link User#NULL_STUDIO} if a studio cannot be found
	 */
	public Studio findStudio(String studioName);
	
	public List<Studio> listAll();
	
	/**
	 * Removes an employee from the persistent store who matches the given studio,
	 * personID, and role.
	 * 
	 * @param studioName
	 *            Employer's name
	 * @param personName
	 *            Target Employee's reference (depends on id)
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean removeEmployee(String studioName, Person employee, String role);
	
	/**
	 * Removes all employees with the given name and role who are employed by the given
	 * studio.
	 * 
	 * @param studioName
	 *            Employer's name
	 * @param personName
	 *            Target Employee's name
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean removeEmployees(String studioName, String personName, String role);
	
	/**
	 * Checks that a studio is in the system
	 * 
	 * @param studioname
	 * @return true if a user with the specified studioname exists
	 */
	public boolean studioExists(String studioName);
	
	/**
	 * Updates a studio in the persistent store
	 * 
	 * @param studio
	 *            Target to update.
	 * @return true if the user was updated successfully. False if the update failed
	 */
	public boolean updateStudio(Studio studio);
	
}
