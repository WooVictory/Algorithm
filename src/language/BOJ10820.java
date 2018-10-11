package language;

import java.io.*;
import java.util.Scanner;

public class BOJ10820 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int smallAlphabet = 0;
        int bigAlphabet = 0;
        int number = 0;
        int space = 0;

        while (true) {
            // BufferedReader를 이용한 EOF까지 입력받기

            String word = bf.readLine();
            if (word == null) break;

            smallAlphabet = 0;
            bigAlphabet = 0;
            number = 0;
            space = 0;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == ' ')
                    space++;
                else if (word.charAt(i) >= 65 && word.charAt(i) <= 90)
                    bigAlphabet++;
                else if (word.charAt(i) >= 97 && word.charAt(i) <= 122)
                    smallAlphabet++;
                else if (word.charAt(i) >= 48 && word.charAt(i) <= 57)
                    number++;

            }
            //System.out.println(smallAlphabet + " " + bigAlphabet + " " + number + " " + space);
            bw.write(smallAlphabet + " " + bigAlphabet + " " + number + " " + space + "\n");

        }
        bw.flush();
        bw.close();
        bf.close();


    }
}