package this_is_coding_test.ch05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class DFS_BFS_Test {

    private final DFS_BFS db = new DFS_BFS();

    int[][] graph = new int[][]{
            {},         // node 0, not use
            {2, 3, 8},  // node 1
            {1, 7},     // node 2
            {1, 4, 5},  // node 3
            {3, 5},     // node 4
            {3, 4},     // node 5
            {7},        // node 6
            {2, 6, 8},  // node 7
            {1, 7}      // node 8
    };

    @Test
    void dfsTest() {
        boolean[] visited = new boolean[9];

        db.dfs(graph, 1, visited);
    }

    @Test
    void bfsTest() {
        boolean[] visited = new boolean[9];

        db.bfs(graph, 1, visited);
    }

    @Test
    void 음료수_얼려_먹기_test() {
        int result = db.음료수_얼려_먹기(4, 5, new String[]{"00110", "00011", "11111", "00000"});
        int answer = 3;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 음료수_얼려_먹기_2_test() {
        String[] tray = {
                "00000111100000",
                "11111101111110",
                "11011101101110",
                "11011101100000",
                "11011111111111",
                "11011111111100",
                "11000000011111",
                "01111111111111",
                "00000000011111",
                "01111111111000",
                "00011111111000",
                "00000001111000",
                "11111111110011",
                "11100011111111",
                "11100011111111"
        };
        int result = db.음료수_얼려_먹기_2(15, 14, tray);
        int answer = 8;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 미로_탈출_test() {
        String[] maze = {
                "101010",
                "111111",
                "000001",
                "111111",
                "111111"
        };
        int result = db.미로_탈출(5, 6, maze);
        int answer = 10;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 미로_탈출_2_test() {
        String[] maze = {
                "110",
                "010",
                "011",
        };
        int result = db.미로_탈출_2(3, 3, maze);
        int answer = 5;

        Assertions.assertThat(result).isEqualTo(answer);
    }
}