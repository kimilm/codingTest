package level_2;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12985
 * 2017 팁스타운 > 예상 대진표
 */
public class 예상_대진표 {
    public int solution(int n, int a, int b) {
        int answer = 1;

        while ((++a / 2) != (++b / 2)) {
            ++answer;
            a /= 2;
            b /= 2;
        }

        return answer;
    }
}
