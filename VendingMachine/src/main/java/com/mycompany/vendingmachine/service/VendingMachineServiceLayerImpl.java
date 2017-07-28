/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.VendingMachinePersistenceException;
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
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        return dao.getItem(itemID);
    }
    //Pass through method

    @Override
    public Change purchaseItem(String itemID) throws VendingMachinePersistenceException, VendingMachineDataValidationException {
        Item itemToPurchase = dao.getItem(itemID);
        BigDecimal oneHundred = new BigDecimal("100");
        if (validateItem(itemID)) {
            if (currentMoney.compareTo(itemToPurchase.getItemPrice()) > 0) {
                makeSaleReduceInventory(itemID);
                //Remaining money to integer
                int remainingCash =  currentMoney.subtract(itemToPurchase.getItemPrice()).multiply(oneHundred).intValueExact();
                //Let's make and return change
                return new Change(remainingCash);
            } else {
                throw new VendingMachineDataValidationException(
                        "Not enough money!");
            }
        } else {
            throw new VendingMachineDataValidationException(
                    "Quantity = 0, cannot purchase");
        }
    }

    @Override
    public Item makeSaleReduceInventory(String itemID) throws VendingMachinePersistenceException {
        Item removedItem = dao.makeSaleReduceInventory(itemID);
        return removedItem;
    }

    private Boolean validateItem(String itemID) throws VendingMachineDataValidationException, VendingMachinePersistenceException {
        Item item = dao.getItem(itemID);

        if (item.getItemQuantity() <= 0) {

            throw new VendingMachineDataValidationException(
                    "Quantity = 0, cannot purchase");
        } else {
            return true;
        }
    }

    @Override
    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    @Override
    public void setCurrentMoney(BigDecimal currentMoney) {
        this.currentMoney = this.currentMoney.add(currentMoney, MathContext.UNLIMITED);
    }

}
