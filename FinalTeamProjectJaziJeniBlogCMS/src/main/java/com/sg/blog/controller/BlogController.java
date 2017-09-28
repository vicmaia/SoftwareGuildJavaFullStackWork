package com.sg.blog.controller;

import com.sg.blog.model.Author;
import com.sg.blog.model.Category;
import com.sg.blog.model.ImageReturn;
import com.sg.blog.model.Post;
import com.sg.blog.model.StaticPage;
import com.sg.blog.service.BlogServiceLayer;
import com.sg.blog.service.BlogServiceLayerDataValidationException;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class BlogController {

    private BlogServiceLayer service;

    @Inject
    public BlogController(BlogServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayMainPage(Model model) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        List<Post> postList = service.getAllPosts();
        List<Category> categoryList = service.getAllCategories();
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("postList", postList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("staticList", staticList);
        
        return "index";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String displayAdminPage(Model model) {
        List<Post> postList = service.getAllPosts();
        model.addAttribute("postList", postList);
        model.addAttribute("categoryList", service.getAllCategories());
        model.addAttribute("itemList", service.getAllItems());
        model.addAttribute("message", service.getMessage());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);
        return "adminPage";
    }

    @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
    public String staticPage(Model model, HttpServletRequest request) {
        int staticID = Integer.parseInt(request.getParameter("staticID"));
        model.addAttribute("categoryList", service.getAllCategories());
        model.addAttribute("authorList", service.getAllAuthors());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);

        try {
            StaticPage staticPage = service.getStaticPageById(staticID);
            model.addAttribute("staticPage", staticPage);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }

        return "static";
    }

    @RequestMapping(value = "/displayAddBlogForm", method = RequestMethod.GET)
    public String displayAddBlogForm(Model model) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        model.addAttribute("categoryList", service.getAllCategories());
        model.addAttribute("authorList", service.getAllAuthors());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("itemList", service.getAllItems());
        model.addAttribute("staticList", staticList);
        model.addAttribute("post", new Post());
        return "addBlogForm";
    }

    @RequestMapping(value = "/addBlog", method = RequestMethod.POST)
    public String addBlog(@Valid @ModelAttribute("post") Post post, BindingResult result, HttpServletRequest request,
            MultipartHttpServletRequest multiRequest, String displayTitle, MultipartFile pictureFile, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categoryList", service.getAllCategories());
            model.addAttribute("authorList", service.getAllAuthors());
            model.addAttribute("itemList", service.getAllItems());
            return "addBlogForm";
        }

        post.setPendingEdit(1);
        service.addPost(post);
        return "redirect:/adminPage";

    }

    @ResponseBody
    @RequestMapping(value = "/addBlog/addPicture", method = RequestMethod.POST)
    public ImageReturn addPicture(MultipartHttpServletRequest multiRequest, String displayTitle, MultipartFile pictureFile, Model model) {

        pictureFile = multiRequest.getFile("file");

        if (!pictureFile.isEmpty()) {
            try {

                String uploadedImageUrl = service.addImage(pictureFile);
                model.addAttribute("uploadedImageUrl", uploadedImageUrl);
                ImageReturn returnedImage = new ImageReturn();
                returnedImage.setLocation(uploadedImageUrl);

                return returnedImage;

            } catch (IOException e) {

                model.addAttribute("errorMsg", e);
                return null;
            }
        }

        return null;
    }

    @RequestMapping(value = "/displayEditBlogForm", method = RequestMethod.GET)
    public String displayEditBlogForm(HttpServletRequest request, Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String postIDParameter = request.getParameter("postID");
        int postID = Integer.parseInt(postIDParameter);
        try {
            Post post = service.getPostById(postID);
            model.addAttribute("post", post);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }
        model.addAttribute("categoryList", service.getAllCategories());
        model.addAttribute("authorList", service.getAllAuthors());
        model.addAttribute("itemList", service.getAllItems());
        return "editBlogForm";
    }

    @RequestMapping(value = "/editBlog", method = RequestMethod.POST)
    public String editBlog(@Valid
            @ModelAttribute("post") Post post, BindingResult result,
            HttpServletRequest request,
            MultipartHttpServletRequest multiRequest,
            String displayTitle, MultipartFile pictureFile,
            Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("categoryList", service.getAllCategories());
            model.addAttribute("authorList", service.getAllAuthors());
            model.addAttribute("itemList", service.getAllItems());
            return "editBlogForm";
        }

        post.setPendingEdit(2);
        service.updatePendingEdit(post);
        int oldPostID = post.getPostID();

        Post newPost = service.addPost(post);
        newPost.setOldPostID(oldPostID);
        newPost.setPendingEdit(1);
        service.updatePost(newPost);

        return "redirect:/adminPage";

    }

    @RequestMapping(value = "/approveEditBlog", method = RequestMethod.GET)
    public String approveEditBlog(HttpServletRequest request, Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String postIdParameter = request.getParameter("postID");

        int postID = Integer.parseInt(postIdParameter);
        try {
            Post post = service.getPostById(postID);
            int oldPostID = post.getOldPostID();
            post.setPendingEdit(0);
            service.updatePendingEdit(post);
            service.deletePost(oldPostID);
            model.addAttribute("post", post);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }

        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/rejectEditBlog", method = RequestMethod.GET)
    public String rejectEditBlog(HttpServletRequest request, Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String postIdParameter = request.getParameter("postID");
        int postID = Integer.parseInt(postIdParameter);
        try {
            Post post = service.getPostById(postID);
            service.rejectEditPost(postID);
            model.addAttribute("post", post);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }
        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/approveDeleteBlog", method = RequestMethod.GET)
    public String approveDeleteBlog(HttpServletRequest request, Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String postIdParameter = request.getParameter("postID");
        int postID = Integer.parseInt(postIdParameter);
        service.deletePost(postID);
        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/pendingDeleteBlog", method = RequestMethod.GET)
    public String pendingDeleteBlog(HttpServletRequest request, Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String postIdParameter = request.getParameter("postID");
        int postID = Integer.parseInt(postIdParameter);
        try {
            Post post = service.getPostById(postID);
            post.setPendingDelete(1);
            service.updatePost(post);
            post.setPendingEdit(0);
            service.updatePost(post);
            model.addAttribute("post", post);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }
        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/rejectDeleteBlog", method = RequestMethod.GET)
    public String rejectDeleteBlog(HttpServletRequest request, Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String postIdParameter = request.getParameter("postID");
        int postID = Integer.parseInt(postIdParameter);
        try {
            Post post = service.getPostById(postID);
            post.setPendingDelete(0);
            service.updatePost(post);
            post.setPendingEdit(0);
            service.updatePost(post);
            model.addAttribute("post", post);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }
        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/displayAddStaticPage", method = RequestMethod.GET)
    public String displayAddStaticPage(Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        model.addAttribute("categoryList", service.getAllCategories());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);
        model.addAttribute("authorList", service.getAllAuthors());
        model.addAttribute("staticPage", new StaticPage());
        return "addStaticForm";
    }

    @RequestMapping(value = "/addStaticPage", method = RequestMethod.POST)
    public String addStaticPage(@Valid
            @ModelAttribute("staticPage") StaticPage staticPage, BindingResult result,
            HttpServletRequest request, Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("authorList", service.getAllAuthors());
            return "addStaticForm";
        }

        service.addStaticPage(staticPage);

        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/displayAddAuthorForm", method = RequestMethod.GET)
    public String displayAddAuthorForm(Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        model.addAttribute("authorList", service.getAllAuthors());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);
        model.addAttribute("author", new Author());
        return "addAuthorForm";
    }

    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
    public String addAuthor(@Valid
            @ModelAttribute("author") Author author, BindingResult result,
            HttpServletRequest request
    ) {

        if (result.hasErrors()) {
            return "addAuthorForm";
        }

        service.addAuthor(author);

        return "redirect:/adminPage";

    }

}
