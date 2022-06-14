package this_is_coding_test.ch15;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearchQuestionsTest {

    private final SearchQuestions sq = new SearchQuestions();

    @TestFactory
    Stream<DynamicTest> 정렬된_배열에서_특정_수의_개수_구하기() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기(new String[]{"7 2", "1 1 2 2 2 2 3"});
                    int answer = 4;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기(new String[]{"7 4", "1 1 2 2 2 2 3"});
                    int answer = -1;

                    Assertions.assertThat(result).isEqualTo(answer);
                })
//                ,
//                DynamicTest.dynamicTest("예제 입력 3", () -> {
//                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기(new String[]{"7 1", "1 1 1 1 1 1 1"});
//                    int answer = 7;
//
//                    Assertions.assertThat(result).isEqualTo(answer);
//                }),
//                DynamicTest.dynamicTest("예제 입력 4", () -> {
//                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기(new String[]{"7 1", "1 1 2 2 2 2 3"});
//                    int answer = 2;
//
//                    Assertions.assertThat(result).isEqualTo(answer);
//                }),
//                DynamicTest.dynamicTest("예제 입력 5", () -> {
//                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기(new String[]{"7 3", "1 1 2 2 2 2 3"});
//                    int answer = 1;
//
//                    Assertions.assertThat(result).isEqualTo(answer);
//                })
        );
    }
}