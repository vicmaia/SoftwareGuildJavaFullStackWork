/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Author;
import com.sg.blog.model.Category;
import com.sg.blog.model.Item;
import com.sg.blog.model.Post;
import com.sg.blog.model.StaticPage;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0001123
 */
public class ItemDaoImplTest {

    AuthorDao authorDao;
    CategoryDao categoryDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao staticPageDao;
    UserDao userDao;

    public ItemDaoImplTest() {
    }

    private Item CreateFirstItem() {
        Item item = new Item();
        item.setItemName("Jazi Junk");
        item.setItemDescription("Used Stuff");
        item.setItemPrice(new BigDecimal("5.99"));
        item.setItemQuantity(10);

        itemDao.addItem(item);

        return item;
    }

    private Item CreateSecondItem() {
        Item item = new Item();
        item.setItemName("Sparkle");
        item.setItemDescription("Necklace made of tin");
        item.setItemPrice(new BigDecimal("14.99"));
        item.setItemQuantity(5);

        itemDao.addItem(item);

        return item;
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
        authorDao = ctx.getBean("authorDao", AuthorDao.class);
        categoryDao = ctx.getBean("categoryDao", CategoryDao.class);
        itemDao = ctx.getBean("itemDao", ItemDao.class);
        postDao = ctx.getBean("postDao", PostDao.class);
        staticPageDao = ctx.getBean("staticPageDao", StaticPageDao.class);
        userDao = ctx.getBean("userDao", UserDao.class);

        //delete all posts
        List<Post> posts = postDao.getAllPosts();
        for (Post currentPost : posts) {
            postDao.deletePost(currentPost.getPostID());
        }

        // delete all staticPages    
        List<StaticPage> pages = staticPageDao.getAllStaticPages();
        for (StaticPage currentPage : pages) {
            staticPageDao.deleteStaticPage(currentPage.getStaticID());
        }

        //delete all authors
        List<Author> authors = authorDao.getAllAuthors();
        for (Author currentAuthor : authors) {
            authorDao.deleteAuthor(currentAuthor.getAuthorID());
        }

        //delete all Items
        List<Item> items = itemDao.getAllItems();
        for (Item currentItem : items) {
            itemDao.deleteItem(currentItem.getItemID());
        }

        //delete all Categories
        List<Category> categories = categoryDao.getAllCategories();
        for (Category currentCategory : categories) {
            categoryDao.deleteCategory(currentCategory.getCategoryID());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllItems() {
        Item item1 = CreateFirstItem();
        Item item2 = CreateSecondItem();
        List<Item> fromDao = itemDao.getAllItems();
        assertEquals(2, fromDao.size());

        assertTrue(fromDao.contains(item1));
        assertTrue(fromDao.contains(item2));
    }

    @Test
    public void testAddItem() {
        Item item = CreateFirstItem();
        List<Item> fromDao = itemDao.getAllItems();

        assertEquals(1, fromDao.size());

        assertTrue(fromDao.contains(item));
    }

    @Test
    public void testDeleteItem() {

        Item item1 = CreateFirstItem();
        List<Item> fromDao = itemDao.getAllItems();

        assertEquals(1, fromDao.size());

        itemDao.deleteItem(item1.getItemID());
        fromDao = itemDao.getAllItems();

        assertEquals(0, fromDao.size());

        assertFalse(fromDao.contains(item1.getItemName()));
        assertFalse(fromDao.contains(item1.getItemDescription()));
    }

    @Test
    public void testUpdateItem() {
        Item item1 = CreateFirstItem();
        List<Item> fromDao = itemDao.getAllItems();

        item1.setItemName("JAZZY Jammies");
        item1.setItemDescription("PJs for kids of all ages");

        itemDao.updateItem(item1);
        Item fromDao1 = itemDao.getItemById(item1.getItemID());

        assertEquals(item1, fromDao1);

        assertTrue(fromDao1.getItemName().contains("JAZZY Jammies"));
        assertTrue(fromDao1.getItemDescription().contains("PJs for kids of all ages"));
    }

    @Test
    public void testGetItemById() {
        Item item1 = CreateFirstItem();

        assertEquals(1, itemDao.getAllItems().size());

        assertEquals(item1, itemDao.getItemById(item1.getItemID()));
    }

}
