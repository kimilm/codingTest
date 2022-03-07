package level_2;

import java.util.*;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 * Summer/Winter Coding(2019) > 멀쩡한 사각형
 */
public class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        Set<Point> pointSet = new HashSet<>();

        int[] mm = new int[]{w, h};
        Arrays.sort(mm);

        int horizontal = mm[0]; // 짧은쪽
        int vertical = mm[1];   // 긴쪽

        // x를 기준으로 점 찾기
        for (int i = 0; i < horizontal; ++i) {
            addPoint(pointSet, i, equationForY(horizontal, vertical, i));
        }

        // y를 기준으로 점 찾기
        for (int i = 0; i < vertical; ++i) {
            addPoint(pointSet, i, equationForX(horizontal, vertical, i));
        }

        //log
        pointSet.forEach(System.out::println);

        long answer = (long)w * h - pointSet.size();

        return answer;
    }

    // y = (b / a) * x
    public double equationForY(int a, int b, int x) {
        return ((double) b / a) * x;
    }

    // x = (a / b) * y
    public double equationForX(int a, int b, int y) {
        return ((double) a / b) * y;
    }

    void addPoint(Set pointSet, double x, double y) {
        if ((y + 0.5) % 2 == 0) {
            pointSet.add(new Point(x, y + 0.5));
            pointSet.add(new Point(x, y - 0.5));
        } else {
            pointSet.add(new Point(x, y));
        }
    }

    static class Point {
        int x;
        int y;

        Point(double x, double y) {
            this.x = (int)x;
            this.y = (int)y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        //log

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
