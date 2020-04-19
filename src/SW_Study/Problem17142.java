package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/19
 * 연구소3.
 * 삼성 기출.
 * 조합 + bfs 탐색.
 */
public class Problem17142 {
    private static int n;
    private static int result = Integer.MAX_VALUE;
    private static int[][] map;
    private static ArrayList<Virus> list;
    private static int[] comArr;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        int m = toInt(in[1]);

        map = new int[n][n];
        list = new ArrayList<>();
        int r;
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
                if (map[i][j] == 2) list.add(new Virus(i, j, 0));
            }
        }

        r = list.size();
        comArr = new int[m];
        combination(r, m, 0, 0);

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void combination(int k, int r, int index, int target) {
        if (r == 0) {
            bfs();
            return;
        }

        if (target == k) return;

        comArr[index] = target;
        combination(k, r - 1, index + 1, target + 1);
        combination(k, r, index, target + 1);
    }

    // 여기서 visit, distance 배열 초기화.
    private static void bfs() {
        LinkedList<Virus> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];
        int[][] copy = new int[n][n];

        for (int a : comArr) System.out.print(a + " ");
        System.out.println();

        // 조합을 통해 선택되지 않은 비활성 바이러스는 -9로 표시한다.
        loop:
        for (int i = 0; i < list.size(); i++) {
            for (int index : comArr) {
                if (i == index) continue loop;
            }

            Virus virus = list.get(i);
            copy[virus.x][virus.y] = -9;
        }

        // 벽인 곳은 -1로 초기화한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) copy[i][j] = -1;
            }
        }

        // 활성 바이러스는 -2로 초기화한다.
        for (int index : comArr) {
            Virus virus = list.get(index);
            copy[virus.x][virus.y] = -2;
            q.add(virus);
            visit[virus.x][virus.y] = true;
        }

        while (!q.isEmpty()) {
            Virus cur = q.remove();
            int value = cur.value;

            for (int j = 0; j < 4; j++) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || copy[nx][ny] == -1 || copy[nx][ny] == -2)
                    continue;


                // 이 경우는 빈칸이거나 비활성 바이러스인 경우, 큐에 넣고 방문 여부를 체크한다.
                // 하지만, 빈칸의 경우만 거리 값을 갱신하고 비활성 바이러스의 경우는 큐에는 넣지만 거리를 갱신하지는 않는다.
                if (copy[nx][ny] == 0 || copy[nx][ny] == -9) {
                    visit[nx][ny] = true;
                    q.add(new Virus(nx, ny, value + 1));

                    // 비활성 바이러스의 경우는
                    // 거리 값을 넣어준다.
                    if (copy[nx][ny] == 0) {
                        copy[nx][ny] = value + 1;
                    } else {
                        // 거리 값의 최소 값을 갱신한다. -9인 지점은 갱신되지 않는다.
                        if (copy[nx][ny] > value + 1) copy[nx][ny] = value + 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
        }

        int max = Integer.MIN_VALUE;

        // 갈 수 없는 곳인 빈칸이 하나라도 존재하면 이 조합의 경우는 최단 거리를 계산할 필요가 없다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0) return;
            }
        }

        // 바이러스를 퍼트리는 데 걸린 총 시간을 max 값에 저장한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] < 0) continue;
                if (max < copy[i][j]) max = copy[i][j];
            }
        }

        // 이동 거리가 하나라도 존재한다면 이동 거리 중 최소값을 찾아 result 값에 저장한다.
        if (check(copy)) {
            if (max < result) result = max;
        } else {
            result = 0;
        }
    }

    // false 값을 반환하는 경우는 이미 바이러스와 벽으로만 이루어져 있어서 바이러스가 벽이 아닌 공간에 다 퍼져 있음을 의미한다.
    private static boolean check(int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] > 0) return true;
            }
        }
        return false;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Virus {
        int x;
        int y;
        int value;

        Virus(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
