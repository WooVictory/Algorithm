package cache;

/**
 * created by victory_woo on 2020/03/29
 * 이중 연결 리스트 클래스.
 */
public class DoublyLinkedList {
    private Node headNode; // 첫 번째 노드.
    private Node tailNode; // 마지막 노드.

    public Node getHeadNode() {
        return headNode;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }

    public Node getTailNode() {
        return tailNode;
    }

    public void setTailNode(Node tailNode) {
        this.tailNode = tailNode;
    }
}
