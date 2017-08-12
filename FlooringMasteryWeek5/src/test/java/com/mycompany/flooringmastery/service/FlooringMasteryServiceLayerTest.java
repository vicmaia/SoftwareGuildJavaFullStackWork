/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import org.junit.After;
import org.junit.AfterClass;
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

    private FlooringMasteryServiceLayer service;

    public FlooringMasteryServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        service = ctx.getBean("ServiceLayerTest", FlooringMasteryServiceLayer.class);
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
     * Test of getOrdersByDate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

    /**
     * Test of saveCurrentWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testSaveCurrentWork() throws Exception {
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
    }

    /**
     * Test of getSingleProduct method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetSingleProduct() throws Exception {
    }

    /**
     * Test of retrieveTaxList method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRetrieveTaxList() throws Exception {
    }

    /**
     * Test of retrieveTax method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRetrieveTax() throws Exception {
    }

    /**
     * Test of retrieveOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRetrieveOrder() throws Exception {
    }

    /**
     * Test of validateOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateOrder() throws Exception {
    }

}
