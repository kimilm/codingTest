package this_is_coding_test.ch05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    /**
     * 난이도 중하
     * 1 <= N, M, 1_000
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 음료수_얼려_먹기(int n, int m, String[] trays) {
        char[][] graph = Arrays.stream(trays)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        Stack<int[]> stack = new Stack<>();

        int answer = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (graph[i][j] == '1') {
                    continue;
                }

                stack.push(new int[]{i, j});

                while (!stack.isEmpty()) {
                    int[] entity = stack.pop();
                    int x = entity[0];
                    int y = entity[1];

                    if (x > -1 && x < n && y > -1 && y < m) {
                        if (graph[x][y] != '1') {
                            graph[x][y] = '1';
                            // 상하좌우를 다 봐야 요철을 확인할 수 있다.
                            // 오른쪽, 아래 2개 방향만 확인하면 놓치는 부분이 생김
                            stack.push(new int[]{x + 1, y});
                            stack.push(new int[]{x - 1, y});
                            stack.push(new int[]{x, y + 1});
                            stack.push(new int[]{x, y - 1});
                        }
                    }
                }

                ++answer;
            }
        }

        return answer;
    }

    /**
     * DFS 를 사용하여 해결하는 방법
     */
    public int 음료수_얼려_먹기_2(int n, int m, String[] trays) {
        char[][] graph = Arrays.stream(trays)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int answer = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (dfs_for_음료수_얼려_먹기(graph, i, j, n, m)) {
                    ++answer;
                }
            }
        }

        return answer;
    }

    public boolean dfs_for_음료수_얼려_먹기(char[][] graph, int x, int y, int n, int m) {
        // 범위 초과시 false
        if (x <= -1 || x >= n || y <= -1 || y >= m) {
            return false;
        }

        // 해당 노드를 방문하지 않았다면
        if (graph[x][y] != '1') {
            // 방문 처리
            graph[x][y] = '1';

            // 상 하 좌 우 위치 재귀적 호출
            dfs_for_음료수_얼려_먹기(graph, x - 1, y, n, m);
            dfs_for_음료수_얼려_먹기(graph, x + 1, y, n, m);
            dfs_for_음료수_얼려_먹기(graph, x, y - 1, n, m);
            dfs_for_음료수_얼려_먹기(graph, x, y + 1, n, m);

            return true;
        }

        return false;
    }

    /**
     * 난이도 중하
     * 4 <= n, m <= 200
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 미로_탈출(int n, int m, String[] maze) {
        char[][] graph = Arrays.stream(maze)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        Queue<int[]> queue = new LinkedList<>();

        int arriveX = n - 1;
        int arriveY = m - 1;

        queue.add(new int[]{0, 0, 1});

        int answer = 0;

        while (!queue.isEmpty()) {
            int[] entity = queue.poll();
            int x = entity[0];
            int y = entity[1];
            int dist = entity[2];

            if (x == arriveX && y == arriveY) {
                answer = dist;
                break;
            }

            if (x > -1 && x < n && y > -1 && y < m) {
                if (graph[x][y] != 0) {
                    graph[x][y] = 0;

                    queue.add(new int[]{x + 1, y, dist + 1});
                    queue.add(new int[]{x - 1, y, dist + 1});
                    queue.add(new int[]{x, y + 1, dist + 1});
                    queue.add(new int[]{x, y - 1, dist + 1});
                }
            }
        }

        return answer;
    }

    /**
     * BFS 를 이용한 풀이
     * 시작점의 거리가 변하는 이슈가 있지만 요구하는 해답이 도착지의 정보이기에 상관없다.
     */
    public int 미로_탈출_2(int n, int m, String[] maze) {
        int[][] graph = Arrays.stream(maze)
                .map(str -> str.chars()
                        .map(value -> value - '0')
                        .toArray())
                .toArray(int[][]::new);

        Queue<int[]> queue = new LinkedList<>();

        // 이동할 네 방향
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //시작 지점 입력
        queue.add(new int[]{0, 0});

        // 큐가 빌 때까지 반복, BFS
        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            int x = location[0];
            int y = location[1];

            // 이동할 네 방향
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 미로 공간을 벗어난 경우 무시
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                // 괴물인 경우 무시
                if (graph[nx][ny] == 0) {
                    continue;
                }
                // 해당 노드를 처음 방문한다면 최단거리 기록
                if (graph[nx][ny] == 1) {
                    int dist = graph[x][y] + 1;
                    graph[nx][ny] = dist;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return graph[n - 1][m - 1];
    }
}
