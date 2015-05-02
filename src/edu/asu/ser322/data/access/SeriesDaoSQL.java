package edu.asu.ser322.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import edu.asu.ser322.data.StorageFactory.SQL;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Series;
import edu.asu.ser322.data.model.User;

class SeriesDaoSQL implements SeriesDao
{
	/**
	 * @return Connection to {@link SQL#CONNECTION_URL}, or null if a connection cannot be
	 *         established.
	 */
	private Connection createConnection()
	{
		Connection connection = null;
		
		try
		{
			Class.forName(SQL.DRIVER_PATH);
			connection = DriverManager.getConnection(SQL.CONNECTION_URL);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return connection;
	}

	@Override
	public boolean addSeries(Series series) {
		boolean result = false;
		String sql = "INSERT INTO Series(SeriesID, SeriesName) VALUES(?, ?)";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, series.getSeriesId());
			statement.setString(2, series.getSeriesName());
			
			statement.execute();
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Series findSeries(String seriesname) {
		String sql = "SELECT * FROM Series WHERE SeriesName=?";
		Series series = Series.NULL_SERIES;
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setString(1, seriesname);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				int resultSeriesId = results.getInt("SeriesID");
				String resultSeriesName = results.getString("SeriesName");
				
				series = new Series(resultSeriesId, resultSeriesName);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return series;
	}

	@Override
	public boolean seriesExists(String seriesname) {
		return !Series.NULL_SERIES.equals(findSeries(seriesname));
	}

	@Override
	public boolean deleteSeries(int seriesID) {
		boolean result = false;
		String sql = "DELETE FROM Series WHERE SeriesID=?";
		
		try (Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);)
		{
			statement.setInt(1, seriesID);
			
			statement.execute();
			result = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return result;
	}

}
