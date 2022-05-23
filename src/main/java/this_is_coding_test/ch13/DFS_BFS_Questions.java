package this_is_coding_test.ch13;

import java.util.*;
import java.util.stream.IntStream;

public class DFS_BFS_Questions {

    /**
     * 난이도: 중하
     * 2 <= N <= 300_000
     * 1 <= M <= 1_000_000
     * 1 <= K <= 300_000
     * 1 <= X <= N
     * 1 <= A, B <= N
     * A != B
     * 제한) 시간: 2초, 메모리: 256MB
     * https://www.acmicpc.net/problem/18352
     */
    public int[] 특정_거리의_도시_찾기(int n, int m, int k, int x, String[] cities) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (String city : cities) {
            int[] node = Arrays.stream(city.split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            graph.get(node[0]).add(node[1]);
        }

        int[] distArray = new int[n + 1];
        Arrays.fill(distArray, (int) 1e9);
        distArray[x] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, 1});

        while (!queue.isEmpty()) {
            int[] entity = queue.poll();
            int node = entity[0];
            int dist = entity[1];

            for (int city : graph.get(node)) {
                if (dist < distArray[city]) {
                    distArray[city] = dist;
                    queue.add(new int[]{city, dist + 1});
                }
            }
        }

        int[] answer = IntStream.range(0, n + 1).filter(i -> distArray[i] == k).toArray();
        if (answer.length == 0) {
            return new int[]{-1};
        }
        return answer;
    }

    /**
     * 모든 도로의 길이가 1이라는 조건을 이용해서 너비 우선 탐색으로 빠르게 풀 수 있다.
     * 노드의 개수 N은 최대 300_000개, 간선의 개수 M은 최대 1_000_000
     * 시간복잡도 O(N + M) 해결 가능
     * 특정한 도시 x를 시작점으로 bfs를 수행하여 모든 도시까지의 최단거리 계산, 거리가 K인 도시 출력
     * 내 풀이는 거리 계산을 비효율적으로 수행했다.
     */

    public int[] 특정_거리의_도시_찾기_2(int n, int m, int k, int x, String[] cities) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (String city : cities) {
            int[] node = Arrays.stream(city.split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            graph.get(node[0]).add(node[1]);
        }

        // 모든 도시 최단거리 초기화
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        distance[x] = 0;

        // bfs 수행
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            // 현재 도시에서 이동 가능한 모든 도시
            for (int city : graph.get(now)) {
                // 아직 방문하지 않은 도시라면
                if (distance[city] == -1) {
                    distance[city] = distance[now] + 1;
                    queue.add(city);
                }
            }
        }

        // 출력
        int[] answer = IntStream.range(0, n + 1).filter(i -> distance[i] == k).toArray();
        if (answer.length == 0) {
            return new int[]{-1};
        }
        return answer;
    }
}
