package this_is_coding_test.ch15;

import java.util.Arrays;

public class SearchQuestions {
    /**
     * 난이도: 중
     * 1 <= N <= 1_000_000
     * -10^9 <= x <= 10^9
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
     *
     * 해설지도 이런식의 코드를 작성함
     * 다만 나는 찾으려는 x 값 범위의 시작 직전과 끝난 직후 인덱스를 찾는 코드를 작성했고
     * 2를 찾음, 1 1 2 2 2 2 3 이라면 1과 6
     * 해설지에서는 x값 범위의 시작과 끝 인덱스를 구함
     * 2를 찾음, 1 1 2 2 2 2 3 이라면 2와 5
     *
     * 또한 시작과 끝을 찾는 2개의 메서드를 선언하였음
     * 하나의 메서드에서 둘 다 찾으려고 하니 메서드 내부에서 + - 연산에 의해 arrayindexoutofboundsexception이 발생했음
     *
     * 몾 찾는 경우를 start == end 로 설정하지 않고 start > end 로 설정하였음
     *
     * 풀이과정은 비슷했음. 좀 더 정교해야했음
     */
    public int 정렬된_배열에서_특정_수의_개수_구하기_2(String[] input) {
        String[] nx = input[0].split(" ");
        int n = Integer.parseInt(nx[0]);
        int x = Integer.parseInt(nx[1]);
        int[] array = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        int first = findFirst(array, x, 0, n - 1);

        // 못 찾았다면 값이 x인 원소가 존재하지 않음
        if (first == -1) {
            return -1;
        }

        int last = findLast(array, x, 0, n - 1);

        return last - first + 1;
    }
    // 시작 위치 이진 탐색
    public int findFirst(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        // 해당 값을 가지는 원소 중에서 가장 왼쪽에 있는 경우에만 인덱스 반환
        if ((mid == 0 || target > array[mid - 1]) && array[mid] == target) {
            return mid;
        }
        // 중간 지점의 값보다 찾고자 하는 값이 작거나 같은 경우 왼쪽 확인
        else if (array[mid] >= target) {
            return findFirst(array, target, start, mid - 1);
        }
        // 중간 지점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
        else {
            return findFirst(array, target, mid + 1, end);
        }
    }
    // 끝 위치 이진 탐색
    public int findLast(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        // 해당 값을 가지는 원소 중에서 가장 오른쪽에 있는 경우에만 인덱스 반환
        if ((mid == array.length - 1 || target < array[mid + 1]) && array[mid] == target) {
            return mid;
        }
        // 중간 지점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
        else if (array[mid] > target) {
            return findLast(array, target, start, mid - 1);
        }
        // 중간 지점의 값보다 찾고자 하는 값이 크거나 같은 경우 오른쪽 확인
        else {
            return findLast(array, target, mid + 1, end);
        }
    }

    /**
     * 난이도: 중하
     * 1 <= N <= 1_000_000
     * -10^9 <= x <= 10^9
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 고정점(String[] input) {
        int n = Integer.parseInt(input[0]);
        int[] array = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        return binSearch(array, 0, n - 1);
    }

    public int binSearch(int[] array, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (array[mid] == mid) {
            return mid;
        }

        if (array[mid] > mid) {
            return binSearch(array, start, mid - 1);
        }
        else {
            return binSearch(array, mid + 1, end);
        }
    }

    /**
     * O(logN) 의 알고리즘을 설계하지 못하면 시간초과
     */
}
