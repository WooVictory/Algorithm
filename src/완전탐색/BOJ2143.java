package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 31/05/2019
 * 완탐 : 두 배열의 합
 * 자꾸 틀리네;;; 잘 모르겠엄..
 */
public class BOJ2143 {
    private static int[] A, B;
    private static ArrayList<Long> aList, bList;
    private static int T, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = parse(br.readLine());
        N = parse(br.readLine());
        A = new int[N];
        String[] num = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = parse(num[i]);
        }

        M = parse(br.readLine());
        B = new int[M];
        num = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            B[i] = parse(num[i]);
        }

        aList = new ArrayList<>();
        bList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            makeList(i, 0, aList, A);
        }

        for (int i = 0; i < M; i++) {
            makeList(i, 0, bList, B);
        }


        Collections.sort(aList);
        Collections.sort(bList);


        int left = 0, right = bList.size() - 1;
        long result = 0;

        while (left < aList.size() && right >= 0) {
            long lv = aList.get(left);
            long rv = bList.get(right);

            if (lv + rv == T) {
                long lc = 0;
                long rc = 0;

                while (left < aList.size() && aList.get(left) == lv) {
                    lc++;
                    left++;
                }

                while (right >= 0 && bList.get(right) == rv) {
                    rc++;
                    right--;
                }

                result += (lc * rc);
            }

            if (lv + rv > T) {
                right--;
            }

            if (lv + rv < T) {
                left++;
            }
        }

        System.out.println(result);
    }

    private static void makeList(int index, long sum, ArrayList<Long> list, int[] arr) {
        if (sum != 0) {
            list.add(sum);
        }

        // 끝에 도달했으면.
        if (index == arr.length) {
            return;
        }

        makeList(index + 1, sum + arr[index], list, arr);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
