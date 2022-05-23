package this_is_coding_test.ch13;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

class DFS_BFS_QuestionsTest {

    private final DFS_BFS_Questions dbq = new DFS_BFS_Questions();

    @TestFactory
    Stream<DynamicTest> 특정_거리의_도시_찾기() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기(4, 4, 2, 1, new String[]{"1 2", "1 3", "2 3", "2 4"});
                    int[] answer = new int[] {4};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기(4, 3, 2, 1, new String[]{"1 2", "1 3", "1 4"});
                    int[] answer = new int[] {-1};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기(4, 4, 1, 1, new String[]{"1 2", "1 3", "2 3", "2 4"});
                    int[] answer = new int[] {2, 3};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기(4, 5, 3, 1, new String[]{"1 2", "1 3", "2 3", "2 4", "4 1"});
                    int[] answer = new int[] {-1};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기(7, 6, 2, 1, new String[]{"1 2", "1 3", "2 4", "2 5", "3 6", "3 7"});
                    int[] answer = new int[] {4, 5, 6, 7};
                    Assertions.assertThat(result).containsExactly(answer);
                })
        );
    }
}