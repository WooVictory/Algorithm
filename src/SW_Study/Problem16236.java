package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/15
 * 아기 상어.
 * 시뮬레이션 + bfs
 * 난이도 높음... 어려움...
 * 다시 풀어보기!
 */
public class Problem16236 {
    private static final int INF = Integer.MAX_VALUE;
    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[20][20];

        // 상어의 위치를 저장하기 위한 변수를 선언한다.
        int sharkX = 0, sharkY = 0;

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
                // 상어의 위치를 저장한다.
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;

                    // 상어가 이동할 때, 최초 상어의 위치는 중요하지 않기 때문에
                    // 상어가 최초 위치했던 곳은 빈곳으로 바꿔준다.
                    map[i][j] = 0;
                }
            }
        }
        System.out.println(solve(sharkX, sharkY));
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int solve(int x, int y) {
        int time = 0; // 이동하는 데 걸리는 시간.
        int size = 2; // 상어의 크기. 이동할 때마다 상어의 크기와 비교해야 함.
        int count = size;
        Fish shark = new Fish(x, y, 0);
        // 최단 거리를 찾아야 하기 때문에 최초 위치는 상어의 위치로 시작한다.

        // 상어가 가장 가까운 물고기를 찾기 위해서 bfs 탐색을 한다.
        while (true) {

            // 방문 여부 체크.
            boolean[][] visit = new boolean[20][20];
            LinkedList<Fish> q = new LinkedList<>();
            visit[shark.x][shark.y] = true;
            q.add(new Fish(shark.x, shark.y, 0));
            shark.distance = INF;

            while (!q.isEmpty()) {
                Fish cur = q.remove();

                // 계속 진행하다 보면 현재 위치의 거리가 우리가 찾은 최단 거리보다 커지는 경우가 존재한다.
                // bfs 탐색을 중단한다.
                // 거리가 최단 거리보다 멀어졌다는 의미는 더이상 확인할 물고기가 없다는 걸 뜻한다.
                if (cur.distance > shark.distance) break;

                // 상어의 크기보다 큰 물고기가 있다면 해당 물고기는 skip 한다. 즉, 건너뛰고 다른 물고기를 탐색한다.
                if (map[cur.x][cur.y] > size) continue;

                // 물고기가 존재하고, 현재 상어보다 물고기의 크기가 작다고 하면 먹을 수 있다.
                if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size) {
                    // 이 물고기까지 온 거리가 그 전에 알고 있던 거리보다 작다면 최단 거리를 갱신한다.
                    // shark 를 현재 위치로 바꿔준다.
                    if (cur.distance < shark.distance) {
                        shark = cur;
                    } else if (cur.distance == shark.distance) {
                        // 거리가 같은 물고기가 존재한다면
                        // 더 위에 있는 물고기를 먹는다.
                        if (cur.x < shark.x) {
                            shark = cur;
                        } else if (cur.x == shark.x && cur.y < shark.y) {
                            // 같은 높이에 있다면 더 왼쪽에 있는 물고기를 먹어야 한다.
                            // 따라서 y 좌표가 더 작은 물고기를 먹는다.
                            shark = cur;
                        }
                    }
                    // 가장 가까운 물고기를 찾았기 때문에 continue 로 아래 네 방향으로 진행하는 탐색을 skip 한다.
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (visit[nx][ny]) continue;

                    visit[nx][ny] = true;
                    q.add(new Fish(nx, ny, cur.distance + 1));
                }
            }

            // INF 가 아닌 경우 물고기를 찾은 경우.
            // 이동 거리르 증가하고, 물고기의 갯수를 줄여준다.
            if (shark.distance != INF) {
                time += shark.distance;
                count--;
                if (count == 0) {
                    size++;
                    count = size;
                }

                // 먹은 물고기는 0으로 지워준다.
                map[shark.x][shark.y] = 0;

            } else {
                break;
            }
        }

        return time;
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Fish {
        int x;
        int y;
        int distance; // 상어가 이동하는 데 걸린 거리.(거리가 곧 시간)

        Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
