package bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 14/06/2019
 * bfs : 탈옥
 */
public class BOJ9376_review {
    private static final String SPACE = " ";
    private static char[][] map = new char[105][105];
    private static int h, w;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = parse(br.readLine());

        while (test_case-- > 0) {
            String[] in = br.readLine().split(SPACE);
            // 상하좌우의 영역을 확장해준다.
            // 왜냐하면 상근이가 밖에서 들어와야 하기 때문에!
            h = parse(in[0]) + 2;
            w = parse(in[1]) + 2;

            // 상근이, 죄수자1, 죄수자2
            Node helper = new Node(0, 0);
            Node prisoner1 = null;
            Node prisoner2 = null;

            // 확장된 영역에서 원래 영역인 1 ~ h 까지만 채운다.
            for (int i = 1; i < h - 1; i++) {
                // 좌우의 영역을 확장하기 위해 "."+br.readLine()+"."을 해준다.
                String s = "." + br.readLine() + ".";

                for (int j = 0; j < w; j++) {
                    char ch = s.charAt(j);

                    switch (ch) {
                        // 비어있는 곳이거나 벽이거나 문이라면 그냥 map 배열에 넣는다.
                        case '.':
                        case '*':
                        case '#':
                            map[i][j] = ch;
                            break;
                        case '$':
                            map[i][j] = ch;
                            // 죄수는 두명 뿐이기 때문에 이렇기 구성한다.
                            if (prisoner1 == null) {
                                prisoner1 = new Node(i, j);
                            } else {
                                prisoner2 = new Node(i, j);
                            }
                            break;
                    }
                }
            }

            // h 높이에서 맨 윗 부분과 맨 아랫 부분의 영역을 확장한다.
            // 이렇게 나누는 이유는 맨 윗 부분과 아랫 부분은 "." 으로 채워져야 하기 때문이다.
            for (int j = 0; j < w; j++) {
                map[0][j] = map[h - 1][j] = '.';
            }

            int[][] dist1 = bfs(helper);
            int[][] dist2 = bfs(prisoner1);
            int[][] dist3 = bfs(prisoner2);

            // 아무리 많이 문을 열어도 h*w 영역 안에서 일어날 것이기 때문에 이렇게 지정.
            int answer = h * w;
            int cost = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 벽이라면 어차피 0이니까 그냥 건너뛴다.
                    if (map[i][j] == '*') {
                        continue;
                    }

                    cost = dist1[i][j] + dist2[i][j] + dist3[i][j];
                    // 해당 지점이 만약 문이라면
                    // 상근이, 죄수자1, 죄수자2가 모두 문을 열고 나왔을 것이므로 -2를 해준다.
                    // 이미 문이 열리면 계속 열려있기 때문에 가중치는 1이다.
                    if (map[i][j] == '#') {
                        cost -= 2;
                    }

                    // 최소값을 구해준다.
                    answer = Math.min(answer, cost);
                }
            }
            bw.write(answer+"\n");
        }
        bw.flush();
    }

    private static int[][] bfs(Node node) {
        int[][] distance = new int[h][w];
        // distance 배열을 -1로 초기화해준다.
        for (int i = 0; i < h; i++) {
            Arrays.fill(distance[i], -1);
        }

        LinkedList<Node> q = new LinkedList<>();
        q.add(node);
        distance[node.x][node.y] = 0;

        while (!q.isEmpty()) {
            Node now = q.remove();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위에서 벗어나면 위로 다시 올라간다.
                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;

                // 벽을 만났을 경우, 건너뛰어도 된다.
                // 가중치도 없고, 벽으로 이동할 수도 없기 때문이다.
                if (map[nx][ny] == '*') {
                    continue;
                }

                // 비어있는 곳이거나 죄수를 만났을 경우,
                // 큐에 넣고 distance 배열은 이전과 같다. 이유는 가중치가 없기 때문이다.
                if (map[nx][ny] == '.' || map[nx][ny] == '$') {
                    // 방문한 적이 없고, 이전보다 이동거리가 길다면, 다음 정점에 대해 탐색이 가능하다.
                    if (distance[nx][ny] == -1 || distance[nx][ny] > distance[x][y]) {
                        q.add(new Node(nx, ny));
                        distance[nx][ny] = distance[x][y];
                    }
                }

                // 문을 만났을 경우,
                // 큐에 넣고 문을 열었기 때문에 가중치를 증가시킨다.
                if (map[nx][ny] == '#') {
                    // 방문한 적이 없고, 이전보다 이동 거리가 길다면 다음 정점에 대해 탐색이 가능하다.
                    if (distance[nx][ny] == -1 || distance[nx][ny] > distance[x][y] + 1) {
                        q.add(new Node(nx, ny));
                        distance[nx][ny] = distance[x][y] + 1;
                    }
                }
            }
        }
        return distance;
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
