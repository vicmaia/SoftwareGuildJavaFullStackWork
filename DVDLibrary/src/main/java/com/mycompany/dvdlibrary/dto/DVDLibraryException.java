/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dto;

/**
 *
 * @author n0252282
 */
public class DVDLibraryException extends Exception {

    public DVDLibraryException(String message) {
        super(message);
    }

    public DVDLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}
