package cache;

/**
 * created by victory_woo on 2020/03/29
 * 노드 클래스.
 */
public class Node {
    private int id; // 해시테이블 키.
    private String data; // 해시테이블 데이터.
    private Node previousNode; // 이전 노드.
    private Node nextNode; // 다음 노드.

    public Node() {

    }

    public Node(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return id + " - " + data;
    }
}
