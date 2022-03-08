package level_2;

import java.math.BigInteger;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 * Summer/Winter Coding(2019) > 멀쩡한 사각형
 */
public class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        int big = Integer.max(w, h);
        int small = Integer.min(w, h);

        // 최대공약수 연산
        int gcd = GCD(big, small);

        big /= gcd;
        small /= gcd;

        long notSquare = big;

        // 직선이 지나가는 위치중 겹치지 않는 부분 구하기
        for (int x = 1; x < small; ++x) {
            double result = equation(big, small, x);

            if (result - (int) result != 0) {
                ++notSquare;
            }
        }

        long answer = (long) w * h - notSquare * gcd;

        return answer;
    }

    // y = (b / a) * x
    public double equation(int a, int b, int x) {
        return ((double) b / a) * x;
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

    /**
     * 이렇게 푸는거였다
     * java.math.BigInteger 에 gcd 함수가 존재한다.
     * 단 BigInteger 는 큰 수를 다루는 대신 성능이 떨어지는 문제가 있어서
     * 빠른 연산을 위해 다양한 비트 조작 메서드를 제공한다.
     */
    public long solutionVer2(int w, int h) {
        BigInteger width = BigInteger.valueOf(w);
        BigInteger height = BigInteger.valueOf(h);

        // 사용되지 않는 사각형 = width + height - 최대공약수
        BigInteger notUse = width.add(height).subtract(width.gcd(height));
        // long notUse = (long) w + (long) h - width.gcd(height).longValue();

        // BigInteger 의 성능저하를 최소화 하기 위해 곱셈부분은 long 으로 선언하여 따로 연산하는게 좋아보인다.
        return width.multiply(height).subtract(notUse).longValue();
    }
}
