package 카카오18;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 18 기출.
 * 다시 푸는 중.
 * 비밀 지도.
 */
public class RE_Test3 {
    public static void main(String[] args) {
        solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        System.out.println();
        solution(6, new int[]{46, 33, 33, 22, 31, 50}, new int[]{27, 56, 19, 14, 14, 10});
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        // 1. arr1, arr2 배열을 바이너리 형태의 문자열을 갖는 String[] 배열로 변환한다.
        String[] map1 = toBinaryString(arr1, n);
        String[] map2 = toBinaryString(arr2, n);
        String[] answer = new String[n];

        StringBuilder sb;

        // 3. for 반복문을 돌면서 map1, map2 에서 바이너리 한줄을 뽑는다.
        for (int i = 0; i < n; i++) {
            sb = new StringBuilder();

            // 4. for 반복문을 또 돌면서 각 문자를 비교해서 둘 중 하나라도 1이면 벽이므로 '#'를 추가
            // 둘 다 0이라면 빈 공간이므로 ' '를 추가한다.
            for (int j = 0; j < map1[i].length(); j++) {
                char a = map1[i].charAt(j);
                char b = map2[i].charAt(j);
                if (a == '1' || b == '1') sb.append("#");
                else sb.append(" ");
            }

            answer[i] = sb.toString();
            System.out.println(answer[i]);
        }

        return answer;
    }

    // 변환하면서 길이가 n보다 작은 경우, 앞에 0을 추가하여 길이를 n으로 맞춘다.
    private static String[] toBinaryString(int[] a, int n) {
        StringBuilder sb;
        String[] result = new String[n];
        for (int i = 0; i < a.length; i++) {
            sb = new StringBuilder(Integer.toBinaryString(a[i]));
            while (sb.length() < n) sb.insert(0, '0');

            result[i] = sb.toString();
        }

        return result;
    }
}
