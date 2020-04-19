package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/19
 * 연구소 3.
 * 삼성 기출.
 */
public class Problem17142Re {
    private static int n, m, result = Integer.MAX_VALUE;
    private static int[][] map;
    private static ArrayList<Virus> list;
    private static int[] set; // 조합.
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        init();

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
                if (map[i][j] == 2) list.add(new Virus(i, j, 0));
            }
        }

        set = new int[m];
        combination(list.size(), m, 0, 0);
        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    private static void init() {
        map = new int[n][n];
        list = new ArrayList<>();
    }

    // N개 중에서 R개를 뽑는 조합.
    private static void combination(int N, int R, int index, int target) {
        if (R == 0) {
            bfs();
            return;
        }

        if (target == N) return;

        set[index] = target;
        combination(N, R - 1, index + 1, target + 1); // 뽑는 경우.
        combination(N, R, index, target + 1); // 뽑지 않는 경우.
    }

    private static void bfs() {
        LinkedList<Virus> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];
        int[][] copy = new int[n][n];

        // 비활성 바이러스를 찾아 -9로 초기화 한다.
        loop:
        for (int i = 0; i < list.size(); i++) {
            for (int index : set) {
                if (i == index) continue loop;
            }

            Virus virus = list.get(i);
            copy[virus.x][virus.y] = -9;
        }

        // 벽인 지점은 -1로 초기화.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) copy[i][j] = -1;
            }
        }

        // 활성 바이러스는 -2로 초기화 하고, 방문 여부를 체크하며 큐에 미리 넣는다.
        for (int index : set) {
            Virus virus = list.get(index);
            copy[virus.x][virus.y] = -2;
            visit[virus.x][virus.y] = true;
            q.add(virus);
        }

        while (!q.isEmpty()) {
            Virus cur = q.remove();
            int time = cur.time;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위를 벗어나거나 방문한 적이 있거나 혹은 벽이거나 활성 바이러스인 경우에는 건너뛴다.
                if (isRange(nx, ny) || visit[nx][ny] || copy[nx][ny] == -1 || copy[nx][ny] == -2) continue;

                // 빈칸이거나 비활성 바이러스인 경우.
                if (copy[nx][ny] == 0 || copy[nx][ny] == -9) {
                    visit[nx][ny] = true;
                    q.add(new Virus(nx, ny, time + 1));

                    // 처음 바이러스가 퍼진 곳은 이전 좌표로 오기까지 걸린 시간 + 1을 copy[nx][ny]에 놔준다.
                    if (copy[nx][ny] == 0) {
                        copy[nx][ny] = time + 1;
                    } else {
                        // 처음이 아닌 경우에는 이전 좌표로 오기까지 걸린 시간과 copy[nx][ny]에 값을 비교하여
                        // 더 작은 값을 copy[nx][ny]에 놔준다.
                        // 이를 통해서 바이러스가 동시에 퍼지는 경우를 구현한다.
                        // 비활성 바이러스는 갱신이 안된다.
                        if (time + 1 < copy[nx][ny]) copy[nx][ny] = time + 1;
                    }
                }
            }
        }

        print(copy);

        // 바이러스가 퍼지지 못한 곳이 한 곳이라도 존재하면 이번 바이러스의 조합은 종료한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0) return;
            }
        }

        // 바이러스가 모든 칸으로 퍼지는데 걸린 시간을 찾는다.
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] < 0) continue;
                if (max < copy[i][j]) max = copy[i][j];
            }
        }

        if (check(copy)) {
            if (max < result) result = max;
        } else {
            result = 0;
        }
    }

    // 범위를 벗어나는지 체크한다.
    private static boolean isRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    // 벽과 바이러스로만 둘러 쌓여 있어서 바이러스가 퍼진 시간이 없는 경우를 확인한다.
    // 0보다 큰 값이 있다는 것은 바이러스가 퍼질 수 있었고, 얼마나 걸렸는지 시간을 계산할 수 있음을 의미한다.
    // 0보다 큰 값이 없다면 이는 이미 바이러스와 벽으로만 구성되어 있어 바이러스가 퍼지는데 걸린 시간은 0이다.
    private static boolean check(int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] > 0) return true;
            }
        }
        return false;
    }

    private static void print(int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // 좌표와 time 을 저장한다.
    static class Virus {
        int x;
        int y;
        int time;

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
