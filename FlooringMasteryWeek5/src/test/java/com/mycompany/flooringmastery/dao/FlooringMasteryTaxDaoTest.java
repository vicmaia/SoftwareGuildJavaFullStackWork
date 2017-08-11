/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryTaxDaoTest {

    private FlooringMasteryTaxDao dao;

    public FlooringMasteryTaxDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        dao = ctx.getBean("FlooringMasteryTaxDao", FlooringMasteryTaxDao.class);
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
     * Test of getAllTaxes method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        assertEquals(4, dao.getAllTaxes().size());
    }

    /**
     * Test of getTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testGetTax() throws Exception {
        Tax testTax = new Tax();

        testTax.setState("OH");
        testTax.setTaxRate(new BigDecimal("6.25"));

        assertTrue(testTax.equals(dao.getTax("OH")));
    }

}
