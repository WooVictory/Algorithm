package 백트래킹;

import java.io.*;

/**
 * created by victory_woo on 15/07/2019
 * 로또
 * 조금 다른 풀이.
 */
public class BOJ6603_other {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] a;
    private static StringBuilder sb;
    private static int count, k;

    public static void main(String[] args) throws IOException {
        while (true) {
            sb = new StringBuilder();
            String[] in = br.readLine().split(" ");
            k = parse(in[0]);

            // k가 0이면 종료한다.
            if (k == 0) break;

            a = new int[k + 1];

            // 입력을 배열에 담는다.
            for (int i = 1; i <= k; i++) {
                a[i] = parse(in[i]);
            }

            // 배열에 담긴 원소 중에서 첫 번째 원소부터 시작해서 로또 번호를 찾는다.
            for (int i = 1; i <= k; i++) {
                count++;
                dfs(i, String.valueOf(a[i]));
            }

            bw.write(sb.toString()+"\n");

        }
        bw.flush();
    }

    private static void dfs(int index, String lotto) {
        // 로또 번호의 길이가 되면 출력한다.
        if (count == 6) {
            sb.append(lotto).append("\n");
        }

        // index 다음부터 시작해서 count 를 증가시키고
        // 다음 로또 번호를 찾는다.
        for (int i = index + 1; i <= k; i++) {
            count++;
            dfs(i, lotto + " " + a[i]);
        }

        // 백트래킹.
        // 끝까지 갔다가 count 를 하나 감소시켜서 다시 찾기 위함이다.
        count--;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}