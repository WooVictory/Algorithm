package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/04/14
 * 주사위 윷놀이.
 * 링크드 리스트를 사용해 직접 윷놀이 판을 구현한다.
 */
public class Problem17825 {
    private static int[] dice;
    private static int[] selections;
    private static Node[] markers;
    private static Node start;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        selections = new int[10]; // 중복을 허용하는 순열을 통해서 어떤 말이 선택되었는지 담는 배열.
        dice = new int[10]; // 각 주사위의 값을 담는 배열.
        markers = new Node[4]; // 4개의 말들.
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) dice[i] = Integer.parseInt(in[i]);

        init();
        permutation(0);
    }

    private static void init() {
        start = new Node(0);
        Node end = null;
        Node center = new Node(25); // 윷놀이 판의 가운데 값.

        // 바깥쪽. -> 2의 배수로 채워져있다.
        Node temp = start;
        for (int i = 2; i <= 40; i += 2) {
            temp = temp.addNext(i);
        }

        // 마지막 도착 지점은 자기를 순회하도록 한다.
        end = temp.addNext(0);
        end.next = end;
        end.isEnd = true;

        // 10 -> 13 -> 16 -> 19 -> 25
        Node node = Node.getNode(10);
        node = node.shortcut = new Node(13);
        node = node.addNext(16);
        node = node.addNext(19);
        node.next = center;

        // 20 -> 22 -> 24 -> 25
        node = Node.getNode(20);
        node = node.shortcut = new Node(22);
        node = node.addNext(24);
        node.next = center;

        // 30 -> 28 -> 27 -> 26 -> 25
        node = Node.getNode(30);
        node = node.shortcut = new Node(28);
        node = node.addNext(27);
        node = node.addNext(26);
        node.next = center;

        // 25 -> 30 -> 35 -> 40
        node = center.addNext(30);
        node = node.addNext(35);
        node.next = Node.getNode(40);
    }

    // 중복을 허용하는 순열.
    // selections : 순열을 통해서 각 주사위의 값에 따라 이동할 말이 들어있다.
    private static void permutation(int depth) {
        if (depth == 10) {
            Arrays.fill(markers, start);
            int score = play();

            return;

        }

        for (int i = 0; i < 4; i++) {
            selections[depth] = i;
            permutation(depth + 1);
        }
    }

    private static int play() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            // 현재 선택된 말을 이동시킨다.
            Node current = markers[selections[i]];
            current.isEmpty = true; // 이전에 있던 자리를 비운다.

            for (int j = 0; j < dice[i]; j++) {
                if (j == 0 && current.shortcut != null) {
                    // 현재 위치에 지름길이 존재한다면 그 쪽으로 이동한다.
                    current = current.shortcut;
                } else {
                    // 현재 주사위의 눈 만큼 이동한다.
                    current = current.next;
                }
            }

            markers[selections[i]] = current;

            // 마지막 노드에 도착하지 않았으며, 이미 말이 있는 노드라면
            if (!current.isEmpty && !current.isEnd) {
                return 0;
            } else {
                current.isEmpty = false;
                score += current.value;
            }
        }

        return score;
    }

    static class Node {
        int value;
        boolean isEmpty, isEnd;
        Node next, shortcut;

        public Node(int value) {
            this.value = value;
            this.isEmpty = true;
            this.isEnd = false;
            this.next = null;
            this.shortcut = null;
        }

        Node addNext(int value) {
            Node nextNode = new Node(value);
            this.next = nextNode;
            return nextNode;
        }

        static Node getNode(int target) {
            Node temp = start;

            while (true) {
                if (temp == null) return null;
                if (temp.value == target) return temp;

                temp = temp.next;
            }
        }
    }
}
