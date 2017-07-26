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
public class FirstLast6Test {

    FirstLast6 fl = new FirstLast6();

    public FirstLast6Test() {
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
     * Test of firstLast6 method, of class FirstLast6.
     */
    @Test
    public void first() {
        int[] numbers = {1, 2, 6};
        assertEquals(true, fl.firstLast6(numbers));
    }

    @Test
    public void second() {
        int[] numbers = {6, 1, 2, 3};
        assertEquals(true, fl.firstLast6(numbers));
    }

    @Test
    public void third() {
        int[] numbers = {13, 6, 1, 2, 3};
        assertEquals(false, fl.firstLast6(numbers));
    }
}
