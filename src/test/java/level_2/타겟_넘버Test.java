package level_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class 타겟_넘버Test {

    private final 타겟_넘버 target = new 타겟_넘버();

    @Test
    void solution() {
        int answer = target.solution(new int[]{4, 1, 2, 1}, 4);
        Assertions.assertThat(answer).isEqualTo(2);
    }

    @Test
    void 타겟_넘버_순열() {
        List<int[]> numbers = target.타겟_넘버_조합(new int[] {4, 1, 2, 1}, 4, 2);
        Assertions.assertThat(numbers).hasSize(6);
    }
}