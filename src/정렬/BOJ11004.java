package 정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 29/03/2019
 * 그냥 퀵 정렬로 돌리면 시간 초과가 발생한다.
 * 퀵 셀렉트라는 알고리즘을 사용해야 한다. 이게 뭐지...?
 * Arrays.sort를 이용하면 정답을 찾을 수 있지만 효율적인 방법이 아니다.
 *
 */
public class BOJ11004 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        bw.write(arr[k-1]+"");
        bw.flush();

        //System.out.println(arr[k-1]);


    }


}
