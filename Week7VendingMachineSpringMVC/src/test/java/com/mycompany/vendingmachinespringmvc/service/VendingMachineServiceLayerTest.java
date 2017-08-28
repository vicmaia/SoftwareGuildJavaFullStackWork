/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.service;

import com.mycompany.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(9, service.getAllItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        Item testItem = service.getItem(1);
        assertEquals(testItem, service.getItem(1));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testPurchaseItem() throws Exception {
        //if no exceptions are returned, this test will pass, no assert needed
        service.setCurrentMoney(new BigDecimal("3"));

        service.purchaseItem(1);
    }

    @Test
    public void testPurchaseItemOutOfStock() throws Exception {

        //user adds 3 dollars
        service.setCurrentMoney(new BigDecimal("3"));

        //user makes purchase of Chex Mix, which is out of stock
        try {
            service.purchaseItem(9);
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
        //user attempts to purchase item 1, insufficient funds.
        try {
            service.purchaseItem(1);
            fail("Expected InsufficientFundsException was not thrown.");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    @Test
    public void testMakeSaleReduceInventory() throws Exception {
        //user adds 2 dollars
        service.setCurrentMoney(new BigDecimal("2"));

        //user makes purchase of item 1
        service.purchaseItem(1);

        //reload to get updated quantity
        Item testItem = service.getItem(1);

        //quantity on item one should now be at 2
        assertEquals(7, (int) testItem.getQuantity());

    }

    @Test
    public void testSetAndGetCurrentMoney() {
        service.setCurrentMoney(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, service.getCurrentMoney());

    }

    @Test
    public void testGiveChange() throws Exception {
        //user adds $3.19
        service.setCurrentMoney(new BigDecimal("3.19"));

        //user makes purchase of taco and gets change
        Change userChange = service.purchaseItem(2);

        //make a new Change object, store return
        Change perfectChange = new Change(219);

        assertEquals(perfectChange, userChange);
    }

    @Test
    public void testCancelGiveChange() throws Exception {
        //user adds money to machine
        service.setCurrentMoney(new BigDecimal("3.19"));

        //user decides they don't want to purcahse, request change 
        Change userChange = service.cancelGiveChange();

        //make a new Change object, store return
        Change perfectChange = new Change(319);

        assertEquals(perfectChange, userChange);
    }

    @Test
    public void testSetAndGetSelection() {
        service.setSelection(1);
        assertEquals(1, (int) service.getSelection());
    }

    @Test
    public void testSetAndGetMessage() {
        service.setMessage("successful test");
        assertTrue(service.getMessage().equals("successful test"));
    }

    /**
     * Test of getChangeMessage method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetChangeMessage() throws Exception {
        //user adds $1.00
        service.setCurrentMoney(new BigDecimal("1.00"));

        //user decides they don't want to purcahse, request change 
        Change userChange = service.cancelGiveChange();

        String changeMessage = ("Quarters:" + 4
                + " Dimes:" + 0
                + "\nNickels:" + 0
                + " Pennies:" + 0);

        assertTrue(service.getChangeMessage().equals(changeMessage));

    }

    @Test
    public void convertMoneyButtonInput() throws Exception {
        //add a quarter
        service.convertMoneyButtonInput("Add Quarter");

        //do we have .25?
        assertEquals(new BigDecimal(".25"), service.getCurrentMoney());
    }
}
