package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 23/05/2019
 * 완탐 : 로또
 * 백트래킹의 대표적인 문제.
 */
public class BOJ6603_1 {
    private static int[] arr;
    private static int count = 0;
    private static final int MAX = 6;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i=7;i<8;i++){
            System.out.println("짝힘?");
        }

        while (true) {
            sb = new StringBuilder();
            String[] input = br.readLine().split(" ");
            int k = parse(input[0]);

            // k가 0이면 종료.
            if (k == 0) break;
            arr = new int[k + 1];

            for (int i = 1; i <= k; i++) {
                arr[i] = parse(input[i]);
            }

            // 입력받은 수 중에서 첫번째 부터 로또 번호를 만들 수 있는지 dfs()를 통해 탐색한다.
            for (int i = 1; i <= k; i++) {
                count++;
                dfs(i, String.valueOf(arr[i]));
            }

            System.out.println(sb.toString());

        }

    }

    private static void dfs(int index, String str) {
        // count 가 6자리 로또 번호를 모두 만들었으면 \n 을 함으로써 하나를 찍는다.
        if (count == MAX) {
            sb.append(str).append("\n");
        }

        // index 번째까지 로또번호를 만들었으므로 그 뒤에 붙어야 하는 번호는 index+1 번째부터 시작해야 한다.
        // 따라서 for 반복문은 index+1부터 시작한다.
        for (int i = index + 1; i < arr.length; i++) {
            count++;
            dfs(i, str + " " + arr[i]);
        }

        // 백트래킹을 위해 감소시켜준다.
        // 재귀 호출을 하다가 마지막 재귀 호출에서 count 를 감소시켜주기 때문에
        // 백트래킹이 가능하다.
        count--;

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
