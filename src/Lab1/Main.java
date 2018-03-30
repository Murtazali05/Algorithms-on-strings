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
        System.out.println("Lead time: " + (finish_time - start_time));
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

        BlocksString blocksString = new BlocksString();
        start_time = System.currentTimeMillis();
        answer = blocksString.searchSubstring(pattern, sample);
        finish_time = System.currentTimeMillis();

        System.out.println("\nMETHOD ARRAYS BLOCKS");
        System.out.println("Lead time: " + (finish_time - start_time));
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

        System.out.print("\n\nEnter the length of the text from the same characters: ");
        sample = TestGenerator.generateTest(in.nextLong(), 'a');
        System.out.print("Enter the length of the pattern from the same characters: ");
        pattern = TestGenerator.generateTest(in.nextLong(), 'a');
        run(pattern, sample);

        System.out.print("\n\nEnter the length of the text from the same characters (a): ");
        sample = TestGenerator.generateTest(in.nextLong(), 'a');
        pattern = "aaaabaa";
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        System.out.print("\n\nEnter the length of the text from the same characters (a) with the (b) symbol in the middle: ");
        long dim = in.nextLong();
        sample = TestGenerator.generateTest(dim, 'a', dim/2, 'b');
        pattern = "aaaaaaa";
        if (dim < 10000) System.out.println("Text: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        System.out.print("\n\nEnter the length of the DNA for the text: ");
        sample = TestGenerator.generatorDNA(in.nextLong());
        System.out.print("Enter the length of the DNA for the pattern: ");
        pattern = TestGenerator.generatorDNA(in.nextLong());
        run(pattern, sample);

    }
}