package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/31
 * 맥주 마시면서 걸어가기.
 * bfs/dfs.
 */
public class boj9205 {
    private static int n;
    private static Node[] nodes;
    private static boolean[] visit;
    // 상근이의 위치, 편의점의 위치들, 펜타포트 락 페스티벌의 위치까지 입력은 순서대로 들어온다.
    // 따라서 각 위치의 순서를 순차적으로 index 로 관리하여 방문 여부를 체크한다.
    private static Node start, end;
    private static boolean isSuccess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            isSuccess = false;
            n = toInt(br.readLine());
            // 상근이의 위치와 펜타포트 락 페스티벌의 위치를 추가로 받기 위해 배열의 크기를 확장한다.
            nodes = new Node[n + 2];
            visit = new boolean[n + 2];

            for (int i = 0; i < n + 2; i++) {
                String[] in = br.readLine().split(" ");
                int x = toInt(in[0]), y = toInt(in[1]);
                nodes[i] = new Node(x, y);
            }

            start = nodes[0];
            end = nodes[n + 1];
            //bfs();
            dfs(0);
            System.out.println(isSuccess ? "happy" : "sad");
        }
    }

    // bfs 탐색.
    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(start);
        visit[0] = true;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            if (cur.equals(end)) {
                isSuccess = true;
                break;
            }

            // 편의점의 위치에서 탐색을 시작한다.
            // 1000이하가 아닌 경우에는 큐에 들어가지 않는다.
            for (int i = 1; i < n + 2; i++) {
                if (!visit[i] && isPossible(cur.x, cur.y, nodes[i].x, nodes[i].y)) {
                    visit[i] = true;
                    q.add(nodes[i]);
                }
            }
        }

        System.out.println(isSuccess ? "happy" : "sad");
    }


    private static void dfs(int index) {
        if (visit[index]) return;
        visit[index] = true;

        Node node = nodes[index];
        int x = node.x;
        int y = node.y;

        if (index == nodes.length - 1) {
            isSuccess = true;
            return;
        }

        for (int i = 1; i < n + 2; i++) {
            if (!visit[i] && isPossible(x, y, nodes[i].x, nodes[i].y)) {
                dfs(index + 1);
            }
        }
    }

    private static boolean isPossible(int lx, int ly, int rx, int ry) {
        return (Math.abs(lx - rx) + Math.abs(ly - ry)) <= 1000;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}