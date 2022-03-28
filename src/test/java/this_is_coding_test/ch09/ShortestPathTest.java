package this_is_coding_test.ch09;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathTest {

    private final ShortestPath sp = new ShortestPath();

    @Test
    void dijkstraTest() {
        sp.dijkstra();
    }

    @Test
    void dijkstra2Test() {
        sp.dijkstra2();
    }

    @Test
    void dijkstra3Test() {
        sp.dijkstra3();
    }

    @Test
    void dijkstra4Test() {
        sp.dijkstra4();
    }

    @Test
    void floydWarshallTest() {
        sp.floydWarshall();
    }

    @Test
    void 미래_도시_test() {
        int result = sp.미래_도시(5, 7, new String[]{"1 2", "1 3", "1 4", "2 4", "3 4", "3 5", "4 5", "4 5"});
        int answer = 3;

        Assertions.assertThat(result).isEqualTo(answer);

        result = sp.미래_도시(4, 2, new String[]{"1 3", "2 4", "3 4"});
        answer = -1;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}