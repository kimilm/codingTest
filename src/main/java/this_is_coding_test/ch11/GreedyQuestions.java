package this_is_coding_test.ch11;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class GreedyQuestions {

    /**
     * 난이도 하
     * 1 <= n <= 100_000
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 모험가_길드(int n, int[] fears) {
        Queue<Integer> queue = Arrays.stream(fears)
                .boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toCollection(LinkedList::new));

        int group = 0;
        int fear = queue.peek();

        while (fear <= queue.size()) {
            for (int i = 0; i < fear; i++) {
                queue.poll();
            }
            ++group;

            if (!queue.isEmpty()) {
                fear = queue.peek();
            }
        }

        return group;
    }

    /**
     * 오름차순 정렬을 수행하고 공포도가 낮은 모험가부터 하나씩 그룹에 포함시킨다
     * 현재 그룹에 포함된 모험가의 수가 현재 확인하고 있는 공포도보다 크거나 같다면 그룹을 결성할 수 있다
     */
    public int 모험가_길드_2(int n, int[] data) {
        int[] fears = data.clone();

        // 오름차순 정렬
        Arrays.sort(fears);

        // 그룹의 수
        int result = 0;

        // 현재 그룹에 포함된 모험가의 수
        int count = 0;

        // 공포도가 낮은 것부터 하나씩 확인하기
        for (int fear : fears) {
            // 현재 그룹에 해당 모험가를 포함시킴
            ++count;

            // 현재 그룹에 포함된 모험가의 수가 현재의 공포도 이상이라면
            if (count >= fear) {
                // 그룹 결성
                ++result;

                // 현재 그룹에 포함된 모험가의 수 초기화
                count = 0;
            }
        }

        return result;
    }
}
