package this_is_coding_test.ch16;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DynamicQuestions {
    /**
     * 난이도: 중하
     * 1 <= T <= 1000
     * 1 <= n, m <= 20
     * 1 <= 각 위치에 매장된 금의 개수 <= 100
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int[] 금광(String[] input) {
        Queue<String> read = Arrays.stream(input).collect(Collectors.toCollection(LinkedList::new));
        int t = Integer.parseInt(read.poll());
        int[] answer = new int[t];

        // 갈 수 있는 이전 위치
        int[] dx = {-1, 0, 1};

        for(int i = 0; i < t; ++i) {
            String[] nm = read.poll().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            // 위 아래 0 패딩 삽입
            int[][] goldMine = fillGold(n, m, read.poll());

            // 1열부터
            for (int j = 1; j < m; j++) {
                // 1~n 행에 대해서
                for (int k = 1; k <= n; k++) {
                    // 이전에 갈 수 있는 위치는 3개
                    int max = Integer.MIN_VALUE;
                    for (int l = 0; l < 3; l++) {
                        int px = k + dx[l];
                        int py = j - 1;
                        // 각 위치에서 얻을 수 있는 최대 금 저장
                        max = Integer.max(max, goldMine[px][py]);
                    }
                    goldMine[k][j] += max;
                }
            }

            answer[i] = IntStream.range(1, n + 1).map(idx -> goldMine[idx][m - 1]).max().getAsInt();
        }

        return answer;
    }

    public int[][] fillGold(int n, int m, String read) {
        String[] golds = read.split(" ");
        int[][] array = new int[n + 2][m];
        int count = 0;

        for (int i = 0; i < n + 2; i++) {
            if (i == 0 || i == n + 1) {
                continue;
            }

            for (int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(golds[count++]);
            }
        }

        return array;
    }
}
