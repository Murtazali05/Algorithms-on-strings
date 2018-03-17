package Lab1;

import java.util.ArrayList;

public class BorderString {
    private ArrayList<Integer> border;

    public BorderString() {
        border = new ArrayList<Integer>();
    }

    private String joinRows(String s1, String s2) {
        return s1 + "$" + s2;
    }

    private void maxBorderArray(String str) {
        int n = str.length();

        border.add(-1);

        for (int i = 0; i < (n - 1); i++) {
            int tmp = border.get(i);

            while ((tmp > -1) && (str.charAt(i + 1) != str.charAt(tmp + 1))) tmp = border.get(tmp);

            if (str.charAt(i + 1) == str.charAt(tmp + 1))
                border.add(i + 1, tmp + 1);
            else
                border.add(i + 1, -1);
        }

    }

    public ArrayList<Integer> searchSubstring(String pattern, String sample) {

        String str = joinRows(pattern, sample);

        maxBorderArray(str);

        int lengthPattern = pattern.length();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < border.size(); i++) {
            if ((border.get(i) + 1) == lengthPattern)
                result.add(i - lengthPattern - lengthPattern + 1);
        }

        return result;
    }

}
