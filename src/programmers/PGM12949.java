package programmers;

/**
 * created by victory_woo on 2020/05/27
 * 행렬의 곱셈.
 */
public class PGM12949 {
    public static void main(String[] args) {
        /*int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2 = {{3, 3}, {3, 3}};*/
        int[][] arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] arr2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
        solution(arr1, arr2);
    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer;
        int n = arr1.length; // 앞 행렬의 행.
        int m = arr2[0].length; // 뒤 행렬의 열.
        int l = arr1[0].length;

        answer = new int[n][m];

        for (int row = 0; row < n; row++) {
            int index = 0;
            for (int c = 0; c < m; c++) {
                // col 값을 m과 같은 값으로 가져가면 안된다.
                // 물론 같겠지만, 앞 행렬의 열과 뒤의 행렬의 행 값이 다를 수도 있기 때문에
                // 명시적으로 앞 행렬의 열을 적어줘야 한다.
                for (int col = 0; col < l; col++) {
                    answer[row][c] += (arr1[row][col] * arr2[col][index]);
                }
                index++;
            }
        }

        print(answer);

        return answer;
    }

    private static void print(int[][] a) {
        for (int[] anA : a) {
            for (int anAnA : anA) {
                System.out.print(anAnA + " ");
            }
            System.out.println();
        }
    }
}
