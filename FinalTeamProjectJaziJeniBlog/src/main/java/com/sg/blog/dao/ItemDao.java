/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Item;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface ItemDao {

    public void addItem(Item item);

    public void deleteItem(int itemID);

    public void updateItem(Item item);

    public Item getItemById(int itemID);

    public List<Item> getAllItems();
}
