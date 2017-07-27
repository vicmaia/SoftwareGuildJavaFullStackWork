/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.section3unittests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author n0252282
 */
public class MakePiTest {

    MakePi pie = new MakePi();

    public MakePiTest() {
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
     * Test of MakePi method, of class MakePi.
     */
    @Test
    public void testMakePi() {
        int[] pieValue = {3, 1, 4};
        Assert.assertArrayEquals(pieValue, pie.MakePi(3));
    }

    @Test
    public void testMakePi5() {
        int[] pieValue = {3, 1, 4, 1, 5};
        Assert.assertArrayEquals(pieValue, pie.MakePi(5));
    }
}
