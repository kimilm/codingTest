package codingTest;

import java.util.*;

import codingTest.Solution.Land;

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();

//		System.out.println(Arrays.toString(solution.solution050802(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},{"PXPX0P", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
//		System.out.println(solution.solution0508032(8, 2, new String [] {"D 2","C","U 3","C","D 4","C","U 2"}));
//		System.out.println(solution.solution0508032(8, 0, new String [] {"C", "C", "D 1", "C", "D 1", "C", "C"}));
//		System.out.println(solution.solution020801(new int [] {1, 1, 9, 1, 1, 1}, 1));
		System.out.println(solution.solution070402("abcxyasdfasdfxyabc"));
		System.out.println(solution.solution070402("abcabcabcabc"	));
		System.out.println(solution.solution070402("abcxyqwertyxyabc"	));
		System.out.println(solution.solution070402("llttaattll"	));
		System.out.println(solution.solution070402("abcdef"	));
	}
}

class Solution {

	// 02 15
	public int solution021501(int[] scoville, int K) { // 더 맵게
		PriorityQueue<Integer> heap = new PriorityQueue<>();

		for (int i = 0; i < scoville.length; ++i) {
			heap.add(scoville[i]);
		}

		int answer = 0;

		int littleSpicy;
		int moreSpicy;

		while (heap.peek() < K) {
			if (heap.size() < 2)
				return -1;
			littleSpicy = heap.poll();
			moreSpicy = heap.poll();

			heap.add(littleSpicy + moreSpicy * 2);

			++answer;
		}

		return answer;
	}

	boolean solution021502(String s) { // 올바른 괄호
		Stack<Boolean> stack = new Stack<>();

		if (s.charAt(0) == ')')
			return false;

		for (int i = 0; i < s.length(); ++i) {
			switch (s.charAt(i)) {
			case '(':
				stack.push(false);
				break;
			case ')':
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
				break;
			}
		}

		return stack.isEmpty();
	}

	int solution021503(int n) { // 다음 큰 숫자
		int bits = Integer.bitCount(n);
		int answer = n + 1;

		while (Integer.bitCount(answer) != bits)
			++answer;

		return answer;
	}

	int solution040501(String numbers) { // 소수 찾기
		Set<Integer> numberSet = new HashSet<>();
		String[] tokens = numbers.split("");
		int len = tokens.length + 1;

		Arrays.sort(tokens, Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.length; ++i) {
			sb.append(tokens[i]);
		}
		int max = Integer.parseInt(sb.toString());

		boolean[] prime = new boolean[max + 1];
		prime[0] = true;
		prime[1] = true;

		for (int i = 2; i < prime.length; ++i) {
			if (!prime[i])
				for (int j = i * 2; j < prime.length; j += i)
					prime[j] = true;
		}

		for (int i = 1; i < len; ++i) {
			Permutation main = new Permutation(tokens, i);
			numberSet.addAll(main.perm(0));
		}

		int answer = 0;

		for (Integer number : numberSet) {
			if (!prime[number])
				++answer;
		}

		return answer;
	}

	// for solution040501 소수 찾기
	// https://redscreen.tistory.com/168
	class Permutation {

		private int n; // nPr의 n
		private int r; // nPr의 r
		private ArrayList<String> itemList;
		private String[] res;
		private List<Integer> result;

		// 초기화
		public Permutation(String[] intArr, int r) {
			this.r = r; // nPr의 r
			this.n = intArr.length; // nPr의 n
			this.res = new String[r]; // 결과값 배열
			this.result = new ArrayList<>();

			// 아이템이 들어갈 리스트
			itemList = new ArrayList<String>();
			for (String item : intArr)
				itemList.add(item);
		}

		public List<Integer> perm(int depth) {
			perm(itemList, 0);
			return result;
		}

		public void perm(ArrayList<String> itemList, int depth) {

			// depth가 0부터 시작했을 경우 depth == r에서 리턴
			if (depth == r) {
				StringBuilder sb = new StringBuilder();
				for (String token : res)
					sb.append(token);
				result.add(Integer.parseInt(sb.toString()));
				// System.out.println(Arrays.toString(res));
				return;
			}

			for (int i = 0; i < n - depth; i++) {
				res[depth] = itemList.remove(i); // 아이템 선택 + 리스트에서 제거
				perm(itemList, depth + 1); // 재귀호출
				itemList.add(i, res[depth]); // 제거된 아이템 복원
			}
		}
	}

	class Land implements Comparable<Land> {
		private int col;
		private int val;

		public Land(int col, int val) {
			this.col = col;
			this.val = val;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		@Override
		public int compareTo(Land o) {
			return o.getVal() - this.val;
		}
	}

