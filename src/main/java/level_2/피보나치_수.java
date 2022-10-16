package level_2;

// https://school.programmers.co.kr/learn/courses/30/lessons/12945
public class 피보나치_수 {
    public int solution(int n) {
        int[] fibo = new int[100_001];
        fibo[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1234567;
        }

        return fibo[n];
    }

    final int[] fiboArray = new int[100_001];

    public int solution2(int n) {
        fiboArray[1] = 1;
        fiboArray[2] = 1;

        return fibo(n);
    }

    public int fibo(int n) {
        if (fiboArray[n] == 0) {
            fiboArray[n] = (fibo(n - 1) + fibo(n - 2)) % 1234567;
        }
        return fiboArray[n];
    }

    /**
     * Solution 01, 재귀 없이                      Solution 02, 재귀 with array
     * 테스트 1 〉	통과 (0.22ms, 78.3MB)         테스트 1 〉	통과 (0.02ms, 79.2MB)
     * 테스트 2 〉	통과 (0.24ms, 75.9MB)         테스트 2 〉	통과 (0.01ms, 74.3MB)
     * 테스트 3 〉	통과 (0.29ms, 77.3MB)         테스트 3 〉	통과 (0.01ms, 71.9MB)
     * 테스트 4 〉	통과 (0.28ms, 76.8MB)         테스트 4 〉	통과 (0.02ms, 74.9MB)
     * 테스트 5 〉	통과 (0.27ms, 73.6MB)         테스트 5 〉	통과 (0.02ms, 78.6MB)
     * 테스트 6 〉	통과 (0.24ms, 77.8MB)         테스트 6 〉	통과 (0.01ms, 73.3MB)
     * 테스트 7 〉	통과 (0.27ms, 86.4MB)         테스트 7 〉	통과 (0.36ms, 73.4MB)
     * 테스트 8 〉	통과 (0.28ms, 75.7MB)         테스트 8 〉	통과 (0.26ms, 77.2MB)
     * 테스트 9 〉	통과 (0.22ms, 76.7MB)         테스트 9 〉	통과 (0.09ms, 71.1MB)
     * 테스트 10 〉	통과 (0.29ms, 71.7MB)         테스트 10 〉	통과 (0.30ms, 76MB)
     * 테스트 11 〉	통과 (0.24ms, 77MB)           테스트 11 〉	통과 (0.12ms, 74.8MB)
     * 테스트 12 〉	통과 (0.21ms, 77.8MB)         테스트 12 〉	통과 (0.14ms, 77.5MB)
     * 테스트 13 〉	통과 (2.38ms, 77.4MB)         테스트 13 〉	통과 (13.48ms, 82.4MB)
     * 테스트 14 〉	통과 (2.32ms, 74.1MB)         테스트 14 〉	통과 (13.07ms, 85.5MB)
     */
}
