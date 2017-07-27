/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.section3unittests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author n0252282
 */
public class CountXXTest {

    CountXX cntXX = new CountXX();

    public CountXXTest() {
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
     * Test of countXX method, of class CountXX.
     */
    @Test
    public void testCountXX1() {
        String test1 = "abcxx";
        assertEquals(1, cntXX.countXX(test1));
    }
    @Test
    public void testCountXX2() {
        String test2 = "xxx";
        assertEquals(2, cntXX.countXX(test2));
    }
    @Test
    public void testCountXX3() {
        String test3 = "xxxx";
        assertEquals(3, cntXX.countXX(test3));
    }

}
