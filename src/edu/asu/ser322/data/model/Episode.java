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
public class Episode {

	private int seasonID;
	private int episodeNumber;
	private Date airDate;
	private String name;
	private String type;
	private String artStyle;
	private String approprateness;
	
	private List<Character> characters;
	
	public int getSeasonID() 
	{
		return seasonID;
	}
	
	public void setSeasonID(int seasonID) 
	{
		this.seasonID = seasonID;
	}
	
	public Date getAirDate() 
	{
		return airDate;
	}
	
	public void setAirDate(Date airDate) 
	{
		this.airDate = airDate;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
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

	public int getEpisodeNumber() 
	{
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) 
	{
		this.episodeNumber = episodeNumber;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
	
	
}
