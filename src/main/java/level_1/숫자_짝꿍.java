package level_1;

// https://school.programmers.co.kr/learn/courses/30/lessons/131128?language=java
public class 숫자_짝꿍 {
    public String solution(String X, String Y) {
        int[] array = new int[10];
        StringBuilder sb = new StringBuilder();
        X.codePoints().forEach(cp -> ++array[cp - 48]);
        Y.codePoints().sorted().forEach(cp -> {
            int idx = cp - 48;
            if (array[idx] > 0) {
                --array[idx];
                sb.append(idx);
            }
        });

        sb.reverse();

        if (sb.length() == 0) {
            return "-1";
        }

        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}
