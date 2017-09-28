/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.controller;

import com.sg.blog.model.Category;
import com.sg.blog.model.StaticPage;
import com.sg.blog.service.BlogServiceLayer;
import com.sg.blog.service.BlogServiceLayerDataValidationException;
import com.sg.blog.service.BlogServiceLayerDuplicateException;
import com.sg.blog.service.BlogServiceLayerForeignKeyConstraintException;
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
 * @author n0190934
 */
@Controller
public class CategoryController {

    private BlogServiceLayer service;

    @Inject
    public CategoryController(BlogServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayAddCategoryForm", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("categoryList", service.getAllCategories());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);
        model.addAttribute("category", new Category());
        model.addAttribute("message", service.getMessage());
        return "addCategoryForm";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@Valid
            @ModelAttribute("category") Category category, BindingResult result,
            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addCategoryForm";
        }
        try {
            service.addCategory(category);
        } catch (BlogServiceLayerDuplicateException e) {
            service.setMessage(e.getMessage());
            return "redirect:/displayAddCategoryForm";
        }

        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/displayEditCategoryForm", method = RequestMethod.GET)
    public String displayEditCategoryForm(HttpServletRequest request, Model model) {
        String categoryIDParameter = request.getParameter("categoryID");
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        int categoryID = Integer.parseInt(categoryIDParameter);
        try {
            Category category = service.getCategoryById(categoryID);
            model.addAttribute("category", category);
            model.addAttribute("categoryList", service.getAllCategories());
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }
        return "editCategoryForm";
    }

    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public String editCategory(@Valid
            @ModelAttribute("category") Category category, BindingResult result,
            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editCategoryForm";
        }
        service.updateCategory(category);

        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request, Model model) {
        try {
            String categoryIDParameter = request.getParameter("categoryID");
            int categoryID = Integer.parseInt(categoryIDParameter);
            service.deleteCategory(categoryID);
        } catch (BlogServiceLayerForeignKeyConstraintException e) {
            service.setMessage(e.getMessage());
            return "redirect:/adminPage";
        }

        return "redirect:/adminPage";
    }
}
