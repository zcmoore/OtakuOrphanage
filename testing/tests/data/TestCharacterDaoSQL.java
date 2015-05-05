package tests.data;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.Gender;
import edu.asu.ser322.data.access.CharacterDaoSQL;

public class TestCharacterDaoSQL
{
	CharacterDaoSQL characterDaoSQL;
	
	@Before
	public void setUp() throws Exception
	{
		characterDaoSQL = new CharacterDaoSQL();
	}
	
	@After
	public void tearDown() throws Exception
	{
		characterDaoSQL = null;
	}
	
	@Test
	public void test()
	{
		Calendar calendar = new GregorianCalendar();
		calendar.set(2010, 10, 14);
		Date dobDate = calendar.getTime();
		
		Character testCharacter = new Character();
		testCharacter.setArchetype("Hero");
		testCharacter.setGender(Gender.valueOf("MALE"));
		testCharacter.setHairColor("Black");
		testCharacter.setId(1);
		testCharacter.setName("Zach");
		testCharacter.setBirthDate(dobDate);
		
		characterDaoSQL.addCharacter(testCharacter);
		
		Character testCharacter2 = characterDaoSQL.findCharacter(1);
		List<Character> testCharacter3 = characterDaoSQL
				.findCharactersByArchetype("Hero");
		List<Character> testCharacter4 = characterDaoSQL.findCharactersByGender(Gender
				.valueOf("MALE"));
		characterDaoSQL.deleteCharacter(1);
		assertEquals(testCharacter3.get(0).getName(), testCharacter4.get(0).getName());
		assertEquals(testCharacter2.getName(), "Zach");
	}
	
}
