package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/20
 * 사다리 조작.
 * 삼성 기출.
 * 다시 풀어보기!
 */
public class sw15684 {
    private static int n, m, h, result = Integer.MAX_VALUE;
    private static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        h = toInt(in[2]);

        // 각각 문제의 최대값으로 초기화한다.
        ladder = new int[30][10];

        // m 개의 줄로 가로 정보가 주어진다.
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            int a = toInt(in[0]), b = toInt(in[1]);
            ladder[a - 1][b - 1] = 1; // 내려올 때, 오른쪽. RIGHT
            ladder[a - 1][b] = 2; // 내려올 때, 왼쪽. LEFT
        }

        int answer = solve(0, 0);
        // 이 경우 정답을 찾지 못했으므로 -1을 출력한다.
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            // 아닌 경우에는 답을 출력한다.
            System.out.println(answer);
        }

    }

    private static boolean check() {
        for (int i = 0; i < n; i++) {
            int row = 0, col = i;
            do {
                if (ladder[row][col] == 1) {
                    col++;
                } else if (ladder[row][col] == 2) {
                    col--;
                }

                ++row;
            } while (row != h);

            if (col != i) return false;
        }

        // 여기까지 왔다는 것은 위에서 제대로 사다리를 타고 내려왔고, col == i 임을 의미하기 때문에
        // 그렇다면 시작점과 도착점이 같다. 따라서 사다리 조작에 성공했음을 의미하므로 true 반환.
        return true;
    }

    static int solve(int position, int count) {
        // 재귀 호출 종료를 위한 조건 검사를 해야 한다.
        // 3보다 커지면 -1을 출력한다. 최대로 가로선을 넣을 수 있는 게 3개이다.
        // 따라서 3개이면 종료 조건이 된다.
        // 또한, position 값이 n*h 값보다 커지면 종료해야 한다.
        if (count == 3 || position >= n * h) {
            // 이 안으로 들어와서 종료될 조건을 만족한다면,
            // 우리가 구현한 사다리가 조건에 만족하는 지 확인해야 한다.
            // 그 조건은 i번 사다리가 i번 쪽으로 도착하는지 확인해야 한다.
            // 조건을 만족한다면 우리가 구한 count 반환.
            // 그렇지 않다면 큰 값 반환.
            if (check()) {
                return count;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        int row = position / n;
        int col = position % n;

        // col 좌표가 마지막 좌표이면 선을 그을 수 없으므로
        // 마지막 좌표가 아닐 때만 그을 수 있도록 조건을 걸어준다.
        // 그리고 현재 좌표와 바로 오른쪽 좌표가 0이어야 선을 그어서 이을 수 있다.
        if (col != n - 1 && ladder[row][col] == 0 && ladder[row][col + 1] == 0) {
            ladder[row][col] = 1;
            ladder[row][col + 1] = 2;
            // position+1을 하게 되면 어차피 현재 좌표와 다음 좌표에 1과 2을 놓았기 때문에
            // 놓을 수 없다. 따라서 position+2를 통해 선을 그은 좌표를 뛰어 넘도록 한다.
            // solve()를 호출해서 반환하는 값이 작은 값일 것이기 때문에 반환하는 값과 result 를 비교하여
            // 더 작은 값을 result 에 저장한다.
            result = Math.min(result, solve(position + 2, count + 1));

            // ladder 배열은 전역 변수처럼 사용하고 있기 때문에 원래대로 복원해줘야 한다.
            ladder[row][col] = 0;
            ladder[row][col + 1] = 0;

        }

        // 가로선을 긋지 않는 선택.
        // 아무것도 바꾸지 않고 position+1, count 로 호출해준다.
        // 또한, 아래의 호출이 더 작은 값을 반환할 수도 있기 때문에 result 와 비교해준다.

        result = Math.min(result, solve(position + 1, count));
        return result;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
