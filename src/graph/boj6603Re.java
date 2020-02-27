package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/27
 * 로또
 * dfs, 백트래킹.
 */
public class boj6603Re {
    private static int[] a;
    private static final int MAX = 6;
    private static StringBuilder sb;
    private static int k, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] in = br.readLine().split(" ");
            sb = new StringBuilder();
            k = toInt(in[0]);

            if (k == 0) return;

            a = new int[k];

            for (int i = 0; i < k; i++) {
                a[i] = toInt(in[i + 1]);
            }

            // 각 숫자에 대해서 dfs 탐색을 모두 진행한다.
            // 인덱스에 해당하는 숫자부터 로또 번호를 만들어 dfs 탐색을 시작한다.
            for (int i = 0; i < k; i++) {
                count++;
                dfs(i, a[i] + " ");
            }
            System.out.println(sb.toString());
        }
    }

    private static void dfs(int index, String lotto) {
        // 6개의 로또 번호를 모두 만들었음을 뜻한다.
        if (count == MAX) {
            sb.append(lotto).append("\n");
        } else {
            // index+1 : 다음 숫자부터 선택해서 로또 번호를 만든다.
            for (int i = index + 1; i < k; i++) {
                count++;
                dfs(i, lotto + a[i] + " ");
            }
        }
        // 백트래킹.
        // 이를 통해서 결국은 처음으로 돌아가면 count 는 0이 된다.
        count--;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
