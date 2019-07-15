package 백트래킹;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 15/07/2019
 * 암호 만들기.
 */
public class BOJ1759 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final String SPACE = " ";
    private static String[] alphabet;
    private static int l;

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(SPACE);
        l = parse(in[0]);

        alphabet = br.readLine().split(SPACE);
        Arrays.sort(alphabet);

        // 암호를 만들지 않았으므로, 빈 문자열과 0번째 인덱스부터 시작한다.
        dfs("", 0);
        bw.flush();
    }

    private static void dfs(String password, int index) throws IOException {

        // 1. 먼저, 비밀번호의 길이가 만들려는 길이와 맞는지 확인한다.
        if (password.length() == l) {
            // 2. 비밀번호가 조건을 만족하는지 검사한다.
            // 모음 1개 이상, 자음 2개 이상.
            if (isPossible(password)) {
                bw.write(password + "\n");
            }
            return;
        }

        // 3. 탐색을 계속 진행하면서 원하는 문자열을 못 찾고
        // 알파벳 배열의 길이를 벗어나면 종료한다.
        if (index >= alphabet.length) {
            return;
        }

        // 5. 다음 알파벳을 사용하는 경우, dfs 탐색 진행.
        // 알파벳을 추가해 비밀번호를 만들고, 다음 인덱스 탐색을 진행한다.
        dfs(password + alphabet[index], index + 1);
        // 6. 다음 알파벳을 사용하지 않는 경우, dfs 탐색 진행.
        // 알파벳을 추가하지 않고, 다음 인덱스 탐색을 진행한다.
        dfs(password, index + 1);
    }

    // 입력받은 password 문자열에서 자음과 모음의 개수를 확인한다.
    // 조건에 맞으면 true 를 반환한다.
    private static boolean isPossible(String password) {
        int moCount = 0;
        int jaCount = 0;
        for (int i = 0; i < password.length(); i++) {
            switch (password.charAt(i)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    moCount++;
                    break;
                default:
                    jaCount++;
                    break;
            }
        }
        return moCount >= 1 && jaCount >= 2;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}