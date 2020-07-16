package mesh;

/**
 * created by victory_woo on 2020/07/15
 */
public class Off_Test2 {
    public static void main(String[] args) {
        //String s = "URDR";
        //String s = "UDUD";
        String s = "UDLR";
        System.out.println(solution(s));
    }

    private static final int U = 1;
    private static final int D = -1;
    private static final int R = 2;
    private static final int L = -2;

    private static int solution(String s) {
        int[] result = new int[4];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'U':
                    result[i] = U;
                    break;
                case 'D':
                    result[i] = D;
                    break;
                case 'R':
                    result[i] = R;
                    break;
                case 'L':
                    result[i] = L;
                    break;
            }
        }

        boolean[] visited = new boolean[s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) continue;
            for (int j = i + 1; j < s.length(); j++) {
                if (result[i] + result[j] == 0) {
                    visited[i] = visited[j] = true;
                    count += 2;
                    break;
                }
            }
        }
        return count;
    }
}
