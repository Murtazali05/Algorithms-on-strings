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
        sample = in.next();
        pattern = in.next();

        BorderString borderString = new BorderString();
        ArrayList<Integer> answer = borderString.maxBorderArray(sample, pattern);

        for (int i = 0; i < answer.size(); i++) {
            System.out.print((answer.get(i) + 1) + " ");
        }
    }
}

