package level_1;

// https://school.programmers.co.kr/learn/courses/30/lessons/132267?language=java
public class 콜라_문제 {
    public int solution(int a, int b, int n) {
        int answer = 0;

        while(n >= a) {
            int change = n / a * b;
            int left = n % a;

            n = change + left;
            answer += change;
        }

        return answer;
    }
}
