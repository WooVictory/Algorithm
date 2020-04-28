package BinarySearchTree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * created by victory_woo on 2020/04/28
 */
public class BinaryTree<Key> {
    private Node<Key> root;

    public BinaryTree(Node<Key> root) {
        this.root = root;
    }

    public Node<Key> getRoot() {
        return root;
    }

    // 전위 순회 : Root -> L -> R
    public void preOrder(Node<Key> node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    // 중위 순회 : L -> Root -> R
    void inOrder(Node<Key> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrder(node.getRight());
        }
    }

    // 후위 순회 : L -> R -> Root
    void postOrder(Node<Key> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    void levelOrder(Node<Key> node) {
        LinkedList<Node<Key>> q = new LinkedList<>();
        Node<Key> current;
        q.add(node);

        while (!q.isEmpty()) {
            current = q.remove();
            System.out.print(current.getKey() + " ");
            if (current.getLeft() != null) q.add(current.getLeft());
            if (current.getRight() != null) q.add(current.getRight());
        }
    }

    public int size(Node<Key> node) {
        if (node == null) return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    public int height(Node<Key> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}
