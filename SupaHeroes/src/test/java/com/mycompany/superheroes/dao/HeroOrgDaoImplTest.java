/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Hero;
import com.mycompany.superheroes.models.HeroOrgBridge;
import com.mycompany.superheroes.models.Location;
import com.mycompany.superheroes.models.Org;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class HeroOrgDaoImplTest {

    OrgDao orgDao;
    HeroDao heroDao;
    LocationDao locationDao;
    HeroOrgDao hobDao;

    public HeroOrgDaoImplTest() {
    }

    private Hero CreateAHero() {
        Hero hero = new Hero();
        hero.setHeroName("Superman");
        hero.setHeroDescription("The one, the only, Superman.");
        hero.setHeroSuperPower("Invincible");

        heroDao.addHero(hero);

        return hero;
    }

    private Hero CreateASecondHero() {
        Hero hero1 = new Hero();
        hero1.setHeroName("Superman");
        hero1.setHeroDescription("The one, the only, Superman.");
        hero1.setHeroSuperPower("Invincible");

        heroDao.addHero(hero1);

        return hero1;
    }

    private Location CreateALocation() {
        Location location = new Location();
        location.setLocationName("SuperFriend Hideout");
        location.setLocationDescription("Super secret super hideout");
        location.setStreet("Undisclosed");
        location.setCity("Under the Atlantic");
        location.setLat(210.123456f);
        location.setLongitude(111.234567f);

        locationDao.addLocation(location);

        return location;
    }

    private Location CreateASecondLocation() {
        Location location = new Location();
        location.setLocationName("SuperFriend Zumba Room");
        location.setLocationDescription("Super secret workout program HQ");
        location.setStreet("100 Aerobics Drive");
        location.setCity("Akron");
        location.setLat(110.123456f);
        location.setLongitude(211.234567f);

        locationDao.addLocation(location);

        return location;
    }

    private Org CreateAnOrg(int locationID) {
        Org org = new Org();
        org.setOrgName("SuperFriends");
        org.setOrgDescription("Really really super");
        org.setLocationID(locationID);

        orgDao.addOrg(org);

        return org;
    }

    private Org CreateASecondOrg(int locationID) {
        Org org1 = new Org();
        org1.setOrgName("Special Guys");
        org1.setOrgDescription("Everyone is special.");
        org1.setLocationID(locationID);

        orgDao.addOrg(org1);

        return org1;
    }

    private HeroOrgBridge CreateAHob(int heroID, int orgID) {
        HeroOrgBridge hob = new HeroOrgBridge();
        hob.setHeroID(heroID);
        hob.setOrgID(orgID);

        hobDao.addHeroOrg(hob);

        return hob;
    }

    private HeroOrgBridge CreateASecondHob(int heroID, int orgID) {
        HeroOrgBridge hob = new HeroOrgBridge();
        hob.setHeroID(heroID);
        hob.setOrgID(orgID);

        hobDao.addHeroOrg(hob);

        return hob;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        orgDao = ctx.getBean("OrgDao", OrgDao.class);
        heroDao = ctx.getBean("HeroDao", HeroDao.class);
        locationDao = ctx.getBean("LocationDao", LocationDao.class);
        hobDao = ctx.getBean("HeroOrgDao", HeroOrgDao.class);

        //delete all heroorgs
        List<HeroOrgBridge> hobs = hobDao.getAllHeroOrgs();
        for (HeroOrgBridge currentHob : hobs) {
            hobDao.deleteHeroOrg(currentHob.getHeroID(), currentHob.getOrgID());
        }
//
//        // delete all sightings        
//        List<Sighting> sightings = orgDao.getAllSightings();
//        for (Sighting currentSighting : sightings) {
//            orgDao.deleteSighting(currentSighting.getSightingID());
//        }
        // delete all heros
        List<Hero> heroes = heroDao.getAllHeroes();
        for (Hero currentHero : heroes) {
            heroDao.deleteHero(currentHero.getHeroID());
        }

        //delete all orgs
        List<Org> orgs = orgDao.getAllOrgs();
        for (Org currentOrg : orgs) {
            orgDao.deleteOrg(currentOrg.getOrgID());
        }

        // delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationID());
        }
    }

    @After
    public void tearDown() {
    }

//HeroOrgs
    @Test
    public void testAddHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());

        HeroOrgBridge fromDao = hobDao.getHeroOrg(hero.getHeroID(), org.getOrgID());

        assertEquals(fromDao, hob);
    }

    @Test
    public void testGetHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());

        HeroOrgBridge fromDao = hobDao.getHeroOrg(hero.getHeroID(), org.getOrgID());

        assertEquals(fromDao, hob);
    }

    @Test
    public void testDeleteHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());

        hobDao.deleteHeroOrg(hero.getHeroID(), org.getOrgID());
        assertNull(hobDao.getHeroOrg(hero.getHeroID(), org.getOrgID()));
    }

    @Test
    public void testUpdateHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();
        Hero hero1 = CreateASecondHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());
        hob.setHeroID(hero1.getHeroID());

        hobDao.updateHeroOrg(hob, hero.getHeroID(), org.getOrgID());

        assertEquals(hob, hobDao.getHeroOrg(hero1.getHeroID(), org.getOrgID()));
    }

    @Test
    public void testGetAllHeroOrgs() {
        Location location = CreateALocation();

        Org org = CreateAnOrg(location.getLocationID());
        Org org1 = CreateASecondOrg(location.getLocationID());

        Hero hero = CreateAHero();
        Hero hero1 = CreateASecondHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());
        HeroOrgBridge hob1 = CreateAHob(hero1.getHeroID(), org1.getOrgID());

        assertEquals(2, hobDao.getAllHeroOrgs().size());

    }

    //Reports
    @Test
    public void testGetOrgMembers() {
        Hero hero1 = CreateAHero();
        Hero hero2 = CreateASecondHero();

        Location location = CreateALocation();
        Location location2 = CreateASecondLocation();

        Org org1 = CreateAnOrg(location.getLocationID());
        Org org2 = CreateASecondOrg(location2.getLocationID());

        HeroOrgBridge hob1 = CreateAHob(hero1.getHeroID(), org1.getOrgID());
        HeroOrgBridge hob2 = CreateAHob(hero2.getHeroID(), org2.getOrgID());

        List<HeroOrgBridge> hobList = hobDao.getOrgMembers(org1.getOrgID());

        assertEquals(2, hobDao.getAllHeroOrgs().size());
        assertEquals(1, hobList.size());
    }

    @Test
    public void testGetHeroMembership() {
        Hero hero1 = CreateAHero();
        Hero hero2 = CreateASecondHero();

        Location location = CreateALocation();
        Location location2 = CreateASecondLocation();

        Org org1 = CreateAnOrg(location.getLocationID());
        Org org2 = CreateASecondOrg(location2.getLocationID());

        HeroOrgBridge hob1 = CreateAHob(hero1.getHeroID(), org1.getOrgID());
        HeroOrgBridge hob2 = CreateAHob(hero2.getHeroID(), org2.getOrgID());

        List<HeroOrgBridge> hobList = hobDao.getHeroMembership(hero1.getHeroID());

        assertEquals(2, hobDao.getAllHeroOrgs().size());
        assertEquals(1, hobList.size());
    }
}
