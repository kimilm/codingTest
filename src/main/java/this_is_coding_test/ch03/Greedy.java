package this_is_coding_test.ch03;

public class Greedy {

    /**
     * 거스름돈 동전 500원 100원 50원 10원이 무제한으로 존재
     * 거슬러줘야 할 돈이 N원일 때 동전의 최소 개수를 구하기
     * N은 10의 배수
     */
    public int 거스름돈(int money) {
        int[] coins = {500, 100, 50, 10};
        int answer = 0;

        for (int coin : coins) {
            answer += money / coin;
            money %= coin;
        }

        return answer;
    }
}
