package 트리;

import java.io.*;

/**
 * created by victory_woo on 09/04/2019
 * 트리 : 트리의 순회.
 */
public class BOJ1991 {
    private static int[][] A;
    private static final int A_ASCII = 65;
    //private static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        A = new int[n][2];

        while (n-- > 0) {
            String[] input = br.readLine().split(" ");
            int x = input[0].charAt(0) - A_ASCII; // 부모 노드.
            String y = input[1]; // 왼쪽 자식 노드.

            if (y.equals(".")) { // 자식이 없는 경우.
                A[x][0] = -1;
            } else {
                A[x][0] = y.charAt(0) - A_ASCII;
            }

            String z = input[2]; // 오른쪽 자식 노드.

            if (z.equals(".")) { // 자식이 없는 경우.
                A[x][1] = -1;
            } else {
                A[x][1] = z.charAt(0) - A_ASCII;
            }

        }


        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);


    }

    private static void preOrder(int x) {
        if (x == -1) {
            return;
        }

        System.out.print((char) (x + A_ASCII)); // 노드 방문.
        preOrder(A[x][0]); // 왼쪽 자식 노드를 서브 트리로 전위 순회.
        preOrder(A[x][1]); // 오른쪽 자식 노드를 서브 트리로 전위 순회.
    }

    private static void inOrder(int x) {
        if (x == -1) {
            return;
        }

        inOrder(A[x][0]); // 왼쪽 자식 노드를 서브 트리로 중위 순회.
        System.out.print((char) (x + A_ASCII)); // 노드 방문.
        inOrder(A[x][1]); // 오른족 자식 노드를 서브 트리로 중위 순회.
    }

    private static void postOrder(int x) {
        if (x == -1) {
            return;
        }

        postOrder(A[x][0]);
        postOrder(A[x][1]);
        System.out.print((char) (x + A_ASCII));
    }

}
