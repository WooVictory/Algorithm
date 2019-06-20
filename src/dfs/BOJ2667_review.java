package dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 20/06/2019
 */
public class BOJ2667_review {
    private static final String NEW_LINE = "\n";
    private static int n, components, count;
    private static int[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static ArrayList<Integer> countList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = parse(br.readLine());

        // 초기화.
        a = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        // 입력.
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }

        find();

        // StringBuilder 객체를 사용해서 출력을 최소화한다.
        StringBuilder sb = new StringBuilder();
        sb.append(components).append(NEW_LINE);

        // 정렬을 함으로써 오름차순 출력을 한다.
        Collections.sort(countList);
        for (int value : countList){
            sb.append(value).append(NEW_LINE);
        }

        bw.write(sb.toString());
        bw.flush();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }

    }

    // 방문하지 않고 정점이 집인 곳을 찾는다.
    // 단지를 구성할 수 있기 때문에 components 변수를 증가시킨다.
    // dfs 탐색을 하면서 인접한 네 방향을 모두 탐색하도록 했기 때문에 탐색할 수 있는 곳은
    // 탐색을 하게 된다. 따라서 연결된 단지를 찾을 수 있다.
    // 다음 집을 찾을 때는 다른 단지이기 때문에 count 를 0으로 초기화 하는 것이다.
    // 그리고 단지에 속하는 집의 수를 오름차순으로 정렬하기 위해 countList 에 추가한다.
    private static void find() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && a[i][j] == 1) {
                    components++;
                    count = 0;
                    dfs(i, j);

                    countList.add(count);
                }
            }
        }
    }

    // dfs 탐색을 한다.
    // 방문을 하고 count 를 증가시킨다. dfs 탐색을 돌 때마다 증가시킨다.
    // 이는 방문하지 않았고 다음 정점이 집인 경우만 dfs 탐색을 하기 때문에 이처럼 count 값을 증가시킴으로 인해서
    // 단지에 속하는 집의 수를 알아낼 수 있다.
    private static void dfs(int x, int y) {
        if(visited[x][y])
            return;

        visited[x][y] = true;
        a[x][y] = components; // 단지 별로 components 를 할당함으로써 자신이 소속된 단지의 번호를 부여해준다.
        count++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (!visited[nx][ny] && a[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}

