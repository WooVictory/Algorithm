package 완전탐색;

import java.io.*;

/**
 * created by victory_woo on 22/04/2019
 * 완전 탐색 : 리모컨
 */
public class BOJ1107 {
    private static boolean[] broken = new boolean[10];
    // 0 ~ 500,000인 경우와 500,000 ~ 1,000,000인 경우 확인하기 위해서 범위를 늘린다.
    private static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 이동할 채널.
        int error = Integer.parseInt(br.readLine()); // 고장난 채널 수.
        // error 즉, 고장난 버튼의 개수가 없으면 입력받지 않아야 함.
        if (error != 0) {
            String[] input = br.readLine().split(" "); // 고장난 채널들.

            // 고장난 버튼을 확인한다.
            checkBroken(input, error);
        }

        // 리모컨의 숫자 버튼을 누르지 않고, +/- 버튼만을 눌러서 이동하는 횟수를 구한다.
        int answer = Math.abs(N - 100);

        // 이동할 채널 channel를 결정한 다음, 가능하면 버튼을 총 몇 번 눌러야 하는지 결정한다.
        // 가능한 채널을 찾기 위해 모두 다 돈다.
        for (int i = 0; i <= MAX; i++) {
            int channel = i; // 이동할 채널 channel
            int length = isPossible(channel);
            if (length > 0) {
                int press = Math.abs(channel - N); // +,-를 몇 번 눌러야 하는지.
                // 최소값을 찾는다.
                if (answer > length + press) {
                    answer = (length + press);
                    //System.out.println(answer);
                }
            }
        }
        bw.write(answer + "");
        bw.write("");


        bw.flush();
    }

    // 버튼이 망가져 있다면 true, 아니라면 false
    private static void checkBroken(String[] input, int error) {
        for (int i = 0; i < error; i++) {
            broken[parse(input[i])] = true;
        }
    }

    private static int isPossible(int channel) {
        if (channel == 0) {
            return broken[channel] ? 0 : 1;
        }

        int length = 0;
        // channel의 각 자리수에 대해 리모컨의 버튼이 고장났는지 모두 확인한다.
        // 채널의 길이는 채널을 누르는 횟수와 같다.
        while (channel > 0) {
            if (broken[channel % 10]) {
                return 0;
            }
            length = length + 1;
            channel = channel / 10;
        }
        return length;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
