package edu.asu.ser322.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Factory to manufacture persistent stores for the OtakuOrphanage Anime Application. This
 * factory is intended to build persistent stores, but it will not <i>populate</i> the
 * store unless otherwise specified.
 * 
 * @author Moore, Zachary
 *
 */
public class StorageFactory
{
	public static final class SQL
	{
		public static final String DRIVER_PATH = "org.sqlite.JDBC";
		public static final String CONNECTION_URL = "jdbc:sqlite:orphanage.db";
	}
	
	/**
	 * Creates an SQLite database and places the store in the root project folder. If the
	 * database already exists, it will be overwritten.
	 * 
	 * @return True if the database was created successfully, false otherwise
	 */
	public boolean buildSQLDataBase()
	{
		try
		{
			// Initialize database driver
			Class.forName(SQL.DRIVER_PATH);
		}
		catch (ClassNotFoundException exception)
		{
			exception.printStackTrace();
			return false;
		}
		
		try (Connection dbConnection = DriverManager.getConnection(SQL.CONNECTION_URL);
				Statement sqlStatement = dbConnection.createStatement();)
		{
			dbConnection.createStatement().execute("PRAGMA foreign_keys = ON");
			String[] tableNames = new String[] { "Studios", "Employs", "People", "Staff",
					"Episodes", "CharacterAppearances", "SeasonEpisodeMap",
					"CharacterBirthDates", "FranchiseSeasonMap", "StudioFranchiseMap",
					"Users", "Watched", "Series", "Seasons", "Characters",
					"ActorAppearances", "Reviews", "Franchises", "ReviewMap" };
			String sql;
			
			for (String tableName : tableNames)
			{
				sql = "drop table if exists " + tableName + ";";
				sqlStatement.executeUpdate(sql);
			}
			
			// @formatter:off
            sql = "CREATE TABLE Studios(" +
            "StudioName    TEXT," +
            "StartDate     TEXT," +
            "CloseDate     TEXT," +
            "PRIMARY KEY (StudioName));";
            sqlStatement.execute(sql);

            sql = "CREATE TABLE Characters(" +
            "CharacterID   INTEGER," +
            "Name          TEXT," +
            "Gender        TEXT," +
            "DOB           TEXT," +
            "HairColor     TEXT," +
            "Archtype      TEXT," +
            "PRIMARY KEY (CharacterID));";
            sqlStatement.execute(sql);

            sql = "CREATE TABLE CharacterBirthDates(" +
            "CharacterID   INTEGER," +
            "Day           TEXT," +
            "Month         TEXT," +
            "Year          TEXT," +
            "PRIMARY KEY (CharacterID)," +
            "FOREIGN KEY(CharacterID) REFERENCES Characters(CharacterID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE Users(" +
            "Username      TEXT," +
            "Password      TEXT," +
            "Waifu         INTEGER," +
            "PRIMARY KEY (Username)," +
            "FOREIGN KEY(Waifu) REFERENCES Characters(CharacterID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE People(" +
            "PersonID      INTEGER       PRIMARY KEY    AUTOINCREMENT," +
            "Name          TEXT);";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE Reviews(" +
            "ReviewID        INTEGER," +
            "Description     TEXT," +
            "PRIMARY KEY (ReviewID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE Franchises(" +
            "FranchiseID    INTEGER," +
            "FranchiseName  TEXT," +
            "PRIMARY KEY (FranchiseID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE Seasons(" +
            "Series         TEXT," +
            "SeasonNumber   INTEGER," +
            "AirDate        TEXT," +
            "ShowName       TEXT," +
            "FinishDate     TEXT," +
            "PRIMARY KEY (Series, SeasonNumber));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE Episodes(" +
            "Series         TEXT," +
            "Season         INTEGER," +
            "EpisodeNumber  INTEGER," +
            "AirDate        TEXT," +
            "Name           TEXT," +
            "Type           TEXT," +
            "ArtStyle       TEXT," +
            "Approprateness TEXT," +
            "PRIMARY KEY (Series, Season, EpisodeNumber)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE Employs(" +
            "Employer      TEXT," +
            "Employee      INTEGER," +
            "Position      TEXT," +
            "PRIMARY KEY (Employer, Employee, Position)," +
            "FOREIGN KEY(Employer) REFERENCES Studios(StudioName)," +
            "FOREIGN KEY(Employee) REFERENCES People(PersonID));";
            sqlStatement.execute(sql);
            
            sql = "CREATE TABLE Staff(" +
            "Employee      INTEGER," +
            "Series        TEXT," +
            "Season        INTEGER," +
            "Episode       INTEGER," +
            "Role          TEXT," +
            "PRIMARY KEY (Employee, Series, Season, Episode, Role)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber)," +
            "FOREIGN KEY(Episode) REFERENCES Episodes(EpisodeNumber)," +
            "FOREIGN KEY(Employee) REFERENCES People(PersonID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE CharacterAppearances(" +
            "Character     INTEGER," +
            "Series        TEXT," +
            "Season        INTEGER," +
            "Episode       INTEGER," +
            "Role          TEXT," +
            "PRIMARY KEY (Character, Series, Season, Episode)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber)," +
            "FOREIGN KEY(Episode) REFERENCES Episodes(EpisodeNumber)," +
            "FOREIGN KEY(Character) REFERENCES Characters(CharacterID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE ActorAppearances(" +
            "Actor         INTEGER," +
            "Character     INTEGER," +
            "Series        TEXT," +
            "Season        INTEGER," +
            "PRIMARY KEY (Actor, Character, Series, Season)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber)," +
            "FOREIGN KEY(Actor) REFERENCES People(PersonID)," +
            "FOREIGN KEY(Character) REFERENCES Characters(CharacterID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE SeasonEpisodeMap(" +
            "Series        TEXT," +
            "Season        INTEGER," +
            "Episode       INTEGER," +
            "PRIMARY KEY (Series, Season, Episode)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber)," +
            "FOREIGN KEY(Episode) REFERENCES Episodes(EpisodeNumber));";
            sqlStatement.execute(sql);
            
            sql = "CREATE TABLE FranchiseSeasonMap(" +
            "Franchise     INTEGER," +
            "Series        TEXT," +
            "Season        INTEGER," +
            "PRIMARY KEY (Series, Franchise)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber)," +
            "FOREIGN KEY(Franchise) REFERENCES Franchises(FranchiseID));";
            sqlStatement.execute(sql);
           
            sql = "CREATE TABLE StudioFranchiseMap(" +
            "Franchise     INTEGER," +
            "Studio        TEXT," +
            "PRIMARY KEY (Studio, Franchise)," +
            "FOREIGN KEY(Studio) REFERENCES Studios(StudioName)," +
            "FOREIGN KEY(Franchise) REFERENCES Franchises(FranchiseID));";
            sqlStatement.execute(sql);
            
            sql = "CREATE TABLE Watched(" +
            "User          TEXT," +
            "Series        TEXT," +
            "Season        INTEGER," +
            "EpisodeCount  INTEGER," +
            "PRIMARY KEY (User, Series, Season)," +
            "FOREIGN KEY(Series) REFERENCES Seasons(Series)," +
            "FOREIGN KEY(Season) REFERENCES Seasons(SeasonNumber));";
            sqlStatement.execute(sql);
            
            sql = "CREATE TABLE ReviewMap(" +
            "User          TEXT," +
            "Review        INTEGER," +
            "Date          TEXT," +
            "PRIMARY KEY (User, Review)," +
            "FOREIGN KEY(User) REFERENCES Users(Username)," +
            "FOREIGN KEY(Review) REFERENCES Reviews(ReviewID));";
            sqlStatement.execute(sql);
           
            // @formatter:on
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
