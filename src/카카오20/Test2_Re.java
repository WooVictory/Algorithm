package 카카오20;

/**
 * created by victory_woo on 2020/04/21
 * 카카오 20 기출.
 * 다시 푸는 중!
 * 자물쇠와 열쇠.
 */
public class Test2_Re {
    public static void main(String[] args) {
        int[][] key = {
                {0, 0, 0}, {1, 0, 0}, {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        };
        System.out.println(solution(key, lock));


    }

    private static void sample(){

    }


    private static int centerStartIndex, centerEndIndex;

    private static boolean solution(int[][] key, int[][] lock) {
        int keyLength = key.length;
        int lockLength = lock.length;
        centerStartIndex = keyLength - 1;
        centerEndIndex = centerStartIndex + lockLength;

        // 기존의 lock 배열을 확장한다.
        int[][] expandedLock = getExpandedLock(keyLength, lockLength, lock);


        // key 배열이 총 4번까지 회전할 수 있음.
        // 어디까지만 반복할지 to 변수를 통해 지정한다.
        int to = expandedLock.length - keyLength;

        // key 배열이 시계 방향으로 90도 회전할 수 있으며, 총 4번까지 할 수 있다.
        for (int r = 0; r < 4; r++) {

            // expandedLock 배열에 대해서 to 까지 반복하며 돌 수 있다.
            for (int i = 0; i <= to; i++) {
                for (int j = 0; j <= to; j++) {

                    // 아래 반복문은 key 배열이 움직이면서 expandedLock 배열에 맞추는 동작을 표현한다.
                    // key 배열을 expandedLock 배열에 합치는 과정이다.
                    int x = 0;
                    int y;
                    for (int k = i; k < keyLength + i; k++) {
                        y = 0;
                        for (int l = j; l < keyLength + j; l++) {
                            expandedLock[k][l] += key[x][y++];
                        }
                        x++;
                    }

                    /*for (int k = i; k < keyLength + i; k++) {
                        for (int l = j; l < keyLength + j; l++) {
                            System.out.print(expandedLock[k][l]+" ");
                        }
                        System.out.println();
                    }
                    System.out.println();*/

                    // expandedLock 배열과 key 배열을 맞춰본 뒤, 열쇠가 자물쇠의 홈과 모두 맞물리는지 확인한다.
                    // 열쇠로 자물쇠를 열 수 있으면 true 반환하면서 종료한다.
                    if (isFit(expandedLock)) return true;

                    // 그렇지 않다면
                    // key 배열이 lock 배열과 비교하고 fit 을 확인하고 나서 expandedLock 배열에 값이 더해지므로
                    // expandedLock 배열을 초기화 한다.
                    expandedLock = getExpandedLock(keyLength, lockLength, lock);
                }
            }

            System.out.println("Before rotate");
            print(key);
            // key 배열이 처음부터 끝까지 진행하고 열쇠로 자물쇠를 열 수 없으면
            // key 배열을 시계 방향으로 90도 회전시킨다.
            key = rotate(key);

            System.out.println("After rotate");
            print(key);
        }

        return false;
    }

    // 확장된 배열을 만든다.
    private static int[][] getExpandedLock(int keyLength, int lockLength, int[][] lock) {
        int size = lockLength + (keyLength - 1) * 2;
        return setLockToCenter(new int[size][size], lock);
    }

    // 확장된 배열의 가운데에 lock 배열을 위치시킨다.
    private static int[][] setLockToCenter(int[][] copy, int[][] lock) {
        int x = 0, y;
        for (int i = centerStartIndex; i < centerEndIndex; i++) {
            y = 0;
            for (int j = centerStartIndex; j < centerEndIndex; j++) {
                copy[i][j] = lock[x][y++];
            }
            x++;
        }

        for (int i=0;i<copy.length;i++){
            for (int j=0;j<copy.length;j++){
                System.out.print(copy[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        return copy;
    }

    // 해당 배열의 값이 모두 1이라면 열쇠의 돌기가 자물쇠의 홈이 정확히 일치하므로 열쇠로 자물쇠를 열 수 있다.
    // 따라서 그런 경우에는 true 반환.
    // 그렇지 않은 경우는 false 반환.
    // 여기서 실수한 부분 -> 0 ~ copy.length 까지 돌아서 확인함..
    // 이렇게 확인하는게 아니라 실제로 중앙에 위치한 lock 배열의 값을 확인해야 하기 때문에 centerStartIndex ~ centerEndIndex 까지 돌아야 한다.
    private static boolean isFit(int[][] copy) {
        for (int i = centerStartIndex; i < centerEndIndex; i++) {
            for (int j = centerStartIndex; j < centerEndIndex; j++) {
                if (copy[i][j] != 1) return false;
            }
        }
        return true;
    }

    // key 배열을 시계 방향으로 90도 회전시킨다.
    private static int[][] rotate(int[][] key) {
        int size = key.length;
        int[][] copy = new int[size][size];

        int k = size - 1;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                copy[j][k] = key[i][j];
            }
            k--;
        }

        return copy;
    }

    // key 배열이 잘 돌아가는지 확인용.
    private static void print(int[][] key) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                System.out.print(key[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] deepCopy(int[][] og) {
        int[][] result = new int[og.length][og.length];

        for (int i = 0; i < og.length; i++) {
            System.arraycopy(og[i], 0, result[i], 0, og[0].length);
        }
        return result;
    }
}
