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
	public static final Character NULL_CHARACTER = new Character();
	
	private int id;
	private Gender gender;
	private String archetype;
	private String name;
	private String hairColor;
	private Date birthDate;
	
	public Character()
	{
		super();
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
	
	public String getHairColor()
	{
		return hairColor;
	}
	
	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
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
