package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 01/08/2019
 * SW 역량 기출 연구소.
 * 결국 벽을 세우기 위해 모든 경우를 찾는 DFS.
 * 벽을 세운 뒤, 바이러스를 퍼뜨리기 위한 DFS.
 * 총 2개의 DFS 탐색을 수행한 뒤, 결과를 찾아야 한다.
 */
public class sw14502 {
    private static int n, m, result;
    private static int[][] map, copyMap;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        for (int i = 0; i < n * m; i++) {
            int x = (int) (i / (m * 1.0));
            int y = i % m;

            // 빈칸일 경우. 벽을 놓는다.
            if (map[x][y] == 0) {
                map[x][y] = 1;
                buildWall(i, 1);
                //map[x][y] = 0; 이거 어차피 buildWall() 함수 마지막에서 백트래킹하기 때문에 필요없음.
            }
        }
        System.out.println(result);
    }

    private static void buildWall(int v, int count) {
        int x = (int) (v / (m * 1.0));
        int y = v % m;

        // 벽을 3개 다 세운 경우.
        if (count == 3) {
            copyMap = new int[n][m];
            // copyMap 으로 모두 복사한다.
            // 원본 배열을 유지하기 위해서.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 바이러스인 곳을 찾는다.
                    if (copyMap[i][j] == 2) {
                        spreadVirus(i, j);
                    }
                }
            }
            getResult();
        } else {
            // 벽이 3개가 다 세워지지 않은 경우에는 다음 벽을 세우기 위해
            // 작업을 수행한다.
            for (int i = v + 1; i < n * m; i++) {
                int nx = (int) (i / (m * 1.0));
                int ny = i % m;

                // 빈칸인 경우, 벽을 세우고 DFS 탐색을 재귀적으로 호출.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1; // 벽을 놓는다.
                    buildWall(i, count + 1);
                }
            }
        }

        // 다시 원래 상태로 되돌아오기 위해서 백트래킹을 한다.
        map[x][y] = 0;
        --count;
    }

    // 결과를 출력하기 위한 함수이다.
    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.max(result, count);
    }

    // 바이러스를 퍼뜨린다.
    // 상하좌우 인접한 곳으로 모두 퍼뜨린다.
    private static void spreadVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위에 들어가고 빈칸이라면 바이러스를 퍼뜨릴 수 있다.
            // 바이러스를 퍼뜨린 곳은 3으로 표시한다.
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 3;
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
