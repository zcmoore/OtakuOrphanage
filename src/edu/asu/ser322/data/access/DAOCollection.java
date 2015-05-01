package edu.asu.ser322.data.access;


public class DAOCollection
{
	public static UserDao getUserDao()
	{
		return new UserDaoSQL();
	}
	
	public static CharacterDao getCharacterDao()
	{
		// TODO
		throw new UnsupportedOperationException();
	}
}
