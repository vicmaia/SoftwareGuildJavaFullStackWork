/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.controller;

import com.sg.blog.model.Item;
import com.sg.blog.model.StaticPage;
import com.sg.blog.service.BlogServiceLayer;
import com.sg.blog.service.BlogServiceLayerDataValidationException;
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
public class ItemController {

    private BlogServiceLayer service;

    @Inject
    public ItemController(BlogServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayAddItemForm", method = RequestMethod.GET)
    public String displayAddItemForm(Model model
    ) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        model.addAttribute("itemList", service.getAllItems());
        List<StaticPage> staticList = service.getAllStaticPages();
        model.addAttribute("staticList", staticList);
        model.addAttribute("item", new Item());
        return "addItemForm";
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@Valid
            @ModelAttribute("item") Item item, BindingResult result,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addItemForm";
        }
        service.addItem(item);

        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/displayEditItemForm", method = RequestMethod.GET)
    public String displayEditItemForm(HttpServletRequest request, Model model) {
        String message = "";
        service.setMessage(message);
        model.addAttribute("message", service.getMessage());
        String itemIDParameter = request.getParameter("itemID");
        int itemID = Integer.parseInt(itemIDParameter);
        try {
            Item item = service.getItemById(itemID);
            model.addAttribute("item", item);
        } catch (BlogServiceLayerDataValidationException e) {
            service.setMessage(e.getMessage());
        }
        model.addAttribute("itemList", service.getAllCategories());
        return "editItemForm";
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.POST)
    public String editItem(@Valid
            @ModelAttribute("item") Item item, BindingResult result,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "editItemForm";
        }
        service.updateItem(item);

        return "redirect:/adminPage";
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public String deleteItem(HttpServletRequest request, Model model) {
        try {
            String itemIDParameter = request.getParameter("itemID");
            int itemID = Integer.parseInt(itemIDParameter);
            service.deleteItem(itemID);
        } catch (BlogServiceLayerForeignKeyConstraintException e) {
            service.setMessage(e.getMessage());
            return "redirect:/adminPage";
        }
        return "redirect:/adminPage";
    }

}
