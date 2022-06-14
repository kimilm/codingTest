package this_is_coding_test.ch14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortingQuestions {
    /**
     * 난이도: 하
     * 1 <= N <= 100_000
     * 1 <= score <= 100
     * 1 <= name.length <= 10
     * 제한) 시간: 1초, 메모리: 256MB
     * https://www.acmicpc.net/problem/10825
     */
    public List<String> 국영수(List<String> inputs) {
        List<Student> studentList = new ArrayList<>();

        for (int i = 1; i < inputs.size(); i++) {
            String[] split = inputs.get(i).split(" ");
            studentList.add(new Student(
                    split[0],
                    Integer.parseInt(split[1]),
                    Integer.parseInt(split[2]),
                    Integer.parseInt(split[3])
            ));
        }

        return studentList.stream().sorted().map(student -> student.name).collect(Collectors.toList());
    }

    static class Student implements Comparable<Student> {
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            // 국어 내림차순
            if (this.korean != o.korean) {
                return o.korean - this.korean;
            }
            // 영어 오름차순
            if (this.english != o.english) {
                return this.english - o.english;
            }
            // 수학 내림차순
            if (this.math != o.math) {
                return o.math - this.math;
            }
            // 이름 사전 오름차순
            return this.name.compareTo(o.name);
        }
    }

    /**
     * 난이도: 하
     * 1 <= N <= 200_000
     * 1 <= 집의_위치 <= 100_000
     * 제한) 시간: 1초, 메모리: 256MB
     * https://www.acmicpc.net/problem/18310
     */
    public int 안테나(String[] inputs) {
        int n = Integer.parseInt(inputs[0]);
        int[] houses = Arrays.stream(inputs[1].split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int antenna = 0;
        int distance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int location = houses[i];
            int sum = Arrays.stream(houses).map(house -> Math.abs(house - location)).sum();

            if (sum < distance) {
                antenna = location;
                distance = sum;
            }
        }

        return antenna;
    }

    /**
     * 집들의 중간에 설치하면 당연히 거리가 최소가 됨
     * 정렬해서 중간값을 리턴하면됨
     * 해당 코드로 제출했는데 틀린것은 정렬을 빼먹어서 그랬음
     */
    public int 안테나2(String[] inputs) {
        int n = Integer.parseInt(inputs[0]);
        int[] houses = Arrays.stream(inputs[1].split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        return houses[(n - 1) / 2];
    }

    /**
     * 난이도: 하
     * 1 <= N <= 500
     * 1 <= stages.length <= 200_000
     * 1 <= stages[i] <= N + 1 (현재 사용자가 도전중인 스테이지, N + 1은 전부 다 깬 사람)
     * 제한) 시간: 1초, 메모리: 128MB
     * https://programmers.co.kr/learn/courses/30/lessons/42889
     */
    public int[] 실패율(int N, int[] stages) {
        List<Stage> stageList = new ArrayList<>();
        // 스테이지 생성
        for (int i = 1; i <= N; i++) {
            stageList.add(new Stage(i));
        }
        // 각 스테이지에 대해서
        for (int stage : stages) {
            // 전부 클리어한 경우(N+1)를 제외하고 해당 스테이지 도착
            if (stage != N + 1) {
                stageList.get(stage - 1).arrive();
            }
            // 전부 클리어한 경우에는 인덱스 조절
            else {
                --stage;
            }
            // 해당 스테이지 이전까지 클리어
            for (int i = 0; i < stage; ++i) {
                stageList.get(i).clear();
            }
        }
        // 실패율 내림차순, 번호 오름차순 정렬 후 리턴
        return stageList.stream().sorted().mapToInt(stage -> stage.number).toArray();
    }

    static class Stage implements Comparable<Stage> {
        int number;
        int clear;
        int arrive;

        public Stage(int number) {
            this.number = number;
            this.clear = 0;
            this.arrive = 0;
        }

        public void arrive() {
            ++this.arrive;
        }

        public void clear() {
            ++this.arrive;
            ++this.clear;
        }

        public float fail() {
            if (arrive != 0) {
                return (arrive - clear) / (float) arrive;
            }
            return 0f;
        }

        @Override
        public int compareTo(Stage o) {
            // 내림차순
            int compare = Float.compare(o.fail(), this.fail());
            if (compare != 0) {
                return compare;
            }
            // 실패율이 같으면 번호 오름차순
            return this.number - o.number;
        }
    }

    /**
     * level_1.L1.실패율, 이전에는 Map을 사용해서 풀었는데 그게 더 빠르지만 코드가 직관적이지 못했다.
     */

    /**
     * 난이도: 중
     * 1 <= N <= 100_000
     * 제한) 시간: 2초, 메모리: 128MB
     * https://www.acmicpc.net/problem/1715
     */
    public int 카드_정렬하기(String[] inputs) {
        int n = Integer.parseInt(inputs[0]);
        int[] card = Arrays.stream(Arrays.copyOfRange(inputs, 1, inputs.length))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        if (n == 1) {
            return 0;
        }

        int answer = 0;
        int bunch = card[0];

        for (int i = 1; i < card.length; ++i) {
            bunch += card[i];
            answer += bunch;
        }

        return answer;
    }

    /**
     * 작은 값 부터 더해나가는 방식은 틀렸다고 나온다.
     *
     * 매 순간 가장 작은 카드뭉치들을 더해나가야 한다.
     * 1. 10 20 40 50 60
     * 2. 30 40 50 60
     * 3. 70 50 60
     * 4. 70 110
     * 5. 180
     */
}
