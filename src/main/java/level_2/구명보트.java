package level_2;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42885
public class 구명보트 {
    // 보트에 탈 수 있는 사람은 최대 두명
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0;
        int head = 0;
        int tail = people.length - 1;

        while(head <= tail) {
            if (people[head] + people[tail] <= limit) {
                ++head;
            }
            --tail;
            ++answer;
        }

        return answer;
    }
}
