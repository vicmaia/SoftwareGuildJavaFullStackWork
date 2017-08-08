/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dao;

/**
 *
 * @author n0252282
 */
public class PlayerPersistenceException extends Exception {

    public PlayerPersistenceException(String message) {
        super(message);
    }

    public PlayerPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
