package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/16
 * 연산자 끼워넣기
 * 삼성 기출.
 * dfs
 */
public class sw14888Re {
    private static int n;
    private static int[] map;
    private static int[] operators = new int[4];
    private static ArrayList<Integer> list = new ArrayList<>();

    private static char[] mh; // 어떤 연산자와 결합되는 지 확인하기 위한 함수.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n];
        mh = new char[n];
        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) map[i] = toInt(in[i]);

        in = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) operators[i] = toInt(in[i]);

        // 첫 번째 숫자와 다음 숫자를 위한 index 를 함께 넘긴다.
        dfs(map[0], 1);

        // 최댓값과 최소값을 출력한다.
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
    }

    /*
    * 인덱스가 연산자의 동작을 의미한다.
    * 0 : +
    * 1 : -
    * 2 : *
    * 3 : /
    * */
    private static void dfs(int sum, int index) {
        // 적용할 수 있는 연산자의 갯수는 +,-,*,/ 총 4가지이다.
        // 이 네가지 중 operators 배열은 각각 몇개를 가지고 있을 수 있으며, operators 배열에 저장된
        // 연산자를 꺼내 숫자와 결합한 뒤, dfs 를 재귀 호출한다.
        for (int i = 0; i < 4; i++) {
            // 0이 의미하는 바는 해당 인덱스에 맞는 연산자가 존재하지 않음을 나타낸다.
            if (operators[i] == 0) continue;

            // 연산자를 사용하기 때문에 하나 감소시켜 준다.
            operators[i]--;

            // 인덱스에 따라서 연산자를 분기하여 연산을 적용한 결과를 만들어 dfs 를 호출한다.
            switch (i) {
                case 0: // +
                    mh[index - 1] = '+';
                    dfs(sum + map[index], index + 1);
                    break;
                case 1: // -
                    mh[index - 1] = '-';
                    dfs(sum - map[index], index + 1);
                    break;
                case 2: // *
                    mh[index - 1] = '*';
                    dfs(sum * map[index], index + 1);
                    break;
                case 3: // /
                    mh[index - 1] = '/';
                    dfs(sum / map[index], index + 1);
                    break;
            }

            // dfs()가 끝나고 돌아와서는 다른 연산자를 사용할 수 있기 때문에
            // 백트래킹을 통해 초기화 해준다.
            mh[index - 1] = ' ';
            operators[i]++;
        }

        // index 가 n이 된 것은 operators 배열에 저장된 연산자를 다 사용한 경우에 도달한다.
        // 즉, 연산자를 모두 사용했기 때문에 위의 for 반복문에 걸리지 않고 아래의 if 문을 확인할 것이다.
        // 그렇기 때문에 결과 값을 만들었으며, list 에 결과를 저장한다.
        if (n == index) {
            for (int i = 0; i < n; i++) {
                System.out.print(map[i] + " ");
                System.out.print(mh[i] + " ");
            }

            System.out.println("= " + sum);
            list.add(sum);
        }
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
