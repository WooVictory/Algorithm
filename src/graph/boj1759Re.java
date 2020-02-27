package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/02/27
 */
public class boj1759Re {
    private static int L, C;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        L = Integer.parseInt(in[0]); // 암호의 길이.
        C = Integer.parseInt(in[1]); // 주어진 알파벳의 갯수.

        // 주어진 알파벳을 입력받고, 정렬시킨다.
        String[] alpha = br.readLine().split(" ");
        Arrays.sort(alpha);

        // dfs 탐색을 시작한다.
        // 시작이기 때문에 암호를 빈 문자열이고, 0번째 인덱스 즉, 첫 번째 알파벳부터 암호를 만들기 시작한다.
        dfs(alpha, "", 0);
        System.out.println(sb.toString());
    }

    private static void dfs(String[] alpha, String password, int index) {
        // 암호의 길이와 L의 길이가 같으면 암호를 L 길이만큼 완성했음을 의미한다.
        if (password.length() == L) {
            // 이때의 password 가 조건을 만족하는지 검사한다.
            if (isPossible(password)) {
                // 만족한다면 sb 객체에 추가한다.
                sb.append(password).append("\n");
            }
            // return 을 하지 않으면 중복되서 문자열이 찍힌다.
            // 이유는 아래에서 dfs 를 포함하는 경우와 포함하지 않는 경우 두 가지로 호출하기 때문!
            return;
        }

        // 다음을 찾기 위한 index 가 C(주어진 알파벳의 갯수) 보다 크거나 같으면 종료하다.
        if (C <= index) return;

        // 다음 알파벳 문자를 포함하는 암호를 만들어서 탐색하는 경우.
        dfs(alpha, password + alpha[index], index + 1);

        // 다음 알파벳 문자를 포함하지 않는 암호를 만들어서 탐색하는 경우.
        dfs(alpha, password, index + 1);
    }

    // 자음과 모음의 갯수를 확인하는 함수.
    private static boolean isPossible(String password) {
        int moCnt = 0, jaCnt = 0;
        for (int i = 0; i < password.length(); i++) {
            if (isMo(password.charAt(i))) moCnt++;
            else jaCnt++;
        }
        return moCnt >= 1 && jaCnt >= 2;
    }

    // 해당 문자가 모음이 맞는지 확인하는 함수.
    private static boolean isMo(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
