package this_is_coding_test.ch13;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

class DFS_BFS_QuestionsTest {

    private final DFS_BFS_Questions dbq = new DFS_BFS_Questions();

    @TestFactory
    Stream<DynamicTest> 특정_거리의_도시_찾기() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기_2(4, 4, 2, 1, new String[]{"1 2", "1 3", "2 3", "2 4"});
                    int[] answer = new int[]{4};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기_2(4, 3, 2, 1, new String[]{"1 2", "1 3", "1 4"});
                    int[] answer = new int[]{-1};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기_2(4, 4, 1, 1, new String[]{"1 2", "1 3", "2 3", "2 4"});
                    int[] answer = new int[]{2, 3};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기_2(4, 5, 3, 1, new String[]{"1 2", "1 3", "2 3", "2 4", "4 1"});
                    int[] answer = new int[]{-1};
                    Assertions.assertThat(result).containsExactly(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int[] result = dbq.특정_거리의_도시_찾기_2(7, 6, 2, 1, new String[]{"1 2", "1 3", "2 4", "2 5", "3 6", "3 7"});
                    int[] answer = new int[]{4, 5, 6, 7};
                    Assertions.assertThat(result).containsExactly(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 연구소() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = dbq.연구소_2(7, 7,
                            new String[]{
                                    "2 0 0 0 1 1 0",
                                    "0 0 1 0 1 2 0",
                                    "0 1 1 0 1 0 0",
                                    "0 1 0 0 0 0 0",
                                    "0 0 0 0 0 1 1",
                                    "0 1 0 0 0 0 0",
                                    "0 1 0 0 0 0 0"
                            });
                    int answer = 27;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = dbq.연구소_2(4, 6,
                            new String[]{
                                    "0 0 0 0 0 0",
                                    "1 0 0 0 0 2",
                                    "1 1 1 0 0 2",
                                    "0 0 0 0 0 2",
                            });
                    int answer = 9;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = dbq.연구소_2(8, 8,
                            new String[]{
                                    "2 0 0 0 0 0 0 2",
                                    "2 0 0 0 0 0 0 2",
                                    "2 0 0 0 0 0 0 2",
                                    "2 0 0 0 0 0 0 2",
                                    "2 0 0 0 0 0 0 2",
                                    "0 0 0 0 0 0 0 0",
                                    "0 0 0 0 0 0 0 0",
                                    "0 0 0 0 0 0 0 0",
                            });
                    int answer = 3;
                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 경쟁적_전염() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = dbq.경쟁적_전염(3, 3, new String[]{
                            "1 0 2",
                            "0 0 0",
                            "3 0 0",
                            "2 3 2"
                    });
                    int answer = 3;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = dbq.경쟁적_전염(3, 3, new String[]{
                            "1 0 2",
                            "0 0 0",
                            "3 0 0",
                            "1 2 2"
                    });
                    int answer = 0;
                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @Test
    public void isRightTest() {
        boolean result1 = dbq.isRight("(())");
        boolean result2 = dbq.isRight("())(");
        Assertions.assertThat(result1).isEqualTo(true);
        Assertions.assertThat(result2).isEqualTo(false);
    }

    @Test
    public void reverseBracketTest() {
        String result = dbq.reverseBracket("(())");
        Assertions.assertThat(result).isEqualTo("))((");
    }


    @Test
    void 연산자_끼워_넣기() {
        int[] result = dbq.연산자_끼워_넣기_2(6, new int[]{1, 2, 3, 4, 5, 6}, new int[]{2, 1, 1, 1});
        int[] answer = new int[]{54, -24};

        Assertions.assertThat(result).containsExactly(answer);
    }

    @Test
    void 감시_피하기() {
        String result = dbq.감시_피하기(5, new String[]{
                "X S X X T",
                "T X S X X",
                "X X X X X",
                "X T X X x",
                "X X T X X"
        });
        String answer = "YES";

        Assertions.assertThat(result).isEqualTo(answer);
    }

    @TestFactory
    Stream<DynamicTest> 인구_이동() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = dbq.인구_이동(new String[]{
                            "2 20 50",
                            "50 30",
                            "20 40"
                    });
                    int answer = 1;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = dbq.인구_이동(new String[]{
                            "2 40 50",
                            "50 30",
                            "20 40"
                    });
                    int answer = 0;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = dbq.인구_이동(new String[]{
                            "2 20 50",
                            "50 30",
                            "30 40"
                    });
                    int answer = 1;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int result = dbq.인구_이동(new String[]{
                            "3 5 10",
                            "10 15 20",
                            "20 30 25",
                            "40 22 10"
                    });
                    int answer = 2;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int result = dbq.인구_이동(new String[]{
                            "4 10 50",
                            "10 100 20 90",
                            "80 100 60 70",
                            "70 20 30 40",
                            "50 20 100 10"
                    });
                    int answer = 3;

                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 블록_이동하기() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = dbq.블록_이동하기_3(new int[][]{
                            {0, 0, 0, 1, 1},
                            {0, 0, 0, 1, 0},
                            {0, 1, 0, 1, 1},
                            {1, 1, 0, 0, 1},
                            {0, 0, 0, 0, 0}
                    });
                    int answer = 7;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = dbq.블록_이동하기_3(new int[][]{
                            {0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 0, 0, 1},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 1, 1, 0},
                            {0, 1, 1, 1, 1, 1, 0},
                            {0, 0, 0, 0, 0, 1, 1},
                            {0, 0, 1, 0, 0, 0, 0}
                    });
                    int answer = 21;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = dbq.블록_이동하기_3(new int[][]{
                            {0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 0, 0, 1},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 1, 0, 0},
                            {0, 1, 1, 1, 1, 1, 0},
                            {0, 0, 0, 0, 0, 1, 0},
                            {0, 0, 1, 0, 0, 0, 0}
                    });
                    int answer = 11;

                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int result = dbq.블록_이동하기_3(new int[][]{
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {1, 1, 1, 1, 1, 1, 1, 0, 0},
                            {1, 1, 1, 1, 1, 1, 1, 1, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 1, 1, 1, 0, 0},
                            {0, 1, 1, 1, 1, 1, 1, 1, 1},
                            {0, 0, 1, 1, 1, 1, 1, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {1, 1, 1, 1, 1, 1, 1, 1, 0},
                    });
                    int answer = 33;

                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @Test
    void hashCodeTest() {
        DFS_BFS_Questions.Robot robot1 = new DFS_BFS_Questions.Robot(0, 0, 0, 0);
        DFS_BFS_Questions.Robot robot2 = new DFS_BFS_Questions.Robot(0, 1, 0, 2);

        DFS_BFS_Questions.Visit visit1 = new DFS_BFS_Questions.Visit(robot1, 5);
        DFS_BFS_Questions.Visit visit2 = new DFS_BFS_Questions.Visit(robot2, 5);

        Set<DFS_BFS_Questions.Visit> set = new HashSet<>();
        set.add(visit1);

        int hash = Objects.hash(1, 2, 3, 4);
        int hash2 = Objects.hash(4, 3, 2, 1);

        Assertions.assertThat(set).contains(visit2);
        Assertions.assertThat(hash).isNotEqualTo(hash2);
    }
}