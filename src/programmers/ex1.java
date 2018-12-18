package programmers;

import java.io.*;

public class ex1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        int total = 0; // 소수의 합
        Boolean isPrime = false;

        for(int i=2;i<=N;i++){
            isPrime = true;

            for(int j=2;j<i;j++) {
                // 이 부분은 입력받은 수가 1과 자기 자신을 제외한 수로 나누는 과정
                // 이 과정에서 나누어 떨어진다는 것은 소수가 아닌 배수임을 의미
                // 그렇기 때문에 한번 들어오면 어차피 소수가 아님으로 낭비를 줄이고자
                // break문으로 빠져나옴.
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }

            }

            if(isPrime == true)
                total += i;

        }

        bw.write(total+"");
        bw.flush();
        bw.close();
    }
}
