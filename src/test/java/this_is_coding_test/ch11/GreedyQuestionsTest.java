package this_is_coding_test.ch11;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GreedyQuestionsTest {

    private final GreedyQuestions gq = new GreedyQuestions();

    @Test
    void 모험가_길드_test() {
        int result = gq.모험가_길드(5, new int[]{2, 3, 1, 2, 2});
        int answer = 2;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 모험가_길드_2_test() {
        int result = gq.모험가_길드_2(5, new int[]{2, 3, 1, 2, 2});
        int answer = 2;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 곱하기_혹은_더하기_test() {
        int result = gq.곱하기_혹은_더하기("02984");
        int answer = 576;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @TestFactory
    Stream<DynamicTest> 문자열_뒤집기_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = gq.문자열_뒤집기("0001100");
                    Assertions.assertThat(result).isEqualTo(1);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = gq.문자열_뒤집기("11111");
                    Assertions.assertThat(result).isEqualTo(0);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = gq.문자열_뒤집기("00000001");
                    Assertions.assertThat(result).isEqualTo(1);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int result = gq.문자열_뒤집기("11001100110011000001");
                    Assertions.assertThat(result).isEqualTo(4);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int result = gq.문자열_뒤집기("11101101");
                    Assertions.assertThat(result).isEqualTo(2);
                }));
    }

    @TestFactory
    Stream<DynamicTest> 만들_수_없는_금액_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = gq.만들_수_없는_금액(5, new int[]{3, 2, 1, 1, 9});
                    Assertions.assertThat(result).isEqualTo(8);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = gq.만들_수_없는_금액(3, new int[]{3, 5, 7});
                    Assertions.assertThat(result).isEqualTo(1);
                }));
    }

    @TestFactory
    Stream<DynamicTest> 만들_수_없는_금액_2_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = gq.만들_수_없는_금액_2(5, new int[]{3, 2, 1, 1, 9});
                    Assertions.assertThat(result).isEqualTo(8);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = gq.만들_수_없는_금액_2(3, new int[]{3, 5, 7});
                    Assertions.assertThat(result).isEqualTo(1);
                }));
    }

    @TestFactory
    Stream<DynamicTest> 볼링공_고르기_test() {
        return Stream.of(DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = gq.볼링공_고르기(5, 3, new int[]{1, 3, 2, 3, 2});
                    Assertions.assertThat(result).isEqualTo(8);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = gq.볼링공_고르기(8, 5, new int[]{1, 5, 4, 3, 2, 4, 5, 2});
                    Assertions.assertThat(result).isEqualTo(25);
                }));
    }

    @TestFactory
    Stream<DynamicTest> 볼링공_고르기_2_test() {
        return Stream.of(DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = gq.볼링공_고르기_2(5, 3, new int[]{1, 3, 2, 3, 2});
                    Assertions.assertThat(result).isEqualTo(8);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = gq.볼링공_고르기_2(8, 5, new int[]{1, 5, 4, 3, 2, 4, 5, 2});
                    Assertions.assertThat(result).isEqualTo(25);
                }));
    }

    @Test
    void 무지의_먹방_라이브_test() {
        int result = gq.무지의_먹방_라이브(new int[] {3, 1, 2}, 5);
        int answer = 1;
        Assertions.assertThat(result).isEqualTo(answer);
    }

    @Test
    void 무지의_먹방_라이브_2_test() {
        int result = gq.무지의_먹방_라이브_2(new int[] {3, 1, 2}, 5);
        int answer = 1;
        Assertions.assertThat(result).isEqualTo(answer);
    }
}