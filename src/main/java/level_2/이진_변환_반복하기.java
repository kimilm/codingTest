package level_2;

// https://school.programmers.co.kr/learn/courses/30/lessons/70129
public class 이진_변환_반복하기 {
    public int[] solution(String s) {
        int count = 0;
        int remove = 0;

        while (s.length() != 1) {
            String reduce = s.chars()
                    .filter(c -> c != '0')
                    .collect(
                            StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append
                    )
                    .toString();
            remove += (s.length() - reduce.length());
            s = Integer.toBinaryString(reduce.length());
            ++count;
        }

        return new int[]{count, remove};
    }
}
