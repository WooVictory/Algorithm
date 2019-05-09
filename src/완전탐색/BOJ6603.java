package 완전탐색;

import java.io.*;

/**
 * created by victory_woo on 09/05/2019
 * 완전 탐색 : 로또
 * 순열 보다는 DFS 와 백트래킹으로 문제를 푼다.
 */
public class BOJ6603 {
    private static int count = 0;
    private static final int MAX = 6;
    private static StringBuilder sb;
    private static final String NEW_LINE = "\n";
    private static int[] arr;
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            sb = new StringBuilder();
            String[] input = br.readLine().split(SPACE);
            int k = parse(input[0]);

            // 0이 입력으로 들어오면 탈출.
            if (k == 0) break;
            arr = new int[k];

            for (int i = 0; i < k; i++) {
                arr[i] = parse(input[i + 1]);
            }

            for (int i = 0; i < k; i++) {
                count++;
                findLottoNumber(i, String.valueOf(arr[i]));
            }

            bw.write(sb.toString());
        }
        bw.flush();
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static void findLottoNumber(int index, String str) {
        // 6개를 모두 고르면 고른 숫자를 문자열 형태로 만든다.
        if (count == MAX) {
            sb.append(str).append(NEW_LINE);
        }

        // i 번째까지 로또 번호가 만들어졌다면 그 뒤에 붙어야 하는 번호는 i+1 부터 시작된다.
        // 따라서 for 반복문은 index+1 부터 시작한다.
        for (int i = index + 1; i < arr.length; i++) {
            count++;
            findLottoNumber(i, str + SPACE + arr[i]);
        }

        // 백트래킹을 위해서 감소시켜준다.
        // 재귀 호출이 다 끝나면 마지막 재귀 호출에서 count 를 감소시키기 때문에
        // 백트래킹이 가능하다.
        count--;
    }
}
