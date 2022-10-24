package level_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class N개의_최소공배수Test {

    private final N개의_최소공배수 n = new N개의_최소공배수();

    @Test
    void solution() {
        int result = n.solution(new int[] {2, 6, 8, 14});
        int answer = 168;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}