package level_2;

import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/12981
public class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        Set<String> wordSet = new HashSet<>();
        int person = 1;
        int round = 1;
        char prevLastChar = words[0].charAt(0);

        for (String word : words) {
            if (wordSet.contains(word) || word.charAt(0) != prevLastChar) {
                return new int[]{person, round};
            }

            wordSet.add(word);
            prevLastChar = word.charAt(word.length() - 1);

            if (++person > n) {
                person = 1;
                ++round;
            }
        }

        return new int[]{0, 0};
    }
}
