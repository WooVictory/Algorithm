package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 02/08/2019
 * SW 역량 기출 연구소 Review.
 * copyMap 을 사용하지 않으면 원본 배열이 계속 바뀐다.
 * 하나의 정점에 벽을 세우고 다른 방향으로 이동하면서 계속 최댓값을 구하기 위해서 바꿔야 하는데
 * 원본을 복사해서 사용하지 않으면 배열이 계속 바뀌어서 값을 유지하기가 힘들고 최댓값을 구할 수 없다.
 */
public class sw14502_review {
    private static final String SPACE = " ";
    private static int n, m, result;
    private static int[][] map, copyMap;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);

        // 초기화.
        n = parse(in[0]);
        m = parse(in[1]);
        map = new int[n][m];

        // 입력.
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        // 이중 for 문 대신 n*m 까지 반복문을 돌려서
        // 나머지와 나누기 연산을 통해 2차원 배열의 인덱스 모두에 접근한다. -> 시간 복잡도 감소.
        for (int i = 0; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            // 빈 칸이라면 벽을 세운다.
            if (map[x][y] == 0) {
                map[x][y] = 1;
                buildWall(i, 1);
            }
        }

        System.out.println(result);
    }

    // 1. 벽을 3개까지 세운다.
    private static void buildWall(int v, int count) {
        int x = v / m;
        int y = v % m;

        // 3개의 벽을 모두 세운 경우.
        if (count == 3) {
            copyMap = new int[n][m];

            // copyMap 으로 원본 배열을 복사한다.
            for (int i = 0; i < n; i++) {
                if (m >= 0) System.arraycopy(map[i], 0, copyMap[i], 0, m);
            }

            // 복사된 배열에서 바이러스를 찾고 퍼뜨린다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyMap[i][j] == 2) {
                        spreadVirus(i, j);
                    }
                }
            }

            // 결과 구하기.
            getResult();
        } else {
            for (int i = v + 1; i < n * m; i++) {
                int nextX = i / m;
                int nextY = i % m;

                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = 1;
                    buildWall(i, count + 1);
                }
            }
        }
        map[x][y] = 0;
        --count;
    }

    // 2. 바이러스를 퍼뜨린다.
    private static void spreadVirus(int x, int y) {
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 3; // 새로 퍼뜨려진 바이러스.
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    // 3. 안전 영역의 크기를 구한다.
    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    count++;
                }
            }
        }

        // 4. 그 중 최대값을 구해 result 를 갱신시킨다.
        result = Math.max(result, count);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
