package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Franchise;

/**
 * Intermediate layer to communicate {@link Franchise} data between a client and a
 * persistent store.
 * 
 * @author Benjamin Paothatat
 *
 */
public interface FranchiseDao
{
	/**
	 * Add a franchise to the persistent store
	 * 
	 * @param franchise
	 *            Franchise data to add. Must be valid
	 * @return true if the franchise was added successfully
	 */
	public boolean addFranchise(Franchise franchise);
	
	/**
	 * Updates a franchise in the persistent store
	 * 
	 * @param franchise
	 *            Target to update.
	 * @return true if the franchise was updated successfully. False if the update failed,
	 *         if the specified franchise does not exist in the persistent store, or if
	 *         the user is invalid.
	 */
	public boolean updateFranchise(Franchise franchise);
	
	/**
	 * Finds the franchise with the given name, and loads it from the persistent store
	 * 
	 * @param name
	 *            Target franchise's name
	 * @return the franchise object, or {@link Franchise#NULL_FRANCHISE} if a franchise
	 *         cannot be found
	 */
	public Franchise findFranchise(String name);
	
	/**
	 * Deletes a franchise with the specified name
	 * 
	 * @param name
	 *            Target franchise's name
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean deleteFranchise(String name);

	List<Franchise> listAll();
}
