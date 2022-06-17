package this_is_coding_test.ch15;

import java.util.*;
import java.util.stream.Collectors;

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
     * <p>
     * 해설지도 이런식의 코드를 작성함
     * 다만 나는 찾으려는 x 값 범위의 시작 직전과 끝난 직후 인덱스를 찾는 코드를 작성했고
     * 2를 찾음, 1 1 2 2 2 2 3 이라면 1과 6
     * 해설지에서는 x값 범위의 시작과 끝 인덱스를 구함
     * 2를 찾음, 1 1 2 2 2 2 3 이라면 2와 5
     * <p>
     * 또한 시작과 끝을 찾는 2개의 메서드를 선언하였음
     * 하나의 메서드에서 둘 다 찾으려고 하니 메서드 내부에서 + - 연산에 의해 arrayindexoutofboundsexception이 발생했음
     * <p>
     * 몾 찾는 경우를 start == end 로 설정하지 않고 start > end 로 설정하였음
     * <p>
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
        } else {
            return binSearch(array, mid + 1, end);
        }
    }

    /**
     * O(logN) 의 알고리즘을 설계하지 못하면 시간초과
     */

    /**
     * 난이도: 중
     * 2 <= N <= 200_000
     * 2 <= C <= N
     * 1 <= x <= 1_000_000_000
     * 제한) 시간: 2초, 메모리: 128MB
     * https://www.acmicpc.net/problem/2110
     */
    public int 공유기_설치(String[] input) {
        String[] nc = input[0].split(" ");
        int n = Integer.parseInt(nc[0]);
        int c = Integer.parseInt(nc[1]) - 1;

        int[] house = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int start = 0;
        int end = n - 1;
        int answer = house[end] - house[start];

        for (int i = c; i > 1; i--) {
            int next = binSearch(house, (house[start] + house[end]) / i, start, end);

            int left = house[next] - house[start];
            int right = house[end] - house[next];
            answer = Integer.min(left, right);

            // 다음으로 나누려면 원소의 개수가 많아야 함
            if (next - start < end - next) {
                start = next;
            } else {
                end = next;
            }
        }

        return answer;
    }

    public int binSearch(int[] array, int target, int start, int end) {
        if (end - start == 1) {
            return Math.abs(target - array[start]) < Math.abs(target - array[end]) ? start : end;
        }

        int mid = (start + end) / 2;

        if (array[mid] == target) {
            return mid;
        }
        if (array[mid] < target) {
            return binSearch(array, target, mid, end);
        } else {
            return binSearch(array, target, start, mid);
        }
    }

    /**
     * '가장 인접한 두 공유기 사이의 거리' 의 최댓값을 탐색해야 하는 문제
     * 각 집의 좌표가 최대 10억, 이진 탐색 사용
     * 이진 탐색으로 가장 인접한 두 공유기 사이의 거리를 조절해가며
     * 매 순간 실제로 공유기를 설치하여 c보다 많은 개수로 공유기를 설치할 수 있는지 체크
     * <p>
     * C보다 많은 갯수의 공유기를 설치할 수 있다면 가장 인접한 두 공유기 사이의 거리 값을 증가시켜서
     * 더 큰 값에 대해서도 성립하는지를 체크하기 위해 다시 탐색을 수행
     * 7장에서 다룬 '떡볶이 떡 만들기' 문제와 유사하게 이진 탐색을 이용해 해결할 수 있는 파라메트릭 서치 유형의 문제
     * <p>
     * [1, 2, 4, 8, 9] 수열에서 C가 3일때
     * 공유기 사이의 거리 gap 은 최소 1, 최대 8
     * 공유기를 앞에서부터 순서대로 설치한다고 할때
     * 절반 크기인 4 gap 으로 공유기를 설치하면
     * 1 2 4 8 9
     * O X X O X
     * 의 형태로 2개의 공유기가 설치됨 < C, 범위를 최소 1, 최대 3으로 수정
     * 1 - 3 범위의 gap 중 절반인 2로 공유기를 설치하면
     * 1 2 4 8 9
     * O X O O X
     * 의 형태로 3개의 공유기가 설치됨 == C, 현재의 gap 을 저장하고 더 큰 범위 탐색, 범위 최소 3, 최대 3으로 수정
     * 1 2 4 8 9
     * O X O O X
     * 의 형태로 3개의 공유기가 설치됨 == C, 범위가 최소 3, 최대 3이므로 더이상 gap 을 증가시킬 수 없음
     * 최적의 값은 3
     * <p>
     * 거리 에 대해서 이진탐색을 적용해야 함
     * 배열의 원소 갯수는 20만개
     * 원소의 최댓값은 10억, 탐색 범위가 10억 -> 이걸 줄이기 위해 이진탐색을 사용
     * 조건을 주의깊게 읽자
     */
    public int 공유기_설치_2(String[] input) {
        String[] nc = input[0].split(" ");
        int n = Integer.parseInt(nc[0]);
        int c = Integer.parseInt(nc[1]);

        int[] house = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        // 가능한 최소 거리
        int start = 1;
        // 가능한 최대 거리
        int end = house[n - 1] - house[0];
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int value = house[0];
            int count = 1;
            // 현재의 mid 값을 이용해 공유기 설치
            for (int i = 1; i < n; i++) {
                // 앞에서부터 하나씩 설치
                if (house[i] >= value + mid) {
                    value = house[i];
                    ++count;
                }
            }
            // C개 이상의 공유기를 설치할 수 있다면
            if (count >= c) {
                // 설치 가능 최소 거리 증가 조정
                start = mid + 1;
                // 현재 상태에서 최적의 결과 저장
                result = mid;
            }
            // 없다면
            else {
                // 설치 가능 최대 거리 감소 조정
                end = mid - 1;
            }
        }

        return result;
    }

    public Integer[] treeSet(Integer[] nonSortedArray) {
        TreeSet<Integer> tree = Arrays.stream(nonSortedArray).collect(Collectors.toCollection(TreeSet::new));
        return tree.toArray(Integer[]::new);
    }

    /**
     * 난이도: 상
     * [가사 단어]
     * 2 <= words.length <= 100_000 (각 단어의 길이)
     * 2 <= 전체 가사 단어 길이의 합 <= 1_000_000
     * 알파벳 소문자로만 구성, 중복x
     * [검색 키워드]
     * 2 <= queries <= 100_000
     * 2 <= 전체 검색 키워드 길이의 합 <= 1_000_000
     * 알파벳 소문자 + 와일드카드(?)로 구성, 중복o
     * 제한) 시간: 1초, 메모리: 128MB
     * https://programmers.co.kr/learn/courses/30/lessons/60060
     */
    public int[] 가사_검색(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        QueryString[] queryStrings = Arrays.stream(queries)
                .map(QueryString::new)
                .toArray(QueryString[]::new);

        // 십만 * 십만 == 백억
        for (int i = 0; i < queries.length; i++) {
            int count = (int) Arrays.stream(words).filter(queryStrings[i]::match).count();
            answer[i] = count;
        }

        return answer;
    }

    static class QueryString {
        int flag;
        int length;
        int wildCardStartIdx;
        int wildCardEndIdx;
        String prefix;
        String postfix;

        public QueryString(String string) {
            this.length = string.length();
            this.wildCardStartIdx = string.indexOf("?");
            this.wildCardEndIdx = string.lastIndexOf("?");
            // 와일드카드가 접두사
            if (wildCardStartIdx == 0) {
                prefix = string.substring(0, wildCardEndIdx + 1);
                postfix = string.substring(wildCardEndIdx + 1);
                flag = 0;
            }
            // 와일드카드가 접미사
            else {
                prefix = string.substring(0, wildCardStartIdx);
                postfix = string.substring(wildCardStartIdx);
                flag = 1;
            }
        }

        public boolean match(String string) {
            boolean result = this.length == string.length();
            if (result) {
                // 와일드카드가 접두사
                if (flag == 0) {
                    result = string.endsWith(postfix);
                }
                // 와일드카드가 접미사
                else if (flag == 1) {
                    result = string.startsWith(prefix);
                }
            }
            return result;
        }

        public int compareTo(String string) {
            // 와일드카드가 접두사
            if (wildCardStartIdx == 0) {
                return postfix.compareTo(string.substring(wildCardEndIdx + 1));
            }
            // 와일드카드가 접미사
            else {
                return prefix.compareTo(string.substring(0, wildCardStartIdx));
            }
        }
    }

    public int[] 가사_검색_2(String[] words, String[] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        TempString[] array = Arrays.stream(words)
                .map(TempString::new)
                .sorted().toArray(TempString[]::new);
        TempString[] reverseArray = Arrays.stream(words)
                .map(string -> new StringBuilder(string).reverse().toString())
                .map(TempString::new)
                .sorted().toArray(TempString[]::new);

        for (int i = 0; i < n; i++) {
            Query query = new Query(queries[i]);
            TempString[] search;

            // 와일드카드가 접두사
            if (query.flag == 0) {
                search = reverseArray;
            }
            // 와일드카드가 접미사
            else {
                search = array;
            }

            // 탐색
            int first = findFirst(search, query, 0, search.length - 1);
            if (first == -1) {
                continue;
            }
            int last = findLast(search, query, 0, search.length - 1);

            answer[i] = last - first + 1;
        }

        return answer;
    }

    public int findFirst(TempString[] array, Query target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        // 일치하는 범위의 왼쪽 끝 찾기
        if (target.compareTo(array[mid].str) == 0
                && (mid == 0 || target.compareTo(array[mid - 1].str) > 0)) {
            return mid;
        }
        // 찾고자 하는 값이 중간값보다 사전순 왼쪽에 있다면 왼쪽 탐색
        else if (target.compareTo(array[mid].str) <= 0) {
            return findFirst(array, target, start, mid - 1);
        }
        // 사전순 오른쪽에 있다면 오른쪽 탐색
        else {
            return findFirst(array, target, mid + 1, end);
        }
    }

    public int findLast(TempString[] array, Query target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        // 일치하는 범위의 오른쪽 끝 찾기
        if (target.compareTo(array[mid].str) == 0
                && (mid == array.length - 1 || target.compareTo(array[mid + 1].str) < 0)) {
            return mid;
        }
        // 찾고자 하는 값이 중간값보다 사전순 왼쪽에 있다면 왼쪽 탐색
        else if (target.compareTo(array[mid].str) < 0) {
            return findLast(array, target, start, mid - 1);
        }
        // 사전순 오른쪽에 있다면 오른쪽 탐색
        else {
            return findLast(array, target, mid + 1, end);
        }
    }

    static class TempString implements Comparable<TempString> {
        String str;

        public TempString(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(TempString o) {
            if (this.str.length() != o.str.length()) {
                return this.str.length() - o.str.length();
            }
            return this.str.compareTo(o.str);
        }
    }

    static class Query {
        int flag;
        int length;
        int wildCardStartIdx;
        String query;

        public Query(String string) {
            this.length = string.length();
            this.wildCardStartIdx = string.indexOf("?");

            // 와일드카드가 접두사
            if (wildCardStartIdx == 0) {
                String reverse = new StringBuilder(string).reverse().toString();
                this.wildCardStartIdx = reverse.indexOf("?");
                this.query = reverse.substring(0, wildCardStartIdx);
                this.flag = 0;
            }
            // 와일드카드가 접미사
            else {
                this.query = string.substring(0, wildCardStartIdx);
                flag = 1;
            }
        }

        public int compareTo(String string) {
            if (this.length != string.length()) {
                return this.length - string.length();
            }
            return query.compareTo(string.substring(0, wildCardStartIdx));
        }
    }

    /**
     * 이진 탐색 문제에서 이진 탐색을 사용하지 않음 -> 시간초과
     * <p>
     * 사전순 뿐만 아니라 길이를 기준으로 정렬하도록 String 클래스를 포장한 TempString 클래스 구현
     * 먼저 정렬된_배열에서_특정_수_개수_구하기 문제처럼 findFirst, findLast 이진탐색 메서드를 구현
     * 검색 단어의 와일드 카드 위치에 따른 구분을 주기 위한 Query 클래스 구현
     * 접두/접미 와일드카드 위치에 따라 탐색을 달리하기 위해 검색 배열을 그대로 정렬하여 저장하고 단어순서를 뒤집어서 저장함
     * <p>
     * 해설에서는 각 단어 길이별로 나누어 리스트를 저장하고 각각 정렬함 -> 중간에 같은 풀이를 떠올리긴 했음
     * 또한 접미 와일드카드를 고려하여 단어 순서를 뒤집은 리스트도 저장함
     * 풀이과정은 비슷한데 해설은
     * 1. 파이썬의 bisect 라이브러리를 사용했고
     * 2. 와일드카드 탐색시 ?를 a와 z로 바꿔서 bisect_left, bisect_right 에 이용했음
     * ex) query: fro??, count_by_range(List list, String leftValue, String rightValue)
     * count_by_range(array[len], query.replace("?", "a") ,query.replace("?", "z"))
     * bisect_left(list, leftValue)
     * bisect_right(list, rightValue)
     */

    // 모든 단어를 길이별로 나누어 저장
    private Map<Integer, List<String>> arr = new HashMap<>();
    // 모든 단어를 길이별로 나누어 뒤집어 저장
    private Map<Integer, List<String>> reversedArr = new HashMap<>();

    public int[] 가사_검색_3(String[] words, String[] queries) {
        List<Integer> answer = new ArrayList<>();

        // 단어를 길이별로 분류하여 저장
        for (String word : words) {
            // 단어의 길이를 키값으로 이용
            int key = word.length();
            String reversed = new StringBuilder(word).reverse().toString();

            if (arr.containsKey(key)) {
                // 길이별로 저장
                arr.get(key).add(word);
                reversedArr.get(key).add(reversed);
            } else {
                // 초기화 이후
                arr.put(key, new ArrayList<>());
                reversedArr.put(key, new ArrayList<>());
                // 길이별로 저장
                arr.get(key).add(word);
                reversedArr.get(key).add(reversed);
            }
        }

        // 이진 탐색 수행을 위해 각 단어 리스트를 정렬
        arr.values().forEach(Collections::sort);
        reversedArr.values().forEach(Collections::sort);

        // 쿼리를 하나씩 확인하여 처리
        for (String query : queries) {
            int result = 0;
            // 접미사가 와일드카드라면 정방향 리스트 탐색
            if (query.charAt(0) != '?') {
                result = countByRange(arr.get(query.length()), query);
            }
            // 접두사가 와일드카드라면 역방향 리스트 탐색
            else {
                query = new StringBuilder(query).reverse().toString();
                result = countByRange(reversedArr.get(query.length()), query);
            }
            // 결과 저장
            answer.add(result);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 값이 [leftValue, rightValue] 인 데이터의 개수를 반환
    public int countByRange(List<String> arr, String query) {
        if (arr == null) {
            return 0;
        }

        String leftValue = query.replaceAll("\\?", "a");
        String rightValue = query.replaceAll("\\?", "z");

        int leftIndex = lowerBound(arr, leftValue, 0, arr.size());
        int rightIndex = upperBound(arr, rightValue, 0, arr.size());

        return rightIndex - leftIndex;
    }

    public int lowerBound(List<String> arr, String target, int start, int end) {
        // target 의 wildCard 는 a 로 변환됨
        while (start < end) {
            int mid = (start + end) / 2;
            // arr.get(mid) 가 target 보다 사전순 같거나 뒤에 있다면 이전 범위 탐색
            if (arr.get(mid).compareTo(target) >= 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public int upperBound(List<String> arr, String target, int start, int end) {
        // target 의 wildCard 는 z 로 변환됨
        while (start < end) {
            int mid = (start + end) / 2;
            // arr.get(mid) 가 target 보다 사전순 뒤에 있다면 이전 범위 탐색
            if (arr.get(mid).compareTo(target) > 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}
