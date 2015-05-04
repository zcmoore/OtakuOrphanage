package edu.asu.ser322.data.model;
/**
 * 
 * @author Benjamin Paothatat
 *
 */
public class Person 
{
    private int personId;
    private String name;
    
    public Person(int personIdInit, String nameInit)
    {
    	personId = personIdInit;
    	name = nameInit;
    }
    public int getPersonId()
    {
		return personId;
	}
    
	public void setPersonId(int personId) 
	{
		this.personId = personId;
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
