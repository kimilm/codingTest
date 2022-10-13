package level_1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class 삼총사Test {

    private final 삼총사 s = new 삼총사();

    @Test
    void solution() {
        int result = s.solution(new int[]{-3, -2, -1, 0, 1, 2, 3});
        Assertions.assertThat(result).isEqualTo(5);
    }
}