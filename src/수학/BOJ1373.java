package 수학;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 21/03/2019
 * 수학 : 2진수 8진수
 */
public class BOJ1373 {
    private static final int ASCII = 48;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder input = new StringBuilder(br.readLine());
        StringBuilder result = new StringBuilder();
        int length = input.length();

        // input의 길이를 확인하고 앞쪽에 부족한 숫자들을 붙여줌.
        switch (length % 3) {
            case 1:
                input.insert(0, "00");
                break;
            case 2:
                input.insert(0, "0");
                break;
        }

        for (int i = 0; i < length; i += 3) {
            result.append(((input.charAt(i) - ASCII) * 4)
                    + ((input.charAt(i + 1) - ASCII) * 2)
                    + ((input.charAt(i + 2) - ASCII)));
        }

        bw.write(result.toString() + "");


        bw.flush();
        bw.close();
        br.close();
    }
}
