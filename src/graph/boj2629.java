package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/04
 * 양팔 저울
 * 다시 풀어보기!
 */
public class boj2629 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());
        int[] weight = new int[n];

        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) weight[i] = toInt(in[i]);

        // 추의 갯수 * 추의 최대 무게
        int max = 30 * 500;
        // check : 실제로 추로 만들 수 있는 무게에 대하여 무게값을 index 로 하여 true, false 값을 가지고 있는 배열
        // 추로 만들 수 있는 값에 대해서는 true, 아니면 false
        boolean[] check = new boolean[max + 1];

        // 임시 저장을 위한 배열이다. check 배열에 값을 저장해주기 위한 배열.
        boolean[] save = new boolean[max + 1];

        check[0] = true;
        save[0] = true;

        int left;
        int right;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= max; j++) {
                if (check[j]) {
                    left = Math.abs(weight[i] - j);
                    right = Math.abs(weight[i] + j);
                    save[left] = true;
                    save[right] = true;
                    save[weight[i]] = true;
                }
            }
            // check <- save 복사.
            System.arraycopy(save, 0, check, 0, max + 1);
        }

        int m = toInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            int value = toInt(input[i]);
            // 입력 받은 구슬의 무게가 max 보다 작거나 같고
            // check 배열을 통해 확인해서 구슬이 추를 이용해서 만들 수 있는 무게가 되는지 확인한다.
            // value <= max 가 없으면 런타임 에러가 난다.
            // 이유는 구슬의 최대 값이 추의 최대값보다 크기 때문...!
            if (value <= max && check[value]) {
                System.out.print("Y ");
            } else {
                System.out.print("N ");
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
