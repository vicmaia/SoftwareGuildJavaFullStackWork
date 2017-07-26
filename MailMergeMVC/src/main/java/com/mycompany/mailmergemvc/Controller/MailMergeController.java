/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailmergemvc.Controller;

import com.mycompany.mailmergemvc.DAO.PersonDAO;
import com.mycompany.mailmergemvc.DAO.PersonDAOInMemoryImpl;
import com.mycompany.mailmergemvc.DTO.Person;
import com.mycompany.mailmergemvc.DTO.View.MailMergeView;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class MailMergeController {

    PersonDAO dao;
    MailMergeView view;

    public MailMergeController(PersonDAO dao, MailMergeView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        //retrieve people to email list 
        List<Person> emailList = dao.retrieveAllPeople();

        //display list to user
        view.printEmailList(emailList);

    }
}
