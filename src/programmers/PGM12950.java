package programmers;

/**
 * created by victory_woo on 2020/03/23
 * 행렬의 덧셈.
 * 이 문제는 실제로 2차원 배열인 2개의 행렬을 더할 때,
 * 더한 결과의 행렬이 몇 행, 몇 열로 나오는지 알아내는 게 핵심 포인트다.
 * 앞 행렬의 행 x 뒤 행렬의 열 -> 다음 행렬의 행과 열을 결정 짓는다.
 */
public class PGM12950 {
    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {2, 3}};
        int[][] arr2 = {{3, 4}, {5, 6}};

        int[][] arr3 = {{1}, {2}};
        int[][] arr4 = {{3}, {4}};
        System.out.println(solution(arr, arr2));
        System.out.println();
        System.out.println(solution(arr3, arr4));
    }

    // 조금 더 간소화 시킨 버전.
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr2[0].length;
        int[][] answer = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) answer[i][j] = arr1[i][j] + arr2[i][j];
        }

        // 출력 확인용.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
        return answer;
    }
}

