package level_2;

import java.util.Stack;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12973
 * 2017 팁스타운 > 짝지어 제거하기
 */
public class 짝지어_제거하기 {
    public int solution(String s) {
        Stack<Character> st = new Stack<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (!st.empty() && st.peek().equals(c)) {
                st.pop();
            } else {
                st.push(c);
            }
        }

        return st.size() != 0 ? 0 : 1;
    }
}
