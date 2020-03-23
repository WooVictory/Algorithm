package programmers;

/**
 * created by victory_woo on 2020/03/23
 * 콜라츠 추측.
 * 로직이 맞는데, 히든 테케 중 2개가 틀렸다.
 * 풀이를 검색해보니 나와 같았다.
 * 다만, 나는 num *= 3, num +=1을 나눠서 썼다.
 * 다른 풀이는 num *= 3 + 1 이었다.
 * 이 2개의 연산이 다른 결과 값을 낸다는 것을 알게 되었다.
 * 문제에서도 곱한 다음 1을 더하는 것이 아니라 곱하고 1을 더하는 것이기 때문에 하나의 문장으로 처리해야 맞는 듯 싶다.
 * <p>
 * 그리고 이렇게 하면 첫 번째 테케가 틀리게 되는데, 이는 복합 대입 연산자를 사용할 때, 연산자 우선 순위로 인해서 3+1이 같이 딸려 들어가기 때문이다.
 * 풀어서 명확하게 작성하는 것이 낫다.
 * num = num * 3 + 1 이렇게!
 *
 * 그래도 626331의 경우, 488이 나온다.
 * 왜그런지 검색해보니 else 에서 조건을 걸어줘야 한다.
 * n 값을 가공하면서 오버플로우가 나서 음수가 나올 수 있는데, 이때 else 문에 걸리게 되면 다른 이상한 값이 나오기 때문이다.
 */
public class PGM12943 {
    public static void main(String[] args) {
        System.out.println(solution(626331));
        System.out.println(solution(6));
    }

    public static int solution(int num) {
        int count = 0;
        while (num != 1) {
            if (num % 2 == 0) num /= 2;
            else if (num % 2 == 1) num = (num * 3) + 1;

            count++;

            if (count == 500) {
                count = -1;
                break;
            }
        }

        return count;
    }
}
