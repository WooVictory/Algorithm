package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/07
 * 인구 이동.
 * 구현 + bfs
 *
 */
public class sw16234 {
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        L = toInt(in[1]);
        R = toInt(in[2]);

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        int count = 0;
        boolean hasNext;
        while (true) {
            hasNext = false;
            visit = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j] && check(i, j) > 1) {
                        hasNext = true;
                    }
                }
            }

            // 2중 for 문이 끝나고서 확인하는 이유는 전체를 탐색하고 인구 이동을 하여, 인구 수를 바꾸는 것이
            // 결국, 최종적인 인구 이동 발생 1번을 의미하기 때문이다.
            if (hasNext) count++;
            else break;
        }
        System.out.println(count);
    }

    private static int check(int x, int y) {
        LinkedList<Nation> nations = new LinkedList<>(); // 연합국.
        LinkedList<Nation> q = new LinkedList<>();
        Nation start = new Nation(x, y);
        q.add(start);
        nations.add(start);
        visit[x][y] = true;
        int sum = map[x][y];

        while (!q.isEmpty()) {
            Nation current = q.remove();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isMoveable(nx, ny, current)) {
                    Nation next = new Nation(nx, ny);
                    q.add(next);
                    nations.add(next);
                    visit[nx][ny] = true;
                    sum += map[nx][ny];
                }
            }
        }

        if (nations.size() > 1) {
            int average = sum / nations.size();
            for (Nation nation : nations) {
                map[nation.x][nation.y] = average;
            }
        }

        return nations.size();
    }

    // 방문한 적이 없는지도 체크해야 함 -> 이 부분 빼먹었음....
    private static boolean isMoveable(int nx, int ny, Nation current) {
        if (0 <= nx && nx < N && 0 <= ny && ny < N && !visit[nx][ny]) {
            int abs = Math.abs(map[current.x][current.y] - map[nx][ny]);
            return L <= abs && abs <= R;
        }
        return false;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Nation {
        int x;
        int y;

        Nation(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}