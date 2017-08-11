/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
import java.math.BigDecimal;
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
public class FlooringMasteryProductDaoTest {
    
    private FlooringMasteryProductDao dao;
    
    public FlooringMasteryProductDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        dao = ctx.getBean("FlooringMasteryProductDao", FlooringMasteryProductDao.class);
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
     * Test of getAllProducts method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        assertEquals(4, dao.getAllProducts().size());
    }

    /**
     * Test of getProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product testProduct = new Product();
        
        testProduct.setProductType("Wood");
        testProduct.setCostPerSquareFoot(new BigDecimal("5.15"));
        testProduct.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        
        assertTrue(testProduct.equals(dao.getProduct("Wood")));
    }
    
}
