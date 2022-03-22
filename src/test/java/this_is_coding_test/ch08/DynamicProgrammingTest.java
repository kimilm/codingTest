package this_is_coding_test.ch08;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {

    private final DynamicProgramming dp = new DynamicProgramming();

    @Test
    void 일로_만들기_test() {
        int result = dp.일로_만들기(26);
        int answer = 3;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 일로_만들기_2_test() {
        int result = dp.일로_만들기_2(26);
        int answer = 3;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 개미_전사_test() {
        int result = dp.개미_전사(4, "1 3 1 5");
        int answer = 8;

        Assertions.assertThat(result).isEqualTo(answer);

        result = dp.개미_전사(8, "1 3 1 5 2 1 1 1");
        answer = 10;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 개미_전사_2_test() {
        int result = dp.개미_전사_2(4, "1 3 1 5");
        int answer = 8;

        Assertions.assertThat(result).isEqualTo(answer);

        result = dp.개미_전사_2(8, "1 3 1 5 2 1 1 1");
        answer = 10;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 바닥_공사_test() {
        int result = dp.바닥_공사(3);
        int answer = 5;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}