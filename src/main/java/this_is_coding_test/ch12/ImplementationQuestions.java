package this_is_coding_test.ch12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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

        for (char token : s.toCharArray()) {
            if (token >= '0' && token <= '9') {
                number += token - '0';
            } else {
                charList.add(token);
            }
        }
        Collections.sort(charList);

        // String str = charList.stream().map(String::valueOf).collect(Collectors.joining());
        StringBuilder sb = new StringBuilder();
        for (Character c : charList) {
            sb.append(c);
        }

        sb.append(number);

        return sb.toString();
    }

    /**
     * 난이도 중하
     * 1 <= s의 길이 <= 1_000, s는 소문자로만 구성됨
     * 제한) 시간: 1초, 메모리: 256MB
     * https://programmers.co.kr/learn/courses/30/lessons/60057
     */
    public int 문자열_압축(String s) {
        int length = s.length();
        int answer = length;

        for (int i = 1; i <= length / 2; ++i) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            StringBuilder str = new StringBuilder(s);
            String compress = s.substring(0, i);

            while (str.length() >= compress.length()) {
                String subStr = str.substring(0, i);
                if (subStr.equals(compress)) {
                    ++count;
                    str.delete(0, i);
                } else {
                    sb.append(compress(count, compress));
                    compress = subStr;
                    count = 0;
                }
            }

            sb.append(compress(count, compress));
            sb.append(str);

            answer = Integer.min(sb.length(), answer);
        }
        return answer;
    }

    public String compress(int count, String compress) {
        if (count != 1) {
            return count + compress;
        } else {
            return compress;
        }
    }

    /**
     * 이전에 프로그래머스에서 풀었던 풀이, 이게 더 빠르다
     * 문제랑 별개로 배운 것 -> Math.log10() + 1 자릿수 계산 오..
     */
    public int 문자열_압축_2(String s) {
        int compLen = 0;
        int compStartIndex;
        int compCount;
        String comp;
        StringBuilder sb;
        Stack<String> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        while (compLen++ < s.length() / 2) {
            sb = new StringBuilder(s);
            comp = s.substring(0, compLen);
            compStartIndex = 0;
            compCount = 1;

            for (int i = compLen; i < s.length(); i += compLen) {
                if (i + compLen > s.length())
                    break;

                if (s.substring(i, i + compLen).compareTo(comp) != 0) {
                    if (compCount != 1) {
                        stack.push(comp);
                        stack.push(Integer.toString(compCount));
                        stack.push(Integer.toString(compCount * compLen + compStartIndex));
                        stack.push(Integer.toString(compStartIndex));
                    }
                    comp = s.substring(i, i + compLen);
                    compCount = 1;
                    compStartIndex = i;
                } else {
                    ++compCount;
                }
            }
            if (compCount != 1) {
                stack.push(comp);
                stack.push(Integer.toString(compCount));
                stack.push(Integer.toString(compCount * compLen + compStartIndex));
                stack.push(Integer.toString(compStartIndex));
            }

            while (!stack.isEmpty()) {
                sb.replace(Integer.parseInt(stack.pop()), Integer.parseInt(stack.pop()), stack.pop() + stack.pop());
            }
            list.add(sb.length());
        }

        if (compLen == 1)
            list.add(1);

        Collections.sort(list);

        return list.get(0);
    }

    /**
     * 책에 나온 풀이, 1번이랑 같은 방법인데 더 빠르다.. 1번은 delete 때문에 느린듯
     */
    public int 문자열_압축_3(String s) {
        AtomicInteger answer = new AtomicInteger(s.length());

        // 1개 단위(step) 부터 압축 단위를 늘려가며 확인
        IntStream.range(1, s.length() / 2 + 1).forEach(step -> {
            StringBuilder compressed = new StringBuilder();
            // 앞에서부터 step 까지의 문자열 추출
            String prev = s.substring(0, step);
            int count = 1;

            // 단위(step) 크기만큼 증가시키며 이전 문자열과 비교
            for (int i = step; i < s.length(); i += step) {
                String substr = "";
                if (i + step > s.length()) {
                    substr = s.substring(i);
                } else {
                    substr = s.substring(i, i + step);
                }
                // 이전 상태와 동일하다면 압축 횟수 증가
                if (prev.equals(substr)) {
                    ++count;
                }
                // 다른 문자열이 나왔다면 (더 이상 압축하지 못하는 경우라면)
                else {
                    if (count != 1) {
                        compressed.append(count).append(prev);
                    } else {
                        compressed.append(prev);
                    }
                    prev = substr;
                    count = 1;
                }
            }

            // 남아있는 문자열에 대해서 처리
            if (count != 1) {
                compressed.append(count).append(prev);
            } else {
                compressed.append(prev);
            }
            // 만들어지는 압축 문자열이 가장 짧은 것이 정답
            answer.set(Integer.min(answer.get(), compressed.length()));
        });

        return answer.get();
    }
}
