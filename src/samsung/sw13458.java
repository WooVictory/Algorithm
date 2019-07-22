package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 22/07/2019
 * sw 역량 기출 시험감독.
 * 시간 초과가 난다...
 */
public class sw13458 {
    private static int n, count;
    private static int B, C;
    private static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        map = new int[n];
        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            map[i] = parse(in[i]);
        }

        in = br.readLine().split(" ");
        B = parse(in[0]);
        C = parse(in[1]);

        solve();

        /*for (int i = 0; i < n; i++) {
            System.out.print(map[i] + " ");
        }*/
        System.out.println(count);
    }

    private static void solve() {
        LinkedList<Integer> q = new LinkedList<>();
        int index = 0;
        q.add(map[index]);

        while (!q.isEmpty()) {
            int value = q.remove();
            value -= B;
            count++;

            while (value > 0) {
                value -= C;
                count++;
            }

            if (value <= 0) {
                index++;
                if (index < n) {
                    q.add(map[index]);
                } else {
                    return;
                }
            }
        }

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
