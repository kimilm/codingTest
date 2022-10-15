package level_2;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12941
public class 최솟값_만들기 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length - 1;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[n - i];
        }

        return answer;
    }
}
