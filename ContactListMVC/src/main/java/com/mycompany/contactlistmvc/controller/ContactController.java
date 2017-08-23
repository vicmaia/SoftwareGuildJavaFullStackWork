/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlistmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0252282
 */
@Controller
public class ContactController {
    
    @RequestMapping(value="/displayContactsPage", method=RequestMethod.GET)
    public String displayContactsPage () {
        //look for a .jsp named contacts and return it to the user with a returned string to Spring
        return "contacts";
    }
}
