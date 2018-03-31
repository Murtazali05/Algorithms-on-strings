package Lab2;

import java.util.ArrayList;

public class StringSearch {
    private int br[];

    private void maxBorderArray(String str) {
        int n = str.length();

        br[0] = -1;

        for (int i = 0; i < (n - 1); i++) {
            int tmp = br[i];

            while ((tmp > -1) && (str.charAt(i + 1) != str.charAt(tmp + 1))) tmp = br[tmp];

            if (str.charAt(i + 1) == str.charAt(tmp + 1))
                br[i + 1] = tmp + 1;
            else
                br[i + 1] = -1;
        }

        for (int i = 0; i < br.length; i++) br[i] = br[i] + 1;
    }

    public ArrayList<Integer> KMP(String pattern, String sample){
        int n = sample.length();
        int m = pattern.length();

        br = new int[m];
        maxBorderArray(pattern);

        int indexP = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (indexP > 0 && (pattern.charAt(indexP) != sample.charAt(i))) indexP = br[indexP - 1];

            if (pattern.charAt(indexP) == sample.charAt(i)) indexP += 1;

            if (indexP == m) {
                answer.add(i - m + 2);
                indexP = br[indexP - 1];
            }
        }

        return answer;
    }

    private ArrayList<Integer> toBrs(String str) {
        int n = str.length();
        ArrayList<Integer> brs = new ArrayList<>(n);
        brs.add(0);

        for (int i = 1; i < n - 1; i++) {

            if (br[i] > 0 && (str.charAt(br[i]) == str.charAt(i + 1)))
                brs.set(i, brs.get(br[i] - 1));
            else
                brs.set(i, br[i]);
        }
        brs.add(n - 1, br[n - 1]);

        return brs;
    }

    public ArrayList<Integer> modifiedKMP(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        maxBorderArray(pattern);
        ArrayList<Integer> brs = toBrs(pattern);
        for (int i = 0; i<pattern.length(); i++){
            System.out.println(br[i] + " " + brs.get(i));
        }
        int indexP = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (indexP > 0 && (pattern.charAt(indexP) != sample.charAt(i))) indexP = brs.get(indexP - 1);

            if (pattern.charAt(indexP) == sample.charAt(i)) indexP += 1;

            if (indexP == m) {
                answer.add(i - m + 2);
                indexP = brs.get(indexP - 1);
            }
        }

        return answer;
    }
}
