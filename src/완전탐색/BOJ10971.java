package 완전탐색;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 07/05/2019
 * 완전 탐색 : 외판원 순회 2
 * 모든 순열을 이용해서 푸는 방법.
 */
public class BOJ10971 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][n]; // 각 도시의 비용을 담는 배열.
        int[] distance = new int[n]; // 어떤 도시로 갈지 저장하는 배열.

        // 도시별 비용을 담은 배열 입력.
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 어떤 도시로 갈지 초기화.
        for (int i = 0; i < n; i++) {
            distance[i] = i;
        }

        int answer = Integer.MAX_VALUE;

        do {
            // 다음 도시로 갈 수 있는지 없는지 정함.
            // 0이면 false, 0이 아니면 true.
            boolean possible = true;
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                if (cost[distance[i]][distance[i + 1]] == 0) {
                    possible = false;
                } else {
                    sum = sum + cost[distance[i]][distance[i + 1]];
                }
            }

            // 마지막 도시에서 다시 처음으로 돌아가는 경우 계산.
            if (possible && cost[distance[n - 1]][distance[0]] != 0) {
                sum = sum + cost[distance[n - 1]][distance[0]];
                if (answer > sum) {
                    answer = sum;
                }
            }

        } while (next_permutation(distance));

        bw.write(answer+"");
        bw.flush();
    }

    // 모든 경우의 수를 다해보기 위함.
    private static boolean next_permutation(int[] arr) {
        int i = arr.length - 1;

        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = arr.length - 1;

        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        j = arr.length - 1;

        while (i <= j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return true;

    }
}
