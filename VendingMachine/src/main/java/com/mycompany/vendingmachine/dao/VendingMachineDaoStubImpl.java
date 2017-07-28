/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Item onlyItem;
    private List<Item> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("1");
        onlyItem.setItemName("Taco");
        onlyItem.setItemPrice(".50");
        onlyItem.setItemQuantity(3);

        itemList.add(onlyItem);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        if (itemID.equals(onlyItem.getItemID())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item removeItem(String itemID) throws VendingMachinePersistenceException {
        if (itemID.equals(onlyItem.getItemID())) {
            return onlyItem;
        } else {
            return null;
        }
    }
}
