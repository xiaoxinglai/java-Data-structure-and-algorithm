package test;

import stack.Stack;

public class Stacktest {
    public static void main(String[] args) {
        Stack<Integer> Stack =new Stack();
        Stack.InitStack();
        Stack.push(1);
        Stack.push(2);
        Stack.push(3);
        Stack.print();
        System.out.println(Stack.Pop());

        int size= Stack.StackLength();
        for (int i = 0; i < size; i++) {
            System.out.println(Stack.Pop());
        }



    }
}
