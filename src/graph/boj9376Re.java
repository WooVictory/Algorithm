package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/07
 * 탈옥.
 * bfs.
 * 다시 풀어보기!
 */
public class boj9376Re {
    private static final int MAX = 105;
    private static int h, w;
    private static char[][] a;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            h = toInt(in[0]) + 2;
            w = toInt(in[1]) + 2;

            a = new char[MAX][MAX];
            Person helper = new Person(0, 0);
            Person prison1 = null, prison2 = null;

            for (int i = 1; i < h - 1; i++) {
                String input = '.' + br.readLine() + '.';
                for (int j = 0; j < w; j++) {
                    char value = input.charAt(j);
                    switch (value) {
                        case '.':
                        case '*':
                        case '#':
                            a[i][j] = value;
                            break;
                        case '$':
                            a[i][j] = value;
                            if (prison1 == null) prison1 = new Person(i, j);
                            else prison2 = new Person(i, j);
                            break;
                    }
                }
            }

            // 까먹은 부분.
            for (int i = 0; i < w; i++) {
                a[0][i] = a[h - 1][i] = '.';
            }

            // 각각의 위치에 따른 bfs 를 모두 돌려서 distance 배열을 얻는다.
            int[][] distance1 = bfs(helper), distance2 = bfs(prison1), distance3 = bfs(prison2);

            // min : 최대 값을 담아준다. 아무리 문을 많이 열어도 입력 받은 이차원 배열 안 쪽이기 때문. 즉, 가로 x 세로 영역
            // 안에서 일어날 것이기 때문이다.
            int min = h * w, cost;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 벽인 경우에는 이동 횟수(가중치)가 없기 때문에 보지 않고 넘어가도 된다.
                    if (a[i][j] == '*') continue;

                    // 벽이 아닌 경우에 해당 정점에 방문한 상근이, 죄수1, 죄수2 에 대한 이동 횟수를 더해 cost 에 저장한다.
                    cost = distance1[i][j] + distance2[i][j] + distance3[i][j];

                    // 다음 정점이 문이라면 cost 에서 -2를 해준다.
                    // 이유는 상근이, 죄수1, 죄수2가 모두 동일한 정점인 문을 열었기 때문에 중복을 제거해줘야 한다.
                    if (a[i][j] == '#') cost -= 2;

                    // 최소값을 찾는다.
                    min = Math.min(min, cost);
                }
            }
            System.out.println(min);
        }
    }

    private static int[][] bfs(Person person) {
        int[][] distance = new int[h][w]; // visit 의 역할도 수행함.
        for (int i = 0; i < h; i++) Arrays.fill(distance[i], -1);

        LinkedList<Person> q = new LinkedList<>();
        q.add(person);
        distance[person.x][person.y] = 0; // 까먹은 부분.

        while (!q.isEmpty()) {
            Person now = q.remove();
            int x = now.x, y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx > h || ny > w) continue;

                // 다음 정점이 벽일 경우, 탐색하지 않아도 된다.
                if (a[nx][ny] == '*') continue;

                // 다음 정점이 문일 경우.
                if (a[nx][ny] == '#') {
                    // 다음 정점에 방문한 적이 없거나 다음 정점에 대한 이동 횟수가 이전의 이동횟수 + 1 보다 큰 경우(최소 이동 횟수라는 뜻)
                    // 탐색이 가능하다. 문을 열기 때문에 가중치를 증가시킨다. (이동횟수를 증가시킨다.)
                    if (distance[nx][ny] == -1 || distance[nx][ny] > distance[x][y] + 1) {
                        distance[nx][ny] = distance[x][y] + 1;
                        q.add(new Person(nx, ny));
                    }
                }

                // 다음 정점이 빈 곳이거나 죄수의 위치일 경우.
                if (a[nx][ny] == '.' || a[nx][ny] == '$') {
                    // 다음 정점에 방문한 적이 없거나 다음 정점에 대한 이동 횟수가 이전의 이동 횟수보다 큰 경우
                    // 탐색이 가능하다. 이 경우에는 문을 열지 않기 때문에 이동 횟수에 대한 증가가 없다.
                    if (distance[nx][ny] == -1 || distance[nx][ny] > distance[x][y]) {
                        distance[nx][ny] = distance[x][y];
                        q.add(new Person(nx, ny));
                    }
                }
            }
        }
        return distance;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Person {
        int x;
        int y;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}