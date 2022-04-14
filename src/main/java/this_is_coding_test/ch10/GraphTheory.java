package this_is_coding_test.ch10;

import java.util.*;
import java.util.stream.Collectors;

public class GraphTheory {

    public void 서로소_집합() {
        // 노드는 1 2 3 4 5 6
        List<int[]> edges = List.of(
                new int[]{1, 4},
                new int[]{2, 3},
                new int[]{2, 4},
                new int[]{5, 6}
        );

        int[] parent = new int[7];
        for (int i = 0; i < 7; ++i) {
            parent[i] = i;
        }

        // union 연산 수행
        for (int[] edge : edges) {
            union_parent(parent, edge[0], edge[1]);
        }

        // 각 원소가 속한 집합 출력
        System.out.print("각 원소가 속한 집합: ");
        for (int i = 1; i < 7; ++i) {
            System.out.print(find_parent_2(parent, i) + " ");
        }

        // 부모 테이블 내용 출력
        System.out.print("\n부모 테이블: ");
        for (int i = 1; i < 7; ++i) {
            System.out.print(parent[i] + " ");
        }
    }

    // 특정 원소가 속한 집합을 찾기 (재귀)
    public int find_parent(int[] parent, int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (parent[x] != x) {
            return find_parent(parent, parent[x]);
        }

        return x;
    }

    // 특정 원소가 속한 집합을 찾기 (경로압축)
    public int find_parent_2(int[] parent, int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (parent[x] != x) {
            parent[x] = find_parent_2(parent, parent[x]);
        }

        return parent[x];
    }

    // 두 원소가 속한 집합을 합치기
    public void union_parent(int[] parent, int a, int b) {
        a = find_parent_2(parent, a);
        b = find_parent_2(parent, b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public void 무방향_그래프의_사이클_판별() {
        // 노드는 1 2 3
        List<int[]> edges = List.of(
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 3}
        );

        int[] parent = new int[4];
        for (int i = 0; i < 4; ++i) {
            parent[i] = i;
        }

        // 그래프 내 사이클 여부
        boolean cycle = false;

        // 모든 간선에 대해서 수행
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            // 사이클이 발생했다면 종료
            if (find_parent_2(parent, a) == find_parent_2(parent, b)) {
                cycle = true;
                break;
            }
            // 아니라면 union 수행
            else {
                union_parent(parent, a, b);
            }
        }

        if (cycle) {
            System.out.println("사이클이 발생했습니다.");
        } else {
            System.out.println("사이클이 발생하지 않았습니다.");
        }
    }

    public int kruskal() {
        // 노드는 1 ~ 7
        // List.of 는 불변 리스트
        // Arrays.asList 는 ArrayList
        List<int[]> edges = Arrays.asList(
                new int[]{1, 2, 29},
                new int[]{1, 5, 75},
                new int[]{2, 3, 35},
                new int[]{2, 6, 34},
                new int[]{3, 4, 7},
                new int[]{4, 6, 23},
                new int[]{4, 7, 13},
                new int[]{5, 6, 53},
                new int[]{6, 7, 25}
        );

        // 부모 테이블 초기화
        int[] parent = new int[8];
        for (int i = 1; i < 8; ++i) {
            parent[i] = i;
        }

        // 최종 비용
        int result = 0;

        // 간선을 비용순 정렬
        edges.sort(Comparator.comparingInt(edge -> edge[2]));

        // 간선을 하나씩 확인
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            // 사이클이 발생하지 않았다면
            if (find_parent_2(parent, a) != find_parent_2(parent, b)) {
                union_parent(parent, a, b);
                result += cost;
            }
        }

        return result;
    }

    public void topologySort() {
        // 노드는 1 ~ 7
        List<int[]> edges = List.of(
                new int[]{1, 2},
                new int[]{1, 5},
                new int[]{2, 3},
                new int[]{2, 6},
                new int[]{3, 4},
                new int[]{4, 7},
                new int[]{5, 6},
                new int[]{6, 4}
        );

        // 각 노드의 진입차수
        int[] indegree = new int[8];

        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            graph.add(new ArrayList<>());
        }

        // 그래프 생성 및 진입차수 증가
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            ++indegree[edge[1]];
        }

        // 위상정렬
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i < 8; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 원소 제거
            Integer now = queue.poll();
            result.add(now);

            for (int node : graph.get(now)) {
                // 진입차수 제거
                --indegree[node];

                // 새롭게 진입차수가 0이 된 노드 큐에 삽입
                if (indegree[node] == 0) {
                    queue.add(node);
                }
            }
        }

        // 결과
        for (int value : result) {
            System.out.print(value + " ");
        }

        System.out.println();
    }

    /**
     * 난이도 중
     * 1 <= n, m <= 100_000
     * 제한) 시간: 2초, 메모리: 128MB
     */
    public String 팀_결성(int n, int m, String[] args) {
        int[] teams = new int[n + 1];

        // 초기화
        for (int i = 0; i < teams.length; i++) {
            teams[i] = i;
        }

        List<String> answer = new ArrayList<>();

        for (String arg : args) {
            int[] operation = Arrays.stream(arg.split(" ")).mapToInt(Integer::parseInt).toArray();

            // union
            if (operation[0] == 0) {
                union(teams, operation[1], operation[2]);
            }
            // find
            else {
                if (find(teams, operation[1]) != find(teams, operation[2])) {
                    answer.add("NO");
                } else {
                    answer.add("YES");
                }
            }
        }
        return String.join("\n", answer);
    }

    public void union(int[] team, int a, int b) {
        int pa = find(team, a);
        int pb = find(team, b);

        if (pa < pb) {
            team[pb] = pa;
        } else {
            team[pa] = pb;
        }
    }

    public int find(int[] teams, int a) {
        if (teams[a] != a) {
            teams[a] = find(teams, teams[a]);
        }

        return teams[a];
    }

    public int 도시_분할_계획(int n, int m, String[] args) {
        int[] parent = new int[n + 1];
        for (int i = 0; i < parent.length; ++i) {
            parent[i] = i;
        }

        // 비용 오름차순 정렬
        List<int[]> edges = Arrays.stream(args)
                .map(arg -> Arrays.stream(arg.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .sorted(Comparator.comparingInt(edge -> edge[2]))
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();

        for(int[] edge : edges) {
            // 사이클이 만들어지지 않으면
            if (find(parent, edge[0]) != find(parent, edge[1])) {
                union(parent, edge[0], edge[1]);
                result.add(edge[2]);
            }
        }
        // 가장 비용이 큰 도로 제거, 2개의 트리로 분리
        result.remove(result.size() - 1);

        return result.stream().mapToInt(Integer::intValue).sum();

        // int result;
        // int last;

        // result += edge[2];
        // last = edge[2];

        // return result - last
        // 처럼 구현하는 방법도 있다
    }
}
