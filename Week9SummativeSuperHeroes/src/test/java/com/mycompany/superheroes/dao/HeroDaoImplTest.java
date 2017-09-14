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
public class HeroDaoImplTest {

    HeroDao heroDao;
    OrgDao orgDao;
    LocationDao locationDao;
    HeroOrgDao hobDao;
    SightingDao sightingDao;

    public HeroDaoImplTest() {
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
        hero1.setHeroName("Bowser");
        hero1.setHeroDescription("Bwa ha ha.");
        hero1.setHeroSuperPower("Super Mean Turtle guy");

        heroDao.addHero(hero1);

        return hero1;
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

//Test Hero heroDaos
    @Test
    public void testAddHero() {
        Hero hero = CreateAHero();

        Hero fromDao = heroDao.getHeroById(hero.getHeroID());

        assertEquals(fromDao, hero);

    }

    @Test
    public void testGetHero() {
        Hero hero = CreateAHero();

        Hero fromDao = heroDao.getHeroById(hero.getHeroID());

        assertEquals(fromDao, hero);

    }

    @Test
    public void testDeleteHero() {
        Hero hero = CreateAHero();

        Hero fromDao = heroDao.getHeroById(hero.getHeroID());
        assertEquals(fromDao, hero);

        heroDao.deleteHero(hero.getHeroID());
        assertNull(heroDao.getHeroById(hero.getHeroID()));
    }

    @Test
    public void testUpdateHero() {
        Hero hero = CreateAHero();

        hero.setHeroName("Spider-Man");

        heroDao.updateHero(hero);

        assertEquals(hero, heroDao.getHeroById(hero.getHeroID()));
    }

    @Test
    public void testGetAllHeroes() {
        Hero hero = CreateAHero();
        Hero hero1 = CreateASecondHero();

        assertEquals(2, heroDao.getAllHeroes().size());

        assertTrue(heroDao.getAllHeroes().contains(hero));
        assertTrue(heroDao.getAllHeroes().contains(hero1));
    }

}
