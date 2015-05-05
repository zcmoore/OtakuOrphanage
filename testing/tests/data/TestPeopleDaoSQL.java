package tests.data;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.asu.ser322.data.access.PeopleDaoSQL;
import edu.asu.ser322.data.model.Person;

public class TestPeopleDaoSQL 
{
    PeopleDaoSQL peopleDaoSQL;
	@Before
	public void setUp() throws Exception 
	{
	    peopleDaoSQL = new PeopleDaoSQL();
	}

	@After
	public void tearDown() throws Exception 
	{
		peopleDaoSQL = null;
	}

	@Test
	public void test() 
	{
		peopleDaoSQL.addPerson("Zach");
		peopleDaoSQL.addPerson("Tmo");
		LinkedList<Person> test = (LinkedList<Person>) peopleDaoSQL.findPerson("Zach");
		boolean isBenInDatabase = peopleDaoSQL.personExists("Ben");
		assertEquals("Zach", test.get(0).getName());
		assertEquals(false, isBenInDatabase);
	}

}
