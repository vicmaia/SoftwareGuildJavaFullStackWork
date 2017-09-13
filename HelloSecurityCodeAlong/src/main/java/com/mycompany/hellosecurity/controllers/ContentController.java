/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellosecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0252282
 */
@Controller
public class ContentController {

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public String displayContentPage() {
        return "content";
    }
}
