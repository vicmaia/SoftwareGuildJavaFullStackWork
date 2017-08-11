package com.mycompany.flooringmastery;

import com.mycompany.flooringmastery.controller.FlooringMasteryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        //instantiate context
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller;
        for (String arg : args) {
            System.out.println(arg);
        }
        // ask context to instantiate controller
        // please get me the bean with the alias called controller and 
        // please cast it back as the type FlooringMasteryController
        if (args[0].equalsIgnoreCase("test")) {
            controller = ctx.getBean("controllerTest", FlooringMasteryController.class);
        } else {
            controller = ctx.getBean("controller", FlooringMasteryController.class);
        }

        controller.run();
    }
}
