/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplecalculator;

import static java.lang.Double.parseDouble;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class AppRefactor {

    public static void main(String[] args) {
        String operator = "";
        double operand1 = 0;
        double operand2 = 0;
        UserIO myUserIO = new UserIOImpl();

        do {
            operator = myUserIO.readString("Please enter your desired operation (+,-,/,* or exit): ");

            if (operator.equalsIgnoreCase("exit")) {
                break;
            }

            operand1 = myUserIO.readDouble("Please enter the first operand: ");

            operand2 = myUserIO.readDouble("Please enter the second operand: ");

            switch (operator) {
                case "+":
                    myUserIO.print(operand1 + " + " + operand2 + " = " + SimpleCalculator.add(operand1, operand2));
                    break;
                case "-":
                    myUserIO.print(operand1 + " - " + operand2 + " = " + SimpleCalculator.subtract(operand1, operand2));
                    break;
                case "/":
                    myUserIO.print(operand1 + " / " + operand2 + " = " + SimpleCalculator.divide(operand1, operand2));
                    break;
                case "*":
                    myUserIO.print(operand1 + " * " + operand2 + " = " + SimpleCalculator.multiply(operand1, operand2));
                    break;
                default:
                    throw new AssertionError();
            }

        } while (!operator.equals("exit"));
    }
}
