package edu.asu.ser322.data.model;

import java.util.Date;

/**
 * Placeholder.
 * 
 * Model of an anime character, identified by {@link Character#id}.
 * 
 * @author Moore, Zachary
 *
 */
public class Character
{
	private int id;
	private Gender gender;
	private String archetype;
	private String name;
	private String hairColour;
	private Date birthDate;
	
	public int getID()
	{
		return id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public Gender getGender()
	{
		return gender;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public String getArchetype()
	{
		return archetype;
	}
	
	public void setArchetype(String archetype)
	{
		this.archetype = archetype;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getHairColour()
	{
		return hairColour;
	}
	
	public void setHairColour(String hairColour)
	{
		this.hairColour = hairColour;
	}
	
	public Date getBirthDate()
	{
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}
	
	public int getAge()
	{
		// TODO calculate age from DOB
		throw new UnsupportedOperationException();
	}
	
}
