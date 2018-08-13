package 입출력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bf.readLine());
        String number = bf.readLine();
        int sum = 0;
        for (int i = 0; i < test_case; i++) {
            sum += Integer.parseInt(String.valueOf(number.charAt(i)));

        }
        System.out.println(sum);
    }
}
