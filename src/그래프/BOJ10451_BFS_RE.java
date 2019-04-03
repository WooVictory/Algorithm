package 그래프;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * created by victory_woo on 03/04/2019
 * 그래프 : 순열 싸이클
 * BFS를 사용
 * 배열을 이용하는 방법
 */
public class BOJ10451_BFS_RE {
    private static int[] arr;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            visit = new boolean[n + 1];
            arr = new int[n + 1];

            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(input[i-1]);
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
        Queue<Integer> q = new LinkedList<>();
        visit[start] = true; // 방문 했음을 표시한다.

        q.add(start);

        while (!q.isEmpty()){
            int x = q.poll();
            int y = arr[x];
            if(!visit[y]){
                visit[y] = true;
                q.add(y);
            }

        }
        // 방문할 때 1을 리턴한다.
        // 예를 들어 1이 들어오면 1에서 갈 수 있는 정점들을 다 방문한다.
        // 1->3->7->5
        // 그래서 main() 안에 있는 반복문이 돌 때 방문 했는지 확인하면 방문을 했기 때문에
        // 방문하지 않고 다음에 방문한다.
        return 1;

    }

}
