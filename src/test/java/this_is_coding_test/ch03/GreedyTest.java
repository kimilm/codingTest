package this_is_coding_test.ch03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GreedyTest {
    private final Greedy greedy = new Greedy();

    @Test
    void 거스름돈_test() {
        Assertions.assertThat(greedy.거스름돈(1260)).isEqualTo(6);
    }

}