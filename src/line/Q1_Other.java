package line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/17
 * 19년도 상반기 라인 코딩 테스트
 * 브라운과 코니.
 */
public class Q1_Other {
    private static final int MAX = 200000;
    private static int C, B;
    private static boolean[][] visit = new boolean[MAX + 1][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        C = toInt(in[0]);
        B = toInt(in[1]);

        System.out.println(solve(C, B));
    }

    private static int solve(int conyPosition, int brownPosition) {
        int time = 0;
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(brownPosition, 0));

        while (true) {
            conyPosition += time;

            if (conyPosition > MAX) return -1;

            if (visit[conyPosition][time % 2]) return time;

            for (int i = 0, size = q.size(); i < size; i++) {
                Node now = q.remove();
                int currentPosition = now.position;
                int newTime = (now.time + 1) % 2, newPosition;

                newPosition = currentPosition - 1;
                if (!visit[newPosition][newTime] && 0 < newPosition) {
                    visit[newPosition][newTime] = true;
                    q.add(new Node(newPosition, newTime));
                }

                newPosition = currentPosition + 1;
                if (!visit[newPosition][newTime] && newPosition <= MAX) {
                    visit[newPosition][newTime] = true;
                    q.add(new Node(newPosition, newTime));
                }

                newPosition = currentPosition * 2;
                if (!visit[newPosition][newTime] && newPosition <= MAX) {
                    visit[newPosition][newTime] = true;
                    q.add(new Node(newPosition, newTime));
                }
            }

            time++;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int position;
        int time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }
}
