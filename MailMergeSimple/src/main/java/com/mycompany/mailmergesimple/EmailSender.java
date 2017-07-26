package com.mycompany.mailmergesimple;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author n0252282
 */
// In class code-along Week 3 Tuesday
public class EmailSender {

    public static void main(String[] args) {
        Person morgan = new Person("morgan@yahoo", "Morgan");
        Person eric = new Person("eric@yahoo", "Eric");
        Person gabe = new Person("gabe@yahoo", "Gabe");

        //call send email
        if (sendEmail(morgan)) {
            System.out.println("Message sent\n");
        }
        //sendEmail(morgan);
        sendEmail(eric);
        sendEmail(gabe);

    }

    public static boolean sendEmail(Person personToReceiveEmail) {
        System.out.println("Sending mail to: " + personToReceiveEmail.getEmail() + ". Hello, " + personToReceiveEmail.getName());
        return true;
    }
}
