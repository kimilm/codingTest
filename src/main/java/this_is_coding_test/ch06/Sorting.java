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
}
