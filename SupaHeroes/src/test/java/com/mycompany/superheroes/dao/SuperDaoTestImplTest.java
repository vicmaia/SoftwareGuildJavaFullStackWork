/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Hero;
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

    private Org CreateAnOrg(int locationID) {
        Org org = new Org();
        org.setOrgName("SuperFriends");
        org.setOrgDescription("Really really super");
        org.setLocationID(locationID);

        dao.addOrg(org);

        return org;
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
    public void testAddGetHero() {
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
    public void testAddGetOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Org fromDao = dao.getOrgById(org.getOrgID());

        assertEquals(fromDao, org);
    }

    /**
     * Test of deleteOrg method, of class SuperDaoTestImpl.
     */
    @Test
    public void testDeleteOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        dao.deleteOrg(org.getOrgID());
        assertNull(dao.getOrgById(org.getOrgID()));
    }

    /**
     * Test of updateOrg method, of class SuperDaoTestImpl.
     */
    @Test
    public void testUpdateOrg() {
    }

    /**
     * Test of getOrgById method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetOrgById() {
    }

    /**
     * Test of getAllOrgs method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetAllOrgs() {
    }

    /**
     * Test of addHeroOrg method, of class SuperDaoTestImpl.
     */
    @Test
    public void testAddHeroOrg() {
    }

    /**
     * Test of deleteHeroOrg method, of class SuperDaoTestImpl.
     */
    @Test
    public void testDeleteHeroOrg() {
    }

    /**
     * Test of updateHeroOrg method, of class SuperDaoTestImpl.
     */
    @Test
    public void testUpdateHeroOrg() {
    }

    /**
     * Test of getHeroOrg method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetHeroOrg() {
    }

    /**
     * Test of getAllHeroOrgs method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetAllHeroOrgs() {
    }

    /**
     * Test of addLocation method, of class SuperDaoTestImpl.
     */
    @Test
    public void testAddLocation() {
    }

    /**
     * Test of deleteLocation method, of class SuperDaoTestImpl.
     */
    @Test
    public void testDeleteLocation() {
    }

    /**
     * Test of updateLocation method, of class SuperDaoTestImpl.
     */
    @Test
    public void testUpdateLocation() {
    }

    /**
     * Test of getLocationById method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetLocationById() {
    }

    /**
     * Test of getAllLocations method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of addSighting method, of class SuperDaoTestImpl.
     */
    @Test
    public void testAddSighting() {
    }

    /**
     * Test of deleteSighting method, of class SuperDaoTestImpl.
     */
    @Test
    public void testDeleteSighting() {
    }

    /**
     * Test of updateSighting method, of class SuperDaoTestImpl.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of getSightingById method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetSightingById() {
    }

    /**
     * Test of getAllSightings method, of class SuperDaoTestImpl.
     */
    @Test
    public void testGetAllSightings() {
    }

}
