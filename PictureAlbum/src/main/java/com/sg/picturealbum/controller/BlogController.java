package com.sg.picturealbum.controller;

import com.sg.picturealbum.dao.AuthorDao;
import com.sg.picturealbum.dao.BlogListDao;
import com.sg.picturealbum.dao.CategoryDao;
import com.sg.picturealbum.dao.ItemDao;
import com.sg.picturealbum.dao.PostDao;
import com.sg.picturealbum.dao.StaticPageDao;
import com.sg.picturealbum.dao.UserDao;
import com.sg.picturealbum.model.Author;
import com.sg.picturealbum.model.Category;
import com.sg.picturealbum.model.Item;
import com.sg.picturealbum.model.Post;
import java.math.BigDecimal;
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
    AuthorDao authorDao;
    CategoryDao catDao;
    ItemDao itemDao;
    PostDao postDao;
    StaticPageDao statDao;
    UserDao userDao;

    @Inject
    public BlogController(BlogListDao dao, AuthorDao authorDao, CategoryDao catDao, ItemDao itemDao,
            PostDao postDao, StaticPageDao statDao, UserDao userDao) {
        this.dao = dao;
        this.authorDao = authorDao;
        this.catDao = catDao;
        this.itemDao = itemDao;
        this.postDao = postDao;
        this.statDao = statDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayMainPage(Model model) {
        // Get all the Posts from the DAO
        List<Post> postList = postDao.getAllPosts();

        //Get all categories from the DAO
        List<Category> categoryList = catDao.getAllCategories();

        // Put the List of Blogs on the Model
        model.addAttribute("postList", postList);

        // Put the List of categories on the Model
        model.addAttribute("categoryList", categoryList);

        // Return the logical name of our View component
        return "index";
    }

    @RequestMapping(value = "/displayAddBlogForm", method = RequestMethod.GET)
    public String displayAddBlogForm(Model model) {
        model.addAttribute("categoryList", catDao.getAllCategories());
        return "addBlogForm";
    }

    @RequestMapping(value = "/addBlog", method = RequestMethod.POST)
    public String addBlog(HttpServletRequest request, Category category) {
        
        String value1 = request.getParameter("submitButton");
        String value2 = request.getParameter("cancelButton");
        // grab the incoming values from the form and create a new Blog
        // object
        Post post = new Post();
        post.setTitle(request.getParameter("title"));
        post.setContent(request.getParameter("content"));
        post.setHashtags(request.getParameter("hashtags"));

//        Category category = new Category();
//        category.setName("");
//        category.setDescription("");
//        category.setCategoryID(Integer.parseInt(request.getParameter("category_id")));

        post.setCategory(category);

        Author author = new Author();
        author.setFirstName(request.getParameter("first_name"));
        author.setLastName(request.getParameter("last_name"));
        author.setTitle(request.getParameter("author_title"));

        post.setAuthor(author);

        Item item = new Item();
        item.setItemName(request.getParameter("item_name"));
        item.setItemDescription(request.getParameter("item_description"));
        item.setItemPrice(new BigDecimal(request.getParameter("item_price")));
        item.setItemQuantity(Integer.parseInt(request.getParameter("item_quantity")));

        post.setItem(item);

        post.setPendingDelete(0);
        post.setPendingEdit(1);
                

        // persist the new Blog
        if (value1 != null) {
            if (value1.contains("Submit")) {
                authorDao.addAuthor(author);
                itemDao.addItem(item);
                postDao.addPost(post);
                return "redirect:/adminPage";
            }
        }
        if (value2 != null) {
            if (value2.contains("Cancel")) {
                return "redirect:/adminPage";
            }
        }

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Blogs Page
        // so it can display the new Blog in the table.
        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/displayEditBlogForm", method = RequestMethod.GET)
    public String displayEditBlogForm(HttpServletRequest request, Model model) {
        String postIDParameter = request.getParameter("postID");
        int postID = Integer.parseInt(postIDParameter);
        Post post = postDao.getPostById(postID);
        model.addAttribute("post", post);
        model.addAttribute("categoryList", catDao.getAllCategories());
        return "editBlogForm";
    }

    @RequestMapping(value = "/editBlog", method = RequestMethod.POST)
    public String editBlog(@Valid @ModelAttribute("post") Post post, BindingResult result, HttpServletRequest request) {
        String value1 = request.getParameter("submitButton");
        String value2 = request.getParameter("cancelButton");
//        if (result.hasErrors()) {
//            return "editBlogForm";
//        }
        if (value1 != null) {
            if (value1.contains("Submit")) {
                post.setPendingEdit(1);
                postDao.updatePost(post);

                return "redirect:/adminPage";
            }
        }
        if (value2 != null) {
            if (value2.contains("Cancel")) {
                return "redirect:/adminPage";
            }
        }
        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String displayAdminPage(Model model
    ) {
        List<Post> postList = postDao.getAllPosts();
        model.addAttribute("postList", postList);
        return "adminPage";
    }
}
