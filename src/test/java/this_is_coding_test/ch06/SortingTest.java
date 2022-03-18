package this_is_coding_test.ch06;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SortingTest {
    private final Sorting s = new Sorting();

    @Test
    void 위에서_아래로_test() {
        int[] result = s.위에서_아래로(3, "15 27 12");
        int[] answer = new int[]{27, 15, 12};

        Assertions.assertThat(result).containsExactly(answer);
    }
}