package 그래프;

import java.io.*;
import java.util.ArrayList;

/**
 * created by victory_woo on 02/04/2019
 * 그래프 : 이분 그래프
 * 방향에 대한 설명이 없으므로 무방향 그래프 즉, 양방향 그래프로 푼다.
 * 양방향 그래프는 인접 리스트로 푸는게 좋다.
 */
public class BOJ1707 {
    private static ArrayList<Integer>[] a;
    private static int[] visit;
    private static final String SPACE = " ";
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine()); // 테스트 케이스
        while (test_case-- > 0) {
            String[] input = br.readLine().split(SPACE);

            int n = Integer.parseInt(input[0]); // 정점의 개수
            int m = Integer.parseInt(input[1]); // 간선의 개수

            // 초기화.
            a = new ArrayList[n + 1];
            visit = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                String[] inputs = br.readLine().split(SPACE);
                int u = parse(inputs[0]);
                int v = parse(inputs[1]);

                // 양방향 그래프이므로 둘 다 연결해준다.
                a[u].add(v);
                a[v].add(u);
            }

            // 모든 정점에 대해서 dfs 탐색을 진행한다.
            // 방문한 적 없는 정점에 대해 dfs
            for (int i = 1; i <= n; i++) {
                if (visit[i] == 0) {
                    dfs(i, 1);
                }
            }

            // 이분 그래프인지 유무를 확인하기 위한 ok 변수.
            boolean ok = true;

            for (int i = 1; i <= n; i++) { // 모든 정점에 대해서
                for (int j : a[i]) { // 각 정점에 (간선으로)연결된 정점들에 대해
                    // 간선으로 연결된 양 끝 정점의 색(값)이 모두 달라야 한다.
                    // 하나라도 같다면 그건 이분 그래프가 아니다.
                    if (visit[j] == visit[i]) {
                        ok = false;
                    }
                }
            }

            if (ok) {
                bw.write(YES + NEW_LINE);
            } else {
                bw.write(NO + NEW_LINE);
            }

        }

        bw.flush();
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    /*
     * c : 0,1,2 값을 가질 수 있다.
     * 0 : 방문하지 않은 경우
     * 1 : 방문했고 빨간색으로 칠한 경우
     * 2 : 방문했고 파란색으로 칠한 경우
     * 실제로 색상은 없지만 1은 빨간색, 2는 파란색이라고 임의로 지정한다.
     * */
    private static void dfs(int x, int c) {
        visit[x] = c; // 방문한 정점에 c로 표시한다.

        for (int y : a[x]) {
            if (visit[y] == 0) { // 방문한 적이 없으면 방문한다.
                dfs(y, 3 - c);
            }
        }

    }
}
