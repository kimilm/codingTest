package this_is_coding_test.ch07;

import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
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

    /**
     * 난이도 중
     * 제한) 시간: 2초, 메모리: 128MB
     * 2 <= N <= 1_000_000
     * 1 <= M <= 2_000_000_000
     */
    public int 떡볶이_떡_만들기(int n, int m, String tteoks) {
        TreeSet<Integer> set = Arrays.stream(tteoks.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(TreeSet::new));

        int sum = 0;
        int height = set.last();
        int cutH = height;
        int cut = 1;

        while(sum < m) {
            int temp = set.higher(--height);
            if (temp != cutH) {
                cutH = temp;
                ++cut;
            }
            sum += cut;
        }

        return height;
    }

    /**
     * 이런 유형을 파라메트릭 서치 유형이라 부른다.
     * 일반적으로 이진탐색을 이용하여 해결한다.
     * 매우 큰 수를 보면 당연하게 이진탐색을 떠올리도록 하자
     * 10억 -> 이진탐색 이용 -> 31번 만에 모든 경우의 수 고려 가능
     * 떡의 개수가 최대 100만 -> 최악의 경우에도 약 3천만 번의 연산 -> 2초 언저리에 연산 완료 가능
     */
    public int 떡볶이_떡_만들기_2(int n, int m, String tteoks) {
        int[] array = parseIntArray(tteoks);

        // 이진 탐색을 위한 시작과 끝 설정
        int start = 0;
        int end = Arrays.stream(array).max().orElseThrow();

        int result = 0;

        // 이진 탐색
        while(start <= end) {
            int total = 0;
            int mid = (start + end) / 2;

            // 잘랐을 때 얻는 떡의 양 계산
            for (int tteok : array) {
                if (tteok > mid) {
                    total += tteok - mid;
                }
            }

            // 얻은 양이 적다면
            if (total < m) {
                // 끝 지점을 당김
                end = mid - 1;
            }
            // 얻은 양이 충분하다면
            else {
                // 최대한 덜 잘랐을 때가 정답임, 여기서 저장
                result = mid;
                // 시작 지점을 당김
                start = mid + 1;
            }
        }

        return result;
    }
}
