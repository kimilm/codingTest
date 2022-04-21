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
}
