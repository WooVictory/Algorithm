package dfs;

import java.io.*;
import java.util.ArrayList;

/**
 * created by victory_woo on 20/06/2019
 * dfs : 연결 요소의 개수
 */
public class BOJ11724 {
    private static final String SPACE = " ";
    private static ArrayList<Integer>[] a;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(SPACE);
        int n = parse(in[0]);
        int m = parse(in[1]);

        // 초기화.
        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        visited = new boolean[n + 1];

        // 인접 리스트 초기화.
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        // 입력.
        for (int i = 0; i < m; i++) {
            String[] num = br.readLine().split(SPACE);
            int u = parse(num[0]);
            int v = parse(num[1]);

            a[u].add(v);
            a[v].add(u);
        }
        bw.write(dfsAll(n) + "");
        bw.flush();
    }

    // 인접 리스트에서 각 요소를 검사한다.
    // 방문한 적이 있는지 체크함으로써 연결된 요소들을 확인할 수 있다.
    // dfs() 함수를 통해서 연결된 요소들은 true 로 체크되기 때문이다.
    // false 라는 말은 연결되지 않았다는 의미이므로 다른 components 이다.
    private static int dfsAll(int n) {
        int components = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i);
            }
        }
        return components;
    }

    // 일반적인 dfs 탐색 과정이다.
    // 방문 여부를 체크하고
    // 인접 리스트에서 요소를 꺼내서 방문했는지를 체크하고 다시 dfs 를 돌려 탐색한다.
    private static void dfs(int x) {
        if(visited[x]){
            return;
        }

        visited[x] = true;
        for (Integer value : a[x]){
            if(!visited[value]){
                dfs(value);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

}
