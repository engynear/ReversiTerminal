package com.engynear.io;

import java.util.Scanner;

public class InputHandler {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean getYNInput() {
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            return true;
        } else if (input.equals("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter Y or N.");
            return getYNInput();
        }
    }

    public static int getMenuInput(int length) {
        //regex numbers
        String input = scanner.nextLine();
        if (input.matches("[0-9]+")) {
            int inputInt = Integer.parseInt(input);
            if (inputInt > 0 && inputInt <= length) {
                return inputInt;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + length);
                return getMenuInput(length);
            }
        }
        System.out.println("Invalid input. Please enter a number between 1 and " + length);
        return getMenuInput(length);
    }

    public static String getNonEmptyString() {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a non-empty string.");
            return getNonEmptyString();
        }
        return input;
    }

    public static int getMoveInput(int length) {
        String input = scanner.nextLine();
        if (input.matches("[-]?[0-9]+")) {
            int inputInt = Integer.parseInt(input);
            if (inputInt == -1 || (inputInt > 0 && inputInt <= length)) {
                return inputInt;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + length + " or -1 to exit.");
                return getMoveInput(length);
            }
        }
        return getMoveInput(length);
    }
}