	int solution040601(int[][] land) {	// 땅따먹기
		Land [] sorted = new Land[4];
		int [] sum = new int [4];
		sum = land[0];
		
		for (int i = 0; i < 4; ++i) {
			sorted[i] = new Land(i, sum[i]);
		}
		
		Arrays.sort(sorted);
		
		for(int row = 1; row < land.length; ++row) {
			for(int col = 0; col < 4; ++col) {
				if (sorted[0].getCol() != col)
					sum[col] = land[row][col] + sorted[0].getVal();
				else
					sum[col] = land[row][col] + sorted[1].getVal();
			}
			
			for (int i = 0; i < 4; ++i) {
				sorted[i] = new Land(i, sum[i]);
			}
			
			Arrays.sort(sorted);
		}
		
		Arrays.sort(sum);
		
		return sum[3];
	}

	boolean solution050401(String[] phone_book) {	// 전화번호 목록
		boolean answer = true;
        String prefix;
        
        Arrays.sort(phone_book);
        
        for(int i = 1; i < phone_book.length && answer; ++i) {
        	prefix = phone_book[i - 1];
        	
        	if (prefix.length() < phone_book[i].length()) {
        		if (phone_book[i].substring(0, prefix.length()).equals(prefix)) {	// String.startsWith()
        			answer = false;
        		} else {
        			++i;
        		}
        	}
        }
        
        return answer;
    }
	
	int solution051001(String[][] clothes) {	// 위장
        int count = clothes.length;
        Map<String, Integer> wears = new HashMap<>();
        
        String key;
        for(int i = 0; i < count; ++i) {
        	key = clothes[i][1];
        	
        	if (wears.containsKey(key))
        		wears.put(key, wears.get(key) + 1);
        	else
        		wears.put(key, 1);
        }
        
        int answer = 0;
        return answer;
	}
	
	public int solution070401(int[] prices, int[] discounts) {
		Integer[] WrapperPrices = Arrays.stream( prices ).boxed().toArray( Integer[]::new );
		Integer[] WrapperDiscounts = Arrays.stream( discounts ).boxed().toArray( Integer[]::new );
		
		Arrays.sort(WrapperPrices, Collections.reverseOrder());
		Arrays.sort(WrapperDiscounts, Collections.reverseOrder());
		
		for (int i = 0; i < WrapperDiscounts.length; ++i) {
			WrapperPrices[i] = (int) (WrapperPrices[i] * (100.0 - WrapperDiscounts[i]) / 100);
		}
		
		int answer = 0;
		
		for (int i = 0; i < WrapperPrices.length; ++i) {
			answer += WrapperPrices[i];
		}
		
		return answer;
    }
	
	public String[] solution070402(String s) {
		int half = s.length() / 2;
		StringBuffer sbf = new StringBuffer(s.substring(0, half));
		StringBuffer sbb = new StringBuffer(s.substring(half));
		
		System.out.println(sbf.toString() + sbb.toString());
		
		
		int fIndex = 0;
		int bIndex = 0;
		int tokenLength = 1;
		String token;
		
		List<String> tokenList = new ArrayList<>();
		List<String> answerList = new ArrayList<>();
		
		while((fIndex + tokenLength) != sbf.length()) {
			token = sbf.substring(fIndex, fIndex + tokenLength);
			bIndex = sbf.length() - tokenLength;
			
			if (sbf.substring(fIndex, fIndex + tokenLength).equals(sbb.substring(bIndex, bIndex + tokenLength))) {
				sbf.delete(fIndex, fIndex + tokenLength);
				sbb.delete(bIndex, bIndex + tokenLength);
				tokenList.add(token);
				tokenLength = 1;
			} else {
				++tokenLength;
			}
		}
		
		for (int i = 0; i < tokenList.size(); ++i) {
			answerList.add(tokenList.get(i));
		}
		
		if (sbf.toString().equals(sbb.toString())) {
			answerList.add(sbf.toString());
			answerList.add(sbb.toString());
		} else {
			sbf.append(sbb);
			answerList.add(sbf.toString());
		};
		
		for (int i = tokenList.size() - 1; i > -1; --i) {
			answerList.add(tokenList.get(i));
		}
		
		
        String[] answer = answerList.toArray(new String [answerList.size()]);
        return answer;
    }
	
	public int solution070403(String s, String t) {
		StringBuffer sb = new StringBuffer(s);
		int idx = sb.indexOf(t);
		int length = t.length();
		int answer = 0;
		
		while(idx != -1) {
			sb.delete(idx, idx + length);
			idx = sb.indexOf(t);
			++answer;
		}
		
		return answer;
	}
}
