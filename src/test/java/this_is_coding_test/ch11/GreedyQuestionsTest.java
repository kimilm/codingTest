package this_is_coding_test.ch11;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreedyQuestionsTest {

    private final GreedyQuestions gq = new GreedyQuestions();

    @Test
    void 모험가_길드_test() {
        int result = gq.모험가_길드(5, new int[]{2, 3, 1, 2, 2});
        int answer = 2;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 모험가_길드_2_test() {
        int result = gq.모험가_길드_2(5, new int[]{2, 3, 1, 2, 2});
        int answer = 2;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 곱하기_혹은_더하기_test() {
        int result = gq.곱하기_혹은_더하기("02984");
        int answer = 576;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}