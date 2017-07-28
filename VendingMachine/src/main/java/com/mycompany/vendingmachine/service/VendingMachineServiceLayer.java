/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface VendingMachineServiceLayer {

    

    //for future use
//    void createItem (Item student) throws
//            VendingMachineDuplicateIdException,
//            VendingMachineDataValidationException,
//            VendingMachinePersistenceException;
    List<Item> getAllItems() throws
            VendingMachinePersistenceException;

    Item getItem(String itemID) throws
            VendingMachinePersistenceException;

    Item removeItem(String itemID) throws
            VendingMachinePersistenceException;

    public void setCurrentMoney(BigDecimal moneyEntry);

    public BigDecimal getCurrentMoney();
}
