/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.listexamples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class ListExamples2 {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        System.out.println("List size: " + stringList.size());

        stringList.add("The First String");

        stringList.add("The Second String");

        stringList.add("The Third String");

        stringList.add("The Fourth String");

        System.out.println("List size: " + stringList.size());
        //enhanced for does a lot for you
        //
        for (String currentString : stringList) {
            System.out.println(currentString);
        }
        //more code but does the same thing
        Iterator<String> iterator = stringList.iterator();
        
        while (iterator.hasNext()){
            String currentString = iterator.next();
            System.out.println(currentString);
        }
    }
}
