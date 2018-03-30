package Lab1;

import Lab1.algorithms.BlocksString;
import Lab1.algorithms.BorderString;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSearch {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        long start_time, finish_time;
        String sample = "", pattern, file_name = "src\\Lab1\\tests\\sample.txt";
        Scanner fin = new Scanner(new FileReader(file_name));
        while(fin.hasNextLine()){
            sample += fin.nextLine();
        }
        System.out.println("The read file is " + file_name + " with a length of " + sample.length());

        System.out.println("Enter pattern: ");
        pattern = in.next();

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
            if (answer.size() < 100) {
                System.out.print("Positions of occurrences:");
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
            if (answer.size() < 100) {
                System.out.print("Positions of occurrences:");
                for (int i = 0; i < answer.size(); i++) {
                    System.out.print(" " + answer.get(i));
                }
            }
        }
    }

}

