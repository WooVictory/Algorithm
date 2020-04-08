package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/08
 * 인구 이동.
 * 다시 푸는 중!
 */
public class sw16234Re {
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북동남서 방향.

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

        boolean hasNext;
        int count = 0;
        while (true) {
            hasNext = false;
            visit = new boolean[N][N];

            // 모든 정점을 돌면서 방문한 적이 없고, 연합국의 수가 1보다 크다면, 연합국이 존재한다.
            // 또한, 연합국끼리 인구 이동이 발생한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j] && check(i, j) > 1) {
                        hasNext = true;
                    }
                }
            }

            // 연합국이 존재하기 때문에 인구 이동 발생 횟수를 증가시킨다.
            // 2중 for 문으로 한 번의 탐색이 다 돌면서 인구 이동이 이루어지는 과정이 결국 한 번의 인구 이동 발생이기 때문에
            // 밖에서 count 값을 증가시킨다.
            if (hasNext) count++;
            else break;
        }

        System.out.println(count);
    }

    // (x,y)좌표 기준으로 인접한 나라들이 연합국인지 확인한다.
    private static int check(int x, int y) {
        LinkedList<Nation> nations = new LinkedList<>(); // 연합국을 저장하는 리스트.
        LinkedList<Nation> q = new LinkedList<>(); // 큐.
        Nation start = new Nation(x, y);
        Nation next;
        q.add(start);
        nations.add(start);
        visit[x][y] = true;
        int sum = map[x][y];

        while (!q.isEmpty()) {
            Nation current = q.remove();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isMoveable(current, nx, ny)) {
                    next = new Nation(nx, ny);
                    q.add(next);
                    nations.add(next);
                    visit[nx][ny] = true;
                    sum += map[nx][ny];
                }
            }
        }

        // 연합국이 존재하는 경우.
        // 평균 인구 수를 구해서 연합국 리스트에서 하나씩 뽑아서
        // 연합국의 좌표를 가지고 와서 map 배열에 평균 인구 수를 업데이트 해준다.
        if (nations.size() > 1) {
            int average = sum / nations.size();

            for (Nation nation : nations) {
                map[nation.x][nation.y] = average;
            }
        }

        return nations.size();
    }

    // 범위 조건을 만족하는지, 그리고 L<= abs <=R 의 조건을 만족하여 국경선을 열 수 있는지 확인한다.
    private static boolean isMoveable(Nation nation, int nx, int ny) {
        if (0 <= nx && nx < N && 0 <= ny && ny < N && !visit[nx][ny]) {

            // x 좌표의 차이 + y 좌표의 차이가 아니라
            // nation 위치의 인구 수 - nx,ny 위치의 인구 수 즉, 인구 수의 차이를 계산한다.
            int abs = Math.abs(map[nation.x][nation.y] - map[nx][ny]);
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
