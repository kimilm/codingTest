package this_is_coding_test.ch12;

public class ImplementationQuestions {

    /**
     * 난이도 하
     * 10 <= N <= 99_999_999, N의 자릿수는 항상 짝수
     * 제한) 시간: 1초, 메모리: 256MB
     * https://www.acmicpc.net/problem/18406
     */
    public String 럭키_스트레이트(long N) {
        String str = String.valueOf(N);
        String left = str.substring(0, str.length() / 2);
        String right = str.substring(str.length() / 2);

        return strSum(left) != strSum(right) ? "READY" : "LUCKY";
    }

    int strSum(String str) {
        return str.chars().map(num -> num - '0').sum();
    }
}
