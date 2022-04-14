package this_is_coding_test.ch10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTheoryTest {

    private final GraphTheory gt = new GraphTheory();

    @Test
    void 서로소_집합() {
        gt.서로소_집합();
    }

    @Test
    void 무방향_그래프의_사이클_판별() {
        gt.무방향_그래프의_사이클_판별();
    }

    @Test
    void kruskal() {
        System.out.println(gt.kruskal());
    }

    @Test
    void topologySort() {
        gt.topologySort();
    }
}