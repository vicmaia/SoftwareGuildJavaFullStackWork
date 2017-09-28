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
import com.sg.blog.model.User;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author n0001123
 */
public interface BlogServiceLayer {

    public Post addPost(Post post);

    public void updatePost(Post post);

    public void deletePost(int postID);

    public List<Post> getAllPosts();

    public Post getPostById(int postId) throws BlogServiceLayerDataValidationException;

    public void updatePendingEdit(Post post);
    
    public void rejectEditPost(int postId);

    public List<Post> getAllPostsMatchingHashTag(String hashTag) throws BlogServiceLayerSearchTagException;
    
    public List<Post> getAllPostsMatchingCategory(int categoryID);

    public void addCategory(Category category) throws BlogServiceLayerDuplicateException;

    public void updateCategory(Category category);

    public void deleteCategory(int categoryID) throws BlogServiceLayerForeignKeyConstraintException;

    public List<Category> getAllCategories();

    Category getCategoryById(int categoryID) throws BlogServiceLayerDataValidationException;

    public void addStaticPage(StaticPage staticPage);

    public void updateStaticPage(StaticPage staticPage);

    public void deleteStaticPage(int staticPageID);

    public List<StaticPage> getAllStaticPages();

    public StaticPage getStaticPageById(int staticPageID) throws BlogServiceLayerDataValidationException;

    public void addAuthor(Author author);

    public List<Author> getAllAuthors();

    public void addItem(Item item);

    public void updateItem(Item item);

    public void deleteItem(int itemID) throws BlogServiceLayerForeignKeyConstraintException;

    public Item getItemById(int itemID) throws BlogServiceLayerDataValidationException;

    public List<Item> getAllItems();

    public String addImage(MultipartFile pictureFile) throws IOException;

    public String getMessage();

    public void setMessage(String message);

    public User addUser(User newUser);

    public void deleteUser(String username);

    public List<User> getAllUsers();

    public Author getAuthorById(int authorID);

    public Object deleteItem();

}
