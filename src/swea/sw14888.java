package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/16
 * 연산자 끼워넣기
 * dfs
 * 다시 풀어보기!
 */
public class sw14888 {
    private static int n;
    private static int[] map;
    private static int[] operators;
    // 연산자는 다음과 같은 순서로 동작이 정의된다.
    // 0 : +, 1 : -, 2 : x, 3 : /
    private static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        n = toInt(br.readLine());

        map = new int[n];
        operators = new int[4];
        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) map[i] = toInt(in[i]); // 숫자를 배열에 저장.

        in = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) operators[i] = toInt(in[i]); // 연산자의 갯수를 배열에 저장.

        dfs(map[0], 1);
        System.out.println(Collections.max(result) + "\n" + Collections.min(result));
    }

    private static void dfs(int sum, int index) {
        // 연산자의 갯수는 4개이기 때문에 총 4개의 연산자가 숫자 뒤에 와서 다음 숫자와 결합할 수 있다.
        // 인덱스가 연산자의 동작을 나타낸다.
        for (int i = 0; i < 4; i++) {
            // 해당 연산자가 주어지지 않았음을 의미한다.
            if (operators[i] == 0) continue;

            // 연산자를 사용하기 때문에 갯수를 감소시켜 준다.
            operators[i]--;
            switch (i) {
                case 0: // +
                    dfs(sum + map[index], index + 1);
                    break;
                case 1: // -
                    dfs(sum - map[index], index + 1);
                    break;
                case 2: // x
                    dfs(sum * map[index], index + 1);
                    break;
                case 3: // /
                    dfs(sum / map[index], index + 1);
                    break;
            }

            // 끝나고 돌아왔을 때, 다른 dfs 에서 사용하기 위해서 초기화를 해준다.
            operators[i]++;
        }

        // index == n인 경우는 마지막을 호출하고 위의 for 반복문에는 걸리지 않는다.
        // 이유는 이미 연산자를 다 사용했기 때문에 index 가 n까지 도달한 것이다.
        // 이 경우, 결과를 result list 에 추가하여 준다.
        if (index == n) {
            result.add(sum);
        }
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
