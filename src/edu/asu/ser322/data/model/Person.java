package edu.asu.ser322.data.model;

/**
 * 
 * Model of Persons
 * 
 * @author Benjamin Paothatat
 *
 */
public class Person
{
	private int id;
	private String name;
	
	public Person(int personIdInit, String nameInit)
	{
		id = personIdInit;
		name = nameInit;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setID(int id)
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
