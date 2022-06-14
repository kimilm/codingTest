package this_is_coding_test.ch15;

import java.util.Arrays;

public class SearchQuestions {
    /**
     * 난이도: 중
     * 1 <= N <= 1_000_000
     * -10^9 <= ㅌ <= 10^9
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 정렬된_배열에서_특정_수의_개수_구하기(String[] input) {
        String[] nx = input[0].split(" ");
        int n = Integer.parseInt(nx[0]);
        int x = Integer.parseInt(nx[1]);
        int[] array = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        int pre = binSearch(array, 0, n - 1, x, true);
        int post = binSearch(array, 0, n - 1, x, false);

        return post - pre - 1;
    }

    public int binSearch(int[] array, int start, int end, int x, boolean flag) {
        // 못 찾았다면
        if (start == end) {
            return -1;
        }

        int mid = (start + end) / 2;

        // 중간값의 좌우에서 경계를 찾았다면
        if (array[mid] == x) {
            // flag == true 라면 좌측 경계 탐색
            if (flag && array[mid - 1] != x) {
                return mid - 1;
            }
            // flag == false 라면 우측 경계 탐색
            if (!flag && array[mid + 1] != x) {
                return mid + 1;
            }
        } else {
            if (flag && array[mid + 1] == x) {
                return mid;
            }
            if (!flag && array[mid - 1] == x) {
                return mid;
            }
        }
        // 중간값이 찾으려는 값이라면
        if (array[mid] == x) {
            // flag == true 라면 좌측 탐색
            if (flag) {
                return binSearch(array, start, mid, x, flag);
            }
            // flag == false 라면 우측 탐색
            else {
                return binSearch(array, mid, end, x, flag);
            }
        }
        // 중간값이 찾으려는 값보다 크다면 좌측 탐색
        else if (array[mid] > x) {
            return binSearch(array, start, mid - 1, x, flag);
        }
        // 중간값이 찾으려는 값보다 작다면 우측 탐색
        else {
            return binSearch(array, mid + 1, end, x, flag);
        }
    }

    /**
     * logN의 알고리즘을 설계해야 함
     * 범위를 반씩 줄이며 찾는 수의 경계에 존재하는 값을 찾으려고 해봤음
     * 문제에서 주어진 케이스는 통과했지만 모두 같은 수로 이루어진 순열, 길이가 1인 순열, 시작과 끝 인덱스에 존재하는 수는 탐색할 수 없음
     */
}
