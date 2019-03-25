package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 25/03/2019
 * 수학 : 소인수 분해.
 */
public class BOJ11653 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());

        // 합성수 m=ab일 때, a 또는 b가 루트 m을 넘을 수 없다.
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                num = num / i;
                sb.append(i).append("\n");
                i--; // 나누어 떨어지는 수에 대해서 다시 나눌 수 있도록 i를 감소시킨다.
            }
        }

        // 위의 for문에서 i*i<=num 조건으로 인해서 마지막에 남은 수가 sb에 추가되지 않는다.
        // 그래서 num에 값이 남아있는 상태로 종료한다.
        // num > 1일 때, num은 i로 나누어 떨어지는 수이므로 i와 같은 작은 수가 남게 된다.
        // 따라서 num을 마지막에 추가해서 출력하면 된다.
        if (num > 1) {
            sb.append(num);
        }

        bw.write(sb.toString() + "");

        bw.flush();
        bw.close();
        br.close();

    }
}
