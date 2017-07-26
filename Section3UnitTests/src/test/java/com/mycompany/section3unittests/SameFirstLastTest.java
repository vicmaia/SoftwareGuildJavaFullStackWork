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
public class SameFirstLastTest {
    SameFirstLast sfl = new SameFirstLast();
    
    public SameFirstLastTest() {
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
     * Test of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void first() {
        int[] numbers = {1, 2, 3};
        assertEquals(false, sfl.sameFirstLast(numbers));
    }

    @Test
    public void second() {
        int[] numbers = {1, 2, 3, 1};
        assertEquals(true, sfl.sameFirstLast(numbers));
    }

    @Test
    public void third() {
        int[] numbers = {1, 2, 1};
        assertEquals(true, sfl.sameFirstLast(numbers));
    }
}
