package this_is_coding_test.ch08;

import java.util.Arrays;

public class DynamicProgramming {

    /**
     * 난이도 중하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= x <= 30_000
     */
    public int 일로_만들기(int x) {
        int[] d = new int[x + 1];
        d[1] = 1;

        return makeOne(d, x) - 1;
    }

    public int makeOne(int[] d, int x) {
        if (d[x] != 0) {
            return d[x];
        }

        int[] array = new int[4];

        if (x % 5 == 0) {
            d[x / 5] = makeOne(d, x / 5);
            array[0] = d[x / 5];
        }

        if (x % 3 == 0) {
            d[x / 3] = makeOne(d, x / 3);
            array[1] = d[x / 3];
        }

        if (x % 2 == 0) {
            d[x / 2] = makeOne(d, x / 2);
            array[2] = d[x / 2];
        }

        d[x - 1] = makeOne(d, x - 1);
        array[3] = d[x - 1];

        int min = Arrays.stream(array).filter(value -> value != 0).min().orElseThrow();
        return min + 1;
    }

    /**
     * Bottom_Up 방식의 풀이
     */
    public int 일로_만들기_2(int x) {
        int[] d = new int[30_001];

        for (int i = 2; i < x + 1; i++) {
            // 현재의 수에서 1을 빼는 경우
            d[i] = d[i - 1] + 1;
            // 현재의 수가 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                d[i] = Integer.min(d[i], d[i / 2] + 1);
            }
            // 현재의 수가 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                d[i] = Integer.min(d[i], d[i / 3] + 1);
            }
            // 현재의 수가 5로 나누어 떨어지는 경우
            if (i % 5 == 0) {
                d[i] = Integer.min(d[i], d[i / 5] + 1);
            }
        }

        return d[x];
    }

    /**
     * 난이도 중
     * 제한) 시간: 1초, 메모리: 128MB
     * 3 <= N <= 100
     * 0 <= K <= 1_000
     */
    public int 개미_전사(int n, String k) {
        int[] foods = Arrays.stream(k.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] d = new int[n];

        d[0] = foods[0];
        d[1] = foods[1];
        d[2] = foods[0] + foods[2];

        for(int i = 3; i < n; ++i) {
            d[i] = foods[i] + Integer.max(d[i - 2], d[i - 3]);
        }

        return Integer.max(d[n - 1], d[n - 2]);
    }
}
