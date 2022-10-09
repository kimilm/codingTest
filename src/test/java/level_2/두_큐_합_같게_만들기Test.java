package level_2;

import com.navercorp.fixturemonkey.FixtureMonkey;
import level_2.fixture.두_큐_합_같게_만들기_fixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

class 두_큐_합_같게_만들기Test {
    private Random random = new Random(System.currentTimeMillis());

    private 두_큐_합_같게_만들기 queueSame = new 두_큐_합_같게_만들기();

    private 두_큐_합_같게_만들기_fixture queueFixtureOne;
    private 두_큐_합_같게_만들기_fixture queueFixtureTwo;

    private List<두_큐_합_같게_만들기_fixture> fixtureList;

    @BeforeEach
    void setUp() {
//        int size = random.nextInt(300_000) + 1;

        FixtureMonkey fixtureMonkey = FixtureMonkey.create();

//        ArbitraryBuilder<두_큐_합_같게_만들기_fixture> fixtureBuilder = fixtureMonkey.giveMeBuilder(두_큐_합_같게_만들기_fixture.class)
//                .size("queueOne", size);

//        queueFixtureOne = fixtureBuilder.sample();
//        queueFixtureTwo = fixtureBuilder.sample();
//        queueFixtureOne = fixtureMonkey.giveMeOne(두_큐_합_같게_만들기_fixture.class);
//        queueFixtureTwo = fixtureMonkey.giveMeOne(두_큐_합_같게_만들기_fixture.class);

//        fixtureList = fixtureMonkey.giveMe(두_큐_합_같게_만들기_fixture.class, 2);
    }

    @Test
    void solution() {
        int[] queue1 = { 1, 1 };
        int[] queue2 = { 1, 5 };

        int result = queueSame.solution2(queue1, queue2);

        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    void solution1() {
        int[] queue1 = { 1, 5 };
        int[] queue2 = { 1, 1 };

        int result = queueSame.solution2(queue1, queue2);

        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    void solution2() {
        int[] queue1 = {3, 2};
        int[] queue2 = {3};

        int result = queueSame.solution2(queue1, queue2);

        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    void solution3() {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};

        int result = queueSame.solution2(queue1, queue2);

        Assertions.assertThat(result).isEqualTo(7);
    }

    @Test
    void equalsTest() {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 2, 1, 2};

        Assertions.assertThat(queue1 == queue2).isEqualTo(false);
        Assertions.assertThat(queue1.hashCode() == queue2.hashCode()).isEqualTo(false);
    }

    @Test
    void equalsTest2() {
        String queue1 = "1212";
        String queue2 = "1212";

        Assertions.assertThat(queue1 == queue2).isEqualTo(true);
        Assertions.assertThat(queue1.hashCode() == queue2.hashCode()).isEqualTo(true);
    }

    @Test
    void equalsTest3() {
        StringBuilder queue1 = new StringBuilder("1212");
        StringBuilder queue2 = new StringBuilder("1212");

        Assertions.assertThat(queue1.equals(queue2)).isEqualTo(false);
        Assertions.assertThat(queue1 == queue2).isEqualTo(false);
        Assertions.assertThat(queue1.hashCode() == queue2.hashCode()).isEqualTo(false);
    }
}