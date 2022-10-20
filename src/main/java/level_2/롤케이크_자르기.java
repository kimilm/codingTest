package level_2;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/132265
public class 롤케이크_자르기 {
    // 9 fail, 95/100
    public int solution(int[] topping) {
        Map<Integer, Integer> frontTopping = new LinkedHashMap<>();
        Map<Integer, Integer> backTopping = new LinkedHashMap<>();

        for (int i = 0; i < topping.length; i++) {
            int reverseIndex = topping.length - i - 1;

            if (!frontTopping.containsKey(topping[i])) {
                frontTopping.put(topping[i], i);
            }

            if (!backTopping.containsKey(topping[reverseIndex])) {
                backTopping.put(topping[reverseIndex], reverseIndex);
            }
        }

        List<Integer> frontValues = new ArrayList<>(frontTopping.values());
        List<Integer> backValues = new ArrayList<>(backTopping.values());
        int answer = (int)1e9;

        for (int i = 0; i < frontValues.size(); i++) {
            answer = Integer.min(answer, Math.abs(frontValues.get(i) - backValues.get(i)));
        }

        return answer;
    }

    // pass
    public int solution2(int[] topping) {
        int splitLimit = topping.length;
        int answer = 0;

        Map<Integer, Integer> back = new HashMap<>();

        for (int i : topping) {
            back.put(i, back.getOrDefault(i, 0) + 1);
        }

        Set<Integer> front = new HashSet<>();

        for (int i = 1; i < splitLimit; i++) {
            int top = topping[i - 1];

            front.add(top);
            back.put(top, back.get(top) - 1);

            if (back.get(top) == 0) {
                back.remove(top);
            }

            if (front.size() != back.size()) {
                continue;
            }

            ++answer;
        }

        return answer;
    }

    /**
     * 테스트 1 〉	통과 (12.60ms, 80.2MB)
     * 테스트 2 〉	통과 (68.91ms, 109MB)
     * 테스트 3 〉	통과 (27.39ms, 96.1MB)
     * 테스트 4 〉	통과 (42.35ms, 83.5MB)
     * 테스트 5 〉	통과 (98.96ms, 139MB)
     * 테스트 6 〉	통과 (175.30ms, 166MB)
     * 테스트 7 〉	통과 (184.39ms, 187MB)
     * 테스트 8 〉	통과 (172.47ms, 183MB)
     * 테스트 9 〉	통과 (143.20ms, 169MB)
     * 테스트 10 〉	통과 (155.57ms, 167MB)
     * 테스트 11 〉	통과 (25.18ms, 96.9MB)
     * 테스트 12 〉	통과 (15.40ms, 82.4MB)
     * 테스트 13 〉	통과 (194.96ms, 168MB)
     * 테스트 14 〉	통과 (147.70ms, 173MB)
     * 테스트 15 〉	통과 (189.14ms, 179MB)
     * 테스트 16 〉	통과 (163.39ms, 173MB)
     * 테스트 17 〉	통과 (163.51ms, 175MB)
     * 테스트 18 〉	통과 (163.72ms, 189MB)
     * 테스트 19 〉	통과 (155.41ms, 171MB)
     * 테스트 20 〉	통과 (173.74ms, 167MB)
     */
}
