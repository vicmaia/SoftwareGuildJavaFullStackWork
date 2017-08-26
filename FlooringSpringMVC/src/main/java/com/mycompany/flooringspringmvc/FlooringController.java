/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringspringmvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0252282
 */
//We must annotate the class with @Controller to register this class with the Spring MVC framework.
@Controller
public class FlooringController {
//The URL and HTTP request method that should be routed to this method are specified using the @RequestMapping annotation.

    @RequestMapping(value = "/FlooringServlet",
            method = RequestMethod.POST)
    public String factorNumber(HttpServletRequest request,
            Map<String, Object> model) {
        
        BigDecimal width = new BigDecimal(request.getParameter("width"));
        BigDecimal length = new BigDecimal(request.getParameter("length"));
        BigDecimal costPerSqFt = new BigDecimal(request.getParameter("costPerSqFt"));


        //do our calculations
        BigDecimal totalSqFt = width.multiply(length);
        BigDecimal totalCost = costPerSqFt.multiply(totalSqFt);
        BigDecimal totalLabor = totalSqFt.divide(new BigDecimal("20"), RoundingMode.UP).multiply(new BigDecimal("86"));
        BigDecimal overall = totalCost.add(totalLabor);

        //set results to attributes on the request
        //set all of the results on the requestattributes map
        model.put("width", width);
        model.put("length", length);
        model.put("costPerSqFt", costPerSqFt);
        model.put("totalSqFt", totalSqFt);
        model.put("totalCost", totalCost);
        model.put("totalLabor", totalLabor);
        model.put("overall", overall);
        return "result";
//        request.setAttribute("isPrime", isPrime);
//        request.setAttribute("isPerfect", isPerfect);


    }
}
