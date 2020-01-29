package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

/**
 * created by victory_woo on 2020/01/29
 */
public class boj13913 {
    private static final int MAX = 200000;
    private static int[] distance, from, result;
    private static boolean[] visit;
    private static int[] state = {1, -1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int n = toInt(in[0]);
        int k = toInt(in[1]);

        distance = new int[MAX + 1];
        from = new int[MAX + 1];
        visit = new boolean[MAX + 1];
        result = new int[MAX + 1];

        bfs(n);

        System.out.println(distance[k]);

        //print(k);
        printWithStack(k);

    }

    private static void printWithStack(int k) {
        Stack<Integer> stack = new Stack<>();
        stack.push(k);
        while (distance[k]-- > 0) {
            k = from[k];
            stack.push(k);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void print(int k) {
        int value = distance[k];

        for (int i = value - 1, current = k; i >= 0; i--) {
            result[i] = from[current];
            current = from[current];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value; i++) sb.append(result[i]).append(" ");
        System.out.println(sb.append(k).toString());
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < state.length; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (next < 0 || next > MAX) continue;

                if (!visit[next]) {
                    visit[next] = true;
                    distance[next] = distance[now] + 1;
                    from[next] = now;
                    q.add(next);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
