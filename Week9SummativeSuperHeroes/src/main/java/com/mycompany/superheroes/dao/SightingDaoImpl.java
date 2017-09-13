/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

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
public class SightingDaoImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
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

}
