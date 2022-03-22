package this_is_coding_test.ch08;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {

    private final DynamicProgramming dp = new DynamicProgramming();

    @Test
    void 일로_만들기() {
        int result = dp.일로_만들기(26);
        int answer = 3;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}