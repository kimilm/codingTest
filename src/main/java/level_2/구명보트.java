package level_2;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42885
public class 구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int sum = Arrays.stream(people).sum();
        int answer = 0;
        int left = limit;
        int head = 0;
        int tail = people.length - 1;

        while(true) {
            if (sum <= limit) {
                ++answer;
                break;
            }

            int last = people[tail--];
            left -= last;
            sum -= last;

            while(people[head] <= left) {
                int first = people[head++];
                left -= first;
                sum -= first;
            }

            ++answer;
            left = limit;
        }

        return answer;
    }
}
