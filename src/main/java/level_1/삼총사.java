package level_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/131705?language=java
public class 삼총사 {
    public int solution(int[] number) {
        List<int[]> threeFriendList = combination(new boolean[number.length], number, 0, number.length, 3);

        int answer = 0;

        for (int[] friends : threeFriendList) {
            if (Arrays.stream(friends).sum() == 0) {
                ++answer;
            }
        }

        return answer;
    }

    public List<int[]> combination(boolean[] visited, int[] array, int start, int n, int r) {
        List<int[]> resultList = new ArrayList<>();

        if (r == 0) {
            resultList.add(IntStream.range(0, n)
                    .filter(idx -> visited[idx])
                    .map(idx -> array[idx])
                    .toArray());

            return resultList;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            resultList.addAll(combination(visited, array, i + 1, n, r - 1));
            visited[i] = false;
        }

        return resultList;
    }
}
