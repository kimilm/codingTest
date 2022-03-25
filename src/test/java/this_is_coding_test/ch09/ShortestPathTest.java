package this_is_coding_test.ch09;

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
}