/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.service;

import com.sg.blog.model.Author;
import com.sg.blog.model.Category;
import com.sg.blog.model.Item;
import com.sg.blog.model.Post;
import com.sg.blog.model.StaticPage;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class BlogServiceLayerTest {

    BlogServiceLayer service;

    public BlogServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("serviceLayer", BlogServiceLayer.class);
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
    public void testGetAllPosts() throws Exception {
        service.getAllPosts();
        assertEquals(1, service.getAllPosts().size());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        service.addCategory(new Category());
        assertEquals(1, service.getAllCategories().size());
    }

    @Test
    public void testGetAllStaticPages() throws Exception {
        service.addStaticPage(new StaticPage());
        assertEquals(3, service.getAllStaticPages().size());
    }

    @Test
    public void testGetAllItems() throws Exception {
        service.addItem(new Item());
        assertEquals(2, service.getAllItems().size());
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        service.addAuthor(new Author());
        assertEquals(2, service.getAllAuthors().size());
    }

    @Test
    public void testGetPostById() throws Exception {
        Post post = service.getPostById(1);
        assertEquals(1, post.getPostID());
    }

    @Test
    public void testGetPostByIdNull() throws Exception {
        try {
            Post post = service.getPostById(100);
        } catch (BlogServiceLayerDataValidationException e) {
            assertEquals("No record returned, please try again", e.getMessage());
        }
    }

    @Test
    public void testGetStaticPageById() throws Exception {
        StaticPage sp = service.getStaticPageById(1);

        assertEquals(1, sp.getStaticID());
    }

    @Test
    public void testGetStaticPageByIdNull() throws Exception {
        try {
            StaticPage sp = service.getStaticPageById(100);
        } catch (BlogServiceLayerDataValidationException e) {
            assertEquals("No record returned, please try again", e.getMessage());
        }
    }

    @Test
    public void testAddItem() throws Exception {
        Item item = new Item();
        item.setItemName("Necklace");
        item.setItemDescription("fau Diamond Necklace");
        item.setItemPrice(new BigDecimal("29.99"));
        item.setItemQuantity(10);
        service.addItem(item);

        assertEquals(item, service.getItemById(item.getItemID()));
    }

    @Test
    public void testDeleteItem() throws Exception {
        Item item = new Item();
        item.setItemName("Necklace");
        item.setItemDescription("fau Diamond Necklace");
        item.setItemPrice(new BigDecimal("29.99"));
        item.setItemQuantity(10);
        service.addItem(item);

        assertEquals(item, service.getItemById(item.getItemID()));
        assertEquals(2, service.getAllItems().size());

        service.deletePost(1);
        service.deleteItem(item.getItemID());

        assertEquals(1, service.getAllItems().size());
    }

    @Test
    public void testDeleteItemFKConstraint() throws Exception {
        Item item = new Item();
        item.setItemName("Necklace");
        item.setItemDescription("fau Diamond Necklace");
        item.setItemPrice(new BigDecimal("29.99"));
        item.setItemQuantity(10);
        service.addItem(item);

        assertEquals(item, service.getItemById(item.getItemID()));
        assertEquals(2, service.getAllItems().size());

        try {
            service.deleteItem(item.getItemID());
        } catch (BlogServiceLayerForeignKeyConstraintException e) {
            assertEquals("Please update or remove all posts for this item before deleting.", e.getMessage());
        }
    }

    @Test
    public void testGetItemById() throws Exception {
        Item item1 = new Item();
        item1.setItemName("Necklace");
        item1.setItemDescription("fau Diamond Necklace");
        item1.setItemPrice(new BigDecimal("29.99"));
        item1.setItemQuantity(10);
        service.addItem(item1);

        assertEquals(2, service.getAllItems().size());

        assertEquals(item1, service.getItemById(item1.getItemID()));
    }

    @Test
    public void testGetItemByIdNull() throws Exception {
        try {
            Item iten = service.getItemById(100);
        } catch (BlogServiceLayerDataValidationException e) {
            assertEquals("No record returned, please try again", e.getMessage());
        }
    }

    @Test
    public void testUpdateItem() throws Exception {
        Item item = new Item();
        item.setItemName("Necklace");
        item.setItemDescription("fau Diamond Necklace");
        item.setItemPrice(new BigDecimal("29.99"));
        item.setItemQuantity(10);
        service.addItem(item);

        item.setItemName("JAZZY Jammies");
        item.setItemDescription("PJs for kids of all ages");

        service.updateItem(item);
        Item fromDao1 = service.getItemById(item.getItemID());

        assertEquals(item, fromDao1);

        assertTrue(fromDao1.getItemName().contains("JAZZY Jammies"));
        assertTrue(fromDao1.getItemDescription().contains("PJs for kids of all ages"));
    }

    @Test
    public void testSetMessage() {
        service.setMessage("test");
        assertEquals("test", service.getMessage());
    }

    @Test
    public void testGetMessage() {
        service.setMessage("test");
        assertEquals("test", service.getMessage());
    }

    @Test
    public void testAddPost() throws Exception {
        Post post = new Post();
        post.setPostID(5);
        post.setTitle("Jazyy Jeni");
        post.setContent("Sale");
        post.setHashtags("#silver");
        Item item = new Item();
        item.setItemName("Silver Bracelet");
        item.setItemDescription("Wicked!  Wow!");
        item.setItemPrice(new BigDecimal("4.28"));
        item.setItemQuantity(8);
        post.setItem(item);
        Author author = new Author();
        author.setFirstName("Sally");
        author.setLastName("Ride");
        author.setTitle("Astronaut");
        post.setAuthor(author);
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        post.setCategory(category);
        post.setPendingDelete(0);
        post.setPendingEdit(1);
        post.setOldPostID(0);

        service.addPost(post);

        assertEquals(post, service.getPostById(post.getPostID()));
    }

    @Test
    public void testUpdatePost() throws Exception {
        Post oldPost = service.getPostById(1);
        oldPost.setTitle("updated title");

        service.updatePost(oldPost);

        Post newPost = service.getPostById(1);

        assertTrue(newPost.getTitle().equals("updated title"));
    }

    @Test
    public void testDeletePost() throws Exception {
        assertEquals(1, service.getAllPosts().size());

        service.deletePost(1);

        assertEquals(0, service.getAllPosts().size());
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        category.setCategoryID(5);
        try {
            service.addCategory(category);
            assertEquals(1, service.getAllCategories().size());
            assertTrue(service.getAllCategories().contains(category));
        } catch (BlogServiceLayerDuplicateException e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetCategoryByIdNull() {

        try {
            service.getCategoryById(100);
        } catch (BlogServiceLayerDataValidationException e) {
            assertEquals("No record returned, please try again", e.getMessage());
        }
    }

    @Test
    public void testAddCategory() throws Exception {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        category.setCategoryID(5);
        service.addCategory(category);

        assertEquals(category, service.getCategoryById(category.getCategoryID()));
    }

    @Test
    public void testAddCategoryDuplicate() throws Exception {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        category.setCategoryID(5);
        service.addCategory(category);

        try {
            service.addCategory(category);
        } catch (BlogServiceLayerDuplicateException e) {
            assertEquals("Duplicate Category name.", e.getMessage());
        }
    }

    @Test
    public void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        category.setCategoryID(5);
        service.addCategory(category);

        Category category2 = new Category();
        category2.setName("Earrings");
        category2.setDescription("Diamonds");
        category2.setCategoryID(5);

        service.updateCategory(category2);
        Category fromService = service.getCategoryById(category2.getCategoryID());

        assertEquals(category2, fromService);

        assertTrue(fromService.getName().contains("Earrings"));
        assertTrue(fromService.getDescription().contains("Diamonds"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        category.setCategoryID(5);
        service.addCategory(category);

        assertEquals(1, service.getAllCategories().size());

        service.deleteCategory(category.getCategoryID());

        assertEquals(0, service.getAllCategories().size());
    }

    @Test
    public void testDeleteCategoryFKContstraint() throws Exception {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        category.setCategoryID(5);
        service.addCategory(category);

        Post post = new Post();
        post.setCategory(category);
        service.addPost(post);

        try {
            service.deleteCategory(category.getCategoryID());
        } catch (BlogServiceLayerForeignKeyConstraintException e) {
            assertEquals("Please update or remove all posts for this category before deleting.", e.getMessage());
        }
    }

    @Test
    public void testAddStaticPage() throws Exception {
        service.addStaticPage(new StaticPage());
        assertEquals(3, service.getAllStaticPages().size());
    }

    @Test
    public void testAddAuthor() throws Exception {
        Author author = new Author();
        author.setAuthorID(99);
        author.setFirstName("Sally");
        author.setLastName("Ride");
        author.setTitle("Astronaut");
        service.addAuthor(author);

        assertEquals(author, service.getAuthorById(author.getAuthorID()));
    }

    @Test
    public void testUpdateStaticPage() throws Exception{
        Author author = new Author();
        author.setFirstName("Sally");
        author.setLastName("Ride");
        author.setTitle("Astronaut");
        service.addAuthor(author);

        StaticPage staticPage = new StaticPage();
        staticPage.setStaticID(80);
        staticPage.setTitle("About US!");
        staticPage.setContent("Html Text goes here!");
        staticPage.setAuthor(author);

        service.addStaticPage(staticPage);

        staticPage.setTitle("Wowsa!");

        service.updateStaticPage(staticPage);

        assertEquals(staticPage, service.getStaticPageById(staticPage.getStaticID()));
    }
    
    
}
