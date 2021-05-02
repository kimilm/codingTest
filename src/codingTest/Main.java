package codingTest;

import java.util.*;

import codingTest.Solution.Land;

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();

		int[] arr = { 1, 2, 3, 4, 5 };

		// for(int i = 0; i < 20; ++i)
		// System.out.println(i + ": " + solution.solution020902(i));

//		System.out.println(solution.solution021501(new int[] { 1, 2, 3, 9, 10, 12 }, 7));
		System.out.println(solution.solution040601(new int[][] {{1,2,3,5},{5,6,7,8},{4,3,2,1}}));     
		

//		System.out.println(solution.solution020801(new int [] {1, 1, 9, 1, 1, 1}, 1));
//		System.out.println(solution.solution020801(new int [] {1, 1, 9, 1, 1, 1}, 2));
//		System.out.println(solution.solution020801(new int [] {1, 1, 9, 1, 1, 1}, 3));
//		System.out.println(solution.solution020801(new int [] {1, 1, 9, 1, 1, 1}, 4));
//		System.out.println(solution.solution020801(new int [] {1, 1, 9, 1, 1, 1}, 5));

//		System.out.println(Arrays.toString());
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

	int solution040501(String numbers) {		// 소수 찾기
		Set<Integer> numberSet = new HashSet<>();
		String [] tokens = numbers.split("");
		int len = tokens.length + 1;
		
		Arrays.sort(tokens, Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tokens.length; ++i) {
			sb.append(tokens[i]);
		}
		int max = Integer.parseInt(sb.toString());

		boolean [] prime = new boolean[max + 1];
		prime[0] = true;
		prime[1] = true;
		
		for(int i = 2; i < prime.length; ++i) {
			if (!prime[i])
				for (int j = i * 2; j < prime.length; j += i)
					prime[j] = true;
		}
		
		for(int i = 1; i < len; ++i) {
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
	    public Permutation(String [] intArr, int r){
	        this.r = r;             // nPr의 r
	        this.n = intArr.length; // nPr의 n
	        this.res = new String[r];  // 결과값 배열
	        this.result = new ArrayList<>();
	        
	        // 아이템이 들어갈 리스트
	        itemList = new ArrayList<String>();
	        for(String item : intArr)
	            itemList.add(item);
	    }
	    
	    public List<Integer> perm(int depth){
	        perm(itemList, 0);
	        return result;
	    }
	 
	    public void perm(ArrayList<String> itemList, int depth) {
	        
	    	// depth가 0부터 시작했을 경우 depth == r에서 리턴
	        if (depth == r) {
	        	StringBuilder sb = new StringBuilder();
	        	for(String token : res)
	        		sb.append(token);
	            result.add(Integer.parseInt(sb.toString())); 
	            //System.out.println(Arrays.toString(res));
	            return;
	        }
	        
	        for (int i = 0; i < n-depth; i++){
	            res[depth] = itemList.remove(i); // 아이템 선택 + 리스트에서 제거
	            perm(itemList, depth+1);         // 재귀호출
	            itemList.add(i, res[depth]);     // 제거된 아이템 복원
	        }
	    }
	}
	
	class Land implements Comparable<Land> {
		private int col;
		private int val;
		
		public Land (int col, int val) {
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
			return  o.getVal() - this.val;
		}
	}
	
	int solution040601(int[][] land) {	// 땅따먹기
		Land [] sorted = new Land[4];
		int [] sum = new int [4];
		
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

}

