package 카카오18;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 18 기출.
 * 다시 푸는 중.
 * 프렌즈 4블록.
 */
public class RE_Test5 {
    public static void main(String[] args) {
        //System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        System.out.println(solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }

    private static char[][] map;

    public static int solution(int m, int n, String[] board) {
        map = new char[m][n];

        // 1. 1차원 String[] board -> 2차원 map 으로 변환.
        for (int i = 0; i < board.length; i++) {
            int len = board[i].length();
            for (int j = 0; j < len; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        boolean[][] visit;
        int count;
        int answer = 0;

        // 2. 루프를 돌면서 count, visit 배열을 초기화하고 2중 for 반복문을 돌면서
        // 지울 수 있는 2x2 블록을 체크한다.
        do {
            count = 0;
            visit = new boolean[m][n];
            for (int r = 0; r <= m - 2; r++) {
                for (int c = 0; c <= n - 2; c++) {
                    // 이 부분을 건너뛰지 않으면 지워진 블록을 의미하는 문자 '.'가 2x2로 맞으면 지울 수 있는 블록처럼 인식한다.
                    if (map[r][c] == '.') continue;

                    // 해당 좌표부터 2x2 범위 내에 모든 블록이 map[r][c] 블록과 같은지 확인한다.
                    check(r, c, map[r][c], visit);
                }
            }

            // 3. 블록을 지우고, 지울 수 있는 블록의 개수를 카운트한다.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j]) {
                        map[i][j] = '.';
                        count++;
                    }
                }
            }

            System.out.println("블록이 떨어지기 전");
            print(m, n);
            answer += count;
            System.out.println("블록이 떨어지고 난 후");
            // 4. 블록을 떨어트린다.
            dropBlock(n, m);
        } while (count != 0);

        return answer;
    }

    // 2x2 범위 내에 모든 값이 block 과 같은지 확인한다.
    // 같지 않은게 하나라도 있다면 return.
    // 모두 같다면 visit 배열을 true 로 체크한다.
    private static void check(int row, int col, char block, boolean[][] visit) {
        for (int r = row; r < row + 2; r++) {
            for (int c = col; c < col + 2; c++) {
                if (map[r][c] != block) return;
            }
        }

        for (int r = row; r < row + 2; r++) {
            for (int c = col; c < col + 2; c++) {
                visit[r][c] = true;
            }
        }
    }

    // 블록을 떨어트린다.
    // 밑에서부터 올라오면서 빈 공간을 찾는다. 이 빈 공간은 위에 있는 블록이 떨어질 지점이다.
    // 그리고 그 위치부터 위로 올라가면서 블록을 찾는다.
    // 그 블록을 앞에서 찾은 지점에 떨어트리고 블록이 있던 곳은 빈 공간으로 바꾼 뒤, break 를 통해 빠져나온다.
    // 여기서 빠져나오는 이유는 불필요한 탐색을 하지 않고, 같은 열에 빈 공간이 많을 수도 있기 때문에 같은 상황을 처리해주기 위해서이다.
    private static void dropBlock(int n, int m) {
        for (int c = 0; c < n; c++) {
            for (int r = m - 1; r >= 0; r--) {
                if (map[r][c] == '.') {
                    for (int nr = r - 1; nr >= 0; nr--) {
                        if (map[nr][c] != '.') {
                            map[r][c] = map[nr][c];
                            map[nr][c] = '.';
                            break;
                        }
                    }
                }
            }
        }
        print(m, n);
    }

    private static void print(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
