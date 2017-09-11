/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.dao;

import com.mycompany.addressBook.dto.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.tree.RowMapper;

/**
 *
 * @author n0252282
 */
public class AddressBookDaoDBImpl implements AddressBookDao {
    private static final String SQL_INSERT_ADDRESS
            = "insert into address "
            + "(lastName, firstName, streetAddress, city, state, zip) "
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ADDRESS
            = "delete from address where lastName = ?";
    private static final String SQL_SELECT_ADDRESS
            = "select * from address where lastName = ?";
    private static final String SQL_UPDATE_ADDRESS
            = "update address set "
            + "lastName = ?, firstName = ?, streetAddress = ?, "
            + "city = ?, state = ?, zip =? "
            + "where lastName = ?";
    private static final String SQL_SELECT_ALL_ADDRESSS
            = "select * from address";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                dvd.getDvdTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the contact object and return it
        dvd.setDvdID(newId);
        return dvd;
    }

    @Override
    public void removeDvd(Integer dvdID) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, dvdID);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                dvd.getDvdTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes(),
                dvd.getDvdID());                                
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSS,
                new DvdMapper());
    }

    // holds all of our Dvd objects - simulates the database
    private Map<Integer, Dvd> dvdMap = new HashMap<>();

    @Override
    public Dvd getDvdByID(Integer dvdID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
                    new DvdMapper(), dvdID);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        // Get all the search term values from the map
        String lastNameSearchCriteria
                = criteria.get(SearchTerm.ADDRESS_TITLE);
        String dvdDirectorSearchCriteria
                = criteria.get(SearchTerm.DIRECTOR);
        String dvdYearSearchCriteria
                = criteria.get(SearchTerm.YEAR);
        String dvdRatingSearchCriteria
                = criteria.get(SearchTerm.RATING);

        // Declare all the predicate conditions - remember that
        // Predicate is a functional interface with one method
        // called test(T t) that returns a boolean.  Since
        // Predicate is generic, we get to specify the type of
        // object we want T to be - in our case, we want T to be
        // of type Dvd.  That means the Predicates declared 
        // here will have one method: boolean test(Dvd c)
        Predicate<Dvd> lastNameMatchPredicate;
        Predicate<Dvd> dvdDirectorMatchPredicate;
        Predicate<Dvd> dvdYearMatchPredicate;
        Predicate<Dvd> dvdRatingMatchPredicate;

        // Placeholder predicate - always returns true. Used for 
        // search terms that are empty - if the user didn't specify 
        // a value for one of the search terms, we must return true
        // because we are ANDing all the search terms together and 
        // our spec says that we return everything in the DAO when
        // the user leaves all the search terms blank.
        Predicate<Dvd> truePredicate = (c) -> {
            return true;
        };

        // Assign values to predicates. If a given search term is empty, 
        // just assign the default truePredicate, otherwise assign the 
        // predicate that only returns true when it finds a match for the 
        // given term.
        if (lastNameSearchCriteria == null
                || lastNameSearchCriteria.isEmpty()) {
            lastNameMatchPredicate = truePredicate;
        } else {
            lastNameMatchPredicate
                    = (c) -> c.getDvdTitle().equals(lastNameSearchCriteria);
        }

        if (dvdDirectorSearchCriteria == null
                || dvdDirectorSearchCriteria.isEmpty()) {
            dvdDirectorMatchPredicate = truePredicate;
        } else {
            dvdDirectorMatchPredicate
                    = (c) -> c.getDirector().equals(dvdDirectorSearchCriteria);
        }

        if (dvdYearSearchCriteria == null
                || dvdYearSearchCriteria.isEmpty()) {
            dvdYearMatchPredicate = truePredicate;
        } else {
            dvdYearMatchPredicate
                    = (c) -> c.getReleaseYear().equals(dvdYearSearchCriteria);
        }

        if (dvdRatingSearchCriteria == null
                || dvdRatingSearchCriteria.isEmpty()) {
            dvdRatingMatchPredicate = truePredicate;
        } else {
            dvdRatingMatchPredicate
                    = (c) -> c.getRating().equals(dvdRatingSearchCriteria);
        }

        // Return the list of Dvds that match the given criteria. 
        // To do this we just AND all the predicates together in a 
        // filter operation.
        return dvdMap.values().stream()
                .filter(lastNameMatchPredicate
                        .and(dvdDirectorMatchPredicate)
                        .and(dvdYearMatchPredicate)
                        .and(dvdRatingMatchPredicate))
                .collect(Collectors.toList());
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdID(rs.getInt("dvdID"));
            dvd.setDvdTitle(rs.getString("lastName"));
            dvd.setReleaseYear(rs.getInt("firstName"));
            dvd.setDirector(rs.getString("streetAddress"));
            dvd.setRating(rs.getString("city"));
            dvd.setNotes(rs.getString("state"));
            return dvd;
        }
    }
    private Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookException {
        Address newAddress = addresses.put(lastName, address);
        writeAddress();
        return newAddress;
    }

    @Override
    public List<Address> getAllAddresses() throws AddressBookException {
        loadAddresss();
        return new ArrayList<Address>(addresses.values());
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookException {
        loadAddresss();
        return addresses.get(lastName);
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookException {
        Address removedAddress = addresses.remove(lastName);
        writeAddress();
        return removedAddress;
    }
}
