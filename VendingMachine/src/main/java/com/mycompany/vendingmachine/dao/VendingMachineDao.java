/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface VendingMachineDao {

    //Item addItem(String studentId, Item student) throws VendingMachinePersistenceException;
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    List<Item> getAllItemsFiltered() throws VendingMachinePersistenceException;

    Item getItem(String itemID) throws VendingMachinePersistenceException;

    Item makeSaleReduceInventory(String itemID) throws NoItemInventoryException, VendingMachinePersistenceException;

}
