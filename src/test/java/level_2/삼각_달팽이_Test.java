package level_2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class 삼각_달팽이_Test {

    private final 삼각_달팽이 samdal = new 삼각_달팽이();

    @Test
    void solution() {
        samdal.solution(1);
        samdal.solution(2);
        samdal.solution(3);
        samdal.solution(4);
        samdal.solution(5);
    }
}