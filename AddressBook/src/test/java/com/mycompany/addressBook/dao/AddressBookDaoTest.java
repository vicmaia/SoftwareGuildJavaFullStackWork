/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.dao;

import com.mycompany.addressBook.dto.Address;
import com.mycompany.addressBook.dto.AddressBookException;
import java.util.List;
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
public class AddressBookDaoTest {

    private AddressBookDao dao = new AddressBookDaoFileImpl();

    public AddressBookDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception { // going to run before all test methods for this class
        // Set DAO to a good state... that good state is going to be empty
        List<Address> addressList = dao.getAllAddresses();
        for (Address address : addressList) {
            dao.removeAddress(address.getLastName());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddAddress() throws Exception {
        Address address = new Address("Gardiner");
        address.setFirstName("Ken");
        address.setStreetAddress("364 Beauty Hill Rd");
        address.setCity("Barrington");
        address.setState("NH");
        address.setZip("03825");

        dao.addAddress(address.getLastName(), address);

        Address fromDao = dao.getAddress(address.getLastName());

        assertEquals(address, fromDao);
    }
//    }
//
//    /**
//     * Test of addStudent method, of class AddressBookDao.
//     */
//    @Test
//    public void testAddStudent() throws Exception {
//
//    }

//    /**
//     * Test of getAllDVDs method, of class AddressBookDao.
//     */
    @Test
    public void testGetAllAddresses() throws Exception {
        Address address1 = new Address("Gardiner");
        address1.setFirstName("Ken");
        address1.setStreetAddress("364 Beauty Hill Rd");
        address1.setCity("Barrington");
        address1.setState("NH");
        address1.setZip("03825");

        dao.addAddress(address1.getLastName(), address1);

        Address address2 = new Address("Smith");
        address2.setFirstName("Joe");
        address2.setStreetAddress("1 Lakeshore Dr.");
        address2.setCity("Rochester");
        address2.setState("NH");
        address2.setZip("03826");

        dao.addAddress(address2.getLastName(), address2);

        assertEquals(2, dao.getAllAddresses().size());

    }

    @Test
    public void testRemoveAddress() throws Exception {
        Address address1 = new Address("Gardiner");
        address1.setFirstName("Ken");
        address1.setStreetAddress("364 Beauty Hill Rd");
        address1.setCity("Barrington");
        address1.setState("NH");
        address1.setZip("03825");

        dao.addAddress(address1.getLastName(), address1);

        Address address2 = new Address("Smith");
        address2.setFirstName("Joe");
        address2.setStreetAddress("1 Lakeshore Dr.");
        address2.setCity("Rochester");
        address2.setState("NH");
        address2.setZip("03826");

        dao.addAddress(address2.getLastName(), address2);

        dao.removeAddress(address1.getLastName());
        assertEquals(1, dao.getAllAddresses().size());
        assertNull(dao.getAddress(address1.getLastName()));

        dao.removeAddress(address2.getLastName());
        assertEquals(0, dao.getAllAddresses().size());
        assertNull(dao.getAddress(address2.getLastName()));

    }
}
