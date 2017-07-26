/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailmergemvc.DTO.View;

import com.mycompany.mailmergemvc.DTO.Person;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class MailMergeView {

    public void printEmailList(List<Person> listToPrint) {
        for (Person personToPrint : listToPrint) {
            System.out.println("Sending message to: " + personToPrint.getEmail());
            System.out.println("Hello, " + personToPrint.getName());
        }

    }
}
