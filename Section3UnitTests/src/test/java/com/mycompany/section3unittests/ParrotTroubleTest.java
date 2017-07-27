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
public class ParrotTroubleTest {

    ParrotTrouble pt = new ParrotTrouble();

    public ParrotTroubleTest() {
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
     * Test of parrotTrouble method, of class ParrotTrouble.
     */
    @Test
    public void testParrotTrouble1() {
        assertTrue(pt.parrotTrouble(true, 6));
    }

    @Test
    public void testParrotTrouble2() {
        assertFalse(pt.parrotTrouble(true, 7));
    }

    @Test
    public void testParrotTrouble3() {
        assertFalse(pt.parrotTrouble(false, 6));
    }

}
