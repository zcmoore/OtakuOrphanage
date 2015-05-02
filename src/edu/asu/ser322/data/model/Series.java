package edu.asu.ser322.data.model;
/**
 * 
 * @author Benjamin Paothatat
 *
 */
public class Series 
{
	public static final Series NULL_SERIES = new Series();

	private int seriesId;
	private String seriesName;
	
	public Series()
	{
		super();
	}
	
	public Series(int seriesId, String seriesName)
	{
		this.seriesId = seriesId;
		this.seriesName = seriesName;
	}
	
	public int getSeriesId()
	{
		return seriesId;
	}
	
	public void setSeriesId(int seriesId)
	{
		this.seriesId = seriesId;
	}
	
	public String getSeriesName()
	{
		return seriesName;
	}
	
	public void setSeriesName(String seriesName)
	{
	    this.seriesName = seriesName;	
	}
	

}
