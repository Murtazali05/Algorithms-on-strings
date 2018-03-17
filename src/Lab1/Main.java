package Lab1;

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
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String sample, pattern;
        long start_time, finish_time;
        sample = in.next();
        pattern = in.next();

        BorderString borderString = new BorderString();
        start_time = System.nanoTime();
        ArrayList<Integer> answer = borderString.searchSubstring(pattern, sample);
        finish_time = System.nanoTime();

        System.out.println("METHOD ARRAYS BORDER");
        System.out.println("Lead time: " + (finish_time - start_time));
        if (answer.isEmpty())
            System.out.println("No occurrences of substring in string found!");
        else {
            System.out.print("Positions of occurrences:");
            for (int i = 0; i < answer.size(); i++) {
                System.out.print(" " + answer.get(i));
            }
            System.out.println();
        }

        BlocksString blocksString = new BlocksString();
        start_time = System.nanoTime();
        answer = blocksString.searchSubstring(pattern, sample);
        finish_time = System.nanoTime();

        System.out.println("\nMETHOD ARRAYS BLOKS");
        System.out.println("Lead time: " + (finish_time - start_time));
        if (answer.isEmpty())
            System.out.println("No occurrences of substring in string found!");
        else {
            System.out.print("Positions of occurrences:");
            for (int i = 0; i < answer.size(); i++) {
                System.out.print(" " + answer.get(i));
            }
        }
    }
}
/*
Примеры:
abaabaab
aba

abbabaabbaababba
ba
*/

