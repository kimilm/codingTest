package this_is_coding_test.ch09;

import java.util.*;
import java.util.stream.IntStream;

public class ShortestPath {

    static class Node {
        int node;
        int value;

        private Node(int node, int value) {
            this.node = node;
            this.value = value;
        }

        public static Node of(int node, int value) {
            return new Node(node, value);
        }
    }

    private final Node[][] graph = new Node[][]{
            {Node.of(1, 2), Node.of(2, 5), Node.of(3, 1)},
            {Node.of(2, 3), Node.of(3, 2)},
            {Node.of(1, 3), Node.of(5, 5)},
            {Node.of(2, 3), Node.of(4, 1)},
            {Node.of(2, 1), Node.of(5, 2)},
            {}
    };

    public void dijkstra() {
        int currentNode = 0;
        int[] spList = new int[6];
        boolean[] visited = new boolean[6];

        Arrays.fill(spList, (int) 1e9);
        spList[currentNode] = 0;

        // 노드 개수만큼 반복
        for (int i = 0; i < 6; i++) {
            visited[currentNode] = true;
            Node[] nodes = graph[currentNode];

            for (Node node : nodes) {
                // 현재 노드에서 갈 수 있는 노드의 거리 계산
                int dist = spList[currentNode] + node.value;

                // 최단거리 리스트 갱신
                spList[node.node] = Integer.min(dist, spList[node.node]);
            }

            // 거리 오름차순 정렬
            Arrays.sort(nodes, Comparator.comparingInt(o -> o.value));

            // 다음 노드 선택
            for (Node node : nodes) {
                if (!visited[node.node]) {
                    currentNode = node.node;
                    break;
                }
            }
        }

        IntStream.range(0, 6).forEach(idx -> System.out.println("노드" + idx + " 까지 거리: " + spList[idx]));
    }


    /**
     * getSmallestNode 함수가 순차탐색, O(v^2)의 시간 복잡도를 가진다
     */
    private final int INF = (int) 1e9;

    public void dijkstra2() {
        List<List<int[]>> graph = makeGraphForDijkstra();
        Integer[] distance = Collections.nCopies(7, INF).toArray(Integer[]::new);
        boolean[] visited = new boolean[7];

        // 시작 노드
        int start = 1;

        distance[start] = 0;
        visited[start] = true;

        // nodeEdge[0]: node, nodeEdge[1]: edge
        graph.get(start).forEach(nodeEdge -> distance[nodeEdge[0]] = nodeEdge[1]);

        // 0과 시작노드를 제외한 전체 노드(2~6)에 대해 반복
        IntStream.range(0, 5).forEach(idx -> {
            // 현재 최단 거리가 가장 짧은 노드를 찾아 방문 처리
            int now = getSmallestNode(distance, visited);
            visited[now] = true;

            // 찾은 노드와 연결된 다른 노드들로 가는 거리 확인
            graph.get(now).forEach(nodeEdge -> {
                int cost = distance[now] + nodeEdge[1];

                // 찾은 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우 갱신
                if (cost < distance[nodeEdge[0]]) {
                    distance[nodeEdge[0]] = cost;
                }
            });
        });

        // 결과 출력
        IntStream.range(1, 7).forEach(idx -> System.out.println("노드" + idx + " 까지 거리: " + distance[idx]));
    }

    public List<List<int[]>> makeGraphForDijkstra() {
        List<List<int[]>> graph = new ArrayList<>();
        IntStream.range(0, 7).forEach((idx) -> graph.add(new ArrayList<>()));

        graph.get(1).addAll(Arrays.asList(new int[]{2, 2}, new int[]{3, 5}, new int[]{4, 1}));
        graph.get(2).addAll(Arrays.asList(new int[]{3, 3}, new int[]{4, 2}));
        graph.get(3).addAll(Arrays.asList(new int[]{2, 3}, new int[]{6, 5}));
        graph.get(4).addAll(Arrays.asList(new int[]{3, 3}, new int[]{5, 1}));
        graph.get(5).addAll(Arrays.asList(new int[]{3, 1}, new int[]{6, 2}));

        return graph;
    }

