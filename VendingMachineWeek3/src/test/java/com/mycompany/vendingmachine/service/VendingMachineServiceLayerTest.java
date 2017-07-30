/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineDaoStubFileImpl;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author n0252282
 */
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;
    private VendingMachineDao dao = new VendingMachineDaoStubFileImpl();

    public VendingMachineServiceLayerTest() {
        service = new VendingMachineServiceLayerImpl(dao);
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAllItems();
        //Clean list
        for (Item item : itemList) {
            //remove item only in dao, no functionality in program so not passed to service layer
            dao.removeItem(item.getItemID());

        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        // Add some inventory
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

        assertEquals(2, service.getAllItems().size());;
    }

    /**
     * Test of getAllItemsFiltered method, of class VendingMachineServiceLayer.
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

        assertEquals(1, service.getAllItemsFiltered().size());;
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);

        assertEquals(item1, service.getItem("1"));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testPurchaseItemSuccess() throws Exception {

        //if no exceptions are returned, this test will pass, no assert needed
        //add items
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

        //user adds 3 dollars
        service.setCurrentMoney(new BigDecimal("3"));
        //user makes purchase "Taco for .50"
        service.purchaseItem("1");

    }

    @Test
    public void testPurchaseItemOutOfStock() throws Exception {

        //if we catch exception, this test will pass, no assert needed
        //add item
        Item item2 = new Item("2");
        item2.setItemName("Nacho");
        item2.setItemPrice("1.50");
        item2.setItemQuantity(0);

        dao.addItem(item2.getItemID(), item2);

        //user adds 3 dollars
        service.setCurrentMoney(new BigDecimal("3"));

        //user makes purchase of nacho, which is out of stock
        try {
            service.purchaseItem("2");
            fail("Expected NoItemInventoryException was not thrown.");
        } catch (NoItemInventoryException e) {
            return;
        }
    }

    @Test
    public void testPurchaseNotEnoughMoney() throws Exception {

        //if we catch exception, this test will pass, no assert needed
        //add item
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);

        //user adds 3 dollars
        service.setCurrentMoney(new BigDecimal(".25"));

        //user attempts to purchase taco, insufficient funds.
        try {
            service.purchaseItem("1");
            fail("Expected InsufficientFundsException was not thrown.");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    /**
     * Test of makeSaleReduceInventory method, of class
     * VendingMachineServiceLayer.
     */
    @Test
    public void testMakeSaleReduceInventory() throws Exception {
        Item item1 = new Item("1");
        item1.setItemName("Taco");
        item1.setItemPrice(".50");
        item1.setItemQuantity(3);

        dao.addItem(item1.getItemID(), item1);

        //user adds 3 dollars
        service.setCurrentMoney(new BigDecimal("2"));

        //user makes purchase of taco
        service.purchaseItem("1");

        //quantity on item one should now be at 2
        assertEquals(2, (int) item1.getItemQuantity());
    }

    /**
     * Test of setCurrentMoney method, of class VendingMachineServiceLayer.
     */
    /**
     * Test of giveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGiveChange() throws Exception {
        
    }

    /**
     * Test of cancelGiveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testCancelGiveChange() throws Exception {
        
    }

}
