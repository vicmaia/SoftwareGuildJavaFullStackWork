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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author n0252282
 */
public class SuperDaoTestImpl implements SuperDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //Hero Related
    private static final String SQL_ADD_HERO
            = "insert into heroes (HeroName, HeroDescription, HeroSuperPower"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_HERO
            = "delete from heroes where HeroID = ?";

    private static final String SQL_UPDATE_HERO
            = "update heroes set HeroName = ?, HeroDescription = ?, HeroSuperPower = ? "
            + "where HeroID =  ?";

    private static final String SQL_SELECT_HERO
            = "select * from heroes where HeroID = ?";

    private static final String SQL_SELECT_ALL_HEROES
            = "select * from heroes";

    //Org Related
    private static final String SQL_ADD_ORG
            = "insert into orgs (OrgName, OrgDescription, LocationID"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_ORG
            = "delete from orgs where OrgID = ?";

    private static final String SQL_UPDATE_ORG
            = "update orgs set OrgName = ?, OrgDescription = ?, LocationID = ? "
            + "where OrgID =  ?";

    private static final String SQL_SELECT_ORG
            = "select * from orgs where OrgID = ?";

    private static final String SQL_SELECT_ALL_ORGS
            = "select * from orgs";

    // HeroOrgBridge
    private static final String SQL_ADD_HERO_ORG
            = "insert into heroesorgbridge (HeroID, OrgID"
            + ") values (?, ?)";

    private static final String SQL_DELETE_HERO_ORG
            = "delete from heroesorgbridge where HeroID = ? and OrgID = ?";

    private static final String SQL_UPDATE_HERO_ORG
            = "update heroesorgbridge set HeroID = ?, OrgID = ? "
            + "where HeroID = ? and OrgID = ?";

    private static final String SQL_SELECT_HERO_ORG
            = "select * from heroesorgbridge where HeroID = ? and OrgID = ?";

    private static final String SQL_SELECT_ALL_HERO_ORGS
            = "select * from heroesorgbridge";

    //Location
    private static final String SQL_ADD_LOCATION
            = "insert into locations (LocationName, LocationDescription, Street"
            + ", City, Lat, Longitude) values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from locations where LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update locations set LocationName = ?, LocationDescription = ?, Street = ? "
            + ", City = ?, Lat = ?, Longitude = ? where LocationID =  ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from locations where LocationID = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from locations";

    //Sightings
    private static final String SQL_ADD_SIGHTING
            = "insert into sightings (HeroID, LocationID, Date"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sightings where SightingID = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sightings set HeroID = ?, LocationID = ?, Date = ? "
            + "where SightingID =  ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sightings where SightingID = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sightings";

    //"Reports"
    private static final String SQL_SIGHTINGS_AT_LOCATION
            = "select * from sightings where LocationID = ?";

    private static final String SQL_SIGHTINGS_LOCATIONS_OF_HERO
            = "select * from sightings where HeroID = ?";

    private static final String SQL_SIGHTINGS_BY_DATE
            = "select * from sightings where Date = ?";

    private static final String SQL_SELECT_ORG_MEMBERS
            = "select * from heroesorgbridge where OrgID = ?";

    private static final String SQL_SELECT_HERO_MEMBERSHIP
            = "select * from heroesorgbridge where HeroID = ?";

    //Heroes
    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroName(rs.getString("HeroName"));
            hero.setHeroDescription(rs.getString("HeroDescription"));
            hero.setHeroSuperPower(rs.getString("HeroSuperPower"));
            hero.setHeroID(rs.getInt("HeroID"));
            return hero;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {

        jdbcTemplate.update(SQL_ADD_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getHeroSuperPower());

        int heroID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        hero.setHeroID(heroID);
    }

    @Override
    public void deleteHero(int heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO, heroID);
    }

    @Override
    public void updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getHeroSuperPower(),
                hero.getHeroID());
    }

    @Override
    public Hero getHeroById(int heroID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO,
                    new HeroMapper(),
                    heroID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROES,
                new HeroMapper());
    }

    //Org Related
    private static final class OrgMapper implements RowMapper<Org> {

        @Override
        public Org mapRow(ResultSet rs, int i) throws SQLException {
            Org org = new Org();
            org.setOrgName(rs.getString("OrgName"));
            org.setOrgDescription(rs.getString("OrgDescription"));
            org.setLocationID(rs.getInt("LocationID"));
            org.setOrgID(rs.getInt("OrgID"));
            return org;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Org org) {

        jdbcTemplate.update(SQL_ADD_ORG,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getLocationID());

        int OrgID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        org.setOrgID(OrgID);
    }

    @Override
    public void deleteOrg(int orgID) {
        jdbcTemplate.update(SQL_DELETE_ORG, orgID);
    }

    @Override
    public void updateOrg(Org org) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getLocationID(),
                org.getOrgID());
    }

    @Override
    public Org getOrgById(int orgID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORG,
                    new OrgMapper(),
                    orgID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Org> getAllOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGS,
                new OrgMapper());
    }

    //HeroOrgBridge
    private static final class HeroOrgMapper implements RowMapper<HeroOrgBridge> {

        @Override
        public HeroOrgBridge mapRow(ResultSet rs, int i) throws SQLException {
            HeroOrgBridge hob = new HeroOrgBridge();
            hob.setHeroID(rs.getInt("HeroID"));
            hob.setOrgID(rs.getInt("OrgID"));
            return hob;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHeroOrg(HeroOrgBridge hob) {

        jdbcTemplate.update(SQL_ADD_HERO_ORG,
                hob.getHeroID(),
                hob.getOrgID());
    }

    @Override
    public void deleteHeroOrg(int heroID, int orgID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG, heroID, orgID);
    }

    @Override
    public void updateHeroOrg(HeroOrgBridge hob, int oldHeroID, int oldOrgID) {
        deleteHeroOrg(oldHeroID, oldOrgID);
        addHeroOrg(hob);
    }

    @Override
    public HeroOrgBridge getHeroOrg(int heroID, int orgID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO_ORG,
                    new HeroOrgMapper(),
                    heroID, orgID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroOrgBridge> getAllHeroOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HERO_ORGS,
                new HeroOrgMapper());
    }

    //Locations
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationDescription(rs.getString("LocationDescription"));
            location.setStreet(rs.getString("Street"));
            location.setCity(rs.getString("City"));
            location.setLat(rs.getFloat("Lat"));
            location.setLongitude(rs.getFloat("Longitude"));
            location.setLocationID(rs.getInt("LocationID"));
            return location;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {

        jdbcTemplate.update(SQL_ADD_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getStreet(),
                location.getCity(),
                location.getLat(),
                location.getLongitude());

        int locationID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        location.setLocationID(locationID);
    }

    @Override
    public void deleteLocation(int locationID) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationID);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getStreet(),
                location.getCity(),
                location.getLat(),
                location.getLongitude(),
                location.getLocationID());
    }

    @Override
    public Location getLocationById(int locationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    locationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }

    //Sightings
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setHeroID(rs.getInt("HeroID"));
            sighting.setLocationID(rs.getInt("LocationID"));
            sighting.setDate(rs.getTimestamp("Date").
                    toLocalDateTime().toLocalDate());
            return sighting;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {

        jdbcTemplate.update(SQL_ADD_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocationID(),
                sighting.getDate().toString());
        int sightingID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingID(sightingID);
    }

    @Override
    public void deleteSighting(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingID);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocationID(),
                sighting.getDate().toString(),
                sighting.getSightingID());
    }

    @Override
    public Sighting getSightingById(int sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
    }

    //Reports
    @Override
    public List<Sighting> getSightingsByLocation(int LocationID) {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SIGHTINGS_AT_LOCATION,
                        new SightingMapper(),
                        LocationID);
        return sightingList;
    }

    @Override
    public List<Sighting> getSightingsByHero(int HeroID) {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SIGHTINGS_LOCATIONS_OF_HERO,
                        new SightingMapper(),
                        HeroID);
        return sightingList;
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate sightingDate) {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SIGHTINGS_BY_DATE,
                        new SightingMapper(),
                        sightingDate.toString());
        return sightingList;
    }

    @Override
    public List<HeroOrgBridge> getOrgMembers(int orgID) {
        List<HeroOrgBridge> heroOrgList
                = jdbcTemplate.query(SQL_SELECT_ORG_MEMBERS,
                        new HeroOrgMapper(),
                        orgID);
        return heroOrgList;
    }

    @Override
    public List<HeroOrgBridge> getHeroMembership(int heroID) {
        List<HeroOrgBridge> heroOrgList
                = jdbcTemplate.query(SQL_SELECT_HERO_MEMBERSHIP,
                        new HeroOrgMapper(),
                        heroID);
        return heroOrgList;
    }
}
