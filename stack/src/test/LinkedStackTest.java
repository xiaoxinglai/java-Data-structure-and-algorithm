package test;

import stack.LinkedStack;

public class LinkedStackTest {

    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack=new LinkedStack();
        linkedStack.InitStack();
        linkedStack.Push(1);
        linkedStack.Push(2);
        linkedStack.Push(3);

        linkedStack.print();

        int size=linkedStack.StackLenth();
        for (int i = 0; i < size; i++) {
            System.out.println(linkedStack.Pop());
        }

    }

}
