package programmers;

import java.io.*;
import java.util.ArrayList;

public class ex1_2 {
    // 소수의 합 - 에라토스테네스의 체 알고리즘 사용
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        ArrayList<Integer> list = new ArrayList<>(N);
        int[] arr = new int[N+1];
        int total = 0;

        for (int i = 2; i <= N; i++)
            arr[i] = i;

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                if (arr[j] != i && arr[j] % i == 0) {

                    arr[j] = 0;
                }
            }
        }

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] != 0)
                total+=arr[i];
        }

        bw.write(total+"");
        bw.flush();
        bw.close();
    }
}
