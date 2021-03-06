package edu.asu.ser322.data.model;

import java.util.Date;
import java.util.List;

/**
 * Model of a season, which consist of episodes
 * 
 * @author Benjamin Paothatat
 *
 */

public class Season
{
	public static final Season NULL_SEASON = new Season();
	
	private String seriesName;
	private int seasonNumber;
	private String name;
	private Date airDate;
	private Date finishDate;
	private List<String> genres;
	private String appropriateness;
	
	private List<Episode> episodes;
	
	public Season()
	{
		super();
	}
	
	public Season(String id, int seasonNumber)
	{
		this.seriesName = id;
		this.seasonNumber = seasonNumber;
	}
	
	public String getSeriesName()
	{
		return seriesName;
	}
	
	public void setSeriesName(String seriesId)
	{
		this.seriesName = seriesId;
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
	
	public List<Episode> getEpisodes()
	{
		return episodes;
	}
	
	public void setEpisodes(List<Episode> episodes)
	{
		this.episodes = episodes;
	}
	
	public String getAppropriateness()
	{
		return appropriateness;
	}
	
	public void setAppropriateness(String appropriateness)
	{
		this.appropriateness = appropriateness;
	}
	
	public List<String> getGenres()
	{
		return genres;
	}
	
	public void setGenres(List<String> genres)
	{
		this.genres = genres;
	}
	
}
