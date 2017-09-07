/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author n0211021
 */
public class DvdLibraryDaoDBImpl implements DvdLibraryDao {

    private static final String SQL_INSERT_DVD
            = "insert into dvds "
            + "(dvdTitle, releaseYear, director, rating, notes) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD
            = "delete from dvds where dvdID = ?";
    private static final String SQL_SELECT_DVD
            = "select * from dvds where dvdID = ?";
    private static final String SQL_UPDATE_DVD
            = "update dvds set "
            + "dvdTitle = ?, releaseYear = ?, director = ?, "
            + "rating = ?, notes = ? "
            + "where dvdID = ?";
    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvds";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
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
        jdbcTemplate.update(SQL_DELETE_DVD, dvdID);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getDvdTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes(),
                dvd.getDvdID());                                
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS,
                new DvdMapper());
    }

    // holds all of our Dvd objects - simulates the database
    private Map<Integer, Dvd> dvdMap = new HashMap<>();

    @Override
    public Dvd getDvdByID(Integer dvdID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
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
        String dvdTitleSearchCriteria
                = criteria.get(SearchTerm.DVD_TITLE);
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
        Predicate<Dvd> dvdTitleMatchPredicate;
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
        if (dvdTitleSearchCriteria == null
                || dvdTitleSearchCriteria.isEmpty()) {
            dvdTitleMatchPredicate = truePredicate;
        } else {
            dvdTitleMatchPredicate
                    = (c) -> c.getDvdTitle().equals(dvdTitleSearchCriteria);
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
                .filter(dvdTitleMatchPredicate
                        .and(dvdDirectorMatchPredicate)
                        .and(dvdYearMatchPredicate)
                        .and(dvdRatingMatchPredicate))
                .collect(Collectors.toList());
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdID(rs.getInt("dvdID"));
            dvd.setDvdTitle(rs.getString("dvdTitle"));
            dvd.setReleaseYear(rs.getInt("releaseYear"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }
}
