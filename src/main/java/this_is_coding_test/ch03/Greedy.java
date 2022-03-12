package this_is_coding_test.ch03;

import java.util.Arrays;

public class Greedy {

    /**
     * 거스름돈 동전 500원 100원 50원 10원이 무제한으로 존재
     * 거슬러줘야 할 돈이 N원일 때 동전의 최소 개수를 구하기
     * N은 10의 배수
     */
    public int 거스름돈(int money) {
        int[] coins = {500, 100, 50, 10};
        int answer = 0;

        for (int coin : coins) {
            answer += money / coin;
            money %= coin;
        }

        return answer;
    }

    /**
     * 2 <= N <= 1_000
     * 1 <= M <= 10_000
     * 1 <= K <= 10_000
     * 1 <= number <= 10_000
     * 시간제한 1초, 메모리제한 128MB
     */
    public int 큰_수의_법칙(int n, int m, int k, int[] numbers) {
        Arrays.sort(numbers);
        int bigFirst = numbers[n - 1];
        int bigSecond = numbers[n - 2];

        int repeat = k;
        int count = 0;
        int answer = 0;

        while (count != m) {
            if (repeat != 0) {
                answer += bigFirst;
                --repeat;
            } else {
                answer += bigSecond;
                repeat = k;
            }
            ++count;
        }

        return answer;
    }
}
