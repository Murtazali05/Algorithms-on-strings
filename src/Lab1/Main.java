package Lab1;

import Lab1.algorithms.BlocksString;
import Lab1.algorithms.BorderString;
import Lab1.tests.TestGenerator;

import java.util.ArrayList;
import java.util.Scanner;

/*
* Рабадангаджиев М.М. 8 группа
*
* Разработать программу поиска паттерна P в тексте T с использованием метода вычисления массива граней префиксов
* --//---  с использованием метода вычисления массива блоков
* Экспериментально оценить время работы для предельно больщих строк
* */
public class Main {

    private static void run(String pattern, String sample) {
        long start_time, finish_time;
        BorderString borderString = new BorderString();
        start_time = System.currentTimeMillis();
        ArrayList<Integer> answer = borderString.searchSubstring(pattern, sample);
        finish_time = System.currentTimeMillis();

        System.out.println("\nMETHOD ARRAYS BORDER");
        System.out.println("Lead time: " + (finish_time - start_time) + " ms");
        if (answer.isEmpty())
            System.out.println("No occurrences of substring in string found!");
        else {
            System.out.println("Number of occurrences: " + answer.size());
            if (answer.size() < 1000) {
                System.out.print("Positions of occurrences: ");
                for (int i = 0; i < answer.size(); i++) {
                    System.out.print(" " + answer.get(i));
                }
            }
            System.out.println();
        }

        answer = null;
        BlocksString blocksString = new BlocksString();
        start_time = System.currentTimeMillis();
        answer = blocksString.searchSubstring(pattern, sample);
        finish_time = System.currentTimeMillis();

        System.out.println("\nMETHOD ARRAYS BLOCKS");
        System.out.println("Lead time: " + (finish_time - start_time) + " ms");
        if (answer.isEmpty())
            System.out.println("No occurrences of substring in string found!");
        else {
            System.out.println("Number of occurrences: " + answer.size());
            if (answer.size() < 1000) {
                System.out.print("Positions of occurrences: ");
                for (int i = 0; i < answer.size(); i++) {
                    System.out.print(" " + answer.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String sample, pattern;
        long lengthPattern, lengthText;

        sample = "abbabaabbaababba";
        pattern = "ba";
        System.out.println("Text: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        sample = "abababababaaba";
        pattern = "aba";
        System.out.println("\nText: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        lengthText = 20000000;
        lengthPattern = 1000000;
        System.out.println("\n\nThe length of the text from the same characters: " + lengthText);
        sample = TestGenerator.generateTest(lengthText, 'a');
        System.out.println("The length of the pattern from the same characters: " + lengthPattern);
        pattern = TestGenerator.generateTest(lengthPattern, 'a');
        run(pattern, sample);

        lengthText = 2000000;
        System.out.println("\n\nThe length of the text from the same characters (a): " + lengthText);
        sample = TestGenerator.generateTest(lengthText, 'a');
        pattern = "aaaabaa";
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        lengthText = 2000000;
        System.out.println("\n\nThe length of the text from the same characters (a) with the (b) symbol in the middle: " + lengthText);
        sample = TestGenerator.generateTest(lengthText, 'a', lengthText/2, 'b');
        pattern = "aaaaaaa";
        if (lengthText < 10000) System.out.println("Text: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        lengthText = 10000000;
        lengthPattern = 9;
        System.out.println("\n\nThe length of the DNA for the text: " + lengthText);
        sample = TestGenerator.generatorDNA(lengthText);
        System.out.print("The length of the DNA for the pattern: " + lengthPattern);
        pattern = TestGenerator.generatorDNA(lengthPattern);
        run(pattern, sample);

    }
}