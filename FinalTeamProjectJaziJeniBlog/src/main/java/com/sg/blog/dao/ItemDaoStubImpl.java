/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0001123
 */
public class ItemDaoStubImpl implements ItemDao {

    Map<Integer, Item> itemList = new HashMap();

    public ItemDaoStubImpl() {
        Item item = new Item();
        item.setItemID(10);
        item.setItemName("Necklace");
        item.setItemDescription("Gold");
        item.setItemPrice(new BigDecimal("49.99"));
        item.setItemQuantity(10);
        itemList.put(item.getItemID(), item);
    }

    @Override
    public void addItem(Item item) {
        itemList.put(item.getItemID(), item);
    }

    @Override
    public void deleteItem(int itemID) {
        itemList.remove(itemID);
    }

    @Override
    public void updateItem(Item item) {
        deleteItem(item.getItemID());
        addItem(item);
    }

    @Override
    public Item getItemById(int itemID) {
        return itemList.get(itemID);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(itemList.values());
    }

}
