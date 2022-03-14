package level_2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 * 2021 KAKAO BLIND RECRUITMENT > 메뉴 리뉴얼
 */
public class 메뉴_리뉴얼 {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> courseMap = new HashMap<>();

        for (String order : orders) {
            // 주문으로 들어온 메뉴 사전순 정렬
            char[] orderArray = order.toCharArray();
            Arrays.sort(orderArray);

            int n = order.length();
            boolean[] visit = new boolean[n];

            // course 의 수 만큼 나올 수 있는 조합을 계산
            for (int r : course) {
                if (r > n) {
                    continue;
                }

                List<String> courseList = combination(orderArray, visit, 0, n, r);
                courseList.forEach(value -> {
                    if (courseMap.containsKey(value)) {
                        courseMap.put(value, courseMap.get(value) + 1);
                    } else {
                        courseMap.put(value, 1);
                    }
                });
            }
        }

        // 한 번만 나온 조합 제거
        List<Map.Entry<String, Integer>> filteredList = courseMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toList());

        // 각 세트 조합당 나온 최대 갯수 저장
        Map<Integer, Integer> maxValueMap = new HashMap<>();
        for (int size : course) {
            int maxValue = filteredList.stream()
                    .filter(entry -> entry.getKey().length() == size)
                    .mapToInt(entry -> entry.getValue())
                    .max()
                    .orElseGet(() -> 0);
            maxValueMap.put(size, maxValue);
        }

        // 조건을 만족하는 세트 문자열 배열로 저장
        String[] answer = courseMap.entrySet().stream()
                .filter(entry -> maxValueMap.get(entry.getKey().length()) == entry.getValue())
                .map(entry -> entry.getKey())
                .sorted()
                .toArray(String[]::new);

        return answer;
    }

    // 주문에서 나올 수 있는 조합
    public List<String> combination(char[] arr, boolean[] visited, int start, int n, int r) {
        List<String> resultList = new ArrayList<>();

        if (r == 0) {
            resultList.add(combinationResult(arr, visited, n));
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            resultList.addAll(combination(arr, visited, i + 1, n, r - 1));
            visited[i] = false;
        }

        return resultList;
    }

    public String combinationResult(char[] arr, boolean[] visited, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}