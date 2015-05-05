package edu.asu.ser322.data.model;

import java.util.Date;

/**
 * 
 * @author Benjamin Poathatat
 *
 */

public class Review
{
	private String userName;
	private String seriesName;
	private int seasonNumber;
	private Date datePosted;
	private String writtenReview;
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getSeriesName()
	{
		return seriesName;
	}
	
	public void setSeriesName(String seriesName)
	{
		this.seriesName = seriesName;
	}
	
	public int getSeasonNumber()
	{
		return seasonNumber;
	}
	
	public void setSeasonNumber(int seasonNumber)
	{
		this.seasonNumber = seasonNumber;
	}
	
	public Date getDatePosted()
	{
		return datePosted;
	}
	
	public void setDatePosted(Date datePosted)
	{
		this.datePosted = datePosted;
	}
	
	public String getWrittenReview()
	{
		return writtenReview;
	}
	
	public void setWrittenReview(String writtenReview)
	{
		this.writtenReview = writtenReview;
	}
	
}
