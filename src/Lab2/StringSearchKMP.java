package Lab2;

import java.util.ArrayList;

public class StringSearchKMP {
    private int borderArray[];

    private void maxBorderArray(String str) {
        int n = str.length();

        borderArray = new int[n];
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

    }

    public ArrayList<Integer> KMP(String pattern, String sample){
        int n = sample.length();
        int m = pattern.length();

        maxBorderArray(pattern);

        int indexP = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (indexP > 0 && (pattern.charAt(indexP) != sample.charAt(i))) indexP = borderArray[indexP - 1];

            if (pattern.charAt(indexP) == sample.charAt(i)) indexP += 1;

            if (indexP == m) {
                answer.add(i - m + 2);
                indexP = borderArray[indexP - 1];
            }
        }

        return answer;
    }

    private int[] modifyBorderArray(String str) {
        maxBorderArray(str);
        int m = str.length();
        int brs[] = new int[m];

        brs[0] = 0;
        brs[m - 1] = borderArray[m - 1];

        for (int i = 1; i < m - 1; i++) {
            if (borderArray[i] > 0 && (borderArray[i] + 1 == borderArray[i + 1]))
                brs[i] = brs[borderArray[i] - 1];
            else
                brs[i] = borderArray[i];
        }
        return brs;
    }

    public ArrayList<Integer> modifiedKMP(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        int brs[] = modifyBorderArray(pattern);

        int indexP = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (indexP > 0 && (pattern.charAt(indexP) != sample.charAt(i))) indexP = brs[indexP - 1];

            if (pattern.charAt(indexP) == sample.charAt(i)) indexP += 1;

            if (indexP == m) {
                answer.add(i - m + 2);
                indexP = brs[indexP - 1];
            }
        }

        return answer;
    }

    private int[][] modifyBorderMatrix(String str) {
        int ASCII = 127;
        int m = str.length(), borderValue;

        int brs[] = modifyBorderArray(str);

        int borderMatrix[][] = new int[m][ASCII];

        for (int i = 0; i < m; i++) {
            borderValue = brs[i];
            borderMatrix[i][(int)str.charAt(borderValue)] = borderValue;
        }

        return borderMatrix;
    }

    public ArrayList<Integer> onlineModifiedKMP(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        int borderMatrix[][] = modifyBorderMatrix(pattern);

        int indexP = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (indexP > 0 && (pattern.charAt(indexP) != sample.charAt(i)))
                indexP = borderMatrix[indexP - 1][(int)sample.charAt(i)];

            if (pattern.charAt(indexP) == sample.charAt(i)) indexP += 1;


            if (indexP == m) {
                answer.add(i - m + 2);
                if (i < n - 1)
                    indexP = borderMatrix[indexP - 1][(int) sample.charAt(i + 1)];
            }
        }

        return answer;
    }
}
