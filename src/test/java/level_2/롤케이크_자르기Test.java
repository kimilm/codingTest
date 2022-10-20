package level_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class 롤케이크_자르기Test {

    private final 롤케이크_자르기 r = new 롤케이크_자르기();

    @Test
    void solution() {
        int result = r.solution(new int[]{1, 2, 1, 3, 1, 4, 1, 2});
        int answer = 2;
        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void solution_test2() {
        int result = r.solution(new int[]{1, 1, 1, 1, 1, 1, 1});
        int answer = 6;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void solution_test3() {
        int result = r.solution(new int[]{1, 2, 3, 1, 4});
        int answer = 0;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void solution_test4() {
        int result = r.solution(new int[]{1, 1, 1, 1, 2, 2});
        int answer = 1;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void solution2() {
        int result = r.solution2(new int[]{1, 2, 1, 3, 1, 4, 1, 2});
        int answer = 2;
        Assertions.assertThat(result).isEqualTo(2);
    }
}