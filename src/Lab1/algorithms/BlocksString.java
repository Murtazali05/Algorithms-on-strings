package Lab1.algorithms;

import java.util.ArrayList;

public class BlocksString {
    private int block[];

    private String joinRows(String s1, String s2) {
        return s1 + "$" + s2;
    }

    private int cmp(String str, int pos1, int pos2) {
        int n = str.length(), dist;

        if (pos1 >= n || pos2 >= n) return 0;

        dist = (n - pos1 < n - pos2) ? (n - pos1) : (n - pos2);
        int j = 0;
        while (j < dist && str.charAt(pos1 + j) == str.charAt(pos2 + j)) j++;

        return j;
    }

    private int cmp2(String str, int pos1, int pos2) {
        int n = str.length(), dist;

        int eqLen = 0;
        while (pos1 < n && pos2 < n && str.charAt(pos1++) == str.charAt(pos2++)) ++eqLen;

        return eqLen;
    }

    private void blocArrays(String str) {
        int n = str.length();
        int left = 0;
        int right = 0;
        block[0] = 0;
        for (int i = 1; i < n; i++) {
            block[i] = 0;
            if (i >= right) {
                block[i] = cmp2(str, 0, i);

                if (block[i] > 0) {
                    right = i + block[i];
                    left = i;
                }
            } else {
                int k = i - left;

                if (block[k] < right - i)
                    block[i] = block[k];
                else {
                    block[i] = right - i;
                    left = i;
                    int complement = cmp2(str, right - i, right);
                    if (complement > 0){
                        block[i] = block[i] + complement;
                        right = i + block[i];
                    }
                }
            }
        }
    }

    public ArrayList<Integer> searchSubstring(String pattern, String sample){
        String str = joinRows(pattern, sample);

        block = new int[str.length()];
        blocArrays(str);

        int lengthPattern = pattern.length();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < block.length; i++) {
            if (block[i] == lengthPattern)
                result.add(i - lengthPattern);
        }

        return result;
    }
}
