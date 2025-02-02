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
public class MakeTags {

    // The web is built with HTML Strings like "<i>Yay</i>" which 
    // draws Yay as italic text. In this example, the "i" tag makes 
    // <i> and </i> which surround the word "Yay". Given tag and word 
    // Strings, create the HTML String with tags around the word, e.g. 
    // “<i>Yay</i>". 
    //
    // makeTags("i", "Yay") -> "<i>Yay</i>"
    // makeTags("i", "Hello") -> "<i>Hello</i>"
    // makeTags("cite", "Yay") -> "<cite>Yay</cite>"
    public String makeTags(String tag, String content) {
        return "<"+tag+">"+content+"</"+tag+">";
    }
}
