/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

/**
 *
 * @author n0252282
 */
public class VendingMachineDuplicateIdException extends Exception {

    public VendingMachineDuplicateIdException(String message) {
        super(message);
    }

    public VendingMachineDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
