package level_2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/87377
 * 위클리 챌린지 > 교점에 별 만들기
 */
public class 교점에_별_만들기 {
    public String[] solution(int[][] line) {
        Set<Point> pointSet = new HashSet<>();

        // find intersection
        for (int i = 0; i < (line.length - 1); ++i) {
            for (int j = (i + 1); j < line.length; ++j) {
                long[] compareResult = compareLines(line[i], line[j]);

                Optional<Point> point = findIntersection(compareResult);

                point.ifPresent(pointSet::add);
            }
        }

        // calculate x y min
        int xMin = pointSet.stream().mapToInt(Point::getX).distinct().min().getAsInt();
        int yMin = pointSet.stream().mapToInt(Point::getY).distinct().min().getAsInt();

        Point move = new Point(-xMin, -yMin);

        pointSet.forEach(point -> point.movePoint(move));
        pointSet.forEach(Point::symmetrize);

        // calculate square
        int xMax = pointSet.stream().mapToInt(Point::getX).distinct().max().getAsInt() + 1;
        int yMax = pointSet.stream().mapToInt(Point::getY).distinct().max().getAsInt() + 1;

        char[][] dots = new char[xMax][yMax];

        // draw star or dot
        for (char[] dot : dots) {
            Arrays.fill(dot, '.');
        }

        pointSet.forEach(point -> dots[point.getX()][point.getY()] = '*');

        List<String> answer = Arrays.stream(dots).map(String::valueOf).collect(Collectors.toList());
        Collections.reverse(answer);

        // return
        return answer.toArray(String[]::new);
    }

    public long[] compareLines(int[] lineOne, int[] lineTwo) {
        long a = lineOne[0], b = lineOne[1], e = lineOne[2];
        long c = lineTwo[0], d = lineTwo[1], f = lineTwo[2];

        long adbc = a * d - b * c;
        long bfed = b * f - e * d;   // x
        long ecaf = e * c - a * f;   // y

        return new long[]{adbc, bfed, ecaf};
    }

    public Optional<Point> findIntersection(long[] compareResult) {
        if (compareResult[0] == 0) {
            return Optional.empty();
        }

        if (hasRemainder(compareResult[1], compareResult[0]) || hasRemainder(compareResult[2], compareResult[0])) {
            return Optional.empty();
        }

        int x = Long.valueOf(compareResult[1] / compareResult[0]).intValue();
        int y = Long.valueOf(compareResult[2] / compareResult[0]).intValue();

        return Optional.of(new Point(x, y));
    }

    public boolean hasRemainder(long dividend, long divisor) {
        return dividend % divisor != 0;
    }

    class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void symmetrize() {
            int temp = this.x;
            this.x = this.y;
            this.y = temp;
        }

        public void movePoint(Point p) {
            this.x += p.getX();
            this.y += p.getY();
        }
    }
}
