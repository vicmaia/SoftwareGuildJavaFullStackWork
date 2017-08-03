/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

/**
 *
 * @author n0252282
 */
public interface DVDLibraryAuditDao {

    public void writeAuditEntry(String entry) throws DVDLibraryPersistenceException;
}
