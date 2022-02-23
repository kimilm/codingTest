package level_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/68645
 * 월간 코드 챌린지 시즌1 > 삼각 달팽이
 */
public class 삼각_달팽이 {
    public int[] solution(int n) {

        Integer[][] snail = new Integer[n][];

        // init
        for (int i = 0; i < n; i++) {
            snail[i] = new Integer[i + 1];
        }

        int value = 1;
        int direction = 2;
        Move move = new Move();

        while (n > 0) {
            for (int i = 0; i < n; i++) {
                // fill and move
                move.moveByDirection(direction);
                snail[move.getI()][move.getJ()] = value++;
            }
            direction = setDirection(direction);
            --n;
        }

        List<Integer> answer = new ArrayList<>();
        Stream.of(snail).forEach(result -> Collections.addAll(answer, result));

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public int setDirection(int direction) {
        if (direction == 0) {
            return 2;
        }
        return --direction;
    }

    class Move {
        private int i;
        private int j;

        public Move() {
            this.i = -1;
            this.j = 0;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public void moveByDirection(int direction) {
            if (direction == 2) {   //down
                ++i;
            } else if (direction == 1) {    //right
                ++j;
            } else if (direction == 0) {    //up
                --i;
                --j;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
