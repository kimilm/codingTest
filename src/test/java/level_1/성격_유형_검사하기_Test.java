package level_1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class 성격_유형_검사하기_Test {

    private 성격_유형_검사하기 test = new 성격_유형_검사하기();

    @Test
    public void test() {
        String[] survey = new String[]{"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String answer = "TCMA";

        String result = test.solution(survey, choices);

        Assertions.assertThat(result).isEqualTo(answer);
    }

}