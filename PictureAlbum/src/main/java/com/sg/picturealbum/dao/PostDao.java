/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.picturealbum.dao;

import com.sg.picturealbum.model.Post;
import java.util.List;

/**
 *
 * @post n0252282
 */
public interface PostDao {

    public void addPost (Post post);

    public void deletePost(int postID);

    public void updatePost(Post post);

    public Post getPostById(int postID);

    public List<Post> getAllPosts();
}
