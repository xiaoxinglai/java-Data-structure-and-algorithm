package test;

import stack.stack;

public class Stacktest {
    public static void main(String[] args) {
        stack<Integer> stack=new stack();
        stack.InitStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
        System.out.println(stack.Pop());

        int size=stack.StackLength();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.Pop());
        }



    }
}
