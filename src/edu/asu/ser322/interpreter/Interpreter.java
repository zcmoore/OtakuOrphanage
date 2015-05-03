package edu.asu.ser322.interpreter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
			Object anime = parseAnime(animeJSON);
		}
	}
	
	private static Object parseAnime(JSONObject animeJSON)
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
		
		System.out.println(title);
		
		return null;
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
