package language;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ10811 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = i;


        for (int k = 0; k < M; k++) {
            Stack<Integer> stack = new Stack<>();
            st = new StringTokenizer(bf.readLine(), " ");
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            for (int m = i; m <= j; m++)
                stack.push(arr[m]);

            for (int m = i; m <= j; m++)
                arr[m] = stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++)
            sb.append(arr[i] + " ");

        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        bf.close();
    }

}
