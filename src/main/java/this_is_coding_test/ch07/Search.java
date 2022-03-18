package this_is_coding_test.ch07;

import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Search {

    /**
     * 난이도 중하
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= N <= 1_000_000
     * 1 < N개 정수 <= 1_000_000
     * 1 <= M <= 100_000
     * 1 < M개 정수 <= 1_000_000
     */
    public String 부품_찾기(int n, String nArray, int m, String mArray) {
        Set<Integer> set = Arrays.stream(nArray.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        int[] array = Arrays.stream(mArray.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        StringBuilder sb = new StringBuilder();

        for(int value : array) {
            if (set.contains(value)) {
                sb.append("yes ");
            } else {
                sb.append("no ");
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
