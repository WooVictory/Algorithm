package 카카오18;

/**
 * created by victory_woo on 2020/04/29
 * 카카오 18 기출.
 * [1차] 비밀지도.
 * 문자열 처리 or 비트 연산.
 */
public class Test1 {
    public static void main(String[] args) {
        solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        solution(6, new int[]{46, 33, 33, 22, 31, 50}, new int[]{27, 56, 19, 14, 14, 10});
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer1 = toBinaryString(arr1);
        String[] answer2 = toBinaryString(arr2);

        print(answer1);
        print(answer2);

        String a, b;
        StringBuilder sb;
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            sb = new StringBuilder();
            a = answer1[i];
            b = answer2[i];
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) == '1' || b.charAt(j) == '1') sb.append('#');
                else sb.append(' ');
            }

            result[i] = sb.toString();
        }

        print(result);

        return result;
    }

    private static String[] toBinaryString(int[] arr) {
        int n = arr.length;
        String[] answer = new String[n];
        StringBuilder sb;
        for (int i = 0; i < n; i++) {
            sb = new StringBuilder(Integer.toBinaryString(arr[i]));
            int len = sb.length();
            while (len < n) {
                sb.insert(0, "0");
                len++;
            }

            answer[i] = sb.toString();
        }
        return answer;
    }

    private static void print(String[] answer) {
        for (String s : answer) System.out.println(s);
        System.out.println();
    }
}
