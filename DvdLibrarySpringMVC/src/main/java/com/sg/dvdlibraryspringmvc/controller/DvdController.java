/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import javax.annotation.PostConstruct;
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
 * @author n0211021
 */
@Controller
public class DvdController {

    DvdLibraryDao dao;

    @Inject
    public DvdController(DvdLibraryDao dao) {
        this.dao = dao;
    }
    
   @PostConstruct
    public void init() {
        dao.getAllDvds();
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model) {
               List<Dvd> dvdList = dao.getAllDvds();

        // Put the List of Dvds on the Model
        model.addAttribute("dvdList", dvdList);
        return "index";
    }

    
    
    
    

    @RequestMapping(value = "/displayDvdsPage", method = RequestMethod.GET)
    public String displayDvdsPage(Model model) {
        
        // Get all the Dvds from the DAO
        List<Dvd> dvdList = dao.getAllDvds();

        // Put the List of Dvds on the Model
        model.addAttribute("dvdList", dvdList);

        // Return the logical name of our View component
        return "index";
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String createDvd(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Dvd
        // object
        Dvd dvd = new Dvd();
        dvd.setDvdTitle(request.getParameter("dvdTitle"));
        dvd.setDirector(request.getParameter("director"));
        String releaseYearstring = (request.getParameter("releaseYear"));
        Integer releaseYear = Integer.parseInt(releaseYearstring);
        dvd.setReleaseYear(releaseYear);
        //dvd.setReleaseYear(Integer.parseInt(request.getParameter("releaseYear")));
        dvd.setRating(request.getParameter("rating"));
        dvd.setNotes(request.getParameter("notes"));

        // persist the new Dvd
        dao.addDvd(dvd);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Dvds Page
        // so it can display the new Dvd in the table.
        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayDvdDetails", method = RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model) {
        String dvdIDParameter = request.getParameter("dvdID");
        Integer dvdID = Integer.parseInt(dvdIDParameter);

        Dvd dvd = dao.getDvdByID(dvdID);

        model.addAttribute("dvd", dvd);

        return "dvdDetails";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        String dvdIDParameter = request.getParameter("dvdID");
        Integer dvdID = Integer.parseInt(dvdIDParameter);
        dao.removeDvd(dvdID);
        return "redirect:displayDvdsPage";
    }

    @RequestMapping(value = "/displayEditDvdForm", method = RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model) {
        String dvdIDParameter = request.getParameter("dvdID");
        Integer dvdID = Integer.parseInt(dvdIDParameter);
        Dvd dvd = dao.getDvdByID(dvdID);
        model.addAttribute("dvd", dvd);
        return "editDvdForm";
    }


    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {

        if (result.hasErrors()) {
            return "editDvdForm";
        }

        dao.updateDvd(dvd);

        return "redirect:displayDvdsPage";
    }

}

