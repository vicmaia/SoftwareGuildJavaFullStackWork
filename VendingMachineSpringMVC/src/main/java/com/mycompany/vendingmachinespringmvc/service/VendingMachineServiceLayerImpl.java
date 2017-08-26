/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.service;

import com.mycompany.vendingmachinespringmvc.dao.VendingDao;
import java.util.List;
import com.mycompany.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.inject.Inject;

/**
 *
 * @author n0252282
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingDao dao;

    @Inject
    public VendingMachineServiceLayerImpl(VendingDao dao) {
        this.dao = dao;
    }

    private BigDecimal currentMoney = new BigDecimal("0");
    private Integer selection = 0;
    private String message = "Please make a selection";

    //Pass through method
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    //Pass through method
    @Override
    public Item getItem(Integer itemId) throws VendingMachinePersistenceException {
        return dao.getItem(itemId);
    }

    //Pass through method
    @Override
    public Change purchaseItem(Integer itemId) throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException {
        Item itemToPurchase = dao.getItem(itemId);
        BigDecimal oneHundred = new BigDecimal("100");

        if (validateItem(itemId)) {
            if (currentMoney.compareTo(itemToPurchase.getPrice()) >= 0) {
                //we have the item in stock and we have enough money 
                //Remaining money to integer
                int remainingCash = currentMoney.subtract(itemToPurchase.getPrice()).multiply(oneHundred).intValueExact();
                //reduce inventory by 1
                makeSaleReduceInventory(itemId);
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
    public Item makeSaleReduceInventory(Integer itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removedItem = dao.makeSaleReduceInventory(itemId);
        return removedItem;
    }

    private Boolean validateItem(Integer itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item item = dao.getItem(itemId);
        if (item != null) {
            if (item.getQuantity() <= 0) {
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
    public void setCurrentMoney(BigDecimal moneyEntry) throws NumberFormatException {
        if (moneyEntry.compareTo(new BigDecimal("0")) > 0) {
            this.currentMoney = this.currentMoney.add(moneyEntry, MathContext.UNLIMITED);
        } else {
            throw new NumberFormatException("Money added must be greater than 0.");
        }
    }

    @Override
    public Integer getSelection() {
        return selection;
    }

    @Override
    public void setSelection(Integer selection) {
        this.selection = selection;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
