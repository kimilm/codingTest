package this_is_coding_test.ch06;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Sorting {

    /**
     * 난이도 하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= N <= 500
     * 1 <= 숫자 <= 100_000
     */
    public int[] 위에서_아래로(int n, String numbers) {
        return Arrays.stream(numbers.split(" "))
                .map(Integer::parseInt)
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue).toArray();
    }

    /**
     * 난이도 하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= N <= 100_000
     * 1 <= 문자열 길이, 성적 <= 100
     */
    public String 성적이_낮은_순서로_학생_출력하기(int n, String[] students) {
        // Comparator.comparingInt(o -> Integer.parseInt(o[1]))
        // 입력값(o)에 대해서 이하의 방법(Integer.parseInt(o[1]))을 적용하여 비교를 수행한다.
        // 문자열을 길이를 기반으로 비교한다면 Comparator.comparingInt(String::length)와 같이 사용할 수 있다.
        return Arrays.stream(students)
                .map(str -> str.split(" "))
                .sorted(Comparator.comparingInt(o -> Integer.parseInt(o[1])))
                .map(str -> str[0])
                .collect(Collectors.joining(" "));
    }

    /**
     * 난이도 하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= N <= 100_000
     * 0 <= K <= N
     * 모든 배열의 원소는 10_000_000보다 작은 자연수
     */
    public int 두_배열의_원소_교체(int n, int k, int[] aArray, int[] bArray) {
        Integer[] sumArray = Arrays.stream(aArray).boxed()
                .sorted()
                .toArray(Integer[]::new);

        Integer[] spareArray = Arrays.stream(bArray).boxed()
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);

        for (int i = 0; i < k; ++i) {
            if (spareArray[i] > sumArray[i]) {
                int temp = sumArray[i];
                sumArray[i] = spareArray[i];
                spareArray[i] = temp;
            }
        }

        return Arrays.stream(sumArray)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
