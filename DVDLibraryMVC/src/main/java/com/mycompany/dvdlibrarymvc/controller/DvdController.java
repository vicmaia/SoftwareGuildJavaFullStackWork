/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrarymvc.controller;

import com.mycompany.dvdlibrarymvc.model.Dvd;
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
import com.mycompany.dvdlibrarymvc.dao.DvdLibraryDao;

/**
 *
 * @author n0252282
 */
@Controller
public class DvdController {
    DvdLibraryDao dao;
    
    @Inject
    public DvdController(DvdLibraryDao dao) {
        this.dao = dao;
    }

@RequestMapping(value = "/displayContactsPage", method = RequestMethod.GET)
public String displayContactsPage(Model model) {
    // Get all the Contacts from the DAO
    List<Dvd> contactList = dao.getAllContacts();

    // Put the List of Contacts on the Model
    model.addAttribute("contactList", contactList);

    // Return the logical name of our View component
    return "contacts";
}
    
    @RequestMapping(value = "/createContact", method = RequestMethod.POST)
public String createContact(HttpServletRequest request) {
    // grab the incoming values from the form and create a new Dvd
    // object
    Dvd contact = new Dvd();
    contact.setFirstName(request.getParameter("firstName"));
    contact.setLastName(request.getParameter("lastName"));
    contact.setCompany(request.getParameter("company"));
    contact.setPhone(request.getParameter("phone"));
    contact.setEmail(request.getParameter("email"));

    // persist the new Dvd
    dao.addContact(contact);

    // we don't want to forward to a View component - we want to
    // redirect to the endpoint that displays the Contacts Page
    // so it can display the new Dvd in the table.
    //redirect: does not look for a .jsp with the name, tells browser to load this page which is defined above
    return "redirect:displayContactsPage";
}

@RequestMapping(value = "/displayContactDetails", method = RequestMethod.GET)
public String displayContactDetails(HttpServletRequest request, Model model) {
    String contactIdParameter = request.getParameter("contactId");
    int contactId = Integer.parseInt(contactIdParameter);

    Dvd contact = dao.getContactById(contactId);

    model.addAttribute("contact", contact);

    return "contactDetails";
}

@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
public String deleteContact(HttpServletRequest request) {
    String contactIdParameter = request.getParameter("contactId");
    long contactId = Long.parseLong(contactIdParameter);
    dao.removeContact(contactId);
    return "redirect:displayContactsPage";
}

@RequestMapping(value = "/displayEditContactForm", method = RequestMethod.GET)
public String displayEditContactForm(HttpServletRequest request, Model model) {
    String contactIdParameter = request.getParameter("contactId");
    long contactId = Long.parseLong(contactIdParameter);
    Dvd contact = dao.getContactById(contactId);
    model.addAttribute("contact", contact);
    return "editContactForm";
}

@RequestMapping(value = "/editContact", method = RequestMethod.POST)
public String editContact(@Valid @ModelAttribute("contact") Dvd contact, BindingResult result) {

    if (result.hasErrors()) {
        return "editContactForm";
    }

    dao.updateContact(contact);

    return "redirect:displayContactsPage";
}

}
