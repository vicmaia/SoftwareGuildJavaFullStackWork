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
public class PlayOutsideTest {

    PlayOutside po = new PlayOutside();

    public PlayOutsideTest() {
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
     * Test of playOutside method, of class PlayOutside.
     */
    // playOutside(70, false) → true
    // playOutside(95, false) → false
    // playOutside(95, true) → true
    @Test
    public void testPlayOutside1() {
        assertTrue(po.playOutside(70, false));
    }

    @Test
    public void testPlayOutside2() {
        assertFalse(po.playOutside(95, false));
    }

    @Test
    public void testPlayOutside3() {
        assertTrue(po.playOutside(95, true));
    }
}
