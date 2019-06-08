package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/06/2019
 * bfs : 이모티콘
 */
public class BOJ14226 {
    private static final int MAX = 10000;
    private static boolean[][] visited;
    private static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = parse(br.readLine()); // 만들 이모티콘의 갯수.

        visited = new boolean[MAX + 1][MAX + 1];

        bfs();
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(1, 0, 0));

        while (!q.isEmpty()) {
            Node node = q.remove();

            // 화면에 나타난 이모티콘의 개수와 만들어야 할 이모티콘의 개수가 같다면
            // 정답을 찾은 것이므로 그때의 count 즉, 시간을 출력해준다.
            if (node.value == S) {
                System.out.println(node.count);
                break;
            }

            // 1. copy
            // 화면에 있는 이모티콘을 모두 클립보드에 복사.
            int copyValue = node.value;
            if (!visited[copyValue][copyValue]) {
                q.add(new Node(copyValue, copyValue, node.count + 1));
                visited[copyValue][copyValue] = true;
            }

            // 2. paste
            // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣는다.
            int addedValue = node.value + node.clipboard;
            if (!visited[addedValue][node.clipboard] && addedValue < MAX) {
                q.add(new Node(addedValue, node.clipboard, node.count + 1));
                visited[addedValue][node.clipboard] = true;
            }

            // 3. delete
            // 화면에 있는 이모티콘 중 하나를 삭제한다.
            int deletedValue = node.value - 1;
            if (0 <= deletedValue && !visited[deletedValue][node.clipboard]) {
                q.add(new Node(deletedValue, node.clipboard, node.count + 1));
                visited[deletedValue][node.clipboard] = true;
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int value; // 화면에 보이는 이모티콘의 개수.
        int clipboard; // 클립보드에 저장된 이모티콘의 개수.
        int count; // 이모티콘을 화면에 만드는데 걸리는 시간.

        Node(int value, int clipboard, int count) {
            this.value = value;
            this.clipboard = clipboard;
            this.count = count;
        }
    }
}
