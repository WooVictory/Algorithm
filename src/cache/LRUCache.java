package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/03/29
 * LRU 캐시 클래스.
 */
public class LRUCache {
    private int actualSize;
    private Map<Integer, Node> map;
    private DoublyLinkedList linkedList;


    public LRUCache() {
        this.map = new HashMap<>();
        this.linkedList = new DoublyLinkedList();
    }

    // 삽입.
    public void put(int id, String data) {
        // 해당 id 를 키 값으로 가지는 값이 존재하면
        // 기존의 노드를 업데이트 한다.
        if (map.containsKey(id)) {
            Node node = map.get(id);
            node.setData(data);
            update(node);
            return;
        }

        Node newNode = new Node(id, data);
        if (actualSize < Constants.CAPACITY) {
            actualSize++;
            add(newNode);
        } else {
            System.out.println("Cache is Full... Remove Tail");
            removeTail(); // 마지막 노드를 제거.
            add(newNode); // 노드 삽입.
        }
    }

    // 노드 삽입 처리.
    private void add(Node newNode) {

        // 새 노드의 다음 노드를 기존의 head 로 세팅 : 새 노드 -> head 노드.
        newNode.setNextNode(linkedList.getHeadNode());
        newNode.setPreviousNode(null);

        // 기존 head 노드의 이전 노드를 새 노드로 세팅 : 새 노드 <- head 노드.
        if (linkedList.getHeadNode() != null) {
            linkedList.getHeadNode().setPreviousNode(newNode);
        }

        // 새 노드를 head 노드로 세팅 : head 노드 = 새 노드.
        linkedList.setHeadNode(newNode);

        // tail 노드가 없으면(즉, 첫번째로 삽입되는 노드) tail 노드를 새 노드로 세팅.
        // tail 노드 = 새 노드.
        if (linkedList.getTailNode() == null) {
            linkedList.setTailNode(newNode);
        }

        map.put(newNode.getId(), newNode);
    }

    // 연결리스트 변경.
    private void update(Node node) {
        // 이전과 다음 노드를 추출.
        Node prev = node.getPreviousNode();
        Node next = node.getNextNode();

        // prev 가 널이 아니므로 이전 노드가 존재함!
        // 따라서 head 노드는 아님. (middle 노드인 경우!)
        if (prev != null) {
            // 이전 노드 -> 노드 -> 다음 노드.
            // 이전 노드 --------> 다음 노드.
            prev.setNextNode(next);
        } else {
            // prev 가 널이므로 이전 노드가 없음.
            // head 노드인 경우.
            // 노드(head) -> 다음 노드.
            // 노드(head) ->
            linkedList.setHeadNode(next); // 다음 노드를 head 노드로 세팅.
        }

        // next 가 널이 아니므로 다음 노드가 존재함!
        // 따라서 tail 노드는 아님 (middle 노드인 경우!)
        if (next != null) {
            // 이전 노드 <- 노드 <- 다음 노드.
            // 이전 노드 <-------- 다음 노드.
            next.setPreviousNode(prev);
        } else {
            // tail 노드인 경우.
            // 이전 노드 -> 노드(tail)
            // -> 이전 노드(tail)
            linkedList.setTailNode(prev);
        }

        // 노드를 새로 삽입하여 head 노드로 변경.
        add(node);
    }

    // tail 노드 삭제.
    private void removeTail() {

        // tail 노드 추출.
        Node lastNode = map.get(linkedList.getTailNode().getId());

        // tail 노드의 이전 노드를 tail 노드로 변경.
        // 이전 노드 -> tail 노드.
        // 이전 노드(tail)
        linkedList.setTailNode(linkedList.getTailNode().getPreviousNode());

        // 기존의 tail 노드를 null 로 변경.
        if (linkedList.getTailNode() != null) {
            linkedList.getTailNode().setNextNode(null);
        }

        // node 삭제를 하지만 map 에서 제거된 노드를 그대로 유지하기 때문에 map 에서 삭제를 해야할 것 같다.
        map.remove(lastNode.getId());
        // 추출한 기존의 tail 노드를 null 로 초기화.
        lastNode = null;
    }

    // 특정 노드를 반환 + 반환된 노드를 head 노드로 변경.
    public Node get(int id) {
        // id 값에 대응되는 키 값이 map 에 존재하지 않으면 null 반환.
        if (!map.containsKey(id)) {
            return null;
        }

        // id 값에 해당하는 노드 추출.
        Node node = map.get(id);
        // checkNodeRelations(node); 노드의 상태를 확인하기 위한 함수.
        // 추출한 노드를 head 노드로 변경.
        update(node);
        return node;
    }

    public void show() {
        Node actualNode = linkedList.getHeadNode();
        while (actualNode != null) {
            System.out.print(actualNode + " <--> ");
            actualNode = actualNode.getNextNode();
        }
        System.out.println();
    }

    public Node getHead() {
        return linkedList.getHeadNode();
    }

    public Node getTail() {
        return linkedList.getTailNode();
    }

    private void checkNodeRelations(Node node) {
        System.out.println("Current = " + node);
        System.out.println("Prev = " + node.getPreviousNode());
        System.out.println("Next = " + node.getNextNode());
    }

    public int getMapSize() {
        return map.size();
    }

    public void printMap() {
        for (int key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
