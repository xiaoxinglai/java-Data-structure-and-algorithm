package test;

import Impl.CircLinkedList;

public class CircLinkedListTest {

    public static void main(String[] args) {

        CircLinkedList circLinkedList=new CircLinkedList();
        circLinkedList.InitList();
        circLinkedList.add(1);
        circLinkedList.add(2);
        circLinkedList.add(3);
        circLinkedList.ListTraverse();

        circLinkedList.ListInsert(3,55);
        circLinkedList.ListTraverse();

        circLinkedList.ListDelete(2);
        circLinkedList.ListTraverse();


    }
}
