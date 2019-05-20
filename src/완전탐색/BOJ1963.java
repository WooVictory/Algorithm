package 완전탐색;

import java.io.*;
import java.util.LinkedList;


/**
 * created by victory_woo on 20/05/2019
 * 완탐 : 소수 경로
 */
public class BOJ1963 {
    private static final int MAX = 10001;
    private static boolean[] prime = new boolean[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        getPrime();

        while (t-- > 0) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            boolean[] check = new boolean[MAX];
            int[] distance = new int[MAX];
            LinkedList<Integer> q = new LinkedList<>();
            q.add(a);
            check[a] = true;
            distance[a] = 0;

            while (!q.isEmpty()) {
                int now = q.remove();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 10; j++) {
                        int next = change(now, i, j); // 한자리씩 바꾸면서 다음 숫자를 구한다.
                        if (next != -1) {
                            // 소수이면서 방문한 적이 없다면 체크.
                            if (!prime[next] && !check[next]) {
                                q.add(next);
                                check[next] = true;
                                distance[next] = distance[now] + 1;
                            }
                        }
                    }
                }
            }

            bw.write(distance[b] + "\n");
        }
        bw.flush();
    }

    private static void getPrime() {
        prime[0] = prime[1] = true;

        for (int i = 2; i < MAX; i++) {
            if (prime[i])
                continue;

            for (int j = i * i; j < MAX; j += i) {
                prime[j] = true;
            }
        }
    }

    // num의 index번째를 digit으로 바꾼다.
    private static int change(int num, int index, int digit) {
        if (index == 0 && digit == 0) {
            return -1;
        }

        String s = Integer.toString(num);
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(index, (char) (digit + '0'));
        System.out.println(Integer.parseInt(sb.toString()));
        return Integer.parseInt(sb.toString());
    }
}