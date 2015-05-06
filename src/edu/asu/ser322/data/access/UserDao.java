package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.User;
import edu.asu.ser322.data.model.WatchRecord;

/**
 * Intermediate layer to communicate {@link User} data between a client and a persistent
 * store.
 * 
 * @author Moore, Zachary
 *
 */
public interface UserDao
{
	/**
	 * Add a user to the persistent store
	 * 
	 * @param user
	 *            User data to add. Must be valid
	 * @return true if the user was added successfully
	 */
	public boolean addUser(User user);
	
	/**
	 * Updates a user in the persistent store
	 * 
	 * @param user
	 *            Target to update.
	 * @return true if the user was updated successfully. False if the update failed, if
	 *         the specified user does not exist in the persistent store, or if the user
	 *         is invalid.
	 */
	public boolean updateUser(User user);
	
	/**
	 * Finds the user with the given username, and loads it from the persistent store
	 * 
	 * @param username
	 *            Target user's username
	 * @return the User object, or {@link User#NULL_USER} if a user cannot be found
	 */
	public User findUser(String username);
	
	/**
	 * Checks that a user is in the system
	 * 
	 * @param username
	 * @return true if a user with the specified username exists
	 */
	public boolean userExists(String username);
	
	/**
	 * Deletes a user with the specified username
	 * 
	 * @param series
	 *            Target user's username
	 * @return true if the deletion was successful. False otherwise
	 */
	public boolean deleteUser(String username);
	
	/**
	 * Marks that a user has watched the specified number of episodes of the given season.
	 * 
	 * @param user
	 * @param anime
	 * @param episodeCount
	 * @param rating
	 * @return
	 */
	public boolean registerWatch(User user, Season anime, int episodeCount, int rating);
	
	/**
	 * Finds a user and logs them in if password is correct. Note: this method is only
	 * responsible for updating the persistent store, if applicable. Application-level
	 * changes (such as GUI or context switches) will not be handled by this method, and
	 * should be handled by the client model.
	 * 
	 * Individual implementations of this method may have various fail criteria, such as a
	 * number of attempts allowed in a given period of time, or not allowing a user to
	 * login if they are already logged in, etc.
	 * 
	 * @param username
	 * @return true if the login was successful
	 */
	public boolean login(String username, String password);
	
	/**
	 * Notify the persistent store that the user has logged out. This may or may not be
	 * necessary, depending on the implementation, but it is safest to call this from the
	 * client whenever the user logs out.
	 * 
	 * @param username
	 * @return true if the logout was successful
	 */
	public boolean logout(String username, String password);
	
	List<User> listAll();
	
	List<WatchRecord> findWatchRecordsFor(String username);
	
	List<WatchRecord> listAllWatchRecords();
}