    // 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환
    public int getSmallestNode(Integer[] distance, boolean[] visited) {
        int minValue = INF;
        int node = 0;  // 가장 최단 거리가 짧은 노드

        for (int i = 1; i < 7; ++i) {
            if (distance[i] < minValue && !visited[i]) {
                minValue = distance[i];
                node = i;
            }
        }

        return node;
    }

    public void dijkstra3() {
        List<List<int[]>> graph = makeGraphForDijkstra();
        int[] distance = new int[7];
        boolean[] visited = new boolean[7];

        // 보통은 앞에 있는 값을 가중치로 사용하지만 먼저 만든 그래프의 간선 정보가 뒤에 있기 때문에 인덱스 혼동을 막기 위해 설정
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(nodeEdge -> nodeEdge[1]));

        Arrays.fill(distance, INF);

        int start = 1;
        distance[start] = 0;

        pq.add(new int[]{start, distance[start]});

        while (!pq.isEmpty()) {
            int[] nodeEdge = pq.poll();
            visited[nodeEdge[0]] = true;

            graph.get(nodeEdge[0]).stream()
                    .filter(entry -> !visited[entry[0]])
                    .forEach(entry -> {
                        int cost = distance[nodeEdge[0]] + entry[1];

                        if (cost < distance[entry[0]]) {
                            distance[entry[0]] = cost;

                            pq.add(new int[]{entry[0], cost});
                        }
                    });
        }

