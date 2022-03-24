package this_is_coding_test.ch09;

import java.util.Arrays;
import java.util.Comparator;
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

    private final Node[][] graph = new Node[][] {
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

        Arrays.fill(spList, (int)1e9);
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
}
