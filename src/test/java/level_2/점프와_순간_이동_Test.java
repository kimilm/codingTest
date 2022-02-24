package level_2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class 점프와_순간_이동_Test {

    private 점프와_순간_이동 jt = new 점프와_순간_이동();

    @Test
    void solutionTest() {
        Arrays.asList(
                jt.solution(5),
                jt.solution(6),
                jt.solution(5000)
        ).forEach(System.out::println);
    }
}