package programmers;

import java.io.*;
import java.util.ArrayList;

public class ex1_1 {
    // 소수의 합을 구하는 문제 - 다른 방법
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        int total = 0;
        ArrayList<Integer> primeList = new ArrayList<>();
        primeList.add(2);

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < primeList.size(); j++) {
                // 소수는 1과 자기 자신으로만 나누어 떨어지는 수다라는 정의를 잘 기억해야함.
                // 소수가 다른 소수들로 나누어 떨어지면 소수가 아니라 배수임.
                // pirmeList는 소수가 들어가 있는 list인데 그거로 확인함.
                if (i % primeList.get(j) == 0)
                    break; // 소수가 아닌 경우

                if (j + 1 == primeList.size())
                    primeList.add(i);
            }
        }

        for (int i = 0; i < primeList.size(); i++)
            total += primeList.get(i);

        bw.write(total + "");
        bw.flush();
        bw.close();
    }
}
