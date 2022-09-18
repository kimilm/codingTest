package level_2;

public class JadenCase_문자열_만들기 {
    public String solution(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        boolean flag = true;

        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == ' ') {
                flag = true;
                continue;
            }

            if (flag && (chars[i] != ' ')) {
                chars[i] = Character.toUpperCase(chars[i]);
                flag = false;
            }
        }

        return String.valueOf(chars);
    }
}
