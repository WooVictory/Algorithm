package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/14
 * 연구소.
 */
public class SW14502_REVIEW {
    private static int n, m, max = 0;
    private static int[][] map, copy;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        for (int i = 0; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            // 빈 공간인 경우, 벽을 세운다.
            // 그리고 3개까지 세워본다.
            if (map[x][y] == 0) {
                map[x][y] = 1;
                setWall(i, 1);
            }
        }
        System.out.println(max);
    }

    // 벽을 3개까지 놓아본다.
    // 이중 for 반복문이 아닌 1중 for 반복문을 사용해 시간 복잡도를 최소화했다.
    private static void setWall(int v, int count) {
        int x = v / m;
        int y = v % m;

        // 3개의 벽을 놓았으면, 바이러스를 퍼트리고 안전 영역의 갯수를 센다.
        if (count == 3) {
            copy = copyOriginal();
            spreadVirus();
            getResult();
        } else {
            for (int i = v + 1; i < n * m; i++) {
                int nx = i / m;
                int ny = i % m;

                // 벽을 놓는다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    setWall(i, count + 1);
                }
            }
        }

        // 백트래킹을 통해 벽을 놓았던 자리를 원래대로 되돌린다.
        map[x][y] = 0;
        --count;
    }

    private static void spreadVirus() {
        LinkedList<Virus> q = new LinkedList<>();

        // 바이러스인 지점을 큐에 넣는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 2) q.add(new Virus(i, j));
            }
        }

        // 큐에 있는 바이러스를 꺼내서 빈 공간에 바이러스를 퍼트린다.
        while (!q.isEmpty()) {
            Virus cur = q.remove();
            int x = cur.x, y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = 2;
                    q.add(new Virus(nx, ny));
                }
            }
        }
    }

    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 0) count++;
            }
        }
        max = Math.max(max, count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // 2차원 배열 deep copy.
    private static int[][] copyOriginal() {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    static class Virus {
        int x;
        int y;

        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
