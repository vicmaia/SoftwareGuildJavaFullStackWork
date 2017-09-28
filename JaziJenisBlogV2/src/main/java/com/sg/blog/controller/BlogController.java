package com.sg.blog.controller;

import com.sg.blog.dao.BlogListDao;
import com.sg.blog.model.Blog;
import com.sg.blog.model.Category;
import com.sg.blog.model.Post;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ward
 */
@Controller
public class BlogController {

    BlogListDao dao;

    @Inject
    public BlogController(BlogListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayMainPage(Model model) {
        // Get all the Posts from the DAO
        List<Post> postList = dao.getAllPosts();

        //Get all categories from the DAO
        List<Category> categoryList = dao.getAllCategories();

        // Put the List of Blogs on the Model
        model.addAttribute("postList", postList);

        // Put the List of categories on the Model
        model.addAttribute("categoryList", categoryList);

        // Return the logical name of our View component
        return "index";
    }

    @RequestMapping(value = "/displayBlogsPage", method = RequestMethod.GET)
    public String displayBlogsPage(Model model) {
        // Get all the Blogs from the DAO
        List<Blog> blogList = dao.getAllBlogs();

        //Get all categories from the DAO
        List<Category> categoryList = dao.getAllCategories();

        // Put the List of Blogs on the Model
        model.addAttribute("blogList", blogList);

        // Put the List of categories on the Model
        model.addAttribute("categoryList", categoryList);

        // Return the logical name of our View component
        return "contacts";
    }

    @RequestMapping(value = "/createBlog", method = RequestMethod.POST)
    public String createBlog(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Blog
        // object
        Blog blog = new Blog();
        blog.setFirstName(request.getParameter("firstName"));
        blog.setLastName(request.getParameter("lastName"));
        blog.setCompany(request.getParameter("company"));
        blog.setPhone(request.getParameter("phone"));
        blog.setEmail(request.getParameter("email"));

        // persist the new Blog
        dao.addBlog(blog);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Blogs Page
        // so it can display the new Blog in the table.
        return "redirect:displayBlogsPage";
    }

    @RequestMapping(value = "/displayBlogDetails", method = RequestMethod.GET)
    public String displayPostDetails(HttpServletRequest request, Model model) {
        String blogIdParameter = request.getParameter("blogId");
        long blogId = Long.parseLong(blogIdParameter);

        Blog blog = dao.getBlogById(blogId);

        model.addAttribute("blog", blog);

        return "blogDetails";
    }

    @RequestMapping(value = "/deleteBlog", method = RequestMethod.GET)
    public String deleteBlog(HttpServletRequest request) {
        String blogIdParameter = request.getParameter("blogId");
        long blogId = Long.parseLong(blogIdParameter);
        dao.removeBlog(blogId);
        return "redirect:displayBlogsPage";
    }

    @RequestMapping(value = "/displayEditBlogForm", method = RequestMethod.GET)
    public String displayEditBlogForm(HttpServletRequest request, Model model) {
        String blogIdParameter = request.getParameter("blogId");
        long blogId = Long.parseLong(blogIdParameter);
        Blog blog = dao.getBlogById(blogId);
        model.addAttribute("blog", blog);
        return "editBlogForm";
    }

    @RequestMapping(value = "/editBlog", method = RequestMethod.POST)
    public String editBlog(@Valid @ModelAttribute("blog") Blog blog, BindingResult result) {

        if (result.hasErrors()) {
            return "editBlogForm";
        }

        dao.updateBlog(blog);

        return "redirect:displayBlogsPage";
    }

}
