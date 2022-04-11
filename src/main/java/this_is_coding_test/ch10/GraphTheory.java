package this_is_coding_test.ch10;

import java.util.Arrays;
import java.util.List;

public class GraphTheory {

    public void 서로소_집합() {
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
}
