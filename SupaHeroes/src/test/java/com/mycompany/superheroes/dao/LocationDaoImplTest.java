/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.dao.LocationDao;
import com.mycompany.superheroes.dao.OrgDao;
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
public class LocationDaoImplTest {

    OrgDao orgDao;
    LocationDao locationDao;

    public LocationDaoImplTest() {
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
        locationDao = ctx.getBean("LocationDao", LocationDao.class);

        //delete all heroorgs
//        List<HeroOrgBridge> hobs = orgDao.getAllHeroOrgs();
//        for (HeroOrgBridge currentHob : hobs) {
//            orgDao.deleteHeroOrg(currentHob.getHeroID(), currentHob.getOrgID());
//        }
//
//        // delete all sightings        
//        List<Sighting> sightings = orgDao.getAllSightings();
//        for (Sighting currentSighting : sightings) {
//            orgDao.deleteSighting(currentSighting.getSightingID());
//        }
//        // delete all heros
//        List<Hero> heroes = orgDao.getAllHeroes();
//        for (Hero currentHero : heroes) {
//            orgDao.deleteHero(currentHero.getHeroID());
//        }
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

//Locations
    @Test
    public void testAddLocation() {
        Location location = CreateALocation();

        Location fromDao = locationDao.getLocationById(location.getLocationID());

        assertEquals(fromDao, location);
    }

    @Test
    public void testGetLocation() {
        Location location = CreateALocation();

        Location fromDao = locationDao.getLocationById(location.getLocationID());

        assertEquals(fromDao, location);
    }

    @Test
    public void testDeleteLocation() {
        Location location = CreateALocation();

        locationDao.deleteLocation(location.getLocationID());

        assertNull(locationDao.getLocationById(location.getLocationID()));
    }

    @Test
    public void testUpdateLocation() {
        Location location = CreateALocation();

        location.setCity("Updated city");

        locationDao.updateLocation(location);

        assertEquals(location, locationDao.getLocationById(location.getLocationID()));
    }

    @Test
    public void testGetAllLocations() {
        Location location = CreateALocation();

        Location location1 = CreateASecondLocation();

        assertEquals(2, locationDao.getAllLocations().size());
    }

}
