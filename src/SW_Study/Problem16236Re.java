package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/16
 * 아기 상어.
 * 삼성 기출.
 * 다시 풀기!
 */
public class Problem16236Re {
    private static final int INF = Integer.MAX_VALUE;
    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[20][20]; // 문제에서 N의 최대값이 20.
        int sharkX = 0, sharkY = 0; // 상어의 위치를 저장한다.
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);

                // 상어의 위치를 저장한다.
                // 그리고 상어의 위치는 필요없기 때문에 0으로 바꿔준다.
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;

                    map[i][j] = 0;
                }
            }
        }

        System.out.println(solve(sharkX, sharkY));
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int solve(int x, int y) {
        int distance = 0;
        int size = 2;
        int count = size;
        Fish shark = new Fish(x, y, 0);
        // 상어의 객체를 바깥 쪽에서 선언해줘야 한다.
        // 무한 루프 안에서 상어가 물고기를 먹기 위해 탐색하면서 계속 위치가 바뀌기 때문에 첫 위치를 전역 처럼 선언해야 한다.

        while (true) {
            boolean[][] visit = new boolean[20][20];
            LinkedList<Fish> q = new LinkedList<>();
            visit[shark.x][shark.y] = true;
            q.add(new Fish(shark.x, shark.y, 0));
            // 물고기를 먹었을 때, 바뀐 상어의 위치를 넣기 위해서 이렇게 객체를 생성하여 큐에 넣는다.
            shark.distance = INF;
            // 상어의 거리를 최단 거리로 업데이트 하기 위해 큰 값으로 초기화 한다.

            while (!q.isEmpty()) {
                Fish cur = q.remove();

                // 종료 조건을 명시해야 한다.

                // 상어가 이동한 거리보다 현재의 이동거리가 크다면 종료.
                // 상어가 이동한 거리가 최단 거리가 되는데, 이보다 큰 것이기 때문에 이미 최단 거리를 찾았고 더 이상 찾지 않아도 됨.
                if (cur.distance > shark.distance) break;

                // 상어의 크기보다 큰 물고기는 skip 한다.
                if (map[cur.x][cur.y] > size) continue;

                // 물고기가 존재하고 그 물고기의 크기가 상어의 크기보다 작은 경우, 상어가 해당 물고기를 먹을 수 있다.
                // 먹을 수 있는 물고기가 여러 마리라면, 거리가 가장 가까운 물고기를 먹는다.
                // 가까운 물고기가 여러 마리인 경우, 가장 위에 있는 물고기를 먹는다. 그러한 물고기가 여러 마리라면
                // 가장 왼쪽에 있는 물고기를 먹는다.
                // 조건을 검사하여, 상어가 어떤 물고기를 먹을 지 결정하게 된다.
                if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size) {
                    if (cur.distance < shark.distance) {
                        shark = cur;
                    } else if (shark.distance == cur.distance) {
                        System.out.println(cur);
                        System.out.println(shark);
                        if (cur.x < shark.x) {
                            shark = cur;
                        } else if (cur.x == shark.x && cur.y < shark.y) {
                            shark = cur;
                        }
                    }
                    continue;
                }

                // 큐에 들어간 상어를 빼면서 네 방향으로 이동할 수 있는 곳을 모두 탐색하면서 큐에 넣는다.
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    // 범위를 벗어나거나 이미 방문한 적이 있다면 건너뛴다.
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (visit[nx][ny]) continue;

                    visit[nx][ny] = true;
                    q.add(new Fish(nx, ny, cur.distance + 1));
                }
            }


            // 위의 bfs 탐색을 진행하게 되면 shark 의 위치가 바뀌고, distance 값도 갱신된다.
            // 위에서 shark 가 어떤 물고기를 먹을지 정해졌으므로, 상어가 물고기를 먹었음을 표현한다.
            // 이동한 거리만큼 증가시키고, 상어가 먹어야 하는 물고기 갯수를 감소시킨다.
            // 만약, 상어가 먹어야 하는 양의 물고기를 다 먹었으면 상어의 크기를 증가시키고 count 를 size 값으로 초기화 한다.
            if (shark.distance != INF) {
                distance += shark.distance;
                count--;
                if (count == 0) {
                    size++;
                    count = size;
                }

                // 물고기를 먹었으므로, 비워준다.
                map[shark.x][shark.y] = 0;
            } else {
                break;
            }
        }

        return distance;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Fish {
        int x;
        int y;
        int distance; // 이동한 거리.

        Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", distance=" + distance +
                    '}';
        }
    }
}
