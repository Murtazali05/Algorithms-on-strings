package Lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GoodSuffixBM {

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

    private int[] suffixBorderArray(String str) {
        int n = str.length(), bsLefth;
        int bs[] = new int[n];

        bs[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            bsLefth = bs[i + 1];

            while ((bsLefth > 0) && (str.charAt(i) != str.charAt(n - bsLefth - 1))) bsLefth = bs[n - bsLefth];

            if (str.charAt(i) == str.charAt(n - bsLefth - 1))
                bs[i] = bsLefth + 1;
            else
                bs[i] = 0;
        }

        return bs;
    }

    private int[] strongSuffixBorderArray(String str) {
        int bs[] = suffixBorderArray(str);
        int m = str.length();
        int bst[] = new int[m];

        bst[m - 1] = 0;
        bst[0] = bs[0];

        for (int i = m - 2; i > 0; i--) {
            if ((bs[i] > 0) && (bs[i] + 1 == bs[i - 1]))
                bst[i] = bst[m - bs[i]];
            else
                bst[i] = bs[i];
        }

        return bst;
    }

    private int[] positionGoodSuffix(String str) {
        int bst[] = strongSuffixBorderArray(str);
        int m = str.length();
        int posBorderSuffix[] = new int[m];

        for (int i = 0; i < m; i++) posBorderSuffix[i] = 0;

        for (int j = 0; j < m - 1; j++) {
            if (bst[j] > 0) {
                int k = m - bst[j];
                posBorderSuffix[k] = j + 1;
            }
        }

        return posBorderSuffix;
    }

    private LinkedList<Integer>[] buildBadSymbolMatrix(String pattern, String sample) {
        int ASCII = 127;
        int m = pattern.length();


        LinkedList<Integer> bs[] = new LinkedList[ASCII]; // для каждого символа список позиций вхождения в паттерн

        for (int i = 0; i < ASCII; i++) bs[i] = new LinkedList<>();

        for (int i = m - 1; i >= 0; i--)
            bs[(int) pattern.charAt(i)].add(i);

        return bs;
    }


    private int shiftBS(LinkedList<Integer>[] p_list, char bad_char, int bad_pos) {
        if (bad_pos < 0) return 1;
        int r_shift = -1;

        List<Integer> bad_list = p_list[(int) bad_char];

        if (!bad_list.isEmpty()) {
            for (int item : bad_list) {
                if (item < bad_pos) {
                    r_shift = item;
                    break;
                }
            }
        }

        return bad_pos - r_shift;
    }

    private int shiftGS(int[] posGS, int[] br, int bad_pos, int m) {
        if (bad_pos == m - 1) return 1;
        if (bad_pos < 0) return m - br[0];

        int copyPos = posGS[bad_pos + 1], shift;

        if (copyPos >= 0)
            shift = bad_pos + 1 - copyPos;
        else
            shift = m - br[bad_pos + 1];

        return shift;

    }

    public List<Integer> goodSuffixBM(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        int borderArray[] = maxBorderArray(pattern);
        LinkedList<Integer> badSymbolArray[] = buildBadSymbolMatrix(pattern, sample);

        int posGoodSuffix[] = positionGoodSuffix(pattern);

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
                int shift = Math.max(shiftBS(badSymbolArray, sample.charAt(i + j), j), shiftGS(posGoodSuffix, borderArray, j, m));
                i += shift;

                jump = 0;
            }
        }

        return answer;

    }

    public List<Integer> goodSuffixxxxBM(String pattern, String sample) {
        int n = sample.length();
        int m = pattern.length();

        int borderArray[] = maxBorderArray(pattern);
        LinkedList<Integer> badSymbolArray[] = buildBadSymbolMatrix(pattern, sample);

        int posGoodSuffix[] = positionGoodSuffix(pattern);

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
                int shift = 1;
                jump = 0;

                if (j == m - 1) shift = 1;
                else {
                    if (j < 0)
                        shift = m - borderArray[0];
                    else {
                        int copyPos = posGoodSuffix[j + 1];

                        if (copyPos >= 0)
                            shift = j + 1 - copyPos;
                        else {
                            shift = m - borderArray[j + 1];
                            jump = shift;
                        }

                        int tmp = shiftBS(badSymbolArray, sample.charAt(i + j), j);
                        if (tmp > shift) {
                            shift = tmp;
                            jump = 0;
                        }
                    }
                }

                i += shift;

            }
        }

        return answer;

    }

    private int[] borderRightSuffix(String str) {
        int br[] = maxBorderArray(str);
        int left = 0, m = str.length();
        int brSuff[] = new int[m];

        int currentBorder = br[m - 1];

        for (int i = 0; i < m; i++) {
            if ((i > left) && (i < m - currentBorder))
                brSuff[i] = currentBorder;
            if (i == m - currentBorder - 1) {
                left = m - currentBorder - 1;
                currentBorder = br[currentBorder];
            }

        }

        return brSuff;
    }

    public List<Integer> goodSuffixJumpBM(String pattern, String sample) {
        int n = sample.length(), m = pattern.length();
        int v, q;

        int borderArray[] = maxBorderArray(pattern);
        int posGoodSuffix[] = positionGoodSuffix(pattern);
        int borderArraySuffix[] = borderRightSuffix(pattern);

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
                v = 1;

                if (j != m - 1) {

                    if (posGoodSuffix[j + 1] == 0)
                        v = 1;
                    else
                        v = j + 1 - posGoodSuffix[j + 1];
                }

                q = v;//Math.max(v, j - borderArraySuffix[i + j]);

                i += q;

                if (j != m - 1) {
                    if (q == m - borderArraySuffix[j + 1])
                        jump = q;
                    else
                        jump = 0;
                } else jump = 0;
            }
        }

        return answer;
    }

}

