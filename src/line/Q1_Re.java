package line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/18
 * 19년도 상반기 인턴 라인 기출문제.
 * bfs.
 * 다시 풀어보기!
 */
public class Q1_Re {
    private static final int MAX = 200000;
    private static int C, B;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        C = toInt(in[0]);
        B = toInt(in[1]);

        // visit 배열을 2차원으로 사용한다.
        // 2차원인 이유는 어떤 위치에 갔을 때, 시간이 홀수 혹은 짝수로 나타내기 위함이다.
        // 예를 들어, 2 -> 4 -> 3 과 2 -> 3의 경우가 있다.
        // 서로 다른 시간 대에 같은 위치 3에 대해서 접근했기 때문에 두 가지 경우는 엄연히 다르게 판단해야 한다.
        // 따라서 홀수와 짝수로 시간대를 나누어 저장하여 구분한다.
        visit = new boolean[MAX + 1][2];
        System.out.println(solve(C, B));
    }

    // time 값을 마지막에 증가시켜 주기 때문에
    // 브라운이 탐색한 부분을 다음 반복문에서 코니가 확인을 하게 된다.
    // 브라운 : (time+1) % 2
    // 코니 : time % 2
    // 이렇게 연산을 진행하기 때문에 time 값이 증가하면 다음 턴에서는 방문했는지 여부를 확인할 수 있다.

    private static int solve(int conyPosition, int brownPosition) {
        int time = 0;
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(brownPosition, 0));

        while (true) {
            // 코니가 움직이고, 시간은 증가한다.
            conyPosition += time;

            // 코니가 범위를 벗어나면 -1을 반환한다.
            if (conyPosition > MAX) return -1;

            // 코니의 위치가 time % 2인 시간대에 방문한 적이 있다는 것은
            // 브라운이 방문했음을 의미하므로 즉, 브라운이 코니를 잡았다. 따라서 time(시간)을 반환한다.
            if (visit[conyPosition][time % 2]) return time;

            // 큐에 넣었던 값들을 빼면서 확인한다.
            // 이때, size 를 변수로 빼두고 이만큼만 돌리도록 해야 한다.
            // 그렇지 않으면 아래에서 큐에 계속 넣기 때문에 무한 루프가 된다.
            for (int i = 0, size = q.size(); i < size; i++) {
                Node now = q.remove();
                int currentPosition = now.position;
                int nextTime = (now.time + 1) % 2;
                int nextPosition;

                // 1. B - 1
                nextPosition = currentPosition - 1;
                if (!visit[nextPosition][nextTime] && 0 < nextPosition) {
                    visit[nextPosition][nextTime] = true;
                    q.add(new Node(nextPosition, nextTime));
                }

                // 2. B + 1
                nextPosition = currentPosition + 1;
                if (!visit[nextPosition][nextTime] && nextPosition <= MAX) {
                    visit[nextPosition][nextTime] = true;
                    q.add(new Node(nextPosition, nextTime));
                }

                // 3. B * 2
                nextPosition = currentPosition * 2;
                if (!visit[nextPosition][nextTime] && nextPosition <= MAX) {
                    visit[nextPosition][nextTime] = true;
                    q.add(new Node(nextPosition, nextTime));
                }
            }
            // 시간을 증가시킨다.
            time++;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int position;
        int time;

        Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }
}
