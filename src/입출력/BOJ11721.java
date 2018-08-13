package 입출력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11721 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();

        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));

            if ((i + 1) % 10 == 0)
                System.out.println();

        }

    }
}
