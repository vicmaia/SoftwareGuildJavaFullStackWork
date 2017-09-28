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
public class CategoryDaoImplTest {

    AuthorDao authorDao;
    CategoryDao categoryDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao staticPageDao;
    UserDao userDao;

    public CategoryDaoImplTest() {
    }

    private Category CreateFirstCategory() {
        Category category = new Category();
        category.setName("Necklace");
        category.setDescription("2C Diamond with 18K gold chain");

        categoryDao.addCategory(category);

        return category;
    }

    private Category CreateSecondCategory() {
        Category category = new Category();
        category.setName("Ring");
        category.setDescription("5C Diamond wrap");

        categoryDao.addCategory(category);

        return category;
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
    public void testGetAllCategories() {
        Category category1 = CreateFirstCategory();
        Category category2 = CreateSecondCategory();
        List<Category> fromDao = categoryDao.getAllCategories();
        assertEquals(2, fromDao.size());

        assertTrue(fromDao.contains(category1));
        assertTrue(fromDao.contains(category2));
    }

    @Test
    public void testAddCategory() {
        Category category1 = CreateFirstCategory();
        List<Category> fromDao = categoryDao.getAllCategories();

        assertEquals(1, fromDao.size());

        assertTrue(fromDao.contains(category1));

    }

    @Test
    public void testDeleteCategory() {
        Category category1 = CreateFirstCategory();
        List<Category> fromDao = categoryDao.getAllCategories();

        assertEquals(1, fromDao.size());

        categoryDao.deleteCategory(category1.getCategoryID());
        fromDao = categoryDao.getAllCategories();

        assertEquals(0, fromDao.size());

        assertFalse(fromDao.contains(category1.getName()));
        assertFalse(fromDao.contains(category1.getDescription()));
    }

    @Test
    public void testUpdateCategory() {
        Category category1 = CreateFirstCategory();
        List<Category> fromDao = categoryDao.getAllCategories();

        category1.setName("Earrings");
        category1.setDescription("Diamonds");

        categoryDao.updateCategory(category1);
        Category fromDao1 = categoryDao.getCategoryById(category1.getCategoryID());

        assertEquals(category1, fromDao1);

        assertTrue(fromDao1.getName().contains("Earrings"));
        assertTrue(fromDao1.getDescription().contains("Diamonds"));
    }

    @Test
    public void testGetCategoryById() {
        Category category1 = CreateFirstCategory();

        assertEquals(1, categoryDao.getAllCategories().size());

        assertEquals(category1, categoryDao.getCategoryById(category1.getCategoryID()));
    }

    @Test
    public void getAllCategoriesByName() {
        Category category1 = CreateFirstCategory();

        List<Category> fromDao = categoryDao.getAllCategoriesByName("Necklace");

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(category1));

    }

}
