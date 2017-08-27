/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.service;

import com.mycompany.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    Item getItem(Integer itemID) throws VendingMachinePersistenceException;

    Change purchaseItem(Integer itemID) throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException;

    Item makeSaleReduceInventory(Integer itemID) throws VendingMachinePersistenceException, NoItemInventoryException;

    public void setCurrentMoney(BigDecimal moneyEntry);

    public BigDecimal getCurrentMoney();

    public Change giveChange(int remainingCash) throws VendingMachinePersistenceException;

    public Change cancelGiveChange() throws VendingMachinePersistenceException, InsufficientFundsException;

    public void setSelection(Integer selection);

    public Integer getSelection();
    
    public String getMessage();
    
    public void setMessage(String message);

    public String getChangeMessage();

}
