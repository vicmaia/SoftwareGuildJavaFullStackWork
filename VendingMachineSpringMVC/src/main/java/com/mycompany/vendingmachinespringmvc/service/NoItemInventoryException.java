/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.service;

/**
 *
 * @author n0252282
 */
public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
