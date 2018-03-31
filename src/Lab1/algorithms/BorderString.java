package Lab1.algorithms;

import java.util.ArrayList;

public class BorderString {
    private int borders[];

    private String joinRows(String s1, String s2) {
        return s1 + "$" + s2;
    }

    private void maxBorderArray(String str) {
        int n = str.length();

        borders[0] = -1;

        for (int i = 0; i < (n - 1); i++) {
            int tmp = borders[i];

            while ((tmp > -1) && (str.charAt(i + 1) != str.charAt(tmp + 1))) tmp = borders[tmp];

            if (str.charAt(i + 1) == str.charAt(tmp + 1))
                borders[i + 1] = tmp + 1;
            else
                borders[i + 1] = -1;
        }

    }

    public ArrayList<Integer> searchSubstring(String pattern, String sample) {

        String str = joinRows(pattern, sample);

        borders = new int[str.length()];

        maxBorderArray(str);

        int lengthPattern = pattern.length();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < borders.length; i++) {
            if ((borders[i] + 1) == lengthPattern)
                result.add(i - lengthPattern - lengthPattern + 1);
        }

        return result;
    }

}
