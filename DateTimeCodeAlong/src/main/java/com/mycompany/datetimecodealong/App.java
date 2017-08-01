/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datetimecodealong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);

        ld = LocalDate.parse("02/28/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);

        String isoDate = ld.toString();
        System.out.println(isoDate);

        ld = LocalDate.parse(isoDate);
        System.out.println(ld);

        //Format to M/D/Y -- 02/28/2018
        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);

        //can even make up a pattern -- 02=28-2018+=+=+=
        formatted = ld.format(DateTimeFormatter.ofPattern("MM=dd-yyyy+=+=+="));
        System.out.println(formatted);
        //FULL is an enum (ee nooom) -- Wednesday, February 28, 2018
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        //Sunday, February 28, 2010
        //Monday, February 22, 2010
        LocalDate past = ld.minusDays(6);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        Period diff = ld.until(past);
        System.out.println(diff);
        System.out.println(diff.getMonths());
        diff = past.until(ld);
        System.out.println(diff.getMonths());

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        formatted = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);

        Date legacyDate = new Date();
        System.out.println(legacyDate);
        
        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);
        
        //convert legacy dates to current datetime api
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(),ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);
        
        //convert gregorian dates to current datetime api
        zdt = legacyCalendar.toZonedDateTime();
        ld = zdt.toLocalDate();
        System.out.println(ld);
    }
};
