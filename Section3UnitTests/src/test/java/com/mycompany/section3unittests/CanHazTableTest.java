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
public class CanHazTableTest {

    CanHazTable cht = new CanHazTable();

    public CanHazTableTest() {
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
     * Test of canHazTable method, of class CanHazTable.
     */
    @Test
    public void condition1() {
        assertEquals(2, cht.canHazTable(5, 10));
    }

    public void condition2() {
        assertEquals(0, cht.canHazTable(5, 2));
    }

    public void condition3() {
        assertEquals(1, cht.canHazTable(5, 5));
    }

}
