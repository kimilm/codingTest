package this_is_coding_test.ch12;

import com.navercorp.fixturemonkey.FixtureMonkey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import this_is_coding_test.ch12.fixture.외벽_점검_fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class ImplementationQuestionsTest {

    private final ImplementationQuestions iq = new ImplementationQuestions();

    @TestFactory
    Stream<DynamicTest> 럭키_스트레이트_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    String result = iq.럭키_스트레이트(123402);
                    Assertions.assertThat(result).isEqualTo("LUCKY");
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    String result = iq.럭키_스트레이트(7755);
                    Assertions.assertThat(result).isEqualTo("READY");
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 문자열_재정렬_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    String result = iq.문자열_재정렬("K1KA5CB7");
                    Assertions.assertThat(result).isEqualTo("ABCKK13");
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    String result = iq.문자열_재정렬("AJKDLSI412K4JSJ9D");
                    Assertions.assertThat(result).isEqualTo("ADDIJJJKKLSS20");
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 문자열_압축_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
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
                })
        );
    }

    @Test
    void 자물쇠와_열쇠_test() {
        boolean result = iq.자물쇠와_열쇠(
                new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}
        );
        boolean answer = true;
        Assertions.assertThat(result).isEqualTo(answer);
    }

    @TestFactory
    Stream<DynamicTest> 뱀_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = iq.뱀(6,
                            3, new String[]{"3 4", "2 5", "5 3"},
                            3, new String[]{"3 D", "15 L", "17 D"}
                    );
                    Assertions.assertThat(result).isEqualTo(9);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = iq.뱀(10,
                            4, new String[]{"1 2", "1 3", "1 4", "1 5"},
                            4, new String[]{"8 D", "10 D", "11 D", "13 L"}
                    );
                    Assertions.assertThat(result).isEqualTo(21);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = iq.뱀(10,
                            5, new String[]{"1 5", "1 3", "1 2", "1 6", "1 7"},
                            4, new String[]{"8 D", "10 D", "11 D", "13 L"}
                    );
                    Assertions.assertThat(result).isEqualTo(13);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 기둥과_보_설치() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int[][] result = iq.기둥과_보_설치_2(5,
                            new int[][]{
                                    {1, 0, 0, 1},
                                    {1, 1, 1, 1},
                                    {2, 1, 0, 1},
                                    {2, 2, 1, 1},
                                    {5, 0, 0, 1},
                                    {5, 1, 0, 1},
                                    {4, 2, 1, 1},
                                    {3, 2, 1, 1}
                            }
                    );
                    int[][] answer = new int[][]{
                            {1, 0, 0},
                            {1, 1, 1},
                            {2, 1, 0},
                            {2, 2, 1},
                            {3, 2, 1},
                            {4, 2, 1},
                            {5, 0, 0},
                            {5, 1, 0}
                    };
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int[][] result = iq.기둥과_보_설치_2(5,
                            new int[][]{
                                    {0, 0, 0, 1},
                                    {2, 0, 0, 1},
                                    {4, 0, 0, 1},
                                    {0, 1, 1, 1},
                                    {1, 1, 1, 1},
                                    {2, 1, 1, 1},
                                    {3, 1, 1, 1},
                                    {2, 0, 0, 0},
                                    {1, 1, 1, 0},
                                    {2, 2, 0, 1}
                            }
                    );
                    int[][] answer = new int[][]{
                            {0, 0, 0},
                            {0, 1, 1},
                            {1, 1, 1},
                            {2, 1, 1},
                            {3, 1, 1},
                            {4, 0, 0}
                    };
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int[][] result = iq.기둥과_보_설치_2(5,
                            new int[][]{
                                    {5, 0, 0, 1},
                                    {4, 1, 1, 1},
                                    {4, 1, 0, 1},
                                    {4, 1, 0, 0},
                                    {5, 0, 0, 0},
                                    {4, 1, 0, 1}
                            }
                    );
                    int[][] answer = new int[][]{
                            {4, 1, 0},
                            {4, 1, 1},
                            {5, 0, 0}
                    };
                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 치킨_배달() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = iq.치킨_배달_2(5, 3,
                            new String[]{
                                    "0 0 1 0 0",
                                    "0 0 2 0 1",
                                    "0 1 2 0 0",
                                    "0 0 1 0 0",
                                    "0 0 0 0 2"
                            }
                    );
                    int answer = 5;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = iq.치킨_배달_2(5, 2,
                            new String[]{
                                    "0 2 0 1 0",
                                    "1 0 1 0 0",
                                    "0 0 0 0 0",
                                    "2 0 0 1 1",
                                    "2 2 0 1 2"
                            }
                    );
                    int answer = 10;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = iq.치킨_배달_2(5, 1,
                            new String[]{
                                    "1 2 0 0 0",
                                    "1 2 0 0 0",
                                    "1 2 0 0 0",
                                    "1 2 0 0 0",
                                    "1 2 0 0 0"
                            }
                    );
                    int answer = 11;
                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> 외벽_점검_test() {
        return Stream.of(
                DynamicTest.dynamicTest("예제 입력 1", () -> {
                    int result = iq.외벽_점검_2(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4});
                    int answer = 2;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 2", () -> {
                    int result = iq.외벽_점검_2(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7});
                    int answer = 1;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 3", () -> {
                    int result = iq.외벽_점검_2(12, new int[]{1, 10}, new int[]{1});
                    int answer = -1;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 4", () -> {
                    int result = iq.외벽_점검_2(12, new int[]{1, 5, 10}, new int[]{8});
                    int answer = 1;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 5", () -> {
                    int result = iq.외벽_점검_2(200, new int[]{0, 100}, new int[]{1, 1});
                    int answer = 2;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 6", () -> {
                    int result = iq.외벽_점검_2(12, new int[]{10, 0}, new int[]{1, 2});
                    int answer = 1;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 7", () -> {
                    int result = iq.외벽_점검_2(30, new int[]{0, 3, 11, 21}, new int[]{10, 4});
                    int answer = 2;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 8", () -> {
                    int result = iq.외벽_점검_2(200, new int[]{0, 10, 50, 80, 120, 160}, new int[]{1, 10, 5, 40, 30});
                    int answer = 3;
                    Assertions.assertThat(result).isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("예제 입력 9", () -> {
                    int result = iq.외벽_점검_2(12, new int[]{1, 3, 4, 9, 10}, new int[]{1, 3});
                    int answer = 2;
                    Assertions.assertThat(result).isEqualTo(answer);
                })
        );
    }

    @Test
    void 외벽_점검_test_with_fixtureMonkey() {
        FixtureMonkey monkey = FixtureMonkey.create();
        List<외벽_점검_fixture> wallFixtures = new ArrayList<>();

        while (wallFixtures.size() != 10) {
            외벽_점검_fixture fixture = monkey.giveMeOne(외벽_점검_fixture.class);
            if (fixture.getN() > Collections.max(fixture.getWeak())) {
                wallFixtures.add(fixture);
            }
        }

        for (외벽_점검_fixture wallFixture : wallFixtures) {
            int result = iq.외벽_점검(wallFixture.getN(), wallFixture.getWeakToArray(), wallFixture.getDistToArray());
            System.out.println(wallFixture + ", result=" + result + "\n");
        }
    }
}