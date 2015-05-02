package edu.asu.ser322.data.model;

/**
 * Placeholder.
 * 
 * Model of a single user, identified by their username.
 * <p>
 * NOTE: the inclusion of {@link #password} is a potential security issue, and will likely
 * be removed, deprecated, or repurposed in a future version. It is currently included for
 * completeness.
 * 
 * @author Moore, Zachary
 *
 */
public class User
{
	public static final User NULL_USER = new User();
	
	private String username;
	private String password;
	private Character waifu;
	
	public String getUsername()
	{
		return username;
	}
	
	
	public User(String username, String password, Character waifu)
	{
		super();
		this.username = username;
		this.password = password;
		this.waifu = waifu;
	}
	
	public User()
	{
		super();
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public Character getWaifu()
	{
		return waifu;
	}
	
	public void setWaifu(Character waifu)
	{
		this.waifu = waifu;
	}
	
}
