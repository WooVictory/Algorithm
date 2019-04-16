package 자료구조.Stack;

/**
 * created by victory_woo on 16/04/2019
 */
public class StackImplement {
    public static void main(String[] args) {
        Stack st = new Stack(20);
        st.push(5);
        st.push(4);
        st.push(3);
        st.push(2);
        st.push(9);

        st.printStack();

        st.pop();
        st.pop();
        st.pop();

        st.printStack();


    }
}

class Stack {
    private int top;
    private Object[] stack;
    private int size;

    public Stack(int size) {
        top = -1;
        stack = new Object[size];
        this.size = size;
    }

    public int length() {
        return this.size;
    }

    public Object peek() {
        return stack[top];
    }

    public void push(int value) {
        stack[++top] = value;
        System.out.println(stack[top] + " PUSH");
    }

    public Object pop() {
        System.out.println(stack[top] + " POP");
        return stack[top--];
    }

    public void printStack() {
        System.out.println("--STACK LIST--");

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }

        System.out.println("--END OF LIST--");
    }

}