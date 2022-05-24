package this_is_coding_test.ch13;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static this_is_coding_test.ch12.ImplementationQuestions.combination;

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

    /**
     * 난이도: 중
     * 3 <= N, M <= 8
     * 3 <= 빈칸의 개수
     * 2 <= 바이러스의 개수 <= 10
     * 제한) 시간: 2초, 메모리: 512MB
     * https://www.acmicpc.net/problem/14502
     */
    public int 연구소(int n, int m, String[] rows) {
        int count = n * m;
        int[][] map = Arrays.stream(rows)
                .map(row -> Arrays.stream(row.split(" "))
                        .mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        List<int[]> combinations = combination(new boolean[count], 0, count, 3).stream()
                .filter(values -> {
                    boolean flag = true;
                    for (int value : values) {
                        int[] rowcol = intToPoint(m, value);
                        if (map[rowcol[0]][rowcol[1]] != 0) {
                            flag = false;
                        }
                    }
                    return flag;
                }).collect(Collectors.toList());

        List<int[]> virusLocation = findVirus(map);
        int answer = Integer.MIN_VALUE;

        for (int[] combination : combinations) {
            int[][] tempMap = arrayCopy(map);
            Arrays.stream(combination)
                    .mapToObj(value -> intToPoint(m, value))
                    .forEach(value -> tempMap[value[0]][value[1]] = 1);

            pandemic(tempMap, virusLocation);

            answer = Integer.max(answer, (int) Arrays.stream(tempMap)
                    .flatMapToInt(Arrays::stream)
                    .filter(x -> x == 0).count());
        }

        return answer;
    }

    public int[][] arrayCopy(int[][] map) {
        int[][] array = new int[map.length][];
        for (int i = 0; i < map.length; ++i) {
            array[i] = map[i].clone();
        }
        return array;
    }

    public int[] intToPoint(int m, int value) {
        int row = value / m;
        int col = value % m;
        return new int[]{row, col};
    }

    public List<int[]> findVirus(int[][] map) {
        List<int[]> virus = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }
        return virus;
    }

    public void pandemic(int[][] map, List<int[]> virus) {
        int n = map.length;
        int m = map[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int[] value : virus) {
            queue.addAll(directions(value));
        }

        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            if (inRange(n, m, location)) {
                if (map[location[0]][location[1]] == 0) {
                    map[location[0]][location[1]] = 2;
                    queue.addAll(directions(location));
                }
            }
        }
    }

    public boolean inRange(int n, int m, int[] location) {
        return !(location[0] < 0 || location[1] < 0 || location[0] == n || location[1] == m);
    }

    public List<int[]> directions(int[] location) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        return List.of(
                new int[]{location[0] + dx[0], location[1] + dy[0]},
                new int[]{location[0] + dx[1], location[1] + dy[1]},
                new int[]{location[0] + dx[2], location[1] + dy[2]},
                new int[]{location[0] + dx[3], location[1] + dy[3]}
        );
    }

    /**
     * 최대 크기는 8 * 8, 설치하는 벽은 3개, 64 C 3 < 100_000 모든 경우의 수를 고려해도 제한시간 내에 가능함
     * 조합은 dfs, bfs 를 통해 구할 수 있고, 안전 영역의 크기 또한 dfs, dbf 를 이용하여 계산할 수 있다.
     * 풀이 과정은 해답지의 접근방식과 동일했음
     */

    // 4가지 이동 방향에 대한 리스트
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, 1, 0, -1};
    // 초기 맵 리스트
    int[][] data;
    // 벽을 설치한 뒤의 맵 리스트
    int[][] temp;
    // 크기
    int globalN, globalM;
    // 결과
    int result = 0;

    public int 연구소_2(int n, int m, String[] rows) {
        // 초기 맵 리스트
        data = Arrays.stream(rows)
                .map(row -> Arrays.stream(row.split(" "))
                        .mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        // 벽을 설치한 뒤의 맵 리스트
        temp = new int[n][m];
        // 크기
        globalN = n;
        globalM = m;

        result = 0;

        dfs(0);

        return result;
    }

    // 깊이 우선 탑색(dfs)을 이용해 각 바이러스가 사방으로 퍼지도록 하기
    public void virus(int x, int y) {
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 상, 하, 좌, 우 중에서 바이러스가 퍼질 수 있는 경우
            if (nx >= 0 && nx < globalN && ny >= 0 && ny < globalM) {
                if (temp[nx][ny] == 0) {
                    // 해당 위치에 바이러스를 배치하고, 다시 재귀적으로 수행
                    temp[nx][ny] = 2;
                    virus(nx, ny);
                }
            }
        }
    }

    // 현재 맵에서 안전 영역의 크기 계산하는 메서드
    public int get_score() {
        int score = 0;
        for (int i = 0; i < globalN; i++) {
            for (int j = 0; j < globalM; j++) {
                if (temp[i][j] == 0) {
                    ++score;
                }
            }
        }
        return score;
    }

    // 깊이 우선 탐색(dfs)를 이용해 울타리를 설치하면서 매번 안전 영역의 크기 계산
    public void dfs(int count) {
        // 울타리가 3개 설치됨
        if (count == 3) {
            for (int i = 0; i < globalN; i++) {
                for (int j = 0; j < globalM; j++) {
                    temp[i][j] = data[i][j];
                }
            }
            // 각 바이러스의 위치에서 전파 진행
            for (int i = 0; i < globalN; i++) {
                for (int j = 0; j < globalM; j++) {
                    if (temp[i][j] == 2) {
                        virus(i, j);
                    }
                }
            }
            // 안전 영역의 최댓값 계산
            result = Integer.max(result, get_score());
            return;
        }
        // 빈 공간에 울타리 설치
        for (int i = 0; i < globalN; i++) {
            for (int j = 0; j < globalM; j++) {
                if (data[i][j] == 0) {
                    data[i][j] = 1;
                    ++count;
                    dfs(count);
                    data[i][j] = 0;
                    --count;
                }
            }
        }
    }
}
