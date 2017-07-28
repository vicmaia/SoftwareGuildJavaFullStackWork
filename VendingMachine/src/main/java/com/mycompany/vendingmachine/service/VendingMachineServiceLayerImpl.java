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
    public Item removeItem(String itemID) throws VendingMachinePersistenceException {
        //return dao.removeStudent(studentId);
        Item removedItem = dao.removeItem(itemID);
        return removedItem;
    }

    private void validateItemData(Item item) throws
            VendingMachineDataValidationException {

        if (item.getItemID() == null
                || item.getItemName().trim().length() == 0
                || item.getItemPrice() == null
                || item.getItemQuantity() < 0) {

            throw new VendingMachineDataValidationException(
                    "ERROR: All fields [Item Name, Item Price, and Item Quantity] are required.");
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
