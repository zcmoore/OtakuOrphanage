package edu.asu.ser322.interpreter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.SeasonDao;
import edu.asu.ser322.data.access.UserDao;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.User;

public class Interpreter
{
	
	public static void main(String[] args) throws IOException, JSONException
	{
		String[] usernames = new String[] { "Suika", "meme", "CCLulu" };
		for (String name : usernames)
			parseAndAddUserData(name);
	}
	
	public static void parseAndAddUserData(String username)
	{
		String baseURL = "http://hummingbird.me/api/v1/users/";
		String suffix = username + "/library";
		String url = baseURL + suffix;
		JSONArray json = readJsonArrayFromUrl(url);
		SeasonDao seasonDao = DAOCollection.getSeasonDao();
		UserDao userDao = DAOCollection.getUserDao();
		
		for (int i = 0; i < json.length(); i++)
		{
			JSONObject entryJSON = json.getJSONObject(i);
			JSONObject animeJSON = entryJSON.getJSONObject("anime");
			
			// User-Anime Relation Data
			int episodeCount = entryJSON.optInt("episodes_watched");
			int rewatchCount = entryJSON.optInt("rewatched_times");
			String watchStatus = entryJSON.optString("status");
			boolean rewatching = entryJSON.optBoolean("rewatching");
			
			// User-Rating
			JSONObject ratingJSON = entryJSON.optJSONObject("rating");
			double rating = (ratingJSON != null) ? ratingJSON.optDouble("value") : -1.0;
			
			// Anime Data
			Season anime = parseAnime(animeJSON);
			
			if (!seasonDao.seasonExists(anime))
				seasonDao.addSeason(anime);
			
			User user = userDao.findUser(username);
			int intRating = (int) rating * 2;
			if (!user.equals(User.NULL_USER))
				userDao.registerWatch(user, anime, episodeCount, intRating);
		}
	}
	
	private static Season parseAnime(JSONObject animeJSON)
	{
		int id = animeJSON.optInt("id");
		String airStatus = animeJSON.optString("status");
		String webURL = animeJSON.optString("url");
		String title = animeJSON.optString("title");
		String alternateTitle = animeJSON.optString("alternate_title");
		int numberOfEpisodes = animeJSON.optInt("episode_count");
		int episodeLength = animeJSON.optInt("episode_length");
		String imageURL = animeJSON.optString("cover_image");
		String ageRating = animeJSON.optString("age_rating");
		double averageRating = animeJSON.optDouble("community_rating");
		String type = animeJSON.optString("show_type");
		String synopsis = animeJSON.optString("synopsis");
		String startDateString = animeJSON.optString("started_airing"); // yyyy-mm-dd
		String endDateString = animeJSON.optString("finished_airing"); // yyyy-mm-dd
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date startDate = optDate(format, startDateString);
		Date endDate = optDate(format, endDateString);
		
		Season anime = new Season();
		anime.setAirDate(startDate);
		anime.setFinishDate(endDate);
		anime.setName(title);
		// TODO: determine how to connect seasons by series
		anime.setSeriesName(title);
		anime.setAppropriateness(ageRating);
		
		List<String> genres = fetchGenres(id);
		anime.setGenres(genres);
		
		List<Episode> episodes = new LinkedList<>();
		for (int index = 0; index < numberOfEpisodes; index++)
		{
			Episode episode = new Episode();
			episode.setApproprateness(ageRating);
			episode.setEpisodeNumber(index);
			episode.setType(type);
			// TODO: fetch additional episode information (art style, air date, etc)
			
			episodes.add(episode);
		}
		anime.setEpisodes(episodes);
		
		// TODO: determine actual season number
		anime.setSeasonNumber(1);
		
		return anime;
	}
	
	private static List<String> fetchGenres(int animeID)
	{
		String baseURL = "http://hummingbird.me/api/v1/anime/";
		String suffix = Integer.toString(animeID);
		String url = baseURL + suffix;
		JSONObject anime = readJsonObjectFromUrl(url);
		
		JSONArray genreArrayJSON = anime.optJSONArray("genres");
		List<String> genres = new LinkedList<>();
		for (int i = 0; i < genreArrayJSON.length(); i++)
		{
			JSONObject genreJSON = genreArrayJSON.getJSONObject(i);
			String genre = genreJSON.optString("name");
			
			genres.add(genre);
		}
		
		return genres;
	}
	
	private static Date optDate(SimpleDateFormat format, String dateString)
	{
		try
		{
			return format.parse(dateString);
		}
		catch (ParseException e)
		{
			return null;
		}
	}
	
	public static JSONArray readJsonArrayFromUrl(String urlString)
	{
		try
		{
			URL url = new URL(urlString);
			String jsonText = IOUtils.toString(url);
			JSONArray json = new JSONArray(jsonText);
			return json;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}
	
	public static JSONObject readJsonObjectFromUrl(String urlString)
	{
		try
		{
			URL url = new URL(urlString);
			String jsonText = IOUtils.toString(url);
			JSONObject json = new JSONObject(jsonText);
			return json;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}
}
