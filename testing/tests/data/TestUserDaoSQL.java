package tests.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.asu.ser322.data.Character;
import edu.asu.ser322.data.User;
import edu.asu.ser322.data.UserDao;
import edu.asu.ser322.data.UserDaoSQL;

public class TestUserDaoSQL
{
	private UserDao userDAO;
	
	@Before
	public void setUp() throws Exception
	{
		userDAO = new UserDaoSQL();
	}
	
	@After
	public void tearDown() throws Exception
	{
		userDAO = null;
	}
	
	@Test
	public void testAddUserNullWaifu()
	{
		String username = "TEST USER";
		String password = "password";
		Character waifu = null;
		
		User user = new User(username, password, waifu);
		userDAO.addUser(user);
		user = userDAO.findUser(username);
		
		assertNotNull(user);
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(waifu, user.getWaifu());
	}
	
}
