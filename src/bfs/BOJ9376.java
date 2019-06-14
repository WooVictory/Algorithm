package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 13/06/2019
 * bfs : 탈옥
 */
public class BOJ9376 {
    private static int h, w;
    private static char[][] map = new char[105][105];
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = parse(br.readLine());

        while (test_case-- > 0) {

            String[] in = br.readLine().split(" ");

            // 영역을 확장해준다.
            h = parse(in[0]) + 2; // 높이 h : 행의 갯수. 즉, 세로가 됨.
            w = parse(in[1]) + 2; // 너비 w : 열의 갯수. 즉, 가로가 됨.

            Node helper = new Node(0, 0); // 상근이
            Node prison1 = null;
            Node prison2 = null;

            // 기존에 h 높이만큼 + 가로+2 해서 영역을 확장한다.
            for (int i = 1; i < h - 1; i++) {
                // 가로 영역 확장을 위해서 양 끝에 "."을 추가해준다.
                String s = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {
                    char ch = s.charAt(j);
                    switch (ch) {
                        case '.':
                        case '*':
                        case '#':
                            map[i][j] = ch;
                            break;
                        case '$':
                            map[i][j] = ch;
                            if (prison1 == null) {
                                prison1 = new Node(i, j);
                            } else {
                                prison2 = new Node(i, j);
                            }
                            break;
                    }
                }
            }

            // h 높이에서 맨 윗 부분과 맨 아랫 부분의 영역을 확장한다.
            for (int j = 0; j < w; j++) {
                map[0][j] = map[h - 1][j] = '.';
            }

            // solve
            int[][] dist1 = bfs(helper);
            int[][] dist2 = bfs(prison1);
            int[][] dist3 = bfs(prison2);

            int answer = h * w;
            int tempCost = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 벽인 경우에는 dist 를 알 필요가 없음.
                    // 어차피 가중치 없어서 0이기 때문.
                    if (map[i][j] == '*')
                        continue;

                    tempCost = dist1[i][j] + dist2[i][j] + dist3[i][j];
                    if (map[i][j] == '#') {
                        tempCost -= 2;
                    }

                    answer = Math.min(answer, tempCost);
                }
            }

            System.out.println(answer);
        }
    }

    private static int[][] bfs(Node node) {
        int[][] dist = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], -1);
        }

        LinkedList<Node> q = new LinkedList<>();
        q.add(node);
        dist[node.x][node.y] = 0;

        while (!q.isEmpty()) {
            Node now = q.remove();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;

                // 벽이라면 이동할 수 없으니까 다음 정점을 확인하기 위해 continue 시킨다.
                if (map[nx][ny] == '*')
                    continue;

                // 다음 정점이 비어있거나 죄수자라면.
                if (map[nx][ny] == '.' || map[nx][ny] == '$') {
                    // 다음 정점에 대해 방문한 적이 없거나 이전 정점에서의 거리보다 길다면. 이 말은 즉, 이동한 거리가 길다는 것.
                    // 이 경우 문을 연 것이 아니기 때문에 가중치가 없다.
                    if (dist[nx][ny] == -1 || dist[nx][ny] > dist[x][y]) {
                        dist[nx][ny] = dist[x][y];
                        q.add(new Node(nx, ny));
                    }
                } else if (map[nx][ny] == '#') {// 다음 정점이 문이라면.
                    // 방문한 적이 없거나 다음 정점의 거리가 현재 정점의 거리보다 길다면
                    // 이 경우에는 문을 연 것이므로 가중치를 1 증가시킨다.
                    if (dist[nx][ny] == -1 || dist[nx][ny] > dist[x][y] + 1) {
                        q.add(new Node(nx, ny));
                        dist[nx][ny] = dist[x][y] + 1;
                    }
                }
            }
        }
        return dist;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}