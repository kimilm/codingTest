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

        int[] targets = parseIntArray(mArray);

        StringBuilder sb = new StringBuilder();

        for (int target : targets) {
            if (set.contains(target)) {
                sb.append("yes ");
            } else {
                sb.append("no ");
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public int[] parseIntArray(String str) {
        return Arrays.stream(str.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * 이진 탐색을 이용한 풀이
     */
    public String 부품_찾기_2(int n, String nArray, int m, String mArray) {
        int[] array = parseIntArray(nArray);
        int[] targets = parseIntArray(mArray);

        StringBuilder sb = new StringBuilder();

        for (int target : targets) {
            if (binarySearchFor부품찾기(array, target, 0, array.length - 1) != Integer.MIN_VALUE) {
                sb.append("yes ");
            } else {
                sb.append("no ");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public int binarySearchFor부품찾기(int[] array, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * 계수정렬을 이용한 풀이
     */
    public String 부품_찾기_3(int n, String nArray, int m, String mArray) {
        // 계수정렬용 배열
        int[] countSort = new int[1_000_001];
        // 초기화
        Arrays.stream(nArray.split(" "))
                .map(Integer::parseInt)
                .forEach(idx -> countSort[idx] = 1);

        int[] targets = parseIntArray(mArray);

        StringBuilder sb = new StringBuilder();

        for (int target : targets) {
            if (countSort[target] != 1) {
                sb.append("no ");
            } else {
                sb.append("yes ");
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
