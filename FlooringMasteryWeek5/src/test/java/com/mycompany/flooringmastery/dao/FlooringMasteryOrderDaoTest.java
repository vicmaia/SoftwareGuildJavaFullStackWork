/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryOrderDaoTest {

    private FlooringMasteryOrderDao dao;

    public FlooringMasteryOrderDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        dao = ctx.getBean("FlooringMasteryOrderDao", FlooringMasteryOrderDao.class);
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
     * Test of getAllOrdersByDate method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetAllOrdersByDate() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-01");
        assertEquals(4, dao.getAllOrdersByDate(ld).size());
    }

    /**
     * Test of getOrderByDate method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetOrderByDate() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-01");
        //need to load Map in Map
        dao.getAllOrdersByDate(ld);
        //Check for order 113
        dao.getOrderByDate(113, ld);
    }

    /**
     * Test of createOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testCreateOrder() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-01");
        //need to load Map in Map
        dao.getAllOrdersByDate(ld);

        //create test order
        Order testOrder = new Order();

        testOrder.setOrderNumber(113);
        testOrder.setCustomerName("Kenny");
        testOrder.setOrderDate(ld);

        Tax testTax = new Tax();
        testTax.setState("MI");
        testTax.setTaxRate(new BigDecimal("5.75"));
        testOrder.setTaxRate(testTax);
        
        Product testProduct = new Product();
        testProduct.setProductType("Tile");
        testProduct.setCostPerSquareFoot(new BigDecimal ("3.50"));
        testProduct.setLaborCostPerSquareFoot(new BigDecimal ("4.15"));
        testOrder.setProduct(testProduct);
        
        testOrder.setArea(new BigDecimal ("800"));
        
        //Put returned object into local object for comparison
        Order createOrderReturn = dao.createOrder(ld, testOrder);
        
        //is the return the same as the test order?
        assertTrue(testOrder.equals(createOrderReturn));
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-01");
        //need to load Map in Map
        dao.getAllOrdersByDate(ld);
        //get initial size of inner map for date (should be 4)
        int initialSize = dao.getAllOrdersByDate(ld).size();
        
        //Remove an order
        dao.removeOrder(ld, 113);
        
        //get final size of inner map for date (should be 3)
        int finalSize = dao.getAllOrdersByDate(ld).size();
        
        //intialsize - 1 = finalsize
        assertTrue(initialSize - 1 == finalSize);
    }

    /**
     * Test of saveCurrentOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testSaveCurrentOrder() throws Exception {
    }

}
