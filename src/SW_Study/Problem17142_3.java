package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/20
 * 연구소 3.
 * 삼성 기출.
 * 다시 풀기.
 * <p>
 * 초기
 * 0 : 빈칸
 * 1 : 벽
 * 2 : 바이러스 위치.
 */
public class Problem17142_3 {
    private static int n;
    private static int result;
    private static int[][] map;
    private static int[] set;
    private static ArrayList<Virus> list;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    // 상하좌우.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        int m = toInt(in[1]);

        // 초기화.
        init();
        // 입력을 받으면서 바이러스의 위치를 리스트에 저장한다.
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
                if (map[i][j] == 2) list.add(new Virus(i, j, 0));
            }
        }

        set = new int[m];

        // 총 바이러스의 개수에서 m개를 뽑는 조합을 진행한다.
        combination(list.size(), m, 0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void init() {
        map = new int[n][n];
        list = new ArrayList<>();
        result = Integer.MAX_VALUE;
    }

    private static void combination(int N, int R, int index, int target) {
        // 조합을 만든 경우, 그 조합을 기반으로 bfs 를 진행한다.
        if (R == 0) {
            bfs();
            return;
        }

        if (target == N) return;

        set[index] = target;
        combination(N, R - 1, index + 1, target + 1);
        combination(N, R, index, target + 1);
    }

    /*
     * bfs() 탐색을 진행해 바이러스를 퍼트린다.
     * 벽은 -1로 초기화.
     * 비활성 바이러스는 -9로 초기화.
     * 바이러스는 -2로 초기화.
     * */
    private static void bfs() {
        int[][] copy = new int[n][n];
        LinkedList<Virus> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];

        // 활성 바이러스와 비활성 바이러스를 나눠서 처리한다.
        // 비활성 바이러스는  -9로 초기화한다.
        // 활성 바이러스는 -2로 초기화 하면서 큐에 넣고 방문 여부를 체크한다.
        for (int i = 0; i < list.size(); i++) {
            boolean flag = false;
            for (int index : set) {
                // 활성 바이러스인 경우.
                if (i == index) flag = true;
            }

            Virus virus = list.get(i);

            if (!flag) {
                copy[virus.x][virus.y] = -9;
            } else {
                copy[virus.x][virus.y] = -2;
                q.add(virus);
                visit[virus.x][virus.y] = true;
            }
        }

        // 벽 초기화.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) copy[i][j] = -1;
            }
        }

        // bfs 탐색을 진행한다.
        while (!q.isEmpty()) {
            Virus cur = q.remove();
            int time = cur.time;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위를 벗어나거나 방문한 적이 있거나 해당 정점이 벽이거나 혹은 활성 바이러스인 경우에는 건너뛴다.
                if (!isRange(nx, ny) || visit[nx][ny] || copy[nx][ny] == -1 || copy[nx][ny] == -2) continue;

                // 빈칸이거나 비활성 바이러스인 경우, 방문 여부를 체크하고 큐에 넣는다.
                // 이유는 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 -> 활성 상태로 바뀌기 때문이다.
                if (copy[nx][ny] == 0 || copy[nx][ny] == -9) {
                    visit[nx][ny] = true;
                    q.add(new Virus(nx, ny, time + 1));

                    // 빈칸인 경우, 처음 방문한 곳이기 때문에 해당 정점에 지금가지 걸린 시간 + 1을 넣어준다.
                    if (copy[nx][ny] == 0) {
                        copy[nx][ny] = time + 1;
                    } else {
                        // 이미 값이 있는 경우는 다른 바이러스에 의해서 이미 퍼지고 시간이 적혀 있는 것을 의미한다.
                        // 따라서 최소 시간으로 갱신이 가능하면 갱신한다.
                        // 왜냐하면 바이러스는 동시에 인접한 네 칸으로 퍼지기 때문이다.
                        // 현재 정점에 적힌 시간보다 지금까지 걸린 시간 + 1이 작으면 지금까지 걸린 시간 + 1로 갱신한다.
                        if (copy[nx][ny] > time + 1) copy[nx][ny] = time + 1;
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;

        if (isFinish(copy)) return;

        // 0보다 큰 값에 대해서 확인을 하며 가장 큰 값을 찾아 max 에 저장한다
        // 이는 바이러스를 모두 퍼트리는 데 걸린 시간을 구한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] < 0) continue;
                if (max < copy[i][j]) max = copy[i][j];
            }
        }

        // true : 바이러스를 퍼트리는 데 걸린 시간 중 최소 값을 구한다.
        if (isCompleted(copy)) {
            if (max < result) result = max;
        } else {
            result = 0;
        }
    }

    // 이미 벽과 충분한 바이러스로 꽉 차고 애초에 빈칸이 없는 경우에는 false 값을 반환한다.
    // 따라서 바이러스가 퍼지는 데 걸리는 시간은 없다. 0이다.
    // 0보다 큰 값이 하나라도 존재한다면 바이러스가 퍼진 시간을 계산할 수 있기 때문에 true 반환.
    private static boolean isCompleted(int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] > 0) return true;
            }
        }
        return false;
    }

    // 바이러스가 모든 곳에 퍼졌는지 확인한다.
    // 모든 곳에 퍼졌다면 false 반환.
    // 퍼지지 않은 곳이 한 곳이라도 존재하면 true 반환.
    private static boolean isFinish(int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0) return true;
            }
        }
        return false;
    }

    // 범위 안에 들어오는 지 확인한다.
    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

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
