package level_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/131128?language=java
public class 숫자_짝꿍 {
    public String solution(String X, String Y) {
        int[] array = new int[10];
        List<Integer> numbers = new ArrayList<>();
        X.codePoints().forEach(cp -> ++array[cp - 48]);
        Y.codePoints().forEach(cp -> {
            int idx = cp - 48;
            if (array[idx] > 0) {
                --array[idx];
                numbers.add(idx);
            }
        });

        if (numbers.isEmpty()) {
            return "-1";
        }

        if (numbers.get(0) == 0) {
            return "0";
        }

        return numbers.stream()
                .sorted(Collections.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
