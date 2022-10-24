package level_2;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12953
public class N개의_최소공배수 {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int answer = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            answer = lcm(arr[i], answer);
        }
        return answer;
    }

    // 유클리드 호제법, a가 큰 수
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
