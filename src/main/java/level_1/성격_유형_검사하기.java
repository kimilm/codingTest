package level_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/118666
public class 성격_유형_검사하기 {
    public static List<Map<Integer, String>> TYPE_MAPS;
    public static Map<String, Integer> COLS;
    public static Map<String, Integer> ROWS;

    static {
        TYPE_MAPS = new ArrayList<>();
        TYPE_MAPS.add(Map.of(0, "R", 1, "T"));
        TYPE_MAPS.add(Map.of(0, "C", 1, "F"));
        TYPE_MAPS.add(Map.of(0, "J", 1, "M"));
        TYPE_MAPS.add(Map.of(0, "A", 1, "N"));

        COLS = new HashMap<>();
        COLS.put("R", 0);
        COLS.put("T", 1);
        COLS.put("C", 0);
        COLS.put("F", 1);
        COLS.put("J", 0);
        COLS.put("M", 1);
        COLS.put("A", 0);
        COLS.put("N", 1);

        ROWS = new HashMap<>();
        ROWS.put("R", 0);
        ROWS.put("T", 0);
        ROWS.put("RT", 0);
        ROWS.put("TR", 0);
        ROWS.put("C", 1);
        ROWS.put("F", 1);
        ROWS.put("CF", 1);
        ROWS.put("FC", 1);
        ROWS.put("J", 2);
        ROWS.put("M", 2);
        ROWS.put("JM", 2);
        ROWS.put("MJ", 2);
        ROWS.put("A", 3);
        ROWS.put("N", 3);
        ROWS.put("AN", 3);
        ROWS.put("NA", 3);
    }

    public String solution(String[] survey, int[] choices) {
        int[][] types = new int[4][2];
        int n = choices.length;

        for (int i = 0; i < n; i++) {
            int choice = choices[i] - 4;

            if (choice != 0) {
                String[] personal = survey[i].split("");

                String type = choice < 0
                        ? personal[0]
                        : personal[1];

                Integer integer = ROWS.get(survey[i]);
                Integer integer1 = COLS.get(type);
                types[integer][integer1] += Math.abs(choice);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            if (types[i][0] < types[i][1]) {
                sb.append(TYPE_MAPS.get(i).get(1));
            } else {
                sb.append(TYPE_MAPS.get(i).get(0));
            }
        }

        return sb.toString();
    }
}