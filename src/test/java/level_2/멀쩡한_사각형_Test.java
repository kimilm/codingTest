package level_2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class 멀쩡한_사각형_Test {

    private final 멀쩡한_사각형 square = new 멀쩡한_사각형();

    @Test
    void solutionTest() {
//        System.out.println(square.solution(8, 12));
        System.out.println(square.solution(1_000_000_000, 999_999_999));
    }

    @Test
    void solutionVer2Test() {
//        System.out.println(square.solutionVer2(8, 12));
        System.out.println(square.solutionVer2(1_000_000_000, 999_999_999));
    }
}