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
import java.util.HashSet;
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
public class PostDaoImplTest {

    AuthorDao authorDao;
    CategoryDao categoryDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao staticPageDao;
    UserDao userDao;

    public PostDaoImplTest() {
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

    private Item CreateFirstItem() {
        Item item = new Item();
        item.setItemName("Gold Necklace");
        item.setItemDescription("Supa awesome");
        item.setItemPrice(new BigDecimal("10.99"));
        item.setItemQuantity(5);

        itemDao.addItem(item);

        return item;
    }

    private Item CreateSecondItem() {
        Item item = new Item();
        item.setItemName("Silver Bracelet");
        item.setItemDescription("Wicked!  Wow!");
        item.setItemPrice(new BigDecimal("4.28"));
        item.setItemQuantity(8);

        itemDao.addItem(item);

        return item;
    }

    private Category CreateFirstCategory() {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");

        categoryDao.addCategory(category);

        return category;
    }

    private Category CreateSecondCategory() {
        Category category = new Category();
        category.setName("Junk");
        category.setDescription("Crap");

        categoryDao.addCategory(category);

        return category;
    }

    private Post CreateFirstPost() {
        Post post = new Post();

        Item item = CreateFirstItem();
        Author author = CreateFirstAuthor();
        Category category = CreateFirstCategory();

        post.setAuthor(author);
        post.setCategory(category);
        post.setContent("Awesome post");
        post.setHashtags("test test test");
        post.setItem(item);
        post.setPendingDelete(0);
        post.setPendingEdit(0);
        post.setOldPostID(0);
        post.setTitle("Awesome post");

        postDao.addPost(post);

        return post;
    }

    private Post CreateSecondPost() {
        Post post = new Post();

        Item item = CreateFirstItem();
        Author author = CreateFirstAuthor();
        Category category = CreateFirstCategory();

        post.setAuthor(author);
        post.setCategory(category);
        post.setContent("Nice Post");
        post.setHashtags("sample tags");
        post.setItem(item);
        post.setPendingDelete(1);
        post.setPendingEdit(1);
        post.setOldPostID(0);
        post.setTitle("Less awesome post");

        postDao.addPost(post);

        return post;
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
    public void testGetAllPosts() {
        Post post1 = CreateFirstPost();
        Post post2 = CreateSecondPost();

        assertEquals(2, postDao.getAllPosts().size());

        Post post3 = postDao.getPostById(post1.getPostID());

        assertEquals(post1, post3);
        assertTrue(postDao.getAllPosts().contains(post2));
    }

    @Test
    public void testAddPost() {
        Post post1 = CreateFirstPost();

        assertEquals(1, postDao.getAllPosts().size());

        assertEquals(post1, postDao.getPostById(post1.getPostID()));
    }

    @Test
    public void testDeletePost() {
        Post post1 = CreateFirstPost();
        Post post2 = CreateSecondPost();

        assertEquals(2, postDao.getAllPosts().size());

        postDao.deletePost(post1.getPostID());

        assertEquals(1, postDao.getAllPosts().size());

        assertFalse(postDao.getAllPosts().contains(post1));
        assertTrue(postDao.getAllPosts().contains(post2));
    }

    /**
     * Test of updateAuthor method, of class AuthorDaoImpl.
     */
    @Test
    public void testUpdatePost() {
        Post post1 = CreateFirstPost();

        post1.setTitle("Joanie");

        postDao.updatePost(post1);

        Post fromDao = postDao.getPostById(post1.getPostID());

        assertTrue(post1.getOldPostID() == 0);
        assertTrue(fromDao.getOldPostID() == 0);

        assertEquals(fromDao, post1);
    }

    /**
     * Test of getAuthorById method, of class AuthorDaoImpl.
     */
    @Test
    public void testGetPostById() {
        Post post1 = CreateFirstPost();

        assertEquals(1, postDao.getAllPosts().size());

        assertEquals(post1, postDao.getPostById(post1.getPostID()));
    }

    @Test
    public void getAllApprovedPosts() {
        Post post1 = CreateFirstPost();

        assertEquals(1, postDao.getAllApprovedPosts().size());

        assertTrue(postDao.getAllApprovedPosts().contains(post1));
    }

    @Test
    public void updatePendingEdit() {
        Post post1 = CreateFirstPost();

        post1.setPendingEdit(1);

        postDao.updatePendingEdit(post1);

        assertEquals(post1, postDao.getPostById(post1.getPostID()));

    }

    @Test
    public void getPostByItemId() {
        Post post1 = CreateFirstPost();

        Item postItem = itemDao.getItemById(post1.getItem().getItemID());

        assertTrue(postDao.getPostByItemId(postItem.getItemID()).contains(post1));

    }

    @Test
    public void getPostByCategoryId() {
        Post post1 = CreateFirstPost();

        Category postCategory = categoryDao.getCategoryById(post1.getCategory().getCategoryID());

        assertTrue(postDao.getPostByCategoryId(postCategory.getCategoryID()).contains(post1));

    }

    @Test
    public void getAllPostsMatchingHashTag() {
        Post post1 = CreateFirstPost();

        List<Post> fromDao = postDao.getAllPostsMatchingHashTag("test");

        assertTrue(fromDao.contains(post1));

    }

}
