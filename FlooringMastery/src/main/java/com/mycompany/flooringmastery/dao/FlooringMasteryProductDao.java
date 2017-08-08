/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface FlooringMasteryProductDao {

    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;

    Product getProduct(String itemID) throws FlooringMasteryPersistenceException;
}
