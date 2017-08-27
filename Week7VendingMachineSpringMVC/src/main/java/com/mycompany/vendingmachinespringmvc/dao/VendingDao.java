/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.dao;

import com.mycompany.vendingmachinespringmvc.model.Item;
import com.mycompany.vendingmachinespringmvc.service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface VendingDao {
    public List<Item> getAllItems() throws VendingMachinePersistenceException;
    public Item getItem(Integer itemId) throws VendingMachinePersistenceException;
    public Item makeSaleReduceInventory(Integer itemId) throws VendingMachinePersistenceException, NoItemInventoryException;
}
