package programmers;

/**
 * created by victory_woo on 2020/04/04
 * 정수 삼각형.
 * 유형 : dp.
 */
public class PGM43105 {


    public static void main(String[] args) {
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };
        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = 0;
        int[][] copyTriangle = new int[triangle.length][triangle.length];
        copyTriangle[0][0] = triangle[0][0];

        // 1. 입력을 2차원 배열에 옮겨 담는다. 원본을 유지하며 copy 생성.
        // 2. 양끝, 즉 좌우에는 해당 수만 올 수 있기 때문에 경우의 수가 1개이다. 각각을 더해서 copy 배열에 넣는다.
        for (int i = 1; i < triangle.length; i++) {
            copyTriangle[i][0] = copyTriangle[i - 1][0] + triangle[i][0];
            copyTriangle[i][i] = copyTriangle[i - 1][i - 1] + triangle[i][i];
        }

        // 확인용.
        print(copyTriangle);

        // 3. 채워지지 않은 부분에 대해서 작업을 진행한다.
        // 위의 두 노드 중 큰 값을 triangle[i][j] 값과(현재 위치의 값) 더한 값을 copy[i][j] 값에 넣어준다.
        for (int i = 2; i < triangle.length; i++) {
            for (int j = 1; j < i; j++) {
                copyTriangle[i][j] = Math.max(copyTriangle[i - 1][j - 1], copyTriangle[i - 1][j]) + triangle[i][j];
            }
        }

        // 4. 마지막 줄에는 각 경로를 따라서 온 값들이 담겨 있다.
        // 이 중에서 최댓값을 찾으면 된다.
        for (int i = 0; i < triangle.length; i++) {
            if (answer < copyTriangle[copyTriangle.length - 1][i])
                answer = copyTriangle[copyTriangle.length - 1][i];
        }

        return answer;
    }

    // 값이 제대로 저장되었는지 확인용.
    private static void print(int[][] copy) {
        for (int[] a : copy) {
            for (int value : a) System.out.print(value + " ");
            System.out.println();
        }
    }
}
