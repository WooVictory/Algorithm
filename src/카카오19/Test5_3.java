package 카카오19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created by victory_woo on 2020/04/28
 * 카카오 19 기출.
 * 길 찾기 게임.
 * 이진 트리.
 */
public class Test5_3 {
    public static void main(String[] args) {
        int[][] node_info = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        solution(node_info);
    }

    private static List<Node> nodes = new ArrayList<>();
    private static int index = 0;

    public static int[][] solution(int[][] node_info) {
        int size = node_info.length;
        // 노드의 정보를 리스트에 저장한다.
        for (int i = 0; i < size; i++) nodes.add(new Node(i + 1, node_info[i][0], node_info[i][1]));

        // 정렬 기준에 따라서 정렬한다.
        Collections.sort(nodes);

        // 정렬을 통해서 root 노드가 제일 앞에 위치한다.
        Node root = nodes.get(0);
        // root 노드에 나머지 노드를 추가해 트리를 구성한다.
        for (int i = 1; i < size; i++) root.addNode(root, nodes.get(i));

        int[][] answer = new int[2][size];
        preOrder(answer, root);
        index = 0;
        postOrder(answer, root);
        return answer;
    }

    // 전위 순회 : Root -> L -> R
    private static void preOrder(int[][] answer, Node node) {
        if (node == null) return;

        answer[0][index++] = node.index;
        preOrder(answer, node.left);
        preOrder(answer, node.right);
    }

    // 후위 순회 : L -> R -> Root
    private static void postOrder(int[][] answer, Node node) {
        if (node == null) return;

        postOrder(answer, node.left);
        postOrder(answer, node.right);
        answer[1][index++] = node.index;
    }

    // Node 를 표현하기 위한 클래스.
    // 노드의 번호, 좌표, left, right 노드를 갖는다.
    static class Node implements Comparable<Node> {
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

        // parent 에 child 자식 노드를 추가한다.
        // child.x < parent.x -> 왼쪽 자식 노드가 될 수 있음을 의미한다.
        // 그런 경우, left 가 null 이면 child 는 parent 의 왼쪽 자식이 된다.
        // null 이 아니라면 이미 왼쪽 자식이 있으므로 왼쪽 자식을 parent 로 하여, 추가할 수 있는지 다시 시도한다.
        // 오른쪽도 로직은 같다.
        void addNode(Node parent, Node child) {
            if (child.x < parent.x) {
                if (parent.left == null) parent.left = child;
                else addNode(parent.left, child);
            } else {
                if (parent.right == null) parent.right = child;
                else addNode(parent.right, child);
            }
        }

        // y 좌표는 큰 값이 먼저 오도록 하고, y 값이 같다면 x 좌표가 작은 값이 먼저 오도록 정렬한다.
        // 이를 통해 그림에서 보여주는 트리의 순서로 정렬된다.
        @Override
        public int compareTo(Node that) {
            if (this.y == that.y) return this.x - that.x;
            return that.y - this.y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
