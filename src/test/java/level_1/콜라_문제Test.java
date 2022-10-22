package level_1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class 콜라_문제Test {

    private 콜라_문제 c = new 콜라_문제();

    @Test
    void solution() {
        int result = c.solution(2, 1, 20);
        int answer = 19;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}