package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 10/07/2019
 * 맥주 마시면서 걸어가기.
 * location 배열 : 상근이네 집, 편의점 위치, 도착 위치를 한꺼번에 관리할 배열.
 */
public class BOJ9205 {

    private static boolean success = false;
    private static int n;
    private static Node[] location;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = parse(br.readLine());

        while (t-- > 0) {
            success = false;
            n = parse(br.readLine());

            location = new Node[n + 2];
            visited = new boolean[n + 2];

            // 상근이네 집, 편의점 위치, 도착 위치를 한번에 배열에 입력받는다.
            // 한 번에 관리하기 위함이다.
            for (int i = 0; i < n + 2; i++) {
                String[] in = br.readLine().split(" ");
                location[i] = new Node(parse(in[0]), parse(in[1]));
            }

            Node start = location[0]; // 상근이네 집.
            Node end = location[n + 1]; // 도착 위치.

            bfs(start, end);

            // success 의 여부에 따라서 happy, sad 를 출력한다.
            if (success)
                System.out.println("happy");
            else
                System.out.println("sad");
        }
    }

    private static void bfs(Node start, Node end) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(start);
        visited[0] = true;

        while (!q.isEmpty()) {
            Node current = q.remove();

            if (current.equals(end)) {
                success = true;
                break;
            }

            // 다음 좌표들에 대해서 탐색을 한다.
            // 맥주 20병으로 갈 수 있는 거리인지와 방문한 적이 없는지를 판단한다.
            for (int i = 1; i < n + 2; i++) {
                if (!visited[i] && isCheck(current, i)) {
                    q.add(location[i]);
                    visited[i] = true;
                }
            }
        }
    }

    // 두 정점의 거리가 1000이하여야 한다.
    // 이유는 50미터에 한 병씩 마시기 때문에 1000미터를 가면 20병을 다 마실 수 있기 때문이다.
    // 그래서 그 거리가 1000보다 작거나 같아야한다.
    // 그렇지 않다면 락 페스티벌 도착 위치까지 가지 못한다.
    private static boolean isCheck(Node current, int i) {
        return Math.abs(current.x - location[i].x) + Math.abs(current.y - location[i].y) <= 1000;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
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
