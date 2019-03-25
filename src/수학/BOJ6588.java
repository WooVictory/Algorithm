package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 24/03/2019
 * 수학 : 골드바흐의 추측
 * 에라토스테네스의 체로 소수를 먼저 구하자.(백만 이하의 소수)
 */
public class BOJ6588 {
    private static boolean[] check;
    private static final String NEW_LINE = "\n";
    private static final String ERROR = "Goldbach's conjecture is wrong.";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // false로 초기화됨.
        check = new boolean[1000001];
        getPrime();

        while (true) {
            int num = Integer.parseInt(br.readLine());
            boolean ok = false;
            // 0이 입력되면 종료.
            // 4보다 큰 수에 대해서 조사하는 것이기 때문에.
            if(0<=num && num<=4){
                break;
            }
            /*
            * 짝수에 대해서 진행하기 때문에 num을 2로 나누어서 반만 진행한다.
            * i는 작은 수(a)
            * (num-i)는 큰 수에 해당한다.(b)
            * 따라서 b-a가 가장 큰 수가 출력된다. 분기 처리를 하지 않아도 된다.
            * */
            for (int i = 2; i <= (num / 2); i++) {
                if (!check[i] && !check[num - i]) {
                    bw.write(num + " = " + i + " + " + (num - i) + NEW_LINE);
                    ok = true;
                    break;
                }
            }

            // num이 소수의 합으로 표현될 수 없는 경우.
            if (!ok) {
                bw.write(ERROR + NEW_LINE);
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    // 에라토스테네스의 체를 이용한 소수 구하기.
    private static void getPrime() {
        for (int i = 2; i < check.length; i++) {
            if (check[i]) {
                continue;
            }
            // 소수가 아닌 수들은 true로 체크한다.
            for (int j = i + i; j < check.length; j = j + i) {
                check[j] = true;
            }
        }
    }

}
