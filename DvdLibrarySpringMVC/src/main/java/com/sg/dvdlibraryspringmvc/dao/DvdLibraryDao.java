/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0211021
 */
public interface DvdLibraryDao {
      // add the given Dvd to the data store
    public Dvd addDvd(Dvd dvd);

    // remove the Dvd with the given id from the data store
    public void removeDvd(Integer dvdID);

    // update the given Dvd in the data store
    public void updateDvd(Dvd dvd);

    // retrieve all Dvds from the data store
    public List<Dvd> getAllDvds();

    // retrieve the Dvd with the given id from the data store
    public Dvd getDvdByID(Integer dvdID);

    // search for Dvds by the given search criteria values
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);  
}
