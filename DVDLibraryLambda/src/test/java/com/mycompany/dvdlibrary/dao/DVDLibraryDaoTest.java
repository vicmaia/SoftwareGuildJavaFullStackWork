/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVD;
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
public class DVDLibraryDaoTest {

    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();

    public DVDLibraryDaoTest() {
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
        List<DVD> DVDList = dao.getAllDVDs();
        for (DVD dvd : DVDList) {
            dao.removeDVD(dvd.getTitle());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddDVD() throws Exception {
        DVD dvd = new DVD("Test");
        dvd.setReleaseDate("1992");
        dvd.setRating("R");
        dvd.setDirector("Coppola");
        dvd.setStudio("Sony");
        dvd.setComment("Fake DVD");

        dao.addDVD(dvd.getTitle(), dvd);

        DVD fromDao = dao.getDVD(dvd.getTitle());

        assertEquals(dvd, fromDao);
    }
//    }
//
//    /**
//     * Test of addStudent method, of class DVDLibraryDao.
//     */
//    @Test
//    public void testAddStudent() throws Exception {
//
//    }

//    /**
//     * Test of getAllDVDs method, of class DVDLibraryDao.
//     */
    @Test
    public void testGetAllDVDs() throws Exception {
        DVD dvd1 = new DVD("Test");
        dvd1.setReleaseDate("1992");
        dvd1.setRating("R");
        dvd1.setDirector("Coppola");
        dvd1.setStudio("Sony");
        dvd1.setComment("Fake DVD");

        dao.addDVD(dvd1.getTitle(), dvd1);

        DVD dvd2 = new DVD("Test2");
        dvd2.setReleaseDate("1994");
        dvd2.setRating("G");
        dvd2.setDirector("Dude");
        dvd2.setStudio("Disney");
        dvd2.setComment("Fake DVD 2");

        dao.addDVD(dvd2.getTitle(), dvd2);

        assertEquals(2, dao.getAllDVDs().size());

    }

    @Test
    public void testRemoveDVD() throws Exception {
        DVD dvd1 = new DVD("Test");
        dvd1.setReleaseDate("1992");
        dvd1.setRating("R");
        dvd1.setDirector("Coppola");
        dvd1.setStudio("Sony");
        dvd1.setComment("Fake DVD");

        dao.addDVD(dvd1.getTitle(), dvd1);

        DVD dvd2 = new DVD("Test2");
        dvd2.setReleaseDate("1994");
        dvd2.setRating("G");
        dvd2.setDirector("Dude");
        dvd2.setStudio("Disney");
        dvd2.setComment("Fake DVD 2");

        dao.addDVD(dvd2.getTitle(), dvd2);

        dao.removeDVD(dvd1.getTitle());
        assertEquals(1, dao.getAllDVDs().size());
        assertNull(dao.getDVD(dvd1.getTitle()));

        dao.removeDVD(dvd2.getTitle());
        assertEquals(0, dao.getAllDVDs().size());
        assertNull(dao.getDVD(dvd2.getTitle()));

    }
}
