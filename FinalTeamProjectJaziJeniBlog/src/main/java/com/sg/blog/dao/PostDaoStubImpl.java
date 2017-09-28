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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0001123
 */
public class PostDaoStubImpl implements PostDao {

    Map<Integer, Post> postList = new HashMap();

    public PostDaoStubImpl() {
        CreateFirstPost();
    }

    private Author CreateFirstAuthor() {
        Author author = new Author();
        author.setFirstName("Sally");
        author.setLastName("Ride");
        author.setTitle("Astronaut");
        return author;
    }

    private Author CreateSecondAuthor() {
        Author author = new Author();
        author.setFirstName("Little");
        author.setLastName("Debbie");
        author.setTitle("Baker");
        return author;
    }

    private Item CreateFirstItem() {
        Item item = new Item();
        item.setItemName("Gold Necklace");
        item.setItemDescription("Super awesome");
        item.setItemPrice(new BigDecimal("10.99"));
        item.setItemQuantity(5);
        return item;
    }

    private Item CreateSecondItem() {
        Item item = new Item();
        item.setItemName("Silver Bracelet");
        item.setItemDescription("Wicked!  Wow!");
        item.setItemPrice(new BigDecimal("4.28"));
        item.setItemQuantity(8);
        return item;
    }

    private Category CreateFirstCategory() {
        Category category = new Category();
        category.setName("Awesome Stuff");
        category.setDescription("Nice");
        return category;
    }

    private Category CreateSecondCategory() {
        Category category = new Category();
        category.setName("Junk");
        category.setDescription("Crapy Junky Stuff");
        return category;
    }

    private Post CreateFirstPost() {
        Post post = new Post();
        Item item = CreateFirstItem();
        Author author = CreateFirstAuthor();
        Category category = CreateFirstCategory();
        post.setPostID(1);
        post.setAuthor(author);
        post.setCategory(category);
        post.setContent("Awesome post");
        post.setHashtags("test test test");
        post.setItem(item);
        post.setPendingDelete(1);
        post.setPendingEdit(1);
        post.setOldPostID(0);
        post.setTitle("Awesome post");
        postList.put(1, post);
        return post;
    }

    private Post CreateSecondPost() {
        Post post = new Post();

        Item item = CreateFirstItem();
        Author author = CreateFirstAuthor();
        Category category = CreateFirstCategory();
        post.setPostID(2);
        post.setAuthor(author);
        post.setCategory(category);
        post.setContent("Nice Post");
        post.setHashtags("sample tags");
        post.setItem(item);
        post.setPendingDelete(1);
        post.setPendingEdit(1);
        post.setOldPostID(0);
        post.setTitle("Less awesome post");
        postList.put(2, post);
        return post;
    }

    @Override
    public List<Post> getPostByItemId(int itemID) {
//        Post post = CreateFirstPost();
        postList.get(itemID);
        return new ArrayList<>(postList.values());
    }

    @Override
    public List<Post> getPostByCategoryId(int categoryID) {
        for (Post currentPost : postList.values()) {
             if (currentPost.getCategory().getCategoryID() == categoryID) {
                 List<Post> matches = new ArrayList<>();
                 matches.add(currentPost);
                 return matches;
             }
        }    
        List<Post> matches = new ArrayList<>();
        return matches;
    }

    @Override
    public Post addPost(Post post) {
        postList.put(post.getPostID(), post);
        return post;
    }

    @Override
    public void deletePost(int postID
    ) {
        postList.remove(postID);
    }

    @Override
    public void updatePost(Post post
    ) {
        CreateSecondPost();
        post.setHashtags("#gold");
        postList.put(post.getPostID(), post);
    }

    @Override
    public void updatePendingEdit(Post post
    ) {
        CreateFirstPost();
        postList.put(post.getPostID(), post);
        post.setPendingEdit(2);
        postList.put(post.getOldPostID(), post);
    }

    @Override
    public Post getPostById(int postID) {
        return postList.get(postID);
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(postList.values());
    }

    @Override
    public List<Post> getAllApprovedPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getAllPostsMatchingHashTag(String hashTag
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
