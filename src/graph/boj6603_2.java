package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/27
 */
public class boj6603_2 {
    private static final int MAX = 6;
    private static int k, count = 0;
    private static StringBuilder sb;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] in = br.readLine().split(" ");
            k = toInt(in[0]);
            if (k == 0) return;

            sb = new StringBuilder();
            a = new int[k];

            for (int i = 0; i < k; i++) {
                a[i] = toInt(in[i + 1]);
            }

            // 입력받은 각 숫자에 대해서 dfs 탐색을 진행한다.
            // count 를 공용으로 쓰지만, dfs 탐색이 끝나고 백트래킹을 통해 결국은 0이 된다.
            // dfs()로 인덱스와 인덱스에 맞는 숫자를 시작으로 하는 로또 번호를 함께 넘겨 시작한다.
            for (int i = 0; i < k; i++) {
                count++;
                dfs(i, a[i] + " ");
            }
            System.out.println(sb.toString());
        }
    }

    private static void dfs(int index, String lotto) {
        // 이 경우, 6개의 로또 번호를 만들었으므로 sb 객체에 추가한다.
        // return 하지 않는다. return 하게 되면 뒤로 돌아갔을 때 다음 번호를 찾지 못한다.
        if (count == MAX) {
            sb.append(lotto).append("\n");
        } else {
            // index+1을 통해서 바로 다음 숫자를 붙여서 로또 번호를 만들고 count 를 증가시킨다.
            for (int i = index + 1; i < k; i++) {
                count++;
                dfs(i, lotto + a[i] + " ");
            }
        }

        // 백트래킹
        // count 값을 감소시킨다.
        // 초기화 작업.
        count--;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}