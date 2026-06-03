package com.wipro.Assignment;

import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        boolean choice;

        do {
            System.out.println("Enter two numbers:");
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();

            System.out.println("Choose operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");

            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Addition: " + (num1 + num2));
                    break;

                case 2:
                    System.out.println("Subtraction: " + (num1 - num2));
                    break;

                case 3:
                    System.out.println("Multiplication: " + (num1 * num2));
                    break;

                case 4:
                    if (num2 != 0) {
                        System.out.println("Division: " + (num1 / num2));
                    } else {
                        System.out.println("Cannot divide by zero");
                    }
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

            System.out.println("Do you want to continue? (true/false)");
            choice = sc.nextBoolean();

        } while (choice);

        sc.close();
    }
}