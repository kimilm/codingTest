package this_is_coding_test.ch11;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class GreedyQuestions {

    /**
     * 난이도 하
     * 1 <= n <= 100_000
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 모험가_길드(int n, int[] fears) {
        Queue<Integer> queue = Arrays.stream(fears)
                .boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toCollection(LinkedList::new));

        int group = 0;
        int fear = queue.peek();

        while (fear <= queue.size()) {
            for (int i = 0; i < fear; i++) {
                queue.poll();
            }
            ++group;

            if (!queue.isEmpty()) {
                fear = queue.peek();
            }
        }

        return group;
    }

    /**
     * 오름차순 정렬을 수행하고 공포도가 낮은 모험가부터 하나씩 그룹에 포함시킨다
     * 현재 그룹에 포함된 모험가의 수가 현재 확인하고 있는 공포도보다 크거나 같다면 그룹을 결성할 수 있다
     */
    public int 모험가_길드_2(int n, int[] data) {
        int[] fears = data.clone();

        // 오름차순 정렬
        Arrays.sort(fears);

        // 그룹의 수
        int result = 0;

        // 현재 그룹에 포함된 모험가의 수
        int count = 0;

        // 공포도가 낮은 것부터 하나씩 확인하기
        for (int fear : fears) {
            // 현재 그룹에 해당 모험가를 포함시킴
            ++count;

            // 현재 그룹에 포함된 모험가의 수가 현재의 공포도 이상이라면
            if (count >= fear) {
                // 그룹 결성
                ++result;

                // 현재 그룹에 포함된 모험가의 수 초기화
                count = 0;
            }
        }

        return result;
    }

    /**
     * 난이도 하
     * 1 <= S의 길이 <= 20
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 곱하기_혹은_더하기(String s) {
        int[] numbers = Arrays.stream(s.split("")).mapToInt(Integer::parseInt).toArray();

        int result = 0;

        for (int number : numbers) {
            if (result > 1) {
                if (number < 2) {
                    result += number;
                } else {
                    result *= number;
                }
            } else {
                result += number;
            }

            // 비교 구문을 이렇게 압축할 수도 있다.
//            if (number < 2 || result < 2) {
//                result += number;
//            } else {
//                result *= number;
//            }
        }

        return result;
    }

    /**
     * 난이도 하
     * 1 <= S의 길이 <= 1_000_000
     * 제한) 시간: 2초, 메모리: 128MB
     * https://www.acmicpc.net/problem/1439
     */
    public int 문자열_뒤집기(String S) {
        int[] counts = new int[2];
        String before = "";

        for (String token : S.split("")) {
            if (!before.equals(token)) {
                before = token;
                ++counts[Integer.parseInt(token)];
            }
        }

        return Integer.min(counts[0], counts[1]);

        // 전체 리스트를 하나씩 확인하며 변경되는 경우를 확인
        // 단순 무식하게 탐욕적으로 푸는 문제라서 그리디?
    }

    /**
     * 난이도 하
     * 1 <= N <= 1_000
     * 1 <= 화폐의 단위 <= 1_000_000
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 만들_수_없는_금액(int n, int[] coins) {
        int[] sortedCoins = Arrays.stream(coins.clone()).sorted().toArray();
        int coin = 0;
        boolean flag = true;

        while (flag) {
            ++coin;
            flag = false;

            for (int i = 0; i < n; ++i) {
                int temp = sortedCoins[i];

                if (temp == coin) {
                    flag = true;
                    break;
                }

                for (int j = i + 1; j < n; ++j) {
                    temp += sortedCoins[j];

                    if (temp == coin) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    break;
                }
            }
        }

        return coin;
    }

    /**
     * 화폐 단위가 다른 동전을 하나씩 가지고 있음, 오름차순 정렬
     * target 금액을 만들 수 있다면 target - 1 까지의 모든 금액을 만들 수 있음
     * 1 부터 가지고 있는 동전을 더해가며 target 금액을 증가시킴
     *
     * 예) target 은 1 부터 시작
     * 가진 동전이 1 1 4
     * 동전 1) 1 가능 target += 1 (2)
     * 동전 1) 2 가능 target += 1 (3)
     * 동전 4) 3 불가능
     *
     * 가진 동전이 1 2 3 5
     * 동전 1) 1 가능 target += 1 (2)
     * 동전 2) 2 가능 target += 2 (4)
     * 동전 3) 4 가능 target += 3 (7)
     * 동전 5) 7 가능 target += 5 (11)
     *
     * target 에 현재 동전 단위를 더해나감, 1 부터 target + 현재 동전 까지 범위의 동전은 모두 만들 수 있다는 얘기
     * target += 현재 동전
     * 현재 동전 = 다음 동전
     *
     * 1 2 3 동전이 있음, target = 1
     * 현재 동전 = 1 로 target = 1 금액 만들기 가능, target += 현재 동전 (target = 2) (1 ~ 1 금액 만들기 가능 / 1)
     * 현재 동전 = 2 로 target = 2 금액 만들기 가능, target += 현재 동전 (target = 4) (2 ~ 3 금액 만들기 가능 / 2,   1+2)
     * 현재 동전 = 3 로 target = 4 금액 만들기 가능, target += 현재 동전 (target = 7) (4 ~ 6 금액 만들기 가능 / 1+3, 2+3, 1+2+3)
     *
     * if) target 보다 현재 동전의 단위가 작거나 같다면 해당 금액을 만들 수 있음
     * 현재 동전 = 4 일시 target = 11 로 갱신, (7 ~ 10 금액 만들기 가능 / 1+2+4, 1+3+4, 2+3+4, 1+2+3+4)
     * 현재 동전 = 5 일시 target = 12 로 갱신, (7 ~ 11 금액 만들기 가능 / 2+5, 1+2+5, 1+3+5, 2+3+5, 1+2+3+5)
     * 현재 동전 = 6 일시 target = 13 로 갱신, (7 ~ 12 금액 만들기 가능 / 1+6, 2+6, 1+2+6, 1+3+6, 2+3+6, 1+2+3+6)
     * 현재 동전 = 7 일시 target = 14 로 갱신, (7 ~ 13 금액 만들기 가능 / 7, 1+7, 2+7, 1+2+7, 1+3+7, 2+3+7, 1+2+3+7)
     *
     * else) target 보다 현재 동전의 단위가 크다면 해당 금액을 만들 수 없음
     * 현재 동전 = 8 일시 이전에 가지고 있는 동전으로는 1부터 6까지의 금액만 만들기 가능, target 금액인 7은 만들 수 없음 -> 리턴
     */
    public int 만들_수_없는_금액_2(int n, int[] coins) {
        int[] data = Arrays.stream(coins).sorted().toArray();

        int target = 1;

        for (int x : data) {
            if (target < x) {
                break;
            }
            target += x;
        }

        return target;
    }

    /**
     * 난이도 하
     * 1 <= N <= 1_000
     * 1 <= M <= 10
     * 1 <= K <= M
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 볼링공_고르기(int n, int m, int[] balls) {
        int[] weights = Arrays.stream(balls.clone()).sorted().toArray();

        int count = 0;

        for (int i = 0; i < weights.length; i++) {
            int currentWeight = weights[i];

            for (int j = i + 1; j < weights.length; j++) {
                if (weights[j] != currentWeight) {
                    ++count;
                }
            }
        }

        return count;
    }
}
