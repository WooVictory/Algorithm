package 카카오20;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 자물쇠와 열쇠.
 */
public class RE_Test3 {
    public static void main(String[] args) {

        int[][] key = {
                {0, 0, 0}, {1, 0, 0}, {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        };

        System.out.println(solution(key, lock));
    }

    private static int keySize = 0, lockSize = 0;
    private static int centerStartIndex = 0, centerEndIndex = 0;

    public static boolean solution(int[][] key, int[][] lock) {
        keySize = key.length;
        lockSize = lock.length;
        centerStartIndex = keySize - 1;
        centerEndIndex = centerStartIndex + lockSize;

        int[][] expandedLock = getExpandedLock(lock);
        int to = expandedLock.length - keySize;

        // 1. key 배열을 최대 4번까지 돌릴 수 있기 때문에 전체 4번 탐색한다.
        for (int r = 0; r < 4; r++) {
            for (int row = 0; row <= to; row++) {
                for (int col = 0; col <= to; col++) {

                    if (isFit(expandedLock, row, col, key)) return true;

                    expandedLock = getExpandedLock(lock);
                }
            }

            key = rotate(key);
        }
        return false;
    }

    private static boolean isFit(int[][] expandedLock, int row, int col, int[][] key) {
        int x = 0, y;
        for (int r = row; r < row + keySize; r++) {
            y = 0;
            for (int c = col; c < col + keySize; c++) {
                expandedLock[r][c] += key[x][y++];
            }
            x++;
        }

        for (int i = centerStartIndex; i < centerEndIndex; i++) {
            for (int j = centerStartIndex; j < centerEndIndex; j++) {
                if (expandedLock[i][j] != 1) return false;
            }
        }
        return true;
    }

    private static int[][] rotate(int[][] key) {
        int size = key.length;
        int[][] copy = new int[size][size];

        int col = size - 1;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                copy[j][col] = key[i][j];
            }
            col--;
        }
        return copy;
    }

    private static int[][] getExpandedLock(int[][] lock) {
        int size = lockSize + (keySize - 1) * 2;
        return makeExpandedLock(size, lock);
    }

    private static int[][] makeExpandedLock(int size, int[][] lock) {
        int[][] expandedLock = new int[size][size];

        int x = 0, y;
        for (int i = centerStartIndex; i < centerEndIndex; i++) {
            y = 0;
            for (int j = centerStartIndex; j < centerEndIndex; j++) {
                expandedLock[i][j] = lock[x][y++];
            }
            x++;
        }

        return expandedLock;
    }

}