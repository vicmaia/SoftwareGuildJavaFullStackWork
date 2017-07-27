/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.section3unittests;

/**
 *
 * @author n0252282
 */
public class MakePi {
    // Return an int array length n containing the first n digits of pi.
    //
    // makePi(3) -> {3, 1, 4}

    public int[] MakePi(int n) {
        double pi = Math.PI;
        
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            result[i] = (int) Math.floor(pi);
            pi -= result[i];
            pi *= 10;
        }
        return result;
    }
}
