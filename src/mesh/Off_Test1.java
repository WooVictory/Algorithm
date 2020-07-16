package mesh;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/07/15
 * 애너그램 문제.
 * 애너그램을 구성하기 위해서 변경해야 하는 문자의 갯수.
 * 123
 */
public class Off_Test1 {
    public static void main(String[] args) {
        String s = "123122"; // 문자열의 구성을 변경하면 애너그램이 되기 때문에 변경해야 하는 문자의 갯수 : 1
        //String s = "123132"; // 문자열의 구성을 변경하면 애너그램이 되기 때문에 변경해야 하는 문자의 갯수 : 0
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        int result = 0;
        int mid = s.length() / 2;
        String first_half = s.substring(0, mid);
        String second_half = s.substring(mid);

        char[] a = first_half.toCharArray();
        char[] b = second_half.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);


        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) result++;

        return result;
    }
}