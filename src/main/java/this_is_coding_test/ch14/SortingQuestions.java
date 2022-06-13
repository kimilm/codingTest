package this_is_coding_test.ch14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
