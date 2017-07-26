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
public class FrontTimesTest {

    FrontTimes ft = new FrontTimes();

    public FrontTimesTest() {
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
     * Test of frontTimes method, of class FrontTimes.
     */
    @Test
    public void choCho() {
        String expectedResult = "ChoCho";
        assertEquals(expectedResult, ft.frontTimes("Chocolate", 2));
    }

    public void choChoCho() {
        String expectedResult = "ChoChoCho";
        assertEquals(expectedResult, ft.frontTimes("Chocolate", 3));
    }

    public void abc() {
        String expectedResult = "AbcAbcAbc";
        assertEquals(expectedResult, ft.frontTimes("Abc", 3));
    }

}
