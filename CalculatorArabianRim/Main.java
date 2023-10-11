package CalculatorArabianRim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите пример разделяя символы пробелом: ");
        String input = scan.nextLine();

        try {
            String output = calc(input);
            System.out.println("Ответ: " + output);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Ждем, пока пользователь нажмет Enter
        System.out.println("Для завершения нажмите Enter...");
        scan.nextLine();
    }

    public static String calc(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неправильный формат ввода");
        }

        String firstNumber = parts[0];
        String operator = parts[1];
        String secondNumber = parts[2];

        boolean isRomanInput = isRomanNumber(firstNumber) && isRomanNumber(secondNumber);
        int firstNumberCorp = isRomanInput ? Roman.convert(firstNumber) : Integer.parseInt(firstNumber);
        int secondNumberCorp = isRomanInput ? Roman.convert(secondNumber) : Integer.parseInt(secondNumber);

        if (!isValidInput(firstNumberCorp, operator, secondNumberCorp)) {
            throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
        }

        int result;
        switch (operator) {
            case "+":
                result = firstNumberCorp + secondNumberCorp;
                break;
            case "-":
                result = firstNumberCorp - secondNumberCorp;
                break;
            case "*":
                result = firstNumberCorp * secondNumberCorp;
                break;
            case "/":
                if (secondNumberCorp == 0 || "0".equals(secondNumber)) {
                    throw new ArithmeticException("Деление на ноль");
                }
                result = firstNumberCorp / secondNumberCorp;
                break;
            default:
                throw new IllegalArgumentException("Неправильный оператор");
        }

        if (isRomanInput) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат должен быть положительным римским числом.");
            }
            if (result >= 3990) {
                throw new IllegalArgumentException("Результат превышает 3990 в римской нотации.");
            }
            return Roman.toRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    public static boolean isValidInput(int firstNumber, String operator, int secondNumber) {
        return firstNumber >= 1 && firstNumber <= 10 && secondNumber >= 1 && secondNumber <= 10 && isOperatorValid(operator);
    }

    public static boolean isOperatorValid(String operator) {
        return "+".equals(operator) || "-".equals(operator) || "*".equals(operator) || "/".equals(operator);
    }

    public static boolean isRomanNumber(String number) {
        return number.matches("^[IVXLCDM]*$");
    }
}
//Пожалуйста, скажите где мыло
