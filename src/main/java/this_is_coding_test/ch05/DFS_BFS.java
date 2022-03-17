package this_is_coding_test.ch05;

import java.util.LinkedList;
import java.util.Queue;

public class DFS_BFS {

    /**
     * DFS recursive
     */
    public void dfs(int[][] graph, int v, boolean[] visited) {
        // 방문처리 이후 출력
        visited[v] = true;
        System.out.print(v + " ");

        // 현재 노드와 연결된 다른 노드 재귀적 방문
        for (int i : graph[v]) {
            if (!visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    /**
     * BFS queue
     */
    public void bfs(int[][] graph, int v, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.print(v + " ");

            for (int i : graph[v]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }
}
