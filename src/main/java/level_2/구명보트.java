package level_2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/42885
public class 구명보트 {
    public int solution(int[] people, int limit) {
        Deque<Integer> deque = Arrays.stream(people)
                .boxed()
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        int sum = Arrays.stream(people).sum();
        int answer = 0;
        int left = limit;

        while(!deque.isEmpty()) {
            if (sum <= limit) {
                ++answer;
                break;
            }

            int last = deque.pollLast();
            left -= last;
            sum -= last;

            while(!deque.isEmpty() && deque.peek() <= left) {
                int first = deque.poll();
                left -= first;
                sum -= first;
            }

            ++answer;
            left = limit;
        }

        return answer;
    }
}
