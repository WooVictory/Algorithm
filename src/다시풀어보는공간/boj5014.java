package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/30
 * 스타트 링크.
 * count 계산을 해도 되고, distance 배열을 사용해 얼만큼 걸렸는지 저장해도 된다!
 */
public class boj5014 {
    private static int F, S, G, U, D, result = 0;
    private static boolean[] visit;
    private static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        F = toInt(in[0]);
        S = toInt(in[1]);
        G = toInt(in[2]);
        U = toInt(in[3]);
        D = toInt(in[4]);
        visit = new boolean[F + 1];
        d = new int[F + 1];

        solve(S);
        if (result == 0 && !visit[G]) System.out.println("use the stairs");
        else System.out.println(result);

        /*if (d[G] == 0 && !visit[G]) System.out.println("use the stairs");
        else System.out.println(d[G]);*/
    }

    private static void solve(int start) {
        LinkedList<Spot> q = new LinkedList<>();
        q.add(new Spot(start, 0));
        visit[start] = true;

        while (!q.isEmpty()) {
            Spot current = q.remove();
            int position = current.position;
            int count = current.count;
            int next;

            if (position == G) {
                result = count;
                return;
            }

            // U 버튼 눌렀을 때.
            next = position + U;
            if (next > F) continue;
            if (!visit[next]) {
                visit[next] = true;
                d[next] = d[position] + 1;
                q.add(new Spot(next, count + 1));
            }

            next = position - D;
            if (next < 0 || next > F) continue;
            if (!visit[next]) {
                visit[next] = true;
                d[next] = d[position] + 1;
                q.add(new Spot(next, count + 1));
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Spot {
        int position;
        int count;

        Spot(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }
}
