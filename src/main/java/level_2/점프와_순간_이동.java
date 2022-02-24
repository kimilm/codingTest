package level_2;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12980
 * Summer/Winter Coding(~2018) > 점프와 순간 이동
 */
public class 점프와_순간_이동 {
    public int solution(int n) {
        int ans = 1;

        while(n > 1) {
            ans += n % 2;
            n /= 2;
        }

        return ans;
    }
}
