package 카카오18;

/**
 * created by victory_woo on 2020/05/01
 * 카카오 18 기출.
 * [1차] 프렌즈 4블록.
 * 구현.
 */
public class Test5_re {
    public static void main(String[] args) {
        //System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        System.out.println(solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }

    private static char[][] map;

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];

        // 1. board -> 2차원 배열로 옮긴다.
        for (int i = 0; i < m; i++) {
            String s = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        boolean[][] visit;
        int count;

        // 지울 수 있는 블록이 없을 때까지 아래 과정을 반복한다.
        do {
            count = 0;
            visit = new boolean[m][n];
            for (int i = 0; i <= m - 2; i++) {
                for (int j = 0; j <= n - 2; j++) {
                    if (map[i][j] == '.') continue;

                    // 2x2 블록이 동일한 블록을 가졌는지 확인한다.
                    check(i, j, map[i][j], visit);
                }
            }

            // 2중 for 문에서 check()가 끝난 뒤, 지워야 할 블록을 지우고 지운 블록의 개수를 카운트 한다.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j]) {
                        map[i][j] = '.';
                        count++;
                    }
                }
            }

            // 지운 블록의 개수를 누적한다.
            answer += count;
            dropBlock(m, n);

        }
        while (count != 0);


        return answer;
    }

    // 2x2 안에서 모두 같은 블록인지 아닌지 확인한다.
    private static void check(int row, int col, char block, boolean[][] visit) {
        // 하나라도 다른 블록이라면 return.
        for (int r = row; r < row + 2; r++) {
            for (int c = col; c < col + 2; c++) {
                if (map[r][c] != block) return;
            }
        }

        // 모두 같은 블록이라면, visit 배열에 true 로 체크한다.
        // 여기서 visit 배열에 true 표시만 하는 이유는 여기서 map 배열에 존재하는 블록을 지우고 .로 만들게 되면
        // 2x2가 겹치는 부분에 대해서 처리가 안된다.
        // A A A
        // A A A
        // 위의 경우, 2x2가 겹쳐서 6개가 지워질 수 있는데, 바로 여기서 지워버리면 이중 for 문을 순회하면서 다음 좌표 기준으로 2x2를 검사할 때,
        // 위의 for 문에 의해서 . != A이기 때문에 return 된다.
        // 따라서 배열의 값을 지우는 부분은 나중에 처리한다.
        for (int r = row; r < row + 2; r++) {
            for (int c = col; c < col + 2; c++) {
                visit[r][c] = true;
            }
        }
    }

    // 블록을 빈 공간으로 떨어트리는 과정이다.
    // 하나의 열씩 처리하며,가장 밑에서부터 올라오면서 빈 공간인 지점을 찾고
    // 그 지점부터 블록이 있는 공간을 찾을 때까지 반복문을 진행한다.
    // 블록이 있는 지점을 찾았다면 해당 블록을 빈 공간에 놓고, 블록이 있던 지점은 빈 공간으로 만든다.
    // 이렇게 함으로써 위에 있는 블록을 아래의 빈 공간에 떨어트릴 수 있다.
    // 밑에서부터 올라가면서 빈 공간과 블록이 있는 공간을 찾기 때문에 가능하다.
    private static void dropBlock(int m, int n) {
        for (int c = 0; c < n; c++) {
            for (int r = m - 1; r >= 0; r--) {
                if (map[r][c] == '.') {
                    moveBlock(r, c);
                }
            }
        }
    }

    // 블록이 있는 공간을 찾아 빈 공간과 바꾸고, 블록이 있던 공간은 빈 공간으로 바꾼 뒤, 반복문을 빠져나온다.
    private static void moveBlock(int row, int col) {
        for (int nr = row - 1; nr >= 0; nr--) {
            if (map[nr][col] != '.') {
                map[row][col] = map[nr][col];
                map[nr][col] = '.';
                break;
            }
        }
    }
}
