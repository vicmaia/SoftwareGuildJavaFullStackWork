/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.initialization;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        int count = 19;
        changeMyValue(count);
        System.out.println("Count = " + count);

        Person myPerson = new Person();
        myPerson.setAge(24);
        myPerson.setName("Joe");
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());

        changeMyName(myPerson);
        
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());
    }

    public static void changeMyValue(int value) {
        value = 21;
    }
    
    public static void changeMyName(Person person) {
        person.setName("Steve");
    }
//        Person person = new Person();
//        person.setAge(35);
//        person.setName("Eric");
//        
//        
//        System.out.println("Age = " + person.getAge());
//        System.out.println("Name = " + person.getName());
}
