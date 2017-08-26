/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.util.List;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author n0252282
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    private BigDecimal currentMoney = new BigDecimal("0");

    //Pass through method
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

//Pass through method
    @Override
    public List<Item> getAllItemsFiltered() throws VendingMachinePersistenceException {
        return dao.getAllItemsFiltered();
    }

    //Pass through method
    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        return dao.getItem(itemID);
    }
    //Pass through method

    @Override
    public Change purchaseItem(String itemID) throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException {
        Item itemToPurchase = dao.getItem(itemID);
        BigDecimal oneHundred = new BigDecimal("100");

        if (validateItem(itemID)) {
            if (currentMoney.compareTo(itemToPurchase.getItemPrice()) >= 0) {
                //we have the item in stock and we have enough money 
                //Remaining money to integer
                int remainingCash = currentMoney.subtract(itemToPurchase.getItemPrice()).multiply(oneHundred).intValueExact();
                //reduce inventory by 1
                makeSaleReduceInventory(itemID);
                //get change
                return giveChange(remainingCash);
            } else {
                throw new InsufficientFundsException(
                        "Not enough money!  Cannot purchase " + itemToPurchase.getItemName() + ".");
            }
        } else {
            throw new NoItemInventoryException(
                    "Quantity = 0, cannot purchase " + itemToPurchase.getItemName() + ".");
        }
    }

    @Override
    public Item makeSaleReduceInventory(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removedItem = dao.makeSaleReduceInventory(itemID);
        return removedItem;
    }

    private Boolean validateItem(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item item = dao.getItem(itemID);
        if (item != null) {
            if (item.getItemQuantity() <= 0) {
                throw new NoItemInventoryException(
                        "Quantity = 0, cannot purchase " + item.getItemName() + ".");
            } else {
                return true;
            }
        } else {
            throw new NoItemInventoryException(
                    "You must choose an item from the inventory.");
        }
    }

    @Override
    public Change giveChange(int remainingCash) throws VendingMachinePersistenceException {
        //Update cash inserted
        this.currentMoney = new BigDecimal("0");
        //Let's make and return change
        return new Change(remainingCash);
    }

    @Override
    public Change cancelGiveChange() throws VendingMachinePersistenceException, InsufficientFundsException {
        if (currentMoney.compareTo(new BigDecimal("0")) > 0) {
            BigDecimal oneHundred = new BigDecimal("100");
            int remainingCash = currentMoney.multiply(oneHundred).intValueExact();
            return giveChange(remainingCash);
        } else {
            throw new InsufficientFundsException("No money to return change on.");
        }

    }

    @Override
    public BigDecimal getCurrentMoney() throws NumberFormatException {
        return currentMoney;
    }

    @Override
    public void setCurrentMoney(String currentMoney) throws NumberFormatException {
        if (currentMoney.compareTo(new BigDecimal("0")) > 0) {
            this.currentMoney = this.currentMoney.add(currentMoney, MathContext.UNLIMITED);
        } else {
            throw new NumberFormatException("Money added must be greater than 0.");
        }
    }
    
    
}
