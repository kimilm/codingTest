package this_is_coding_test.ch05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
}