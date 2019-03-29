package 정렬;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 29/03/2019
 * 정렬 : 카드
 * 정렬은 먼저 한 후에 같은 숫자의 개수를 확인해준다.
 * 그리고 이전에 등장한 숫자의 개수를 저장해서 현재 들어온 카드 숫자의 개수와 비교하면 된다.
 */
public class BOJ11652 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
        }

        // 정렬을 먼저 한다.
        Arrays.sort(arr);

        long answer = arr[0];
        int before_count = 1; // 이전에 등장한 숫자 카드의 개수를 관리한다.
        int count = 1; // 현재 등장한 숫자 카드의 개수를 관리한다.

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                count += 1;
            } else {
                count = 1;
            }

            if (before_count < count) {
                before_count = count;
                answer = arr[i];
            }
        }

        bw.write(answer + "");

        bw.flush();
        bw.close();
        br.close();

    }
}
