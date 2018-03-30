package Lab1.algorithms;

import java.util.ArrayList;

public class BlocksString {
    private ArrayList<Integer> block;

    public BlocksString() {
        block = new ArrayList<>();
    }

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

    private void blocArrays(String str) {
        int n = str.length();
        int left = 0;
        int right = 0;
        block.add(0);
        for (int i = 1; i < n; i++) {
            block.add(i, 0);
            if (i > right) {
                block.set(i, cmp(str, 0, i));

                if (block.get(i) > 0) {
                    right = i + block.get(i) - 1;
                    left = i;
                }
            } else {
                int k = i - left;

                if (block.get(k) < right - i + 1)
                    block.set(i, block.get(k));
                else {
                    block.set(i, right - i + 1);
                    left = i;
                    int complement = cmp(str, right - i + 1, right + 1);
                    if (complement > 0){
                        block.set(i, block.get(i) + complement);
                        right = i + block.get(i) - 1;
                    }
                }
            }
        }
    }

    public ArrayList<Integer> searchSubstring(String pattern, String sample){
        String str = joinRows(pattern, sample);
        blocArrays(str);

        int lengthPattern = pattern.length();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < block.size(); i++) {
            if (block.get(i) == lengthPattern)
                result.add(i - lengthPattern);
        }

        return result;
    }
}
