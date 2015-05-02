package edu.asu.ser322.data.model;

import java.util.Date;
import java.util.List;

/**
 * Model of a season, which belongs to a series and consist of episodes
 * 
 * @author Benjamin Paothatat
 *
 */
		
public class Season {
    private int seriesId;
    private int seasonNumber;
    private String name;
    private Date airDate;
    private Date finishDate;
    
    private List<Episodes> episodes;
    
	public int getSeriesId() 
	{
		return seriesId;
	}
	
	public void setSeriesId(int seriesId) 
	{
		this.seriesId = seriesId;
	}
	
	public int getSeasonNumber() 
	{
		return seasonNumber;
	}
	
	public void setSeasonNumber(int seasonNumber) 
	{
		this.seasonNumber = seasonNumber;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Date getAirDate() 
	{
		return airDate;
	}
	
	public void setAirDate(Date airDate) 
	{
		this.airDate = airDate;
	}
	
	public Date getFinishDate() 
	{
		return finishDate;
	}
	
	public void setFinishDate(Date finishDate) 
	{
		this.finishDate = finishDate;
	}
	
	public List<Episodes> getEpisodes() 
	{
		return episodes;
	}
	
	public void setEpisodes(List<Episodes> episodes)
	{
		this.episodes = episodes;
	}
    
    
    
}
