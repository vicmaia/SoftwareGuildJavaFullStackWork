package com.sg.picturealbum.dao;

import com.sg.picturealbum.model.Author;
import com.sg.picturealbum.model.Blog;
import com.sg.picturealbum.model.Category;
import com.sg.picturealbum.model.Item;
import com.sg.picturealbum.model.Post;
import com.sg.picturealbum.model.StaticPage;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ward
 */
public interface BlogListDao {
// add the given Blog to the data store

    public Blog addBlog(Blog blog);
    // remove the Blog with the given id from the data store

    public void removeBlog(long blogId);
    
    // update the given Blog in the data store
    public void updateBlog(Blog blog);

    // retrieve all Blogs from the data store
    public List<Blog> getAllBlogs();

    public Post addPost(Post post);

    public void removePost(int postId);

    public void updatePost(Post post);
    // retrieve all Posts from the data store
    public List<Post> getAllPosts();

    // retrieve all Categories from the data store
    public List<Category> getAllCategories();

    public List<Author> getAllAuthors();

    public List<Item> getAllItems();

    public List<StaticPage> getAllStatic();

    // retrieve the Blog with the given id from the data store
    public Blog getBlogById(long blogId);

    // search for Blogs by the given search criteria values
    public Post getPostById(int postId);

    public List<Blog> searchBlogs(Map<SearchTerm, String> criteria);
    
    public List<Post> searchPosts(Map<SearchTerm, String> criteria);
    
    List<Post> getAllPostsMatchingHashTag(String hashTag);
}
