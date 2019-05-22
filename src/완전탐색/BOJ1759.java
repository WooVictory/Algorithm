package 완전탐색;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 21/05/2019
 * 완탐 : 암호 만들기
 * 정렬되어야 한다. 따라서, 먼저 정렬을 한다.
 * 다음, 경우를 나눠서 dfs 재귀 함수를 작성한다.
 * 최소 모음 한개와 최소 자음 두개를 포함해야 하는지 확인 한다.
 * 브루트 포스 방법으로 모든 경우를 다 확인해 보는 방법으로 재귀 호출을 사용한 완전 탐색이다.
 */
public class BOJ1759 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int L = parse(input[0]);
        int C = parse(input[1]);

        String[] alpha = br.readLine().split(" ");
        Arrays.sort(alpha); // 정렬.

        dfs(L, alpha, "", 0);
        bw.flush();

    }

    private static void dfs(int length, String[] alpha, String password, int index) throws IOException {
        // 정답을 찾은 경우.
        if (password.length() == length) {
            if (check(password)) {
                bw.write(password + "\n");
            }
            return;
        }

        // 더 이상 사용할 수 있는 알파벳이 없는 경우.
        if (index >= alpha.length) {
            return;
        }

        // 사용하는 경우.
        dfs(length, alpha, password + alpha[index], index + 1);
        // 사용하지 않는 경우.
        dfs(length, alpha, password, index + 1);
    }

    // 만들어진 암호에서 모음과 자음의 개수를 체크하기 위한 함수.
    private static boolean check(String password) {
        int ja = 0;
        int mo = 0;
        for (char x : password.toCharArray()) {
            // 모음 체크.
            if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                mo++;
            } else {
                ja++;
            }
        }

        return ja >= 2 && mo >= 1;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
