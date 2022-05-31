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

    /**
     * 난이도: 중
     * 1 <= N <= 200
     * 1 <= K <= 1_000
     * 제한) 시간: 1초, 메모리: 256MB
     * https://www.acmicpc.net/problem/18405
     */
    public int 경쟁적_전염(int n, int k, String[] data) {
        int[][] testTube = new int[n][];
        for (int i = 0; i < n; i++) {
            testTube[i] = Arrays.stream(data[i].split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] sxy = Arrays.stream(data[n].split(" ")).mapToInt(Integer::parseInt).toArray();

        // [시간, 바이러스 번호, x, y]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (testTube[i][j] != 0) {
                    pq.add(new int[]{0, testTube[i][j], i, j});
                }
            }
        }

        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        while (!pq.isEmpty() && pq.peek()[0] < sxy[0]) {
            int[] virus = pq.poll();

            int time = virus[0];
            int type = virus[1];
            int x = virus[2];
            int y = virus[3];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (testTube[nx][ny] == 0) {
                        testTube[nx][ny] = type;
                        pq.add(new int[]{time + 1, type, nx, ny});
                    }
                }
            }
        }

        return testTube[sxy[1] - 1][sxy[2] - 1];
    }

    /**
     * 풀이과정은 내가 푼 방식과 해답지의 풀이 방식이 거의 유사하다
     * 다만 해답지에서는 우선순위 큐를 사용하지 않고 일반 큐를 사용했다.
     * 나는
     * 바이러스의 종류에 따른 우선순위, 시간에 따른 우선순위를 고려하려고 우선순위 큐를 사용했는데
     * 해답지에서는
     * 바이러스 종류에 따른 우선순위를 큐에 삽입하기 전 종류에 따라서 오름차순 정렬을 수행하여 해결했고
     * 시간에 따른 우선순위는 선입선출에 따라 자연스럽게 해결되었다.
     * (사전에 정렬이 수행된 해답지의 큐 상태, [타입, 시간])
     * 1. {[1, 0, 위치], [2, 0, 위치], [3, 0, 위치]}
     * 2. {[2, 0, 위치], [3, 0, 위치], [1, 1, 상], [1, 1, 하], [1, 1, 좌], [1, 1, 우]}
     * 3. {[3, 0, 위치], [1, 1, 상], [1, 1, 하], [1, 1, 좌], [1, 1, 우], [2, 1, 상], [2, 1, 하], [2, 1, 좌], [2, 1, 우]}
     */

    /**
     * 난이도: 하
     * 2 <= p.length <= 1_000
     * 제한) 시간: 1초, 메모리: 128MB
     * https://programmers.co.kr/learn/courses/30/lessons/60058
     */
    public String 괄호_변환(String p) {
        return makeRight(p);
    }

    public String makeRight(String w) {
        // 입력이 빈 문자열이면 그대로 리턴
        if (w.equals("")) {
            return w;
        }

        char[] chars = w.toCharArray();
        String u = "";
        String v = "";
        int openCount = 0;
        int closeCount = 0;

        // 문자열 w를 u, v로 분리
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '(') {
                ++openCount;
            } else {
                ++closeCount;
            }

            if (openCount == closeCount) {
                u = w.substring(0, i + 1);
                v = w.substring(i + 1);
                break;
            }
        }

        // u가 올바른 문자열이라면 v에 대해 1부터 다시 수행한 결과를 붙여서 리턴
        if (isRight(u)) {
            return u + makeRight(v);
        }

        // u가 올바른 문자열이 아니라면
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(makeRight(v)).append(')')
                .append(reverseBracket(u.substring(1, u.length() - 1)));

        return sb.toString();
    }

    public boolean isRight(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return true;
    }

    public String reverseBracket(String u) {
        StringBuilder sb = new StringBuilder();

        for (char ch : u.toCharArray()) {
            if (ch == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }

        return sb.toString();
    }

    /**
     * Q. DFS/BFS 문제는 아닌것 같은데..?
     * A. DFS 알고리즘에 핵심이 되는 재귀 함수 구현을 요구한다는 점에서 DFS 연습 문제로 분류하였다.
     * 문제에 제시된 알고리즘을 재귀 함수를 이용하여 안정적으로 구현하면 된다.
     * 소스코드를 단순화 하는것이 좋음
     * 1. 특정 문자열에서 "균형잡힌 괄호 문자열"의 인덱스를 반환하는 함수
     * 2. 특정한 "균형잡힌 괄호 문자열"이 "올바른 괄호 문자열"인지 판단하는 함수를 별도로 구현
     * 이후 재귀함수에서 이 두 함수를 불러오도록 작성
     *
     * 나는 open/closeCount 2개의 변수로 균형잡힌 여부를 판단했고
     * 스택으로 올바른 문자열 여부를 판단했는데
     * 해답지에서는 하나의 count 변수에서 +-를 수행하여 균형잡힌 여부와 올바른 여부를 판단했다.
     * 이외의 과정은 알고리즘이 문제에 제시되어 동일함
     */

    /**
     * 난이도: 중
     * 2 <= N <= 11
     * 1 <= A[i] <= 100
     * 제한) 시간: 2초, 메모리: 512MB
     * https://www.acmicpc.net/problem/14888
     */
    public int[] 연산자_끼워_넣기(int n, int[] numbers, int[] operators) {
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < operators.length; ++i) {
            for (int j = 0; j < operators[i]; j++) {
                index.add(i);
            }
        }

        Set<Perm> operatorPermutation = permutation(index.stream().mapToInt(i -> i).toArray(), new int[index.size()], new boolean[index.size()], 0, index.size(), index.size());

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (Perm p : operatorPermutation) {
            int result = calculate(numbers, p.array);
            max = Integer.max(max, result);
            min = Integer.min(min, result);
        }

        return new int[]{max, min};
    }

    public Set<Perm> permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        Set<Perm> set = new HashSet<>();
        if (depth == r) {
            set.add(new Perm(Arrays.copyOfRange(output, 0, r)));
            return set;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                set.addAll(permutation(arr, output, visited, depth + 1, n, r));
                visited[i] = false;
            }
        }

        return set;
    }

    public int calculate(int[] numbers, int[] operators) {
        int result = operation(numbers[0], numbers[1], operators[0]);

        for (int i = 1; i < operators.length; i++) {
            result = operation(result, numbers[i + 1], operators[i]);
        }

        return result;
    }

    public int operation(int a, int b, int operator) {
        if (operator == 0) {
            return a + b;
        }
        if (operator == 1) {
            return a - b;
        }
        if (operator == 2) {
            return a * b;
        }
        if (operator == 3) {
            return a / b;
        }
        return Integer.MIN_VALUE;
    }

    static class Perm {
        int[] array;

        public Perm(int[] array) {
            this.array = array;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }

        @Override
        public boolean equals(Object obj) {
            return Arrays.equals(array, ((Perm) obj).array);
        }
    }

    /**
     * 모든 경우의 수를 계산하는 완전탐색 dfs 혹은 bfs를 이용하여 문제를 해결할 수 있다.
     * 사칙연산을 중복하여 사용할 수 있기 때문에 중복 순열을 계산,
     * n = 4 일때 중복을 허용하여 3개를 뽑아 나열하는 모든 경우의 수를 고려
     * 파이썬의 중복순열 라이브러리를 이용해서 찾을 수 있다.
     * 하지만 중복순열 라이브러리 말고 dfs를 이용하여 푸는 방법을 제시함
     */

    int max = -(int) 1e9;
    int min = (int) 1e9;
    int g_n = 0;
    int[] g_numbers = null;
    int[] g_operators = null;

    public int[] 연산자_끼워_넣기_2(int n, int[] numbers, int[] operators) {
        g_n = n;
        g_numbers = numbers;
        g_operators = operators;

        dfs(1, numbers[0]);

        return new int[]{max, min};
    }

    public void dfs(int i, int now) {
        // 모든 연산자를 다 사용했을 경우, 최솟값과 최댓값 업데이트
        if (i == g_n) {
            max = Integer.max(max, now);
            min = Integer.min(min, now);
        } else {
            // 각 연산자에 대해 재귀적으로 수행
            if (g_operators[0] > 0) {
                g_operators[0] -= 1;
                dfs(i + 1, now + g_numbers[i]);
                g_operators[0] += 1;
            }
            if (g_operators[1] > 0) {
                g_operators[1] -= 1;
                dfs(i + 1, now - g_numbers[i]);
                g_operators[1] += 1;
            }
            if (g_operators[2] > 0) {
                g_operators[2] -= 1;
                dfs(i + 1, now * g_numbers[i]);
                g_operators[2] += 1;
            }
            if (g_operators[3] > 0) {
                g_operators[3] -= 1;
                dfs(i + 1, now / g_numbers[i]);
                g_operators[3] += 1;
            }
        }
    }

    /**
     * 난이도: 중상
     * 3 <= N <= 6
     * 제한) 시간: 2초, 메모리: 256MB
     * https://www.acmicpc.net/problem/14888
     */
    public String 감시_피하기(int n, String[] input) {
        Character[][] school = Arrays.stream(input)
                .map(data -> Arrays.stream(data.split(" "))
                        .map(str -> str.charAt(0))
                        .toArray(Character[]::new))
                .toArray(Character[][]::new);

        List<int[]> teachers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (school[i][j].equals('T')) {
                    teachers.add(new int[]{i, j});
                }
            }
        }

        int size = n * n;
        List<int[]> locationList = combination(new boolean[size], 0, size, 3);

        for (int[] location : locationList) {
            boolean flag = false;
            for (int point : location) {
                int[] xy = intToPoint(n, point);
                if (!school[xy[0]][xy[1]].equals('X')) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            for (int point : location) {
                int[] xy = intToPoint(n, point);
                school[xy[0]][xy[1]] = 'B';
            }

            for (int[] teacher : teachers) {
                int x = teacher[0];
                int y = teacher[1];
                //상
                while (x-- > 0 && !flag) {
                    if (school[x][y].equals('B')) {
                        break;
                    }

                    if (school[x][y].equals('S')) {
                        flag = true;
                        break;
                    }
                }
                //하
                x = teacher[0];
                while (x++ < n - 1 && !flag) {
                    if (school[x][y].equals('B')) {
                        break;
                    }

                    if (school[x][y].equals('S')) {
                        flag = true;
                        break;
                    }
                }
                //좌
                x = teacher[0];
                while (y-- > 0 && !flag) {
                    if (school[x][y].equals('B')) {
                        break;
                    }

                    if (school[x][y].equals('S')) {
                        flag = true;
                        break;
                    }
                }
                //우
                y = teacher[1];
                while (y++ < n - 1 && !flag) {
                    if (school[x][y].equals('B')) {
                        break;
                    }

                    if (school[x][y].equals('S')) {
                        flag = true;
                        break;
                    }
                }
            }

            if (!flag) {
                return "YES";
            }

            for (int point : location) {
                int[] xy = intToPoint(n, point);
                school[xy[0]][xy[1]] = 'X';
            }
        }

        return "NO";
    }

    /**
     * n은 최대 6, 36 C 3 의 최악의 경우에도 10_000이 넘지 않음. 모든 조합을 고려하여 완전 탐색을 수행해도 문제가 없음
     * 풀이와 해답지가 비슷했다.
     */

    /**
     * 난이도: 중
     * 1 <= N <= 50
     * 1 <= L <= R <= 100
     * 0 <= A[r][c] <= 100
     * 0 <= 인구이동 횟수 <= 2_000
     * 제한) 시간: 2초, 메모리: 512MB
     * https://www.acmicpc.net/problem/16234
     */
    public int 인구_이동(String[] input) {
        String[] nlr = input[0].split(" ");
        int n = Integer.parseInt(nlr[0]);
        int l = Integer.parseInt(nlr[1]);
        int r = Integer.parseInt(nlr[2]);

        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        int[][] map = new int[n][];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(input[i + 1].split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] unionArray = new int[n * n];
        Arrays.fill(unionArray, Integer.MAX_VALUE);

        int answer = 0;

        while (true) {
            int[] union = Arrays.copyOf(unionArray, unionArray.length);
            // 마을 크기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int now = pointToInt(n, i, j);
                    if (union[now] == Integer.MAX_VALUE) {
                        union[now] = now;
                    }
                    // 상하좌우 국경선 체크
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (checkBorder(n, nx, ny)) {
                            int difference = Math.abs(map[i][j] - map[nx][ny]);
                            if (difference >= l && difference <= r) {
                                union[pointToInt(n, nx, ny)] = union[now];
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < union.length; i++) {
                setParent(union, union[i]);
            }

            Map<Integer, List<Integer>> freq = new HashMap<>();
            for (int i = 0; i < union.length; i++) {
                int key = union[i];
                if (freq.containsKey(key)) {
                    freq.get(key).add(i);
                } else {
                    freq.put(key, new ArrayList<>());
                }
            }

            if (freq.size() == n * n) {
                break;
            }

            for (Map.Entry<Integer, List<Integer>> entry : freq.entrySet()) {
                List<int[]> points = entry.getValue().stream()
                        .map(value -> intToPoint(n, value))
                        .collect(Collectors.toList());
                points.add(intToPoint(n, entry.getKey()));

                int average = points.stream().mapToInt(point -> map[point[0]][point[1]]).sum();
                average /= points.size();

                for (int[] point : points) {
                    map[point[0]][point[1]] = average;
                }
            }

            ++answer;
        }

        return answer;
    }

    public int setParent(int[] union, int now) {
        if (union[now] != now) {
            union[now] = setParent(union, union[now]);
        }

        return union[now];
    }

    public int pointToInt(int n, int x, int y) {
        return x * n + y;
    }

    public boolean checkBorder(int n, int x, int y) {
        return x > -1 && y > -1 && x < n && y < n;
    }
}
