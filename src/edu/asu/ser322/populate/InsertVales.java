package edu.asu.ser322.populate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.asu.ser322.data.access.DAOCollection;
import edu.asu.ser322.data.model.Franchise;
import edu.asu.ser322.data.model.Gender;
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
	    
	    
	    
	    
	}
}
