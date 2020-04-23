package 카카오20;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/23
 * 카카오 20 기출.
 * 블록 이동하기.
 * bfs.
 */
public class Test5 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        solution(board);
    }


    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static int solution(int[][] board) {
        int len = board.length;
        boolean[][][][] visit = new boolean[len][len][len][len];
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 1, 0));
        visit[0][0][0][1] = true;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            int hx = cur.hx, hy = cur.hy;
            int tx = cur.tx, ty = cur.ty;
            int time = cur.time;

            if ((hx == len - 1 && hy == len - 1) || (tx == len - 1 && ty == len - 1)) {
                System.out.println(time);
                return time;
            }

            // 회전....


            // 이동.
            for (int i = 0; i < 4; i++) {
                int hnx = hx + dx[i], hny = hy + dy[i];
                int tnx = tx + dx[i], tny = ty + dy[i];

                if (hnx < 0 || hny < 0 || tnx < 0 || tny < 0 || hnx >= len || hny >= len || tnx >= len || tny >= len)
                    continue;

                if (visit[hnx][hny][tnx][tny]) continue;

                visit[hnx][hny][tnx][tny] = true;
                q.add(new Node(hnx, hny, tnx, tny, time + 1));
            }


        }


        int answer = 0;

        return answer;
    }

    static class Node {
        int hx;
        int hy;
        int tx;
        int ty;
        int time;

        public Node(int hx, int hy, int tx, int ty, int time) {
            this.hx = hx;
            this.hy = hy;
            this.tx = tx;
            this.ty = ty;
            this.time = time;
        }
    }
}
