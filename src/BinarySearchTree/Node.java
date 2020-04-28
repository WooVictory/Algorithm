package BinarySearchTree;

/**
 * created by victory_woo on 2020/04/28
 */
public class Node<Key> {
    private Key key;
    private Node<Key> left, right;

    public Node(Key key) {
        this.key = key;
        setLeft(null);
        setRight(null);
    }

    public Node(Key key, Node<Key> left, Node<Key> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Node<Key> getLeft() {
        return left;
    }

    public void setLeft(Node<Key> left) {
        this.left = left;
    }

    public Node<Key> getRight() {
        return right;
    }

    public void setRight(Node<Key> right) {
        this.right = right;
    }
}

