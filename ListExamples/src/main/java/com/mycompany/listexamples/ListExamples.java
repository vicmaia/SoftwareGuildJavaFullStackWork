/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.listexamples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class ListExamples {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        System.out.println("List size: " + stringList.size());

        stringList.add("The First String");

        System.out.println("List size: " + stringList.size());

        stringList.add("The Second String");

        System.out.println("List size: " + stringList.size());

        stringList.remove(1);

        System.out.println("List size: " + stringList.size());

        stringList.remove(0);

        System.out.println("List size: " + stringList.size());

        stringList.remove(0);
        
        System.out.println("List size: " + stringList.size());
    }
}
