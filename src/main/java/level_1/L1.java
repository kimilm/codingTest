package level_1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L1 {
    // 01 27
    public String solution012701(String[] participant, String[] completion) {
        String answer = null;
        Map<String, Integer> map = new HashMap<>();

        for (String str : participant) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 0);
            }
        }

        for (String str : completion) {
            Integer value = map.get(str);

            if (value != 0) {
                map.put(str, --value);
            } else {
                map.remove(str);
            }
        }

        answer = map.keySet().iterator().next();

        return answer;
    }

    // 01 28
    public String solution012801(String s) {
        char[] c = s.toCharArray();

        Arrays.sort(c);

        String answer = new String(c);

        return new StringBuffer(answer).reverse().toString();
    }

    public int[] solution012802(int[] numbers) {
        Set<Integer> sum = new HashSet<Integer>();

        for (int i = 0; i < numbers.length; ++i) {
            for (int j = i + 1; j < numbers.length; ++j)
                sum.add(numbers[i] + numbers[j]);
        }

        return null;
        // return sum.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    public int[] solution012803(int[] numbers) {
        Set<Integer> sum = new TreeSet<Integer>(); // treeset 은 add 하면서 정렬됨

        for (int i = 0; i < numbers.length; ++i) {
            for (int j = i + 1; j < numbers.length; ++j)
                sum.add(numbers[i] + numbers[j]);
        }

        return sum.stream().mapToInt(Integer::intValue).toArray();
    }

    // 01 29
    public String solution012901(String new_id) {
        String answer = new_id.toLowerCase();
        answer.replaceAll("", "");

        return answer;
    }

    public String solution012902(int a, int b) {
        int[] month = new int[13];

        month[0] = 0;
        month[1] = 31;
        month[3] = 31;
        month[5] = 31;
        month[7] = 31;
        month[8] = 31;
        month[10] = 31;
        month[12] = 31;
        month[4] = 30;
        month[6] = 30;
        month[9] = 30;
        month[11] = 30;
        month[2] = 29;

        String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int count = 5;

        List<Map<Integer, String>> year = new ArrayList<>();

        for (int i = 0; i < 13; ++i) {
            year.add(new HashMap<>());
            for (int j = 0; j < month[i]; ++j) {
                year.get(i).put(j + 1, days[count++]);
                count = count % 7;
            }
        }

        String answer = year.get(a).get(b);
        return answer;
    }

    public String solution012903(String s) {
        String answer = "";
        boolean even = false;
        int half = s.length() / 2;

        if (s.length() % 2 == 0)
            even = true;

        if (even) {
            answer = s.substring(half - 1, half + 1);
        } else {
            answer = s.substring(half, half + 1);
        }

        return answer;
    }

    public int[] solution012904(int[] arr) {
        int temp = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) {
            if (temp != arr[i]) {
                list.add(arr[i]);
                temp = arr[i];
            }
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 02 01
    public int[] solution020101(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] % divisor == 0) {
                list.add(arr[i]);
            }
        }

        Collections.sort(list);

        if (list.isEmpty()) {
            list.add(-1);
        }

        int[] answer = new int[list.size()];
