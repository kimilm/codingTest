package level_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//https://school.programmers.co.kr/learn/courses/30/lessons/118667
public class 두_큐_합_같게_만들기 {
    public int solution2(int[] queue1, int[] queue2) {
        // tail out of index padding
        int[] array = new int[queue1.length + queue2.length + 1];
        System.arraycopy(queue1, 0, array, 0, queue1.length);
        System.arraycopy(queue2, 0, array, queue1.length, queue2.length);

        long queueOneSum = Arrays.stream(queue1).sum();
        long queueTwoSum = Arrays.stream(queue2).sum();

        long half = (queueOneSum + queueTwoSum) / 2;
        long sum = queueOneSum;
        int count = 0;

        int head = 0;
        int tail = queue1.length - 1;
        int finish = array.length - 1;

        while (head < finish && tail < finish) {
            if (sum == half) {
                return count;
            }

            if (sum > half) {
                sum -= array[head++];
            } else {
                sum += array[++tail];
            }

            ++count;
        }

        return -1;
    }

    /**
     * queue 사용했을땐 11번 실패 케이스에서 시간초과 발생 -> 근데 다른사람 풀이 보니까 큐로 푼 사람들 있던데.. 뭐지...
     * 이어붙인 어레이 사용, 인덱스로 계산했을 때 전반적인 수행시간이 1/10 수준으로 감소함
     * 전                                            후
     * 테스트 1 〉	통과 (7.79ms, 78.6MB)             테스트 1 〉	통과 (0.63ms, 70.9MB)
     * 테스트 2 〉	통과 (3.53ms, 75.7MB)             테스트 2 〉	통과 (0.68ms, 70.2MB)
     * 테스트 3 〉	통과 (3.97ms, 76.3MB)             테스트 3 〉	통과 (0.76ms, 72MB)
     * 테스트 4 〉	통과 (4.15ms, 77.4MB)             테스트 4 〉	통과 (1.23ms, 77.5MB)
     * 테스트 5 〉	통과 (4.26ms, 73.6MB)             테스트 5 〉	통과 (0.96ms, 71.3MB)
     * 테스트 6 〉	통과 (4.65ms, 73.8MB)             테스트 6 〉	통과 (0.70ms, 77.5MB)
     * 테스트 7 〉	통과 (5.13ms, 79.3MB)             테스트 7 〉	통과 (0.83ms, 78.6MB)
     * 테스트 8 〉	통과 (5.71ms, 82.2MB)             테스트 8 〉	통과 (1.01ms, 75.6MB)
     * 테스트 9 〉	통과 (5.63ms, 80.4MB)             테스트 9 〉	통과 (0.93ms, 74.7MB)
     * 테스트 10 〉	통과 (12.09ms, 74.6MB)            테스트 10 〉	통과 (1.09ms, 73.8MB)
     * 테스트 11 〉	실패 (시간 초과)                   테스트 11 〉	통과 (7.48ms, 83.2MB)
     * 테스트 12 〉	통과 (64.07ms, 109MB)             테스트 12 〉	통과 (5.09ms, 87.6MB)
     * 테스트 13 〉	통과 (63.48ms, 103MB)             테스트 13 〉	통과 (4.66ms, 88.3MB)
     * 테스트 14 〉	통과 (24.12ms, 103MB)             테스트 14 〉	통과 (5.04ms, 82.4MB)
     * 테스트 15 〉	통과 (88.89ms, 129MB)             테스트 15 〉	통과 (6.88ms, 113MB)
     * 테스트 16 〉	통과 (90.00ms, 117MB)             테스트 16 〉	통과 (8.13ms, 93.3MB)
     * 테스트 17 〉	통과 (118.51ms, 112MB)            테스트 17 〉	통과 (5.70ms, 96.8MB)
     * 테스트 18 〉	통과 (149.40ms, 141MB)            테스트 18 〉	통과 (8.84ms, 143MB)
     * 테스트 19 〉	통과 (202.88ms, 169MB)            테스트 19 〉	통과 (15.92ms, 133MB)
     * 테스트 20 〉	통과 (152.93ms, 141MB)            테스트 20 〉	통과 (13.33ms, 142MB)
     * 테스트 21 〉	통과 (296.06ms, 150MB)            테스트 21 〉	통과 (10.09ms, 134MB)
     * 테스트 22 〉	통과 (100.49ms, 159MB)            테스트 22 〉	통과 (13.52ms, 143MB)
     * 테스트 23 〉	통과 (130.99ms, 150MB)            테스트 23 〉	통과 (12.31ms, 120MB)
     * 테스트 24 〉	통과 (135.47ms, 157MB)            테스트 24 〉	통과 (14.73ms, 126MB)
     * 테스트 25 〉	통과 (4.88ms, 70.9MB)             테스트 25 〉	통과 (0.83ms, 74.1MB)
     * 테스트 26 〉	통과 (4.44ms, 71.3MB)             테스트 26 〉	통과 (0.78ms, 76.4MB)
     * 테스트 27 〉	통과 (6.03ms, 77.4MB)             테스트 27 〉	통과 (1.10ms, 92.4MB)
     * 테스트 28 〉	통과 (55.25ms, 109MB)             테스트 28 〉	통과 (10.98ms, 93.9MB)
     * 테스트 29 〉	통과 (8.99ms, 78.6MB)             테스트 29 〉	통과 (1.61ms, 76.4MB)
     * 테스트 30 〉	통과 (75.44ms, 120MB)             테스트 30 〉	통과 (9.43ms, 98.6MB)
     */

    public int solution(int[] queue1, int[] queue2) {
        List<Integer> queueOneAsList = Arrays.stream(queue1).boxed().collect(Collectors.toList());
        List<Integer> queueTwoAsList = Arrays.stream(queue2).boxed().collect(Collectors.toList());
        MyQueue myQueue1 = MyQueue.of(queueOneAsList);
        MyQueue myQueue2 = MyQueue.of(queueTwoAsList);

        long mid = (myQueue1.getSum() + myQueue2.getSum()) / 2;
        int count = 0;

        while (myQueue1.getSum() != myQueue2.getSum()) {
            count += move(myQueue1, myQueue2, mid);

            if (myQueue1.isEmpty()
                    || myQueue2.isEmpty()
                    || myQueue1.equals(queueOneAsList)) {
                return -1;
            }
        }

        return count;
    }

    public int move(MyQueue queue1, MyQueue queue2, long mid) {
        if (queue1.getSum() < queue2.getSum()) {
            MyQueue temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        int count = 0;

        queue2.add(queue1.poll());
        ++count;

        return count;
    }
}

class MyQueue {
    private LinkedList<Integer> queue;
    private long sum;

    public MyQueue(LinkedList<Integer> queue, long sum) {
        this.queue = queue;
        this.sum = sum;
    }

    public static MyQueue of(List<Integer> list) {
        return new MyQueue(
                new LinkedList<>(list),
                list.stream().mapToLong(i -> i).sum()
        );
    }

    public long getSum() {
        return sum;
    }

    public void add(int value) {
        queue.add(value);
        sum += value;
    }

    public int peek() {
        return queue.peek();
    }

    public int poll() {
        int poll = queue.poll();
        sum -= poll;

        return poll;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean equals(List<Integer> list) {
        return queue.equals(list);
    }
}
