/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.dao.LocationDao;
import com.mycompany.superheroes.dao.SightingDao;
import com.mycompany.superheroes.models.Hero;
import com.mycompany.superheroes.models.HeroOrgBridge;
import com.mycompany.superheroes.models.Location;
import com.mycompany.superheroes.models.Org;
import com.mycompany.superheroes.models.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class SightingDaoImplTest {

    OrgDao orgDao;
    HeroDao heroDao;
    LocationDao locationDao;
    HeroOrgDao hobDao;
    SightingDao sightingDao;

    public SightingDaoImplTest() {
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

    private Sighting CreateASighting() {
        Hero hero = CreateAHero();
        Location location = CreateALocation();

        Sighting sighting = new Sighting();
        sighting.setHeroID(hero.getHeroID());
        sighting.setLocationID(location.getLocationID());
        sighting.setDate(LocalDate.parse("2005-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        sightingDao.addSighting(sighting);

        return sighting;
    }

    private Sighting CreateASecondSighting() {
        Hero hero = CreateASecondHero();
        Location location = CreateASecondLocation();

        Sighting sighting1 = new Sighting();
        sighting1.setHeroID(hero.getHeroID());
        sighting1.setLocationID(location.getLocationID());
        sighting1.setDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        sightingDao.addSighting(sighting1);

        return sighting1;
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
        sightingDao = ctx.getBean("SightingDao", SightingDao.class);

        //delete all heroorgs
        List<HeroOrgBridge> hobs = hobDao.getAllHeroOrgs();
        for (HeroOrgBridge currentHob : hobs) {
            hobDao.deleteHeroOrg(currentHob.getHeroID(), currentHob.getOrgID());
        }

        // delete all sightings        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingID());
        }

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

    //Sightings
    @Test
    public void testAddSighting() {
        Sighting sighting = CreateASighting();

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingID());

        assertEquals(fromDao, sighting);
    }

    @Test
    public void testGetSighting() {
        Sighting sighting = CreateASighting();

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingID());

        assertEquals(fromDao, sighting);
    }

    @Test
    public void testDeleteSighting() {
        Sighting sighting = CreateASighting();

        sightingDao.deleteSighting(sighting.getSightingID());

        assertNull(sightingDao.getSightingById(sighting.getSightingID()));
    }

    @Test
    public void testUpdateSighting() {
        Sighting sighting = CreateASighting();

        sighting.setDate(LocalDate.parse("2010-10-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        sightingDao.updateSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingID());

        assertEquals(fromDao, sighting);
    }

    @Test
    public void testGetAllSightings() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        assertEquals(2, sightingDao.getAllSightings().size());

        assertTrue(sightingDao.getAllSightings().contains(sighting1));
        assertTrue(sightingDao.getAllSightings().contains(sighting2));
    }

    //Reports
    @Test
    public void testGetSightingsByLocation() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        List<Sighting> sightingList = sightingDao.getSightingsByLocation(sighting2.getLocationID());

        assertEquals(2, sightingDao.getAllSightings().size());
        assertTrue(sightingDao.getAllSightings().contains(sighting1));
        assertTrue(sightingDao.getAllSightings().contains(sighting2));

        assertEquals(1, sightingList.size());
        assertTrue(sightingList.contains(sighting2));
    }

    @Test
    public void testGetSightingsByHero() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        List<Sighting> sightingList = sightingDao.getSightingsByHero(sighting2.getHeroID());

        assertEquals(2, sightingDao.getAllSightings().size());
        assertTrue(sightingDao.getAllSightings().contains(sighting1));
        assertTrue(sightingDao.getAllSightings().contains(sighting2));

        assertEquals(1, sightingList.size());
        assertTrue(sightingList.contains(sighting2));
    }

    @Test
    public void testGetSightingsByDate() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        List<Sighting> sightingList = sightingDao.getSightingsByDate(sighting2.getDate());

        assertEquals(2, sightingDao.getAllSightings().size());
        assertTrue(sightingDao.getAllSightings().contains(sighting1));
        assertTrue(sightingDao.getAllSightings().contains(sighting2));

        assertEquals(1, sightingList.size());
        assertTrue(sightingList.contains(sighting2));
    }
}
