package level_2.fixture;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class 두_큐_합_같게_만들기_fixture {

    @Size(min = 100, max = 100)
    private List<@Min(1) @Max(1_000_000_000) Integer> queue = new ArrayList<>();

    public int[] toArray() {
        return queue.stream().mapToInt(i -> i).toArray();
    }


}