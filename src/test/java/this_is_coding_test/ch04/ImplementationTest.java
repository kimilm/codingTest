package this_is_coding_test.ch04;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ImplementationTest {
    private final Implementation imp = new Implementation();

    @Test
    void 상하좌우_test() {
        String result = imp.상하좌우(5, "R R R U D D");
        String answer = "3 4";

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 상하좌우_2_test() {
        String result = imp.상하좌우_2(5, "R R R U D D");
        String answer = "3 4";

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 시각_test() {
        int result = imp.시각(5);
        int answer = 11475;

        Assertions.assertThat(result).isEqualTo(answer);

        result = imp.시각_2(5);
        answer = 11475;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 왕실의_나이트_test() {
        int result = imp.왕실의_나이트("a1");
        int answer = 2;

        Assertions.assertThat(result).isEqualTo(answer);

        result = imp.왕실의_나이트("c3");
        answer = 8;

        Assertions.assertThat(result).isEqualTo(answer);

        result = imp.왕실의_나이트("b3");
        answer = 6;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 왕실의_나이트_2_test() {
        int result = imp.왕실의_나이트_2("a1");
        int answer = 2;

        Assertions.assertThat(result).isEqualTo(answer);

        result = imp.왕실의_나이트_2("c3");
        answer = 8;

        Assertions.assertThat(result).isEqualTo(answer);

        result = imp.왕실의_나이트_2("b3");
        answer = 6;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}