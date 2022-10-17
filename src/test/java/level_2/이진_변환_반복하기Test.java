package level_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class 이진_변환_반복하기Test {

    private final 이진_변환_반복하기 p = new 이진_변환_반복하기();

    @Test
    void solution() {
        int[] result = p.solution("110010101001");
        int[] answer = {3, 8};

        Assertions.assertThat(result).containsExactly(answer);
    }
}