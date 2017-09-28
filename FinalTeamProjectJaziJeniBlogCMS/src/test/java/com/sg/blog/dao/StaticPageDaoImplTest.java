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
 * @author n0001123
 */
public class StaticPageDaoImplTest {

    AuthorDao authorDao;
    CategoryDao categoryDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao staticPageDao;
    UserDao userDao;

    public StaticPageDaoImplTest() {
    }

    private StaticPage CreateFirstStaticPage() {
        Author author = CreateFirstAuthor();

        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("About US!");
        staticPage.setContent("Html Text goes here!");
        staticPage.setAuthor(author);

        staticPageDao.addStaticPage(staticPage);
        return staticPage;
    }

    private StaticPage CreateSecondStaticPage() {
        Author author = CreateSecondAuthor();

        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("Another About US!");
        staticPage.setContent("More Html Text goes here!");
        staticPage.setAuthor(author);

        staticPageDao.addStaticPage(staticPage);

        return staticPage;
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
    public void testGetAllStaticPages() {
        StaticPage staticPage1 = CreateFirstStaticPage();
        StaticPage staticPage2 = CreateSecondStaticPage();
        List<StaticPage> fromDao = staticPageDao.getAllStaticPages();
        assertEquals(2, fromDao.size());

        assertTrue(fromDao.contains(staticPage1));
        assertTrue(fromDao.contains(staticPage2));

    }

    @Test
    public void testDeleteStaticPage() {
        StaticPage staticPage1 = CreateFirstStaticPage();
        StaticPage staticPage2 = CreateSecondStaticPage();

        List<StaticPage> fromDao = staticPageDao.getAllStaticPages();
        assertEquals(2, fromDao.size());

        staticPageDao.deleteStaticPage(staticPage1.getStaticID());

        List<StaticPage> fromDao1 = staticPageDao.getAllStaticPages();
        assertEquals(1, fromDao1.size());

        assertFalse(fromDao1.contains(staticPage1));
        assertTrue(fromDao1.contains(staticPage2));
    }

    @Test
    public void testUpdateStaticPage() {
        StaticPage staticPage1 = CreateFirstStaticPage();

        staticPage1.setTitle("Wowsa!");

        staticPageDao.updateStaticPage(staticPage1);

        assertEquals(staticPage1, staticPageDao.getStaticPageById(staticPage1.getStaticID()));
    }

    @Test
    public void testGetStaticPageById() {
        StaticPage staticPage1 = CreateFirstStaticPage();

        List<StaticPage> fromDao = staticPageDao.getAllStaticPages();
        assertEquals(1, fromDao.size());

        assertEquals(staticPage1, staticPageDao.getStaticPageById(staticPage1.getStaticID()));
    }

    @Test
    public void testAddStaticPage() {
        StaticPage staticPage1 = CreateFirstStaticPage();

        List<StaticPage> fromDao = staticPageDao.getAllStaticPages();
        assertEquals(1, fromDao.size());

        assertEquals(staticPage1, staticPageDao.getStaticPageById(staticPage1.getStaticID()));
    }

}
