/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.dao;

import com.mycompany.vendingmachinespringmvc.model.Item;
import com.mycompany.vendingmachinespringmvc.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

/**
 *
 * @author n0252282
 */
public class VendingDaoStubInMemImpl implements VendingDao {

    private Map<Integer, Item> items = newHashMap();

    public VendingDaoStubInMemImpl() {
        Item item = new Item(1, "Goldfish", new BigDecimal(.75), 8);
        items.put(item.getItemId(), item);

        item = new Item(2, "Doritos", new BigDecimal(1.00), 8);
        items.put(item.getItemId(), item);

        item = new Item(3, "M&Ms", new BigDecimal(1.25), 7);
        items.put(item.getItemId(), item);

        item = new Item(4, "Swedish Fish", new BigDecimal(1.50), 6);
        items.put(item.getItemId(), item);

        item = new Item(5, "Snickers", new BigDecimal(1.75), 5);
        items.put(item.getItemId(), item);

        item = new Item(6, "Hershey Bar", new BigDecimal(2.00), 4);
        items.put(item.getItemId(), item);

        item = new Item(7, "Slim Jim", new BigDecimal(2.25), 3);
        items.put(item.getItemId(), item);

        item = new Item(8, "Trail Mix", new BigDecimal(2.50), 2);
        items.put(item.getItemId(), item);

        item = new Item(9, "Party Mix", new BigDecimal(2.75), 0);
        items.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(Integer itemId) throws VendingMachinePersistenceException {
        return items.get(itemId);
    }

    @Override
    public Item makeSaleReduceInventory(Integer itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removedItem = items.get(itemId);
        if (removedItem.getQuantity() > 0) {
            removedItem.setQuantity(removedItem.getQuantity() - 1);
        } else {
            throw new NoItemInventoryException("Not possible to reduce inventory below 0");
        }
        return removedItem;
    }

}
