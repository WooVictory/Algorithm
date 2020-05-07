package 카카오19;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 길 찾기 게임.
 */
public class RE_Test4 {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        solution(nodeinfo);
    }

    // y 좌표가 같으면 x 좌표가 작은 순서대로
    // 그렇지 않으면 y 좌표가 큰 순서대로 정렬.
    private static Comparator<Node> comp = (a, b) -> {
        if (a.y == b.y) return a.x - b.x;
        return b.y - a.y;
    };

    public static int[][] solution(int[][] node_info) {
        ArrayList<Node> nodes = new ArrayList<>();

        // 1. node_info -> 리스트에 담으면서 노드의 번호를 저장한다.
        int num = 1;
        for (int[] cord : node_info) {
            nodes.add(new Node(num++, cord[0], cord[1]));
        }

        // 2. nodes 리스트를 트리를 읽는 순서대로 정렬한다.
        nodes.sort(comp);
        // 3. root 노드가 맨 앞으로 위치하기 때문에 root 노드를 가져온다.
        Node root = nodes.get(0);
        // 4. 나머지 노드를 root 노드에 추가하여 이진 트리를 구성한다.
        for (int i = 1; i < nodes.size(); i++) {
            root.addNode(root, nodes.get(i));
        }

        int size = nodes.size();
        int[][] answer = new int[2][size];

        // 5. 전위 순회.
        preOrder(answer, root);
        index = 0;
        // 6. 후위 순회.
        postOrder(answer, root);
        for (int[] a : answer) {
            for (int v : a) System.out.print(v + " ");
            System.out.println();
        }
        return answer;
    }

    private static int index = 0;

    // Root -> L -> R
    private static void preOrder(int[][] answer, Node node) {
        if (node == null) return;

        answer[0][index++] = node.num;
        preOrder(answer, node.left);
        preOrder(answer, node.right);
    }

    // L -> R -> Root
    private static void postOrder(int[][] answer, Node node) {
        if (node == null) return;

        postOrder(answer, node.left);
        postOrder(answer, node.right);
        answer[1][index++] = node.num;
    }

    static class Node {
        int num;
        int x;
        int y;
        Node left, right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        void addNode(Node parent, Node child) {
            // 왼쪽 자식 노드인 경우.
            // 왼쪽 자식이 null 인 경우, 추가할 수 있다.
            // null 이 아닌 경우, 왼쪽 자식을 parent 로 하여 그 노드의 자식으로 추가할 수 있는지 확인한다.
            if (child.x < parent.x) {
                if (parent.left == null) parent.left = child;
                else addNode(parent.left, child);
            } else {
                // 오른쪽 자식 노드인 경우.
                if (parent.right == null) parent.right = child;
                else addNode(parent.right, child);
            }
        }
    }
}