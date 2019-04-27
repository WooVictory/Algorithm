package 완전탐색;

import java.io.*;

/**
 * created by victory_woo on 27/04/2019
 * Review 1107
 * 1. 이동할 채널 channel을 결정한다.
 * 2. +,- 버튼으로만 이동할 수 있는지 계산한다.
 */
public class BOJ1107_Review {
    private static boolean[] brokenRemote = new boolean[10];
    private static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 이동하려는 채널.
        int m = Integer.parseInt(br.readLine()); // 고장난 버튼의 개수.

        // 고장난 버튼이 있을 경우에만.
        if (m != 0) {
            String[] input = br.readLine().split(" "); // 고장난 버튼들.

            // 고장난 버튼 : true
            // 고장나지 않은 버튼 : false
            for (int i = 0; i < m; i++) {
                brokenRemote[Integer.parseInt(input[i])] = true;
            }
        }

        int answer = Math.abs(n - 100); // +,- 버튼을 눌러서 이동할 수 있는 경우.

        for (int i = 0; i <= MAX; i++) {
            int channel = i;
            int len = possible(channel);

            if (len > 0) {
                int pressButton = Math.abs(channel - n);
                if (answer > len + pressButton) {
                    answer = len + pressButton;
                }
            }
        }
        bw.write(answer + "");
        bw.flush();

    }

    // 길이를 리턴한다.
    // 채널을 누른 횟수와 채널의 길이가 같기 때문.

    private static int possible(int channel) {
        // channel이 0이면 0 return.
        if (channel == 0) {
            return 0;
        }

        int len = 0;
        while (channel > 0) {
            // 버튼이 고장났으면 0 return.
            if (brokenRemote[channel % 10]) {
                return 0;
            }

            len += 1;
            channel /= 10;
        }
        // 길이를 리턴.
        return len;
    }
}
