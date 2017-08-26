/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lucky7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class LuckyController {
//The URL and HTTP request method that should be routed to this method are specified using the @RequestMapping annotation.

    @RequestMapping(value = "/LuckyServlet",
            method = RequestMethod.POST)
    public String luckyNumber(HttpServletRequest request,
            Map<String, Object> model) {
        
        List<Integer> results = new ArrayList<>();
        int highestResult = 0;
        String input = request.getParameter("bet");

        int bet = Integer.parseInt(input);
        int originalBet = bet;
        
        Random random = new Random();

        while (bet > 0) {
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;

            int total = dice1 + dice2;

            if (total == 7) {
                bet += 4;
            } else {
                bet--;
            }

            if (bet >= highestResult) {
                highestResult = bet;
            }

            results.add(total);
        }
        
        

        model.put("youBet", originalBet);
        model.put("rounds", results.size());
        model.put("highestTotal", highestResult);
        
        return "result";
    }
}