//				Integer[] answer = (Integer[])list.toArray();

        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public long solution020102(int a, int b) {
        long answer = 0;

        int low, high;
        if (a < b) {
            low = a;
            high = b + 1;
        } else {
            low = b;
            high = a + 1;
        }

        for (int i = low; i < high; ++i) {
            answer += i;
        }

        return answer;
    }

    public String[] solution020103(String[] strings, int n) {
        String[] answer = strings;

        Arrays.sort(answer, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(n) != o2.charAt(n))
                    return o1.charAt(n) - o2.charAt(n);
                else
                    return o1.compareTo(o2);
            }

        });

        return answer;
    }

    boolean solution020104(String s) {
        String str = s.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();
        map.put('p', 0);
        map.put('y', 0);

        char token = ' ';

        for (int i = 0; i < s.length(); ++i) {
            token = str.charAt(i);
            switch (token) {
                case 'p':
                case 'y':
                    map.put(token, map.get(token) + 1);
                    break;
            }
        }

        return map.get('p') == map.get('y');
    }

    public boolean solution020105(String s) {

        if (s.length() == 4 || s.length() == 6) {
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) > 57 || s.charAt(i) < 48)
                    return false;
            }
            return true;
        }
        return false;
    }

    // 02 02
    public String solution020201(String[] seoul) { // 김서방 찾기
        int index = 0;

        for (int i = 0; i < seoul.length; ++i) {
            if (seoul[i].equals("Kim")) {
                index = i;
                break;
            }
        }

        return "김서방은 " + index + "에 있다";
    }

    public int solution020202(int n) { // 소수 찾기
        boolean[] prime = new boolean[n + 1];

        for (int i = 2; i < n + 1; ++i)
            if (!prime[i])
                for (int j = 2; (i * j) < n + 1; ++j)
                    if (!prime[i * j])
                        prime[i * j] = true;

        int answer = 0;

        for (int i = 2; i < n + 1; ++i)
            if (!prime[i])
                ++answer;

        return answer;
    }

    public String solution020203(int n) { // 수박
        String[] watermellon = {"수", "박"};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            sb.append(watermellon[i % 2]);
        }

        return sb.toString();
    }

    public int solution020204(String s) { // 문자열 정수로 바꾸기
        return Integer.parseInt(s);
    }

    public String solution020205(String s, int n) { // 시저 암호
        char[] str = s.toCharArray();

        for (int i = 0; i < str.length; ++i) {
            if (str[i] == 32)
                continue;
            else if (str[i] < 91)
                str[i] = (char) (((str[i] - (65 - n)) % 26) + 65); // str[i] - 'a' 와 같은 코드도 가능
            else
                str[i] = (char) (((str[i] - (97 - n)) % 26) + 97);
        }

        return new String(str);
    }

    public int solution020206(int n) { // 약수의 합
        int answer = 0;

        for (int i = 1; i < n + 1; ++i) { // 자신 제외 가장 큰 약수는 (자신 / 2) (존재할경우)
            if (n % i == 0) { // for문을 i < n / 2 + 1 까지 돌린 후 (i <= n/2)
                answer += i; // 마지막에 자기 자신을 더해주면 절반 이상 시간을 아낄 수 있음
            }
        }

        return answer;
    }

    public int solution020207(int n) { // 자릿수 더하기
        String number = Integer.toString(n);
        int answer = 0;

        for (int i = 0; i < number.length(); ++i) { // 리소스만 많이 먹는 코드
            answer += (number.charAt(i) - '0'); // answer += n % 10;
        } // n /= 10; 이게 낫다

        return answer;
    }

    // 02 03
    public String solution020301(String s) { // 이상한 문자 만들기
        String[] upper = s.trim().toUpperCase().split("\\s+");
        String[] lower = s.trim().toLowerCase().split("\\s+");

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < upper.length; ++i) {
            for (int j = 0; j < upper[i].length(); ++j) {
                if (j % 2 == 0)
                    answer.append(upper[i].charAt(j));
                else
                    answer.append(lower[i].charAt(j));
            }
            answer.append(' ');
        }

        return answer.toString().trim();
    }

    public String solution0203012(String s) {
        int index = 0;
        char token = ' ';

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            token = s.charAt(i);

            if (token == ' ') {
                if (index != 0)
                    sb.append(token);
                index = 0;
                continue;
            } else {
                if (Character.getType(token) != Character.OTHER_LETTER) {
                    if (index % 2 == 0) {
                        sb.append(Character.toUpperCase(token));
                    } else {
                        sb.append(Character.toLowerCase(token));
                    }
                } else {
                    sb.append(token);
                }

                ++index;
            }

        }

        return sb.toString().trim();
    }

    public int[] solution020302(long n) { // 자연수 뒤집어 배열로 만들기
        String s = new StringBuilder(Long.toString(n)).reverse().toString();

        int[] answer = new int[s.length()];

        for (int i = 0; i < s.length(); ++i) {
            answer[i] = s.charAt(i) - '0';
        }

        return answer;
    }

    public long solution020303(long n) { // 정수 내림차순으로 배치하기
        String[] s = Long.toString(n).split("");

        Arrays.sort(s, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }

        });

        StringBuilder sb = new StringBuilder();

        for (String token : s) {
            sb.append(token);
        }

        return Long.parseLong(sb.toString());
    }

    // 02 04
    public long solution020401(long n) { // 정수 제곱근 판별
        long sqrt = (long) Math.sqrt(n);

        if (Math.pow(sqrt, 2) == n)
            return (long) Math.pow(sqrt + 1, 2);
        else
            return -1;
    }

    public String solution020402(int num) { // 짝수와 홀수
        return num % 2 == 0 ? "Even" : "Odd";
    }

    public int[] solution020403(int[] arr) { // 제일 작은 수 제거하기
        int[] sorted = arr.clone();
        int[] answer = new int[arr.length - 1];

        Arrays.sort(sorted);

        int index = 0;

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != sorted[0])
                answer[index++] = arr[i];
        }

        if (answer.length == 0) {
            answer = new int[1];
            answer[0] = -1;
        }

        return answer;
    }

    public int[] solution020404(int n, int m) { // 최대공약수와 최소공배수
        int high = n > m ? n : m;
        int low = n < m ? n : m;

        int divisor = 1;
        int multiple = 0;

        for (int i = 2; i <= low; ) {
            if ((low % i == 0) && (high % i == 0)) {
                divisor *= i;
                low /= i;
                high /= i;
            } else {
                ++i;
            }
        }

        multiple = divisor * low * high;

        int[] answer = {divisor, multiple};
        return answer;
    }

    // 02 05
    public int solution020501(int num) { // 콜라즈 추측
        long longNum = num;
        for (int i = 0; i < 500; ++i) {
            if (longNum == 1) {
                return i;
            } else if (longNum % 2 != 0) {
                longNum = longNum * 3 + 1;
            } else {
                longNum /= 2;
            }
        }
        return -1;
    }

    public double solution020502(int[] arr) { // 평균 구하기
        long temp = 0;

        for (int i = 0; i < arr.length; ++i) {
            temp += arr[i];
        }
        double answer = temp / (double) arr.length;

        return answer;
    }

    public boolean solution020503(int x) { // 하샤드 수
        String[] number = Integer.toString(x).split("");
        int sum = 0;

        for (int i = 0; i < number.length; ++i) {
            sum += Integer.parseInt(number[i]);
        }

        return x % sum == 0;
    }

    public String solution020504(String phone_number) { // 핸드폰 번호 가리기
        StringBuilder answer = new StringBuilder(phone_number);
        StringBuilder stars = new StringBuilder();

        for (int i = 0; i < phone_number.length() - 4; ++i) {
            stars.append('*');
        }

        answer.replace(0, phone_number.length() - 4, stars.toString());
        return answer.toString();
    }

    public int[][] solution020505(int[][] arr1, int[][] arr2) { // 행렬의 덧셈
        int n = arr1.length;
        int m = arr1[0].length;
        int[][] answer = new int[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        return answer;
    }

    public long[] solution020506(int x, int n) { // x 만큼 간격이 있는 n개의 숫자
        long[] answer = new long[n];
        long append = 0;

        for (int i = 0; i < n; ++i) {
            append += x;
            answer[i] = append;
        }

        return answer;
    }

    public String solution020507(int a, int b) { // 직사각형 별 찍기
        StringBuilder answer = new StringBuilder();
        StringBuilder row = new StringBuilder();

        for (int j = 0; j < a; ++j) {
            row.append('*');
        }
        row.append('\n');

        for (int i = 0; i < b; ++i) {
            answer.append(row);
        }

        return answer.toString();
    }

    public int[] solution020508(int[] answers) { // 모의고사
        Person[] persons = new Person[3];
        persons[0] = new Person(new int[]{1, 2, 3, 4, 5});
        persons[1] = new Person(new int[]{2, 1, 2, 3, 2, 4, 2, 5});
        persons[2] = new Person(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        for (int answer : answers) {
            for (int i = 0; i < 3; ++i) {
                persons[i].check(answer);
            }
        }

        int maxScore = max(persons[0].getScore(), persons[1].getScore(), persons[2].getScore());

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            if (persons[i].getScore() == maxScore)
                list.add(i + 1);
        }

        int[] high = new int[list.size()];

        for (int i = 0; i < list.size(); ++i)
            high[i] = list.get(i);

        return high;
    }

    public int max(int a, int b, int c) {
        int temp = a;
        temp = temp > b ? temp : b;
        temp = temp > c ? temp : c;
        return temp;
    }

    class Person {
        private int[] howTo;
        private int length;
        private int index;
        private int score;

        public Person(int[] array) {
            howTo = array;
            length = array.length;
            index = 0;
            score = 0;
        }

        public int getScore() {
            return score;
        }

        public void check(int answer) {
            if (howTo[index] == answer) {
                ++score;
            }
            ++index;
            index %= length;
        }
    }

    public int[] 실패율(int N, int[] stages) {
        int[] answer = new int [N];
        double [] reach = new double [N];
        double [] nonClear = new double [N];
        Map <Integer, Double> map = new LinkedHashMap<>();

        for (int stage : stages) {
            if (stage > N) {
                --stage;
                for (int i = 0; i < stage; ++i) {
                    ++reach[i];
                }
            }
            else {
                for (int i = 0; i < stage; ++i) {
                    ++reach[i];
                }
                ++nonClear[stage - 1];
            }
        }

        for (int i = 0; i < N; ++i) {
            if (reach[i] != 0) {
                map.put(i + 1, nonClear[i] / reach[i]);
            }
            else {
                map.put(i + 1, 0.0);
            }
        }

        List<Map.Entry<Integer, Double>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Double>> () {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                int cmp = o2.getValue().compareTo(o1.getValue());
                if (cmp == 0)
                    return o1.getKey().compareTo(o2.getKey());
                else
                    return cmp;
            }
        });

        for (int i = 0; i < N; ++i) {
            answer[i] = entries.get(i).getKey();
        }

        return answer;
    }

    // https://school.programmers.co.kr/learn/courses/30/lessons/92334
    public int[] 신고_결과_받기(String[] id_list, String[] report, int k) {
        // 유저 배열 사이즈
        int n = id_list.length;
        // 유저의 인덱스 저장
        Map<String, Integer> userIndex = IntStream.range(0, n)
                .boxed()
                .collect(Collectors.toMap(i -> id_list[i], Function.identity()));
        // 유저가 받을 메일 저장
        int[] answer = new int[id_list.length];
        // 유저가 어떤 유저에게 신고당했는지 저장
        Map<String, Set<String>> reportMap = new HashMap<>();
        for (String id : id_list) {
            reportMap.put(id, new HashSet<>());
        }

        for (String rep : report) {
            // 신고 내역 분리, 0이 1을 신고함
            String[] users = rep.split(" ");
            reportMap.get(users[1]).add(users[0]);
        }

        // 2. 신고한 유저에게 메일 발송
        for (Map.Entry<String, Set<String>> entry : reportMap.entrySet()) {
            Set<String> reporterSet = entry.getValue();
            // k번 이상 신고당했다면
            if (reporterSet.size() >= k) {
                for (String reported : reporterSet) {
                    // 메일 발송
                    ++answer[userIndex.get(reported)];
                }
            }
        }
        return answer;
    }

    // https://school.programmers.co.kr/learn/courses/30/lessons/76501
    public int 음양_더하기(int[] absolutes, boolean[] signs) {
        int n = absolutes.length;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (signs[i]) {
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }

        return answer;
    }

    // https://school.programmers.co.kr/learn/courses/30/lessons/70128
    public int 내적(int[] a, int[] b) {
        int answer = 0;

        for (int i = 0; i <a.length; i++) {
            answer += a[i] * b[i];
        }

        return answer;
    }
}
