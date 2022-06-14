package this_is_coding_test.ch14;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class SortingQuestionsTest {
    private final SortingQuestions sq = new SortingQuestions();

    @Test
    void 국영수() {
        List<String> result = sq.국영수(
                Arrays.asList(
                        "12",
                        "Junkyu 50 60 100",
                        "Sangkeun 80 60 50",
                        "Sunyoung 80 70 100",
                        "Soong 50 60 90",
                        "Haebin 50 60 100",
                        "Kangsoo 60 80 100",
                        "Donghyuk 80 60 100",
                        "Sei 70 70 70",
                        "Wonseob 70 70 90",
                        "Sanghyun 70 70 80",
                        "nsj 80 80 80",
                        "Taewhan 50 60 90"
                )
        );
        List<String> answer = Arrays.asList(
                "Donghyuk",
                "Sangkeun",
                "Sunyoung",
                "nsj",
                "Wonseob",
                "Sanghyun",
                "Sei",
                "Kangsoo",
                "Haebin",
                "Junkyu",
                "Soong",
                "Taewhan"
        );

        Assertions.assertThat(result).containsExactlyElementsOf(answer);
    }

    @Test
    void 안테나() {
        int result = sq.안테나2(new String[]{"4", "5 1 7 9"});
        int answer = 5;
        Assertions.assertThat(result).isEqualTo(answer);
    }

    @TestFactory
    Stream<DynamicTest> 실패율() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int[] result = sq.실패율(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
                    int[] answer = {3, 4, 2, 1, 5};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int[] result = sq.실패율(4, new int[]{4, 4, 4, 4, 4});
                    int[] answer = {4, 1, 2, 3};
                    Assertions.assertThat(result).containsExactly(answer);
                })
        );
    }

    @Test
    void 카드_정렬하기() {
        int result = sq.카드_정렬하기_2(new String[]{"3", "10", "20", "40"});
        int answer = 100;
        Assertions.assertThat(result).isEqualTo(answer);
    }
}