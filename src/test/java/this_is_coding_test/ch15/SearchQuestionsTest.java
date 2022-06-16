package this_is_coding_test.ch15;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

class SearchQuestionsTest {

    private final SearchQuestions sq = new SearchQuestions();

    @TestFactory
    Stream<DynamicTest> 정렬된_배열에서_특정_수의_개수_구하기() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기_2(new String[]{"7 2", "1 1 2 2 2 2 3"});
                    int answer = 4;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기_2(new String[]{"7 4", "1 1 2 2 2 2 3"});
                    int answer = -1;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기_2(new String[]{"7 1", "1 1 1 1 1 1 1"});
                    int answer = 7;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기_2(new String[]{"7 1", "1 1 2 2 2 2 3"});
                    int answer = 2;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int result = sq.정렬된_배열에서_특정_수의_개수_구하기_2(new String[]{"7 3", "1 1 2 2 2 2 3"});
                    int answer = 1;

                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 고정점() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = sq.고정점(new String[]{"5", "-15 -6 1 3 7"});
                    int answer = 3;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = sq.고정점(new String[]{"7", "-15 -4 2 8 9 13 15"});
                    int answer = 2;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = sq.고정점(new String[]{"7", "-15 -4 3 8 9 13 15"});
                    int answer = -1;

                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 공유기_설치() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = sq.공유기_설치_2(new String[]{"5 3", "1 2 8 4 9"});
                    int answer = 3;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = sq.공유기_설치_2(new String[]{"13 4", "0 1 2 3 4 5 6 7 8 9 10 11 12"});
                    int answer = 4;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = sq.공유기_설치_2(new String[]{"12 4", "2 3 4 5 6 7 8 9 10 11 12 100"});
                    int answer = 5;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = sq.공유기_설치_2(new String[]{"11 4", "1 91 92 93 94 95 96 97 98 99 100"});
                    int answer = 4;

                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

//    @Test
//    void treeSetTest() {
//        Integer[] result = sq.treeSet(new Integer[]{2, 4, 1, 5, 3});
//        Integer[] answer = {1, 2, 3, 4, 5};
//
//        Assertions.assertThat(result).containsExactly(answer);
//    }
//
//    @Test
//    void compareToTest() {
//        String call = "fro";
//        String param = "fra";
//
//        Assertions.assertThat(param.compareTo(call)).isLessThan(0);
//    }

    @Test
    void 가사_검색() {
        int[] result = sq.가사_검색(
                new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                new String[]{"fro??", "????o", "fr???", "fro???", "pro?"}
        );
        int[] answer = {3, 2, 4, 1, 0};

        Assertions.assertThat(result).containsExactly(answer);
    }
}