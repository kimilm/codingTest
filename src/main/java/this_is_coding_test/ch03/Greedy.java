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

    /**
     * 이 문제는 가장 큰 수가 K번, 두 번쨰로 큰 수가 1번 더하는 부분이 반복된다
     * 전체를 반복할 필요 없이 해당 부분을 곱해버리면 됨
     */
    public int 큰_수의_법칙_2(int n, int m, int k, int[] numbers) {
        Arrays.sort(numbers);
        int first = numbers[n - 1];
        int second = numbers[n - 2];

        // 큰 수가 더해지는 횟수 = 전체 크기 / 반복되는 부분의 크기 * 큰 수의 반복 횟수 + 나머지
        int count = m / (k + 1) * k;    // 전체 크기 / 반복되는 부분의 크기 * 큰 수의 반복 횟수
        count += m % (k + 1);   // 나머지

        int answer = count * first;
        answer += (m - count) * second;

        return answer;
    }

    /**
     * 1 <= N, M <= 100
     * 1 <= card <= 10_000
     * 시간제한 1초, 메모리제한 128MB
     */
    public int 숫자_카드_게임(int n, int m, int[][] cards) {
        return Arrays.stream(cards)
                .mapToInt(value -> Arrays.stream(value)
                        .min().orElseThrow())
                .max().orElseThrow();
    }

    /**
     * 2 <= N <= 100_000
     * 2 <= K <= 100_000
     * N >= K
     * 시간제한 1초, 메모리제한 128MB
     */
    public int 일이_될_때까지(int n, int k) {
        int answer = 0;

        while(n != 1) {
            if (n % k != 0) {
                --n;
            } else {
                n /= k;
            }
            ++answer;
        }

        return answer;
    }
}
