package 카카오18;

/**
 * created by victory_woo on 2020/05/01
 * 카카오 18 기출.
 * [1차] 프렌즈 4블록.
 */
public class Test5 {
    public static void main(String[] args) {
        //System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        //System.out.println(solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
        System.out.println(solution(4, 4, new String[]{"ABCD", "BACE", "BCDD", "BCDD"}));
    }

    private static char[][] map;

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];

        // 1. 1차원 board -> 2차원 map 배열로 변환.
        for (int i = 0; i < m; i++) {
            String s = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int count;
        // 2x2로 겹치는 블록을 찾고 지워야 할 리스트에 넣는 과정.
        do {
            count = 0;
            boolean[][] visit = new boolean[m][n];

            for (int i = 0; i <= m - 2; i++) {
                for (int j = 0; j <= n - 2; j++) {
                    if (map[i][j] == '.') continue;

                    if (isMatch(i, j, map[i][j])) {
                        for (int r = i; r < i + 2; r++) {
                            for (int c = j; c < j + 2; c++) {
                                if (!visit[r][c]) {
                                    visit[r][c] = true;
                                }
                            }
                        }
                    }
                }
            }

            // 지워야할 블록을 지운다.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j]) {
                        count++;
                        map[i][j] = '.';
                    }
                }
            }

            answer += count;
            findPosition(m, n);
        } while (count != 0);

        print(m, n);

        return answer;
    }

    //
    private static void findPosition(int m, int n) {
        for (int c = 0; c < n; c++) {
            for (int r = m - 1; r >= 0; r--) {
                if (map[r][c] == '.') dropBlock(r, c);
            }
        }
    }

    private static void dropBlock(int r, int c) {
        for (int nr = r - 1; nr >= 0; nr--) {
            if (map[nr][c] != '.') {
                map[r][c] = map[nr][c];
                map[nr][c] = '.';
                break;
            }
        }
    }
    // 2x2 박스 안에 문자가 같은 문자로만 이루어져 있는지 아닌지 확인.

    private static boolean isMatch(int row, int col, char value) {
        for (int r = row; r < row + 2; r++) {
            for (int c = col; c < col + 2; c++) {
                if (map[r][c] != value) return false;
            }
        }
        return true;
    }

    private static void print(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
