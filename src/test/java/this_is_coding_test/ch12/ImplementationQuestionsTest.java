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
    Stream<DynamicTest> 럭키_스트레이트() {
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
    Stream<DynamicTest> 문자열_재정렬() {
        return Stream.of(DynamicTest.dynamicTest("예제 입력 1", () -> {
                    String result = iq.문자열_재정렬("K1KA5CB7");
                    Assertions.assertThat(result).isEqualTo("ABCKK13");
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    String result = iq.문자열_재정렬("AJKDLSI412K4JSJ9D");
                    Assertions.assertThat(result).isEqualTo("ADDIJJJKKLSS20");
                }));
    }
}