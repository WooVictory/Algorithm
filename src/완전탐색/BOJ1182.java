package 완전탐색;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 24/05/2019
 * 완탐 : 부분 순열의 합
 */
public class BOJ1182 {
    private static int[] arr;
    private static int n, s;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = parse(input[0]);
        s = parse(input[1]);
        arr = new int[n];

        String[] numbers = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = parse(numbers[i]);
        }

        dfs(0, 0);


        /*
        * 주의 사항
        * 부분집합을 구하다보면 공집합이 생기기 마련이다.
        * s 가 0이라면 공집합 때문에 아무 값도 더하지 않아서 0인 경우도 카운트하게 된다.
        * 따라서 이 부분에 대한 예외 처리가 필요하다.
        * */

        if (s == 0){
            count--;
        }
        System.out.println(count);


    }

    private static void dfs(int index, int sum) {
        // 인덱스의 끝에 도달하면 모든 경우 다 탐색했으므로
        // 지금까지 더한 값과 s를 비교하고 같다면 count 를 증가시키고 리턴한다.
        if (index == n) {
            if (sum == s) {
                count++;
            }
            return;
        }

        // arr[i] 를 더하는 경우.
        dfs(index + 1, sum + arr[index]);
        // arr[i] 를 더하지 않는 경우.
        dfs(index + 1, sum);

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
