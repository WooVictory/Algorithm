package 자료구조;

import java.io.*;

public class BOJ10820 {
    // 문자열 분석
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String input;
        int smallAlphabetCount = 0;
        int bigAlphabetCount = 0;
        int numberCount = 0;
        int spaceCount = 0;
        while ((input = bf.readLine()) != null) {
            smallAlphabetCount = 0;
            bigAlphabetCount = 0;
            numberCount = 0;
            spaceCount = 0;
            for (int i = 0; i < input.length(); i++) {
                int index = input.charAt(i);
                if (index >= 65 && index <= 90) {
                    bigAlphabetCount++;
                } else if (index >= 97 && index <= 122) {
                    smallAlphabetCount++;
                } else if (index == 32) {
                    spaceCount++;
                } else if (index >= 48 && index <= 57) {
                    numberCount++;
                }
            }

     /*       System.out.print(smallAlphabetCount + " ");
            System.out.print(bigAlphabetCount + " ");
            System.out.print(numberCount + " ");
            System.out.print(spaceCount + "\n");*/

            // 밑에 코드는 콘솔에서 확인은 불가하지만, 정답은 맞음
            bw.write(smallAlphabetCount + " ");
            bw.write(bigAlphabetCount + " ");
            bw.write(numberCount + " ");
            bw.write(spaceCount + "\n");

        }

        bw.flush();
        bw.close();
        bf.close();
    }
}
