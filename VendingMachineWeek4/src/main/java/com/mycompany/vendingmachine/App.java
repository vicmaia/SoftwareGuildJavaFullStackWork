package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.controller.VendingMachineController;
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

        // ask context to instantiate controller
        // please get me the bean with the alias called controller and 
        // please cast it back as the type classrostercontroller
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);

        controller.run();
    }
}
