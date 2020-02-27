package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/27
 * 로또
 * dfs, 백트래킹
 * dfs 2번.
 */
public class boj6603 {
    private static int[] a;
    private static final int MAX = 6;
    private static StringBuilder sb;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] in = br.readLine().split(" ");
            k = toInt(in[0]);
            sb = new StringBuilder();

            if (k == 0) return;

            a = new int[k];

            for (int i = 0; i < k; i++) {
                a[i] = toInt(in[i + 1]);
            }

            dfs("", 0, 0);
            System.out.println(sb.toString());
        }
    }

    private static void dfs(String password, int index, int count) {
        // count == MAX : 6개의 숫자로 로또 번호를 만들었음을 의미한다.
        if (count == MAX) {
            sb.append(password).append("\n");
            return;
        }

        // index 가 입력 받은 숫자의 갯수보다 크거나 같으면 안된다.
        // 배열의 인덱스를 벗어나기 때문에!
        if (k <= index) return;

        // 다음 숫자를 포함해서 로또 번호를 만든 경우. 다음 숫자를 포함했기 때문에 count+1
        dfs(password + a[index] + " ", index + 1, count + 1);

        // 다음 숫자를 포함하지 않고 로또 번호를 만든 경우. 다음 숫자를 포함하지 않기 때문에 count
        dfs(password, index + 1, count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
