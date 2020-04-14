package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/15
 * 주사위 윷놀이.
 * 삼성 기출.
 * 시뮬레이션.
 */
public class sw17825Re {
    private static final int TEN = 10;
    private static int[] permutation, now, step;
    private static boolean[] check;
    private static Node[] map;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        permutation = new int[TEN];
        step = new int[TEN];
        map = new Node[43];

        for (int i = 0; i < TEN; i++) step[i] = toInt(in[i]);
        setDice();
        getPermutation(0);
        System.out.println(max);
    }

    // 파란 점이 있는 부분에 대해서 처리한다.
    // 바깥 라인과의 중복된 값이 존재하기 때문에 인덱스를 다르게 주었고, 값은 그대로 저장했다.
    // 또한, 빨간 점으로 된 다음 좌표를 저장한다.
    private static void setDice() {
        for (int i = 0; i <= 40; i += 2) map[i] = new Node(i, i + 2);

        map[10].isBlue = map[20].isBlue = map[30].isBlue = true;

        map[10].setBlue(11);
        map[20].setBlue(17);
        map[30].setBlue(31);

        map[11] = new Node(13, 13);
        map[13] = new Node(16, 15);
        map[15] = new Node(19, 25);

        map[17] = new Node(22, 19);
        map[19] = new Node(24, 25);

        map[31] = new Node(28, 33);
        map[33] = new Node(27, 35);
        map[35] = new Node(26, 25);

        map[25] = new Node(25, 37);

        map[37] = new Node(30, 39);
        map[39] = new Node(35, 40);

        map[42] = new Node(0, 42);
    }

    private static void getPermutation(int depth) {
        if (depth == 10) {
            now = new int[4];
            check = new boolean[43];
            move();
            return;
        }

        for (int i = 0; i < 4; i++) {
            permutation[depth] = i;
            getPermutation(depth + 1);
        }
    }

    private static void move() {
        int score = 0;
        for (int i = 0; i < TEN; i++) {
            // 말을 움직여서 말이 도착한 지점의 값을 구한다.
            // 말이 step 만큼 움직이도록 horseMove() 함수를 호출한다.
            // end 값은 말이 주사위 step 만큼 움직이고 난 뒤의 칸.
            int end = horseMove(permutation[i], step[i]);
            if (end == -1) return;
            now[permutation[i]] = end; // 현재 말이 있는 칸을 업데이트 한다.
            score += map[end].score;
        }

        if (max < score) max = score;
    }

    // now : 윷놀이 판 위에서 말이 위치한 인덱스.(말이 있는 윷놀이 판의 인덱스)
    private static int horseMove(int horse, int step) {
        // 몇 번째 말이 움직일 것인지 뽑는다.
        // 즉, 이동할 말을 정한다.
        int temp = now[horse];

        // 말이 step 만큼 움직인다.
        // 첫 좌표이면서 파란 점이라면 파란 점을 temp 에 저장한다.
        // 그게 아니라면 말이 이동할 다음 좌표(빨간 점의 위치)를 찾아 temp 에 저장한다.
        for (int i = 0; i < step; i++) {
            if (i == 0 && map[temp].isBlue) {
                temp = map[temp].blue;
                continue;
            }

            temp = map[temp].red;
        }

        // 도착 지점에 도착하지도 않았는데, 방문한 곳을 또 방문한 경우.
        if (temp <= 40 && check[temp]) {
            return -1;
        } else {
            check[now[horse]] = false;
            check[temp] = true;
            return temp;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int score;
        int blue;
        int red;
        boolean isBlue;

        public Node(int score, int red) {
            this.score = score;
            this.red = red;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }

        public void setRed(int red) {
            this.red = red;
        }

    }
}
