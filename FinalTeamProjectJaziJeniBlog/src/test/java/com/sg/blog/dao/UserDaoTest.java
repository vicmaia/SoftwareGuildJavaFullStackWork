/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0001123
 */
public class UserDaoTest {

    UserDao userDao;

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        userDao = ctx.getBean("userDao", UserDao.class);

        //delete all users
        List<User> users = userDao.getAllUsers();
        for (User currentUser : users) {
            userDao.deleteUser(currentUser.getUsername());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddUser() {
        User newUser = new User();
        newUser.setPassword("test");
        newUser.setUsername("test");
        newUser.setId(10);

        userDao.addUser(newUser);

        List<User> returnedFromDao = userDao.getAllUsers();

        assertEquals(newUser, returnedFromDao.get(0));

    }

    @Test
    public void testDeleteUser() {
        User newUser = new User();
        newUser.setPassword("test");
        newUser.setUsername("test");
        newUser.setId(10);

        userDao.addUser(newUser);

        List<User> returnedFromDao = userDao.getAllUsers();

        assertEquals(1, returnedFromDao.size());

        userDao.deleteUser("test");

        returnedFromDao = userDao.getAllUsers();

        assertEquals(0, returnedFromDao.size());
    }

    @Test
    public void testGetAllUsers() {
        User newUser = new User();
        newUser.setPassword("test");
        newUser.setUsername("test");
        newUser.setId(10);

        userDao.addUser(newUser);

        List<User> returnedFromDao = userDao.getAllUsers();

        assertEquals(1, returnedFromDao.size());
    }

}
