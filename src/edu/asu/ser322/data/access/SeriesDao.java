package edu.asu.ser322.data.access;

import edu.asu.ser322.data.model.Series;

public interface SeriesDao {
	/**
	 * Add a series to the persistent store
	 * 
	 * @param series
	 *            Series data to add. Must be valid
	 * @return true if the series was added successfully
	 */
    public boolean addSeries(Series series);
    
    /**
	 * Finds the series with the given series, and loads it from the persistent store
	 * 
	 * @param seriesname
	 *            Target series' seriesname
	 * @return the Series object, or null if a series cannot be found
	 */
    public Series findSeries(String seriesname);
    
    /**
	 * Checks that a series is in the system
	 * 
	 * @param seriesname
	 * @return true if a series with the specified series exists
	 */
	public boolean seriesExists(String seriesname);
	
	/**
	 * Deletes a user with the specified seriesname
	 * 
	 * @param seriesname
	 *            Target series's seriesname
	 * @return true if the deletion was successful. False otherwise
	 */
    public boolean deleteSeries(int seriesID);
}
