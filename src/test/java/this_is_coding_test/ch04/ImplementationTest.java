package this_is_coding_test.ch04;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ImplementationTest {
    private final Implementation imp = new Implementation();

    @Test
    void 상하좌우() {
        String result = imp.상하좌우(5, "R R R U D D");
        String answer = "3 4";

        Assertions.assertThat(result).isEqualTo(answer);
    }
}