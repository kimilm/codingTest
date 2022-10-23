package level_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class 구명보트Test {

    private final 구명보트 g = new 구명보트();

    @Test
    void solution() {
        int result = g.solution(new int[]{70, 80, 50}, 100);
        int answer = 3;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}