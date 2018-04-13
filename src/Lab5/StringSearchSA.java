package Lab5;

import java.util.ArrayList;
import java.util.List;

public class StringSearchSA {

    public List<Integer> search(String pattern, String sample) {

        int n = sample.length();
        int m = pattern.length();

        int ASCII = 127;

        List<Integer> answer = new ArrayList<>();

        long vector[] = new long[ASCII];

        for (int i = 0; i < ASCII; i++) vector[i] = 0;

        for (int i = 0; i < m; i++)
            vector[(int) pattern.charAt(i)] |= 1 << (m - 1 - i);

        long uHigh = 1 << (m - 1);
        long M = 0;

        for (int i = 0; i < n; i++) {
            M = (M >> 1 | uHigh) & vector[(int) sample.charAt(i)];

            if ((M & 1) > 0)
                answer.add(i - m + 2);
        }

        return answer;
    }
}