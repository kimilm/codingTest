package this_is_coding_test.ch10;

import org.assertj.core.api.Assertions;
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

    @Test
    void 팀_결성_test() {
        String result = gt.팀_결성(7, 8, new String[]{
                "0 1 3",
                "1 1 7",
                "0 7 6",
                "1 7 1",
                "0 3 7",
                "0 4 2",
                "0 1 1",
                "1 1 1"
        });

        String answer = "NO\nNO\nYES";

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 도시_분할_계획_test() {
        int result = gt.도시_분할_계획(7, 12, new String[]{
                "1 2 3",
                "1 3 2",
                "3 2 1",
                "2 5 2",
                "3 4 4",
                "7 3 6",
                "5 1 5",
                "1 6 2",
                "6 4 1",
                "6 5 3",
                "4 5 3",
                "6 7 4",
        });

        int answer = 8;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}