/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.dao;

/**
 *
 * @author n0252282
 */
public class AddressBookException extends Exception {

    public AddressBookException(String message) {
        super(message);
    }

    public AddressBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