        System.out.println(Arrays.toString(distance));
    }

    /**
     * PriorityQueue 가 getSmallestNode 함수를 대체, heap 구조를 사용해서 O(NlogN)의 시간복잡도를 가진다
     */
    public void dijkstra4() {
        List<List<int[]>> graph = makeGraphForDijkstra();
        Integer[] distance = Collections.nCopies(7, INF).toArray(Integer[]::new);

        // edge 를 weight 로 설정, 일반적으로 먼저 오는 원소를 가중치로 설정한다 (edge, node) 형태로 저장
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(edgeNode -> edgeNode[0]));

        // 시작 노드
        int start = 1;
        distance[start] = 0;

        // (edge, node) 형태로 우선순위 큐에 삽입
        pq.add(new int[]{distance[start], start});

        // 큐가 비어있지 않다면
        while (!pq.isEmpty()) {
            // 가장 최단 거리가 짧은 노드 정보 꺼내기
            int[] pair = pq.poll();
            int dist = pair[0];
            int now = pair[1];

            // 현재 노드가 이미 처리된 적이 있다면 무시
            if (distance[now] < dist) {
                continue;
            }

            // 현재 노드와 인접한 다른 노드들을 확인
            graph.get(now).forEach(nodeEdge -> {
                // 그래프에는 (node, edge) 형태로 저장됨
                int node = nodeEdge[0];
                int edge = nodeEdge[1];
                int cost = distance[now] + edge;

                // 현재 노드를 거쳐서 다른 노드로 가는 거리가 더 짧을 경우
                if (cost < distance[node]) {
                    distance[node] = cost;
                    // 우선순위 큐에는 (edge, node) 형태로 저장됨
                    pq.add(new int[]{cost, node});
                }
            });
        }
        // 결과 출력
        System.out.println(Arrays.toString(distance));
    }

    // 플로이드 워셜 알고리즘은 2차원 배열을 사용함
    // 그래프를 처음부터 리스트 형태가 아닌 2차원 배열 형태로 작성하여 바로 이용해도 됨
    public void floydWarshall() {
        List<List<int[]>> graph = makeGraphForFloydWarshall();
        int[][] spArray = new int[5][5];

        // 최단거리 2차원 배열 초기화
        for (int[] row : spArray) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < 5; ++i) {
            spArray[i][i] = 0;

            for (int[] node : graph.get(i)) {
                spArray[i][node[0]] = node[1];
            }
        }

        // a: 출발, b: 도착
        // 플로이드-워셜
        // 도달 불가능한 노드는 INF로 설정, 수열로 도달 가능한 노드만 뽑는 것은 잘못된 접근
        for (int k = 1; k < 5; ++k) {
            for (int a = 1; a < 5; ++a) {
                for (int b = 1; b < 5; ++b) {
                    spArray[a][b] = Integer.min(spArray[a][b], spArray[a][k] + spArray[k][b]);
                }
            }
        }

        // 결과 출력
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.print(spArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public List<List<int[]>> makeGraphForFloydWarshall() {
        List<List<int[]>> graph = new ArrayList<>();
        IntStream.range(0, 5).forEach((idx) -> graph.add(new ArrayList<>()));

        graph.get(1).addAll(Arrays.asList(new int[]{2, 4}, new int[]{4, 6}));
        graph.get(2).addAll(Arrays.asList(new int[]{1, 3}, new int[]{3, 7}));
        graph.get(3).addAll(Arrays.asList(new int[]{1, 5}, new int[]{4, 4}));
        graph.get(4).addAll(List.of(new int[]{3, 2}));

        return graph;
    }

    /**
     * 난이도 중
     * 제한) 시간: 1초, 메모리: 128MB
     * 1 <= N, M <= 100
     * 1 <= K <= 100
     */
    public int 미래_도시(int n, int m, String[] graph) {
        int[] xk = Arrays.stream(graph[graph.length - 1].split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x = xk[0];
        int k = xk[1];

        List<List<Integer>> city = new ArrayList<>();
        IntStream.range(0, n + 1).forEach(idx -> city.add(new ArrayList<>()));

        for (int i = 0; i < graph.length - 1; i++) {
            int[] node = Arrays.stream(graph[i].split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            city.get(node[0]).add(node[1]);
            city.get(node[1]).add(node[0]);
        }

        int aToK = dijkstraFor미래도시(city, 1, k);
        int kToX = dijkstraFor미래도시(city, k, x);

        int answer = aToK + kToX;

        return answer < INF ? answer : -1;
    }

    public int dijkstraFor미래도시(List<List<Integer>> graph, int depart, int arrive) {
        int n = graph.size();
        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        distance[depart] = 0;

        // (거리, 노드), 거리 가까운 순으로 가중치 설정
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(edgeNode -> edgeNode[0]));
        pq.add(new int[]{distance[depart], depart});

        while (!pq.isEmpty()) {
            int[] pick = pq.poll();
            int edge = pick[0]; // 현재 노드까지 최소 거리
            int now = pick[1];  // 현재 노드

            for (Integer node : graph.get(now)) {
                // 현재 노드를 거쳐서 다음 노드까지 가는게 더 빠르다면
                if (edge + 1 < distance[node]) {
                    distance[node] = edge + 1;
                    pq.add(new int[]{distance[node], node});
                }
            }
        }

        return distance[arrive];
    }

    /**
     * 이 문제는 전형적인 플로이드 워셜 알고리즘 문제다.
     * 모든 노드에서 각각 모든 노드로 가는 최단 거리를 계산함
     * 1번 노드부터 K 노드 까지의 최단 거리 + K 노드부터 X 노드 까지의 최단 거리
     */

    public int 미래_도시_2(int n, int m, String[] graph) {
        // 그래프 초기화
        int[][] citys = new int[n + 1][n + 1];

        // 전체 거리를 무한으로 설정
        for (int[] city : citys) {
            Arrays.fill(city, INF);
        }

        // 자기 자신으로 가는 거리는 0
        for (int i = 0; i < n + 1; ++i) {
            citys[i][i] = 0;
        }

        // 연결된 노드는 1로 초기화
        for (int i = 0; i < m; ++i) {
            int[] city = Arrays.stream(graph[i].split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // 양방향
            citys[city[0]][city[1]] = 1;
            citys[city[1]][city[0]] = 1;
        }

        // x, k 초기화
        int[] arrives = Arrays.stream(graph[m].split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int x = arrives[0];
        int k = arrives[1];

        // 플로이드-워셜
        for (int i = 1; i < n + 1; ++i) {
            for (int a = 1; a < n + 1; ++a) {
                for (int b = 1; b < n + 1; ++b) {
                    citys[a][b] = Integer.min(citys[a][b], citys[a][i] + citys[i][b]);
                }
            }
        }

        int distance = citys[1][k] + citys[k][x];

        return distance < INF ? distance : -1;
    }
}
