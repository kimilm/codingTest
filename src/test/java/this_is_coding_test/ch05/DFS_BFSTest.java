package this_is_coding_test.ch05;

import org.junit.jupiter.api.Test;

class DFS_BFSTest {

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
}