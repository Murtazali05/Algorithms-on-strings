package Lab1.tests;

import java.util.Random;

public class TestGenerator {
    private static String symbols = "ACGT";

    public static String generatorDNA(long length){
        StringBuilder randString = new StringBuilder();
        Random random = new Random();

        for (long i = 0; i<length; i++){
            randString.append(symbols.charAt(random.nextInt(symbols.length())));
        }

        return randString.toString();
    }

    public static String generateTest(long length, char symbol){
        StringBuilder line = new StringBuilder();
        for (long i = 0; i < length; i++)
            line.append(symbol);

        return line.toString();
    }

    public static String generateTest(long length, char symbol, long pos_other_symbol, char other_symbol){
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < pos_other_symbol; i++)
            line.append(symbol);
        line.append(other_symbol);
        for (long i = pos_other_symbol + 1; i < length; i++)
            line.append(symbol);
        return line.toString();
    }
}
