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
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String operator = "";
        double operand1 = 0;
        double operand2 = 0;

        do {
            System.out.println("Please enter your desired operation (+,-,/,* or exit): ");

            operator = sc.nextLine();

            if (operator.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Please enter the first operand: ");
            operand1 = parseDouble(sc.nextLine());

            System.out.println("Please enter the second operand: ");
            operand2 = parseDouble(sc.nextLine());

            switch (operator) {
                case "+":
                    System.out.println(operand1 + " + " + operand2 + " = " + SimpleCalculator.add(operand1, operand2));
                    break;
                case "-":
                    System.out.println(operand1 + " - " + operand2 + " = " + SimpleCalculator.subtract(operand1, operand2));
                    break;
                case "/":
                    System.out.println(operand1 + " / " + operand2 + " = " + SimpleCalculator.divide(operand1, operand2));
                    break;
                case "*":
                    System.out.println(operand1 + " * " + operand2 + " = " + SimpleCalculator.multiply(operand1, operand2));
                    break;
                default:
                    throw new AssertionError();
            }

        } while (!operator.equals("exit"));
    }
}
