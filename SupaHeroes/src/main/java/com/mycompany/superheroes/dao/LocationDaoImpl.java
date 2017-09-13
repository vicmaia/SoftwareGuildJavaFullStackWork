/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LocationDaoImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
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
}
