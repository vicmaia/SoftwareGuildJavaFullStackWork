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
public class MakeTagsTest {

    MakeTags tags = new MakeTags();

    public MakeTagsTest() {
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
    public void testMakeTags1() {
        String test1 = "<i>Yay</i>";
        assertEquals(test1, tags.makeTags("i", "Yay"));
    }

    @Test
    public void testMakeTags2() {
        String test2 = "<i>Hello</i>";
        assertEquals(test2, tags.makeTags("i", "Hello"));
    }

    @Test
    public void testMakeTags3() {
        String test3 = "<cite>Yay</cite>";
        assertEquals(test3, tags.makeTags("cite", "Yay"));
    }

}
