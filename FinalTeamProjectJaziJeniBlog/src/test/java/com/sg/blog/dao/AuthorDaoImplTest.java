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
 * @author n0252282
 */
public class AuthorDaoImplTest {

    AuthorDao authorDao;
    CategoryDao categoryDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao staticPageDao;
    UserDao userDao;

    public AuthorDaoImplTest() {
    }

    private Author CreateFirstAuthor() {
        Author author = new Author();
        author.setFirstName("Sally");
        author.setLastName("Ride");
        author.setTitle("Astronaut");

        authorDao.addAuthor(author);

        return author;
    }

    private Author CreateSecondAuthor() {
        Author author = new Author();
        author.setFirstName("Little");
        author.setLastName("Debbie");
        author.setTitle("Baker");

        authorDao.addAuthor(author);

        return author;
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
    public void testGetAllAuthors() {
        Author author1 = CreateFirstAuthor();
        Author author2 = CreateSecondAuthor();

        assertEquals(2, authorDao.getAllAuthors().size());

        assertTrue(authorDao.getAllAuthors().contains(author1));
        assertTrue(authorDao.getAllAuthors().contains(author2));
    }

    @Test
    public void testAddAuthor() {
        Author author1 = CreateFirstAuthor();

        assertEquals(1, authorDao.getAllAuthors().size());

        assertEquals(author1, authorDao.getAuthorById(author1.getAuthorID()));
    }

    @Test
    public void testDeleteAuthor() {
        Author author1 = CreateFirstAuthor();
        Author author2 = CreateSecondAuthor();

        assertEquals(2, authorDao.getAllAuthors().size());

        authorDao.deleteAuthor(author1.getAuthorID());

        assertEquals(1, authorDao.getAllAuthors().size());

        assertFalse(authorDao.getAllAuthors().contains(author1));
        assertTrue(authorDao.getAllAuthors().contains(author2));
    }

    /**
     * Test of updateAuthor method, of class AuthorDaoImpl.
     */
    @Test
    public void testUpdateAuthor() {
        Author author1 = CreateFirstAuthor();
        
        author1.setFirstName("Joanie");
        
        authorDao.updateAuthor(author1);
        
        assertEquals(author1, authorDao.getAuthorById(author1.getAuthorID()));
    }

    /**
     * Test of getAuthorById method, of class AuthorDaoImpl.
     */
    @Test
    public void testGetAuthorById() {
        Author author1 = CreateFirstAuthor();

        assertEquals(1, authorDao.getAllAuthors().size());

        assertEquals(author1, authorDao.getAuthorById(author1.getAuthorID()));
        
    }

}
