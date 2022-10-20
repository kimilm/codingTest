package level_2;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/132265
public class 롤케이크_자르기 {
    // 1, 9, 19 fail, 85/100
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
        int answer = 0;

        for (int i = frontValues.size() - 1; i > -1; --i) {
            answer = backValues.get(i) - frontValues.get(i);

            if (answer > -1) {
                break;
            }
        }

        return answer;
    }

    // 1, 12 pass, other time over fail
    public int solution2(int[] topping) {
        int splitLimit = topping.length - 1;
        int answer = 0;

        for (int i = 1; i < splitLimit; i++) {
            long front = Arrays.stream(Arrays.copyOf(topping, i)).distinct().count();
            long back = Arrays.stream(Arrays.copyOfRange(topping, i, topping.length)).distinct().count();

            if (front != back) {
                continue;
            }

            ++answer;
        }

        return answer;
    }
}
