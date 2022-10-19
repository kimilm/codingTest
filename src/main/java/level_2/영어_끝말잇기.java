package level_2;

import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/12981
public class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        Set<String> wordSet = new HashSet<>();
        char prevLastChar = words[0].charAt(0);

        int count = 0;
        for (String word : words) {
            if (wordSet.contains(word) || word.charAt(0) != prevLastChar) {
                return new int[]{count % n + 1, count / n + 1};
            }

            ++count;
            wordSet.add(word);
            prevLastChar = word.charAt(word.length() - 1);
        }

        return new int[]{0, 0};
    }
}
