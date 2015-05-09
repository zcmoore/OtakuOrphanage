package edu.asu.ser322.data.model;

public class WatchRecord
{
	private User user;
	private Season season;
	private int episodesWatched;
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Season getSeason()
	{
		return season;
	}
	
	public void setSeason(Season season)
	{
		this.season = season;
	}
	
	public int getEpisodesWatched()
	{
		return episodesWatched;
	}
	
	public void setEpisodesWatched(int episodesWatched)
	{
		this.episodesWatched = episodesWatched;
	}
	
}
