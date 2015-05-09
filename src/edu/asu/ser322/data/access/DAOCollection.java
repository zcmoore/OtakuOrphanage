package edu.asu.ser322.data.access;

public class DAOCollection
{
	public static CharacterDao getCharacterDao()
	{
		return new CharacterDaoSQL();
	}
	
	public static EpisodeDao getEpisodeDao()
	{
		return new EpisodeDaoSQL();
	}
	
	public static FranchiseDao getFranchiseDao()
	{
		return new FranchiseDaoSQL();
	}
	
	public static PeopleDao getPeopleDao()
	{
		return new PeopleDaoSQL();
	}
	
	public static SeasonDao getSeasonDao()
	{
		return new SeasonDaoSQL();
	}
	
	public static StudioDao getStudioDao()
	{
		return new StudioDaoSQL();
	}
	
	public static UserDao getUserDao()
	{
		return new UserDaoSQL();
	}
}
