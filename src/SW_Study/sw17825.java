package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/14
 * 주사위 윷놀이.
 * 삼성 기출.
 * 이 구현의 정확한 방법을 모르겠돠...
 */
public class sw17825 {
    private static int max;
    private static int[] permutation, step, now;
    private static boolean[] check;
    private static Node[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        map = new Node[43];
        permutation = new int[10];
        step = new int[10];

        for (int i = 0; i < 10; i++) step[i] = toInt(in[i]);

        // 미리 짝수번째 숫자들에 대해서는 해당 칸의 점수와 다음 빨간 화살표를 지정해준다.
        for (int i = 0; i <= 40; i += 2) map[i] = new Node(i, i + 2);

        setDice();
        permutation[0] = 0;
        calculatePermutation(9, 0);
        System.out.println(max);
    }

    private static void setDice() {
        map[10].isBlue = map[20].isBlue = map[30].isBlue = true;
        map[10].blue = 11;
        map[20].blue = 17;
        map[30].blue = 32;

        // 파란 점이 있는 경우에 대해서 isBlue 값을 업데이트하고, 파란 점인 경우 화살표의 다음 점을 연결한다.

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

        map[42] = new Node(0, 42); // 도착점을 처리하기 위함.
    }


    // 중복을 허용하는 순열.
    // 주사위를 굴렸을 때 나오는 10개의 수를 미리 알고 있다.
    // 따라서 주사위를 던진 경우에 따라서 1~4번 말 중 어떤 말을 이동시킬 것인지 골라야 한다.
    // 10번 동안 1번 말을 계속 이동시켜도 되고, 번갈아 가며 이동시켜도 된다.
    // 따라서 중복을 허용하는 순열을 통해 어떤 말이 이동할 지 구한다.하
    // now : 인덱스 별로 4마리의 말을 의미한다.
    // 인덱스의 말이 윷놀이 판의 어느 칸에 있는지 저장한다.
    private static void calculatePermutation(int n, int k) {
        // 중복을 허용해서 n개 만큼 순열을 만든 경우.
        if (n == k) {
            now = new int[4];
            check = new boolean[43];
            move();
            return;
        }

        for (int i = 0; i < 4; i++) {
            permutation[k] = i;
            calculatePermutation(n, k + 1);
        }
    }

    //
    private static void move() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            int end = moveHorse(permutation[i], step[i]);
            if (end == -1) return;
            now[permutation[i]] = end;
            score += map[end].score;
        }

        if (max < score) max = score;
    }

    private static int moveHorse(int horse, int step) {
        int temp = now[horse];
        for (int i = 0; i < step; i++) {
            if (i == 0 && map[temp].isBlue) {
                temp = map[temp].blue;
                continue;
            }

            // 말이 윷놀이 판 위를 이동
            temp = map[temp].red;
        }

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
        int score; // 해당 칸의 점수.
        int red; // 빨간 화살표로 이동할 경우, 다음 점.
        int blue; // 파란 화살표로 이동할 경우, 다음 점.
        boolean isBlue; // 파란 점인지 여부.

        Node(int score, int red) {
            this.score = score;
            this.red = red;
        }
    }
}
