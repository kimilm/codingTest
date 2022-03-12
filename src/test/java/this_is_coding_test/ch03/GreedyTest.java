package this_is_coding_test.ch03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GreedyTest {
    private final Greedy greedy = new Greedy();

    @Test
    void 거스름돈_test() {
        Assertions.assertThat(greedy.거스름돈(1260)).isEqualTo(6);
    }

    @Test
    void 큰_수의_법칙_test() {
        int result = greedy.큰_수의_법칙(5, 8, 3, new int[]{2, 4, 5, 4, 6});
        Assertions.assertThat(result).isEqualTo(46);

        result = greedy.큰_수의_법칙_2(5, 8, 3, new int[]{2, 4, 5, 4, 6});
        Assertions.assertThat(result).isEqualTo(46);
    }

    @Test
    void 숫자_카드_게임_test() {
        int result = greedy.숫자_카드_게임(3, 3, new int[][]{{3, 1, 2}, {4, 1, 4}, {2, 2, 2}});
        Assertions.assertThat(result).isEqualTo(2);

        result = greedy.숫자_카드_게임(2, 4, new int[][]{{7, 3, 1, 8}, {3, 3, 3, 4}});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    void 일이_될_때까지_test() {
        int result = greedy.일이_될_때까지(17, 4);
        Assertions.assertThat(result).isEqualTo(3);

        result = greedy.일이_될_때까지(25, 5);
        Assertions.assertThat(result).isEqualTo(2);
    }
}