/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
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
public class ChangeTest {

    Change myChange;

    public ChangeTest() {

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

    @Test
    public void testQuarter() {
        myChange = new Change(115);
        assertEquals(4, myChange.getNumQuarters());
    }

    @Test
    public void testDimes() {
        myChange = new Change(115);
        assertEquals(1, myChange.getNumDimes());
    }

    @Test
    public void testNickels() {
        myChange = new Change(115);
        assertEquals(1, myChange.getNumNickles());
    }

    @Test
    public void testPennies() {
        myChange = new Change(4);
        assertEquals(4, myChange.getNumPennies());
    }

}
