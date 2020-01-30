package graph;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/01/30
 */
public class Dijkstra {
    public static void main(String[] args) {

        final int M = Integer.MAX_VALUE;
        int[][] G = {
                {0, 3, 5, M, M, M},
                {M, 0, 2, 6, M, M},
                {M, 1, 0, 4, 6, M},
                {M, M, M, 0, 2, 3},
                {3, M, M, M, 0, 6},
                {M, M, M, M, M, 0}
        };

        int s = 0; // 시작 정점.
        int[] D = G[s].clone(); // 가중치 배열. 시작 정점의 진출차수로 가중치 배열을 초기화.
        System.out.println(Arrays.toString(D));
        boolean[] visit = new boolean[G.length]; // 사용한 정점들을 저장할 배열.

        // 정점을 하나씩 선택하기.
        for (int i = 0; i < G.length; i++) {
            // 사용하지 않은 정점 중에서 가중치가 최소인 정점을 찾아서
            // visit 배열에 정점을 추가.

            int minIdx = -1; // 최소가중치가 저장된 D 배열의 index
            int min = M; // 최소 가중치를 저장할 변수.

            // 최소 정점을 찾는다.
            for (int j = 0; j < D.length; j++) {
                if (!visit[j] && min > D[j]) {
                    minIdx = j;
                    min = D[j];
                }
            }

            // 가중치가 최소인 정점의 인덱스를 얻어 정점을 알아낸다.
            visit[minIdx] = true;

            // 선택한 정점을 통해서 갈 수 있는 인접한 정점의 가중치를 갱신한다.
            for (int j = 0; j < D.length; j++) {
                // 사용하지 않은 정점, 인접한 정점, 가중치가 지금보다 더 작으면 갱신.
                if (!visit[j] && G[minIdx][j] != M && D[j] > D[minIdx] + G[minIdx][j]) {
                    D[j] = D[minIdx] + G[minIdx][j];
                }
            }
        }

        System.out.println(Arrays.toString(D));
    }
}
