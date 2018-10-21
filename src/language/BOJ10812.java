package language;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10812 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++)
            arr[i] = i;


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int begin = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int mid = Integer.parseInt(st.nextToken());

            int temp = begin;
            for (int j = mid; j <= end; j++) {
                arr[temp] = j;
                temp++;
            }
            int cnt = (end + 1) - mid;
            System.out.println("cnt : "+cnt);
            temp = cnt + begin;
            System.out.println("temp : "+temp);
            for (int k = begin; k < mid; k++) {
                arr[temp] = k;
                temp++;
            }

        }

        for (int i = 1; i < arr.length; i++)
            System.out.print(arr[i] + " ");

    }
}
