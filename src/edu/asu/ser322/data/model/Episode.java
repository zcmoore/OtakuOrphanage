package edu.asu.ser322.data.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * Model of an episode
 * 
 * @author Benjamin Paothatat
 *
 */
public class Episode
{
	
	public static final Episode NULL_EPISODE = new Episode();
	
	private String seriesName;
	private int seasonNumber;
	private int episodeNumber;
	private Date airDate;
	private String EpisodeName;
	private String type;
	private String artStyle;
	private String approprateness;
	
	private List<Character> characters;
	
	public Episode()
	{
		super();
	}
	
	public Episode(String seriesName, int seasonNumber, int episodeNumber)
	{
		this.seriesName = seriesName;
		this.seasonNumber = seasonNumber;
		this.episodeNumber = episodeNumber;
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
	
	public int getEpisodeNumber()
	{
		return episodeNumber;
	}
	
	public void setEpisodeNumber(int episodeNumber)
	{
		this.episodeNumber = episodeNumber;
	}
	
	public Date getAirDate()
	{
		return airDate;
	}
	
	public void setAirDate(Date airDate)
	{
		this.airDate = airDate;
	}
	
	public String getEpisodeName()
	{
		return EpisodeName;
	}
	
	public void setEpisodeName(String name)
	{
		this.EpisodeName = name;
	}
	
	public String getArtStyle()
	{
		return artStyle;
	}
	
	public void setArtStyle(String artStyle)
	{
		this.artStyle = artStyle;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getApproprateness()
	{
		return approprateness;
	}
	
	public void setApproprateness(String approprateness)
	{
		this.approprateness = approprateness;
	}
	
	public List<Character> getCharacters()
	{
		return characters;
	}
	
	public void setCharacters(List<Character> characters)
	{
		this.characters = characters;
	}
	
}
