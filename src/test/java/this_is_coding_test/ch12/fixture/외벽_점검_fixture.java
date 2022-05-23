package this_is_coding_test.ch12.fixture;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * 1 <= n <= 200
 * 1 <= weak.length <= 15
 * 0 <= weak <= n - 1
 * 1 <= dist.length <= 8
 * 1 <= dist <= 100
 */
@Data
public class 외벽_점검_fixture {
    //    @Min(12)
    @Min(12)
//    @Max(200)
    @Max(12)
    private int n;

    @NotNull
//    @Size(min = 1, max = 15)
    @Size(min = 4, max = 6)
//    private List<@Min(0) @Max(200) Integer> weak = new ArrayList<>();
    private Set<@Min(0) @Max(20) Integer> weak = new HashSet<>();

    @NotNull
//    @Size(min = 1, max = 8)
    @Size(min = 1, max = 4)
//    private List<@Min(1) @Max(100) Integer> dist = new ArrayList<>();
    private Set<@Min(1) @Max(10) Integer> dist = new HashSet<>();

    public int[] getWeakToArray() {
        return weak.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    public int[] getDistToArray() {
        return dist.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
}
