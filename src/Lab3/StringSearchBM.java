package Lab3;

import java.util.ArrayList;
import java.util.List;

public class StringSearchBM {

    private int[] maxBorderArray(String str) {
        int n = str.length();
        int borderArray[] = new int[n];

        borderArray[0] = -1;

        for (int i = 0; i < (n - 1); i++) {
            int tmp = borderArray[i];

            while ((tmp > -1) && (str.charAt(i + 1) != str.charAt(tmp + 1))) tmp = borderArray[tmp];

            if (str.charAt(i + 1) == str.charAt(tmp + 1))
                borderArray[i + 1] = tmp + 1;
            else
                borderArray[i + 1] = -1;
        }

        for (int i = 0; i < borderArray.length; i++) borderArray[i] += 1;

        return borderArray;
    }

    private int[] buildBadSymbolArray(String pattern){
        int ASCII = 127;
        int m = pattern.length();
        int bs[] = new int[ASCII];
        for (int i = 0; i<ASCII; i++) bs[i] = 0;

        for (int i = 0; i<m; i++) bs[(int)pattern.charAt(i)] = i;

        return bs;
    }

    public List<Integer> badSymbolBM(String pattern, String sample){
        int n = sample.length();
        int m = pattern.length();

        int badSymbolArray[] = buildBadSymbolArray(pattern);

        List<Integer> answer = new ArrayList<>();

        int i = 0, j;
        while (i < n - m + 1) {
            j = m - 1;

            while ((j >= 0) && (pattern.charAt(j) == sample.charAt(i + j))) j--;

            if (j == -1) {
                answer.add(i + 1);
                i++;
            } else {
                i += Math.max(1, j - badSymbolArray[(int) sample.charAt(i + j)]);
            }
        }

        return answer;
    }

    public List<Integer> badSymbolJumpBM(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        int borderArray[] = maxBorderArray(pattern);
        int badSymbolArray[] = buildBadSymbolArray(pattern);

        List<Integer> answer = new ArrayList<>();

        int i = 0, j;
        int jump = 0;
        while (i < n - m + 1) {
            j = m - 1;

            while ((j >= jump) && (pattern.charAt(j) == sample.charAt(i + j))) j--;

            if (j == jump - 1) {
                answer.add(i + 1);
                jump = m - borderArray[m - 1] - 1;
                i += jump + 1;
            } else {
                i += Math.max(1, j - badSymbolArray[(int) sample.charAt(i + j)]);
                jump = 0;
            }
        }

        return answer;

    }

    public List<Integer> extendBdSymbolJumpBM(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        int borderArray[] = maxBorderArray(pattern);
        ArrayList<Integer> badSymbolArray[] = buildBadSymbolMatrix(pattern, sample);

        List<Integer> answer = new ArrayList<>();

        int i = 0, j;
        int jump = 0;
        while (i < n - m + 1) {
            j = m - 1;

            while ((j >= jump) && (pattern.charAt(j) == sample.charAt(i + j))) j--;

            if (j == jump - 1) {
                answer.add(i + 1);
                jump = m - borderArray[m - 1] - 1;
                i += jump + 1;
            } else {
                i += shift(badSymbolArray, sample.charAt(i + j), j);
                jump = 0;
            }
        }

        return answer;

    }

    int shift(ArrayList<Integer>[] p_list, char bad_char, int bad_pos) {
        if (bad_pos < 0) return 1;
        int r_shift = -1;

        ArrayList<Integer> bad_list = p_list[(int)bad_char];

        if (!bad_list.isEmpty()){
            for (int item : bad_list){
                if (item < bad_pos){
                    r_shift = item;
                    break;
                }
            }
        }

        return bad_pos - r_shift;
    }

    private ArrayList<Integer>[] buildBadSymbolMatrix(String pattern, String sample){
        int ASCII = 127;
        int m = pattern.length();


        ArrayList<Integer> bs[] = new ArrayList[ASCII];

        for (int i = 0; i<ASCII; i++) bs[i] = new ArrayList<Integer>();

        for (int i = m - 1; i>=0; i--)
            bs[(int)pattern.charAt(i)].add(i);

        return bs;
    }

}
