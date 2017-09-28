/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.HeroOrgBridge;
import com.mycompany.superheroes.models.Location;
import com.mycompany.superheroes.models.Org;
import com.mycompany.superheroes.models.Sighting;
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
public class OrgDaoImplTest {

    OrgDao orgDao;
    LocationDao locationDao;
    HeroOrgDao hobDao;
    SightingDao sightingDao;

    public OrgDaoImplTest() {
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

    //Test Org dao
    @Test
    public void testAddOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Org fromDao = orgDao.getOrgById(org.getOrgID());

        assertEquals(fromDao, org);
    }

    @Test
    public void testGetOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        Org fromDao = orgDao.getOrgById(org.getOrgID());

        assertEquals(fromDao, org);
    }

    @Test
    public void testDeleteOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        orgDao.deleteOrg(org.getOrgID());
        assertNull(orgDao.getOrgById(org.getOrgID()));
    }

    @Test
    public void testUpdateOrg() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());

        org.setOrgName("Techno-force");

        orgDao.updateOrg(org);

        assertEquals(org, orgDao.getOrgById(org.getOrgID()));
    }

    @Test
    public void testGetAllOrgs() {
        Location location = CreateALocation();
        Org org = CreateAnOrg(location.getLocationID());
        Org org1 = CreateASecondOrg(location.getLocationID());

        assertEquals(2, orgDao.getAllOrgs().size());

        assertTrue(orgDao.getAllOrgs().contains(org));
        assertTrue(orgDao.getAllOrgs().contains(org1));
    }
}
