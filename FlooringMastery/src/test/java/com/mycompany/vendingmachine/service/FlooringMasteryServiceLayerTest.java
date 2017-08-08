/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryServiceLayerTest {

    private VendingMachineServiceLayer service;

    public FlooringMasteryServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws VendingMachinePersistenceException {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(2, service.getAllItems().size());;
    }

    /**
     * Test of getAllItemsFiltered method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItemsFiltered() throws Exception {
        assertEquals(1, service.getAllItemsFiltered().size());;
    }

    /*
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        Item testItem = service.getItem("1");
        assertEquals(testItem, service.getItem("1"));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testPurchaseItemSuccess() throws Exception {
        //if no exceptions are returned, this test will pass, no assert needed
        service.setCurrentMoney(new BigDecimal("3"));
        //user makes purchase "Taco for .50"
        service.purchaseItem("1");

    }

    @Test
    public void testPurchaseItemOutOfStock() throws Exception {

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
        //user adds $.25
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

        //user adds 3 dollars
        service.setCurrentMoney(new BigDecimal("2"));

        //user makes purchase of taco
        service.purchaseItem("1");

        //reload from file to get updated quantity
        Item testItem = service.getItem("1");

        //quantity on item one should now be at 2
        assertEquals(2, (int) testItem.getItemQuantity());
    }

    /**
     * Test of giveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGiveChange() throws Exception {

        //user adds $3.19
        service.setCurrentMoney(new BigDecimal("3.19"));

        //user makes purchase of taco and gets change
        Change userChange = service.purchaseItem("1");

        //make a new Change object, store return
        Change perfectChange = new Change(269);

        assertEquals(perfectChange, userChange);
    }

    /**
     * Test of cancelGiveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testCancelGiveChange() throws Exception {
        //user adds money to machine
        service.setCurrentMoney(new BigDecimal("3.19"));

        //user decides they don't need tacos, request change 
        Change userChange = service.cancelGiveChange();

        //make a new Change object, store return
        Change perfectChange = new Change(319);

        assertEquals(perfectChange, userChange);
    }

}
