package edu.asu.ser322.data.access;

public class DAOCollection
{
	public static UserDao getUserDao()
	{
		return new UserDaoSQL();
	}
	
	public static CharacterDao getCharacterDao()
	{
		return new CharacterDaoSQL();
	}
	
	public static SeasonDao getSeasonDao()
	{
		return new SeasonDaoSQL();
	}
	
	public static FranchiseDao getFranchiseDao()
	{
		return new FranchiseDaoSQL();
	}
}
