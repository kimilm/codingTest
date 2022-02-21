package level_2.fixture;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class 교점에_별_만들기_fixture {
    @Min(-100_000)
    @Max(100_000)
    private int a;

    @Min(-100_000)
    @Max(100_000)
    private int b;

    @Min(-100_000)
    @Max(100_000)
    private int c;

    public int[] toArray() {
        return new int[]{a, b, c};
    }
}