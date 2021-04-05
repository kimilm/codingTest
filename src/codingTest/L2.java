package codingTest;

import java.util.*;

public class L2 {
	// 02 08
		class Document {
			private int document;
			private int priority;

			Document(int document, int priority) {
				this.document = document;
				this.priority = priority;
			}

			int getDocument() {
				return document;
			}

			int getPriority() {
				return priority;
			}
		}

		public int solution020801(int[] priorities, int location) {
			Queue<Document> documents = new LinkedList<Document>();
			Queue<Integer> priorityOrder = new PriorityQueue<Integer>(priorities.length, Collections.reverseOrder());

			for (int i = 0; i < priorities.length; ++i)
				documents.add(new Document(i, priorities[i]));

			for (int priority : priorities)
				priorityOrder.add(priority);

			int answer = 0;
			Document print = null;

			while (!documents.isEmpty()) {
				if (documents.peek().getPriority() == priorityOrder.peek()) {
					++answer;
					print = documents.poll();
					priorityOrder.poll();

					if (print.getDocument() == location) {
						break;
					}

				} else {
					documents.add(documents.poll());
				}
			}

			return answer;
		}

		// 02 09
		public int[] solution020901(int[] prices) { // 주식가격
			Stack<Integer> stack = new Stack<>();

			int[] answer = new int[prices.length];

			for (int i = 0; i < prices.length - 1; ++i) {
				for (int j = i + 1; j < prices.length; ++j) {
					stack.push(1);

					if (prices[i] > prices[j]) {
						break;
					}
				}

				while (!stack.isEmpty())
					answer[i] += stack.pop();
			}

			return answer;
		}

		public String solution020902(int n) { // 124 나라
			StringBuilder[] code = new StringBuilder[3];
			StringBuilder answer = new StringBuilder();
			code[0] = new StringBuilder("4");
			code[1] = new StringBuilder("1");
			code[2] = new StringBuilder("2");

			while (n > 0) {
				answer.append(code[n % 3]);
				if (n % 3 == 0)
					--n; // n = (n - 1) / 3; 이 세줄이 이거 하나로 끝난다
				n /= 3;
			}

			return answer.reverse().toString();
		}

		// 02 10
		public int solution021001(int bridge_length, int weight, int[] truck_weights) { // 다리를 지나는 트럭
			int answer = 0;
			int truck = 0;
			int currentWeight = 0;

			Queue<Integer> bridge = new LinkedList<>();

			if (truck_weights.length == 0) {
				return 0;
			}

			for (int i = 0; i < bridge_length; ++i)
				bridge.add(0);

			while (!bridge.isEmpty()) {
				currentWeight -= bridge.poll();

				++answer;

				if (truck == truck_weights.length) {
					continue;
				} else if (currentWeight + truck_weights[truck] <= weight) {
					bridge.add(truck_weights[truck]);
					currentWeight += truck_weights[truck];
					++truck;
				} else {
					bridge.add(0);
				}
			}

			return answer;
		}

		public int[] solution021002(int[] progresses, int[] speeds) { // 기능개발
			List<Integer> done = new ArrayList<>();

			int index = 0;
			int jobs = 0;

			while (index != speeds.length) {
				for (int i = index; i < speeds.length; ++i) {
					progresses[i] += speeds[i];
				}

				while (progresses[index] > 99) {
					++jobs;
					++index;

					if (index == speeds.length) {
						break;
					}
				}

				if (jobs != 0) {
					done.add(jobs);
					jobs = 0;
				}
			}

			int[] answer = new int[done.size()];

			for (int i = 0; i < done.size(); ++i) {
				answer[i] = done.get(i);
			}

			return answer;
		}

		public String solution021003(int[] numbers) { // 가장 큰 수 -> 메모리 초과 실패
			List<int[]> permutationList = new ArrayList<>();

			permutation(permutationList, numbers, 0, numbers.length, numbers.length);

			StringBuilder[] numberArray = new StringBuilder[permutationList.size()];

			for (int i = 0; i < permutationList.size(); ++i) {
				numberArray[i] = new StringBuilder();
				for (int j = 0; j < numbers.length; ++j) {
					numberArray[i].append(String.valueOf(permutationList.get(i)[j]));
				}
			}

			Arrays.sort(numberArray, Collections.reverseOrder());

			return numberArray[0].toString();
		}

		public void permutation(List<int[]> permutationList, int[] arr, int depth, int n, int r) {
			if (depth == r) {
				permutationList.add(arr.clone());
			}
			for (int i = depth; i < n; i++) {
				swap(arr, depth, i);
				permutation(permutationList, arr, depth + 1, n, r);
				swap(arr, depth, i);
			}
		}

		public void swap(int[] arr, int depth, int i) {
			int temp = arr[depth];
			arr[depth] = arr[i];
			arr[i] = temp;
		}
}
