package codingTest;

import java.util.*;

import codingTest.Solution.Point;

public class Test {
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
	
	public int[] solution050901(String code, String day, String[] data) {
		TreeMap<String, String> prices = new TreeMap<>();

		String[] splits;
		for (int i = 0; i < data.length; ++i) {
			splits = data[i].split("=| ");

			if (splits[3].equals(code))
				if (splits[5].startsWith(day))
					prices.put(splits[5], splits[1]);
		}

		Iterator<Map.Entry<String, String>> it = prices.entrySet().iterator();

		int idx = 0;
		int[] answer = new int[prices.size()];

		while (it.hasNext()) {
			answer[idx++] = Integer.parseInt(it.next().getValue());
		}

		return answer;
	}

	class Person implements Comparable<Person> {
		private int id;
		private int time;
		private int level;

		Person(int id, int time, int level) {
			this.id = id;
			this.time = time;
			this.level = level;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		@Override
		public int compareTo(Person o) {
			if (this.time == o.getTime())
				if (this.level == o.getLevel())
					return this.id - o.getId();
				else
					return this.level - o.getLevel();
			else
				return this.time - o.getTime();
		}
	}

	public int[] solution0509021(int[] t, int[] r) {
		LinkedList<Person> lList = new LinkedList<>();

		for (int i = 0; i < t.length; ++i) {
			lList.add(new Person(i, t[i], r[i]));
		}

		int[] answer = new int[t.length];

		Person temp;

		for (int i = 0; i < t.length; ++i) {
			Collections.sort(lList);

			temp = lList.poll();
			answer[i] = temp.getId();

			for (int j = 0; j < lList.size(); ++j) {
				if (lList.get(j).getTime() == temp.getTime())
					lList.get(j).setTime(temp.getTime() + 1);
				else
					break;
			}
		}

		return answer;
	}

	public int[] solution0509022(int[] t, int[] r) {
		PriorityQueue<Person> pQue = new PriorityQueue<>();

		for (int i = 0; i < t.length; ++i) {
			pQue.add(new Person(i, t[i], r[i]));
		}

		int[] answer = new int[t.length];

		Person temp;

		for (int i = 0; i < t.length; ++i) {
			temp = pQue.poll();
			answer[i] = temp.getId();

			if (!pQue.isEmpty() && temp.getTime() == pQue.peek().getTime())
				pQue.peek().setTime(temp.getTime() + 1);
		}

		return answer;
	}
}
