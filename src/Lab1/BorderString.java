package Lab1;

import java.util.ArrayList;

public class BorderString {

    private String joinRows(String s1, String s2) {
        return s1 + "$" + s2;
    }

    public ArrayList<Integer> maxBorderArray(String sample, String pattern) {
        String line = this.joinRows(pattern, sample);
        int n = line.length();
        ArrayList<Integer> border = new ArrayList<Integer>();
        border.add(-1);

        for (int i = 0; i < (n - 1); i++) {
            int tmp = border.get(i);

            while ((tmp > -1) && (line.charAt(i + 1) != line.charAt(tmp + 1))) tmp = border.get(tmp);

            if (line.charAt(i + 1) == line.charAt(tmp + 1))
                border.add(i + 1, tmp + 1);
            else
                border.add(i + 1, -1);
        }

        return border;
    }
}
