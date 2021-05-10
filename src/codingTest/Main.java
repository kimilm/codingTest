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
//		System.out.println(solution.solution040601(new int[][] {{1,2,3,5},{5,6,7,8},{4,3,2,1}}));
//		System.out.println(solution.solution050401(new String[] { "1195524421", "97674223", "119" }));
//		System.out.println(solution.solution050801("one4seveneight"));
//		System.out.println(Arrays.toString(solution.solution050802(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},{"PXPX0P", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
//		System.out.println(solution.solution0508032(8, 2, new String [] {"D 2","C","U 3","C","D 4","C","U 2"}));
//		System.out.println(solution.solution0508032(8, 0, new String [] {"C", "C", "D 1", "C", "D 1", "C", "C"}));
		System.out.println(solution.solution0508032(8, 0, new String [] {"C", "C", "D 1", "C", "U 1", "C", "C"}));

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
        		if (phone_book[i].substring(0, prefix.length()).equals(prefix)) {	// String.startWith()
        			answer = false;
        		} else {
        			++i;
        		}
        	}
        }
        
        return answer;
    }
	
	int solution050601(String name) {
		boolean [] done = new boolean[name.length()];
		int curIdx = 0;
		int cnt = 0;
		int answer = 0;
		
		for(int i = 0; i < name.length(); ++i) {
			if (name.charAt(i) == 'A') {
				done[i] = true;
				++cnt;
			}
		}
		
		while (cnt != name.length()) {
			
		}
		
		return answer;
	}
	
	int solution050801(String s) {
		StringBuilder answer = new StringBuilder();
        int index = 0;
        String token;
        
        while (index != s.length()) {
            if (s.charAt(index) > 47 && s.charAt(index) < 58) {
                answer.append(s.charAt(index));
                index += 1;
            } else {
                token = s.substring(index, index + 2);
                switch (token) {
                    case "ze":
                        answer.append('0');
                        index += 4;
                        break;
                    case "on":
                        answer.append('1');
                        index += 3;
                        break;
                    case "tw":
                        answer.append('2');
                        index += 3;
                        break;
                    case "th":
                        answer.append('3');
                        index += 5;
                        break;
                    case "fo":
                        answer.append('4');
                        index += 4;
                        break;
                    case "fi":
                        answer.append('5');
                        index += 4;
                        break;
                    case "si":
                        answer.append('6');
                        index += 3;
                        break;
                    case "se":
                        answer.append('7');
                        index += 5;
                        break;
                    case "ei":
                        answer.append('8');
                        index += 5;
                        break;
                    case "ni":
                        answer.append('9');
                        index += 4;
                        break;
                }
            }
        }
        
        return Integer.parseInt(answer.toString());
	}
	
	class Point {
		private int x;
		private int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
	
	int [] solution050802(String [][] places) {
		int[] answer = new int[5];
		char[] place = new char[5];
		List<Point> points = new ArrayList<>();
        
		int x;
    	int y;
    	int dist;
    	boolean flg = true;
        
        for(int i = 0; i < 5; ++i) {
        	for (int j = 0; j < 5; ++j) {
        		place = places[i][j].toCharArray();
        		
        		for (int k = 0; k < 5; ++k) {
        			if (place[k] == 'P') {
        				points.add(new Point(j, k));
        			}
        		}
        	}
        	
        	
        	for(int l = 0; l < points.size() - 1 && flg; ++l) {
        		for(int m = l + 1;  m < points.size() && flg; ++m) {
        			x = points.get(m).getX() - points.get(l).getX();
        			y = points.get(m).getY() - points.get(l).getY();
        			dist = Math.abs(x) + Math.abs(y);

        			switch(dist) {
        			case 1:
        				flg = false;
        				break;
        			case 2:
        				if (x == 0) {
        					if (places[i][points.get(m).getX()].charAt(points.get(m).getY() - 1) == 'O')
        						flg = false;
        				} else if (y == 0) {
        					if (places[i][points.get(m).getX() - 1].charAt(points.get(m).getY()) == 'O')
        						flg = false;
        				} else if (y > 0) {
        					if (places[i][points.get(m).getX() - 1].charAt(points.get(m).getY()) == 'O' || places[i][points.get(m).getX()].charAt(points.get(m).getY() - 1) == 'O')
        						flg = false;
        				} else {
        					if (places[i][points.get(m).getX() - 1].charAt(points.get(m).getY()) == 'O' || places[i][points.get(m).getX()].charAt(points.get(m).getY() + 1) == 'O')
        						flg = false;
        				}
        				break;
        			}
        		}
        	}
        	
        	if (flg) {
        		answer[i] = 1;
        	} else {
        		answer[i] = 0;
        	}
        	
        	points.clear();
        	flg = true;
        }
        
        return answer;
	}
	
	public String solution050803(int n, int k, String[] cmd) {
		Stack<Integer> stack = new Stack<>();
		List<Integer> lList = new LinkedList<>();
		StringBuilder answer = new StringBuilder();
		
		for (int i = 0; i < n; ++i) {
			answer.append('O');
			lList.add(i);
		}
		
		char [] token;
		for (String str : cmd) {
			token = str.toCharArray();
			
			switch(token[0]) {
			case 'U':
				k -= token[2] - 48;
				break;
			case 'D':
				k += token[2] - 48;
				break;
			case 'C':
				stack.add(lList.remove(k));
				--n;
				if (k == n)
					--k;
				break;
			case 'Z':
				lList.add(stack.peek() - 1, stack.peek());
				if (k >= stack.pop())
					++k;
				++n;
				break;
			}
		}
		
		while(!stack.isEmpty())
			answer.replace(stack.peek(), stack.pop() + 1, "X");

        return answer.toString();
    }
	
	public String solution0508032(int n, int k, String[] cmd) {
		Stack<Integer> stack = new Stack<>();
		StringBuilder answer = new StringBuilder();
		
		for (int i = 0; i < n; ++i) {
			answer.append('O');
		}
		
		char [] token;
		for (String str : cmd) {
			token = str.toCharArray();
			
			switch(token[0]) {
			case 'U':
				k -= token[2] - 48;
				break;
			case 'D':
				k += token[2] - 48;
				break;
			case 'C':
				stack.add(k);
				--n;
				if (k == n)
					--k;
				break;
			case 'Z':
				if (k >= stack.pop())
					++k;
				++n;
				break;
			}
		}
		
		Object [] array = stack.toArray();
		int [] replaceArray = new int[stack.size()];
		int prev = (int)array[0];
		int add;
		
		for (int i = 0; i < array.length; ++i) {
			replaceArray[i] = (int)array[0];
		}
		
		for (int i = 1; i < array.length; ++i) {
			add = (int)array[i] - prev;
			if (add > 0) {
				++add; 
			} else if (add == 0) {
				add = 1;
			}
			
			for (int j = i; j < array.length; ++j) {
				replaceArray[j] += add;
			}
			
			prev = (int)array[i];
		}
		
		for (int replace : replaceArray)
			answer.replace(replace, replace + 1, "X");

        return answer.toString();
    }
	
	public String solution0508033(int n, int k, String[] cmd) {
		Stack<Integer> stack = new Stack<>();
		TreeSet<Integer> set = new TreeSet<>();
		StringBuilder answer = new StringBuilder();
		
		for (int i = 0; i < n; ++i) {
			answer.append('O');
			set.add(i);
		}
		
		char [] token;
		for (String str : cmd) {
			token = str.toCharArray();
			
			switch(token[0]) {
			case 'U':
				k -= token[2] - 48;
				break;
			case 'D':
				k += token[2] - 48;
				break;
			case 'C':
				stack.add(k);
				--n;
				if (k == n)
					--k;
				break;
			case 'Z':
				if (k >= stack.pop())
					++k;
				++n;
				break;
			}
		}
		
		Object [] array = stack.toArray();
		int [] replaceArray = new int[stack.size()];
		int prev = (int)array[0];
		int add;
		
		for (int i = 0; i < array.length; ++i) {
			replaceArray[i] = (int)array[0];
		}
		
		for (int i = 1; i < array.length; ++i) {
			add = (int)array[i] - prev;
			if (add > 0) {
				++add; 
			} else if (add == 0) {
				add = 1;
			}
			
			for (int j = i; j < array.length; ++j) {
				replaceArray[j] += add;
			}
			
			prev = (int)array[i];
		}
		
		for (int replace : replaceArray)
			answer.replace(replace, replace + 1, "X");

        return answer.toString();
    }
}
