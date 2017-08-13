/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
        LocalDate ld = LocalDate.parse("2010-01-01");
        List<Order> orderList = new ArrayList();
        orderList = service.getOrdersByDate(ld);

        assertEquals(2, orderList.size());

    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-01");
        Order testOrder = new Order();

        //Create testorder
        testOrder.setOrderNumber(113);
        testOrder.setCustomerName("Kenny");

        Tax testTax = new Tax();
        testTax.setState("OH");
        testOrder.setTaxRate(testTax);

        Product testProduct = new Product();
        testProduct.setProductType("Tile");
        testOrder.setProduct(testProduct);

        testOrder.setArea(new BigDecimal("800"));

        Order newOrder = service.createOrder(ld, testOrder);

        List<Order> orderList = service.getOrdersByDate(ld);

        assertEquals(3, orderList.size());
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-01");
        Order testOrder = new Order();
        Order editedOrder = new Order();

        testOrder = service.retrieveOrder(ld, 200);

        //Create edited order
        editedOrder.setOrderNumber(200);
        editedOrder.setCustomerName("Linden");
        editedOrder.setOrderDate(ld);

        Tax testTaxEdit = new Tax();
        testTaxEdit.setState("OH");
        editedOrder.setTaxRate(testTaxEdit);

        Product testProductEdit = new Product();
        testProductEdit.setProductType("Tile");
        editedOrder.setProduct(testProductEdit);

        editedOrder.setArea(new BigDecimal("800"));

        service.editOrder(testOrder, editedOrder);

        List<Order> orderList = service.getOrdersByDate(ld);

        assertEquals(2, orderList.size());

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
