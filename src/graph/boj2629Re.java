package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/04
 * 양팔 저울.
 */
public class boj2629Re {
    private static final int MAX = 30 * 500;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());
        int[] weight = new int[n];

        // 구슬을 입력받는다.
        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) weight[i] = toInt(in[i]);

        int m = toInt(br.readLine());

        // check : 구슬을 조합해서 만들 수 있는 모든 무게에 대해서 true 값을 저장한다.
        // 무게 => index 가 된다.
        boolean[] check = new boolean[MAX + 1];

        // temp : check 에 저장하기 위한 임시 배열.
        // for 문에서 check 만을 사용하게 된다면 두 번 계산되기 때문에 temp 배열을 사용한다.
        boolean[] temp = new boolean[MAX + 1];

        check[0] = true;
        temp[0] = true;

        int left;
        int right;

        // 각각의 추에 대해서 0~MAX 까지의 무게를 더하거나 빼면서 left, right 를 구한다.
        // 그리고 구한 값을 temp 에 저장한다.
        // 여기서 구한 값은 추의 조합으로 만들 수 있는 무게를 의미한다.
        // 만들 수 있는 무게가 index 가 된다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= MAX; j++) {
                if (check[j]) {
                    left = Math.abs(weight[i] - j);
                    right = Math.abs(weight[i] + j);
                    temp[left] = true;
                    temp[right] = true;
                    temp[weight[i]] = true;
                }
            }

            // temp -> check 배열에 복사한다.
            System.arraycopy(temp, 0, check, 0, MAX + 1);
        }

        // 구슬에 대한 정보를 입력받는다.
        in = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            int value = toInt(in[i]);

            // 구슬의 무게가 추의 최대값보다 작거나 같아야 한다.
            // 이유는 구슬의 최대값이 40,000이고 추의 최대 값이 15,000이기 때문이다.
            // 그리고 구슬의 무게인 value 가 check 배열에서 true 값을 가지면 Y 아니면 N 을 찍는다.
            // 이것은 추의 조합으로 구슬의 무게를 알아낼 수 있음을 의미한다.
            if (value <= MAX && check[value]) System.out.print("Y ");
            else System.out.print("N ");
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}