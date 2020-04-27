package 카카오19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * created by victory_woo on 2020/04/27
 * 카카오 19 기출.
 * 길 찾기 게임.
 * 정렬된 binary search tree(이진 탐색 트리)
 */
public class Test5 {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        solution(nodeinfo);
    }

    private static List<Node> nodes = new ArrayList<>();
    private static int index = 0;
    // y 좌표가 같으면 x 좌표가 작은 값이 먼저 와야 한다.(x 오름차순)
    // 그렇지 않으면 y 좌표가 큰 값이 먼저 와야 한다.(y 내림차순)
    private static Comparator<Node> compare = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.y == o2.y) return Integer.compare(o1.x, o2.x);

            return Integer.compare(o2.y, o1.y);
        }
    };

    public static int[][] solution(int[][] nodeinfo) {
        // 전체 노드의 갯수.
        int size = nodeinfo.length;
        // 노드를 하나씩 생성하면서 nodes 리스트에 추가한다.
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        nodes.sort(compare);

        // 첫 번째는 무조건 root 노드일 수 밖에 없다.
        // 나머지 노드에 대해서 root 로 이루어진 노드가 하나인 이진 트리에 계속 하나씩 추가할 것이다.
        Node root = nodes.get(0);
        for (int i = 1; i < size; i++) {
            root.addNode(nodes.get(i));
        }

        // 위의 for 문이 끝나면 이진 트리가 완성될 것이다.

        // 전위, 후위를 담기 때문에 2개이다.
        int[][] answer = new int[2][size];
        preOrder(answer, root);
        index = 0;
        postOrder(answer, root);

        return answer;
    }


    // root -> L -> R
    private static void preOrder(int[][] answer, Node node) {
        // null 인 경우, 오면 안되는 것이기 때문에 return.
        if (node == null) return;

        // node 의 id 를 기록하고, 왼쪽 서브트리, 오른쪽 서브트리를 차례로 방문하기 위해 재귀 호출을 이용한다.
        answer[0][index++] = node.index;
        preOrder(answer, node.left);
        preOrder(answer, node.right);
    }

    // L -> R -> root
    private static void postOrder(int[][] answer, Node node) {
        if (node == null) return;

        postOrder(answer, node.left);
        postOrder(answer, node.right);
        answer[1][index++] = node.index;
    }

    // 노드의 번호와 x,y 좌표를 저장한다.
    // 그리고 각 노드는 left, right 링크를 갖는다.
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

        void addNode(Node child) {
            Node parent = this;
            if (child.x < parent.x) {
                if (parent.left == null) parent.left = child;
                else parent.left.addNode(child);
            } else {
                if (parent.right == null) parent.right = child;
                else parent.right.addNode(child);
            }
        }
    }

    // addNode()는 Node 객체에 대해서 이루어지는 동작이기 때문에 Node 클래스 안에서 작성하고 사용하는 게 더 역할이 명확하다.
    private static void addNode(Node parent, Node child) {
        if (child.x < parent.x) { // 왼쪽 자식이 될 수 있는 경우.
            // parent 의 left 가 널이라면 바로 child 가 parent 의 왼쪽 자식이 되면 된다.
            if (parent.left == null) {
                parent.left = child;
            } else {
                // null 값이 아니라면, 그 node 를 parent 로 해서 다시 추가를 시도한다.
                addNode(parent.left, child);
            }
        } else {
            // 큰 경우로, 오른쪽 자식 노드가 될 수 있는 경우.
            if (parent.right == null) {
                parent.right = child;
            } else {
                addNode(parent.right, child);
            }
        }
    }
}
