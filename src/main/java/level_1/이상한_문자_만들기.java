package level_1;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12930
 * 연습문제 > 이상한 문자 만들기
 */
public class 이상한_문자_만들기 {
    public String solution(String s) {
        char[] answer = s.toCharArray();
        boolean flag = true;

        for (int i = 0; i < answer.length; ++i) {
            if (answer[i] != ' ') {
                if (flag) {
                    answer[i] = Character.toUpperCase(answer[i]);
                } else {
                    answer[i] = Character.toLowerCase(answer[i]);
                }
                flag = !flag;
            } else {
                flag = true;
            }
        }

        return new String(answer);
    }
}
