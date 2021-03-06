package edu.asu.ser322.data.model;

/**
 * A franchise represents a collection of related series and seasons. The members in a
 * franchise may be different seasons of the same series, or different, but related
 * series.
 * <p>
 * For instance, <a href="http://en.wikipedia.org/wiki/Chaos;Head">Chaos;Head</a> and <a
 * href="http://en.wikipedia.org/wiki/Steins;Gate>Steins;Gate</a> are in the same
 * franchise, but are two different series/shows.
 * 
 * @author Moore, Zachary
 *
 */
public class Franchise
{
	public static final Franchise NULL_FRANCHISE = new Franchise();
	
	private int id;
	private String name;
	
	public Franchise()
	{
		super();
	}
	
	public Franchise(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
