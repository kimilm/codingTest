package level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165

public class 타겟_넘버 {
    public int solution(int[] numbers, int target) {
        int n = numbers.length;
        int answer = 0;

        // 배열 전체 더한 값 저장
        int sum = Arrays.stream(numbers).sum();

        for (int i = 1; i < n; i++) {
            // 마이너스가 i개 붙을 수 있는 경우
            List<int[]> minusList = 타겟_넘버_조합(numbers, n, i);

            for (int[] minus : minusList) {
                // 이미 더했으니 두 번 빼야 원하는 결과가 나옴, 곱하기 2
                int calculate = sum - (Arrays.stream(minus).sum() << 1);

                if (calculate == target) {
                    ++answer;
                }
            }
        }

        return answer;
    }

    public List<int[]> 타겟_넘버_조합(int[] arr, int n, int r) {
        return 타겟_넘버_조합(arr, new boolean[n], 0, n, r);
    }

    public List<int[]> 타겟_넘버_조합(int[] arr, boolean[] visited, int start, int n, int r) {
        List<int[]> result = new ArrayList<>();

        if (r == 0) {
            result.add(IntStream.range(0, n)
                    .filter(i -> visited[i])
                    .map(i -> arr[i])
                    .toArray()
            );
            return result;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            result.addAll(타겟_넘버_조합(arr, visited, i + 1, n, r - 1));
            visited[i] = false;
        }

        return result;
    }
}
