package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 29/03/2019
 * 정렬 : 수 정렬하기 3
 * 정렬을 하기 보다는 배열의 인덱스를 관리함으로써
 * 정렬하듯이 출력한다.
 * 시간 : 1500ms 정도 걸림.
 */
public class BOJ10989_Other {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[10001]; // 숫자의 범위 : 10000 이하.

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[num] += 1;
        }

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > 0) {
                sb.append(i).append(NEW_LINE);
                arr[i]--;
            }
        }

        System.out.println(sb.toString());

    }
}
