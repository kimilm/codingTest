package level_2;

import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/42842
public class 카펫 {
    public int[] solution(int brown, int yellow) {
        int width = 0;
        int height = 0;
        int carpet = brown + yellow;
        List<Integer> divisors = getDivisors(carpet);

        for (Integer divisor : divisors) {
            width = carpet / divisor;
            height = divisor;

            if ((width + height - 2) * 2 == brown) {
                break;
            }
        }

        return new int[]{width, height};
    }

    public List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        double sqrt = Math.sqrt(n);

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }

        return divisors;
    }
}
