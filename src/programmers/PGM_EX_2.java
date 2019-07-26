package programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 06/04/2019
 * 문제는 팰린드롬의 갯수를 구하는 것이다.
 * 팰린 드롬 문제 풀어보기.
 */
public class PGM_EX_2 {
    private static int[] count;
    private static final int ASCII = 48;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        count = new int[input.length()];

        System.out.println(solution(input));

        for (int i : count) {
            System.out.print(i + " ");
        }


    }

    private static int solution(String str) {
        int total = 0;

        // 문자열의 숫자를 count 배열에 담는다.
        // count 배열은 해당 숫자를 인덱스로 삼아 등장한 갯수를 담는다.
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - ASCII;
            count[index]++;
        }

        boolean check = false;

        for (int i = 0; i < count.length; i++) {

            // 갯수를 제거하고 배열에 저장된 값도 감소시킨다.
            while (count[i] > 1) {
                total += 2;
                count[i] -= 2;
            }

            // 0인 원소는 확인하지 않는다.
            if (count[i] == 0) {
                continue;
            } else if (count[i] <= 1) {
                // 1보다 작은 원소 중에서는 팰림드롬을 구성하는 기준이 되는 1개의 수만 더해주고 나머지들은 더하지 않는다.
                if (!check) {
                    total += count[i];
                    check = true;
                }
            }

        }

        // 그러면 팰린드롬을 구성하는 가장 긴 숫자의 길이를 구할 수 있다.
        return total;
    }
}
