package com.sg.blog.dao;

import com.sg.blog.model.Blog;
import com.sg.blog.model.Category;
import com.sg.blog.model.Post;
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

    // retrieve all Posts from the data store
    public List<Post> getAllPosts();

    // retrieve all Categories from the data store
    public List<Category> getAllCategories();

    // retrieve the Blog with the given id from the data store
    public Blog getBlogById(long blogId);
    // search for Blogs by the given search criteria values

    public List<Blog> searchBlogs(Map<SearchTerm, String> criteria);
}
