/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        LocalDate ld = LocalDate.parse("2010-01-01");

        service.removeOrder(ld, 200);

        List<Order> orderList = service.getOrdersByDate(ld);

        assertEquals(1, orderList.size());
    }

    /**
     * Test of saveCurrentWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testSaveCurrentWork() throws Exception {
        try {
            service.saveCurrentWork();
        } catch (FlooringMasteryPersistenceException e) {
            fail("Exception was thrown.");
        }
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productList = service.getAllProducts();

        assertEquals(4, productList.size());

    }

    /**
     * Test of getSingleProduct method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetSingleProduct() throws Exception {

        Product testProduct = service.getSingleProduct("Wood");

        assertTrue(testProduct.getProductType().compareTo("Wood") == 0);
    }

    /**
     * Test of retrieveTaxList method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRetrieveTaxList() throws Exception {
        List<Tax> taxList = service.retrieveTaxList();

        assertEquals(4, taxList.size());

    }

    /**
     * Test of retrieveTax method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRetrieveTax() throws Exception {
        Tax testTax = service.retrieveTax("OH");

        assertTrue(testTax.getState().compareTo("OH") == 0);
    }

    /**
     * Test of retrieveOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRetrieveOrder() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-10");

        Order testOrder = service.retrieveOrder(ld, 300);

        Order testOrder2 = new Order();

        testOrder2.setOrderNumber(300);
        testOrder2.setCustomerName("Mary");
        testOrder2.setOrderDate(ld);

        Tax testTax2 = new Tax();
        testTax2.setState("OH");
        testTax2.setTaxRate(new BigDecimal("6.25"));
        testOrder2.setTaxRate(testTax2);

        Product testProduct2 = new Product();
        testProduct2.setProductType("Carpet");
        testProduct2.setCostPerSquareFoot(new BigDecimal("2.25"));
        testProduct2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testOrder2.setProduct(testProduct2);

        testOrder2.setArea(new BigDecimal("500"));

        assertTrue(testOrder.equals(testOrder2));
    }

    /**
     * Test of validateOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateOrder() throws Exception {
        LocalDate ld = LocalDate.parse("2010-01-10");
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

        try {
            service.validateOrder(testOrder);
        } catch (DataValidationException e) {
            fail("Valid order threw exception");
        }
    }
}
