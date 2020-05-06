package Stack;

import java.util.Stack;

/**
 * created by victory_woo on 2020/05/06
 * Stack 2개를 이용해서 Queue 구현하기.
 */
public class StackWithQueue {
    public static void main(String[] args) {
        StackQueue<String> queue = new StackQueue<>();
        queue.enQueue("A");
        queue.enQueue("B");
        queue.enQueue("C");

        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }

    static class StackQueue<T> {
        private Stack<T> inBox;
        private Stack<T> outBox;

        StackQueue() {
            inBox = new Stack<>();
            outBox = new Stack<>();
        }

        void enQueue(T item) {
            inBox.push(item);
        }

        Object deQueue() {
            if (outBox.isEmpty()) {
                while (!inBox.isEmpty()) {
                    outBox.push(inBox.pop());
                }
            }

            return outBox.pop();
        }
    }
}
