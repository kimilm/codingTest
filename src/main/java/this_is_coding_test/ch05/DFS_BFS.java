package this_is_coding_test.ch05;

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
}
