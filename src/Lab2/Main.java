package Lab2;

import Lab1.tests.TestGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static void run(String pattern, String sample) {
        long start_time, finish_time;
        StringSearch stringSearch = new StringSearch();
        start_time = System.currentTimeMillis();
        ArrayList<Integer> answer = stringSearch.KMP(pattern, sample);
        finish_time = System.currentTimeMillis();

        System.out.println("\nMETHOD KMP");
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


//        start_time = System.currentTimeMillis();
//        answer = stringSearch.modifiedKMP(pattern, sample);
//        finish_time = System.currentTimeMillis();
//
//        System.out.println("\nMETHOD Modified KMP");
//        System.out.println("Lead time: " + (finish_time - start_time));
//        if (answer.isEmpty())
//            System.out.println("No occurrences of substring in string found!");
//        else {
//            System.out.println("Number of occurrences: " + answer.size());
//            if (answer.size() < 1000) {
//                System.out.print("Positions of occurrences: ");
//                for (int i = 0; i < answer.size(); i++) {
//                    System.out.print(" " + answer.get(i));
//                }
//            }
//            System.out.println();
//        }

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
        lengthPattern = 100000;
        System.out.println("\n\nThe length of the text from the same characters: " + lengthText);
        sample = TestGenerator.generateTest(lengthText, 'a');
        System.out.print("The length of the pattern from the same characters: " + lengthPattern);
        pattern = TestGenerator.generateTest(lengthPattern, 'a');
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
