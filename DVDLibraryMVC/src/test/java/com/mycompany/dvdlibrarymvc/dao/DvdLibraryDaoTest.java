/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrarymvc.dao;

import com.mycompany.dvdlibrarymvc.dao.SearchTerm;
import com.mycompany.dvdlibrarymvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mycompany.dvdlibrarymvc.dao.DvdLibraryDao;

/**
 *
 * @author n0252282
 */
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("contactListDao", DvdLibraryDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteContact() {
        // Create new contact
        Dvd nc = new Dvd();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setCompany("Oracle");
        nc.setEmail("john@doe.com");
        nc.setPhone("1234445678");
        dao.addContact(nc);
        Dvd fromDb = dao.getContactById(nc.getContactId());
        assertEquals(fromDb, nc);
        dao.removeContact(nc.getContactId());
        assertNull(dao.getContactById(nc.getContactId()));
    }

    @Test
    public void addUpdateContact() {
        // Create new contact
        Dvd nc = new Dvd();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");
        dao.addContact(nc);
        nc.setPhone("9999999999");
        dao.updateContact(nc);
        Dvd fromDb = dao.getContactById(nc.getContactId());
        assertEquals(fromDb, nc);
    }

    @Test
    public void getAllContacts() {
        // Create new contact
        Dvd nc = new Dvd();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");
        dao.addContact(nc);
        // Create new contact
        Dvd nc2 = new Dvd();
        nc2.setFirstName("John");
        nc2.setLastName("Jones");
        nc2.setCompany("Apple");
        nc2.setEmail("john@jones.com");
        nc2.setPhone("5556667777");
        dao.addContact(nc2);
        List<Dvd> cList = dao.getAllContacts();
        assertEquals(cList.size(), 2);
    }

    @Test
    public void searchContacts() {
        // Create new contact
        Dvd nc = new Dvd();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");
        dao.addContact(nc);
        // Create new contact
        Dvd nc2 = new Dvd();
        nc2.setFirstName("John");
        nc2.setLastName("Jones");
        nc2.setCompany("Apple");
        nc2.setEmail("john@jones.com");
        nc2.setPhone("5556667777");
        dao.addContact(nc2);
        // Create new contact - same last name as first 
        // contact but different company
        Dvd nc3 = new Dvd();
        nc3.setFirstName("Steve");
        nc3.setLastName("Smith");
        nc3.setCompany("Microsoft");
        nc3.setEmail("steve@msft.com");
        nc3.setPhone("5552221234");
        dao.addContact(nc3);
        // Create search criteria
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.LAST_NAME, "Jones");
        List<Dvd> cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc2, cList.get(0));

        // New search criteria - look for Smith
        criteria.put(SearchTerm.LAST_NAME, "Smith");
        cList = dao.searchContacts(criteria);
        assertEquals(2, cList.size());

        // Add company to search criteria
        criteria.put(SearchTerm.COMPANY, "Sun");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc, cList.get(0));

        // Change company to Microsoft, should get back nc3
        criteria.put(SearchTerm.COMPANY, "Microsoft");
        cList = dao.searchContacts(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc3, cList.get(0));
        // Change company to Apple, should get back nothing
        criteria.put(SearchTerm.COMPANY, "Apple");
        cList = dao.searchContacts(criteria);
        assertEquals(0, cList.size());
    }
}
