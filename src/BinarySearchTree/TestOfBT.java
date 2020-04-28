package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * created by victory_woo on 2020/04/28
 */
public class TestOfBT {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test1() {
        List<Node> list = new ArrayList<>();
        Node<Integer> root = new Node<>(8);
        setList(list);
        for (Node child : list) addNode(root, child);

        BinaryTree<Integer> bt = new BinaryTree<>(root);
        System.out.print("전위 순회 : ");
        bt.preOrder(bt.getRoot());
        System.out.println();

        System.out.print("중위 순회 : ");
        bt.inOrder(bt.getRoot());
        System.out.println();

        System.out.print("후위 순휘 : ");
        bt.postOrder(bt.getRoot());
        System.out.println();

    }

    private static void test2() {
        Node<Character> n8 = new Node<>('H', null, null);
        Node<Character> n7 = new Node<>('G', null, null);
        Node<Character> n6 = new Node<>('F', null, null);
        Node<Character> n5 = new Node<>('E', n8, null);
        Node<Character> n4 = new Node<>('D', null, n7);
        Node<Character> n3 = new Node<>('C', null, n6);
        Node<Character> n2 = new Node<>('B', n4, n5);
        Node<Character> root = new Node<>('A', n2, n3);

        BinaryTree<Character> bt = new BinaryTree<>(root);
        System.out.println("트리 노드 수 : " + bt.size(bt.getRoot()));
        System.out.println("트리 높이 : " + bt.height(bt.getRoot()));
        System.out.print("전위 순회 : ");
        bt.preOrder(bt.getRoot());
        System.out.println();

        System.out.print("중위 순회 : ");
        bt.inOrder(bt.getRoot());
        System.out.println();

        System.out.print("후위 순회 : ");
        bt.postOrder(bt.getRoot());
        System.out.println();

        System.out.print("레벨 순회 : ");
        bt.levelOrder(bt.getRoot());
        System.out.println();

    }

    private static void setList(List<Node> list) {
        /*list.add(new Node<>('B'));
        list.add(new Node<>('C'));
        list.add(new Node<>('D'));
        list.add(new Node<>('E'));
        list.add(new Node<>('F'));
        list.add(new Node<>('G'));
R        list.add(new Node<>('H'));*/

        list.add(new Node<>(3));
        list.add(new Node<>(10));
        list.add(new Node<>(2));
        list.add(new Node<>(5));
        list.add(new Node<>(14));
        list.add(new Node<>(11));
        list.add(new Node<>(15));
    }

    // 값을 비교할 수 있는 경우에 자식 노드를 추가할 수 있음.
    private static void addNode(Node parent, Node child) {
        if ((Integer) child.getKey() < (Integer) parent.getKey()) {
            if (parent.getLeft() == null) parent.setLeft(child);
            else addNode(parent.getLeft(), child);
        } else {
            if (parent.getRight() == null) parent.setRight(child);
            else addNode(parent.getRight(), child);
        }
    }

}
