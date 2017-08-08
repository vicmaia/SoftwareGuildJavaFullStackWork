/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface FlooringMasteryTaxDao {

    List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException;

    Tax getTax(String taxID) throws FlooringMasteryPersistenceException;
}
