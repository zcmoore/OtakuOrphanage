package edu.asu.ser322.populate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.access.UserDao;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Franchise;
import edu.asu.ser322.data.model.Gender;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.Studio;
import edu.asu.ser322.data.model.Character;
import edu.asu.ser322.data.model.User;

public class InsertVales
{
	public static void main(String[] args)
	{
		
		/**
		 * adding user
		 */
		User user = new User("Bansal", "1234", null);
		UserDao dao = DAOCollection.getUserDao();
		dao.addUser(user);
		
		/**
		 * adding Studios
		 */
		Calendar calendarTokyo = new GregorianCalendar();
		calendarTokyo.set(1964, 4, 12);
		Date airDateTokyo = calendarTokyo.getTime();
		Studio studioTokyo = new Studio("TV Tokyo", airDateTokyo);
		DAOCollection.getStudioDao().addStudio(studioTokyo);

		Calendar calendarViz = new GregorianCalendar();
		calendarViz.set(1986, 1, 1);
		Date airDateViz = calendarViz.getTime();
		Studio studioViz = new Studio("Viz media", airDateViz);
		DAOCollection.getStudioDao().addStudio(studioViz);

		Calendar calendarAni = new GregorianCalendar();
		calendarAni.set(1995, 9, 1);
		Date airDateAni = calendarAni.getTime();
		Studio studioAni = new Studio("Aniplex", airDateAni);
		DAOCollection.getStudioDao().addStudio(studioAni);

		/**
		 * adding Franchises
		 */
		Franchise narutoF = new Franchise();
		narutoF.setId(1);
		narutoF.setName("Naruto");
		DAOCollection.getFranchiseDao().addFranchise(narutoF);

		Franchise AngelBeatsF = new Franchise();
		AngelBeatsF.setId(2);
		AngelBeatsF.setName("Angel Beats");
		DAOCollection.getFranchiseDao().addFranchise(AngelBeatsF);

		Franchise godOnlyKnows = new Franchise();
		godOnlyKnows.setId(3);
		godOnlyKnows.setName("The World God Only Know");
		DAOCollection.getFranchiseDao().addFranchise(godOnlyKnows);

		Franchise blueExorcist = new Franchise();
		blueExorcist.setId(4);
		blueExorcist.setName("Blue Exorcist");
		DAOCollection.getFranchiseDao().addFranchise(blueExorcist);

		Franchise hamatoraA = new Franchise();
		hamatoraA.setId(5);
		hamatoraA.setName("Hamatora The Animation");
		DAOCollection.getFranchiseDao().addFranchise(hamatoraA);

		Franchise bakemonogatari = new Franchise();
		bakemonogatari.setId(6);
		bakemonogatari.setName("Bakemonogatari");
		DAOCollection.getFranchiseDao().addFranchise(bakemonogatari);

		Franchise breakBlade = new Franchise();
		breakBlade.setId(7);
		breakBlade.setName("Break Blade");
		DAOCollection.getFranchiseDao().addFranchise(breakBlade);

		Franchise toLoveRu = new Franchise();
		toLoveRu.setId(8);
		toLoveRu.setName("To Love-Ru");
		DAOCollection.getFranchiseDao().addFranchise(toLoveRu);

		Franchise soulEater = new Franchise();
		soulEater.setId(9);
		soulEater.setName("Soul Eater");
		DAOCollection.getFranchiseDao().addFranchise(soulEater);

		/**
		 * adding people
		 */
		DAOCollection.getPeopleDao().addPerson("Chie Nakamura");
		DAOCollection.getPeopleDao().addPerson("Kana Hanazawa");
		DAOCollection.getPeopleDao().addPerson("Akeno Watanabe");

		/**
		 * adding characters
		 */
		Character sumiI = new Character();
		sumiI.setId(1);
		sumiI.setName("Sumi Ishihara");
		sumiI.setGender(Gender.valueOf("FEMALE"));
		sumiI.setAge(28);
		sumiI.setArchetype("Justice Seeker");
		sumiI.setHairColor("Black");

		Character sakuraH = new Character();
		sakuraH.setId(2);
		sakuraH.setName("Sakura Haruna");
		sakuraH.setGender(Gender.valueOf("FEMALE"));
		sakuraH.setAge(16);
		sakuraH.setArchetype("Justice Seeker");
		sakuraH.setHairColor("Pink");

		Character shioriS = new Character();
		shioriS.setId(3);
		shioriS.setName("Shiori Shiomiya");
		shioriS.setGender(Gender.valueOf("FEMALE"));
		shioriS.setAge(17);
		shioriS.setArchetype("Quite");
		shioriS.setHairColor("Black");

		Character kanadeT = new Character();
		kanadeT.setId(4);
		kanadeT.setName("Kanade Tachibana");
		kanadeT.setGender(Gender.valueOf("FEMALE"));
		kanadeT.setAge(16);
		kanadeT.setArchetype("Quite-Hero");
		kanadeT.setHairColor("White");

		Character rinOku = new Character();
		rinOku.setId(5);
		rinOku.setName("Rin Okumura");
		rinOku.setGender(Gender.valueOf("MALE"));
		rinOku.setAge(15);
		rinOku.setArchetype("Hero");
		rinOku.setHairColor("Black");

		Character bDay = new Character();
		bDay.setId(6);
		bDay.setName("Birthday");
		bDay.setGender(Gender.valueOf("MALE"));
		bDay.setAge(23);
		bDay.setArchetype("Carefree");
		bDay.setHairColor("Orange");

		Character nadekoS = new Character();
		nadekoS.setId(7);
		nadekoS.setName("Nadeko Sengoku");
		nadekoS.setGender(Gender.valueOf("FEMALE"));
		nadekoS.setAge(14);
		nadekoS.setArchetype("Shy");
		nadekoS.setHairColor("Black");

		Character cleoZ = new Character();
		cleoZ.setId(8);
		cleoZ.setName("Cleo Zaubraff");
		cleoZ.setGender(Gender.valueOf("FEMALE"));
		cleoZ.setAge(12);
		cleoZ.setArchetype("Clumsy");
		cleoZ.setHairColor("Black");

		Character LizThomp = new Character();
		LizThomp.setId(9);
		LizThomp.setName("Elizabeth Thompson");
		LizThomp.setGender(Gender.valueOf("FEMALE"));
		LizThomp.setAge(23);
		LizThomp.setArchetype("under-Dog");
		LizThomp.setHairColor("Blonde");

		Character ritoYuu = new Character();
		ritoYuu.setId(10);
		ritoYuu.setName("Rito Yuuki");
		ritoYuu.setGender(Gender.valueOf("MALE"));
		ritoYuu.setAge(16);
		ritoYuu.setArchetype("Protaganist");
		ritoYuu.setHairColor("Orange");

		DAOCollection.getCharacterDao().addCharacter(sumiI);
		DAOCollection.getCharacterDao().addCharacter(sakuraH);
		DAOCollection.getCharacterDao().addCharacter(shioriS);
		DAOCollection.getCharacterDao().addCharacter(kanadeT);
		DAOCollection.getCharacterDao().addCharacter(rinOku);
		DAOCollection.getCharacterDao().addCharacter(bDay);
		DAOCollection.getCharacterDao().addCharacter(nadekoS);
		DAOCollection.getCharacterDao().addCharacter(cleoZ);
		DAOCollection.getCharacterDao().addCharacter(LizThomp);
		DAOCollection.getCharacterDao().addCharacter(ritoYuu);

		/**
		 * adding Season
		 */
		Calendar calendarNaruto = new GregorianCalendar();
		calendarNaruto.set(2002, 10, 3);
		Date airDateNaruto = calendarNaruto.getTime();

		Calendar calendarNaruto2 = new GregorianCalendar();
		calendarNaruto2.set(2007, 2, 8);
		Date finishDateNaruto = calendarNaruto2.getTime();

		Season narutoSeason = new Season("Nartuo", 1);
		narutoSeason.setName("Naruto");
		narutoSeason.setAirDate(airDateNaruto);
		narutoSeason.setFinishDate(finishDateNaruto);
		narutoSeason.setAppropriateness("PG-13");

		Calendar calendarNaruto3 = new GregorianCalendar();
		calendarNaruto3.set(2007, 2, 15);
		Date airDateNaruto3 = calendarNaruto3.getTime();

		Season narutoSeason2 = new Season("Nartuo", 2);
		narutoSeason2.setName("Naruto: Shippuuden");
		narutoSeason2.setAirDate(airDateNaruto3);
		narutoSeason2.setAppropriateness("PG-13");

		Calendar calendarAngel = new GregorianCalendar();
		calendarAngel.set(2010, 4, 3);
		Date airDateAngel = calendarAngel.getTime();

		Calendar calendarAngel2 = new GregorianCalendar();
		calendarAngel2.set(2010, 6, 26);
		Date finishDateAngel = calendarAngel2.getTime();

		Season angelBeats = new Season("Angel Beats!", 1);
		angelBeats.setName("Angel Beats!");
		angelBeats.setAirDate(airDateAngel);
		angelBeats.setFinishDate(finishDateAngel);
		angelBeats.setAppropriateness("PG-13");

		Calendar calendarAngel3 = new GregorianCalendar();
		calendarAngel3.set(2010, 12, 22);
		Date airDateAngel2 = calendarAngel3.getTime();

		Calendar calendarAngel4 = new GregorianCalendar();
		calendarAngel4.set(2010, 12, 22);
		Date finishDateAngel2 = calendarAngel4.getTime();

		Season angelBeats2 = new Season("Angel Beats!", 2);
		angelBeats2.setName("Angel Beats: Another Epilogue");
		angelBeats2.setAirDate(airDateAngel2);
		angelBeats2.setFinishDate(finishDateAngel2);
		angelBeats2.setAppropriateness("PG-13");

		Calendar calendarGodOnly = new GregorianCalendar();
		calendarGodOnly.set(2010, 10, 7);
		Date airDateGodOnly = calendarGodOnly.getTime();

		Calendar calendarGodOnly2 = new GregorianCalendar();
		calendarGodOnly2.set(2010, 12, 23);
		Date finishDateGodOnly = calendarGodOnly2.getTime();

		Season theWorldGodOnlyKnow = new Season("The World God Only Knows", 1);
		theWorldGodOnlyKnow.setName("The World God Only Knows");
		theWorldGodOnlyKnow.setAirDate(airDateGodOnly);
		theWorldGodOnlyKnow.setFinishDate(finishDateGodOnly);
		theWorldGodOnlyKnow.setAppropriateness("PG-13");

		Calendar calendarGodOnly3 = new GregorianCalendar();
		calendarGodOnly3.set(2011, 4, 12);
		Date airDateGodOnly1 = calendarGodOnly3.getTime();

		Calendar calendarGodOnly4 = new GregorianCalendar();
		calendarGodOnly4.set(2010, 6, 28);
		Date finishDateGodOnly1 = calendarGodOnly4.getTime();

		Season theWorldGodOnlyKnow2 = new Season("The World God Only Knows", 2);
		theWorldGodOnlyKnow2.setName("The World God Only Knows II");
		theWorldGodOnlyKnow2.setAirDate(airDateGodOnly1);
		theWorldGodOnlyKnow2.setFinishDate(finishDateGodOnly1);
		theWorldGodOnlyKnow2.setAppropriateness("PG-13");

		Calendar calendarBlueExorcist = new GregorianCalendar();
		calendarBlueExorcist.set(2011, 4, 17);
		Date airDateBlueExorcist = calendarBlueExorcist.getTime();

		Calendar calendarBlueExorcist1 = new GregorianCalendar();
		calendarBlueExorcist1.set(2011, 10, 2);
		Date finishDateBlueExorcist = calendarBlueExorcist1.getTime();

		Season blueExorcist1 = new Season("Blue Exorcist", 1);
		blueExorcist1.setName("Blue Exorcist");
		blueExorcist1.setAirDate(airDateBlueExorcist);
		blueExorcist1.setFinishDate(finishDateBlueExorcist);
		blueExorcist1.setAppropriateness("PG-13");

		Calendar calendarBlueExorcist2 = new GregorianCalendar();
		calendarBlueExorcist2.set(2011, 10, 26);
		Date airDateBlueExorcist1 = calendarBlueExorcist2.getTime();

		Season blueExorcist2 = new Season("Blue Exorcist", 2);
		blueExorcist2.setName("Blue Exorcist: Runaway Kuro");
		blueExorcist2.setAirDate(airDateBlueExorcist1);
		blueExorcist2.setFinishDate(airDateBlueExorcist1);
		blueExorcist2.setAppropriateness("PG-13");

		DAOCollection.getSeasonDao().addSeason(narutoSeason);
		DAOCollection.getSeasonDao().addSeason(narutoSeason2);
		DAOCollection.getSeasonDao().addSeason(angelBeats);
		DAOCollection.getSeasonDao().addSeason(angelBeats2);
		DAOCollection.getSeasonDao().addSeason(theWorldGodOnlyKnow);
		DAOCollection.getSeasonDao().addSeason(theWorldGodOnlyKnow2);
		DAOCollection.getSeasonDao().addSeason(blueExorcist1);
		DAOCollection.getSeasonDao().addSeason(blueExorcist2);

		/**
		 * adding Episodes
		 */
		Episode episodeNaruto1 = new Episode("Naruto", 1, 1);
		episodeNaruto1.setAirDate(airDateNaruto);
		episodeNaruto1.setApproprateness("PG-13");
		episodeNaruto1.setEpisodeName("Enter: Naruto Uzumaki!");
		episodeNaruto1.setArtStyle("Traditional");
		episodeNaruto1.setType("TV Show");

		Episode episodeNaruto2 = new Episode("Naruto", 1, 220);
		episodeNaruto2.setAirDate(finishDateNaruto);
		episodeNaruto2.setApproprateness("PG-13");
		episodeNaruto2.setEpisodeName("Departure");
		episodeNaruto2.setArtStyle("Traditional");
		episodeNaruto2.setType("TV Show");

		Episode episodeNaruto3 = new Episode("Naruto", 2, 1);
		episodeNaruto3.setAirDate(airDateNaruto3);
		episodeNaruto3.setApproprateness("PG-13");
		episodeNaruto3.setEpisodeName("Homecoming");
		episodeNaruto3.setArtStyle("Traditional");
		episodeNaruto3.setType("TV Show");

		Calendar currentNaruto = new GregorianCalendar();
		currentNaruto.set(2015, 5, 14);
		Date currentDateNaruto = currentNaruto.getTime();

		Episode episodeNaruto4 = new Episode("Naruto", 2, 412);
		episodeNaruto4.setAirDate(currentDateNaruto);
		episodeNaruto4.setApproprateness("PG-13");
		episodeNaruto4.setEpisodeName("Neji's Judgement");
		episodeNaruto4.setArtStyle("Traditional");
		episodeNaruto4.setType("TV Show");

		Episode episodeAngel = new Episode("Angel Beats!", 1, 1);
		episodeAngel.setAirDate(airDateAngel);
		episodeAngel.setApproprateness("PG-13");
		episodeAngel.setEpisodeName("Departure");
		episodeAngel.setArtStyle("Traditional");
		episodeAngel.setType("TV Show");

		Episode episodeAngel1 = new Episode("Angel Beats!", 1, 13);
		episodeAngel1.setAirDate(finishDateAngel);
		episodeAngel1.setApproprateness("PG-13");
		episodeAngel1.setEpisodeName("Graduation");
		episodeAngel1.setArtStyle("Traditional");
		episodeAngel1.setType("TV Show");

		Episode episodeAngel2 = new Episode("Angel Beats!", 2, 1);
		episodeAngel2.setAirDate(airDateAngel2);
		episodeAngel2.setApproprateness("PG-13");
		episodeAngel2.setEpisodeName("Stairway to Heaven");
		episodeAngel2.setArtStyle("Traditional");
		episodeAngel2.setType("TV Show");

		Episode episodeWorld = new Episode("The World God Only Knows", 1, 1);
		episodeWorld.setAirDate(airDateGodOnly);
		episodeWorld.setApproprateness("PG-13");
		episodeWorld.setEpisodeName("Flag 1.0 Love Makes the World Go Round");
		episodeWorld.setArtStyle("Traditional");
		episodeWorld.setType("TV Show");

		Episode episodeWorld1 = new Episode("The World God Only Knows", 1, 12);
		episodeWorld1.setAirDate(finishDateGodOnly);
		episodeWorld1.setApproprateness("PG-13");
		episodeWorld1.setEpisodeName("Flag 12.0 More Than a God, Less Than a Human");
		episodeWorld1.setArtStyle("Traditional");
		episodeWorld1.setType("TV Show");

		Episode episodeWorld2 = new Episode("The World God Only Knows", 2, 1);
		episodeWorld2.setAirDate(airDateGodOnly1);
		episodeWorld2.setApproprateness("PG-13");
		episodeWorld2.setEpisodeName("Flag 1.0 Flower in Bloom");
		episodeWorld2.setArtStyle("Traditional");
		episodeWorld2.setType("TV Show");

		Episode episodeWorld3 = new Episode("The World God Only Knows", 2, 12);
		episodeWorld3.setAirDate(finishDateGodOnly1);
		episodeWorld3.setApproprateness("PG-13");
		episodeWorld3.setEpisodeName("Flag 12.0 Summer Wars");
		episodeWorld3.setArtStyle("Traditional");
		episodeWorld3.setType("TV Show");

		Episode episodeBlue = new Episode("Blue Exorcist", 1, 1);
		episodeBlue.setAirDate(airDateBlueExorcist);
		episodeBlue.setApproprateness("PG-13");
		episodeBlue.setEpisodeName("The Devil Resides in Human Souls");
		episodeBlue.setArtStyle("Traditional");
		episodeBlue.setType("TV Show");

		Episode episodeBlue1 = new Episode("Blue Exorcist", 1, 25);
		episodeBlue1.setAirDate(finishDateBlueExorcist);
		episodeBlue1.setApproprateness("PG-13");
		episodeBlue1.setEpisodeName("Stop, Time");
		episodeBlue1.setArtStyle("Traditional");
		episodeBlue1.setType("TV Show");

		Episode episodeBlue2 = new Episode("Blue Exorcist", 2, 1);
		episodeBlue2.setAirDate(airDateBlueExorcist1);
		episodeBlue2.setApproprateness("PG-13");
		episodeBlue2.setEpisodeName("Runaway Kuro");
		episodeBlue2.setArtStyle("Traditional");
		episodeBlue2.setType("TV Show");

		DAOCollection.getEpisodeDao().addEpisode(episodeNaruto1);
		DAOCollection.getEpisodeDao().addEpisode(episodeNaruto2);
		DAOCollection.getEpisodeDao().addEpisode(episodeNaruto3);
		DAOCollection.getEpisodeDao().addEpisode(episodeNaruto4);
		DAOCollection.getEpisodeDao().addEpisode(episodeAngel);
		DAOCollection.getEpisodeDao().addEpisode(episodeAngel1);
		DAOCollection.getEpisodeDao().addEpisode(episodeAngel2);
		DAOCollection.getEpisodeDao().addEpisode(episodeWorld);
		DAOCollection.getEpisodeDao().addEpisode(episodeWorld1);
		DAOCollection.getEpisodeDao().addEpisode(episodeWorld2);
		DAOCollection.getEpisodeDao().addEpisode(episodeWorld3);
		DAOCollection.getEpisodeDao().addEpisode(episodeBlue);
		DAOCollection.getEpisodeDao().addEpisode(episodeBlue1);
		DAOCollection.getEpisodeDao().addEpisode(episodeBlue2);

		/**
		 * Adding ActorAppearances
		 */

		DAOCollection.getPeopleDao().associatePersonWithShow(
				DAOCollection.getPeopleDao().findPerson("Chie Nakamura").get(0).getID(),
				2, narutoSeason);
		DAOCollection.getPeopleDao().associatePersonWithShow(
				DAOCollection.getPeopleDao().findPerson("Chie Nakamura").get(0).getID(),
				7, theWorldGodOnlyKnow);
		DAOCollection.getPeopleDao().associatePersonWithShow(
				DAOCollection.getPeopleDao().findPerson("Kana Hanazawa").get(0).getID(),
				3, theWorldGodOnlyKnow);
		DAOCollection.getPeopleDao().associatePersonWithShow(
				DAOCollection.getPeopleDao().findPerson("Kana Hanazawa").get(0).getID(),
				4, angelBeats);
		DAOCollection.getPeopleDao().associatePersonWithShow(
				DAOCollection.getPeopleDao().findPerson("Akeno Watanabe").get(0).getID(),
				5, blueExorcist1);
		DAOCollection.getPeopleDao().associatePersonWithShow(
				DAOCollection.getPeopleDao().findPerson("Akeno Watanabe").get(0).getID(),
				6, blueExorcist1);

		/**
		 * Adding CharacterAppearances
		 */
		DAOCollection.getCharacterDao().associateCharacterWithShow(1, episodeWorld,
				"Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(1, episodeWorld1,
				"Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(1, episodeWorld2,
				"Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(2, episodeNaruto1,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(2, episodeNaruto2,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(2, episodeNaruto3,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(2, episodeNaruto4,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(3, episodeWorld,
				"Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(3, episodeWorld1,
				"Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(3, episodeWorld2,
				"Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(4, episodeAngel,
				"Anti-Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(4, episodeAngel1,
				"Anti-Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(4, episodeAngel2,
				"Anti-Hero");
		DAOCollection.getCharacterDao()
				.associateCharacterWithShow(5, episodeBlue, "Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(5, episodeBlue1,
				"Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(5, episodeBlue2,
				"Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(6, episodeBlue,
				"Anti-Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(6, episodeBlue1,
				"Anti-Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(6, episodeBlue2,
				"Anti-Hero");
		DAOCollection.getCharacterDao().associateCharacterWithShow(7, episodeWorld,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(7, episodeWorld1,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(7, episodeWorld2,
				"Side Kick");
		DAOCollection.getCharacterDao().associateCharacterWithShow(8, episodeNaruto1,
				"Supporting Character");
		DAOCollection.getCharacterDao().associateCharacterWithShow(8, episodeNaruto2,
				"Supporting Character");
		DAOCollection.getCharacterDao().associateCharacterWithShow(8, episodeNaruto3,
				"Supporting Character");
		DAOCollection.getCharacterDao().associateCharacterWithShow(8, episodeNaruto4,
				"Supporting Character");
		DAOCollection.getCharacterDao().associateCharacterWithShow(9, episodeAngel,
				"Underdog Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(9, episodeAngel1,
				"Underdog Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(9, episodeAngel2,
				"Underdog Heroine");
		DAOCollection.getCharacterDao().associateCharacterWithShow(10, episodeBlue,
				"Protaginist");
		DAOCollection.getCharacterDao().associateCharacterWithShow(10, episodeBlue1,
				"Protaginist");
		DAOCollection.getCharacterDao().associateCharacterWithShow(10, episodeBlue2,
				"Protaginist");
		/*
		 *  Add Watches
		 */
		DAOCollection.getUserDao().registerWatch(DAOCollection.getUserDao().findUser("Bansal"), angelBeats, 1, 10);
		DAOCollection.getUserDao().registerWatch(DAOCollection.getUserDao().findUser("Bansal"), theWorldGodOnlyKnow, 1, 7);
		DAOCollection.getUserDao().registerWatch(DAOCollection.getUserDao().findUser("Bansal"), blueExorcist1, 1, 5);

	}
}
