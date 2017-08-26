/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author n0211021
 */
@Controller
public class SearchController {

    private DvdLibraryDao dao;

    @Inject
    public SearchController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displaySearchPage", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @RequestMapping(value = "/search/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap) {
        // Create the map of search criteria to send to the DAO
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        // Determine which search terms have values, translate the String
        // keys into SearchTerm enums, and set the corresponding values
        // appropriately.
        String currentTerm = searchMap.get("dvdTitle");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DVD_TITLE, currentTerm);
        }
        currentTerm = searchMap.get("director");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR, currentTerm);
        }
        currentTerm = searchMap.get("rating");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RATING, currentTerm);
        }
        currentTerm = searchMap.get("releaseYear");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.YEAR, currentTerm);
        }

        return dao.searchDvds(criteriaMap);
    }

}
