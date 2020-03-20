package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/20
 * 사다리 조작.
 * 삼성 기출.
 * 조합.
 * 다시 풀어보기!
 */
public class sw15684Re {
    private static int n, m, h, result = Integer.MAX_VALUE;
    private static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        h = toInt(in[2]);

        // h의 최댓값 : 30, n의 최댓값 : 10이기 때문에 아래와 같이 초기화 한다.
        // 가로선을 그을 수 있는 곳의 시작점에는 1을 도착점에는 2를 놓는다.
        // 이유는 1,2를 통해서 방향을 바꾸기 위함이다.
        // 1 : 오른쪽, 2 : 왼쪽.
        ladder = new int[30][10];
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            int a = toInt(in[0]), b = toInt(in[1]);
            ladder[a - 1][b - 1] = 1;
            ladder[a - 1][b] = 2;
        }

        int answer = solve(0, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    // 재귀 호출을 사용한다.
    private static int solve(int position, int count) {
        // 종료 조건이다.
        // count == 3이거나 position 이 h*n의 범위를 벗어나면 전부 탐색했으므로 종료 조건이다.
        if (count == 3 || position >= h * n) {
            // check() 함수를 통해서 유효성 검사를 하고 true 라면 count 반환.
            // 그렇지 않다면 Integer.MAX_VALUE 반환.
            if (check()) {
                return count;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        // position 을 열의 갯수 즉, n으로 나누어서 row, col 을 구한다.
        int row = position / n;
        int col = position % n;

        // col 이 마지막 좌표까지 가지 않고, ladder 2차원 배열의 값이 0이면 가로선을 그을 수 있다.
        // 따라서 [row][col], [row][col+1]이 모두 0이면 그을 수 있으므로 위와 같이 시작점 : 1, 도착점 : 2를 놓는다.
        if (col != n - 1 && ladder[row][col] == 0 && ladder[row][col + 1] == 0) {
            ladder[row][col] = 1;
            ladder[row][col + 1] = 2;
            // 한번에 2개에 값을 놓았기 때문에 position+1이 아닌 position+2를 넘겨준다. +1을 해봤자 이미 위에서 가로선을 그은 끝 부분과
            // 겹치기 때문에 불필요한 부분이다. 그리고 가로선을 하나 그었으므로 count+1을 넘기며 solve()를 호출하고, 이 함수가 반환하는 값과
            // result 값을 비교하여 작은 수를 result 변수에 갱신시켜준다.
            result = Math.min(result, solve(position + 2, count + 1));

            // 재귀 함수 호출이 끝나고 돌아왔을 때, 이 값을 초기화해줘야 한다.
            // 이유는 아래 쪽에서 해당 정점을 선택하지 않는 재귀 함수 호출을 했을 때는 그 정점을 보고 방문할 수 있어야 하기 때문이다.
            ladder[row][col] = 0;
            ladder[row][col + 1] = 0;
        }

        // 다음 자리에 대해서 가로선을 긋는 걸 선택하지 않는 경우.
        // 그래서 position+1, count 를 넘기는 것이다.
        // 다음 position 을 선택하고 count 는 유지한다.
        result = Math.min(result, solve(position + 1, count));
        return result;
    }

    // 각각의 열에 대해서 사다리를 타고 출발하여 도착점에 도달했을 때, 출발점과 같은지 다른지 판단한다.
    private static boolean check() {
        for (int i = 0; i < n; i++) {
            // 열을 바꿔가며, 첫 행부터 탐색을 시작한다.
            int row = 0, col = i;
            do {
                // 오른쪽이라면 col 을 증가시켜 우측으로 이동한다.
                // 왼쪽이라면 col 을 감소시켜 좌측으로 이동한다.
                if (ladder[row][col] == 1) col++;
                else if (ladder[row][col] == 2) col--;

                // 우측으로 이동하건 좌측으로 이동하건 무조건 아래로 한칸 이동한다.
                row++;
            } while (row < h);

            // col 이 시작 열이었던 i 와 같은지 비교한다. 다르다면 false 반환.
            if (col != i) return false;
        }

        // 여기까지 왔다는 것은 위의 조건 검사에서 걸리지 않았음을 의미한다.
        // 또한, col == i 임을 의미하기 때문에 시작점과 도착점이 같다. 따라서 사다리 조작이 성공했음으로 true 반환.
       return true;
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
