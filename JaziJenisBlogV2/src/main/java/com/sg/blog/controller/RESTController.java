/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.controller;

import com.sg.blog.dao.BlogListDao;
import com.sg.blog.model.Blog;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ward
 */
@CrossOrigin
@Controller
public class RESTController {
private BlogListDao dao;

    @Inject
    public RESTController(BlogListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Blog getBlog(@PathVariable("id") long id) {
        return dao.getBlogById(id);
    }

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Blog createBlog(@Valid @RequestBody Blog blog) {
        return dao.addBlog(blog);
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable("id") long id) {
        dao.removeBlog(id);
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBlog(@PathVariable("id") long id, @Valid @RequestBody Blog blog) throws UpdateIntegrityException {
        
        if (id != blog.getBlogId()) {
            throw new UpdateIntegrityException("Blog Id on URL must match Blog Id in submitted data.");
        }
        dao.updateBlog(blog);
    }

    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getAllBlogs() {
        return dao.getAllBlogs();
    }    
}
