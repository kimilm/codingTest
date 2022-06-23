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

        for (int i = 0; i < t; ++i) {
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

    /**
     * 풀이과정은 해설과 동일했음. 해설에서는 패딩을 주지 않고 갈 수 있는 범위를 체크함
     * 점화식: dp[i][j] = array[i][j] + max(dp[i - 1][j - 1], dp[i][j - 1], dp[i + 1][j - 1])
     * 현재 위치의 값 + 왼쪽 (위에서 오는 경우, 옆에서 오는경우, 아래에서 오는 경우) 의 최댓값
     */

    /**
     * 난이도: 중하
     * 1 <= n <= 500
     * 제한) 시간: 2초, 메모리: 128MB
     * https://www.acmicpc.net/problem/1932
     */
    public int 정수_삼각형(String[] input) {
        int n = Integer.parseInt(input[0]);
        int[][] triangle = new int[n][];

        for (int i = 1; i < input.length; ++i) {
            triangle[i - 1] = Arrays.stream(input[i].split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < triangle[i].length; j++) {
                int prevLeftValue = j - 1 == -1 ? 0 : triangle[i - 1][j - 1];
                int prevRightValue = j == triangle[i - 1].length ? 0 : triangle[i - 1][j];

                triangle[i][j] += Integer.max(prevLeftValue, prevRightValue);
            }
        }

        return Arrays.stream(triangle[n - 1]).max().orElseThrow();
    }

    /**
     * 난이도: 중
     * 1 <= n <= 15
     * 1 <= T(i) <= 5
     * 1 <= P(i) <= 1_000
     * 제한) 시간: 2초, 메모리: 512MB
     * https://www.acmicpc.net/problem/14501
     */
    public int 퇴사(String[] input) {
        int n = Integer.parseInt(input[0]);
        int[] t = new int[n];
        int[] p = new int[n];
        int[] dp = new int[n];

        for (int i = 1; i < n + 1; ++i) {
            String[] split = input[i].split(" ");
            t[i - 1] = Integer.parseInt(split[0]);
            p[i - 1] = Integer.parseInt(split[1]);
        }

        // 첫날 일해서 퇴사일을 넘기면 안 됨
        if (t[0] <= n) {
            dp[t[0] - 1] = p[0];
        }
        // 퇴사 전까지
        for (int i = 1; i < n; i++) {
            // 돈 받는날 = 오늘 + 걸리는 시간 t[i] - 1일
            int idx = i + t[i] - 1;
            // 퇴사일 전까지
            if (idx < n) {
                // 어제까지 일해서 번 돈 + 오늘 일하면 받을 수 있는 돈
                int pay = dp[i - 1] + p[i];
                // 더 많이 받게 되는날로 교체
                dp[idx] = Integer.max(dp[idx], pay);
            }
            dp[i] = Integer.max(dp[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    /**
     * 뒤쪽 날짜부터 거꾸로 확인하는 방식으로 접근하여 해결하는 다이나믹 프로그래밍 아이디어를 적용할 수 있다.
     * day  1일  2일  3일  4일  5일  6일  7일
     * T    3일  5일  1일  1일  2일  4일  2일
     * P    10만 20만 10만 20만 15만 40만 200만
     * 1일 차에 상담을 진행, 4일부터 다시 상담 진행 가능
     * -> 1일차의 상담 금액 + 4일부터의 최대 상담 금액
     * 현재 상담 일자의 이윤(p[i]) + 현재 상담을 마친 일자부터의 최대 이윤(dp[t[i] + i])
     * 계산된 각각의 값 중에서 최댓값 출력
     * dp[i] = i번째 날부터 마지막 날까지 낼 수 있는 최대 이익
     * 점화식: dp[i] = max(p[i] + dp[t[i] + i], max_value)
     */

    public int 퇴사_2(String[] input) {
        int n = Integer.parseInt(input[0]);
        int[] t = new int[n];
        int[] p = new int[n];

        for (int i = 1; i < n + 1; ++i) {
            String[] split = input[i].split(" ");
            t[i - 1] = Integer.parseInt(split[0]);
            p[i - 1] = Integer.parseInt(split[1]);
        }

        int[] dp = new int[n + 1];
        int max_value = 0;
        // 리스트를 뒤에서부터 확인
        for (int i = n - 1; i > -1; --i) {
            int time = t[i] + i;
            // 상담이 기간 안에 끝나는 경우
            if (time <= n) {
                // 점화식에 맞게, 현재까지의 최고 이익 계산
                dp[i] = Integer.max(p[i] + dp[time], max_value);
                max_value = dp[i];
            }
            // 상담이 기간을 벗어나는 경우
            dp[i] = max_value;
        }
        return max_value;
    }
}
