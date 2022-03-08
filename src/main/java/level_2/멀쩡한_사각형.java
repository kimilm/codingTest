package level_2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 * Summer/Winter Coding(2019) > 멀쩡한 사각형
 */
public class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        Set<Point> pointSet = new HashSet<>();

        int big = Integer.max(w, h);
        int small = Integer.min(w, h);

        int gcd = GCD(big, small);

        big /= gcd;
        small /= gcd;

        // x를 기준으로 점 찾기
        for (int x = 0; x < big; ++x) {
            addPoint(pointSet, x, equationForY(big, small, x));
        }

        // y를 기준으로 점 찾기
        for (int y = 0; y < small; ++y) {
            addPoint(pointSet, equationForX(big, small, y), y);
        }

        long answer = (long) w * h - (long) pointSet.size() * gcd;

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

    public int GCD(int a, int b) {
        int r = a % b;

        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }

        return b;
    }

    void addPoint(Set<Point> pointSet, double x, double y) {
        int pointX = (int) Math.floor(x);
        int pointY = (int) Math.floor(y);

        pointSet.add(new Point(pointX, pointY));
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
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
    }
}
