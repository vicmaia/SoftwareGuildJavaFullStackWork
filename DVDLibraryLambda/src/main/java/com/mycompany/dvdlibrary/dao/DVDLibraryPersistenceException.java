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
public class DVDLibraryPersistenceException extends Exception {

    public DVDLibraryPersistenceException(String message) {
        super(message);
    }

    public DVDLibraryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
