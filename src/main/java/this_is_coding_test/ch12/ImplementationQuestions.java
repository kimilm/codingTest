package this_is_coding_test.ch12;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 난이도 하
     * 1 <= S의 길이 <= 10_000
     * 제한) 시간: 1초, 메모리: 256MB
     */
    public String 문자열_재정렬(String s) {
        List<Character> charList = new ArrayList<>();
        int number = 0;

        for(char token : s.toCharArray()) {
            if (token >= '0' && token <= '9') {
                number += token - '0';
            } else {
                charList.add(token);
            }
        }
        Collections.sort(charList);

        // String str = charList.stream().map(String::valueOf).collect(Collectors.joining());
        StringBuilder sb = new StringBuilder();
        for(Character c : charList) {
            sb.append(c);
        }

        sb.append(number);

        return sb.toString();
    }
}
