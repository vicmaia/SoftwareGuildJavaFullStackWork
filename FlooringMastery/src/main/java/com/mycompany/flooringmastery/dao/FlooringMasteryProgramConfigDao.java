/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

/**
 *
 * @author n0252282
 */
public interface FlooringMasteryProgramConfigDao {

    Boolean getConfig(Boolean prodMode) throws FlooringMasteryPersistenceException;
}
