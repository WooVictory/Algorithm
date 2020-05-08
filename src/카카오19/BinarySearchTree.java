package 카카오19;

/**
 * created by victory_woo on 2020/04/27
 * 이진 탐색 트리 구현.
 * 삭제 연산이 이해가 안된다....
 * https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Data%20Structure/code/binarySearchTree.java
 */
public class BinarySearchTree {

    public Node root;

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        //트리에 노드를 삽입
        binarySearchTree.insert(3);
        binarySearchTree.insert(8);
        binarySearchTree.insert(1);
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(2);
        binarySearchTree.insert(10);
        binarySearchTree.insert(9);
        binarySearchTree.insert(20);
        binarySearchTree.insert(25);
        binarySearchTree.insert(15);
        binarySearchTree.insert(16);

        System.out.println("트리삽입 결과 : ");
        binarySearchTree.display(binarySearchTree.root);
        System.out.println("");
        System.out.println("이진트리에서 4를 탐색 : " + binarySearchTree.find(4));
        System.out.println("이진트리에서 2를 삭제 : " + binarySearchTree.delete(2));
        binarySearchTree.display(binarySearchTree.root);
        System.out.println("\n이진트리에서 4를 삭제 : " + binarySearchTree.delete(4));
        binarySearchTree.display(binarySearchTree.root);
        System.out.println("\n이진트리에서 10을 삭제 : " + binarySearchTree.delete(10));
        binarySearchTree.display(binarySearchTree.root);
    }

    public BinarySearchTree() {
        this.root = null;
    }

    public class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
            setLeft(null);
            setRight(null);
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    // 탐색 연산.
    // 현재 노드와 찾는 값이 같으면 true 반환.
    // 그렇지 않고 찾는 값이 현재 노드보다 작으면 왼쪽 자식 노드에서 찾도록 한다.
    // 반대쪽은 오른쪽 자식 노드에서 찾도록 한다.
    public boolean find(int id) {
        Node current = root;
        while (current != null) {
            if (current.getData() == id) return true;
            else if (id < current.getData()) current = current.getLeft();
            else current = current.getRight();
        }

        return false;
    }

    // 삽입 연산.
    // while 말고 재귀 호출로도 구현할 수 있음.
    public void insert(int id) {
        Node newNode = new Node(id);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent;

        while (true) {
            parent = current;
            if (id < current.getData()) {
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(newNode);
                    return;
                }
            } else {
                current = current.getRight();
                if (current == null) {
                    parent.setRight(newNode);
                    return;
                }
            }
        }
    }

    // 삭제 연산
    // 1. 자식이 없는 단말 노드 -> 그냥 삭제
    // 2. 자식이 1개인 노드 -> 지워진 노드에 자식을 올리기
    // 3. 자식이 2개인 노드 -> 오른쪽 자식 노드에서 가장 작은 값 or 왼쪽 자식 노드에서 가장 큰 값을 올리기.
    public boolean delete(int id) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;

        while (current.getData() != id) {
            parent = current;
            // 왼쪽 자식 노드인 경우.
            if (id < current.getData()) {
                isLeftChild = true;
                current = current.getLeft();
            } else {
                isLeftChild = false;
                current = current.getRight();
            }

            if (current == null) return false;
        }

        // 1. 자식 노드가 없는 경우.
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) root = null;

            if (isLeftChild) parent.setLeft(null);
            else parent.setRight(null);
        }
        // 2. 1개의 자식 노드를 갖는 경우.
        else if (current.getRight() == null) { // 왼쪽 자식 노드 1개만을 갖는 경우.
            if (current == root) root = current.getLeft();
            else if (isLeftChild) parent.setLeft(current.getLeft());
            else parent.setRight(current.getLeft());
        } else if (current.getLeft() == null) {
            if (current == root) root = current.getRight();
            else if (isLeftChild) parent.setLeft(current.getRight());
            else parent.setRight(current.getRight());
        }

        // 3. 2개의 자식 노드를 갖는 경우.
        else if (current.getLeft() != null && current.getRight() != null) {
            // 오른쪽 서브트리의 최소값을 찾는다.
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }

            successor.setLeft(current.getLeft());
        }
        return true;
    }

    private Node getSuccessor(Node deleteNode) {
        Node successor = null;
        Node successorParent = null;
        Node current = deleteNode.getRight();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }

        if (successor != deleteNode.getRight()) {
            successorParent.setLeft(successor.getRight());
            successor.setRight(deleteNode.getRight());
        }
        return successor;
    }

    public void display(Node root) {
        if (root != null) {
            display(root.getLeft());
            System.out.print(" " + root.getData());
            display(root.getRight());
        }
    }
}
