/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailmergemvc;

import com.mycompany.mailmergemvc.Controller.MailMergeController;
import com.mycompany.mailmergemvc.DAO.PersonDAO;
import com.mycompany.mailmergemvc.DAO.PersonDAOInMemoryImpl;
import com.mycompany.mailmergemvc.DTO.View.MailMergeView;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        PersonDAO dao = new PersonDAOInMemoryImpl();
        MailMergeView view = new MailMergeView();

        MailMergeController controller = new MailMergeController(dao, view);
        controller.run();
    }
}
