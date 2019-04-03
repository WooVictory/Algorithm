package 그래프;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 03/04/2019
 * 그래프 : 순열 싸이클
 * bfs 사용.
 * 인접 리스트 : 700ms 걸림.
 */
public class BOJ1045_BFS {
    private static ArrayList<Integer>[] arr;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            visit = new boolean[n + 1];
            arr = new ArrayList[n + 1];

            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
                int u = Integer.parseInt(input[i-1]);
                arr[i].add(u);
            }

            int result = 0;

            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {

                    result +=bfs(i);

                }
            }

            bw.write(result + "\n");

        }

        bw.flush();
    }

    private static int bfs(int start){
        LinkedList<Integer> q = new LinkedList<>();
        visit[start] = true; // 방문 했음을 표시한다.

        q.add(start);

        while (!q.isEmpty()){
            int x = q.poll();
            for (int y : arr[x]){
                if(!visit[y]){
                    visit[y] = true;
                    q.add(y);
                }
                //visit[y] = false;
            }
        }
        return 1;

    }

}
