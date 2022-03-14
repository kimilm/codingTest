package level_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

@Disabled
class 메뉴_리뉴얼_Test {
    private final 메뉴_리뉴얼 menu = new 메뉴_리뉴얼();

    @Test
    void solutionTest() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] result = menu.solution(orders, course);
        String[] answer = {"AC", "ACDE", "BCFG", "CDE"};
        Assertions.assertThat(result).containsExactly(answer);

        orders = new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        course = new int[] {2, 3, 5};

        result = menu.solution(orders, course);
        answer = new String[] {"ACD", "AD", "ADE", "CD", "XYZ"};
        Assertions.assertThat(result).containsExactly(answer);

        orders = new String[] {"XYZ", "XWY", "WXA"};
        course = new int[] {2, 3, 4};

        result = menu.solution(orders, course);
        answer = new String[] {"WX", "XY"};
        Assertions.assertThat(result).containsExactly(answer);
    }

    @Test
    void combinationTest() {
        String str = "abcde";
        int n = str.length();

        List<String> combination = menu.combination(str.toCharArray(), new boolean[n], 0, n, 3);
        combination.size();
    }
}