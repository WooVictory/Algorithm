package 자료구조;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 09/03/2019
 */
public class BOJ1158 {
    public static void main(String[] args) throws IOException {

        // 입,출력.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 리스트 사용.
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // StringBuilder를 사용하여 문자열 처리.
        StringBuilder sb = new StringBuilder("<");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()) - 1;

        // 삭제할 인덱스.
        int kill_index = 0;

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        while (n-- > 0) {
            kill_index += m;

            // 인덱스가 리스트의 사이즈보다 클 경우 원으로 이루어진 것처럼 앞으로 인덱스를 당겨온다.
            if (kill_index >= list.size()) {
                kill_index %= list.size();
            }

            sb.append(list.remove(kill_index)).append(", ");
        }


        bw.write(sb.toString().substring(0, sb.length() - 2) + ">");
        bw.flush();
        bw.close();
        br.close();

    }
}
