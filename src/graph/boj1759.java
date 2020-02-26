package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/02/26
 * 암호 만들기.
 * dfs, 백트래킹.
 */
public class boj1759 {
    private static int l, c;
    private static char[] a;
    private static boolean[] visit;
    private static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        l = toInt(in[0]); // 암호의 길이.
        c = toInt(in[1]); // 알파벳의 갯수.

        a = new char[c];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < c; i++) {
            a[i] = s[i].charAt(0);
        }

        // 정렬해준다.
        Arrays.sort(a);

        for (int i = 0; i <= c - l; i++) {
            visit = new boolean[26];
            // i : 첫번째 정점부터 시작함을 의미. 여기서는 a부터 시작.
            // 1 : count 를 의미한다. 몇개의 암호를 만들었는지 판단.
            // password : 암호를 만들면서 dfs 탐색을 한다.
            dfs(i, 1, "" + a[i]);
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int v, int count, String password) {
        int index = a[v] - 'a';
        visit[index] = true;
        // 알파벳은 인덱스화 시켜 방문 여부를 체크한다.
        // 중복이 안되기 때문.

        // count == l : 암호의 갯수인 4만큼 dfs 탐색을 했음을 의미한다.
        // 따라서 암호를 완성했음을 나타낸다.
        // isPossible()이 true 일 경우, sb 객체에 암호를 추가한다.
        if (count == l) {
            if (isPossible()) sb.append(password).append("\n");
        } else {
            // 다음 정점부터 다시 시작한다.
            // 인덱스를 구해서 해당 알파벳이 이전에 방문한 적이 있는지 조사하고 없다면 dfs 탐색을 한다.
            for (int i = v + 1; i < c; i++) {
                int newIndex = a[i] - 'a';
                if (!visit[newIndex]) {
                    dfs(i, count + 1, password + a[i]);
                }
            }
        }

        // 백트래킹.
        // 탐색이 끝나고 돌아와서 이전 상태를 초기화 해준다.
        // 이로 인해서 다른 정점으로의 방문이 가능하다.
        visit[index] = false;
    }

    // 모음 : a,e,i,o,u(0,4,8,14,20)
    private static boolean isPossible() {
        int moCnt = 0; // 모음의 개수.
        int jaCnt = 0; // 자음의 개수.

        if (visit[0]) moCnt++;
        if (visit[4]) moCnt++;
        if (visit[8]) moCnt++;
        if (visit[14]) moCnt++;
        if (visit[20]) moCnt++;

        for (int i = 0; i < 26; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 14 || i == 20) continue;

            if (visit[i]) jaCnt++;
        }

        return moCnt >= 1 && jaCnt >= 2;
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

}
