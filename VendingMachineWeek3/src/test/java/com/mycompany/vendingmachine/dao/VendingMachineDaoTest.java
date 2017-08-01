/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author n0252282
 */
public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoTest() {
    }

    @Before
    public void setUp() throws Exception {
        List<Item> itemList = dao.getAllItems();
        for (Item item : itemList) {
            dao.removeItem(item.getItemID());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);

        Item item2 = new Item("2");
        item2.setItemName("Nacho");
        item2.setItemPrice("1.50");
        item2.setItemQuantity(0);

        dao.addItem(item2.getItemID(), item2);

        assertEquals(2, dao.getAllItems().size());;

    }

    /**
     * Test of getAllItemsFiltered method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItemsFiltered() throws Exception {
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);

        Item item2 = new Item("2");
        item2.setItemName("Nacho");
        item2.setItemPrice("1.50");
        item2.setItemQuantity(0);

        dao.addItem(item2.getItemID(), item2);

        assertEquals(1, dao.getAllItemsFiltered().size());;

    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    //these methods are not necc. for current program
    @Test
    public void testAddItemGetItem() throws Exception {
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);

        assertEquals(item1, dao.getItem("1"));
    }

    /**
     * Test of makeSaleReduceInventory method, of class VendingMachineDao.
     */
    @Test
    public void testMakeSaleReduceInventory() throws Exception {
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);
        dao.makeSaleReduceInventory("1");

        Item testItem = dao.getItem(item1.getItemID());
        int testItemQuantity = testItem.getItemQuantity();

        assertEquals(2, testItemQuantity);
    }
}
