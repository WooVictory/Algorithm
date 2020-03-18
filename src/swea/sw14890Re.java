package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/18
 * 경사로.
 * 삼성 기출.
 * 시뮬레이션.
 */
public class sw14890Re {
    private static int N, L, result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        L = toInt(in[1]);

        // 가로, 세로를 처리하기 위해서 2개의 배열을 사용한다.
        int[][] map = new int[N][N];
        int[][] map2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            // 가로, 세로를 처리하기 위해 i, j 의 위치를 바꿔 값을 저장한다.
            // 이유는 동일한 로직을 사용하기 위해서다.
            for (int j = 0; j < N; j++) map[i][j] = map2[j][i] = toInt(in[j]);
        }

        for (int i = 0; i < N; i++) {
            solve(i, map);
            solve(i, map2);
        }

        System.out.println(result);
    }

    private static void solve(int index, int[][] a) {
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            // 높이 차이가 없다면 그 다음 정점을 검사한다.
            if (a[index][i] == a[index][i + 1]) continue;

            // 두 정점 간의 높이 차이를 계산한다.
            // 높이 차이가 1과 -1이 아니면 경사로를 만들 수 없으므로 return.
            int diff = a[index][i] - a[index][i + 1];
            if (diff != 1 && diff != -1) return;

            if (diff == 1) {
                // 오른쪽 경사로.
                for (int j = 1; j <= L; j++) {
                    // 범위를 벗어나거나 이미 방문해서 경사로를 만든 적이 있다면 return.
                    if (i + j >= N || visit[i + j]) return;

                    // diff = 1일 때는 오른쪽 경사로를 만든다.
                    // 그리고 이때의 정점은 자기 자신이 높은 위치의 값을 갖는다.
                    // 오른쪽으로 L 길이만큼 평평한지 확인을 하는 과정에서 자기 자신과 높이 차이가 1인지도 함께 확인을 한다.
                    // 높이의 차이가 1이 아니면 평평하지 않음을 의미하기 때문에 return.
                    if (a[index][i] - 1 == a[index][i + j]) visit[i + j] = true;
                    else return;
                }

            } else {
                // 왼쪽 경사로.
                for (int j = 0; j < L; j++) {
                    // 범위를 벗어나거나 이미 방문하여 경사로를 만든 적이 있다면 return.
                    if (i - j < 0 || visit[i - j]) return;

                    // diff = -1일 때는 왼쪽 경사로를 만든다.
                    // 그리고 이때의 정점은 바로 오른쪽에 자기 자신보다 높이가 1 높은 위치를 갖는다.
                    // 따라서 자기 자신은 오른쪽 보다 1 작은 높이를 갖는다.
                    // 왼쪽으로 L 길이만큼 평평한지 확인을 해야 하기 때문에 i - j를 해준다.
                    // 평평하다면 경사로를 이용해 길을 만들 수 있기 때문에 visit[] 배열에 체크해준다.
                    // 평평하지 않다면 경사로를 이용해 길을 만들 수 없기 때문에 return.
                    if (a[index][i] == a[index][i - j]) visit[i - j] = true;
                    else return;
                }
            }
        }
        // 무사히 여기까지 왔다면 길을 만들 수 있음을 의미한다.
        // 따라서 result 를 증가시킨다.
        result++;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
