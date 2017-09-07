/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.dao;

import com.mycompany.vendingmachinespringmvc.service.NoItemInventoryException;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static junit.framework.Assert.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class VendingDaoTest {

    private VendingDao dao;
//
//    public VendingDaoTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        dao = ctx.getBean("vendingDao", VendingDao.class);
//    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItemsFiltered method, of class VendingMachineDao.
     */
//    @Test
    public void testGetAllItems() throws Exception {

        assertEquals(9, dao.getAllItems().size());;

    }

//    @Test
    public void testGetItem() throws Exception {

        assertEquals("Goldfish", dao.getItem(1).getItemName());;

    }

    /**
     * Test of makeSaleReduceInventory method, of class VendingMachineDao.
     */
//    @Test
    public void testMakeSaleReduceInventorySuccess() throws Exception {
        dao.makeSaleReduceInventory(1);
        int quantityRemaining = dao.getItem(1).getQuantity();
        assertEquals(7, quantityRemaining);
    }

//    @Test
    public void testMakeSaleReduceInventoryFailure() throws Exception {
        try {
            dao.makeSaleReduceInventory(9);
            dao.makeSaleReduceInventory(9);
            fail();
        } catch (NoItemInventoryException e) {
            System.out.println(e.getMessage());
        }

    }
}
