/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Post;
import java.util.List;

/**
 *
 * @post n0252282
 */
public interface PostDao {

    public Post addPost (Post post);

    public void deletePost(int postID);

    public void updatePost(Post post);
    
    public void updatePendingEdit(Post post);

    public Post getPostById(int postID);
    
    public List<Post> getPostByItemId(int itemID);
    
    public List<Post> getPostByCategoryId(int categoryID);

    public List<Post> getAllPosts();
    
    public List<Post> getAllApprovedPosts();
    
    public List<Post> getAllPostsMatchingHashTag(String hashTag);

}
