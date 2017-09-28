/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.service;

import com.sg.blog.dao.AuthorDao;
import com.sg.blog.dao.CategoryDao;
import com.sg.blog.dao.ItemDao;
import com.sg.blog.dao.PostDao;
import com.sg.blog.dao.StaticPageDao;
import com.sg.blog.dao.UserDao;
import com.sg.blog.model.Author;
import com.sg.blog.model.Category;
import com.sg.blog.model.Item;
import com.sg.blog.model.Post;
import com.sg.blog.model.StaticPage;
import com.sg.blog.model.User;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author n0001123
 */
public class BlogServiceLayerImpl implements BlogServiceLayer {

    private String message = "Jazi Jeni's Jewelry and Junk";

    AuthorDao authorDao;
    CategoryDao catDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao statDao;
    UserDao userDao;

    @Inject
    public BlogServiceLayerImpl(AuthorDao authorDao, CategoryDao catDao, ItemDao itemDao, PostDao postDao, StaticPageDao statDao, UserDao userDao) {
        this.authorDao = authorDao;
        this.catDao = catDao;
        this.itemDao = itemDao;
        this.postDao = postDao;
        this.statDao = statDao;
        this.userDao = userDao;
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public List<Category> getAllCategories() {
        return catDao.getAllCategories();
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return statDao.getAllStaticPages();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public Post getPostById(int postId) throws BlogServiceLayerDataValidationException {
        if (postDao.getPostById(postId) != null) {
            return postDao.getPostById(postId);
        } else {
            throw new BlogServiceLayerDataValidationException("No record returned, please try again");
        }
    }

    @Override
    public StaticPage getStaticPageById(int staticPageID) throws BlogServiceLayerDataValidationException {
        if (statDao.getStaticPageById(staticPageID) != null) {
            return statDao.getStaticPageById(staticPageID);
        } else {
            throw new BlogServiceLayerDataValidationException("No record returned, please try again");
        }
    }

    @Override
    public void addItem(Item item
    ) {
        itemDao.addItem(item);
    }

    @Override
    public Post addPost(Post post
    ) {
        return postDao.addPost(post);
    }

    @Override
    public void updatePost(Post post
    ) {
        postDao.updatePost(post);
    }

    @Override
    public void deletePost(int postID
    ) {
        postDao.deletePost(postID);
    }

    @Override
    public void addCategory(Category category) throws BlogServiceLayerDuplicateException {
        List<Category> categoryList = catDao.getAllCategoriesByName(category.getName());

        if (!categoryList.isEmpty()) {
            throw new BlogServiceLayerDuplicateException("Duplicate Category name.");
        } else {
            catDao.addCategory(category);
        }
    }

    @Override
    public Category getCategoryById(int categoryId) throws BlogServiceLayerDataValidationException {
        if (catDao.getCategoryById(categoryId) != null) {
            return catDao.getCategoryById(categoryId);
        } else {
            throw new BlogServiceLayerDataValidationException("No record returned, please try again");
        }
    }

    @Override
    public void updateCategory(Category category
    ) {
        catDao.updateCategory(category);
    }

    @Override
    public void deleteCategory(int categoryID) throws BlogServiceLayerForeignKeyConstraintException {
        List<Post> postList = postDao.getPostByCategoryId(categoryID);

        if (!postList.isEmpty()) {
            throw new BlogServiceLayerForeignKeyConstraintException(
                    "Please update or remove all posts for this category before deleting.");
        } else {
            catDao.deleteCategory(categoryID);
        }
    }

    @Override
    public void addStaticPage(StaticPage staticPage
    ) {
        statDao.addStaticPage(staticPage);
    }

    @Override
    public void addAuthor(Author author
    ) {
        authorDao.addAuthor(author);
    }

    @Override
    public void updatePendingEdit(Post post
    ) {
        postDao.updatePendingEdit(post);
    }

    @Override
    public void deleteItem(int itemID) throws BlogServiceLayerForeignKeyConstraintException {

        List<Post> postList = postDao.getPostByItemId(itemID);

        if (!postList.isEmpty()) {
            throw new BlogServiceLayerForeignKeyConstraintException(
                    "Please update or remove all posts for this item before deleting.");
        } else {
            itemDao.deleteItem(itemID);
        }
    }

    @Override
    public void updateItem(Item item
    ) {
        itemDao.updateItem(item);
    }

    @Override
    public Item getItemById(int itemID) throws BlogServiceLayerDataValidationException {
        if (itemDao.getItemById(itemID) != null) {
            return itemDao.getItemById(itemID);
        } else {
            throw new BlogServiceLayerDataValidationException("No record returned, please try again");
        }
    }

    @Override
    public void updateStaticPage(StaticPage staticPage
    ) {
        statDao.updateStaticPage(staticPage);
    }

    @Override
    public void deleteStaticPage(int staticPageID
    ) {
        statDao.deleteStaticPage(staticPageID);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message
    ) {
        this.message = message;
    }

    @Override
    public String addImage(MultipartFile pictureFile) throws IOException {
        String projectPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String trimmedPath = projectPath.substring(0, (projectPath).trim().length() - 41);
        String imagePath = "src/main/webapp/blog_images/";
        String imageDirectory = trimmedPath + imagePath;
        String baseUrl = "http://localhost:8080/BlogSpringMVC/blog_images/";

        String filename = pictureFile.getOriginalFilename().trim();
        String uploadedImageUrl = baseUrl + filename;

        try {

            pictureFile.transferTo(new File(imageDirectory + filename));

        } catch (IOException e) {
            throw new IOException(
                    "File upload failed, please retry the upload", e);
        }

        return uploadedImageUrl;
    }

    @Override
    public List<Post> getAllPostsMatchingHashTag(String hashTag) throws BlogServiceLayerSearchTagException {

        List<Post> postList = postDao.getAllPostsMatchingHashTag(hashTag);

        if (postList.isEmpty()) {
            throw new BlogServiceLayerSearchTagException(
                    "No search tags found based on search criteria, please try another search");
        } else {
            return postDao.getAllPostsMatchingHashTag(hashTag);
        }

    }

    @Override
    public List<Post> getAllPostsMatchingCategory(int categoryID
    ) {
        return postDao.getPostByCategoryId(categoryID);
    }

    @Override
    public Author getAuthorById(int authorID) {
            return authorDao.getAuthorById(authorID);
       }

    public User addUser(User newUser
    ) {
        return userDao.addUser(newUser);
    }

    @Override
    public void deleteUser(String username
    ) {
        userDao.deleteUser(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void rejectEditPost(int postId) {
        Post post = postDao.getPostById(postId);

        if (post.getOldPostID() > 0) {

            Post originalPost = postDao.getPostById(post.getOldPostID());
            originalPost.setPendingEdit(0);
            postDao.updatePendingEdit(originalPost);
            postDao.deletePost(postId);

        } else {
            postDao.deletePost(postId);

        }
    }

    @Override
    public Object deleteItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
