package edu.asu.ser322.data.access;

import java.util.List;

import edu.asu.ser322.data.model.Season;

/**
 * 
 * @author Benjamin Paothatat
 * @author Moore, Zachary
 *
 */
public interface SeasonDao
{
	/**
	 * Add a season to the persistent store
	 * 
	 * @param season
	 *            Series data to add. Must be valid
	 * @return true if the season was added successfully
	 */
	public boolean addSeason(Season season);
	
	/**
	 * Updates a season in the persistent store
	 * 
	 * @param season
	 *            Target to update.
	 * @return true if the season was updated successfully. False if the update failed
	 */
	public boolean updateSeason(Season season);
	
	/**
	 * Finds the seasons with the given seriesname, and loads it from the persistent store
	 * 
	 * @param seriesname
	 *            Target series' seriesname
	 * @return the a list of seasons, or null if a series cannot be found
	 */
	public List<Season> findSeasonsBySeriesName(String seriesname);
	
	/**
	 * Finds the seasons that aired in the specified year and loads them from the
	 * persistent store
	 * 
	 * @param year
	 *            Target seasons' year
	 * @return the a list of seasons, or null if a series cannot be found
	 */
	public List<Season> seasonsByAirYear(int airYear);
	
	/**
	 * Remove a season from the persistent store
	 * 
	 * @param seriesName
	 *            , seasonNumber Series data to add. Must be valid
	 * @return true if the deletion was successfully
	 */
	public boolean removeSeason(String seriesName, int seasonNumber);
	
	/**
	 * Fetch a single season given its series name and season number. No two seasons will
	 * share the same seasonName and seriesNumber.
	 * 
	 * @param seriesname
	 * @param seasonnumber
	 * @return
	 */
	public Season findSeason(String seriesname, int seasonnumber);
	
	/**
	 * Finds the seasons that consist of a specified genre and loads them from the
	 * persistent store
	 * 
	 * @param genre
	 *            Target seasons' genre
	 * @return the a list of seasons, or null if a series cannot be found
	 */
	public List<Season> seasonsByGenre(String genre);
	
	List<String> listSeries();
	
	List<Season> listAll();
	
	/**
	 * @param seriesName
	 * @return True if at least one season exists for the given seriesName
	 */
	boolean seasonExists(String seriesName);
	
	boolean seasonExists(String seriesName, int seasonNumber);

	boolean seasonExists(Season season);
}
