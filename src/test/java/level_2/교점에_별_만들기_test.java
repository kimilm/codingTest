package level_2;

import com.navercorp.fixturemonkey.FixtureMonkey;
import level_2.fixture.교점에_별_만들기_fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Disabled
class 교점에_별_만들기_test {
    교점에_별_만들기 star = new 교점에_별_만들기();

    private FixtureMonkey monkey;
    private List<교점에_별_만들기_fixture> lineFixtures;

    @BeforeEach
    void setUp() {
        Random random = new Random(System.currentTimeMillis());
        monkey = FixtureMonkey.create();
        lineFixtures = monkey.giveMe(교점에_별_만들기_fixture .class, random.nextInt(1000) + 2);
    }

    @DisplayName("별 찍기")
    @RepeatedTest(100)
    void test() {
        System.out.println("line Size: " + lineFixtures.size());
        List<교점에_별_만들기_fixture > testInput = lineFixtures.stream()
                .filter(line -> (line.getA() != 0) || (line.getB() != 0))
                .collect(Collectors.toList());

        if (testInput.size() < 2) {
            System.out.println("input size error");
            return;
        }

        int [][] lines = new int [testInput.size()][3];
        IntStream.range(0, testInput.size()).forEach(index -> {
            lines[index] = testInput.get(index).toArray();
        });

        String [] result = star.solution(lines);
        Arrays.stream(result).forEach(System.out::println);
    }
}