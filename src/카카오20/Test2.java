package 카카오20;

/**
 * created by victory_woo on 2020/04/20
 * 카카오 20 기출.
 * 자물쇠와 열쇠.
 */
public class Test2 {
    public static void main(String[] args) {
        int[][] key = {
                {0, 0, 0}, {1, 0, 0}, {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        };
        System.out.println(solution(key, lock));
    }

    private static int startCenterIndex, endCenterIndex;

    public static boolean solution(int[][] key, int[][] lock) {
        startCenterIndex = key.length - 1;
        endCenterIndex = startCenterIndex + lock.length;

        int[][] copyLock = getExpandedLock(lock, key);

        /*for (int i = 0; i < copyLock.length; i++) {
            for (int j = 0; j < copyLock.length; j++) {
                System.out.print(copyLock[i][j] + " ");
            }
            System.out.println();
        }*/

        // key 배열을 4번까지 회전 가능하기 때문에 4번 회전한다.
        for (int r = 0; r < 4; r++) {
            // 확장된 copyLock 배열에 대해서 어디까지 탐색할 지 정한다.
            int to = copyLock.length - key.length;

            for (int i = 0; i <= to; i++) {
                for (int j = 0; j <= to; j++) {

                    // key.length+1 보다 작은 곳까지 가야지, lock 배열의 끝에서 key 배열 만큼 더 확장해서 탐색이 가능해진다.
                    int x = 0, y;
                    for (int k = i; k < key.length + i; k++) {
                        y = 0;
                        for (int l = j; l < key.length + j; l++) {
                            copyLock[k][l] += key[x][y++];
                            //copyLock[k][l] += lock[x][y++];
                            // 여기서 실수했음.. lock 을 누적하는 게 아니라 key 값을 누적해야 함..!
                        }
                        x++;
                    }

                    if (isFit(copyLock)) {
                        return true;
                    }

                    copyLock = getExpandedLock(lock, key);

                }
            }

            /*System.out.println("Before rotate");
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    System.out.print(key[i][j] + " ");
                }
                System.out.println();
            }*/
            key = rotate(key);

            /*System.out.println("After rotate");
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    System.out.print(key[i][j] + " ");
                }
                System.out.println();
            }*/
        }
        return false;
    }

    private static int[][] rotate(int[][] key) {
        int size = key.length;
        int[][] copyKey = new int[size][size];

        int k = size - 1;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                copyKey[j][k] = key[i][j];
            }
            k--;
        }

        return copyKey;
    }

    private static int[][] getExpandedLock(int[][] lock, int[][] key) {
        int size = lock.length + (key.length - 1) * 2;
        return setLockToCenter(new int[size][size], lock);
    }

    // 잘 구현 됐음.
    private static int[][] setLockToCenter(int[][] copyLock, int[][] lock) {
        int x = 0, y;
        for (int i = startCenterIndex; i < endCenterIndex; i++) {
            y = 0;
            for (int j = startCenterIndex; j < endCenterIndex; j++) {
                copyLock[i][j] = lock[x][y++];
            }
            x++;
        }
        return copyLock;
    }

    private static boolean isFit(int[][] copyLock) {
        for (int i = startCenterIndex; i < endCenterIndex; i++) {
            for (int j = startCenterIndex; j < endCenterIndex; j++) {
                if (copyLock[i][j] != 1) return false;
            }
        }
        return true;
    }

    private static void print(int m, int[][] key) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(key[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}