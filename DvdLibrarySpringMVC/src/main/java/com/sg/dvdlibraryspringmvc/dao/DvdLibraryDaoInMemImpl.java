/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author n0211021
 */
public class DvdLibraryDaoInMemImpl implements DvdLibraryDao{
    
    private DvdLibraryDaoInMemImpl () {
        Dvd dvd = new Dvd();
        dvd.setDirector("Bobby");
        dvd.setDvdID(1);
        dvd.setDvdTitle("Flubber");
        dvd.setRating("R");
        dvd.setReleaseYear(1999);
        dvd.setNotes("Simply the best piece of theatrical art I've ever witnessed");
        dvdMap.put(dvd.getDvdID(), dvd);
    }
    
  

    // holds all of our Dvd objects - simulates the database
    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    // used to assign ids to Dvds - simulates the auto increment
    // primary key for Dvds in a database
    private static Integer dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
        // assign the current counter values as the dvdid
        dvd.setDvdID(dvdIdCounter);
        // increment the counter so it is ready for use for the 
        // next dvd
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdID(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(Integer dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdID(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        
        Collection<Dvd> c = dvdMap.values();
        return new ArrayList(c);
    }

    @Override
    public Dvd getDvdByID(Integer dvdID) {
        return dvdMap.get(dvdID);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        // Get all the search term values from the map
        String dvdTitleSearchCriteria =  
            criteria.get(SearchTerm.DVD_TITLE);
        String dvdDirectorSearchCriteria = 
             criteria.get(SearchTerm.DIRECTOR);
        String dvdYearSearchCriteria = 
            criteria.get(SearchTerm.YEAR);
        String dvdRatingSearchCriteria = 
            criteria.get(SearchTerm.RATING);
        
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
        if (dvdTitleSearchCriteria == null || 
            dvdTitleSearchCriteria.isEmpty()) {
            dvdTitleMatchPredicate = truePredicate;
        } else {
            dvdTitleMatchPredicate = 
                (c) -> c.getDvdTitle().equals(dvdTitleSearchCriteria);
        }

        if (dvdDirectorSearchCriteria == null || 
            dvdDirectorSearchCriteria.isEmpty()) {
            dvdDirectorMatchPredicate = truePredicate;
        } else {
            dvdDirectorMatchPredicate = 
                (c) -> c.getDirector().equals(dvdDirectorSearchCriteria);
        }

        if (dvdYearSearchCriteria == null || 
            dvdYearSearchCriteria.isEmpty()) {
            dvdYearMatchPredicate = truePredicate;
        } else {
            dvdYearMatchPredicate = 
                (c) -> c.getReleaseYear().equals(dvdYearSearchCriteria);
        }

        if (dvdRatingSearchCriteria == null || 
            dvdRatingSearchCriteria.isEmpty()) {
            dvdRatingMatchPredicate = truePredicate;
        } else {
            dvdRatingMatchPredicate = 
                (c) -> c.getRating().equals(dvdRatingSearchCriteria);
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
    

}
   
