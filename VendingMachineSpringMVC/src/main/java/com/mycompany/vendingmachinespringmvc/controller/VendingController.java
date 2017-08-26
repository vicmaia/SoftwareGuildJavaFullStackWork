/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.controller;

import com.mycompany.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachinespringmvc.model.Item;
import com.mycompany.vendingmachinespringmvc.service.InsufficientFundsException;
import com.mycompany.vendingmachinespringmvc.service.NoItemInventoryException;
import com.mycompany.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0252282
 */
@Controller
public class VendingController {

    private VendingMachineServiceLayer service;

    @Inject
    public VendingController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model) throws VendingMachinePersistenceException {
        List<Item> items = service.getAllItems();
        BigDecimal moneyInserted = service.getCurrentMoney();
        Integer selection = service.getSelection();
        String message = service.getMessage();

        model.addAttribute("items", items);
        model.addAttribute("money", moneyInserted);
        model.addAttribute("selection", selection);
        model.addAttribute("message", message);
        return "index";
    }
    //also need add money, return change, make purchase

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request) {
        String btnValue = request.getParameter("money");

        if (btnValue.equals("Add Dollar")) {
            service.setCurrentMoney(new BigDecimal("1.00"));
        }
        if (btnValue.equals("Add Quarter")) {
            service.setCurrentMoney(new BigDecimal(".25"));
        }
        if (btnValue.equals("Add Dime")) {
            service.setCurrentMoney(new BigDecimal(".10"));
        }
        if (btnValue.equals("Add Nickel")) {
            service.setCurrentMoney(new BigDecimal(".05"));
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) throws
            InsufficientFundsException,
            VendingMachinePersistenceException {
        Integer selectItem = Integer.parseInt(request.getParameter("id"));
        service.setSelection(selectItem);
        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    public String makePurchase(HttpServletRequest request) throws
            InsufficientFundsException,
            VendingMachinePersistenceException,
            NoItemInventoryException {
        Integer selectItem = Integer.parseInt(request.getParameter("id"));
        service.purchaseItem(selectItem);
        return "redirect:/";
    }
}
