package Lab4;

import java.util.ArrayList;
import java.util.List;

public class StringSearchKR {

    private final int POWER = 53;  // простое число примерно равное мощности входного алфавита
    private final long q = 2147483647;


    private long getHash(String str, int m, long q){
        long hash = 0;

        for (int i = 0; i < m; i++)
            hash = (hash * POWER + (int)str.charAt(i)) % q;

        return hash;
    }

    private long REHASH(long hash, long factor, char a, char b){
        return ((hash - (int)a * factor) * POWER + (int)b) % q;
    }

    private boolean compare(String pattern, String sample, int pos){
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != sample.charAt(pos + i))
                return false;
        }
        return true;
    }

    public List<Integer> search(String pattern, String sample) {

        int n = sample.length();
        int m = pattern.length();

        List<Integer> answer = new ArrayList<>();

        long factor = 1;
        for (int i = 0; i < m - 1; i++)
            factor = (factor * POWER) % q;

        long patternHash = getHash(pattern, m, q);
        long sampleHash = getHash(sample, m, q);

        for (int j = 0; j < n - m; j++) {

            if (patternHash == sampleHash && compare(pattern, sample, j))
                answer.add(j + 1);

            sampleHash = REHASH(sampleHash, factor, sample.charAt(j), sample.charAt(j + m));

            if (sampleHash < 0)
                sampleHash += q;

        }

        if (patternHash == sampleHash && compare(pattern, sample, n - m)) {
            answer.add(n - m + 1);
        }

        return answer;
    }
}
