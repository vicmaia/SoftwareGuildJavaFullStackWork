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
import com.mycompany.superheroes.models.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SuperDaoTestImplTest {

    SuperDao dao;

    public SuperDaoTestImplTest() {
    }

    private Hero CreateAHero() {
        Hero hero = new Hero();
        hero.setHeroName("Superman");
        hero.setHeroDescription("The one, the only, Superman.");
        hero.setHeroSuperPower("Invincible");

        dao.addHero(hero);

        return hero;
    }

    private Hero CreateASecondHero() {
        Hero hero1 = new Hero();
        hero1.setHeroName("Superman");
        hero1.setHeroDescription("The one, the only, Superman.");
        hero1.setHeroSuperPower("Invincible");

        dao.addHero(hero1);

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

        dao.addLocation(location);

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

        dao.addLocation(location);

        return location;
    }

    private Org CreateAnOrg(int locationID) {
        Org org = new Org();
        org.setOrgName("SuperFriends");
        org.setOrgDescription("Really really super");
        org.setLocationID(locationID);

        dao.addOrg(org);

        return org;
    }

    private Org CreateASecondOrg(int locationID) {
        Org org1 = new Org();
        org1.setOrgName("Special Guys");
        org1.setOrgDescription("Everyone is special.");
        org1.setLocationID(locationID);

        dao.addOrg(org1);

        return org1;
    }

    private HeroOrgBridge CreateAHob(int heroID, int orgID) {
        HeroOrgBridge hob = new HeroOrgBridge();
        hob.setHeroID(heroID);
        hob.setOrgID(orgID);

        dao.addHeroOrg(hob);

        return hob;
    }

    private HeroOrgBridge CreateASecondHob(int heroID, int orgID) {
        HeroOrgBridge hob = new HeroOrgBridge();
        hob.setHeroID(heroID);
        hob.setOrgID(orgID);

        dao.addHeroOrg(hob);

        return hob;
    }

    private Sighting CreateASighting() {
        Hero hero = CreateAHero();
        Location location = CreateALocation();

        Sighting sighting = new Sighting();
        sighting.setHeroID(hero.getHeroID());
        sighting.setLocationID(location.getLocationID());
        sighting.setDate(LocalDate.parse("2005-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        dao.addSighting(sighting);

        return sighting;
    }

    private Sighting CreateASecondSighting() {
        Hero hero = CreateASecondHero();
        Location location = CreateASecondLocation();

        Sighting sighting1 = new Sighting();
        sighting1.setHeroID(hero.getHeroID());
        sighting1.setLocationID(location.getLocationID());
        sighting1.setDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        dao.addSighting(sighting1);

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

        dao = ctx.getBean("superHeroesDao", SuperDao.class);

        //delete all heroorgs
        List<HeroOrgBridge> hobs = dao.getAllHeroOrgs();
        for (HeroOrgBridge currentHob : hobs) {
            dao.deleteHeroOrg(currentHob.getHeroID(), currentHob.getOrgID());
        }

        // delete all sightings        
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingID());
        }

        // delete all heros
        List<Hero> heroes = dao.getAllHeroes();
        for (Hero currentHero : heroes) {
            dao.deleteHero(currentHero.getHeroID());
        }
        //delete all orgs
        List<Org> orgs = dao.getAllOrgs();
        for (Org currentOrg : orgs) {
            dao.deleteOrg(currentOrg.getOrgID());
        }

        // delete all locations
        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocation(currentLocation.getLocationID());
        }

    }

    @After
    public void tearDown() {
    }

    //Test Hero daos
    @Test
    public void testAddHero() {
        Hero hero = CreateAHero();

        Hero fromDao = dao.getHeroById(hero.getHeroID());

        assertEquals(fromDao, hero);

    }

    @Test
    public void testGetHero() {
        Hero hero = CreateAHero();

        Hero fromDao = dao.getHeroById(hero.getHeroID());

        assertEquals(fromDao, hero);

    }

    @Test
    public void testDeleteHero() {
        Hero hero = CreateAHero();

        Hero fromDao = dao.getHeroById(hero.getHeroID());
        assertEquals(fromDao, hero);

        dao.deleteHero(hero.getHeroID());
        assertNull(dao.getHeroById(hero.getHeroID()));
    }

    @Test
    public void testUpdateHero() {
        Hero hero = CreateAHero();

        hero.setHeroName("Spider-Man");

        dao.updateHero(hero);

        assertEquals(hero, dao.getHeroById(hero.getHeroID()));
    }

    @Test
    public void testGetAllHeroes() {
        Hero hero = CreateAHero();
        Hero hero1 = CreateASecondHero();

        assertEquals(2, dao.getAllHeroes().size());
    }

    //Test Org dao
    @Test
    public void testAddOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Org fromDao = dao.getOrgById(org.getOrgID());

        assertEquals(fromDao, org);
    }

    @Test
    public void testGetOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Org fromDao = dao.getOrgById(org.getOrgID());

        assertEquals(fromDao, org);
    }

    @Test
    public void testDeleteOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        dao.deleteOrg(org.getOrgID());
        assertNull(dao.getOrgById(org.getOrgID()));
    }

    @Test
    public void testUpdateOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        org.setOrgName("Techno-force");

        dao.updateOrg(org);

        assertEquals(org, dao.getOrgById(org.getOrgID()));
    }

    @Test
    public void testGetAllOrgs() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());
        Org org1 = CreateASecondOrg(location.getLocationID());

        assertEquals(2, dao.getAllOrgs().size());
    }

    //HeroOrgs
    @Test
    public void testAddHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());

        HeroOrgBridge fromDao = dao.getHeroOrg(hero.getHeroID(), org.getOrgID());

        assertEquals(fromDao, hob);
    }

    @Test
    public void testGetHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());

        HeroOrgBridge fromDao = dao.getHeroOrg(hero.getHeroID(), org.getOrgID());

        assertEquals(fromDao, hob);
    }

    @Test
    public void testDeleteHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());

        dao.deleteHeroOrg(hero.getHeroID(), org.getOrgID());
        assertNull(dao.getHeroOrg(hero.getHeroID(), org.getOrgID()));
    }

    @Test
    public void testUpdateHeroOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Hero hero = CreateAHero();
        Hero hero1 = CreateASecondHero();

        HeroOrgBridge hob = CreateAHob(hero.getHeroID(), org.getOrgID());
        hob.setHeroID(hero1.getHeroID());

        dao.updateHeroOrg(hob, hero.getHeroID(), org.getOrgID());

        assertEquals(hob, dao.getHeroOrg(hero1.getHeroID(), org.getOrgID()));
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

        assertEquals(2, dao.getAllOrgs().size());

    }

    //Locations
    @Test
    public void testAddLocation() {
        Location location = CreateALocation();

        Location fromDao = dao.getLocationById(location.getLocationID());

        assertEquals(fromDao, location);
    }

    @Test
    public void testGetLocation() {
        Location location = CreateALocation();

        Location fromDao = dao.getLocationById(location.getLocationID());

        assertEquals(fromDao, location);
    }

    @Test
    public void testDeleteLocation() {
        Location location = CreateALocation();

        dao.deleteLocation(location.getLocationID());

        assertNull(dao.getLocationById(location.getLocationID()));
    }

    @Test
    public void testUpdateLocation() {
        Location location = CreateALocation();

        location.setCity("Updated city");

        dao.updateLocation(location);

        assertEquals(location, dao.getLocationById(location.getLocationID()));
    }

    @Test
    public void testGetAllLocations() {
        Location location = CreateALocation();

        Location location1 = CreateASecondLocation();

        assertEquals(2, dao.getAllLocations().size());
    }

    //Sightings
    @Test
    public void testAddSighting() {
        Sighting sighting = CreateASighting();

        Sighting fromDao = dao.getSightingById(sighting.getSightingID());

        assertEquals(fromDao, sighting);
    }

    @Test
    public void testGetSighting() {
        Sighting sighting = CreateASighting();

        Sighting fromDao = dao.getSightingById(sighting.getSightingID());

        assertEquals(fromDao, sighting);
    }

    @Test
    public void testDeleteSighting() {
        Sighting sighting = CreateASighting();

        dao.deleteSighting(sighting.getSightingID());

        assertNull(dao.getSightingById(sighting.getSightingID()));
    }

    @Test
    public void testUpdateSighting() {
        Sighting sighting = CreateASighting();

        sighting.setDate(LocalDate.parse("2010-10-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        dao.updateSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getSightingID());

        assertEquals(fromDao, sighting);
    }

    @Test
    public void testGetAllSightings() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        assertEquals(2, dao.getAllSightings().size());
    }

    //Reports
    @Test
    public void testGetSightingsByLocation() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        List<Sighting> sightingList = dao.getSightingsByLocation(sighting2.getLocationID());

        assertEquals(2, dao.getAllLocations().size());
        assertEquals(1, sightingList.size());
    }

    @Test
    public void testGetSightingsByHero() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        List<Sighting> sightingList = dao.getSightingsByHero(sighting2.getHeroID());

        assertEquals(2, dao.getAllLocations().size());
        assertEquals(1, sightingList.size());
    }

    @Test
    public void testGetSightingsByDate() {
        Sighting sighting1 = CreateASighting();
        Sighting sighting2 = CreateASecondSighting();

        List<Sighting> sightingList = dao.getSightingsByDate(sighting2.getDate());

        assertEquals(2, dao.getAllLocations().size());
        assertEquals(1, sightingList.size());
    }

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

        List<HeroOrgBridge> hobList = dao.getOrgMembers(org1.getOrgID());

        assertEquals(2, dao.getAllHeroOrgs().size());
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

        List<HeroOrgBridge> hobList = dao.getHeroMembership(hero1.getHeroID());

        assertEquals(2, dao.getAllHeroOrgs().size());
        assertEquals(1, hobList.size());
    }
}
