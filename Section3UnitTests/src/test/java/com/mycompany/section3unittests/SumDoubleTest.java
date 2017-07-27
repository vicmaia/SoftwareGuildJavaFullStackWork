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
public class SumDoubleTest {

    SumDouble sd = new SumDouble();

    public SumDoubleTest() {
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
     * Test of sumDouble method, of class SumDouble.
     */
    @Test
    public void testSumDouble1() {
        assertEquals(3, sd.sumDouble(1, 2));
    }

    @Test
    public void testSumDouble2() {
        assertEquals(5, sd.sumDouble(3, 2));
    }

    @Test
    public void testSumDouble3() {
        assertEquals(8, sd.sumDouble(2, 2));
    }
}
