package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/15
 * 주사위 윷놀이.
 * 삼성 기출.
 */
public class Problem17825_2 {
    private static int[] permutation, step, now;
    private static boolean[] check;
    private static Node[] map;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        map = new Node[43];
        permutation = new int[10];
        step = new int[10];

        for (int i = 0; i < 10; i++) step[i] = Integer.parseInt(in[i]);

        initDice();
        getPermutation(0);
        System.out.println(max);
    }

    private static void initDice() {
        // 가장 바깥 라인은 2의 배수로 이루어져 있다.
        for (int i = 0; i <= 40; i += 2) map[i] = new Node(i, i + 2);

        // 파란색 칸인 부분을 체크해준다.
        map[10].isBlue = map[20].isBlue = map[30].isBlue = true;

        // 파란색 칸은 파란색 화살표로 이동해야 하며, 파란색 화살표로 이어진 다음 칸으로 연결한다.
        map[10].blue = 11;
        map[20].blue = 17;
        map[30].blue = 31;

        // 파란색 화살표를 통해 이동할 수 있는 칸들은 칸의 값을 바꿔준다.
        // 이유는 바깥 라인의 짝수와 겹치는 부분이 있기 때문에 구분하기 위함이다.
        // 해당 칸의 점수와 다음 red 좌표를 저장한다.
        map[11] = new Node(13, 13);
        map[13] = new Node(16, 15);
        map[15] = new Node(19, 25);

        map[17] = new Node(22, 19);
        map[19] = new Node(24, 25);

        map[25] = new Node(25, 37);

        map[31] = new Node(28, 33);
        map[33] = new Node(27, 35);
        map[35] = new Node(26, 25);

        map[37] = new Node(30, 39);
        map[39] = new Node(35, 40);

        // 마지막에 도착 지점인 40이 갈 곳을 정해준다.
        // 이로 인해서 마지막에는 무한 루프를 형성한다. 도착 지점 처리를 위함.
        map[42] = new Node(0, 42);
    }

    // 중복을 허용하는 순열을 구해서 주어진 10개의 주사위 값에 대해서 어느 말이 이동할 지 모두 구한다.
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

    // 순열을 통해 구한 말들을 주어진 주사위 값을 이용하여 이동시킨다.
    // 이동시키면서 구할 수 있는 score 를 더하여 max 와 비교한다.
    // 이 과정을 반복한다.
    private static void move() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            int horsePosition = horseMove(permutation[i], step[i]);
            if (horsePosition == -1) return;
            now[permutation[i]] = horsePosition;
            score += map[horsePosition].score;
        }

        if (max < score) max = score;
    }

    // 말이 step 만큼 칸을 이동한다.
    // now : 4마리의 말이 각각 어느 칸에 위치하는지 말이 존재하는 칸의 인덱스를 저장한다.
    // temp : 현재 말이 있는 칸의 인덱스 정보.말
    // horse : permutation 으로 인해 선택된 말을 의미한다.
    // now[horse] : permutation 으로 인해 선택된 말이 윷놀이 판에서 어느 칸에 위치하는지를 나타낸다.
    private static int horseMove(int horse, int step) {
        // 현재 말이 위치한 칸의 인덱스.
        int temp = now[horse];

        for (int i = 0; i < step; i++) {
            // 말이 위치한 칸의 인덱스를 통해 파란색 칸인지 확인한다.
            // 파란색 칸이라면 파란색 화살표가 가리키는 좌표를 temp 에 저장한다.
            if (i == 0 && map[temp].isBlue) {
                temp = map[temp].blue;
                continue;
            }

            // 파란색 칸이 아니라면 빨간색인 다음 좌표를 temp 에 저장한다.
            temp = map[temp].red;
        }

        // 40보다 작다는 것은 말이 아직 도착 지점에 오지 못했음을 의미한다.
        // 그리고 이미 방문한 적이 있다는 것은 결국, 도착하지 못하면서 방문한 곳을 또 방문하여 해매고 있다.
        // 따라서 이 경우에는 -1을 반환한다.
        if (temp <= 40 && check[temp]) {
            return -1;
        } else {
            // 말의 시작 위치를 false 로 만들고,
            // 말이 이동한 위치를 true 로 만든다.
            // 그리고 말이 이동한 위치 값을 반환한다.
            check[now[horse]] = false;
            check[temp] = true;
            return temp;
        }
    }

    static class Node {
        int score; // 윷놀이 판의 점수.
        int red; // 빨간색 화살표가 가리키는 좌표.
        int blue; // 파란색 화살표가 가리키는 좌표.
        boolean isBlue; // 파란색 칸인지의 여부.

        Node(int score, int red) {
            this.score = score;
            this.red = red;
        }
    }
}
