package programmers;

/**
 * created by victory_woo on 2020/03/24
 * 비밀 지도. -> 2017 카카오 블라인드 코딩 테스트 1번.
 * 단순 구현으로 풀 수도 있고, 비트 연산을 이용해서 풀 수도 있다.
 * 시간은 단순 구현이 더 적게 걸린다.
 * 단순 구현 : solution()
 * 비트 연산 : solution2()
 */
public class PGM17681 {
    public static void main(String[] args) {
        solution2(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] second = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(arr1[i]));
            while (sb.length() < n) sb.insert(0, "0");
            answer[i] = sb.toString();
        }

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(arr2[i]));
            while (sb.length() < n) sb.insert(0, "0");
            second[i] = sb.toString();
        }

        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                char a = answer[i].charAt(j);
                char b = second[i].charAt(j);
                if (a == '1' || b == '1') sb.append('#');
                else sb.append(' ');
            }

            result[i] = sb.toString();
            System.out.println(sb.toString());
        }

        return result;
    }

    public static String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            String result = String.format("%" + n + "s", Integer.toBinaryString(arr1[i] | arr2[i]));
            result = result.replace('1', '#');
            result = result.replace('0', ' ');
            answer[i] = result;
        }
        return answer;
    }
}
