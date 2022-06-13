package this_is_coding_test.ch14;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        int result = sq.안테나(new String[]{"4", "5 1 7 9"});
        int answer = 5;
        Assertions.assertThat(result).isEqualTo(answer);
    }
}