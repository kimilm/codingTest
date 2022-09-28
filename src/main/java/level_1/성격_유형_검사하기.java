package level_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/118666
public class 성격_유형_검사하기 {
    public static List<Map<Integer, Character>> TYPE_MAPS;
    public static Map<Character, Integer> COLS;
    public static Map<Character, Integer> ROWS;

    static {
        TYPE_MAPS = new ArrayList<>();
        TYPE_MAPS.add(Map.of(0, 'R', 1, 'T'));
        TYPE_MAPS.add(Map.of(0, 'C', 1, 'F'));
        TYPE_MAPS.add(Map.of(0, 'J', 1, 'M'));
        TYPE_MAPS.add(Map.of(0, 'A', 1, 'N'));

        COLS = new HashMap<>();
        COLS.put('R', 0);
        COLS.put('T', 1);
        COLS.put('C', 0);
        COLS.put('F', 1);
        COLS.put('J', 0);
        COLS.put('M', 1);
        COLS.put('A', 0);
        COLS.put('N', 1);

        ROWS = new HashMap<>();
        ROWS.put('R', 0);
        ROWS.put('T', 0);
        ROWS.put('C', 1);
        ROWS.put('F', 1);
        ROWS.put('J', 2);
        ROWS.put('M', 2);
        ROWS.put('A', 3);
        ROWS.put('N', 3);
    }

    public String solution(String[] survey, int[] choices) {
        int[][] types = new int[4][2];
        int n = choices.length;

        for (int i = 0; i < n; i++) {
            int choice = choices[i] - 4;

            if (choice != 0) {
                char type = choice < 0
                        ? survey[i].charAt(0)
                        : survey[i].charAt(1);

                types[ROWS.get(type)][COLS.get(type)] += Math.abs(choice);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            if (types[i][0] < types[i][1]) {
                sb.append(TYPE_MAPS.get(i).get(1));
            } else {
                sb.append(TYPE_MAPS.get(i).get(0));
            }
        }

        return sb.toString();
    }
}

/**
 * 전                                        후
 * 테스트 1 〉	통과 (0.21ms, 75.5MB)        테스트 1 〉	통과 (0.06ms, 78.7MB)
 * 테스트 2 〉	통과 (0.16ms, 78MB)          테스트 2 〉	통과 (0.07ms, 73.6MB)
 * 테스트 3 〉	통과 (0.25ms, 77.1MB)        테스트 3 〉	통과 (0.06ms, 74.6MB)
 * 테스트 4 〉	통과 (0.29ms, 88.7MB)        테스트 4 〉	통과 (0.08ms, 77.1MB)
 * 테스트 5 〉	통과 (0.23ms, 77.3MB)        테스트 5 〉	통과 (0.07ms, 76.4MB)
 * 테스트 6 〉	통과 (0.23ms, 73.1MB)        테스트 6 〉	통과 (0.10ms, 75.1MB)
 * 테스트 7 〉	통과 (0.59ms, 73.1MB)        테스트 7 〉	통과 (0.08ms, 75.1MB)
 * 테스트 8 〉	통과 (0.76ms, 77.5MB)        테스트 8 〉	통과 (0.10ms, 72.9MB)
 * 테스트 9 〉	통과 (1.16ms, 77.6MB)        테스트 9 〉	통과 (0.10ms, 84.6MB)
 * 테스트 10 〉	통과 (1.36ms, 70.9MB)        테스트 10 〉	통과 (0.11ms, 78MB)
 * 테스트 11 〉	통과 (1.43ms, 72.8MB)        테스트 11 〉	통과 (0.12ms, 88.2MB)
 * 테스트 12 〉	통과 (3.55ms, 70.2MB)        테스트 12 〉	통과 (0.28ms, 73.4MB)
 * 테스트 13 〉	통과 (5.61ms, 75.4MB)        테스트 13 〉	통과 (0.34ms, 75.3MB)
 * 테스트 14 〉	통과 (5.84ms, 77.2MB)        테스트 14 〉	통과 (0.41ms, 77.7MB)
 * 테스트 15 〉	통과 (6.07ms, 81.9MB)        테스트 15 〉	통과 (0.50ms, 78.8MB)
 * 테스트 16 〉	통과 (6.40ms, 75.1MB)        테스트 16 〉	통과 (0.55ms, 78.8MB)
 * 테스트 17 〉	통과 (6.21ms, 81.4MB)        테스트 17 〉	통과 (0.48ms, 77.4MB)
 * 테스트 18 〉	통과 (6.62ms, 81.9MB)        테스트 18 〉	통과 (0.46ms, 76.4MB)
 * 테스트 19 〉	통과 (7.27ms, 85.6MB)        테스트 19 〉	통과 (0.52ms, 74MB)
 * 테스트 20 〉	통과 (5.90ms, 80.8MB)        테스트 20 〉	통과 (0.42ms, 75.1MB)
 *
 * split -> charAt 으로 변경, 가장 느린 테스트 케이스에서 10배의 성능 차이가 있었음
 */