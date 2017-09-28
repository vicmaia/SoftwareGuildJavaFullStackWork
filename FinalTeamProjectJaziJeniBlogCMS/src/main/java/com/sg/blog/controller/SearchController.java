/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.controller;

import com.sg.blog.dao.CategoryDao;
import com.sg.blog.dao.PostDao;
import com.sg.blog.dao.StaticPageDao;
import com.sg.blog.model.Category;
import com.sg.blog.model.Post;
import com.sg.blog.model.StaticPage;
import com.sg.blog.service.BlogServiceLayer;
import com.sg.blog.service.BlogServiceLayerSearchTagException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    private BlogServiceLayer service;

    @Inject
    public SearchController(BlogServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/searchHashTag", method = RequestMethod.GET)
    public String searchHashTag(HttpServletRequest request, Model model) {
        String hashTagToSearch = request.getParameter("hashTagToSearch");
        try {

            List<Post> postList = service.getAllPostsMatchingHashTag(hashTagToSearch);
            List<Category> categoryList = service.getAllCategories();
            List<StaticPage> staticList = service.getAllStaticPages();
            model.addAttribute("staticList", staticList);

            model.addAttribute("postList", postList);
            model.addAttribute("categoryList", categoryList);
            return "search";

        } catch (BlogServiceLayerSearchTagException e) {
            service.setMessage(e.getMessage());
            model.addAttribute("message", service.getMessage());
            List<Category> categoryList = service.getAllCategories();
            List<StaticPage> staticList = service.getAllStaticPages();
            model.addAttribute("staticList", staticList);
            model.addAttribute("categoryList", categoryList);
            return "search";
        }

    }

    @RequestMapping(value = "/displayFilteredCategory", method = RequestMethod.GET)
    public String displayFilteredCategory(HttpServletRequest request, Model model
    ) {
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        List<Post> postList = service.getAllPostsMatchingCategory(categoryID);
        List<Category> categoryList = service.getAllCategories();
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);
        model.addAttribute("postList", postList);
        model.addAttribute("categoryList", categoryList);

        return "search";
    }
}
