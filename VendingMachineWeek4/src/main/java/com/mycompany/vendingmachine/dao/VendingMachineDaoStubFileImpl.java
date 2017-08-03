/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author n0252282
 */
public class VendingMachineDaoStubFileImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<>();

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<>(items.values());
    }

    @Override
    public List<Item> getAllItemsFiltered() throws VendingMachinePersistenceException {
        return items
                .values()
                .stream()
                .filter(i -> i.getItemQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        return items.get(itemID);
    }

    @Override
    public Item makeSaleReduceInventory(String itemID) throws NoItemInventoryException, VendingMachinePersistenceException {
        Item removedItem = items.get(itemID);
        if (removedItem.getItemQuantity() > 0) {
            removedItem.setItemQuantity(removedItem.getItemQuantity() - 1);
        } else {
            throw new NoItemInventoryException("Not possible to reduce inventory below 0");
        }
        return removedItem;
    }

    //Added for future use and for testing
    @Override
    public Item addItem(String itemID, Item item) throws VendingMachinePersistenceException {
        Item newItem = items.put(itemID, item);
        return newItem;
    }

    @Override
    public Item removeItem(String itemID) throws VendingMachinePersistenceException {
        Item removedItem = items.remove(itemID);
        return removedItem;
    }
}
