package Lab4;

import Lab1.tests.TestGenerator;

import java.util.List;

public class Main {

    private static void run(String pattern, String sample) {
        long start_time, finish_time;
        StringSearchKR stringSearchKR = new StringSearchKR();
        start_time = System.currentTimeMillis();
        List<Integer> answer = stringSearchKR.search(pattern, sample);
        finish_time = System.currentTimeMillis();

        System.out.println("\nKARP-RABIN");
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

    }

    public static void main(String[] args) {

        String sample, pattern;
        long lengthPattern, lengthText;

        sample = "abbabaabbaababba";
        pattern = "ba";
        System.out.println("\nText: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        sample = "abababababaaba";
        pattern = "aba";
        System.out.println("\nText: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        sample = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        pattern = "aaa";
        System.out.println("\nText: " + sample);
        System.out.println("Pattern: " + pattern);
        run(pattern, sample);

        lengthText = 2000000;
        lengthPattern = 100000;
        System.out.println("\n\nThe length of the text from the same characters: " + lengthText);
        sample = TestGenerator.generateTest(lengthText, 'a');
        System.out.println("The length of the pattern from the same characters: " + lengthPattern);
        pattern = TestGenerator.generateTest(lengthPattern, 'a');
        run(pattern, sample);

        lengthText = 2000000;
        System.out.println("\nThe length of the text from the same characters (a): " + lengthText);
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
        System.out.println("The length of the DNA for the pattern: " + lengthPattern);
        pattern = TestGenerator.generatorDNA(lengthPattern);
        run(pattern, sample);

    }
}
