package 카카오19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * created by victory_woo on 2020/04/27
 * 카카오 19 기출.
 * 길 찾기 게임.
 */
public class Test5_re {
    public static void main(String[] args) {
        int[][] node_info = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        solution(node_info);
    }

    private static List<Node> nodes = new ArrayList<>();
    private static int index = 0;
    private static Comparator<Node> comp = (a, b) -> {
        if (a.y == b.y) return Integer.compare(a.x, b.x);
        return Integer.compare(b.y, a.y);
    };
    // y 값이 같으면 x 값을 기준으로 작은 수가 먼저 오도록 정렬한다.(x 오름차순)
    // 그렇지 않다면 y 값을 기준으로 큰 값이 먼저 오도록 정렬한다.(y 내림차순)

    public static int[][] solution(int[][] node_info) {
        int size = node_info.length;
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(i + 1, node_info[i][0], node_info[i][1]));
        }

        nodes.sort(comp);
        // nodes 리스트에 담고 정렬 기준에 따라 정렬을 하면
        // root 노드가 제일 앞에 오고 그의 왼쪽, 오른쪽 자식 순으로 정렬된다.

        // 제일 앞에는 root 노드가 온다.
        // root 노드를 기준으로 나머지의 노드를 추가하여 이진 트리를 구성한다.
        Node root = nodes.get(0);
        for (int i = 1; i < size; i++) {
            root.addNode(root, nodes.get(i));
        }

        // 전위 순회, 후위 순회를 하여 answer 배열에 순회한 결과를 담아준다.
        int[][] answer = new int[2][size];
        preOrder(answer, root);
        index = 0;
        postOrder(answer, root);
        return answer;
    }

    // 전위 순회 : root -> L -> R
    private static void preOrder(int[][] answer, Node node) {
        if (node == null) return;

        answer[0][index++] = node.index;
        preOrder(answer, node.left);
        preOrder(answer, node.right);
    }

    // 후위 순회 : L -> R -> root
    private static void postOrder(int[][] answer, Node node) {
        if (node == null) return;

        postOrder(answer, node.left);
        postOrder(answer, node.right);
        answer[1][index++] = node.index;
    }

    // Node 번호와 (x,y) 좌표를 가지고 left, right 노드를 갖는다.
    static class Node {
        int index;
        int x;
        int y;
        Node left;
        Node right;

        Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        // parent 노드에 child 노드를 추가해 이진 트리를 구성한다.
        void addNode(Node parent, Node child) {
            if (child.x < parent.x) { // 왼쪽 자식 노드의 경우.
                // null 이라면 왼쪽 자식 노드에 없기 때문에 왼쪽 자식 노드를 설정해준다.
                if (parent.left == null) parent.left = child;
                else addNode(parent.left, child);
                // null 이 아니라면 왼쪽 자식이 있으므로, 그 왼쪽 자식 노드를 parent 로 하여 addNode()를 호출해
                // 그의 자식 노드로 추가할 수 있는지 다시 시도한다.
            } else { // 오른쪽 자식 노드의 경우.
                // 오른쪽 자식 노드의 경우도 왼쪽 자식 노드를 추가하는 과정과 동일하다.
                if (parent.right == null) parent.right = child;
                else addNode(parent.right, child);
            }
        }
    }
}
