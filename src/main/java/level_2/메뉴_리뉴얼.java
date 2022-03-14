package level_2;

import java.util.*;
import java.util.function.Function;
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
        String[] answer = filteredList.stream()
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

    /**
     * 보고 공부할 다른 사용자 풀이 01
     */
    static HashMap<String, Integer> map;
    static int m;

    public String[] solution_use_PriorityQueue(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            m = 0;
            for (int j = 0; j < orders.length; j++) {
                find(0, "", course[i], 0, orders[j]);
            }
            for (String s : map.keySet()) {
                if (map.get(s) == m && m > 1) {
                    pq.offer(s);
                }
            }
        }
        String ans[] = new String[pq.size()];
        int k = 0;
        while (!pq.isEmpty()) {
            ans[k++] = pq.poll();
        }
        return ans;
    }

    static void find(int cnt, String str, int targetNum, int idx, String word) {
        if (cnt == targetNum) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String temps = "";
            for (int i = 0; i < c.length; i++) temps += c[i];
            map.put(temps, map.getOrDefault(temps, 0) + 1);
            m = Math.max(m, map.get(temps));
            return;
        }
        for (int i = idx; i < word.length(); i++) {
            char now = word.charAt(i);
            find(cnt + 1, str + now, targetNum, i + 1, word);
        }
    }

    /**
     * 보고 공부할 다른 사용자 풀이 02
     */
    public String[] solution_use_Menu_Class(String[] orders, int[] course) {
        Menus menus = Arrays.stream(orders)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Menus::new));

        List<Integer> courses = Arrays.stream(course)
                .boxed()
                .collect(Collectors.toList());

        return menus.getMenusByCourses(courses).stream()
                .map(Menu::getMenu)
                .toArray(String[]::new);
    }

    private static class Menus {
        private final List<Menu> menusOfConsumers;

        public Menus(List<String> menus) {
            this.menusOfConsumers = menus.stream()
                    .sorted()
                    .map(Menu::new)
                    .collect(Collectors.toList());
        }

        public List<Menu> getMenusByCourses(List<Integer> courses) {
            Map<Integer, Map<Menu, Long>> menus = menusOfConsumers.stream()
                    .map(menu -> getCombinations(menu, courses))
                    .flatMap(Collection::stream)
                    .collect(Collectors.groupingBy(Menu::length, Collectors.groupingBy(Function.identity(), Collectors.counting())));

            return menus.entrySet().stream()
                    .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                            entry.getValue().entrySet().stream()
                                    .max(Map.Entry.comparingByValue())
                                    .map(Map.Entry::getValue)
                                    .map(Long::intValue)
                                    .orElse(0)))
                    .filter(entry -> entry.getValue() > 1)
                    .flatMap(entry -> menus.get(entry.getKey()).entrySet().stream()
                            .filter(innerEntry -> innerEntry.getValue().intValue() == entry.getValue())
                            .map(Map.Entry::getKey))
                    .sorted()
                    .collect(Collectors.toList());
        }

        private Set<Menu> getCombinations(Menu menu, List<Integer> courses) {
            Set<Menu> menus = new HashSet<>();
            courses.forEach(i -> getCombination(menu, i, 0, new HashSet<>(), menus));
            return menus;
        }

        private void getCombination(Menu menu, int course, int index, Set<Character> menuRepository, Set<Menu> menus) {
            if (course == 0) {
                menus.add(menuRepository.stream()
                        .map(String::valueOf)
                        .sorted()
                        .collect(Collectors.collectingAndThen(Collectors.joining(), Menu::new)));
                return;
            }

            String menuName = menu.getMenu();

            for (int i = index, length = menuName.length(); i < length; i++) {
                menuRepository.add(menuName.charAt(i));
                getCombination(menu, course - 1, i + 1, menuRepository, menus);
                menuRepository.remove(menuName.charAt(i));
            }
        }
    }

    public static class Menu implements Comparable<Menu> {
        private final String menu;

        public Menu(String menu) {
            this.menu = menu;
        }

        public String getMenu() {
            return menu;
        }

        public int length() {
            return menu.length();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Menu menu1 = (Menu) o;
            return Objects.equals(menu, menu1.menu);
        }

        @Override
        public int hashCode() {
            return Objects.hash(menu);
        }

        @Override
        public int compareTo(Menu other) {
            return this.menu.compareTo(other.menu);
        }
    }
}