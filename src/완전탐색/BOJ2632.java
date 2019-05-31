package 완전탐색;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 31/05/2019
 * 완탐 : 피자 판매
 * 내 풀이 테스트 케이스에 대한 정답은 구했지만, 시간 초과
 * 내 생각에도 좋은 방법은 아니다.
 */
public class BOJ2632 {
    private static final String SPACE = " ";
    private static int[] mArr, nArr;
    private static int m, n, count, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        S = parse(br.readLine()); // 손님이 원하는 피자 크기.
        String[] input = br.readLine().split(SPACE);
        m = parse(input[0]);
        n = parse(input[1]);

        mArr = new int[m];
        nArr = new int[n];

        for (int i = 0; i < m; i++) {
            mArr[i] = parse(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            nArr[i] = parse(br.readLine());
        }

        Arrays.sort(mArr);
        Arrays.sort(nArr);

        dfs(0, 0, mArr, m);
        dfs(0, 0, nArr, n);
        make();

        bw.write(String.valueOf(count));
        bw.flush();

    }

    private static void dfs(int index, int sum, int[] arr, int num) {
        if (index == num) {
            if (sum == S) {
                count++;
            }
            return;
        }

        dfs(index + 1, sum + arr[index], arr, num);
        dfs(index + 1, sum, arr, num);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static void make() {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                sum += mArr[j];
                for (int k = 0; k < n; k++) {
                    int total = sum + nArr[k];
                    if (total == S) {
                        System.out.println("mArr: "+mArr[j]);
                        System.out.println("nArr: " + nArr[k]);
                        count++;
                    }
                }
            }
            sum = 0;
        }
    }
}

/*   for (int i = 0; i < m; i++) {
            System.out.println(mArr[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(nArr[i]);
        }*/