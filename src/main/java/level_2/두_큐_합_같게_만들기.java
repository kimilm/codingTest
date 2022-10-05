package level_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class 두_큐_합_같게_만들기 {
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
                list.stream().mapToInt(i -> i).sum()
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
