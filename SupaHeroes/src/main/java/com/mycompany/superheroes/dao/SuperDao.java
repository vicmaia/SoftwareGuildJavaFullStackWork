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
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface SuperDao {

    //Heroes
    public void addHero(Hero hero);

    public void deleteHero(int heroID);

    public void updateHero(Hero hero);

    public Hero getHeroById(int heroID);

    public List<Hero> getAllHeroes();

    //Orgs
    public void addOrg(Org org);

    public void deleteOrg(int orgId);

    public void updateOrg(Org org);

    public Org getOrgById(int orgID);

    public List<Org> getAllOrgs();

    //HeroOrgBridge
    public void addHeroOrg(HeroOrgBridge heroOrgBridge);

    public void deleteHeroOrg(int heroID, int orgID);

    public void updateHeroOrg(HeroOrgBridge heroOrgBridge, int oldHeroID, int oldOrgID);

    public HeroOrgBridge getHeroOrg(int heroID, int orgID);

    public List<HeroOrgBridge> getAllHeroOrgs();

    //Location
    public void addLocation(Location location);

    public void deleteLocation(int locationID);

    public void updateLocation(Location location);

    public Location getLocationById(int locationID);

    public List<Location> getAllLocations();

    //Sightings
    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int sightingID);

    public List<Sighting> getAllSightings();

    //Reports
    public List<Sighting> getSightingsByLocation(int LocationID);

    public List<Sighting> getSightingsByHero(int HeroID);

    public List<Sighting> getSightingsByDate(LocalDate sightingDate);
    
    public List<HeroOrgBridge> getOrgMembers(int orgID);
    
    public List<HeroOrgBridge> getHeroMembership(int heroID);

}
