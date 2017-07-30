/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author n0252282
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Item stubInventory;
    private List<Item> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        stubInventory = new Item("1");
        stubInventory.setItemName("Taco");
        stubInventory.setItemPrice(".50");
        stubInventory.setItemQuantity(3);

//        stubInventory = new Item("2");
//        stubInventory.setItemName("Nacho");
//        stubInventory.setItemPrice("1.50");
//        stubInventory.setItemQuantity(0);

        itemList.add(stubInventory);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public List<Item> getAllItemsFiltered() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        if (itemID.equals(stubInventory.getItemID())) {
            return stubInventory;
        } else {
            return null;
        }
    }

    @Override
    public Item makeSaleReduceInventory(String itemID) throws VendingMachinePersistenceException {
        if (itemID.equals(stubInventory.getItemID())) {
            return stubInventory;
        } else {
            return null;
        }
    }

    @Override
    public Item addItem(String itemID, Item item) throws VendingMachinePersistenceException {
        if (itemID.equals(stubInventory.getItemID())) {
            return stubInventory;
        } else {
            return null;
        }
    }

    @Override
    public Item removeItem(String itemID) throws VendingMachinePersistenceException {
        if (itemID.equals(stubInventory.getItemID())) {
            return stubInventory;
        } else {
            return null;
        }
    }

}
