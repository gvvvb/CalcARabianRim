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
            throw new IllegalArgumentException("Ошибка: " + e.getMessage());
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

        if (!isValidInput(firstNumber, operator, secondNumber)) {
            throw new IllegalArgumentException("Некорректные данные");
        }

        // Проверка нотаций
        if ((isRomanNumber(firstNumber) && !isRomanNumber(secondNumber)) ||
                (!isRomanNumber(firstNumber) && isRomanNumber(secondNumber))) {
            throw new IllegalArgumentException("Нельзя сочетать римские и арабские числа");
        }
        int firstNumberCorp = isRomanNumber(firstNumber) ? Roman.convert(firstNumber) : Integer.parseInt(firstNumber);
        int secondNumberCorp = isRomanNumber(secondNumber) ? Roman.convert(secondNumber) : Integer.parseInt(secondNumber);
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
                if (secondNumberCorp == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                result = firstNumberCorp / secondNumberCorp;
                break;
            default:
                throw new IllegalArgumentException("Неправильный оператор");
        }
        if (isRomanNumber(firstNumber) && isRomanNumber(secondNumber)) {
            if (result <= 0 || result >= 3990) {
                throw new IllegalArgumentException("Результат в римской нотации должен быть в диапазоне I до MMMCMXCIX");
            }
            return Roman.toRoman(result);
        } else {
            if (result < 1 || result > 10) {
                throw new IllegalArgumentException("Результат должен быть в диапазоне от 1 до 10");
            }
            return String.valueOf(result);
        }
    }
    public static boolean isValidInput(String firstNumber, String operator, String secondNumber) {
        return (isRomanNumber(firstNumber) || isInteger(firstNumber)) &&
                (isRomanNumber(secondNumber) || isInteger(secondNumber)) &&
                "+".equals(operator) || "-".equals(operator) || "*".equals(operator) || "/".equals(operator);
    }
    public static boolean isInteger(String input) {
        try {
            int number = Integer.parseInt(input);
            return number >= 1 && number <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isRomanNumber(String number) {
        return number.matches("^[IVXLCDM]*$");
    }
}
//Пожалуйста, скажите где мыло
