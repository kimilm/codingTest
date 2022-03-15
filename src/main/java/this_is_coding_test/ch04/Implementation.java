package this_is_coding_test.ch04;

import java.nio.charset.StandardCharsets;

public class Implementation {

    /**
     * 난이도 하
     * 시간제한 1초
     * 메모리제한 128MB
     * 1 <= N <= 100
     * 1 <= 이동 횟수 <= 100
     * x y 좌표를 공백으로 구분하여 출력
     */
    public String 상하좌우(int N, String travels) {
        int x = 1;
        int y = 1;
        String[] travelArray = travels.split(" ");

        for (String travel : travelArray) {
            if (travel.equals("L") && y > 1) {
                --y;
            } else if (travel.equals("R") && y < N) {
                ++y;
            } else if (travel.equals("U") && x > 1) {
                --x;
            } else if (travel.equals("D") && x < N) {
                ++x;
            }
        }

        return x + " " + y;
    }

    /**
     * dx dy 배열을 이용해서 이동 방향을 기록하는 방식이 자주 사용되니 알아두자
     */
    public String 상하좌우_2(int N, String plans) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        String[] move_types = {"L", "R", "U", "D"};

        int x = 1;
        int y = 1;

        int nx = 0;
        int ny = 0;

        for (String plan : plans.split(" ")) {
            for (int i = 0; i < move_types.length; i++) {
                if (plan.equals(move_types[i])) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                }
            }

            if (nx < 1 || ny < 1 || nx > N || ny > N) {
                continue;
            }

            x = nx;
            y = ny;
        }

        return x + " " + y;
    }

    /**
     * 0 <= N <= 23
     * 00시 00분 00초 ~ N시 59분 59초 모든 시각중 3이 하나라도 포함되는 모든 경우의 수 출력
     */
    public int 시각(int N) {
        int hourHasThree = 60 * 60;
        int minuteHasThree = 15 * 60;
        int secondHasThree = 45 * 15;

        int answer = 0;

        for (int i = 0; i <= N; i++) {
            if (String.valueOf(i).contains("3"))
                answer += hourHasThree;
            else {
                answer += minuteHasThree + secondHasThree;
            }
        }

        return answer;
    }

    /**
     * 하루는 86_400초, 모든 경우의 수가 86_400개
     * 100_000개도 되지 않는 경우의 수를 가진다 -> 큰 고민 할 것 없이 그냥 for문 돌려도 된다
     */
    public int 시각_2(int N) {
        int answer = 0;

        for (int h = 0; h < (N + 1); ++h) {
            for (int m = 0; m < 60; ++m) {
                for (int s = 0; s < 60; ++s) {
                    String str = String.valueOf(h) + m + s;
                    if (str.contains("3")) {
                        ++answer;
                    }
                }
            }
        }

        return answer;
    }

    /**
     * 난이도 하
     * 시간제한 1초
     * 메모리제한 128MB
     * 현재 나이트의 위치를 열과 행으로 a1과 같이 입력
     * 나이트가 이동할 수 있는 경우의 수를 출력
     */
    public int 왕실의_나이트(String move) {
        byte[] moves = move.getBytes(StandardCharsets.UTF_8);
        int[] rotates = {-2, 2, 2, -2};
        int x = moves[0] - 'a';
        int y = moves[1] - '1';
        int body = x;
        int tail = y;
        int answer = 8;

        for (int rotate : rotates) {
            int straight = body + rotate;
            int left = tail - 1;
            int right = tail + 1;

            if (straight < 0 || straight > 8) {
                answer -= 2;
            } else if (left < 0 || left > 8 || right < 0 || right > 8) {
                --answer;
            }

            int tmp = body;
            body = tail;
            tail = tmp;
        }
        return answer;
    }

    /**
     * 쓸데없는 고민을 너무 많이 했다.
     * 애초에 나이트가 갈 수 있는 위치는 8개
     * steps 변수로 상하좌우_2의 dx dy를 대신함
     */
    public int 왕실의_나이트_2(String move) {
        byte[] moves = move.getBytes(StandardCharsets.UTF_8);
        int row = moves[0] - 'a';
        int col = moves[1] - '1';

        int[][] steps = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};
        int answer = 0;

        for (int[] step : steps) {
            int nextCol = col + step[0];
            int nextRow = row + step[1];

            if (nextCol >= 0 && nextCol <= 7 && nextRow >= 0 && nextRow <= 7) {
                ++answer;
            }
        }

        return answer;
    }
}
