/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrarymvc.dao;

import com.mycompany.dvdlibrarymvc.model.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0252282
 */
public interface DvdLibraryDao {
    // add the given Dvd to the data store
    public Dvd addContact(Dvd contact);

    // remove the Dvd with the given id from the data store
    public void removeContact(long contactId);

    // update the given Dvd in the data store
    public void updateContact(Dvd contact);

    // retrieve all Contacts from the data store
    public List<Dvd> getAllContacts();

    // retrieve the Dvd with the given id from the data store
    public Dvd getContactById(long contactId);

    // search for Contacts by the given search criteria values
    public List<Dvd> searchContacts(Map<SearchTerm, String> criteria);
}