package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/02/28
 * 스도쿠
 * dfs, 백트래킹.
 * 다시 풀어보기!
 */
public class boj2580 {
    private static int n = 9;
    private static int[][] map = new int[n][n];
    private static ArrayList<Node> zeroList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(in[j]);
            }
        }

        // 스도쿠가 놓여있지 않은 0인 곳을 zeroList 에 추가한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) zeroList.add(new Node(i, j));
            }
        }

        // 0번째 인덱스부터 탐색을 시작한다.
        dfs(0);

    }

    private static void dfs(int index) {
        // 스도쿠를 채우는 방법이 여러 개 존재하더라도 하나만 출력하기 위해서
        // 0인 곳의 갯수만큼 dfs 탐색을 하면 결과를 출력하고 프로그램을 종료한다.
        if (index == zeroList.size()) {
            print();
            System.exit(0);
        }

        // 0이 존재하는 곳의 (x,y) 좌표를 얻는다.
        int x = zeroList.get(index).x;
        int y = zeroList.get(index).y;

        // 1~9까지 스도쿠 판에 놓아본다.
        for (int i = 1; i <= 9; i++) {
            // i 를 index 자리에 놓을 수 있는지 확인한다.
            if (isSafe(i, index)) {
                // i 를 스도쿠 판에 놓는다.
                map[x][y] = i;

                // 그리고 다음 인덱스에 대해 조사한다.
                // 이 인덱스는 사전에 스도쿠 판에서 0인 곳에 대해서 조사했다.
                // 그래서 차근 차근 0번째 인덱스부터 시작하여 0인 곳에 스도쿠를 채우기 위해서 이를 기준으로 돌리는 것이다.
                dfs(index + 1);
            }

            // 스도쿠 배열을 결국 다시 초기화 해줘야 한다.
            // 왜냐하면 첫 번째 진행한 방법에서 답을 못찾았을 수도 있다.
            // 그러면 뒤로 돌아와서 초기화한 뒤, 다시 진행해야 하기 때문에 초기화해야 한다.
            // 백트래킹.
            if (i == 9) {
                map[x][y] = 0;
            }
        }
    }

    // value 라는 값을 스도쿠 판에 놓을 수 있는지 검사해야 한다.
    private static boolean isSafe(int value, int index) {
        return checkRow(value, index) && checkCol(value, index) && checkBox(value, index);
    }

    // 가로 행에 대해서 유효성을 검사한다.
    private static boolean checkRow(int value, int index) {
        int x = zeroList.get(index).x;
        int y = zeroList.get(index).y;
        for (int i = 0; i < 9; i++) {
            // 0인 y 좌표가 i와 같으면 즉, 빈 칸의 좌표인 경우에는 건너뛴다.
            if (y == i) continue;

            // x행에 대해 i 즉, 열을 바꿔가며 value 와 같다면 false
            // 즉, 같은 행에 대해서 동일한 값이 존재한다는 의미이다.
            if (map[x][i] == value) return false;
        }
        // 없다면 true 반환.
        return true;
    }

    // 세로 열에 대해서 유효성을 검사한다.
    private static boolean checkCol(int value, int index) {
        int x = zeroList.get(index).x;
        int y = zeroList.get(index).y;
        for (int i = 0; i < 9; i++) {
            // 0인 x 좌표가 i와 같으면 즉, 빈 칸의 좌표인 경우에는 건너뛴다.
            if (x == i) continue;

            // y열에 대해서 i 즉, 행을 바꿔가며 value 와 같은 값이 있다면 false
            // 즉, 같은 열에 대해서 동일한 값이 존재한다는 의미이다.
            if (map[i][y] == value) return false;
        }
        // 없다면 true 반환.
        return true;
    }

    // 3x3 박스에 대해서 유효성을 검사한다.
    private static boolean checkBox(int value, int index) {

        // (0,0)이 빈칸인 경우 -> (0,0) ~ (2,2)까지 검사해야 한다.
        // (1,4)이 빈칸인 경우 -> (0,3) ~ (2,5)까지 검사해야 한다.
        // 검사하기 위해 a,b를 구한다.
        int a = zeroList.get(index).x / 3;
        int b = zeroList.get(index).y / 3;
        a = a * 3;
        b = b * 3;

        for (int i = a; i < a + 3; i++) {
            for (int j = b; j < b + 3; j++) {
                int x = zeroList.get(index).x;
                int y = zeroList.get(index).y;
                // 빈칸 인 경우의 좌표는 건너뛴다.
                if (x == i && y == j) continue;

                // value 와 동일한 값이 존재한다면 false
                if (map[i][j] == value) return false;
            }
        }
        // 없다면 true 반환.
        return true;
    }

    // 결과 출력.
    private static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
