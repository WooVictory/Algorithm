package programmers;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 06/04/2019
 */
public class PGM_EX_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        bw.write(isAnagram(input[0], input[1])+"");
        bw.flush();

    }

    private static boolean isAnagram(String first, String second){
        char[] charWordFirst = first.toLowerCase().toCharArray();
        char[] charWordSecond = second.toLowerCase().toCharArray();

        //listen	silent
        Arrays.sort(charWordFirst);
        Arrays.sort(charWordSecond);

        String wordFirst = new String(charWordFirst);
        String wordSecond = new String(charWordSecond);

        if(wordFirst.equals(wordSecond)){
            return true;
        }

        return false;
    }


    class Solution {
        public boolean solution(String a, String b) {

            boolean[] firstStrAlpha = new boolean[26];
            boolean[] secondStrAlpha = new boolean[26];

            for(int i = 0 ; i < a.length(); i++){
                // 소문자일때
                if(Character.isLowerCase(a.charAt(i))) {
                    firstStrAlpha[Character.toUpperCase(a.charAt(i)) - 'A'] = true;
                }else{
                    firstStrAlpha[a.charAt(i) - 'A'] = true;
                }
            }


            for(int i = 0 ; i < b.length(); i++){
                // 소문자일때
                if(Character.isLowerCase(b.charAt(i))) {
                    secondStrAlpha[Character.toUpperCase(b.charAt(i)) - 'A'] = true;
                }else{
                    secondStrAlpha[b.charAt(i) - 'A'] = true;
                }
            }

            for(int i = 0 ; i < firstStrAlpha.length; i++){
                if(firstStrAlpha[i] != secondStrAlpha[i]){
                    return false;
                }
            }

            return true;
        }
    }
}

