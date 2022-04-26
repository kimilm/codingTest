package this_is_coding_test.ch12;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ImplementationQuestionsTest {

    private final ImplementationQuestions iq = new ImplementationQuestions();

    @TestFactory
    Stream<DynamicTest> 럭키_스트레이트_test() {
        return Stream.of(DynamicTest.dynamicTest("예제 입력 1", () -> {
                    String result = iq.럭키_스트레이트(123402);
                    Assertions.assertThat(result).isEqualTo("LUCKY");
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    String result = iq.럭키_스트레이트(7755);
                    Assertions.assertThat(result).isEqualTo("READY");
                }));
    }

    @TestFactory
    Stream<DynamicTest> 문자열_재정렬_test() {
        return Stream.of(DynamicTest.dynamicTest("예제 입력 1", () -> {
                    String result = iq.문자열_재정렬("K1KA5CB7");
                    Assertions.assertThat(result).isEqualTo("ABCKK13");
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    String result = iq.문자열_재정렬("AJKDLSI412K4JSJ9D");
                    Assertions.assertThat(result).isEqualTo("ADDIJJJKKLSS20");
                }));
    }

    @TestFactory
    Stream<DynamicTest> 문자열_압축_test() {
        return Stream.of(DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = iq.문자열_압축_3("aabbaccc");
                    Assertions.assertThat(result).isEqualTo(7);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = iq.문자열_압축_3("ababcdcdababcdcd");
                    Assertions.assertThat(result).isEqualTo(9);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = iq.문자열_압축_3("abcabcdede");
                    Assertions.assertThat(result).isEqualTo(8);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int result = iq.문자열_압축_3("abcabcabcabcdededededede");
                    Assertions.assertThat(result).isEqualTo(14);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int result = iq.문자열_압축_3("xababcdcdababcdcd");
                    Assertions.assertThat(result).isEqualTo(17);
                }));
    }
}