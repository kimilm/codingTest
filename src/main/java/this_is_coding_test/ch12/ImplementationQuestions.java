package this_is_coding_test.ch12;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImplementationQuestions {

    /**
     * 난이도 하
     * 10 <= N <= 99_999_999, N의 자릿수는 항상 짝수
     * 제한) 시간: 1초, 메모리: 256MB
     * https://www.acmicpc.net/problem/18406
     */
    public String 럭키_스트레이트(long N) {
        String str = String.valueOf(N);
        String left = str.substring(0, str.length() / 2);
        String right = str.substring(str.length() / 2);

        return strSum(left) != strSum(right) ? "READY" : "LUCKY";
    }

    int strSum(String str) {
        return str.chars().map(num -> num - '0').sum();
    }

    /**
     * 난이도 하
     * 1 <= S의 길이 <= 10_000
     * 제한) 시간: 1초, 메모리: 256MB
     */
    public String 문자열_재정렬(String s) {
        List<Character> charList = new ArrayList<>();
        int number = 0;

        for (char token : s.toCharArray()) {
            if (token >= '0' && token <= '9') {
                number += token - '0';
            } else {
                charList.add(token);
            }
        }
        Collections.sort(charList);

        // String str = charList.stream().map(String::valueOf).collect(Collectors.joining());
        StringBuilder sb = new StringBuilder();
        for (Character c : charList) {
            sb.append(c);
        }

        sb.append(number);

        return sb.toString();
    }

    /**
     * 난이도 중하
     * 1 <= s의 길이 <= 1_000, s는 소문자로만 구성됨
     * 제한) 시간: 1초, 메모리: 256MB
     * https://programmers.co.kr/learn/courses/30/lessons/60057
     */
    public int 문자열_압축(String s) {
        int length = s.length();
        int answer = length;

        for (int i = 1; i <= length / 2; ++i) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            StringBuilder str = new StringBuilder(s);
            String compress = s.substring(0, i);

            while (str.length() >= compress.length()) {
                String subStr = str.substring(0, i);
                if (subStr.equals(compress)) {
                    ++count;
                    str.delete(0, i);
                } else {
                    sb.append(compress(count, compress));
                    compress = subStr;
                    count = 0;
                }
            }

            sb.append(compress(count, compress));
            sb.append(str);

            answer = Integer.min(sb.length(), answer);
        }
        return answer;
    }

    public String compress(int count, String compress) {
        if (count != 1) {
            return count + compress;
        } else {
            return compress;
        }
    }

    /**
     * 이전에 프로그래머스에서 풀었던 풀이, 이게 더 빠르다
     * 문제랑 별개로 배운 것 -> Math.log10() + 1 자릿수 계산 오..
     */
    public int 문자열_압축_2(String s) {
        int compLen = 0;
        int compStartIndex;
        int compCount;
        String comp;
        StringBuilder sb;
        Stack<String> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        while (compLen++ < s.length() / 2) {
            sb = new StringBuilder(s);
            comp = s.substring(0, compLen);
            compStartIndex = 0;
            compCount = 1;

            for (int i = compLen; i < s.length(); i += compLen) {
                if (i + compLen > s.length())
                    break;

                if (s.substring(i, i + compLen).compareTo(comp) != 0) {
                    if (compCount != 1) {
                        stack.push(comp);
                        stack.push(Integer.toString(compCount));
                        stack.push(Integer.toString(compCount * compLen + compStartIndex));
                        stack.push(Integer.toString(compStartIndex));
                    }
                    comp = s.substring(i, i + compLen);
                    compCount = 1;
                    compStartIndex = i;
                } else {
                    ++compCount;
                }
            }
            if (compCount != 1) {
                stack.push(comp);
                stack.push(Integer.toString(compCount));
                stack.push(Integer.toString(compCount * compLen + compStartIndex));
                stack.push(Integer.toString(compStartIndex));
            }

            while (!stack.isEmpty()) {
                sb.replace(Integer.parseInt(stack.pop()), Integer.parseInt(stack.pop()), stack.pop() + stack.pop());
            }
            list.add(sb.length());
        }

        if (compLen == 1)
            list.add(1);

        Collections.sort(list);

        return list.get(0);
    }

    /**
     * 책에 나온 풀이, 1번이랑 같은 방법인데 더 빠르다.. 1번은 delete 때문에 느린듯
     */
    public int 문자열_압축_3(String s) {
        AtomicInteger answer = new AtomicInteger(s.length());

        // 1개 단위(step) 부터 압축 단위를 늘려가며 확인
        IntStream.range(1, s.length() / 2 + 1).forEach(step -> {
            StringBuilder compressed = new StringBuilder();
            // 앞에서부터 step 까지의 문자열 추출
            String prev = s.substring(0, step);
            int count = 1;

            // 단위(step) 크기만큼 증가시키며 이전 문자열과 비교
            for (int i = step; i < s.length(); i += step) {
                String substr = "";
                if (i + step > s.length()) {
                    substr = s.substring(i);
                } else {
                    substr = s.substring(i, i + step);
                }
                // 이전 상태와 동일하다면 압축 횟수 증가
                if (prev.equals(substr)) {
                    ++count;
                }
                // 다른 문자열이 나왔다면 (더 이상 압축하지 못하는 경우라면)
                else {
                    if (count != 1) {
                        compressed.append(count).append(prev);
                    } else {
                        compressed.append(prev);
                    }
                    prev = substr;
                    count = 1;
                }
            }

            // 남아있는 문자열에 대해서 처리
            if (count != 1) {
                compressed.append(count).append(prev);
            } else {
                compressed.append(prev);
            }
            // 만들어지는 압축 문자열이 가장 짧은 것이 정답
            answer.set(Integer.min(answer.get(), compressed.length()));
        });

        return answer.get();
    }

    /**
     * 난이도 중하
     * key: M * M (3 <= M <= 20)
     * lock: N * N (3 <= N <= 20)
     * M <= N
     * 제한) 시간: 1초, 메모리: 256MB
     * https://programmers.co.kr/learn/courses/30/lessons/60059
     */
    public boolean 자물쇠와_열쇠(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        int[][] plate = initLock(lock, m, n);

        // 열쇠 4번 회전
        for (int times = 0; times < 4; ++times) {
            for (int i = 0; i < m + n - 1; i++) {
                for (int j = 0; j < m + n - 1; ++j) {
                    // 자물쇠 판 초기화
                    int[][] temp = new int[plate.length][];
                    initTemp(plate, temp);

                    // 열쇠 체크
                    for (int k = 0; k < m; k++) {
                        for (int l = 0; l < m; l++) {
                            // xor 연산, 1이 홀수 -> 참
                            temp[i + k][j + l] = temp[i + k][j + l] ^ key[k][l];
                        }
                    }

                    if (isUnLocked(temp, m, n)) {
                        return true;
                    }
                }
            }
            rotate(key);
        }
        return false;
    }

    public int[][] initLock(int[][] lock, int m, int n) {
        int padding = m - 1;
        int size = n + 2 * padding;
        int[][] temp = new int[size][size];

        // 자물쇠 주위로 패딩을 만들어서 중간에 값 입력
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                temp[i + padding][j + padding] = lock[i][j];
            }
        }

        return temp;
    }

    public void initTemp(int[][] plate, int[][] temp) {
        for (int i = 0; i < plate.length; i++) {
            temp[i] = plate[i].clone();
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;

            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public boolean isUnLocked(int[][] matrix, int m, int n) {
        int padding = m - 1;
        int sum = 0;
        for (int i = padding; i < padding + n; i++) {
            for (int j = padding; j < padding + n; j++) {
                sum += matrix[i][j];
            }
        }
        // sum 을 1로 주고 비트 and 연산으로 바꿔봤는데 빨라지긴 하나 드라마틱하게 빨라지지는 않았음
        return n * n == sum;
    }

    /**
     * 자물쇠와 열쇠의 크기는 최대 20 * 20, 모든 원소에 접근하는데는 400만큼의 연산
     * 일반적으로 1초에 2_000만 ~ 1억 정도의 연산을 처리할 수 있음 => 완전탐색으로 접근하는 것이 바람직함
     * 고민해서 풀었는데 문제 해결 방식이 책과 동일했다 굳
     */

    /**
     * 난이도 중
     * 2 <= N <= 100
     * 0 <= K <= 100
     * 1 <= L <= 100
     * 1 <= X <= 10_000
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 뱀(int n, int k, String[] kList, int l, String[] lList) {
        boolean[][] plate = new boolean[n][n];
        int[][] apples = Arrays.stream(kList)
                .map(list -> Arrays.stream(list.split(" "))
                        .mapToInt(value -> Integer.parseInt(value) - 1)
                        .toArray())
                .toArray(int[][]::new);
        Queue<Integer> snake = new LinkedList<>();
        Queue<Map.Entry<Integer, Character>> directions = Arrays.stream(lList)
                .map(list -> {
                    String[] temp = list.split(" ");
                    Map.Entry<Integer, Character> entry = Map.entry(Integer.parseInt(temp[0]), temp[1].charAt(0));
                    return entry;
                })
                .collect(Collectors.toCollection(LinkedList::new));

        // 0: 우, 1: 하, 2: 좌, 3: 상
        int direction = 0;
        int[] dRow = new int[]{0, 1, 0, -1};
        int[] dCol = new int[]{1, 0, -1, 0};

        // 사과
        for (int[] apple : apples) {
            int row = apple[0];
            int col = apple[1];
            plate[row][col] = true;
        }
        snake.add(coordinateToInt(5, 0, 0));

        int time = 0;
        int row = 0;
        int col = 0;

        while (true) {
            ++time;

            row += dRow[direction];
            col += dCol[direction];

            // 보드판을 벗어나면 종료
            if (row < 0 || row >= n || col < 0 || col >= n) {
                break;
            }

            // 이동 방향으로 뱀 늘리기
            int body = coordinateToInt(n, row, col);

            // 이동 방향에 뱀 몸통이 있다면 종료
            if (snake.contains(body)) {
                break;
            }

            // 이동
            snake.add(body);

            // 사과가 있다면
            if (plate[row][col]) {
                plate[row][col] = false;
            }
            // 아무것도 없다면
            else {
                snake.poll();
            }

            // 시간 종료시 방향 전환
            if (!directions.isEmpty() && directions.peek().getKey().equals(time)) {
                direction = changeDirection(direction, directions.poll().getValue());
            }
        }

        return time;
    }

    public int coordinateToInt(int n, int row, int col) {
        return n * row + col;
    }

    public int changeDirection(int current, char next) {
        int nextDirection = current;

        // 우측으로 90도
        if (next == 'D') {
            nextDirection += 1;
        }
        // 좌측으로 90도
        else if (next == 'L') {
            nextDirection += 3;
        }

        return nextDirection % 4;
    }

    /**
     * 뱀의 위치를 따로 계산했는데 plate 상에서 2로 설정하거나 했으면 별도의 뱀 변수를 두지 않아도 될 것 같다.
     * 괜히 코드가 복잡해보인다.
     *
     * 해당 부분만 수정하면 답지의 코드와 유사한 풀이가 된다.
     */

    /**
     * 난이도 중하
     * 5 <= n <= 100
     * 1 <= build_frame.length <= 1000
     * build_frame[].length == 4
     * 제한) 시간: 5초, 메모리: 128MB
     * https://programmers.co.kr/learn/courses/30/lessons/60061
     */
    // 0: 기둥, 1: 보, 2: 없음
    public static final int COLUMN = 0;
    public static final int BEAM = 1;

    public int[][] 기둥과_보_설치(int n, int[][] build_frame) {
        int length = n + 1;
        boolean[][] columns = new boolean[length][length];
        boolean[][] beams = new boolean[length][length];

        for (int[] request : build_frame) {
            int x = request[0];
            int y = request[1];
            int materialType = request[2];
            int buildType = request[3];

            // 삭제
            if (buildType == 0) {
                if (canDelete(columns, beams, x, y, materialType)) {
                    build(columns, beams, x, y, materialType, false);
                }
            }
            // 설치
            else if (buildType == 1) {
                if (canSet(columns, beams, x, y, materialType)) {
                    build(columns, beams, x, y, materialType, true);
                }
            }
        }

        List<int[]> answer = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (columns[i][j]) {
                    answer.add(new int[]{i, j, COLUMN});
                }
                if (beams[i][j]) {
                    answer.add(new int[]{i, j, BEAM});
                }
            }
        }

        answer.sort((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else {
                return o1[2] - o2[2];
            }
        });

        return answer.toArray(int[][]::new);
    }

    public boolean canSet(boolean[][] columns, boolean[][] beams, int x, int y, int materialType) {
        // 기둥 설치 가능 여부
        if (materialType == 0) {
            // 바닥 위 || 다른 기둥 위 || 보의 한쪽 끝 위
            return checkColumnsForColumn(columns, x, y) || checkBeamsForColumn(beams, x, y);
        }
        // 보 설치 가능 여부
        else if (materialType == 1) {
            // 한쪽 끝이 기둥 위 || 양쪽 끝이 다른 보와 동시 연결
            return checkColumnsForBeam(columns, x, y) || checkBeamsForBeam(beams, x, y);
        }

        return false;
    }

    public boolean checkColumnsForColumn(boolean[][] columns, int x, int y) {
        // 바닥 바로 위
        if (y == 0) {
            return true;
        }
        // 다른 기둥 위
        else {
            return columns[x][y - 1];
        }
    }

    public boolean checkBeamsForColumn(boolean[][] beams, int x, int y) {
        // 보의 한쪽 끝 위
        boolean check = beams[x][y];

        if (x != 0) {
            check |= beams[x - 1][y];
        }

        return check;
    }

    public boolean checkColumnsForBeam(boolean[][] columns, int x, int y) {
        // 바닥에는 바로 설치할 수 없음
        if (y == 0) {
            return false;
        }

        // 한쪽 끝이 기둥 위
        boolean check = columns[x][y - 1];

        if (x != columns.length - 1) {
            check |= columns[x + 1][y - 1];
        }

        return check;
    }

    public boolean checkBeamsForBeam(boolean[][] beams, int x, int y) {
        // 양쪽 끝이 다른 보와 동시 연결
        boolean check = false;

        if (x != 0) {
            check = beams[x - 1][y];
        }

        if (x != beams.length - 1) {
            check &= beams[x + 1][y];
        }

        return check;
    }

    public boolean canDelete(boolean[][] columns, boolean[][] beams, int x, int y, int materialType) {
        int length = columns.length;
        // 건축 상태 복사
        boolean[][] tempColumns = new boolean[length][];
        boolean[][] tempBeams = new boolean[length][];
        for (int i = 0; i < length; ++i) {
            tempColumns[i] = columns[i].clone();
            tempBeams[i] = beams[i].clone();
        }

        // 해당 부분 지우기
        if (materialType == COLUMN) {
            tempColumns[x][y] = false;
        } else {
            tempBeams[x][y] = false;
        }

        // 지운 건축물의 영향을 받는 주변 건축물 저장
        List<int[]> nearList = new ArrayList<>();
        int[][] nearArray;

        // 기둥을 지울 경우 영향을 받는건 (x, y + 1) 위치의 기둥과 보, (x - 1, y + 1) 위치의 보
        if (materialType == COLUMN) {
            nearArray = new int[][]{{x, y + 1, COLUMN}, {x, y + 1, BEAM}, {x - 1, y + 1, BEAM}};
        }
        // 보를 지울 경우 영향을 받는건 (x, y) (x + 1, y) 위치의 기둥, (x - 1, y) (x + 1, y) 위치의 보
        else {
            nearArray = new int[][]{{x, y, COLUMN}, {x + 1, y, COLUMN}, {x - 1, y, BEAM}, {x + 1, y, BEAM}};
        }

        for (int[] near : nearArray) {
            if (near[0] >= 0 && near[0] < length && near[1] >= 0 && near[1] < length) {
                if (isInstalled(tempColumns, tempBeams, near[0], near[1], near[2])) {
                    nearList.add(near);
                }
            }
        }

        boolean delete = true;

        // 건축물을 지워도 주변 모든 건축물이 설치 가능하다면 해당 위치를 지울 수 있음
        for (int[] near : nearList) {
            delete &= canSet(tempColumns, tempBeams, near[0], near[1], near[2]);
        }

        return delete;
    }

    public boolean isInstalled(boolean[][] columns, boolean[][] beams, int x, int y, int materialType) {
        if (materialType == COLUMN) {
            return columns[x][y];
        } else {
            return beams[x][y];
        }
    }

    public void build(boolean[][] columns, boolean[][] beams, int x, int y, int materialType, boolean build) {
        if (materialType == COLUMN) {
            columns[x][y] = build;
        } else {
            beams[x][y] = build;
        }
    }

    /**
     * 채점 실패 원인 분석
     * 1. 보 위에 기둥이 바로 설치되는 경우를 고려하지 못했음
     *      |
     *      -----
     *          |
     *    이렇게 설치되는 경우 지금의 코드에서는 좌표가 겹칩
     *
     * 2. 런타임 에러의 발생 원인
     *      int[][] nearArray = {{x, y + 1}, {x, y - 1}, {x - 1, y}, {x + 1, y}};
     *      여기에 {x, y} (보의 왼쪽 끝에 기둥이 설치된 경우 고려) 를 추가하면 채점시 런타임 에러가 추가로 발생함 (테스트 22번)
     *
     *      -> 설치 단계에서 인덱스 - 1 하는 과정에서 발생, 설치 가능 여부를 세분화하여 런타임 에러 모두 제거함
     *
     * 3. 실패 케이스 분석
     *      1) 기둥 삭제시 보의 위치를 잘못 확인했음, 수정했으나 여전히 실패 케이스 발생
     *      2) 보 삭제시 영향 받는 기둥의 위치를 잘못 확인했음, 대부분의 실패 케이스 수정됨
     *      3) 보 설치 조건을 잘못 확인했음, 성공
     */

    /**
     * 전체 연산이 1000개 이하, 처리시간은 5초 -> O(M^3) 연산으로도 처리가 가능하다
     * O(M^3) 연산 : 매 연산마다 전체 구조물을 확인
     */

    public int[][] 기둥과_보_설치_2(int n, int[][] build_frame) {
        Set<Structure> answer = new HashSet<>();

        // 작업의 개수는 최대 1000개
        for (int[] frame : build_frame) {
            Structure structure = new Structure(frame[0], frame[1], frame[2]);
            int operate = frame[3];

            // 삭제하는 경우
            if (operate == 0) {
                // 일단 삭제를 수행
                answer.remove(structure);
                // 가능한 구조물인지 확인
                if (!possible(answer)) {
                    // 가능한 구조물이 아니라면 다시 설치
                    answer.add(structure);
                }
            }
            // 설치하는 경우
            else if (operate == 1) {
                // 일단 설치를 수행
                answer.add(structure);
                // 가능한 구조물인지 확인
                if (!possible(answer)) {
                    // 가능한 구조물이 아니라면 다시 제거
                    answer.remove(structure);
                }
            }
        }

        // 정렬된 결과를 반환
        return answer.stream()
                .sorted()
                .map(Structure::toArray)
                .toArray(int[][]::new);
    }

    // 현재 설치된 구조물이 '가능한' 구조물인지 확인하는 함수
    public boolean possible(Set<Structure> answer) {
        for (Structure structure : answer) {
            int x = structure.x;
            int y = structure.y;
            int stuff = structure.stuff;

            // 설치된 것이 '기둥'인 경우
            if (stuff == 0) {
                // (바닥 위 || 보의 한쪽 끝부분 위 || 다른 기둥 위) 라면 정상
                if (y == 0
                        || answer.contains(new Structure(x - 1, y, 1)) || answer.contains(new Structure(x, y, 1))
                        || answer.contains(new Structure(x, y - 1, 0))) {
                    continue;
                }
                // 아니라면 거짓 반환
                return false;
            }
            // 설치된 것이 '보'인 경우
            else if (stuff == 1) {
                // (한쪽 끝부분이 기둥 위 || 양쪽 끝부분이 다른 보와 동시에 연결) 라면 정상
                if (answer.contains(new Structure(x, y - 1, 0)) || answer.contains(new Structure(x + 1, y - 1, 0))
                        || answer.contains(new Structure(x - 1, y, 1)) && answer.contains(new Structure(x + 1, y, 1))) {
                    continue;
                }
                // 아니라면 거짓 반환
                return false;
            }
        }

        return true;
    }

    static class Structure implements Comparable<Structure> {
        int x;
        int y;
        int stuff;

        public Structure(int x, int y, int stuff) {
            this.x = x;
            this.y = y;
            this.stuff = stuff;
        }

        public int[] toArray() {
            return new int[]{x, y, stuff};
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, stuff);
        }

        @Override
        public boolean equals(Object obj) {
            Structure structure = (Structure) obj;

            return this.x == structure.x
                    && this.y == structure.y
                    && this.stuff == structure.stuff;
        }

        @Override
        public int compareTo(Structure other) {
            if (this.x == other.x && this.y == other.y) {
                return Integer.compare(this.stuff, other.stuff);
            }
            if (this.x == other.x) {
                return Integer.compare(this.y, other.y);
            }
            return Integer.compare(this.x, other.x);
        }
    }

    /**
     * 난이도 중
     * 2 <= N <= 50
     * 1 <= M <= 13
     * 1 <= 집 <= 2N
     * M <= 치킨집 <= 13
     * 제한) 시간: 1초, 메모리: 512MB
     * https://www.acmicpc.net/problem/15686
     */
    public int 치킨_배달(int n, int m, String[] map) {
        Map<int[], Integer> houses = new HashMap<>();
        List<int[]> chickens = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] street = map[i].split(" ");

            for (int j = 0; j < n; j++) {
                // 집
                if (street[j].equals("1")) {
                    houses.put(new int[]{i, j}, Integer.MAX_VALUE);
                }
                // 치킨집
                else if (street[j].equals("2")) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        boolean[] visited = new boolean[chickens.size()];
        List<int[]> leftChickens = combination(visited, 0, chickens.size(), m);

        int answer = Integer.MAX_VALUE;

        // 조합으로 뽑힌 남은 치킨집들의 인덱스
        for (int[] lefts : leftChickens) {
            // 집 복사
            Map<int[], Integer> copiedHouses = new HashMap<>(houses);

            // 각 치킨집과 집까지의 거리 저장
            for (int idx : lefts) {
                // 모든 집에 대해서
                copiedHouses.entrySet()
                        .forEach(entry -> {
                            int dist = distance(entry.getKey(), chickens.get(idx));
                            // 짧은 거리 저장
                            if (dist < entry.getValue()) {
                                entry.setValue(dist);
                            }
                        });
            }

            answer = Integer.min(answer, copiedHouses.values().stream().mapToInt(i -> i).sum());
        }

        // 최솟값 출력
        return answer;
    }

    public List<int[]> combination(boolean[] visited, int start, int n, int r) {
        List<int[]> resultList = new ArrayList<>();

        if (r == 0) {
            resultList.add(IntStream.range(0, n)
                    .filter(idx -> visited[idx])
                    .toArray());
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            resultList.addAll(combination(visited, i + 1, n, r - 1));
            visited[i] = false;
        }

        return resultList;
    }

    public int distance(int[] house, int[] chicken) {
        return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
    }

    /**
     * 최대 13개의 치킨집 중에서 M개를 뽑는 조합의 수는 최대 1716, 100_000만이 채 되지 않음 -> 모든 경우의 수를 다 계산해도 무리가 없다.
     * 풀이과정은 동일한데 최소 거리를 연산하는 과정을 비효율적으로 작성한 것 같다.
     * <p>
     * 먼저 푼 방식 -> 각 집에 거리를 묶어서 저장 아니 왜 이렇게 풀었지..? 어차피 매번 새로 계산해야되는건데
     * 치킨집을 기준으로 각 집까지의 거리를 계산했음 -> 계산을 위해 억지로 돌아감
     * 해답 풀이 방식 -> 거리 계산용 변수 temp 를 둠.
     * 칩을 기준으로 각 치킨집까지의 거리를 계산했음, 이게 맞지
     */

    public int 치킨_배달_2(int n, int m, String[] map) {
        List<int[]> house = new ArrayList<>();
        List<int[]> chicken = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            String[] data = map[r].split(" ");

            for (int c = 0; c < n; c++) {
                if (data[c].equals("1")) {
                    // 집
                    house.add(new int[]{r, c});
                } else if (data[c].equals("2")) {
                    // 치킨집
                    chicken.add(new int[]{r, c});
                }
            }
        }

        // 모든 치킨집 중에서 m개의 치킨집을 뽑는 조합 계산
        List<List<int[]>> candidates = combinationToChicken(chicken, m);

        // 치킨 거리 합의 최소를 찾아 반환
        int result = (int) 1e9;

        for (List<int[]> candidate : candidates) {
            result = Integer.min(result, get_sum(house, candidate));
        }

        return result;
    }

    public List<List<int[]>> combinationToChicken(List<int[]> chicken, int m) {
        List<int[]> indexes = combination(new boolean[chicken.size()], 0, chicken.size(), m);

        List<List<int[]>> candidates = new ArrayList<>();

        for (int[] index : indexes) {
            List<int[]> candidate = Arrays.stream(index)
                    .mapToObj(chicken::get)
                    .collect(Collectors.toList());

            candidates.add(candidate);
        }

        return candidates;
    }

    // 치킨 거리의 합을 계산
    public int get_sum(List<int[]> house, List<int[]> candidate) {
        int result = 0;

        // 모든 집에 대해
        for (int[] h : house) {
            int hx = h[0];
            int hy = h[1];

            // 가장 가까운 치킨집 찾기
            int temp = (int) 1e9;
            for (int[] c : candidate) {
                int cx = c[0];
                int cy = c[1];

                temp = Integer.min(temp, Math.abs(hx - cx) + Math.abs(hy - cy));
            }
            // 가장 가까운 치킨집까지의 거리를 더하기
            result += temp;
        }
        // 치킨 거리의 합 반환
        return result;
    }

    /**
     * 1 <= n <= 200
     * 1 <= weak.length <= 15
     * 0 <= weak <= n - 1
     * 1 <= dist.length <= 8
     * 1 <= dist <= 100
     * 제한) 시간: 1초, 메모리: 128MB
     * https://programmers.co.kr/learn/courses/30/lessons/60062
     */

    public int 외벽_점검(int n, int[] weak, int[] dist) {
        LinkedList<Integer> weakQueue = Arrays.stream(weak)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Integer> weakList = null;
        int weakPointDist = (int) 1e9;
        int start = weakQueue.getLast();

        do {
            int a = weakQueue.getFirst();
            int b = weakQueue.getLast();
            int temp = calculateDist(a, b, n, true);

            if (temp < weakPointDist) {
                weakPointDist = temp;
                weakList = new LinkedList<>(weakQueue);
            }
            weakQueue.add(weakQueue.poll());
        }
        while (weakQueue.getFirst() != start);

        for (int i = 0; i < weakList.size(); i++) {
            if (weakList.get(i) < weakList.getFirst()) {
                weakList.set(i, weakList.get(i) + n);
            }
        }

        boolean flag = true;
        int tail = weakList.size() - 1;

        LinkedList<Integer> friends = Arrays.stream(dist)
                .boxed()
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        while (true) {
            if (tail < 0) {
                break;
            }

            int a = weakList.getFirst();
            int b = weakList.get(tail);
            int temp = b - a;

            if (a != b) {
                int check = weakList.size();
                for (int i = 0; i < friends.size(); i++) {
                    if (temp <= friends.get(i)) {
                        friends.remove(i);
                        for (int j = 0; j <= tail; j++) {
                            weakList.poll();
                        }
                        break;
                    }
                }
                if (weakList.size() != check) {
                    tail = weakList.size() - 1;
                    continue;
                }
            } else {
                friends.poll();
                weakList.remove(tail);
                tail = weakList.size() - 1;
                continue;
            }
            --tail;
        }

        int answer = dist.length - friends.size();
        if (answer == 0) {
            answer = -1;
        }
        return answer;
    }

    public int calculateDist(int a, int b, int n, boolean ls) {
        int distOne = Math.abs(a - b);
        int distTwo = n - distOne;

        // true : long distance
        if (ls) {
            return Integer.max(distOne, distTwo);
        }
        // false : short distance
        return Integer.min(distOne, distTwo);
    }

    /**
     * ver.01) 72.0 / 100.0
     */
}
