package edu.asu.ser322.populate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.Episode;
import edu.asu.ser322.data.model.Franchise;
import edu.asu.ser322.data.model.Gender;
import edu.asu.ser322.data.model.Season;
import edu.asu.ser322.data.model.Studio;
import edu.asu.ser322.data.model.Character;

public class InsertVales
{
	public static void main(String[] args)
	{ 
		/**
		 * adding Studios
		 */
		Calendar calendarTokyo = new GregorianCalendar();
		calendarTokyo.set(1964,4,12);
		Date airDateTokyo = calendarTokyo.getTime();
	    Studio studioTokyo = new Studio("TV Tokyo",airDateTokyo);
	    DAOCollection.getStudioDao().addStudio(studioTokyo);
	    
	    Calendar calendarViz = new GregorianCalendar();
		calendarViz.set(1986,1,1);
		Date airDateViz = calendarViz.getTime();
	    Studio studioViz = new Studio("Viz media",airDateViz);
	    DAOCollection.getStudioDao().addStudio(studioViz);
	    
	    Calendar calendarAni = new GregorianCalendar();
		calendarAni.set(1995,9,1);
		Date airDateAni = calendarAni.getTime();
	    Studio studioAni = new Studio("Aniplex",airDateAni);
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
	    LizThomp.setId(8);
	    LizThomp.setName("Elizabeth Thompson");
	    LizThomp.setGender(Gender.valueOf("FEMALE"));
	    LizThomp.setAge(23);
	    LizThomp.setArchetype("under-Dog");
	    LizThomp.setHairColor("Blonde");
	    
	    Character ritoYuu = new Character();
	    ritoYuu.setId(9);
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
		Date finshDateNaruto = calendarNaruto2.getTime();
		
		Season narutoSeason = new Season("Nartuo", 1);
		narutoSeason.setName("Naruto");
		narutoSeason.setAirDate(airDateNaruto);
		narutoSeason.setFinishDate(finshDateNaruto);
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
		
		Season theWorldGodOnlyKnow = new Season("The World God Only Know", 1);
		theWorldGodOnlyKnow.setName("The World God Only Know");
		theWorldGodOnlyKnow.setAirDate(airDateGodOnly);
		theWorldGodOnlyKnow.setFinishDate(finishDateGodOnly);
		theWorldGodOnlyKnow.setAppropriateness("PG-13");
		
		Calendar calendarGodOnly3 = new GregorianCalendar();
		calendarGodOnly3.set(2011, 4, 12);
		Date airDateGodOnly1 = calendarGodOnly3.getTime();
		
		Calendar calendarGodOnly4 = new GregorianCalendar();
		calendarGodOnly4.set(2010, 6, 28);
		Date finishDateGodOnly1 = calendarGodOnly4.getTime();
		
		Season theWorldGodOnlyKnow2 = new Season("The World God Only Know", 2);
		theWorldGodOnlyKnow2.setName("The World God Only Know II");
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
		
		Calendar calendarBlueExorcist3 = new GregorianCalendar();
		calendarBlueExorcist3.set(2011, 10, 26);
		Date finishDateBlueExorcist1 = calendarBlueExorcist3.getTime();
		
		Season blueExorcist2 = new Season("Blue Exorcist", 2);
		blueExorcist2.setName("Blue Exorcist: Runaway Kuro");
		blueExorcist2.setAirDate(airDateBlueExorcist1);
		blueExorcist2.setFinishDate(finishDateBlueExorcist1);
		blueExorcist2.setAppropriateness("PG-13");
		
		 /**
	     * adding Episodes
	     */
		Episode episodeNaruto1 = new Episode("Naruto", 1, 1);
		
		
	}
}
